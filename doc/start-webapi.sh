#!/bin/bash

PROJECT_NAME=demo-webapi

pid=`ps -ef |grep demo-webapi |grep -v grep |awk '{print $2}'`

if [ -n "$pid" ]; then
    kill -9 $pid
    echo "------------------------------"
    echo "     Shutdown tomcat...       "
    echo "------------------------------"
fi

#sh startup.sh


