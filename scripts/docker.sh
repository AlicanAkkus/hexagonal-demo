#!/usr/bin/env bash

removeImages(){
    eval 'docker rmi -f $(docker images -f dangling=true -q)'
}

removeVolumes(){
    eval 'docker volume rm $(docker volume ls -q -f dangling=true)'
}

case "${1}" in
    "image")
        removeImages
        ;;
    "volume")
        removeVolumes
        ;;
    "help")
        echo "docker.sh image -> Delete all dangling images"
        echo "docker.sh volume -> Delete all dangling volumes"
        echo "docker.sh -> Delete all dangling images & volumes"
        ;;
    *)
        removeImages && removeVolumes
esac
