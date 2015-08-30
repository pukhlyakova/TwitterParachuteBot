#!/bin/bash

exec java -ea -classpath lib/*:. Twitbot
echo $! > $PID_RC