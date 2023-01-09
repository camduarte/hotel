INSERT INTO reserve (checkin_date, checkout_date, value, payment_method) VALUES
('2023-04-12', '2023-04-19', 10000.0, 'CASH'),
('2023-05-25', '2023-05-28', 11000.0, 'DEBIT'),
('2023-02-09', '2023-02-24', 20000.0, 'CREDIT'),
('2023-06-20', '2023-07-20', 25000.0, 'CASH'),
('2023-09-10', '2023-09-25', 30000.0, 'CASH');

INSERT INTO guest (name, lastname, birthdate, nationality, phone_number, id_reserve) VALUES
('Elon', 'Musk', '1960-09-22', 'Somalí', '1231', 1),
('Marta', 'Sánchez', '1975-03-16', 'Croata', '7987', 2),
('Howard', 'Lovecraft', '1915-02-14', 'Guatemateco', '3737', 3),
('Richie', 'Blackmore', '1935-12-08', 'Zueco', '5765', 4),
('Shaggy', 'Rogers', '1987-11-29', 'Rumaí', '2432', 5);

INSERT INTO employee (name, lastname, email, password) VALUES
('admin', 'admin', 'admin@gmail.com', 'admin');
