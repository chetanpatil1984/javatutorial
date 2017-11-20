#!/bin/sh

while true
  do
  curl --silent --output /dev/null --header "Content-Type: text/xml;charset=UTF-8" --header "SOAPAction:http://xmlns.oracle.com/oxp/service/v2:getReportDefinition" --data @request.xml http://adc00raj.us.oracle.com:9502/xmlpserver/services/v2/ReportService
done