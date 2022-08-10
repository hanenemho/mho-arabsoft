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
                  
                 bat'kubectl apply -f ./deployments/Database/configmap.yaml --token ZXlKaGJHY2lPaUpTVXpJMU5pSXNJbXRwWkNJNklrWjBjWEJPUldzMFMxSllUekUwVEZodVZVcFhPRmhrVEZKV1lUVlZVREUwVDJvMlNXZzRRVmh3TjBVaWZRLmV5SnBjM01pT2lKcmRXSmxjbTVsZEdWekwzTmxjblpwWTJWaFkyTnZkVzUwSWl3aWEzVmlaWEp1WlhSbGN5NXBieTl6WlhKMmFXTmxZV05qYjNWdWRDOXVZVzFsYzNCaFkyVWlPaUprWldaaGRXeDBJaXdpYTNWaVpYSnVaWFJsY3k1cGJ5OXpaWEoyYVdObFlXTmpiM1Z1ZEM5elpXTnlaWFF1Ym1GdFpTSTZJbk5sWTNKbGRDMXpZUzF6WVcxd2JHVWlMQ0pyZFdKbGNtNWxkR1Z6TG1sdkwzTmxjblpwWTJWaFkyTnZkVzUwTDNObGNuWnBZMlV0WVdOamIzVnVkQzV1WVcxbElqb2lhbVZ1YTJsdWN5SXNJbXQxWW1WeWJtVjBaWE11YVc4dmMyVnlkbWxqWldGalkyOTFiblF2YzJWeWRtbGpaUzFoWTJOdmRXNTBMblZwWkNJNklqVTRaR05rT0dKbExUVTJaRFV0TkRNeE1pMDVaRGd5TFRReE9HVTJNRFV5WmpBd1pTSXNJbk4xWWlJNkluTjVjM1JsYlRwelpYSjJhV05sWVdOamIzVnVkRHBrWldaaGRXeDBPbXBsYm10cGJuTWlmUS5nYXFITjdGb2p2blJUb0V2YS1NVXpvRFRJc2xpSzVGY1E4RDcyZXdnLTZfZERFSmFyYVM2aDBaY21TS3pndUxGb2hCR3NBMThhNUFRRE1VbU51RHhmU0NIbnBkaldHQi10NGtiQ3JLUmJUaXFWTEY1QXF3dXJZaDlUQVlyenhGMUhHT3dNcHVrVVgySnZjbTlYakpMenp0UkNOWEJpOVN6Mk9XeXdxLWUyWVl2NlQ1ZGg0RUhxdTM5ZTRTdGU0clN1Q2tkME5kVzRsOXNWc0Y1eTV4YkJOYmFsRnlocEtiQlRMamU1Tk96NU95RmlsYVoxMmtsZ1RYZU9COFlQalZOd3VfLUNPbW1CX2pUdVNEX291emNNMFRtUEZaaFBTX2xaa1ZRS0hocU52cXE3YXJoMHIta1NVQVoxOW9KUmoxODliS2ZyS3lEOUstQlJ5cVVIamxXbmc= --server https://kubernetes.docker.internal:6443'
                 bat'kubectl apply -f ./deployments/Database/deployment.yaml'
                 bat'kubectl apply -f ./deployments/Database/service.yaml '
                 
              }
          }

        stage("K8s Deploying backend") {
             steps {
                  bat 'cd ../Backend/'
                  kubernetesDeploy (configs:"configmap.yaml ",kubeconfigId:"kubernetes")
                  kubernetesDeploy (configs:"deployment.yaml",kubeconfigId:"kubernetes")
	          kubernetesDeploy (configs:"service.yaml",kubeconfigId:"kubernetes")
                 
                 
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
                  kubernetesDeploy (configs:"deployement.yaml",kubeconfigId:"kubernetes")
	          kubernetesDeploy (configs:"service.yaml",kubeconfigId:"kubernetes")
                 
                  
                 
              }
          }
    }  
}
