#!/bin/sh

echo "starting bi opmn processes"
${BI_INSTANCE}/bin/opmnctl startall > /dev/null 2>&1
echo "started bi opmn processes"