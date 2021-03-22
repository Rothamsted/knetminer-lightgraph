#!/bin/bash

# This is the Bash Launcher.
# 

# These are passed to the JVM. they're appended, so that you can predefine it from the shell
[[ "$JAVA_TOOL_OPTIONS" =~ -Xm[s|x] ]] || JAVA_TOOL_OPTIONS="$JAVA_TOOL_OPTIONS -Xms2G -Xmx4G"

# We always work with universal text encoding.
[[ "$JAVA_TOOL_OPTIONS" =~ -Dfile.encoding ]] || JAVA_TOOL_OPTIONS="$JAVA_TOOL_OPTIONS -Dfile.encoding=UTF-8"

# Monitoring with jvisualvm/jconsole (end-user doesn't usually need this)
#JAVA_TOOL_OPTIONS="$JAVA_TOOL_OPTIONS 
# -Dcom.sun.management.jmxremote.port=5010
# -Dcom.sun.management.jmxremote.authenticate=false
# -Dcom.sun.management.jmxremote.ssl=false"
       
# Used for invoking a command in debug mode (end user doesn't usually need this)
#JAVA_TOOL_OPTIONS="$JAVA_TOOL_OPTIONS -Xdebug -Xnoagent"
#JAVA_TOOL_OPTIONS="$JAVA_TOOL_OPTIONS -Djava.compiler=NONE -Xrunjdwp:transport=dt_socket,server=y,suspend=y,address=5005"

export JAVA_TOOL_OPTIONS

# You shouldn't need to change the rest
#
###

cd "$(dirname $0)"
mydir="$(pwd)"

# Additional .jar files or other CLASSPATH directories can be added as you need.
#
# $mydir is included due to files like log4j or Spring files often looked up in the CP.
# Remove it if you don't need it and prefer a stricter CP
# 
# See https://javarevisited.blogspot.com/2012/10/5-ways-to-add-multiple-jar-to-classpath-java.html 
# for details about the classpath in Java.
# 
export CLASSPATH="$CLASSPATH:$mydir:$mydir/lib/*"

# See here for an explanation about ${1+"$@"} :
# http://stackoverflow.com/questions/743454/space-in-java-command-line-arguments 

java uk.ac.ebi.example.App ${1+"$@"}

ex_code=$?

# We assume stdout is for actual output, that might be pipelined to some other command, the rest (including logging)
# goes to stderr.
# 
echo Java Finished. Quitting the Shell Too. >&2
echo >&2
exit $ex_code
