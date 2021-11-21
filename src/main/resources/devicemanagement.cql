CREATE TABLE IF NOT EXISTS device(
   deviceid uuid,
   longitude double,
   latitude double,
   PRIMARY KEY (deviceid)
   );


CREATE TABLE IF NOT EXISTS devicedetails(
   deviceid uuid,
   humidity int,
   unit text,
   value double,
   timestamp timestamp,
   PRIMARY KEY (deviceid, timestamp));
