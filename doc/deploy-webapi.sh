#!/bin/bash

DEPLOY_HOME=/app/demo-webapi
WEB_APPS=$DEPLOY_HOME/webapps
ROOT=$WEB_APPS/ROOT

WAR_FILE=$DEPLOY_HOME/$1
if [ ! -f "$WAR_FILE" ]; then
    echo "ERROR: file [$WAR_FILE] not exist!"
    exit 1
fi

rm -rf $ROOT
unzip -oq $WAR_FILE -d $ROOT
sleep 3
rm -rf $WAR_FILE

export JAVA_HOME=/usr/jdk1.8.0_162
export PATH=$PATH:$JAVA_HOME/bin

pid=`ps -ef |grep $DEPLOY_HOME/ |grep -v grep |grep -v $0 |awk '{print $2}'`
if [ -n "$pid" ]; then
    kill -9 $pid
    echo "------------------------------"
    echo "     Shutdown tomcat...       "
    echo "------------------------------"
fi
sh ./startup.sh
