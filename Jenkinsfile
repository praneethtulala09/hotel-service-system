pipeline {
    agent any
    
    tools {
        maven 'M3'
        jdk 'JDK11'
    }
    
    stages {
        stage('1. Checkout') {
            steps {
                echo '📥 Code already checked out by Jenkins'
                bat 'dir'
            }
        }
        
        stage('2. Build') {
            steps {
                echo '🔨 Compiling...'
                bat 'mvn clean compile'
            }
        }
        
        stage('3. Test') {
            steps {
                echo '🧪 Running tests...'
                bat 'mvn test'
            }
        }
        
        stage('4. Package') {
            steps {
                echo '📦 Creating JAR...'
                bat 'mvn package'
            }
        }
    }
    
    post {
        success { echo '✅ BUILD SUCCESSFUL!' }
        failure { echo '❌ BUILD FAILED!' }
    }
}
