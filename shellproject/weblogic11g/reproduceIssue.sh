#!/bin/sh

. ./setEnv.sh

echo "invoking script"

RETRY_COUNT=1
while true
  do

	echo "attempting to reproduce issue : ${RETRY_COUNT}"

  ${CURRENT_DIR}/domainRestart.sh
  ${CURRENT_DIR}/pingStatus.sh
  if [ $? -eq 0 ]
    then
      echo "EXIT: " $?
      exit
  fi

  echo "issue not reproduced : ${RETRY_COUNT}"
	RETRY_COUNT=$(($RETRY_COUNT+1))

done