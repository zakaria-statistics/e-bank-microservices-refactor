pipeline {
    agent any

    environment {
        DOCKER_HUB_CREDENTIALS = credentials('dockerhub') // Replace with your Jenkins credentials ID
        DOCKER_HUB_USERNAME = 'zacklordbing1909' // Replace with your Docker Hub username
        KUBECONFIG_CREDENTIALS = credentials('kubeconfig') // Replace with your Kubernetes credentials ID
    }

    stages {
        stage('Build and Push Images') {
            parallel {
                /*stage('Build and Push Gateway Service') {
                    steps {
                        dir('gateway-service') {
                            script {
                                if (fileExists('Dockerfile')) {
                                    echo "Building and pushing Gateway Service..."
                                    sh """
                                        docker build -t ${DOCKER_HUB_USERNAME}/gateway-service:latest .
                                        echo ${DOCKER_HUB_CREDENTIALS_PSW} | docker login -u ${DOCKER_HUB_USERNAME} --password-stdin
                                        docker push ${DOCKER_HUB_USERNAME}/gateway-service:latest
                                    """
                                } else {
                                    error "Dockerfile not found in gateway-service. Skipping build."
                                }
                            }
                        }
                    }
                }
                stage('Build and Push Account Service') {
                    steps {
                        dir('account-service') {
                            script {
                                if (fileExists('Dockerfile')) {
                                    echo "Building and pushing Account Service..."
                                    sh """
                                        docker build -t ${DOCKER_HUB_USERNAME}/account-service:latest .
                                        echo ${DOCKER_HUB_CREDENTIALS_PSW} | docker login -u ${DOCKER_HUB_USERNAME} --password-stdin
                                        docker push ${DOCKER_HUB_USERNAME}/account-service:latest
                                    """
                                } else {
                                    error "Dockerfile not found in account-service. Skipping build."
                                }
                            }
                        }
                    }
                }
                stage('Build and Push Customer Service') {
                    steps {
                        dir('customer-service') {
                            script {
                                if (fileExists('Dockerfile')) {
                                    echo "Building and pushing Customer Service..."
                                    sh """
                                        docker build -t ${DOCKER_HUB_USERNAME}/customer-service:latest .
                                        echo ${DOCKER_HUB_CREDENTIALS_PSW} | docker login -u ${DOCKER_HUB_USERNAME} --password-stdin
                                        docker push ${DOCKER_HUB_USERNAME}/customer-service:latest
                                    """
                                } else {
                                    error "Dockerfile not found in customer-service. Skipping build."
                                }
                            }
                        }
                    }
                }
                stage('Build and Push Config Service') {
                    steps {
                        dir('config-service') {
                            script {
                                if (fileExists('Dockerfile')) {
                                    echo "Building and pushing Config Service..."
                                    sh """
                                        docker build -t ${DOCKER_HUB_USERNAME}/config-service:latest .
                                        echo ${DOCKER_HUB_CREDENTIALS_PSW} | docker login -u ${DOCKER_HUB_USERNAME} --password-stdin
                                        docker push ${DOCKER_HUB_USERNAME}/config-service:latest
                                    """
                                } else {
                                    error "Dockerfile not found in config-service. Skipping build."
                                }
                            }
                        }
                    }
                }
                stage('Build and Push Angular Client') {
                    steps {
                        dir('angular-client') {
                            script {
                                if (fileExists('Dockerfile')) {
                                    echo "Building and pushing Angular Client..."
                                    sh """
                                        docker build -t ${DOCKER_HUB_USERNAME}/angular-client:latest .
                                        echo ${DOCKER_HUB_CREDENTIALS_PSW} | docker login -u ${DOCKER_HUB_USERNAME} --password-stdin
                                        docker push ${DOCKER_HUB_USERNAME}/angular-client:latest
                                    """
                                } else {
                                    error "Dockerfile not found in angular-client. Skipping build."
                                }
                            }
                        }
                    }
                }*/
                stage('Build and Push Discovery Service') {
                    steps {
                        dir('discovery-service') {
                            script {
                                if (fileExists('Dockerfile')) {
                                    echo "Building and pushing Discovery Service..."
                                    sh """
                                        docker build -t ${DOCKER_HUB_USERNAME}/discovery-service:latest .
                                        echo ${DOCKER_HUB_CREDENTIALS_PSW} | docker login -u ${DOCKER_HUB_USERNAME} --password-stdin
                                        docker push ${DOCKER_HUB_USERNAME}/discovery-service:latest
                                    """
                                } else {
                                    error "Dockerfile not found in discovery-service. Skipping build."
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
                        def serviceNames = [/*'account-postgres', 'customer-mysql',*/ 'discovery-service'/*, 'config-service', 'customer-service', 'account-service', 'gateway-service', 'angular-service'*/]
                        serviceNames.each { serviceName ->
                            echo "Deploying ${serviceName} to Kubernetes..."
                            sh "kubectl apply -f k8s/${serviceName}.yml --kubeconfig=${KUBECONFIG}"
                        }
                    }
                }
            }
        }
    }
}
