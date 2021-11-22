pipeline {
    agent any
    stages {
        stage('build') {
            steps {
                dir('eureka') {
                    sh './mvnw clean install'
                }
                dir('Authentication') {
                    sh './mvnw clean install'
                }
                dir('Feedback') {
                    sh './mvnw clean install'
                }
                dir('Marketplace') {
                    sh './mvnw clean install'
                }
                dir('Services') {
                    sh './mvnw clean install'
                }
                dir('Shopping') {
                    sh './mvnw clean install'
                }
                dir('ShoppingCart') {
                    sh './mvnw clean install'
                }
                dir('Users') {
                    sh './mvnw clean install'
                }
            }
        }
        stage('deploy') {
            steps {
                sh 'docker-compose up --build'
            }
        }
    }
}
