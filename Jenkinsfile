pipeline {
    agent any

    /*options {
        skipDefaultCheckout()
    }*/

    environment {
        DOCKER_HUB_CREDENTIALS = credentials('dockerhub') // Replace with your Jenkins credentials ID
        DOCKER_HUB_USERNAME = 'zacklordbing1909' // Replace with your Docker Hub username
        KUBECONFIG_CREDENTIALS = credentials('kubeconfig') // Replace with your Kubernetes credentials ID
        DOCKER_HOST = "tcp://docker-daemon.cicd.svc.cluster.local:2375"
    }

    stages {
        /*stage('Clean Workspace') {
            steps {
                cleanWs()
            }
        }*/
        /*stage('Checkout Code') {
            steps {
                git branch: 'master', url: 'https://github.com/zakaria-statistics/e-bank-microservices-refactor.git'
                echo "Listing contents of the workspace after checkout..."
                sh "ls -la"
                sh "ls -la . || echo '. directory not found'"
            }
        }*/
        stage('Build and Push Images') {
            steps {
                script {
                    // Dynamically find all microservices with Dockerfiles
                    def microservices = []
                    def serviceNames = [/*'account-postgres', 'account-postgres', 'angular-client', 'config-service', 'customer-mysql', 'customer-service', 'discovery-service',*/ 'gateway-service']
                    def servicePaths = serviceNames.collect { serviceName ->
                        def path = serviceName // Adjusted to match the folder structure
                        if (fileExists(path)) {
                            return path
                        } else {
                            echo "Directory ${path} does not exist. Skipping."
                            return null
                        }
                    }.findAll { it != null }
                    echo "Resolved servicePaths: ${servicePaths}"
                    writeFile file: 'microservices.txt', text: servicePaths.join('\n')
                    microservices = readFile('microservices.txt').split('\n').findAll { it.trim() }

                    withCredentials([usernamePassword(credentialsId: 'dockerhub', usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')]) {
                        microservices.each { servicePath ->
                            def serviceName = servicePath.tokenize('/').last() // Extract folder name as service name
                            echo "Building and pushing Docker image for ${serviceName}..."
                            echo "Resolved servicePath: ${servicePath}"
                            sh "ls -la ${servicePath}" // List files in the resolved servicePath for debugging
                            dir(servicePath) {
                                echo "Checking current directory: ${pwd()}"
                                sh "ls -la" // List files in the current directory for debugging
                                if (fileExists('Dockerfile')) {
                                    echo "Dockerfile found in ${servicePath}"
                                    sh """
                                        docker build -t ${DOCKER_USER}/${serviceName}:latest .
                                        echo ${DOCKER_PASS} | docker login -u ${DOCKER_USER} --password-stdin
                                        docker push ${DOCKER_USER}/${serviceName}:latest
                                    """
                                } else {
                                    error "Dockerfile not found in ${servicePath}. Skipping build for ${serviceName}."
                                }
                            }
                        }
                    }
                }
            }
        }

        stage('Deploy to Kubernetes') {
            steps {
                script {
                    withCredentials([file(credentialsId: 'kubeconfig', variable: 'KUBECONFIG')]) {
                        def serviceNames = ['account-service', 'angular-service', 'config-service', 'customer-service', 'discovery-service', 'gateway-service']
                        serviceNames.each { serviceName ->
                            echo "Deploying ${serviceName} to Kubernetes..."
                            sh 'kubectl apply -f k8s/' + serviceName + '.yml --kubeconfig=' + KUBECONFIG
                        }
                    }
                }
            }
        }
    }
}
