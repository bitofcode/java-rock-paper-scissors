#!/usr/bin/env bash

ROOT_PATH=$(cd $(dirname $0) && pwd)

(

cd ${ROOT_PATH}/acceptanceTests/fitnesse

java -jar lib/fitnesse-standalone.jar -p 9890 -r fitnesse-root -d root

)