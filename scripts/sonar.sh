#!/usr/bin/env bash

if [[ ! ${BRANCH} == refs/heads/master ]]; then
  echo "Branch is not master branch, it is ${BRANCH}, exiting!"
  exit 0
fi

./gradlew sonarqube -Dsonar.host.url=${SONAR_URL} -Dsonar.login=${SONAR_TOKEN}
