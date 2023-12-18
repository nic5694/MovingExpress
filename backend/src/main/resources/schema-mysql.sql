USE `movingsystem-db`;

CREATE TABLE IF NOT EXISTS customers (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id VARCHAR(36) UNIQUE NOT NULL,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NULL,
    profile_picture_url VARCHAR(255) NULL,
    email VARCHAR(255) UNIQUE NULL,
    phone_number VARCHAR(15) NULL,
    street_address VARCHAR(255) NULL,
    city VARCHAR(255) NULL,
    country VARCHAR(255) NULL,
    postal_code VARCHAR(10) NULL
    );

CREATE TABLE IF NOT EXISTS trucks (
    id INT AUTO_INCREMENT PRIMARY KEY,
    vin VARCHAR(17) UNIQUE NOT NULL,
    capacity DOUBLE
    );
CREATE TABLE IF NOT EXISTS addresses (
                                       id INT AUTO_INCREMENT PRIMARY KEY,
                                       address_id VARCHAR(36) UNIQUE NOT NULL,
    street_address VARCHAR(255) NOT NULL,
    city VARCHAR(255) NOT NULL,
    country VARCHAR(255) NOT NULL,
    postal_code VARCHAR(10) NOT NULL
    );


CREATE TABLE IF NOT EXISTS shipments (
                                         id INT AUTO_INCREMENT PRIMARY KEY,
                                         shipment_id VARCHAR(36) UNIQUE,
    status VARCHAR(255),
    expected_moving_date Date,
    actual_moving_date Date NULL,
    name VARCHAR(255),
    approximate_weight DOUBLE NULL,
    weight DOUBLE NULL,
    pickup_address_id VARCHAR(36),
    destination_address_id VARCHAR(36),
    vin VARCHAR(17) NULL,
    user_id VARCHAR(36) NULL,
    email VARCHAR(255) NULL,
    phone_number VARCHAR(15) NULL,
    FOREIGN KEY (pickup_address_id) REFERENCES addresses(address_id),
    FOREIGN KEY (destination_address_id) REFERENCES addresses(address_id),
    FOREIGN KEY (vin) REFERENCES trucks(vin),
    FOREIGN KEY (user_id) REFERENCES customers(user_id)
    );


CREATE TABLE IF NOT EXISTS inventories (
    id INT AUTO_INCREMENT PRIMARY KEY,
    inventory_id VARCHAR(36) UNIQUE NOT NULL,
    description VARCHAR(255),
    shipment_id VARCHAR(36),
    FOREIGN KEY (shipment_id) REFERENCES shipments(shipment_id)
    );

CREATE TABLE IF NOT EXISTS items (
    id INT AUTO_INCREMENT PRIMARY KEY,
    item_id VARCHAR(36) UNIQUE NOT NULL,
    name VARCHAR(255) NOT NULL,
    type ENUM('ITEM', 'BOX') NOT NULL,
    price DECIMAL(10, 2),
    description VARCHAR(255),
    weight DOUBLE NULL,
    handling_instructions VARCHAR(255),
    inventory_id VARCHAR(36),
    FOREIGN KEY (inventory_id) REFERENCES inventories(inventory_id)
    );

CREATE TABLE IF NOT EXISTS moving_estimators (
    id INT AUTO_INCREMENT PRIMARY KEY,
    estimator_id VARCHAR(36) UNIQUE NOT NULL,
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    email VARCHAR(255) UNIQUE,
    phone_number VARCHAR(15),
    shipment_id VARCHAR(36),
    FOREIGN KEY (shipment_id) REFERENCES shipments(shipment_id)
    );

CREATE TABLE IF NOT EXISTS quotes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    quote_id VARCHAR(36) UNIQUE NOT NULL,
    pickup_street_address VARCHAR(255),
    pickup_city VARCHAR(255),
    pickup_country VARCHAR(255),
    pickup_postal_code VARCHAR(10),
    pickup_number_of_rooms INT,
    pickup_elevator BOOLEAN,
    pickup_building_type VARCHAR(255),
    destination_street_address VARCHAR(255),
    destination_city VARCHAR(255),
    destination_country VARCHAR(255),
    destination_postal_code VARCHAR(10),
    destination_number_of_rooms INT,
    destination_elevator BOOLEAN,
    destination_building_type VARCHAR(255),
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    email_address VARCHAR(255),
    phone_number VARCHAR(15),
    contact_method VARCHAR(30),
    expected_moving_date Date,
    initiation_date DATETIME,
    comment VARCHAR(255),
    quote_status VARCHAR(10),
    shipment_name VARCHAR(255)
--     FOREIGN KEY (emailAddress) REFERENCES clients(email),
--     FOREIGN KEY (movingEstimatorId) REFERENCES MovingEstimator(estimatorId)
    );


CREATE TABLE IF NOT EXISTS moving_crews (
    id INT AUTO_INCREMENT PRIMARY KEY,
    moving_crew_id VARCHAR(36) UNIQUE NOT NULL,
    type VARCHAR(255),
    shipment_id VARCHAR(36),
    FOREIGN KEY (shipment_id) REFERENCES shipments(shipment_id)
    );

CREATE TABLE IF NOT EXISTS drivers (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id VARCHAR(36) UNIQUE NOT NULL,
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    phone_number VARCHAR(15),
    email VARCHAR(255) UNIQUE NOT NULL,
    shipment_id VARCHAR(36),
    FOREIGN KEY (shipment_id) REFERENCES shipments(shipment_id)
    );

CREATE TABLE IF NOT EXISTS shipment_reviewers (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id VARCHAR(36),
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    email VARCHAR(255) UNIQUE NOT NULL,
    phone_number VARCHAR(15)
    );

CREATE TABLE IF NOT EXISTS observers (
    id INT AUTO_INCREMENT PRIMARY KEY,
    observer_id VARCHAR(36) UNIQUE NOT NULL,
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    email VARCHAR(255) UNIQUE NOT NULL,
    shipment_id VARCHAR(36),
    FOREIGN KEY (shipment_id) REFERENCES shipments(shipment_id)
    );
