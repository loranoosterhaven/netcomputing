#!/bin/bash

a="$PWD"
b="/app/out/artifacts/client_jar/app.jar"

ip=$1
port=$2

java -jar $a$b $ip $port
