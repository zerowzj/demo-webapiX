#!/bin/sh

PROJECT_NAME=demo-webapi
DEPLOY_HOME=/app/demo-webapi

cd `dirname $0`

pid=`ps -ef |grep $PROJECT_NAME |grep -v grep |awk '{print $2}'`

if [ -n "$pid" ]; then
 kill -9 $pid
 echo "shutdown tomcat..."
fi

cd $DEPLOY_HOME/bin
sh startup.sh


