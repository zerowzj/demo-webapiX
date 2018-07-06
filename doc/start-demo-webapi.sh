#!/bin/sh

DEPLOY_HOME=/app/demp-webapi


PID=`ps -ef |grep demo-webapi |grep -v grep |awk {}`


cd DEPLOY_HOME/bin
sh start.sh


