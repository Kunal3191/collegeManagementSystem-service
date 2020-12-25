pipeline {
    agent any
    stages {
        stage('Java Version') {
            steps {
                bat 'java -version'
            }
        }
        stage('Clean and Test') {
            steps {
                bat 'mvn clean test'
            }
        }
    }
}