INSERT INTO flights
(id, departure_country, destination_country, started_at, ended_at,
 delay_started_at, distance, estimated_flight_time,  status, air_company_id, created_at)
VALUES (1, 'Ukraine', 'Poland', '2022-02-07 08:00', '2022-02-07 09:40', null, 1000, 90,
        'COMPLETED', null, '2012-02-09');

INSERT INTO flights
(id, departure_country, destination_country, started_at, ended_at,
 delay_started_at, distance, estimated_flight_time,  status, air_company_id, created_at)
VALUES (2, 'Poland', 'Ukraine', '2022-02-05 16:00', '2022-02-05 17:30', null, 1000, 100,
        'COMPLETED', null, '2012-02-09');

INSERT INTO flights
(id, departure_country, destination_country, started_at, ended_at,
 delay_started_at, distance, estimated_flight_time,  status, air_company_id, created_at)
VALUES (3, 'Ukraine', 'Germany', '2022-02-06 07:00', '2022-02-07 09:30', null, 1400, 130,
        'COMPLETED', null, '2012-02-09');
