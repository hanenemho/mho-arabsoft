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
                sh "sudo mvn clean install -U"
                sh "sudo mvn test"
                sh "sudo mvn package "
              
                // sh "mvn clean package -DskipTests" pour une machine linux
            }
        }
         stage("Backend Sonar") {
              steps {
                  sh "sudo mvn sonar:sonar -Dmaven.test.skip"
                 
              }
          }
        
        stage("Dockerising Backend") {
            steps{
                script{
              dockerImage_back = docker.build("hanenemho12/soned-fact-backend:${currentBuild.number}")
                }
            }
          }
        stage('Push Backend Docker_Image') {
            steps{
                script{
             withDockerRegistry([ credentialsId: "docker_hub", url: "" ]) {
             dockerImage_back.push()
             }
            }
         }    
        }
          stage("K8s Deploying Data base") {
            steps {
                  
                 sh 'sudo kubectl apply -f deployments/Database/configmap.yaml --kubeconfig /home/ubuntu/.kube/config'
                 sh 'sudo kubectl apply -f deployments/Database/secret.yaml --kubeconfig /home/ubuntu/.kube/config'
                 sh 'sudo kubectl apply -f deployments/Database/deployment.yaml --kubeconfig /home/ubuntu/.kube/config'
                 sh 'sudo kubectl apply -f deployments/Database/service.yaml --kubeconfig /home/ubuntu/.kube/config'
                 }
                 
              }

          

        stage("K8s Deploying backend") {

           
             steps {
                 /* script {kubernetesDeploy (configs:'deployments/Backend/configmap.yaml',kubeconfigId:'aws-EKS-us-east-2')}
                 script {kubernetesDeploy (configs:'deployments/Backend/deployment.yaml',kubeconfigId:'aws-EKS-us-east-2')}
                 script {kubernetesDeploy (configs:'deployments/Backend/service.yaml',kubeconfigId:'aws-EKS-us-east-2')*/
                 sh'rm -rf deployments/Backend/deployment.yaml '
                 sh"sed -i 's/IMAGE_TAG/${currentBuild.number}/g' deployments/Backend/deployement.template >  deployments/Backend/deployement.yaml"
                 sh 'sudo kubectl apply -f deployments/Backend/configmap.yaml --kubeconfig /home/ubuntu/.kube/config '
                sh 'sudo kubectl apply -f deployments/Backend/deployment.yaml --kubeconfig /home/ubuntu/.kube/config'
               sh 'sudo kubectl apply -f deployments/Backend/service.yaml --kubeconfig /home/ubuntu/.kube/config'
                 }
                 
                 
              }
          
       
       
       stage ('GIT Frontend') {
            steps {
               sh"rm -rf Front"
               sh "mkdir Front"
               sh "cd Front"
               echo "Getting Project from Git"; 
                git branch: "main", 
                    url: "https://github.com/hanenemho/mho-frontendarab.git",
                    credentialsId: "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"; 
            }
        }
     
         stage("Frontend Build") {
           steps {
	            sh 'npm install'
                //sh'sudo chmod -R 775 node_modules '
                sh "npm run build --prod"
            }
        }
        stage("Dockerising Frontend") {
            steps{
                script{
             dockerImage_front = docker.build("hanenemho12/sonede-frontend:${currentBuild.number}")
            }
            }
          }
        stage('Push image') {
            steps{
                script{
             withDockerRegistry([ credentialsId: "docker_hub", url: "" ]) {
             dockerImage_front.push()
             }
            }
            }
         }    
        stage("K8s Deploying Frontend") {
            steps {
               sh'cd ./deployments/Frontend/'
               sh"rm -rf deployments/Frontend/deployement.yaml"
               sh"sed -i 's/IMAGE_TAG/${currentBuild.number}/g' deployments/Frontend/deployement.template >  deployments/Frontend/deployement.yaml"
               sh 'sudo kubectl apply -f deployments/Frontend/deployement.yaml --kubeconfig /home/ubuntu/.kube/config'
               sh 'sudo kubectl apply -f deployments/Frontend/service.yaml --kubeconfig /home/ubuntu/.kube/config'
              }
          }
    }  
    post {
        always {
            cleanWs()
        }
    }
}
