
-- Insert 30 rows with verified set to true
INSERT INTO users (id, creation_time, city, street_name, z_code)
SELECT s.id, current_timestamp, 'Boston', 'Brookline Avenue', s.id
FROM generate_series(1,30) AS s(id);
