platform='unknown'
unamestr=`uname`
if [[ "$unamestr" == 'Darwin' ]]; then
	export JAVA_HOME=$(/usr/libexec/java_home)
fi
mvn clean install
java -jar target/Document-1.0.jar 1 
