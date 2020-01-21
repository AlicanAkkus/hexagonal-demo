#!/bin/sh

LOG_FOLDER=/tmp/craftbase-logs/

# FOR FT AND STG PROFILES
CONFIG_SERVER_ENCRYPT_KEY="D6Yh6Rd=t?WA^=zfEcZGdfzND%8J\$z7R"

apis="
order-api:8100
"

################################
# BOOT DOWN FUNCTIONS
################################

down_infra() {
    docker-compose down
}

down_api() {
    api_name=$1
    # shellcheck disable=SC2009
    pid_list=$(ps ax | grep -i bootRun | grep -v grep | grep -i "$api_name" | awk '{print $1}')
    for pid in ${pid_list}
    do
        # shellcheck disable=SC2009
        details=$(ps -p "${pid}" | grep "${pid}" )
        kill -9 "${pid}"
        echo "\033[31mkilled: $pid :> \033[33m $details"
    done
}

down_apis() {
    # shellcheck disable=SC2009
    pid_list=$(ps ax | grep -i bootRun | grep -v grep | awk '{print $1}')
    for pid in ${pid_list}
    do
        # shellcheck disable=SC2009
        details=$(ps -p "${pid}" | grep "${pid}" )
        kill -9 "${pid}"
        echo "\033[31mkilled: $pid :> \033[33m $details"
    done
}

################################
# BOOT UP FUNCTIONS
################################

up_infra() {
    docker-compose up -d
}

up_apis() {
    echo "\033[37mBooting APIs ..."

    # shellcheck disable=SC2039
    split="${apis:1:${#apis}-2}"
    api_arr=$(echo "${split}" | tr "," "\n")
    for i in ${api_arr}
    do
        value="${i##*:}"
        key="${i%%:*}"
        up_api "${key}" "${value}"
    done
}

up_api() {
    api_name=$1
    server_port=$(get_api_port "$1")

    echo "\033[97m   $api_name (port:$server_port) ...\c"

    upFlag=$(isRunning "${server_port}")

    if [ "${upFlag}" = 1 ]; then
        echo "\033[36m already up"
        return 1
    fi

    startBoot=$(date +%s)

    mkdir -p ${LOG_FOLDER}
    touch ${LOG_FOLDER}/"${api_name}".log

    if [ "$api_name" = "config-server" ]; then
        nohup bash -c "./gradlew $api_name:bootRun -Pargs=--spring.profiles.active=ft,--server.port=$server_port,--encryptKey=$CONFIG_SERVER_ENCRYPT_KEY,--workspace=$CONFIG_REPO_PATH" > ${LOG_FOLDER}/"${api_name}".log&
    else
        nohup bash -c "./gradlew $api_name:bootRun -Pargs=--spring.profiles.active=ft" > ${LOG_FOLDER}/"${api_name}".log&
    fi

    upFlag=$(isUp "${server_port}")

    endBoot=$(date +%s)

    totalBoot=$((endBoot-startBoot))
    if [ "${upFlag}" = 1 ]; then
        echo "\033[32m done\033[36m (in ${totalBoot} sec)"
    else
        echo "\033[31m failed\033[36m (in ${totalBoot} sec)"
    fi
}

################################
# HELPER FUNCTIONS
################################

prop() {
    grep "${1}" .env | cut -d'=' -f2
}

prepare_log_files() {
    mkdir -p ${LOG_FOLDER}/
    rm -rf ${LOG_FOLDER:?}/*

    # shellcheck disable=SC2039
    split="${apis:1:${#apis}-2}"
    api_arr=$(echo "$split" | tr "," "\n")
    for i in ${api_arr}
    do
        value="${i##*:}"
        key="${i%%:*}"
        touch ${LOG_FOLDER}/"${key}".log
    done
}

get_api_port() {
    expr "$apis" : ".*,$1:\([^,]*\),.*"
}

isRunning() {
    if [ -z "$(lsof -Pi :"$1" -sTCP:LISTEN)" ]; then
        echo 0
    else
        echo 1
    fi
}

isUp() {
    x=1
    # wait max 60 seconds to conclude that an api is up or down
    while [ -z "$(lsof -Pi :"$1" -sTCP:LISTEN)" ] && [  ${x} -le 20 ]; do
        sleep 3;
        x=$(( x + 1 ))
    done

    if [ -z "$(lsof -Pi :"$1" -sTCP:LISTEN)" ]; then
        echo 0
    else
        echo 1
    fi
}

usage() {
    echo "Usage:"
    echo "   \033[31m ./run.sh \033[36mup-all                      \033[33m boots up all infra components, config server and all apis"
    echo "   \033[31m ./run.sh \033[36mup-apis                     \033[33m boots up all apis"
    echo "   \033[31m ./run.sh \033[36mup-infra                    \033[33m boots up all infra components and config server"
    echo "   \033[31m ./run.sh \033[36mup-api        \033[31m[API NAME]    \033[33m boots up given apis"
    echo "   \033[31m ./run.sh \033[36mdown-all                    \033[33m stops all infra components and config server and all apis"
    echo "   \033[31m ./run.sh \033[36mdown-apis                   \033[33m stops all apis"
    echo "   \033[31m ./run.sh \033[36mdown-infra                  \033[33m stops all infra components and config server"
    echo "   \033[31m ./run.sh \033[36mdown-api      \033[31m[API NAME]    \033[33m stops given api"
    echo "   \033[31m ./run.sh \033[36mrestart-api   \033[31m[API NAME]    \033[33m restarts given api"
    echo "   \033[31m ./run.sh \033[36mrestart-infra \033[31m[INFRA NAME]  \033[33m restarts given infra component"
    echo "   \033[31m ./run.sh \033[36mrestart-all                 \033[33m restarts all infra component and all apis"
}

################################
# MAIN
################################

echo "\033[96m****************************************"

if [ ! -f ".env" ]; then
  echo "ERROR: .env file does not exist. Skipping"
  exit 11
fi

start=$(date +%s)
CONFIG_REPO_PATH=$(prop "configRepoPath")
CONFIG_REPO_BRANCH=$(prop "configRepoBranch")

if [ -z "$CONFIG_REPO_PATH" ]; then
  echo "ERROR: ConfigRepoPath is not defined in .env file"
  exit 12
fi

if [ -z "$CONFIG_REPO_BRANCH" ]; then
  echo "ERROR: configRepoBranch is not defined in .env file"
  exit 13
fi

echo ""
echo "Common Configs:"
echo "\033[31mConfig Repo Path  : \033[36m$CONFIG_REPO_PATH"
echo "\033[31mConfig Repo Branch: \033[36m$CONFIG_REPO_BRANCH"
echo ""

prepare_log_files

case "${1}" in

    ########################
    # BOOT UP
    ########################

    "up-all")
        up_infra
        sleep 10
        up_apis
        ;;
    "up-infra")
        up_infra
        ;;
    "up-apis")
        up_apis
        ;;
    "up-api")
        api_name=$3
        up_api "${api_name}"
        ;;

    ########################
    # BOOT DOWN
    ########################

    "down-all")
        down_apis
        sleep 10
        down_infra
        ;;
    "down-infra")
        down_infra
        ;;
    "down-apis")
        down_apis
        ;;
    "down-api")
        down_api "$2"
        ;;

    ########################
    # RESTART
    ########################

    "restart-api")
        api_name=$3
        down_api "${api_name}"
        sleep 5
        up_api "${api_name}"
        ;;
    "restart-infra")
        infra_name=$3
        docker-compose rm -f -s -v "${infra_name}"
        docker-compose up -d "${infra_name}"
        ;;
    "restart-all")
        down_apis
        sleep 10
        down_infra
        sleep 5
        up_infra
        sleep 10
        up_apis
        ;;
    "-h")
        usage
        ;;
    *)
        echo "\033[31mWARNING! Unknown command: \033[31m\"${1}\""
        echo ""
        usage
esac

end=$(date +%s)

runtime=$((end-start))

echo ""
echo "\033[96m****************************************"
echo "\033[96m[END] Execution completed in \033[31m $runtime seconds "
