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

  ./domainRestart.sh
  ./ping.sh

  echo "issue not reproduced : ${RETRY_COUNT}"
	RETRY_COUNT=$(($RETRY_COUNT+1))

done