USE `movingsystem-db`;

CREATE TABLE IF NOT EXISTS clients (
    id INT AUTO_INCREMENT PRIMARY KEY,
    clientId VARCHAR(36) UNIQUE NOT NULL,
    firstName VARCHAR(255),
    lastName VARCHAR(255),
    email VARCHAR(255) UNIQUE NOT NULL,
    phoneNumber VARCHAR(15)
    );

CREATE TABLE IF NOT EXISTS trucks (
    id INT AUTO_INCREMENT PRIMARY KEY,
    vin VARCHAR(17) UNIQUE NOT NULL,
    capacity DOUBLE
    );
CREATE TABLE IF NOT EXISTS addresses (
                                       id INT AUTO_INCREMENT PRIMARY KEY,
                                       addressId VARCHAR(36) UNIQUE NOT NULL,
    streetAddress VARCHAR(255),
    city VARCHAR(255),
    province VARCHAR(255),
    country VARCHAR(255),
    postalCode VARCHAR(10)
    );


CREATE TABLE IF NOT EXISTS shipments (
    id INT AUTO_INCREMENT PRIMARY KEY,
    shipmentId VARCHAR(36) UNIQUE NOT NULL,
    shipmentStatus VARCHAR(255),
    departureAddressId VARCHAR(36),
    arrivalAddressId VARCHAR(36),
    vin VARCHAR(17) UNIQUE,
    clientId VARCHAR(36),
    FOREIGN KEY (departureAddressId) REFERENCES addresses(addressId),
    FOREIGN KEY (arrivalAddressId) REFERENCES addresses(addressId),
    FOREIGN KEY (vin) REFERENCES trucks(vin),
    FOREIGN KEY (clientId) REFERENCES clients(clientId)
    );

CREATE TABLE IF NOT EXISTS inventories (
    id INT AUTO_INCREMENT PRIMARY KEY,
    inventoryId VARCHAR(36) UNIQUE NOT NULL,
    description VARCHAR(255),
    shipmentId VARCHAR(36),
    FOREIGN KEY (shipmentId) REFERENCES shipments(shipmentId)
    );

CREATE TABLE IF NOT EXISTS items (
    id INT AUTO_INCREMENT PRIMARY KEY,
    itemId VARCHAR(36) UNIQUE NOT NULL,
    itemName VARCHAR(255) NOT NULL,
    type ENUM('ITEM', 'BOX') NOT NULL,
    price DECIMAL(10, 2),
    description VARCHAR(255),
    weight DOUBLE NULL,
    handlingInstructions VARCHAR(255),
    inventoryId VARCHAR(36),
    FOREIGN KEY (inventoryId) REFERENCES inventories(inventoryId)
    );

CREATE TABLE IF NOT EXISTS moving_estimators (
    id INT AUTO_INCREMENT PRIMARY KEY,
    estimatorId VARCHAR(36) UNIQUE NOT NULL,
    firstName VARCHAR(255),
    lastName VARCHAR(255),
    email VARCHAR(255) UNIQUE,
    phoneNumber VARCHAR(15),
    shipmentId VARCHAR(36),
    FOREIGN KEY (shipmentId) REFERENCES shipments(shipmentId)
    );

CREATE TABLE IF NOT EXISTS quotes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    quote_id VARCHAR(36) UNIQUE NOT NULL,
    pickUp_street_address VARCHAR(255),
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
    quoteStatus VARCHAR(10),
    shipmentName VARCHAR(255)
--     FOREIGN KEY (emailAddress) REFERENCES clients(email),
--     FOREIGN KEY (movingEstimatorId) REFERENCES MovingEstimator(estimatorId)
    );


CREATE TABLE IF NOT EXISTS moving_crews (
    id INT AUTO_INCREMENT PRIMARY KEY,
    movingCrewId VARCHAR(36) UNIQUE NOT NULL,
    type VARCHAR(255),
    shipmentId VARCHAR(36),
    FOREIGN KEY (shipmentId) REFERENCES shipments(shipmentId)
    );

CREATE TABLE IF NOT EXISTS drivers (
    id INT AUTO_INCREMENT PRIMARY KEY,
    driverId VARCHAR(36) UNIQUE NOT NULL,
    firstName VARCHAR(255),
    lastName VARCHAR(255),
    phoneNumber VARCHAR(15),
    email VARCHAR(255) UNIQUE NOT NULL,
    shipmentId VARCHAR(36),
    FOREIGN KEY (shipmentId) REFERENCES shipments(shipmentId)
    );

CREATE TABLE IF NOT EXISTS shipment_reviewers (
    id INT AUTO_INCREMENT PRIMARY KEY,
    shipmentReviewerId VARCHAR(36),
    firstName VARCHAR(255),
    lastName VARCHAR(255),
    email VARCHAR(255) UNIQUE NOT NULL,
    phoneNumber VARCHAR(15)
    );

CREATE TABLE IF NOT EXISTS observers (
    id INT AUTO_INCREMENT PRIMARY KEY,
    observerId VARCHAR(36) UNIQUE NOT NULL,
    firstName VARCHAR(255),
    lastName VARCHAR(255),
    email VARCHAR(255) UNIQUE NOT NULL,
    shipmentId VARCHAR(36),
    FOREIGN KEY (shipmentId) REFERENCES shipments(shipmentId)
    );
