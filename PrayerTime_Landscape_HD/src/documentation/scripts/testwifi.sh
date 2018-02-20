#!/bin/bash

#TESTIP=192.168.1.1
TESTIP=$(/sbin/ip route | awk '/default/ { print $3 }')

echo -n ${TESTIP}
ping -c4 ${TESTIP} > /dev/null

if [ $? != 0 ]
then
    logger -t $0 "WiFi seems down, restarting"
    echo -n "WiFi seems down, restarting"
    ifdown --force wlan0
    ifup wlan0
else
    logger -t $0 "WiFi seems up."
    echo -n "WiFi seems up."
fi
