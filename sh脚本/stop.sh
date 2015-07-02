#!/bin/sh
ps x|grep riskcustomer77 |grep -v grep |awk '{print $1}'|xargs kill -9
