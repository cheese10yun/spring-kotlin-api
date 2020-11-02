pipeline {
  agent any

  options {
          buildDiscarder(logRotator(daysToKeepStr: '3', numToKeepStr: '3'))
  }

    stages {

        stage('Start') {
            agent any
            steps {
                slackSend (channel: '#jenkins', color: '#FFFF00', message: "start : Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]' (${env.BUILD_URL})")
            }
        }
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Test') {
            steps {
                sh "./gradlew test"
            }
        }

//         stage('Build') {
//             steps {
//                 sh "./gradlew --build-cache clean build"
//             }
//         }
    }

     post {
        success {
            slackSend (channel: '#jenkins', color: '#00FF00', message: "success : Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]' (${env.BUILD_URL})")
        }
        failure {
            slackSend (channel: '#jenkins', color: '#FF0000', message: "failed : Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]' (${env.BUILD_URL})")
        }
    }
}