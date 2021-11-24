pipeline {
    agent any
    tools { 
        maven 'maven'
    }
    stages {
        stage('build') {
            steps {
                dir('src/eurekaserver') {
                    sh 'mvn clean compile'
                }
                dir('src/Authentication') {
                    sh 'mvn clean compile'
                }
                dir('src/Feedback') {
                    sh 'mvn clean compile'
                }
                dir('src/Marketplace') {
                    sh 'mvn clean compile'
                }
                dir('src/Services') {
                    sh 'mvn clean compile'
                }
                dir('src/Shopping') {
                    sh 'mvn clean compile'
                }
                dir('src/ShoppingCart') {
                    sh 'mvn clean compile'
                }
                dir('src/Users') {
                    sh 'mvn clean compile'
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
