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
                 
	         script {kubernetesDeploy (configs:'deployments/Database/configmap.yaml',kubeconfigId:'k8scred')
                 script {kubernetesDeploy (configs:'deployments/Database/secret.yaml',kubeconfigId:'k8scred')
                 script {kubernetesDeploy (configs:'deployments/Database/deployment.yaml',kubeconfigId:'k8scred')
                 script {kubernetesDeploy (configs:'deployments/Database/service.yaml',kubeconfigId:'k8scred')
                 }
               		 
             }
          }

    }  
}
