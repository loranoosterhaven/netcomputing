#!/bin/bash

a="$PWD"
b="/app/out/artifacts/server_jar/app.jar"

server_ip=$1
server_port=$2
dashboard_ip=$3
dashboard_port=$4

java -jar $a$b $server_ip $server_port $dashboard_ip $dashboard_port
