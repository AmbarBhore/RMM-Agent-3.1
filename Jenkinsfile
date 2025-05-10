pipeline {
	agent any
	environment {
		DOCKER_IMAGE = 'ambarbhore1234@rmm-agent:3.1'
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
	}
}
