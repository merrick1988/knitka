#!/bin/bash
# Start jetty with debugging enabled

maven_opts="-Xdebug -Xnoagent -Dcom.sun.management.jmxremote -Djava.compiler=NONE -Xrunjdwp:transport=dt_socket,address=4004,server=y,suspend=n -Djava.net.preferIPv4Stack=true -Dmaven.test.skip=true"

export MAVEN_OPTS=$maven_opts

mvn -Dspring.profiles.active=envconfig -Pjetty-run

