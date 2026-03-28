#!/bin/bash

if [[ $(basename "$PWD") == "scripts" ]] && [[ -f "../gradlew" ]]; then
    cd ..
elif [[ -f "./gradlew" ]]; then
    echo "Found gradlew in current directory."
elif [[ -f "../gradlew" ]]; then
    cd ..
    echo "Found gradlew in parent directory."
else
    echo "Fatal: gradlew not found in expected locations."
    exit
fi

if [ -f .env ]; then
    set -a
    source .env
    set +a
else
    echo "Fatal: .env file not found"
    exit
fi

./gradlew publishMods --no-daemon
