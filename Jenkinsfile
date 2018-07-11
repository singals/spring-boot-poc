pipeline {
  agent { docker { image 'java:8' } }

  options {
      timestamps()
      buildDiscarder(logRotator(numToKeepStr: '10'))
  }

  stages {
      stage('Build & Test Project') {
        steps {
          sh './gradlew clean build'

          junit 'build/test-results/test/*.xml'
        }
      }
  }
}