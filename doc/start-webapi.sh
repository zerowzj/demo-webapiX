#!/bin/bash

PROJECT_NAME=demo-webapi

export JAVA_HOME=/usr/jdk1.8.0_162
export PATH=$PATH:$JAVA_HOME/bin

pid=`ps -ef |grep $PROJECT_NAME |grep -v grep |awk '{print $2}'`

if [ -n "$pid" ]; then
    kill -9 $pid
    echo "------------------------------"
    echo "     Shutdown tomcat...       "
    echo "------------------------------"
fi

sh ./startup.sh


