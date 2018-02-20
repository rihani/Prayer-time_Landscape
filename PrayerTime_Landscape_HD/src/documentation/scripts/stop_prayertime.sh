#!/bin/bash
pid=`ps aux | grep JavaFXApplication4 | awk '{print $2}'`
kill -9 $pid
