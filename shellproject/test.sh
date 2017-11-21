#!/bin/sh

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