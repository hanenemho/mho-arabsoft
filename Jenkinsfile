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
         stage("Backend Build") {
           steps {
                bat "mvn clean install -U"
                bat "mvn test"
                bat "mvn package "
              
                // sh "mvn clean package -DskipTests" pour une machine linux
            }
        }
         stage("Backend Sonar") {
              steps {
                  bat "mvn sonar:sonar -Dmaven.test.skip"
                 
              }
          }
        
        stage("Dockerising Backend") {
             steps {
                  bat "docker build -t soned-fact-backend:latest . "
                  
                 
              }
          }

          stage("K8s Deploying Data base") {
            steps {
                  
                 script {kubernetesDeploy (configs:'deployments/Database/configmap.yaml',kubeconfigId:'k8scred')}
                 script {kubernetesDeploy (configs:'deployments/Database/secret.yaml',kubeconfigId:'k8scred')}
                 script {kubernetesDeploy (configs:'deployments/Database/deployment.yaml',kubeconfigId:'k8scred')}
                 script {kubernetesDeploy (configs:'deployments/Database/service.yaml',kubeconfigId:'k8scred')
                 }
                 
              }
          }

        stage("K8s Deploying backend") {
             steps {
                  script {kubernetesDeploy (configs:'deployments/Backend/configmap.yaml',kubeconfigId:'k8scred')}
                 script {kubernetesDeploy (configs:'deployments/Backend/deployment.yaml',kubeconfigId:'k8scred')}
                 script {kubernetesDeploy (configs:'deployments/DBackend/service.yaml',kubeconfigId:'k8scred')
                 }
                 
                 
              }
          }
      stage ('GIT Frontend') {
            steps {
               bat "mkdir Front"
               bat "cd Front"
               echo "Getting Project from Git"; 
                git branch: "main", 
                    url: "https://github.com/hanenemho/mho-frontendarab.git",
                    credentialsId: "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"; 
            }
        }
     
         stage("Frontend Build") {
           steps {
                bat "npm run build --prod"
            }
        }
        stage("Dockerising Frontend") {
             steps {
                  bat "docker build -t  sonede-frontend:latest . "
                  
                 
              }
          }
        stage("K8s Deploying Frontend") {
            steps {
                  bat'cd ./deployments/Frontend/'
                  script {kubernetesDeploy (configs:'deployments/Frontend/deployement.yaml',kubeconfigId:'k8scred')}
	          script {kubernetesDeploy (configs:'deployments/Frontend/service.yaml',kubeconfigId:'k8scred')}
                 
                  
                 
              }
          }
    }  
}
