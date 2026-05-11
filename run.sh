javac -classpath $(hadoop classpath) *.java

jar cf loginDuration.jar *.class

hdfs dfs -mkdir -p /assignment2/input

hdfs dfs -put -f login_logs.txt /assignment2/input

hdfs dfs -rm -r /assignment2/output

hadoop jar loginDuration.jar LoginDriver \
/assignment2/input /assignment2/output

hdfs dfs -cat /assignment2/output/part-r-00000