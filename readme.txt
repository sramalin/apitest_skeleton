#How to run the test from commandline
mvn clean test -Denvironment=<environment>

<environment> can be ci,sqe,local

Example : mvn clean test -Denvironment=ci
#How to Setup in Jenkins