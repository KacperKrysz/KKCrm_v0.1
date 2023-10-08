DELETE FROM kkcrmdatabase.users;
DELETE FROM kkcrmdatabase.clients;
INSERT INTO kkcrmdatabase.users (email, login, name, password, surname) VALUES (null, 'kacper', null, 'kacper', null);
INSERT INTO kkcrmdatabase.clients (fullName, shortName, type, industry, address, nip, source, contactPerson, trainingPatron, softwarePatron, additionalInfo, haspqfmea, pqfmeaUpdateDate, haspqfmeaPlus, pqfmeaPlusUpdateDate, haspqmsa, pqmsaUpdateDate, needManualUpdate, creationDate, modificationDate) VALUES('Pełne Imię i Nazwisko 1', 'Skrócona Nazwa 1', 'Typ 1', 'Branża 1', 'Adres 1', 'NIP 1', 'Źródło 1', 'Osoba Kontaktowa 1', 'Patron Szkolenia 1', 'Patron Oprogramowania 1', 'Dodatkowe Informacje 1', 1, NOW(), 1, NOW(), 1, NOW(), 1, NOW(), NOW());
