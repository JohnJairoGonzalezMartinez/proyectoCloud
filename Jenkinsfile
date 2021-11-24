pipeline {
    agent any
    stages {
        stage('build') {
            steps {
                sh 'chown -R jenkins /var/jenkins_home/workspace/'
                sh 'pwd'
                sh 'whoami'
                sh 'find .'
                dir('src/eurekaserver') {
                    sh 'chmod +r pom.xml'
                    sh 'chmod +r ./mvnw'
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
