#!/bin/sh
if [ -z "$SB_ARGS" ]; then
    echo "running with default spring config"
    java -Djava.security.egd="file:/dev/./urandom" ${JAVA_OPTS} -jar /app.jar
else
    echo "running with configration ${SB_ARGS}"
    java -Djava.security.egd="file:/dev/./urandom" ${JAVA_OPTS} ${SB_ARGS} -jar /app.jar
fi
