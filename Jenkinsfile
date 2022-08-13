pipeline {
    agent any
    stages {
       stage ('GIT') {
            steps {
               echo "Getting Project from Git"; 
                git branch: "master", 
                    url: "https://github.com/hanenemho/mho-arabsoft.git",
                    credentialsId: "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"; 
            }
        }
         
          stage("K8s Deploying Data base") {
            steps { 
                 withKubeConfig([credentialsId: 'kubernetes', serverUrl: 'https://kubernetes.docker.internal:6443']) {
      sh 'kubectl apply -f ./deployments/Database/configmap.yaml'
		 
              }
          }

    }  
}
