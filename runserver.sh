#!/bin/bash

a="$PWD"
b="/app/out/artifacts/server_jar/app.jar"

server_ip=$0
server_port=$1
dashboard_ip=$2
dashboard_port=$3

java -jar $a$b $server_ip $server_port $dashboard_ip $dashboard_port
