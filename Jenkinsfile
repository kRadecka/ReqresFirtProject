pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                    git 'https://github.com/kRadecka/ReqresFirtProject.git'
                    sh './mvnw clean compile'
            }
        }
        stage('Test') {
            steps {
                sh './mvnw test'
            }

            post {
                always {
                    junit '**/target/TEST-*.xml'
            }
        }
    }
}