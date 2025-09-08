pipeline {
  agent any
  stages {
    stage('Build') { steps { sh 'for d in user-service appointment-service inventory-service billing-service auth-service notification-service; do (cd $d && ./gradlew clean build -x test); done' } }
    stage('Docker Build') { steps { sh 'echo Docker build steps here (build and push images)' } }
  }
}
