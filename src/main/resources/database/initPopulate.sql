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
INSERT INTO railroad.ways(first_station_id, second_station_id) VALUES (2,3);
INSERT INTO railroad.ways(first_station_id, second_station_id) VALUES (2,4);
INSERT INTO railroad.ways(first_station_id, second_station_id) VALUES (3,5);
INSERT INTO railroad.ways(first_station_id, second_station_id) VALUES (5,1);
INSERT INTO railroad.ways(first_station_id, second_station_id) VALUES (1,6);
INSERT INTO railroad.ways(first_station_id, second_station_id) VALUES (6,7);
INSERT INTO railroad.ways(first_station_id, second_station_id) VALUES (7,8);
INSERT INTO railroad.ways(first_station_id, second_station_id) VALUES (8,9);
INSERT INTO railroad.ways(first_station_id, second_station_id) VALUES (9,10);
INSERT INTO railroad.ways(first_station_id, second_station_id) VALUES (10,11);
INSERT INTO railroad.ways(first_station_id, second_station_id) VALUES (11,12);
INSERT INTO railroad.ways(first_station_id, second_station_id) VALUES (12,13);
INSERT INTO railroad.ways(first_station_id, second_station_id) VALUES (1,13);
INSERT INTO railroad.ways(first_station_id, second_station_id) VALUES (1,9);

/*INSERT TRAINS*/
INSERT INTO railroad.trains(number,seats) VALUES (1,250);
INSERT INTO railroad.trains(number,seats) VALUES (2,250);
INSERT INTO railroad.trains(number,seats) VALUES (3,250);
INSERT INTO railroad.trains(number,seats) VALUES (4,250);
INSERT INTO railroad.trains(number,seats) VALUES (5,250);
INSERT INTO railroad.trains(number,seats) VALUES (6,250);
INSERT INTO railroad.trains(number,seats) VALUES (7,250);
INSERT INTO railroad.trains(number,seats) VALUES (8,250);


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
