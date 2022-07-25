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


        
        
         stage("Build") {
           steps {
                bat "mvn clean install -U"
                bat "mvn test"
                bat "mvn package "
              
                // sh "mvn clean package -DskipTests" pour une machine linux
            }
        }
        
         stage("Sonar") {
              steps {
                  bat "mvn sonar:sonar -Dmaven.test.skip"
                 
              }
          }
        
        // stage("DEPLOY") {
        //     steps {
        //         bat "mvn deploy -DskipTests"
        //     }mysql -h 127.0.0.1 -u root -proot
        // }docker run --name some-mysql -e MYSQL_ROOT_PASSWORD=my-secret-pw -d mysql:tag
        
       // stage("Docker") {
        //      steps {
          //        bat "docker start db"
            //      bat "docker build -t arabsoft:latest . "
              //    bat "docker run --name mho -p 8081:8080 --link db arabsoft:latest "
                 
              //}
          //}
    }
   
  //  post {
  //      always {
            //cleanWs()
 //       }
  //  }
    
}
