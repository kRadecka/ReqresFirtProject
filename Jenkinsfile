pipeline {
    agent any
    stages{
    stage('SCM Checkout'){      
        git 'https://github.com/kRadecka/ReqresFirtProject.git'
    }
    stage('Compile-Package'){
        def mvnHome = tool name: 'MAVEN_HOME',type:'maven'
        sh "${mvnHome}/bin/mvn package"
    }
    stage('Build test code') {
          steps {
               sh 'mvn clean install -DskipTests'
            }
    }
     stage('Execute test') {
          steps {
              sh 'mvn -Dtest= RunAllTests test'
          }
     }
    }
}
