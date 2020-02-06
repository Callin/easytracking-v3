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
                cp -R easytracking-v3/target /home/dragos/apps/easytracking/backend-new
                echo "Rename the current version to old"
                cd /home/dragos/apps/easytracking
                if [ -d backend-old ]; then rm -Rf backend-old; fi
                if [ -d backend ]; then mv backend backend-old; fi
                echo "Rename the new version to current"
                mv backend-new backend
                cd /home/dragos/apps/easytracking/backend
                echo "Kill the old process"
                output=`lsof -i :6000`
                if [ ${#output} != 0 ]; then
                    echo "Port 6000 is already used. Terminating the process"
                    pkill -f "java"
                else
                    echo "Port 6000 is not in use"
                fi
                echo "Start the new process "
                echo $WORKSPACE
                echo $PWD
                java -jar easytracking-v3-0.0.1-SNAPSHOT.jar &
            '''
        }
      }
    }
}
