USE jManagr;

START TRANSACTION;

INSERT INTO User
    (firstname, lastname, username, role, department_id, active, deleted, version)
VALUES
    ("Bill", "Gates", "m$.word", "USER", 1, 1, 0, CURRENT_TIMESTAMP),
    ("Linus", "Torvalds", "tux", "ADMIN", 2, 1, 0, CURRENT_TIMESTAMP),
    ("Edward", "Snowden", "iNSAnity", "ADMIN", 3, 1, 0, CURRENT_TIMESTAMP),
    ("Mark", "Zuckerberg", "needYourData", "AGENT", 1, 1, 0, CURRENT_TIMESTAMP),
    ("Steve", "Jobs", "one_More_Thing", "AGENT", 1, 1, 0, CURRENT_TIMESTAMP),
    ("Tim", "Pritlove", "ccc", "USER", 3, 1, 0, CURRENT_TIMESTAMP);


INSERT INTO Department
    (name, active, deleted, version)
VALUES
    ("CEO's", 1, 0, CURRENT_TIMESTAMP),
    ("Open Source Development", 1, 0, CURRENT_TIMESTAMP),
    ("Freedom of Speech Research Inc.", 1, 0, CURRENT_TIMESTAMP),
    ("/dev/null", 1, 0, CURRENT_TIMESTAMP);

INSERT INTO Resource
    (name, parent_id, active, deleted, version)
VALUES
    ("NSA Headquarter", NULL, 1, 0, CURRENT_TIMESTAMP),
    ("Flughafen Moskau-Scheremetjewo", NULL, 1, 0, CURRENT_TIMESTAMP),
    ("Universität Helsinki", NULL, 1, 0, CURRENT_TIMESTAMP),
    ("M$ Headquarter", NULL, 1, 0, CURRENT_TIMESTAMP),
    ("The cloud", NULL, 1, 0, CURRENT_TIMESTAMP),
    ("An Apple Tree®", NULL, 1, 0, CURRENT_TIMESTAMP),
    ("Metaebene", NULL, 1, 0, CURRENT_TIMESTAMP),
    ("Admin-PC", 1, 1, 0, CURRENT_TIMESTAMP),
    ("Putin's Privatjet", 2, 1, 0, CURRENT_TIMESTAMP),
    ("git (Software)", 3, 1, 0, CURRENT_TIMESTAMP),
    ("M$ Word", 4, 1, 0, CURRENT_TIMESTAMP),
    ("Trunk", 6, 1, 0, CURRENT_TIMESTAMP),
    ("Dokumente", 8, 1, 0, CURRENT_TIMESTAMP),
    ("Leaf", 12, 1, 0, CURRENT_TIMESTAMP),
    ("Apple", 12, 1, 0, CURRENT_TIMESTAMP);

INSERT INTO Ticket
    (name, description, `status`, `date`, resource_id, department_id, `user_id`, active, deleted, version)
VALUES
    ("Bedenken", "Wir sollten nicht so viele Menschen abhören", "CLOSED", "2013-02-10 10:00:00", 1, NULL, 4, 1, 0, CURRENT_TIMESTAMP);


INSERT INTO ResourceData
    (resource_id, `key`, `value`, active, deleted, version)
VALUES
    (1, "Adresse", "Crypto City, Fort Meade,Maryland, USA", 1, 0, CURRENT_TIMESTAMP),
    (1, "Boss", "Richard H. Ledgett", 1, 0, CURRENT_TIMESTAMP),
    (1, "Haushaltsvolumen", "10,8 Mrd. $", 1, 0, CURRENT_TIMESTAMP),
    (1, "Mitarbeiter", "40.000", 1, 0, CURRENT_TIMESTAMP),
    (1, "Website", "www.nsa.gov", 1, 0, CURRENT_TIMESTAMP),
    (10, "Version", "2.0.0", 1, 0, CURRENT_TIMESTAMP),
    (10, "Lizenz", "GNU GPLv2", 1, 0, CURRENT_TIMESTAMP),
    (10, "Programmiersprache", "C", 1, 0, CURRENT_TIMESTAMP),
    (13, "Geheimhaltungsstufe", "TOP SECRET", 1, 0, CURRENT_TIMESTAMP);

COMMIT;