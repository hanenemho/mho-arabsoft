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
                  
                 bat'kubectl apply -f deployments/Database/configmap.yaml --token eyJhbGciOiJSUzI1NiIsImtpZCI6IkZ0cXBORWs0S1JYTzE0TFhuVUpXOFhkTFJWYTVVUDE0T2o2SWg4QVhwN0UifQ.eyJpc3MiOiJrdWJlcm5ldGVzL3NlcnZpY2VhY2NvdW50Iiwia3ViZXJuZXRlcy5pby9zZXJ2aWNlYWNjb3VudC9uYW1lc3BhY2UiOiJkZWZhdWx0Iiwia3ViZXJuZXRlcy5pby9zZXJ2aWNlYWNjb3VudC9zZWNyZXQubmFtZSI6ImplbmtpbnMiLCJrdWJlcm5ldGVzLmlvL3NlcnZpY2VhY2NvdW50L3NlcnZpY2UtYWNjb3VudC5uYW1lIjoiamVua2lucyIsImt1YmVybmV0ZXMuaW8vc2VydmljZWFjY291bnQvc2VydmljZS1hY2NvdW50LnVpZCI6IjU4ZGNkOGJlLTU2ZDUtNDMxMi05ZDgyLTQxOGU2MDUyZjAwZSIsInN1YiI6InN5c3RlbTpzZXJ2aWNlYWNjb3VudDpkZWZhdWx0OmplbmtpbnMifQ.dkSr1YDZfji1OqOc0g6r8tYF9hkd49o4N2n-9b355SfY5h6Ex9qq_6kDsxz2z3CIUtnTZQCFDyM5lbb6KhUigT2lMuUEuPahqOfriqLrbMcWxpYNVdO8nsr9J6oPK6AWOh_FueCRu1G76MN_NOmNx8LEsfppHGDEvr8d84BtiPZF29jlDbPd902pP02_yvO6Deib5f-qgxliDTltw2GbmCDYGEVjFoyYp5Rf9MAL_0wQsocDVXVM7zfRRnNotmUIyKPoSRHadkKubq5M519jj-xcvXcPgiW4kG_vaio5jIJEVTuVrQo9U58F-p4vWIkk89MDPsglzPk_9z8G2gYBoA --server https://kubernetes.docker.internal:6443 '
                 bat'kubectl apply -f deployments/Database/deployment.yaml'
                 bat'kubectl apply -f deployments/Database/service.yaml '
                 
              }
          }

    }  
}
