#run the java program using shell script and output to log file

nohup /usr/java6/bin/java -cp .:/java/lib/db2jcc.jar:/java/db2jcc_licence_cu.jar QueryBlob $1 $2 $3 > QueryBlob.log 
