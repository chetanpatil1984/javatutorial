#!/bin/sh

export MW_HOME=/scratch/chetpati/OBIMH
. ${MW_HOME}/user_projects/domains/bifoundation_domain/bin/setDomainEnv.sh
export BI_INSTANCE=${MW_HOME}/instances/instance1
export WLS_ADMINURL=t3://localhost:7001
export WLS_USER=weblogic
export WLS_PW=welcome1
export BI_SERVER_NAME=bi_server1


while true
do

echo "starting node manager"
${WL_HOME}/server/bin/startNodeManager.sh
echo "started node manager"

echo "stopping bi opmn processes"
${BI_INSTANCE}/bin/opmnctl stopall
echo "stopped bi opmn processes"

echo "stopping managed server"
${DOMAIN_HOME}/bin/stopManagedWebLogic.sh ${BI_SERVER_NAME} ${WLS_ADMINURL} ${WLS_USER} ${WLS_PW}
echo "stopped managed server"

echo "stopping admin server"
${DOMAIN_HOM}/bin/stopWebLogic.sh
echo "stopped admin server"

echo "starting admin server"
${DOMAIN_HOME}/bin/startWebLogic.sh
echo "started admin server"

echo "starting managed server"
${DOMAIN_HOME}/bin/startManagedWebLogic.sh ${BI_SERVER_NAME} ${WLS_ADMINURL} ${WLS_USER} ${WLS_PW}
echo "started managed server"

echo "starting bi opmn processes"
${BI_INSTANCE}/bin/opmnctl startall
echo "started bi opmn processes"

done