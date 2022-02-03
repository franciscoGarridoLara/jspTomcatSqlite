all:
	../../bin/catalina.sh stop
	javac -cp ../../lib/servlet-api.jar WEB-INF/src/*/*.java -d WEB-INF/classes
	../../bin/catalina.sh run
