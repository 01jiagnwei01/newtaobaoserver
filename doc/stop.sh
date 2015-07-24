#!/bin/sh
ps x|grep apache-tomcat |grep -v grep |awk '{print $1}'|xargs kill -9
