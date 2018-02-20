#!/bin/bash
IP=10.8.0.1
#Look for number of received pings, if 0 received then restart OpenVPN service
RESULT=`ping -c 8 -W 2 $IP | grep transm | awk '{print $4}'`
echo "Result is $RESULT"

if [ $RESULT -eq 0 ]
then

sudo pkill -9 openvpn
sudo openvpn --daemon --cd /etc/openvpn --config  middle-path.conf

echo "OpenVPN restart"
date
else

echo "No restart needed"
date

fi