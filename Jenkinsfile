pipeline {
    //agent any
    agent {
        dockerfile true
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
                docker build -t cp22590/service-rest-redis:1.0.0 .
            }
        }
    }
}