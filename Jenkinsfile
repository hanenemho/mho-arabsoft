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
                  bat "kubectl apply -f ./deployments/Database/configmap.yaml"
                  bat "kubectl apply -f ./deployments/Database/deployment.yaml"
                  bat "kubectl apply -f ./deployments/Database/service.yaml"
                  
                 
              }
          }

        stage("K8s Deploying backend") {
             steps {
                  bat "kubectl apply -f ./deployments/Backend/configmap.yaml"
                  bat "kubectl apply -f ./deployments/Backend/deployment.yaml"
                  bat "kubectl apply -f ./deployments/Backend/service.yaml"
                  
                 
              }
          }
      stage ('GIT Frontend') {
            steps {
               bat "mkdir Front"
               bat "cd Front"
               echo "Getting Project from Git"; 
                git branch: "master", 
                    url: "https://github.com/hanenemho/mho-arabsoft.git",
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
                  bat "kubectl apply -f ./deployments/Frontend/deployment.yaml"
                  bat "kubectl apply -f ./deployments/Frontend/service.yaml"
                  
                 
              }
          }


    }
   
  //  post {
  //      always {
            //cleanWs()
 //       }
  //  }
    
}
