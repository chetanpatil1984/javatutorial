#!/bin/sh

. ./setEnv.sh

echo "invoking script"

RETRY_COUNT=1
while true
do

	echo "attempting to reproduce issue : ${RETRY_COUNT}"

  ${CURRENT_DIR}/domainRestart.sh
  ${CURRENT_DIR}/ping.sh

  echo "issue not reproduced : ${RETRY_COUNT}"
	RETRY_COUNT=$(($RETRY_COUNT+1))

done