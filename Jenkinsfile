pipeline {
    agent any
    tools{
        maven 'maven'
    }
    stages {
        stage('build') {
            steps {
                dir('src/eurekaserver') {
                    when { changeset "/*"}
                    bat 'echo executed'
                    bat 'mvn clean package -DskipTests'
                }
                dir('src/Authentication') {
                    bat 'mvn clean package -DskipTests'
                }
                dir('src/Feedback') {
                    bat 'mvn clean package -DskipTests'
                }
                dir('src/Marketplace') {
                    bat 'mvn clean package -DskipTests'
                }
                dir('src/Services') {
                    bat 'mvn clean package -DskipTests'
                }
                dir('src/Shopping') {
                    bat 'mvn clean package -DskipTests'
                }
                dir('src/ShoppingCart') {
                    bat 'mvn clean package -DskipTests'
                }
                dir('src/Users') {
                    bat 'mvn clean package -DskipTests'
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
