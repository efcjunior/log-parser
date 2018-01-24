# log-parser
The goal is to write a parser in Java that parses web server access log file, loads the log to MySQL and checks if a given IP makes more than a certain number of requests for the given duration. 

# SQL Query

(1) Write MySQL query to find IPs that mode more than a certain number of requests for a given time period.

```
select ip, count(*) as 'requests_total' from request_http_log where date_format(dt,'%Y-%m-%d.%H:%i:%s') between date_format('2017-01-01.00:00:00','%Y-%m-%d.%H:%i:%s') and date_format('2017-01-01.23:59:59','%Y-%m-%d.%H:%i:%s') group by ip having (requests_total > 500);

```
(2) Write MySQL query to find requests made by a given IP.

```
select * from request_http_log where ip = '192.168.102.136';
```

# Java 

## Execution instructions:

1. Download e extract folder dist. There are folowing items into this folder:

+dist\parser.jar
+dist\jdbc.properties
+dist\schema_db_parser.sql

2. Leave jdbc.properties file at same folder where is the parser.jar file;  

3. Open the file jdbc.properties and change connection data;

4. Windows command line: Put access log file path between quote: 

Ex.:

* java -cp "parser.jar" com.ef.Parser --accesslog="C:\Users\access.log" --startDate=2017-01-01.13:00:00 --duration=hourly --threshold=100

4. Linux command line:

Ex.:

* java -cp "parser.jar" com.ef.Parser --accesslog=/folder/file --startDate=2017-01-01.13:00:00 --duration=hourly --threshold=100
