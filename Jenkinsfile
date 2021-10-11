pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                    git 'https://github.com/kRadecka/ReqresFirtProject.git'
                    sh 'mvn clean install'
            }
        }
        stage('Test') {
            steps {
                sh 'mvn test'
            }

            post {
                always {
                    junit '**/target/TEST-*.xml'
            }
        }
    }
}