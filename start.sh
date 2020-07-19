
##################################################
#
# Application start script
#
# Pre-requisites:
# 1. Java 1.8
# 2. Docker
#
# @author: Ravi Kumar Soni
# @authorLinkedProfile: https://www.linkedin.com/in/tenaciousprevailer
# @authorContact: 8955047966
# @purpose: Societe Generale Test
#
##################################################

function log() {
  echo "[$(date)]: $*"
}

function startDockerContainers() {
  log "Going to start docker containers..."
  docker-compose -f src/main/docker/mongodb.yml up -d
  docker-compose -f src/main/docker/elasticsearch.yml up -d
  docker-compose -f src/main/docker/redis.yml up -d
}

function stopDockerContainers() {
  log "Going to stop docker containers..."
  docker-compose -f src/main/docker/mongodb.yml down
  docker-compose -f src/main/docker/elasticsearch.yml down
  docker-compose -f src/main/docker/redis.yml down
}

commandType=$1
log "Command Type: $commandType"
if [ "$commandType" = "start" ]
then
  log "Building application..."
  ./mvnw clean package spring-boot:repackage

  stopDockerContainers
  startDockerContainers

  log "Starting application..."
  # TODO remove the hardcoded version
  java -jar target/employee-portal-0.0.1.jar

elif [ "$commandType" = "startDockerContainers" ]
then
  stopDockerContainers
  startDockerContainers
elif [ "$commandType" = "stop" ]
then
  stopDockerContainers
else
  log "Command type [$commandType] not supported"
  log "Supported commands are: start, startDockerContainers, stop"
fi
