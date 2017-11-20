#!/bin/sh

. ./setEnv.sh

pkill curlsoaprequests.sh
pkill curl

COUNT=1
while [ "${COUNT}" -le 20 ]
  do
  {CURRENT_DIR}/curlsoaprequests.sh &
  COUNT=$(($count+1))
done