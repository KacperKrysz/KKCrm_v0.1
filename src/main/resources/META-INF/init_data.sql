DELETE FROM kkcrmdatabase.users;
DELETE FROM kkcrmdatabase.clients;
insert into kkcrmdatabase.activities (contactPerson,description,priority,status,subject,client_id) values ('kacper','coś','wazne','teraz','planowanie','2')
INSERT INTO kkcrmdatabase.users (email, login, fullName, password) VALUES ('kacperkrysz@gmail.com', 'kacper', 'Kacper Kryszylowicz', 'kacper');
INSERT INTO kkcrmdatabase.clients (fullName, shortName, type, industry, address, nip, source, contactPerson, trainingPatron, softwarePatron, additionalInfo, haspqfmea, pqfmeaUpdateDate, haspqfmeaPlus, pqfmeaPlusUpdateDate, haspqmsa, pqmsaUpdateDate, needManualUpdate, creationDate, modificationDate) VALUES('Pełne Imię i Nazwisko 1', 'Skrócona Nazwa 1', 'Typ 1', 'Branża 1', 'Adres 1', 'NIP 1', 'Źródło 1', 'Osoba Kontaktowa 1', 'Patron Szkolenia 1', 'Kacper Kryszylowicz', 'Dodatkowe Informacje 1', 1, NOW(), 1, NOW(), 1, NOW(), 1, NOW(), NOW());
