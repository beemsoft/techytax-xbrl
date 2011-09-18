ALTER TABLE account_balance MODIFY COLUMN `balance` decimal(50,2) NOT NULL;

ALTER TABLE accounts CHANGE name name varchar(200) not null;
ALTER TABLE accounts CHANGE description description varchar(200) not null;
ALTER TABLE accounts CHANGE number number varchar(50) not null;

ALTER TABLE boekwaarde MODIFY COLUMN `saldo` decimal(50) NOT NULL;
ALTER TABLE restwaarde MODIFY COLUMN `restwaarde` decimal(50) NOT NULL;

ALTER TABLE kosten MODIFY COLUMN `bedrag` decimal(50,2) NOT NULL;
ALTER TABLE kosten MODIFY COLUMN `btw` decimal(48,2) NOT NULL;
ALTER TABLE kosten MODIFY COLUMN `omschrijving` varchar(400) NOT NULL;

