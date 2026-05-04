pipeline {
    agent any

    tools {
        jdk 'JDK17'
        maven 'Maven3'
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/zapatamix/jenkins-esport.git'
            }
        }

        stage('Build & Test') {
            steps {
                bat 'mvn -B clean verify'
            }
        }

        stage('Results') {
            steps {
                junit 'target\\surefire-reports\\*.xml'
            }
        }

        stage('Debug (opcional)') {
            steps {
                bat 'where mvn'
                bat 'java -version'
            }
        }
    }

    post {
        success {
            echo 'Build y tests OK ✅'
        }
        failure {
            echo 'Error en build o tests ❌'
        }
    }
}
