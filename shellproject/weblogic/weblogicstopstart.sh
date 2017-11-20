#!/bin/sh

export MW_HOME=/scratch/chetpati/OBIMH
. ${MW_HOME}/user_projects/domains/bifoundation_domain/bin/setDomainEnv.sh
export BI_INSTANCE=${MW_HOME}/instances/instance1
export WLS_ADMINURL=t3://localhost:7001
export WLS_USER=weblogic
export WLS_PW=welcome1
export BI_SERVER_NAME=bi_server1

echo "invoking script"

RETRY_COUNT=1
while true
do

	echo "attempting to reproduce issue : ${RETRY_COUNT}"

	echo "starting node manager"
	${WL_HOME}/server/bin/startNodeManager.sh > /dev/null 2>&1 &
	echo "started node manager"

	echo "stopping bi opmn processes"
	${BI_INSTANCE}/bin/opmnctl stopall > /dev/null 2>&1 &
	echo "stopped bi opmn processes"

	echo "stopping managed server"
	${DOMAIN_HOME}/bin/stopManagedWebLogic.sh ${BI_SERVER_NAME} ${WLS_ADMINURL} ${WLS_USER} ${WLS_PW} > /dev/null 2>&1 &
	sleep 1m
	echo "stopped managed server"

	echo "stopping admin server"
	${DOMAIN_HOM}/bin/stopWebLogic.sh > /dev/null 2>&1 &
	sleep 1m
	echo "stopped admin server"

	echo "killing the weblogic processes"
	/sbin/fuser -k 9704/tcp > /dev/null 2>&1 &
	/sbin/fuser -k 7001/tcp > /dev/null 2>&1 &
	echo "killed the weblogic processes"

	echo "starting admin server"
	${DOMAIN_HOME}/bin/startWebLogic.sh > /dev/null 2>&1 &
	sleep 2m
	echo "started admin server"

	echo "starting managed server"
	${DOMAIN_HOME}/bin/startManagedWebLogic.sh ${BI_SERVER_NAME} ${WLS_ADMINURL} > /dev/null 2>&1 &
	sleep 5m
	echo "started managed server"

	echo "starting bi opmn processes"
	${BI_INSTANCE}/bin/opmnctl startall > /dev/null 2>&1 &
	echo "started bi opmn processes"

	PING_COUNT=1
	while true
	do
		STATUS_CODE=`curl --silent -w "%{http_code}" --output /dev/null --header "Content-Type: text/xml;cset=UTF-8" --header "SOAPAction:http://xmlns.oracle.com/oxp/service/v2:getReportDefinition" --data @request.xml http://slc11cfs.us.oracle.com:9704/xmlpserver/services/v2/ReportService`

		if [ "${STATUS_CODE}" -eq 200 ]
		  then
			echo "ping bip ${PING_COUNT} status ${STATUS_CODE}"
			break
	  elif [ "${PING_COUNT}" -lt 10000 ]
	    then
			PING_COUNT=$(($PING_COUNT+1))
			echo "ping bip ${PING_COUNT} status ${STATUS_CODE}"
		else
			echo "issue reproduced"
		  exit
		fi
	done

  echo "issue not reproduced : ${RETRY_COUNT}"
	RETRY_COUNT += 1

done