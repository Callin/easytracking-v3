pipeline {
   agent any
   stages {
      stage('Checkout') {
          steps {
            sh '''#!/bin/bash
                echo 'Removing existing project directory'
                rm -rf easytracking-v3
                echo 'Cloning git project'
                git clone 'https://github.com/Callin/easytracking-v3.git'
            '''
          }
      }
      stage('Build') {
          steps {
            sh '''#!/bin/bash
                echo 'Running mvn clean package'
                cd easytracking-v3
                mvn clean package -DskipTests
            '''
            }
      }
      stage('Deploy') {
          steps {
            sh '''#!/bin/bash
                echo 'Copying new version to temporary directory'
                cp -R target/easytracking-v3/*.jar /home/dragos/apps/easytracking/backend-new
                echo "Rename the current version to old"
                cd /home/dragos/apps/easytracking
                if [ -d backend-old ]; then rm -Rf backend-old; fi
                mv backend backend-old
                echo "Rename the new version to current"
                mv backend-new backend
                cd /home/dragos/apps/easytracking/backend
                kill -9 $(lsof -t -i:6000)
                nohup java -jar /home/dragos/apps/easytracking/backendeasytracking-v3-0.0.1-SNAPSHOT.jar &
            '''
        }
      }
    }
}