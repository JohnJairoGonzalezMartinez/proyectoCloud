pipeline {
    agent any
    stages {
        stage('build') {
            steps {
                dir('src/eureka') {
                    sh './mvnw clean install'
                }
                dir('src/Authentication') {
                    sh './mvnw clean install'
                }
                dir('src/Feedback') {
                    sh './mvnw clean install'
                }
                dir('src/Marketplace') {
                    sh './mvnw clean install'
                }
                dir('src/Services') {
                    sh './mvnw clean install'
                }
                dir('src/Shopping') {
                    sh './mvnw clean install'
                }
                dir('src/ShoppingCart') {
                    sh './mvnw clean install'
                }
                dir('src/Users') {
                    sh './mvnw clean install'
                }
            }
        }
        stage('deploy') {
            steps {
                dir('src'){
                    sh 'docker-compose up --build'
                }
                
            }
        }
    }
}
