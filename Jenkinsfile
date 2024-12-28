pipeline {
    agent any
    environment {
        // Go to docker hub account > Security > Personal Access Tokens
        // Create a token for Jenkins like local-jenkins-dockerhub-token
        // Go to Jenkins > Manage Jenkins > Credentials > System > Global Credentials > Add Credentials
        // Create a credential using the docker hub user name & access token e.g Dockerhub-Cred-Local-Jenkins. Use this cred in this Jenkinsfile like below
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
                // The DOCKERHUB_CREDENTIALS by default has DOCKERHUB_CREDENTIALS_PSW(for password) & DOCKERHUB_CREDENTIALS_USR(for user)
                // Use these to login. The password is pulled from echo & is provided through STDIN. It shows like this in Jenkins logs :
                // + echo ****
                // + docker login -u cp22590 --password-stdin
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