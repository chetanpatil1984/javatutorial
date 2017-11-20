#!/bin/sh

echo "stopping bi opmn processes"
${BI_INSTANCE}/bin/opmnctl stopall > /dev/null 2>&1
echo "stopped bi opmn processes"