pipeline {
    agent any
    tools{
        maven 'maven'
    }
    stages {
        stage('build') {
            steps {
                dir('src/eurekaserver') {
                    bat 'mvn clean package'
                }
                dir('src/Authentication') {
                    bat 'mvn clean package'
                }
                dir('src/Feedback') {
                    bat 'mvn clean package'
                }
                dir('src/Marketplace') {
                    bat 'mvn clean package'
                }
                dir('src/Services') {
                    bat 'mvn clean package'
                }
                dir('src/Shopping') {
                    bat 'mvn clean package'
                }
                dir('src/ShoppingCart') {
                    bat 'mvn clean package'
                }
                dir('src/Users') {
                    bat 'mvn clean package'
                }
            }
        }
        stage('deploy'){
            steps{
                dir('src'){
                    bat 'docker-compose down'
                    bat 'docker-compose up --build -d'
                }
            }
        }
    }
}
