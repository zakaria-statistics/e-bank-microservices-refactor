pipeline {
    agent any

    environment {
        DOCKER_HUB_CREDENTIALS = credentials('dockerhub') // Replace with your Jenkins credentials ID
        DOCKER_HUB_USERNAME = 'zacklordbing1909' // Replace with your Docker Hub username
        KUBECONFIG_CREDENTIALS = credentials('kubeconfig') // Replace with your Kubernetes credentials ID
    }

    stages {
        stage('Build and Push Images') {
            steps {
                script {
                    // Dynamically find all microservices with Dockerfiles
                    def microservices = []
                    def serviceNames = ['account-service', 'angular-client', 'config-service', 'customer-service', 'discovery-service', 'gateway-service']
                    def servicePaths = serviceNames.collect { "microservices/${it}" }
                    writeFile file: 'microservices.txt', text: servicePaths.join('\n')
                    microservices = readFile('microservices.txt').split('\n').findAll { it.trim() }

                    withCredentials([usernamePassword(credentialsId: 'dockerhub', usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')]) {
                        microservices.each { servicePath ->
                            def serviceName = servicePath.tokenize('/').last() // Extract folder name as service name
                            echo "Building and pushing Docker image for ${serviceName}..."
                            dir(servicePath) {
                                sh """
                                    docker build -t ${DOCKER_USER}/${serviceName}:latest .
                                    echo ${DOCKER_PASS} | docker login -u ${DOCKER_USER} --password-stdin
                                    docker push ${DOCKER_USER}/${serviceName}:latest
                                """
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
                        def microservices = readFile('microservices.txt').split('\n').findAll { it.trim() }
                        microservices.each { servicePath -> 
                            def serviceName = servicePath.tokenize('/').last()
                            echo "Deploying ${serviceName} to Kubernetes..."
                            dir("k8s/${serviceName}") {
                                sh """
                                    kubectl apply -f .
                                """
                            }
                        }
                    }
                }
            }
        }
    }
}
