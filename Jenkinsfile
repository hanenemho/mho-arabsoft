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
        

       


    
   
}
    
}
