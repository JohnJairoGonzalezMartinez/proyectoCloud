pipeline {
    agent {
            docker {image 'mongodb'}
        }
    tools { 
        maven 'maven'
        dockerTool 'docker'
    }
    environment {
        registryName = "proyectocloud"
        registryCredential = 'e8e377ca-7917-43b8-aa7a-5e92fe562d1b'
        registryUrl = 'proyectocloud.azurecr.io'
    }
    stages {
        stage('build') {
            steps {
                dir('src/MongoDB'){
                    script {
                        dockerImage = docker.build registryName
                    }
                }

                dir('src/eurekaserver'){
                    sh 'mvn clean compile'
                    script {
                        dockerImage = docker.build registryName
                    }
                    
                }
                /*
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
                */
            }
        }
        stage('Upload Image to ACR') {
            steps{   
                script {
                    docker.withRegistry( "http://${registryUrl}", registryCredential ) {
                    dockerImage.push()
                    }
                }
            }
        }

        stage('stop previous containers') {
            steps {
                sh 'docker ps -f name=mypythonContainer -q | xargs --no-run-if-empty docker container stop'
                sh 'docker container ls -a -fname=mypythonContainer -q | xargs -r docker container rm'
            }
        }
      
        stage('Docker Run') {
            steps{
                script {
                    sh 'docker run -d -p 8096:5000 --rm --name mypythonContainer ${registryUrl}/${registryName}'
                }
            }
        }

    }
}
