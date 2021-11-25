pipeline {
    agent any
    tools{
        maven 'maven'
        docker 'docker'
    }
    stages {
        stage('build') {
            steps {
                dir('src/eurekaserver') {
                    sh 'ls'
                    sh 'mvn clean compile'
                    sh 'docker build -t eureka-iamge .'
                    sh 'docker run -it --rm eureka --network=marketplace-net -p 18761:8761 eureka-image'
                }

                /*
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
                */
            }
        }
        stage('deploy') {
            steps {
                sh 'docker-compose up --build'
            }
        }
    }
}
