#!/usr/bin/env bash

# NETWORK
NETWORK='craftbase_platform_network'

# DATA
TEST_DB_DATA='craftbase_platform_test_db_data'

# ENV
TEST_DB_ENVS='--env MYSQL_ROOT_PASSWORD=my-secret-pw'

# NAME
TEST_DB_NAME='craftbase_platform_test_db'
TEST_DB_IMAGE='mysql'
TEST_REDIS_NAME='craftbase_platform_test_redis'
TEST_KAFKA_NAME='craftbase_platform_test_kafka'
TEST_KADMIN_NAME='craftbase_platform_test_kadmin'
TEST_MONGO_NAME='craftbase_platform_test_mongo_db'
TEST_ZOOKEEPER_NAME='craftbase_platform_test_zookeeper'

isUp() {
    if [[ ! -z $(lsof -Pi :$1) ]]; then
        echo 1
    fi
}

isDown() {
    if [[ -z $(lsof -Pi :$1) ]]; then
        echo 1
    fi
}

create_volume(){
    if !(docker volume ls | grep ${1});
    then
       echo ">> Creating ${1} volume"
       docker volume create ${1}
    fi
}

create_network(){
    if !(docker network ls | grep ${1});
    then
       echo ">> Creating ${1} network"
       docker network create ${1}
    fi
}

up_db() {
    if [[ ! $(isUp 4306) ]]; then
        echo ">> starting: db up"
        create_volume $TEST_DB_DATA
        create_network $NETWORK
        docker run --rm --network ${NETWORK} --name ${TEST_DB_NAME} -v ${TEST_DB_DATA} ${TEST_DB_ENVS} -p 4306:3306 -d ${TEST_DB_IMAGE}
    else
        echo ">> db is already up"
    fi
}

down_db() {
    if [[ ! $(isDown 4306) ]]; then
        echo ">> down db"
        docker rm -f ${TEST_DB_NAME}
        docker volume rm ${TEST_DB_DATA}
    else
        echo ">> db is already down"
    fi
}

case "${1}" in
    "db")
        case "${2}" in
        "up") echo Starting db && up_db;;
        "down") echo Stopping db && down_db;;
        esac;;
    "infra")
        case "${2}" in
        "up")
            echo Starting db && up_db;
        ;;
        "down")
            echo Stopping db && down_db;
        ;;
        esac;;
    *)
        echo Unknown command! ${1}
esac