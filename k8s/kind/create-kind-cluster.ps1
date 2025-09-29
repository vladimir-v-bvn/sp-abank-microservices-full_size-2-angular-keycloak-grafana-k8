kind create cluster --name microservices --config kind-config.yaml
#Start-Process -FilePath "kind" -ArgumentList "create cluster", "--name microservices", "--config kind-config.yaml"
#Set kubectl context to "kind-microservices"
#You can now use your cluster with:
#kubectl cluster-info --context kind-microservices