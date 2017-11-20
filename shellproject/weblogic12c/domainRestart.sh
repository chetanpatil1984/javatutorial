#!/bin/sh

if [ -z ${CURRENT_DIR} ]
  then
  . ./setEnv.sh
fi
. ${MW_HOME}/user_projects/domains/bi/bin/setDomainEnv.sh

echo "invoking domain restart"

${DOMAIN_HOME}/bitools/bin/stop.sh
sleep 1m
${DOMAIN_HOME}/bitools/bin/start.sh

echo "invoked domain restart"