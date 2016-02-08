#!/bin/bash
set -euo pipefail

ORCHESTRATOR_CONFIG_URL=$1
shift

cd it
mvn verify \
  -Dorchestrator.configUrl=$ORCHESTRATOR_CONFIG_URL \
  -PdbTests \
  -Dcategory=Categ1 \
  -B -e -V $*
