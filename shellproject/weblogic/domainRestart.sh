#!/bin/sh

. ./setEnv.sh
. ${MW_HOME}/user_projects/domains/bifoundation_domain/bin/setDomainEnv.sh

echo "invoking domain restart"

./stopBiOpmnProcesses.sh
./stopWeblogic.sh
./startWeblogic.sh
./startBiOpmnProcesses.sh

echo "invoked domain restart"