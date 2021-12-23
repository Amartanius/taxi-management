INSERT INTO FORMULA (CITY_ID, FORMULA) VALUES
  ('PARIS', '(1.0 + ( 2.5 * Distance) + ( (#hour >= 6 && #hour < 20) ? 0 : 0.5) + ((#hour >= 16 && #hour < 19) ? 1 : 0))');

INSERT INTO RIDE (Driver_Id, Start_Time, Status, Distance, Duration, Charge, City_Id, created_By, creation_Date, last_Modified_By, last_Modified_Date) VALUES
  ('DRIVER_1',  PARSEDATETIME('17-09-2012 20:47:52.690', 'dd-MM-yyyy hh:mm:ss.SS'), 'STARTED' , 1.0 , PARSEDATETIME('00:22:12.690', 'HH:mm:ss.SS') , NULL , 'PARIS' , 'EL HOUSSINE' , PARSEDATETIME('17-09-2012 18:47:52.690', 'dd-MM-yyyy hh:mm:ss.SS') , NULL , NULL),
  ('DRIVER_1',  PARSEDATETIME('17-09-2012 12:47:52.690', 'dd-MM-yyyy hh:mm:ss.SS'), 'ARRIVED' , 3.2 , PARSEDATETIME('00:12:12.690', 'HH:mm:ss.SS') , 6.1 , 'PARIS' , 'EL HOUSSINE' , PARSEDATETIME('17-09-2012 18:47:52.690', 'dd-MM-yyyy hh:mm:ss.SS') , NULL , NULL),
  ('DRIVER_1',  PARSEDATETIME('17-09-2012 18:47:52.690', 'dd-MM-yyyy hh:mm:ss.SS'), 'ARRIVED' , 3.2 , PARSEDATETIME('00:12:12.690', 'HH:mm:ss.SS') , NULL , 'PARIS' , 'EL HOUSSINE' , PARSEDATETIME('17-09-2012 18:47:52.690', 'dd-MM-yyyy hh:mm:ss.SS') , NULL , NULL),
  ('DRIVER_2',  PARSEDATETIME('18-09-2012 13:47:52.690', 'dd-MM-yyyy hh:mm:ss.SS'), 'ARRIVED' , 2.2 , PARSEDATETIME('00:12:12.690', 'HH:mm:ss.SS') , NULL , 'PARIS' , 'EL HOUSSINE' , PARSEDATETIME('17-09-2012 18:47:52.690', 'dd-MM-yyyy hh:mm:ss.SS') , NULL , NULL),
  ('DRIVER_2',  PARSEDATETIME('19-09-2012 14:47:52.690', 'dd-MM-yyyy hh:mm:ss.SS'), 'ARRIVED' , 2.2 , PARSEDATETIME('00:12:12.690', 'HH:mm:ss.SS') , NULL , 'PARIS' , 'EL HOUSSINE' , PARSEDATETIME('17-09-2012 18:47:52.690', 'dd-MM-yyyy hh:mm:ss.SS') , NULL , NULL),
  ('DRIVER_3',  PARSEDATETIME('17-09-2012 15:47:52.690', 'dd-MM-yyyy hh:mm:ss.SS'), 'ARRIVED' , 1.2 , PARSEDATETIME('00:12:12.690', 'HH:mm:ss.SS') , NULL , 'PARIS' , 'EL HOUSSINE' , PARSEDATETIME('17-09-2012 18:47:52.690', 'dd-MM-yyyy hh:mm:ss.SS') , NULL , NULL),
  ('DRIVER_2',  PARSEDATETIME('20-09-2012 18:47:52.690', 'dd-MM-yyyy hh:mm:ss.SS'), 'ARRIVED' , 3.2 , PARSEDATETIME('00:12:12.690', 'HH:mm:ss.SS') , NULL , 'PARIS' , 'EL HOUSSINE' , PARSEDATETIME('17-09-2012 18:47:52.690', 'dd-MM-yyyy hh:mm:ss.SS') , NULL , NULL),
  ('DRIVER_1',  PARSEDATETIME('17-09-2012 16:27:52.690', 'dd-MM-yyyy hh:mm:ss.SS'), 'ARRIVED' , 6.2 , PARSEDATETIME('00:12:12.690', 'HH:mm:ss.SS') , NULL , 'PARIS' , 'EL HOUSSINE' , PARSEDATETIME('17-09-2012 18:47:52.690', 'dd-MM-yyyy hh:mm:ss.SS') , NULL , NULL),
  ('DRIVER_4',  PARSEDATETIME('21-09-2012 18:34:52.690', 'dd-MM-yyyy hh:mm:ss.SS'), 'ARRIVED' , 2.2 , PARSEDATETIME('00:12:12.690', 'HH:mm:ss.SS') , NULL , 'PARIS' , 'EL HOUSSINE' , PARSEDATETIME('17-09-2012 18:47:52.690', 'dd-MM-yyyy hh:mm:ss.SS') , NULL , NULL),
  ('DRIVER_6',  PARSEDATETIME('21-09-2012 18:37:52.690', 'dd-MM-yyyy hh:mm:ss.SS'), 'ARRIVED' , 0.2 , PARSEDATETIME('00:12:12.690', 'HH:mm:ss.SS') , NULL , 'PARIS' , 'EL HOUSSINE' , PARSEDATETIME('17-09-2012 18:47:52.690', 'dd-MM-yyyy hh:mm:ss.SS') , NULL , NULL),
  ('DRIVER_1',  PARSEDATETIME('18-09-2012 18:47:52.690', 'dd-MM-yyyy hh:mm:ss.SS'), 'ARRIVED' , 2.2 , PARSEDATETIME('00:12:12.690', 'HH:mm:ss.SS') , NULL , 'PARIS' , 'EL HOUSSINE' , PARSEDATETIME('17-09-2012 18:47:52.690', 'dd-MM-yyyy hh:mm:ss.SS') , NULL , NULL),
  ('DRIVER_1',  PARSEDATETIME('19-09-2012 13:00:52.690', 'dd-MM-yyyy hh:mm:ss.SS'), 'ARRIVED' , 2.3 , PARSEDATETIME('00:02:12.690', 'HH:mm:ss.SS') , NULL , 'PARIS' , 'EL HOUSSINE' , PARSEDATETIME('17-09-2012 18:47:52.690', 'dd-MM-yyyy hh:mm:ss.SS') , NULL , NULL);
