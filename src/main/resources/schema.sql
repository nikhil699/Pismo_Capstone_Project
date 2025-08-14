CREATE TABLE IF NOT EXISTS accounts (
    account_id SERIAL PRIMARY KEY,
    document_number VARCHAR(20) UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS operation_types (
    operation_type_id SERIAL PRIMARY KEY,
    description VARCHAR(50) UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS transactions (
    transaction_id SERIAL PRIMARY KEY,
    account_id BIGINT NOT NULL REFERENCES accounts(account_id),
    operation_type_id BIGINT NOT NULL REFERENCES operation_types(operation_type_id),
    amount NUMERIC(15,2) NOT NULL,
    event_date TIMESTAMP NOT NULL
);