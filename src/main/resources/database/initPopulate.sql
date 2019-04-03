/*INSERT ROLES*/
INSERT INTO railroad.roles(role_name) VALUE ('ROLE_USER');
INSERT INTO railroad.roles(role_name) VALUE ('ROLE_MODERATOR');
INSERT INTO railroad.roles(role_name) VALUE ('ROLE_ADMIN');

/*INSERT ADMIN USER*/
INSERT INTO railroad.users(username, password) VALUES ('Admin','$2a$11$/qcpIJk3sHYy0ANMQvMVMOj7ZBT5EE12V61rPOn1i9pQrZFr3yCAq');
INSERT INTO railroad.user_roles(user_id, role_id) VALUES (1,3);

/*INSERT STATIONS*/
INSERT INTO railroad.stations(station_name) VALUE('Moscow');
INSERT INTO railroad.stations(station_name) VALUE('Saint-Petersburg');
INSERT INTO railroad.stations(station_name) VALUE('Veliky Novgorod');
INSERT INTO railroad.stations(station_name) VALUE('Pskov');
INSERT INTO railroad.stations(station_name) VALUE('Tver');
INSERT INTO railroad.stations(station_name) VALUE('Ryazan');
INSERT INTO railroad.stations(station_name) VALUE('Tambov');
INSERT INTO railroad.stations(station_name) VALUE('Volgograd');
INSERT INTO railroad.stations(station_name) VALUE('Saratov');
INSERT INTO railroad.stations(station_name) VALUE('Samara');
INSERT INTO railroad.stations(station_name) VALUE('Ufa');
INSERT INTO railroad.stations(station_name) VALUE('Kazan');
INSERT INTO railroad.stations(station_name) VALUE('Nizhny Novgorod');

/*INSERT WAYS*/
INSERT INTO railroad.ways(first_station_id, second_station_id, distance) VALUES (2,3,200);
INSERT INTO railroad.ways(first_station_id, second_station_id, distance) VALUES (2,4,350);
INSERT INTO railroad.ways(first_station_id, second_station_id, distance) VALUES (3,5,390);
INSERT INTO railroad.ways(first_station_id, second_station_id, distance) VALUES (5,1,190);
INSERT INTO railroad.ways(first_station_id, second_station_id, distance) VALUES (1,6,200);
INSERT INTO railroad.ways(first_station_id, second_station_id, distance) VALUES (6,7,290);
INSERT INTO railroad.ways(first_station_id, second_station_id, distance) VALUES (7,8,525);
INSERT INTO railroad.ways(first_station_id, second_station_id, distance) VALUES (8,9,380);
INSERT INTO railroad.ways(first_station_id, second_station_id, distance) VALUES (9,10,480);
INSERT INTO railroad.ways(first_station_id, second_station_id, distance) VALUES (10,11,460);
INSERT INTO railroad.ways(first_station_id, second_station_id, distance) VALUES (11,12,530);
INSERT INTO railroad.ways(first_station_id, second_station_id, distance) VALUES (12,13,400);
INSERT INTO railroad.ways(first_station_id, second_station_id, distance) VALUES (1,13,470);
INSERT INTO railroad.ways(first_station_id, second_station_id, distance) VALUES (1,9,1000);

/*INSERT TRAINS*/
INSERT INTO railroad.trains(number,seats) VALUES (1,250);
INSERT INTO railroad.trains(number,seats) VALUES (2,250);
INSERT INTO railroad.trains(number,seats) VALUES (3,250);
INSERT INTO railroad.trains(number,seats) VALUES (4,250);
INSERT INTO railroad.trains(number,seats) VALUES (5,250);
INSERT INTO railroad.trains(number,seats) VALUES (6,250);
INSERT INTO railroad.trains(number,seats) VALUES (7,250);
INSERT INTO railroad.trains(number,seats) VALUES (8,250);
INSERT INTO railroad.trains(number,seats) VALUES (9,250);
INSERT INTO railroad.trains(number,seats) VALUES (10,250);

/*INSERT TRAIN_STATIONS*/

/*INSERT Moscow -> Tver*/
INSERT INTO railroad.train_stations(id, train_id, station_id) VALUES (0,1,1);
INSERT INTO railroad.train_stations(id, train_id, station_id) VALUES (1,1,5);

/*INSERT Tver -> Moscow*/
INSERT INTO railroad.train_stations(id, train_id, station_id) VALUES (0,2,5);
INSERT INTO railroad.train_stations(id, train_id, station_id) VALUES (1,2,1);

/*INSERT Tver -> Veliky Novgorod*/
INSERT INTO railroad.train_stations(id, train_id, station_id) VALUES (0,3,5);
INSERT INTO railroad.train_stations(id, train_id, station_id) VALUES (1,3,3);

/*INSERT Veliky Novgorod -> Tver*/
INSERT INTO railroad.train_stations(id, train_id, station_id) VALUES (0,4,3);
INSERT INTO railroad.train_stations(id, train_id, station_id) VALUES (1,4,5);

/*INSERT Veliky Novgorod -> Saint-Petersburg*/
INSERT INTO railroad.train_stations(id, train_id, station_id) VALUES (0,5,3);
INSERT INTO railroad.train_stations(id, train_id, station_id) VALUES (1,5,2);

/*INSERT Veliky Novgorod -> Saint-Petersburg*/
INSERT INTO railroad.train_stations(id, train_id, station_id) VALUES (0,6,2);
INSERT INTO railroad.train_stations(id, train_id, station_id) VALUES (1,6,3);

/*INSERT Moscow -> Saint-Petersburg*/
INSERT INTO railroad.train_stations(id, train_id, station_id) VALUES (0,7,1);
INSERT INTO railroad.train_stations(id, train_id, station_id) VALUES (1,7,5);
INSERT INTO railroad.train_stations(id, train_id, station_id) VALUES (2,7,3);
INSERT INTO railroad.train_stations(id, train_id, station_id) VALUES (3,7,2);

/*INSERT Moscow -> Saint-Petersburg*/
INSERT INTO railroad.train_stations(id, train_id, station_id) VALUES (0,8,2);
INSERT INTO railroad.train_stations(id, train_id, station_id) VALUES (1,8,3);
INSERT INTO railroad.train_stations(id, train_id, station_id) VALUES (2,8,5);
INSERT INTO railroad.train_stations(id, train_id, station_id) VALUES (3,8,1);

/*INSERT Moscow -> Tver -> Veliky Novgorod*/
INSERT INTO railroad.train_stations(id, train_id, station_id) VALUES (0,9,1);
INSERT INTO railroad.train_stations(id, train_id, station_id) VALUES (1,9,5);
INSERT INTO railroad.train_stations(id, train_id, station_id) VALUES (2,9,3);

/*INSERT Veliky Novgorod -> Tver -> Moscow*/
INSERT INTO railroad.train_stations(id, train_id, station_id) VALUES (0,10,3);
INSERT INTO railroad.train_stations(id, train_id, station_id) VALUES (1,10,5);
INSERT INTO railroad.train_stations(id, train_id, station_id) VALUES (2,10,1);

/*INSERT SCHEDULES*/

/*INSERT SCHEDULE FOR TRAIN NUMBER 1 (MOSCOW - TVER)*/
INSERT INTO railroad.schedules(arrival_date, depart_date, train_id, station_id) VALUES ('2019-04-01 05:00:00','2019-04-01 05:30:00',1,1);
INSERT INTO railroad.schedules(arrival_date, depart_date, train_id, station_id) VALUES ('2019-04-01 07:00:00','2019-04-01 07:30:00',1,5);

INSERT INTO railroad.schedules(arrival_date, depart_date, train_id, station_id) VALUES ('2019-04-02 05:00:00','2019-04-02 05:30:00',1,1);
INSERT INTO railroad.schedules(arrival_date, depart_date, train_id, station_id) VALUES ('2019-04-02 07:00:00','2019-04-02 07:30:00',1,5);

INSERT INTO railroad.schedules(arrival_date, depart_date, train_id, station_id) VALUES ('2019-04-03 05:00:00','2019-04-03 05:30:00',1,1);
INSERT INTO railroad.schedules(arrival_date, depart_date, train_id, station_id) VALUES ('2019-04-03 07:00:00','2019-04-03 07:30:00',1,5);

INSERT INTO railroad.schedules(arrival_date, depart_date, train_id, station_id) VALUES ('2019-04-04 05:00:00','2019-04-04 05:30:00',1,1);
INSERT INTO railroad.schedules(arrival_date, depart_date, train_id, station_id) VALUES ('2019-04-04 07:00:00','2019-04-04 07:30:00',1,5);

INSERT INTO railroad.schedules(arrival_date, depart_date, train_id, station_id) VALUES ('2019-04-05 05:00:00','2019-04-05 05:30:00',1,1);
INSERT INTO railroad.schedules(arrival_date, depart_date, train_id, station_id) VALUES ('2019-04-05 07:00:00','2019-04-05 07:30:00',1,5);


/*INSERT SCHEDULE FOR TRAIN NUMBER 2 (TVER - MOSCOW)*/
INSERT INTO railroad.schedules(arrival_date, depart_date, train_id, station_id) VALUES ('2019-04-01 16:00:00','2019-04-01 16:30:00',2,5);
INSERT INTO railroad.schedules(arrival_date, depart_date, train_id, station_id) VALUES ('2019-04-01 20:00:00','2019-04-01 20:30:00',2,1);

INSERT INTO railroad.schedules(arrival_date, depart_date, train_id, station_id) VALUES ('2019-04-02 16:00:00','2019-04-02 16:30:00',2,5);
INSERT INTO railroad.schedules(arrival_date, depart_date, train_id, station_id) VALUES ('2019-04-02 20:00:00','2019-04-02 20:30:00',2,1);

INSERT INTO railroad.schedules(arrival_date, depart_date, train_id, station_id) VALUES ('2019-04-03 16:00:00','2019-04-03 16:30:00',2,5);
INSERT INTO railroad.schedules(arrival_date, depart_date, train_id, station_id) VALUES ('2019-04-03 20:00:00','2019-04-03 20:30:00',2,1);

INSERT INTO railroad.schedules(arrival_date, depart_date, train_id, station_id) VALUES ('2019-04-04 16:00:00','2019-04-04 16:30:00',2,5);
INSERT INTO railroad.schedules(arrival_date, depart_date, train_id, station_id)VALUES ('2019-04-05 20:00:00','2019-04-04 20:30:00',2,1);

INSERT INTO railroad.schedules(arrival_date, depart_date, train_id, station_id) VALUES ('2019-04-05 16:00:00','2019-04-05 16:30:00',2,5);
INSERT INTO railroad.schedules(arrival_date, depart_date, train_id, station_id) VALUES ('2019-04-05 20:00:00','2019-04-05 20:30:00',2,1);



/*INSERT SCHEDULE FOR TRAIN NUMBER 3 (TVER - V.NOVGOROD)*/
INSERT INTO railroad.schedules(arrival_date, depart_date, train_id, station_id) VALUES ('2019-04-01 12:00:00','2019-04-01 12:30:00',3,5);
INSERT INTO railroad.schedules(arrival_date, depart_date, train_id, station_id) VALUES ('2019-04-01 15:00:00','2019-04-01 15:30:00',3,3);

INSERT INTO railroad.schedules(arrival_date, depart_date, train_id, station_id)VALUES ('2019-04-02 12:00:00','2019-04-02 12:30:00',3,5);
INSERT INTO railroad.schedules(arrival_date, depart_date, train_id, station_id) VALUES ('2019-04-02 15:00:00','2019-04-02 15:30:00',3,3);

INSERT INTO railroad.schedules(arrival_date, depart_date, train_id, station_id) VALUES ('2019-04-03 12:00:00','2019-04-03 12:30:00',3,5);
INSERT INTO railroad.schedules(arrival_date, depart_date, train_id, station_id) VALUES ('2019-04-03 15:00:00','2019-04-03 15:30:00',3,3);

INSERT INTO railroad.schedules(arrival_date, depart_date, train_id, station_id)VALUES ('2019-04-04 12:00:00','2019-04-04 12:30:00',3,5);
INSERT INTO railroad.schedules(arrival_date, depart_date, train_id, station_id) VALUES ('2019-04-04 15:00:00','2019-04-04 15:30:00',3,3);

INSERT INTO railroad.schedules(arrival_date, depart_date, train_id, station_id) VALUES ('2019-04-05 12:00:00','2019-04-05 12:30:00',3,5);
INSERT INTO railroad.schedules(arrival_date, depart_date, train_id, station_id) VALUES ('2019-04-05 15:00:00','2019-04-05 15:30:00',3,3);


/*INSERT SCHEDULE FOR TRAIN NUMBER 4 (V.NOVGOROD - TVER)*/
INSERT INTO railroad.schedules(arrival_date, depart_date, train_id, station_id) VALUES ('2019-04-01 09:00:00','2019-04-01 09:30:00',4,3);
INSERT INTO railroad.schedules(arrival_date, depart_date, train_id, station_id) VALUES ('2019-04-01 15:00:00','2019-04-01 15:30:00',4,5);

INSERT INTO railroad.schedules(arrival_date, depart_date, train_id, station_id) VALUES ('2019-04-02 09:00:00','2019-04-02 09:30:00',4,3);
INSERT INTO railroad.schedules(arrival_date, depart_date, train_id, station_id) VALUES ('2019-04-02 15:00:00','2019-04-02 15:30:00',4,5);

INSERT INTO railroad.schedules(arrival_date, depart_date, train_id, station_id) VALUES ('2019-04-03 09:00:00','2019-04-03 09:30:00',4,3);
INSERT INTO railroad.schedules(arrival_date, depart_date, train_id, station_id) VALUES ('2019-04-03 15:00:00','2019-04-03 15:30:00',4,5);

INSERT INTO railroad.schedules(arrival_date, depart_date, train_id, station_id) VALUES ('2019-04-04 09:00:00','2019-04-04 09:30:00',4,3);
INSERT INTO railroad.schedules(arrival_date, depart_date, train_id, station_id) VALUES ('2019-04-04 15:00:00','2019-04-04 15:30:00',4,5);

INSERT INTO railroad.schedules(arrival_date, depart_date, train_id, station_id) VALUES ('2019-04-05 09:00:00','2019-04-05 09:30:00',4,3);
INSERT INTO railroad.schedules(arrival_date, depart_date, train_id, station_id) VALUES ('2019-04-05 15:00:00','2019-04-05 15:30:00',4,5);


/*INSERT SCHEDULE FOR TRAIN NUMBER 5 (V.NOVGOROD - SPB)*/
INSERT INTO railroad.schedules(arrival_date, depart_date, train_id, station_id) VALUES ('2019-04-01 15:15:00','2019-04-01 15:30:00',5,3);
INSERT INTO railroad.schedules(arrival_date, depart_date, train_id, station_id) VALUES ('2019-04-01 21:00:00','2019-04-01 21:30:00',5,2);

INSERT INTO railroad.schedules(arrival_date, depart_date, train_id, station_id) VALUES ('2019-04-02 15:15:00','2019-04-02 15:30:00',5,3);
INSERT INTO railroad.schedules(arrival_date, depart_date, train_id, station_id) VALUES ('2019-04-02 21:00:00','2019-04-02 21:30:00',5,2);

INSERT INTO railroad.schedules(arrival_date, depart_date, train_id, station_id) VALUES ('2019-04-03 15:15:00','2019-04-03 15:30:00',5,3);
INSERT INTO railroad.schedules(arrival_date, depart_date, train_id, station_id) VALUES ('2019-04-03 21:00:00','2019-04-03 21:30:00',5,2);

INSERT INTO railroad.schedules(arrival_date, depart_date, train_id, station_id) VALUES ('2019-04-04 15:15:00','2019-04-04 15:30:00',5,3);
INSERT INTO railroad.schedules(arrival_date, depart_date, train_id, station_id) VALUES ('2019-04-04 21:00:00','2019-04-04 21:30:00',5,2);

INSERT INTO railroad.schedules(arrival_date, depart_date, train_id, station_id) VALUES ('2019-04-05 15:15:00','2019-04-05 15:30:00',5,3);
INSERT INTO railroad.schedules(arrival_date, depart_date, train_id, station_id) VALUES ('2019-04-05 21:00:00','2019-04-05 21:30:00',5,2);


/*INSERT SCHEDULE FOR TRAIN NUMBER 6 (SPB - V.NOVGOROD)*/
INSERT INTO railroad.schedules(arrival_date, depart_date, train_id, station_id) VALUES ('2019-04-01 05:00:00','2019-04-01 05:30:00',6,2);
INSERT INTO railroad.schedules(arrival_date, depart_date, train_id, station_id) VALUES ('2019-04-01 07:00:00','2019-04-01 07:30:00',6,3);

INSERT INTO railroad.schedules(arrival_date, depart_date, train_id, station_id) VALUES ('2019-04-02 05:00:00','2019-04-02 05:30:00',6,2);
INSERT INTO railroad.schedules(arrival_date, depart_date, train_id, station_id) VALUES ('2019-04-02 07:00:00','2019-04-02 07:30:00',6,3);

INSERT INTO railroad.schedules(arrival_date, depart_date, train_id, station_id) VALUES ('2019-04-03 05:00:00','2019-04-03 05:30:00',6,2);
INSERT INTO railroad.schedules(arrival_date, depart_date, train_id, station_id) VALUES ('2019-04-03 07:00:00','2019-04-03 07:30:00',6,3);

INSERT INTO railroad.schedules(arrival_date, depart_date, train_id, station_id) VALUES ('2019-04-04 05:00:00','2019-04-04 05:30:00',6,2);
INSERT INTO railroad.schedules(arrival_date, depart_date, train_id, station_id) VALUES ('2019-04-04 07:00:00','2019-04-04 07:30:00',6,3);

INSERT INTO railroad.schedules(arrival_date, depart_date, train_id, station_id) VALUES ('2019-04-05 05:00:00','2019-04-05 05:30:00',6,2);
INSERT INTO railroad.schedules(arrival_date, depart_date, train_id, station_id) VALUES ('2019-04-05 07:00:00','2019-04-05 07:30:00',6,3);


/*INSERT SCHEDULE FOR TRAIN NUMBER 7 (MSK - SPB)*/
INSERT INTO railroad.schedules(arrival_date, depart_date, train_id, station_id) VALUES ('2019-04-01 05:00:00','2019-04-01 05:30:00',7,1);
INSERT INTO railroad.schedules(arrival_date, depart_date, train_id, station_id) VALUES ('2019-04-01 07:00:00','2019-04-01 07:30:00',7,5);
INSERT INTO railroad.schedules(arrival_date, depart_date, train_id, station_id) VALUES ('2019-04-01 12:30:00','2019-04-01 12:30:00',7,3);
INSERT INTO railroad.schedules(arrival_date, depart_date, train_id, station_id) VALUES ('2019-04-01 15:30:00','2019-04-01 16:00:00',7,2);

INSERT INTO railroad.schedules(arrival_date, depart_date, train_id, station_id) VALUES ('2019-04-02 05:00:00','2019-04-02 05:30:00',7,1);
INSERT INTO railroad.schedules(arrival_date, depart_date, train_id, station_id) VALUES ('2019-04-02 07:00:00','2019-04-02 07:30:00',7,5);
INSERT INTO railroad.schedules(arrival_date, depart_date, train_id, station_id) VALUES ('2019-04-02 12:30:00','2019-04-02 12:30:00',7,3);
INSERT INTO railroad.schedules(arrival_date, depart_date, train_id, station_id) VALUES ('2019-04-02 15:30:00','2019-04-02 16:00:00',7,2);

INSERT INTO railroad.schedules(arrival_date, depart_date, train_id, station_id) VALUES ('2019-04-03 05:00:00','2019-04-03 05:30:00',7,1);
INSERT INTO railroad.schedules(arrival_date, depart_date, train_id, station_id) VALUES ('2019-04-03 07:00:00','2019-04-03 07:30:00',7,5);
INSERT INTO railroad.schedules(arrival_date, depart_date, train_id, station_id) VALUES ('2019-04-03 12:30:00','2019-04-03 12:30:00',7,3);
INSERT INTO railroad.schedules(arrival_date, depart_date, train_id, station_id) VALUES ('2019-04-03 15:30:00','2019-04-03 16:00:00',7,2);

INSERT INTO railroad.schedules(arrival_date, depart_date, train_id, station_id) VALUES ('2019-04-04 05:00:00','2019-04-04 05:30:00',7,1);
INSERT INTO railroad.schedules(arrival_date, depart_date, train_id, station_id) VALUES ('2019-04-04 07:00:00','2019-04-04 07:30:00',7,5);
INSERT INTO railroad.schedules(arrival_date, depart_date, train_id, station_id) VALUES ('2019-04-04 12:30:00','2019-04-04 12:30:00',7,3);
INSERT INTO railroad.schedules(arrival_date, depart_date, train_id, station_id) VALUES ('2019-04-04 15:30:00','2019-04-04 16:00:00',7,2);

INSERT INTO railroad.schedules(arrival_date, depart_date, train_id, station_id) VALUES ('2019-04-05 05:00:00','2019-04-05 05:30:00',7,1);
INSERT INTO railroad.schedules(arrival_date, depart_date, train_id, station_id) VALUES ('2019-04-05 07:00:00','2019-04-05 07:30:00',7,5);
INSERT INTO railroad.schedules(arrival_date, depart_date, train_id, station_id) VALUES ('2019-04-05 12:30:00','2019-04-05 12:30:00',7,3);
INSERT INTO railroad.schedules(arrival_date, depart_date, train_id, station_id) VALUES ('2019-04-05 15:30:00','2019-04-05 16:00:00',7,2);


/*INSERT SCHEDULE FOR TRAIN NUMBER 8 (SPB - MSK)*/
INSERT INTO railroad.schedules(arrival_date, depart_date, train_id, station_id) VALUES ('2019-04-01 05:00:00','2019-04-01 05:30:00',8,2);
INSERT INTO railroad.schedules(arrival_date, depart_date, train_id, station_id) VALUES ('2019-04-01 07:00:00','2019-04-01 07:30:00',8,3);
INSERT INTO railroad.schedules(arrival_date, depart_date, train_id, station_id) VALUES ('2019-04-01 12:30:00','2019-04-01 12:30:00',8,5);
INSERT INTO railroad.schedules(arrival_date, depart_date, train_id, station_id) VALUES ('2019-04-01 15:30:00','2019-04-01 16:00:00',8,1);

INSERT INTO railroad.schedules(arrival_date, depart_date, train_id, station_id) VALUES ('2019-04-02 05:00:00','2019-04-02 05:30:00',8,2);
INSERT INTO railroad.schedules(arrival_date, depart_date, train_id, station_id) VALUES ('2019-04-02 07:00:00','2019-04-02 07:30:00',8,3);
INSERT INTO railroad.schedules(arrival_date, depart_date, train_id, station_id) VALUES ('2019-04-02 12:30:00','2019-04-02 12:30:00',8,5);
INSERT INTO railroad.schedules(arrival_date, depart_date, train_id, station_id) VALUES ('2019-04-02 15:30:00','2019-04-02 16:00:00',8,1);

INSERT INTO railroad.schedules(arrival_date, depart_date, train_id, station_id) VALUES ('2019-04-03 05:00:00','2019-04-03 05:30:00',8,2);
INSERT INTO railroad.schedules(arrival_date, depart_date, train_id, station_id) VALUES ('2019-04-03 07:00:00','2019-04-03 07:30:00',8,3);
INSERT INTO railroad.schedules(arrival_date, depart_date, train_id, station_id) VALUES ('2019-04-03 12:30:00','2019-04-03 12:30:00',8,5);
INSERT INTO railroad.schedules(arrival_date, depart_date, train_id, station_id) VALUES ('2019-04-03 15:30:00','2019-04-03 16:00:00',8,1);

INSERT INTO railroad.schedules(arrival_date, depart_date, train_id, station_id) VALUES ('2019-04-04 05:00:00','2019-04-04 05:30:00',8,2);
INSERT INTO railroad.schedules(arrival_date, depart_date, train_id, station_id) VALUES ('2019-04-04 07:00:00','2019-04-04 07:30:00',8,3);
INSERT INTO railroad.schedules(arrival_date, depart_date, train_id, station_id) VALUES ('2019-04-04 12:30:00','2019-04-04 12:30:00',8,5);
INSERT INTO railroad.schedules(arrival_date, depart_date, train_id, station_id) VALUES ('2019-04-04 15:30:00','2019-04-04 16:00:00',8,1);

INSERT INTO railroad.schedules(arrival_date, depart_date, train_id, station_id) VALUES ('2019-04-05 05:00:00','2019-04-05 05:30:00',8,2);
INSERT INTO railroad.schedules(arrival_date, depart_date, train_id, station_id) VALUES ('2019-04-05 07:00:00','2019-04-05 07:30:00',8,3);
INSERT INTO railroad.schedules(arrival_date, depart_date, train_id, station_id) VALUES ('2019-04-05 12:30:00','2019-04-05 12:30:00',8,5);
INSERT INTO railroad.schedules(arrival_date, depart_date, train_id, station_id)VALUES ('2019-04-05 15:30:00','2019-04-05 16:00:00',8,1);