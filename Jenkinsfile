pipeline {
	agent any
	environment {
		DOCKER_IMAGE = 'ambarbhore1234/rmmagent'
		KUBECONFIG_CRED_ID = 'kubeconfig'
	}
	
	stages {
		stage('clone repo') {
			steps {
				git branch: 'main', credentialsId: 'github-config', url: 'https://github.com/AmbarBhore/RMM-Agent-3.1.git'			}
		}
		stage('Build') {
			steps {
				sh 'mvn clean package'
			}
		}
		stage('Image push to docker-hub') {
			steps {
				withCredentials([usernamePassword(credentialsId: 'dockerhub', usernameVariable: 'DOCKER_USERNAME', passwordVariable: 'DOCKER_PASSWORD')]) {
					sh '''
					   echo "Building an docker image : $DOCKER_IMAGE"
					   docker build -t $DOCKER_IMAGE:rmmagent .
						
					   echo "Logging into the Docker hub"
				           echo "$DOCKER_PASSWORD" | docker login -u "$DOCKER_USERNAME" --password-stdin						
					   echo "pushing image to the docker hub"
					   docker push $DOCKER_IMAGE:rmmagent
				        '''
				}
			}
		}
		stage('Deploy on Kubernetes (Green)') {
    			steps {
        			withCredentials([file(credentialsId: "${KUBECONFIG_CRED_ID}", variable: 'KUBECONFIG_FILE')]) {
            				sh '''
                				echo "Setting kubeconfig access"
                				export KUBECONFIG=$KUBECONFIG_FILE

                				echo "Deploying GREEN environment"
                				kubectl apply -f k8s/deployment-green.yaml

                				echo "Validating GREEN deployment"
                				kubectl rollout status deployment/rmmagent-green

               					 echo "Switching service selector to GREEN"
               					 kubectl patch service rmmagent-service -p '{"spec": {"selector": {"app": "rmmagent", "version": "green"}}}'

              					 echo "Green deployment is now live"
            				 '''
       				 }	
		        }
		}
	}
}
