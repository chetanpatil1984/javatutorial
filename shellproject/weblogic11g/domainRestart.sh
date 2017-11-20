#!/bin/sh

if [ -z ${CURRENT_DIR} ]
  then
  . ./setEnv.sh
fi
. ${MW_HOME}/user_projects/domains/bifoundation_domain/bin/setDomainEnv.sh

echo "invoking domain restart"

${CURRENT_DIR}/stopBiOpmnProcesses.sh
${CURRENT_DIR}/stopWeblogic.sh
${CURRENT_DIR}/startWeblogic.sh
${CURRENT_DIR}/startBiOpmnProcesses.sh

echo "invoked domain restart"