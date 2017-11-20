#!/bin/sh

echo "starting node manager"
${WL_HOME}/server/bin/startNodeManager.sh > /dev/null 2>&1 &
sleep 30s
echo "started node manager"

echo "starting admin server"
${DOMAIN_HOME}/bin/startWebLogic.sh > /dev/null 2>&1 &
sleep 2m
echo "started admin server"

echo "starting managed server"
${DOMAIN_HOME}/bin/startManagedWebLogic.sh ${BI_SERVER_NAME} ${WLS_ADMINURL} > /dev/null 2>&1 &
sleep 5m
echo "started managed server"