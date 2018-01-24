# log-parser
The goal is to write a parser in Java that parses web server access log file, loads the log to MySQL and checks if a given IP makes more than a certain number of requests for the given duration. 

# SQL

(1) Write MySQL query to find IPs that mode more than a certain number of requests for a given time period.

```
select ip, count(*) as count from request_Http_log where date_format(dt,'%Y-%m-%d.%H:%i:%s') between date_format('2017-01-01.13:00:00','%Y-%m-%d.%H:%i:%s') and date_format('2017-01-01.14:00:00','%Y-%m-%d.%H:%i:%s') group by ip having (count > 500)
```
(2) Write MySQL query to find requests made by a given IP.

```
select * from request_Http_log where ip = '';
```

# Java
