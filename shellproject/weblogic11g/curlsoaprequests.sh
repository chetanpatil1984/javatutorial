#!/bin/sh

while true
  do
  curl --silent --output /dev/null --header "Content-Type: text/xml;charset=UTF-8" --header "SOAPAction:http://xmlns.oracle.com/oxp/service/v2:getReportDefinition" --data @request.xml http://slc11cfs.us.oracle.com:9704/xmlpserver/services/v2/ReportService
done