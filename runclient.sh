#!/bin/bash

a="$PWD"
b="/app/out/artifacts/client_jar/app.jar"

ip=$0
port=$1

java -jar $a$b $ip $port
