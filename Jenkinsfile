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
                    sh 'mvn clean install'
                }
                dir('src/Feedback') {
                    sh 'mvn clean install'
                }
                dir('src/Marketplace') {
                    sh 'mvn clean install'
                }
                dir('src/Services') {
                    sh 'mvn clean install'
                }
                dir('src/Shopping') {
                    sh 'mvn clean install'
                }
                dir('src/ShoppingCart') {
                    sh 'mvn clean install'
                }
                dir('src/Users') {
                    sh 'mvn clean install'
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
