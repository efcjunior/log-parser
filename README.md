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

##

## 
