pipeline {
  agent any

  options {
      timestamps()
      buildDiscarder(logRotator(numToKeepStr: '10'))
  }

  stages {
      stage('Build & Test Project') {
        steps {
          sh gradlew clean build
        }
      }
  }

  post {
          always {
              archiveArtifacts artifacts: 'build/libs/*.jar', fingerprint: true
              junit 'build/reports/tests/test/index.html'
          }
      }
}