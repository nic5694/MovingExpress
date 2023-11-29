USE `MovingSystem-DB`;

CREATE TABLE IF NOT EXISTS Client (
    id INT AUTO_INCREMENT PRIMARY KEY,
    clientId VARCHAR(36) UNIQUE NOT NULL,
    firstName VARCHAR(255),
    lastName VARCHAR(255),
    email VARCHAR(255) UNIQUE,
    phoneNumber VARCHAR(15)
    );

CREATE TABLE IF NOT EXISTS Truck (
    id INT AUTO_INCREMENT PRIMARY KEY,
    vin VARCHAR(17) UNIQUE NOT NULL,
    capacity DOUBLE
    );

CREATE TABLE IF NOT EXISTS Shipment (
    id INT AUTO_INCREMENT PRIMARY KEY,
    shipmentId VARCHAR(36) UNIQUE NOT NULL,
    shipmentStatus VARCHAR(255),
    shipmentDepartureAddress VARCHAR(255),
    shipmentArrivalAddress VARCHAR(255),
    vin VARCHAR(17),
    clientId VARCHAR(36),
    FOREIGN KEY (vin) REFERENCES Truck(vin),
    FOREIGN KEY (clientId) REFERENCES Client(clientId)
    );

CREATE TABLE IF NOT EXISTS Inventory (
    id INT AUTO_INCREMENT PRIMARY KEY,
    inventoryId VARCHAR(36) UNIQUE NOT NULL,
    description VARCHAR(255),
    shipmentId VARCHAR(36),
    FOREIGN KEY (shipmentId) REFERENCES Shipment(shipmentId)
    );

CREATE TABLE IF NOT EXISTS Item (
    id INT AUTO_INCREMENT PRIMARY KEY,
    itemId VARCHAR(36) UNIQUE NOT NULL,
    type ENUM('ITEM', 'BOX','ITEMS','BOXES') NOT NULL,
    price DECIMAL(10, 2),
    picture BLOB,
    description VARCHAR(255),
    handlingInstructions VARCHAR(255),
    inventoryId VARCHAR(36),
    FOREIGN KEY (inventoryId) REFERENCES Inventory(inventoryId)
    );

CREATE TABLE IF NOT EXISTS Quote (
    id INT AUTO_INCREMENT PRIMARY KEY,
    quoteId VARCHAR(36) UNIQUE NOT NULL,
    pickUpLocation VARCHAR(50),
    dropOffDestination VARCHAR(50),
    clientEmail VARCHAR(255),
    FOREIGN KEY (clientEmail) REFERENCES Client(email)
    );

CREATE TABLE IF NOT EXISTS MovingCrew (
    id INT AUTO_INCREMENT PRIMARY KEY,
    movingCrewId VARCHAR(36) UNIQUE NOT NULL,
    type VARCHAR(255),
    shipmentId VARCHAR(36),
    FOREIGN KEY (shipmentId) REFERENCES Shipment(shipmentId)
    );

CREATE TABLE IF NOT EXISTS Driver (
    id INT AUTO_INCREMENT PRIMARY KEY,
    driverId VARCHAR(36) UNIQUE NOT NULL,
    firstName VARCHAR(255),
    lastName VARCHAR(255),
    email VARCHAR(255) UNIQUE,
    shipmentId VARCHAR(36),
    FOREIGN KEY (shipmentId) REFERENCES Shipment(shipmentId)
    );

CREATE TABLE IF NOT EXISTS ShipmentReviewer (
    id INT AUTO_INCREMENT PRIMARY KEY,
    shipmentReviewerId VARCHAR(36),
    firstName VARCHAR(255),
    lastName VARCHAR(255),
    email VARCHAR(255) UNIQUE,
    phoneNumber VARCHAR(15)
    );

CREATE TABLE IF NOT EXISTS Observer (
    id INT AUTO_INCREMENT PRIMARY KEY,
    observerId VARCHAR(36) UNIQUE NOT NULL,
    firstName VARCHAR(255),
    lastName VARCHAR(255),
    email VARCHAR(255) UNIQUE,
    shipmentId VARCHAR(36),
    FOREIGN KEY (shipmentId) REFERENCES Shipment(shipmentId)
    );

CREATE TABLE IF NOT EXISTS MovingEstimator (
    id INT AUTO_INCREMENT PRIMARY KEY,
    estimatorId VARCHAR(36) UNIQUE NOT NULL,
    firstName VARCHAR(255),
    lastName VARCHAR(255),
    email VARCHAR(255) UNIQUE,
    phoneNumber VARCHAR(15),
    shipmentId VARCHAR(36),
    FOREIGN KEY (shipmentId) REFERENCES Shipment(shipmentId)
    );
