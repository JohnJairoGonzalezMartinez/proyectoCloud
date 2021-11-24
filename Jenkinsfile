pipeline {
    agent any
    tools { 
        maven 'maven'
    }
    stages {
        stage('build') {
            steps {
                dir('src/eurekaserver') {
                    sh 'mvn clean install'
                }
                dir('src/Authentication') {
                    sh 'chmod +x mvnw && ./mvnw clean install'
                }
                dir('src/Feedback') {
                    sh 'chmod +x mvnw && ./mvnw clean install'
                }
                dir('src/Marketplace') {
                    sh 'chmod +x mvnw && ./mvnw clean install'
                }
                dir('src/Services') {
                    sh 'chmod +x mvnw && ./mvnw clean install'
                }
                dir('src/Shopping') {
                    sh 'chmod +x mvnw && ./mvnw clean install'
                }
                dir('src/ShoppingCart') {
                    sh 'chmod +x mvnw && ./mvnw clean install'
                }
                dir('src/Users') {
                    sh 'chmod +x mvnw && ./mvnw clean install'
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
