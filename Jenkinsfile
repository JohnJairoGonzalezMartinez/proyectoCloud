pipeline {
    agent any
    tools{
        maven 'maven'
    }
    stages {
        stage('build') {
            steps {
                dir('src/eurekaserver') {
                    sh 'find .'
                    sh 'mvn clean package'
                    sh 'docker -t eurekaserverimage .'
                    sh 'docker run -it --rm --name eurekaserver --network=marketplace-net -p 18761:8761 eurekaserverimage'
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
    }
}
