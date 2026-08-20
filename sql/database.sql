
-- database.sql

DROP TYPE IF EXISTS account_type_enum CASCADE;
CREATE TYPE account_type_enum AS ENUM (
    'STANDARD',
     'PREMIUM',
      'GOLD'
);

DROP TYPE IF EXISTS transaction_type_enum CASCADE;
CREATE TYPE transaction_type_enum AS ENUM (
    'IN',
     'OUT'
);

DROP TABLE IF EXISTS account CASCADE;
CREATE TABLE account (
                         id            VARCHAR(36)         PRIMARY KEY,
                         account_type  account_type_enum   NOT NULL
);

DROP TABLE IF EXISTS transaction CASCADE;
CREATE TABLE transaction (
                             id                 VARCHAR(36)             PRIMARY KEY,
                             created_at         TIMESTAMP               NOT NULL DEFAULT CURRENT_TIMESTAMP,
                             transaction_type   transaction_type_enum   NOT NULL,
                             amount             NUMERIC(19, 4)          NOT NULL CHECK (amount >= 0),
                             reason             VARCHAR(255),
                             account_id         VARCHAR(36)             NOT NULL,

                             CONSTRAINT fk_transaction_account
                                 FOREIGN KEY (account_id)
                                     REFERENCES account (id)
                                     ON DELETE CASCADE
);

CREATE INDEX idx_transaction_account_id ON transaction (account_id);

CREATE INDEX idx_transaction_type ON transaction (transaction_type);

CREATE INDEX idx_transaction_created_at ON transaction (created_at);

CREATE OR REPLACE VIEW account_balance AS
SELECT
    a.id AS account_id,
    a.account_type,
    COALESCE(SUM(CASE WHEN t.transaction_type = 'IN'  THEN t.amount ELSE 0 END), 0)
        - COALESCE(SUM(CASE WHEN t.transaction_type = 'OUT' THEN t.amount ELSE 0 END), 0) AS balance
FROM account a
         LEFT JOIN transaction t ON t.account_id = a.id
GROUP BY a.id, a.account_type;
