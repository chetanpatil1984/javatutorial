 #!/bin/sh

echo "stopping managed server"
${DOMAIN_HOME}/bin/stopManagedWebLogic.sh ${BI_SERVER_NAME} ${WLS_ADMINURL} ${WLS_USER} ${WLS_PW} > /dev/null 2>&1 &
sleep 1m
echo "stopped managed server"

echo "stopping admin server"
${DOMAIN_HOM}/bin/stopWebLogic.sh > /dev/null 2>&1 &
sleep 1m
echo "stopped admin server"



echo "killing the weblogic processes"
ps -ef | grep "startManaged*" | grep -v grep | awk '{print $2}' | xargs kill -9
ps -ef | grep "startWebLogic*" | grep -v grep | awk '{print $2}' | xargs kill -9
/sbin/fuser -k 9704/tcp
/sbin/fuser -k 7001/tcp
sleep 15s
echo "killed the weblogic processes"
