# harmony

To start the application run following command from project folder:

$ java -jar target/harmony-1.0-SNAPSHOT-fat.jar

or 

$ ./start.sh

To benchmark performance, use for example following command:

$ ab -n 1000 -c 10 http://localhost:8082/api/users