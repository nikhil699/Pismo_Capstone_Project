CREATE TABLE IF NOT EXISTS Accounts (
    Account_id SERIAL PRIMARY KEY,
    Document_Number VARCHAR(20) UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS OperationsTypes (
    OperationType_ID SERIAL PRIMARY KEY,
    Description VARCHAR(50) UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS Transactions (
    Transaction_Id SERIAL PRIMARY KEY,
    Account_Id BIGINT NOT NULL REFERENCES accounts(account_id),
    OperationType_ID BIGINT NOT NULL REFERENCES operation_types(operation_type_id),
    Amount NUMERIC(15,2) NOT NULL,
    EventDate TIMESTAMP NOT NULL
);
