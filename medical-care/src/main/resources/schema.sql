CREATE TABLE IF NOT EXISTS beneficiary(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    beneficiary_name VARCHAR(250),
    phone_number VARCHAR(11),
    birth_date DATE,
    inclusion_date TIMESTAMP,
    update_date TIMESTAMP
);

CREATE TABLE IF NOT EXISTS document(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    document_type VARCHAR(250),
    document_number VARCHAR(11),
    description VARCHAR(50),
    inclusion_date TIMESTAMP,
    update_date TIMESTAMP,
    beneficiary_id BIGINT,
    constraint FK_beneficiary_document foreign key(beneficiary_id) references beneficiary(id)
);