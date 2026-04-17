pipeline {
    agent any
    
    tools {
    maven 'M3' 
    jdk 'JDK11'
}
    
    environment {
        DOCKER_IMAGE = 'your-dockerhub-username/hotel-booking' // Change this
        DOCKER_TAG = "${BUILD_NUMBER}"
    }
    
    stages {
        stage('1. Checkout') {
            steps {
                echo '📥 Downloading code from GitHub...'
                git branch: 'main', url: 'https://github.com/your-username/hotel-booking-system.git'
            }
        }
        
        stage('2. Build') {
            steps {
                echo '🔨 Compiling code...'
                bat 'mvn clean compile' // 'bat' is used for Windows, 'sh' for Linux
            }
        }
        
        stage('3. Test') {
            steps {
                echo '🧪 Running JUnit Tests...'
                bat 'mvn test'
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml' // This shows test results in Jenkins UI
                }
            }
        }
        
        stage('4. Package') {
            steps {
                echo '📦 Creating JAR file...'
                bat 'mvn package'
            }
        }
        
        stage('5. Docker Build & Push') {
            steps {
                echo '🐳 Containerizing application...'
                // Login and Push using the credentials ID we created in Phase 1
                withCredentials([usernamePassword(credentialsId: 'dockerhub-credentials', 
                                                 passwordVariable: 'PASS', 
                                                 usernameVariable: 'USER')]) {
                    bat "docker login -u %USER% -p %PASS%"
                    bat "docker build -t ${DOCKER_IMAGE}:${DOCKER_TAG} ."
                    bat "docker tag ${DOCKER_IMAGE}:${DOCKER_TAG} ${DOCKER_IMAGE}:latest"
                    bat "docker push ${DOCKER_IMAGE}:${DOCKER_TAG}"
                    bat "docker push ${DOCKER_IMAGE}:latest"
                }
            }
        }
        
        stage('6. K8s Deployment') {
            steps {
                echo '☸️ Deploying to Kubernetes...'
                bat 'kubectl apply -f deployment.yaml'
                bat 'kubectl apply -f service.yaml'
            }
        }
    }
    
    post {
        success { echo '✅ PROJECT DEPLOYED SUCCESSFULLY!' }
        failure { echo '❌ PIPELINE FAILED! Check console output.' }
    }
}
