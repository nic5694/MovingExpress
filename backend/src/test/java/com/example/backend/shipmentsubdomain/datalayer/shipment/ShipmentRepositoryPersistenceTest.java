package com.example.backend.shipmentsubdomain.datalayer.shipment;

import com.example.backend.shipmentsubdomain.datalayer.Address.Address;
import com.example.backend.shipmentsubdomain.datalayer.Address.AddressRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class ShipmentRepositoryPersistenceTest {

    @Autowired
    private ShipmentRepository shipmentRepository;

    @Autowired
    private AddressRepository addressRepository;

    @BeforeEach
    public void setUp() {
        Address departureAddress1 = new Address("123 Main St", "CityA", "USA", "12345");
        Address arrivalAddress1 = new Address("456 Oak St", "CityB", "USA", "54321");
        addressRepository.save(departureAddress1);
        addressRepository.save(arrivalAddress1);

        Address departureAddress2 = new Address("789 Elm St", "CityC", "USA", "67890");
        Address arrivalAddress2 = new Address("321 Pine St", "CityD", "USA", "98765");
        addressRepository.save(departureAddress2);
        addressRepository.save(arrivalAddress2);

        Shipment shipment1 = new Shipment("user123", null, ShipmentStatus.QUOTED, LocalDate.now(), null, 500.0, "Household", departureAddress1, arrivalAddress1, "user123@example.com");
        Shipment shipment2 = new Shipment("user456", null, ShipmentStatus.DELIVERED, LocalDate.now().minusDays(10), LocalDate.now().minusDays(5), 700.0, "Office Move", departureAddress2, arrivalAddress2, "user456@example.com");
        shipmentRepository.save(shipment1);
        shipmentRepository.save(shipment2);
    }

    @Test
    public void whenFindByUserId_thenCorrectShipmentsAreReturned() {
        // Act
        List<Shipment> foundShipments = shipmentRepository.findShipmentByUserId("user123");

        // Assert
        assertThat(foundShipments).hasSize(1);
        assertThat(foundShipments.get(0).getEmail()).isEqualTo("user123@example.com");
        assertThat(foundShipments.get(0).getShipmentStatus()).isEqualTo(ShipmentStatus.QUOTED);
    }

    @Test
    public void whenFindByEmail_thenCorrectShipmentsAreReturned() {
        // Act
        List<Shipment> foundShipments = shipmentRepository.findShipmentByEmail("user456@example.com");

        // Assert
        assertThat(foundShipments).hasSize(1);
        assertThat(foundShipments.get(0).getUserId()).isEqualTo("user456");
        assertThat(foundShipments.get(0).getShipmentStatus()).isEqualTo(ShipmentStatus.DELIVERED);
    }

    @Test
    public void whenFindAll_thenAllShipmentsAreReturned() {
        // Act
        List<Shipment> allShipments = shipmentRepository.findAll();

        // Assert
        assertThat(allShipments).hasSize(2);
        assertThat(allShipments).extracting("name").containsExactlyInAnyOrder("Household", "Office Move");
    }
}
