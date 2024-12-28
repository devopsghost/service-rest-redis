pipeline {
    agent any
    environment {
        DOCKERHUB_CREDENTIALS = credentials('Dockerhub-Cred-Local-Jenkins')
    }
    tools {
        maven 'maven3.9.9'
    }
    stages {
        stage('Build JAR') {
            steps {
                withMaven {
                    sh 'mvn -B -DskipTests clean package'
                }
            }
        }
        stage('Run Tests') {
            steps {
                withMaven {
                    sh 'mvn test'
                }
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }
        stage('Build Image') {
            steps {
                sh 'docker build -t cp22590/service-rest-redis:1.0.0 .'
            }
        }
        stage('Push Image to Dockerhub') {
            steps {
                sh 'echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u $DOCKERHUB_CREDENTIALS_USR --password-stdin'
                sh 'docker push cp22590/service-rest-redis:1.0.0'
            }
        }
    }
    post {
        always {
            sh 'docker logout'
        }
    }
}