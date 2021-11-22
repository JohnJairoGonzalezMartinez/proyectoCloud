pipeline {
    agent any
    stages {
        stage('build') {
            steps {
                dir('eureka') {
                    sh './mvn clean install'
                }
                dir('Authentication') {
                    sh './mvn clean install'
                }
                dir('Feedback') {
                    sh './mvn clean install'
                }
                dir('Marketplace') {
                    sh './mvn clean install'
                }
                dir('Services') {
                    sh './mvn clean install'
                }
                dir('Shopping') {
                    sh './mvn clean install'
                }
                dir('ShoppingCart') {
                    sh './mvn clean install'
                }
                dir('Users') {
                    sh './mvn clean install'
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
