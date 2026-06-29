#!/usr/bin/env bash
set -e

cd "$(dirname "$0")"

echo "Bootstrap BOMs..."
mvn install -N -q
mvn install -f spin/bom/pom.xml -q
mvn install -f commons/bom/pom.xml -q
mvn install -f connect/bom/pom.xml -q
mvn install -f engine-dmn/bom/pom.xml -q
mvn install -f bom/pom.xml -N -q
mvn install -f bom/camunda-only-bom/pom.xml -q
mvn install -f bom/camunda-bom/pom.xml -q
mvn install -f parent/pom.xml -N -q
mvn install -f internal-dependencies/pom.xml -N -q

echo "Building..."
mvn install -DskipTests "$@"

echo "Done."
