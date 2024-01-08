package com.example.backend.shipmentsubdomain.datalayer.shipment;

import com.example.backend.shipmentsubdomain.datalayer.Address.Address;
import com.example.backend.shipmentsubdomain.datalayer.Address.AddressRepository;
import com.example.backend.shipmentsubdomain.datalayer.Country;
import com.example.backend.shipmentsubdomain.presentationlayer.QuoteResponseModel;
import com.example.backend.util.exceptions.QuoteNotFoundException;
import com.example.backend.util.exceptions.ShipmentNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ShipmentRepositoryPersistenceTest {

    @Autowired
    private ShipmentRepository shipmentRepository;

    @Autowired
    private AddressRepository addressRepository;

    private String shipmentIdForShipment1 ;
    private Shipment shipment1;

    @BeforeEach
    public void setUp() {
        Address departureAddress1 = new Address("123 Main St", "CityA", Country.USA, "12345");
        Address arrivalAddress1 = new Address("456 Oak St", "CityB", Country.USA, "54321");
        addressRepository.save(departureAddress1);
        addressRepository.save(arrivalAddress1);

        Address departureAddress2 = new Address("789 Elm St", "CityC", Country.USA, "67890");
        Address arrivalAddress2 = new Address("321 Pine St", "CityD", Country.USA, "98765");
        addressRepository.save(departureAddress2);
        addressRepository.save(arrivalAddress2);

        shipment1 = new Shipment("user123", null, Status.QUOTED, LocalDate.now(), null, 500.0, "Household", departureAddress1, arrivalAddress1, "user123@example.com", "1234567890");
        Shipment shipment2 = new Shipment("user456", null, Status.DELIVERED, LocalDate.now().minusDays(10), LocalDate.now().minusDays(5), 700.0, "Office Move", departureAddress2, arrivalAddress2, "user456@example.com", "0987654321");
        shipmentRepository.save(shipment1);
        shipmentIdForShipment1 = shipment1.getShipmentIdentifier().getShipmentId();
        shipmentRepository.save(shipment2);
    }

    @Test
    public void whenFindByUserId_thenCorrectShipmentsAreReturned() {
        // Act
        List<Shipment> foundShipments = shipmentRepository.findShipmentByUserId("user123");

        // Assert
        assertThat(foundShipments).hasSize(1);
        assertThat(foundShipments.get(0).getEmail()).isEqualTo("user123@example.com");
        assertThat(foundShipments.get(0).getStatus()).isEqualTo(Status.QUOTED);
    }

    @Test
    public void whenFindByEmail_thenCorrectShipmentsAreReturned() {
        // Act
        List<Shipment> foundShipments = shipmentRepository.findShipmentByEmail("user456@example.com");

        // Assert
        assertThat(foundShipments).hasSize(1);
        assertThat(foundShipments.get(0).getUserId()).isEqualTo("user456");
        assertThat(foundShipments.get(0).getStatus()).isEqualTo(Status.DELIVERED);
    }

    @Test
    public void whenFindAll_thenAllShipmentsAreReturned() {
        // Act
        List<Shipment> allShipments = shipmentRepository.findAll();

        // Assert
        assertThat(allShipments).hasSize(2);
        assertThat(allShipments).extracting("name").containsExactlyInAnyOrder("Household", "Office Move");
    }

    @Test
    public void whenFindShipmentByValidShipmentId_thenReturnShipment(){
        shipmentRepository.deleteAll();
        Shipment newShipment=shipmentRepository.save(buildShipment());

        Shipment existingShipment=shipmentRepository.findShipmentByShipmentIdentifier_ShipmentId(newShipment.getShipmentIdentifier().getShipmentId());

        assertNotNull(existingShipment);
        assertEquals(existingShipment.getUserId(), "exampleUserId", "User IDs should match");
        assertEquals(existingShipment.getStatus(), Status.QUOTED, "Status should match");
        assertEquals(existingShipment.getExpectedMovingDate(), LocalDate.of(2023, 1, 1), "Expected moving dates should match");
        assertEquals(existingShipment.getActualMovingDate(), LocalDate.of(2023, 1, 5), "Actual moving dates should match");
        assertEquals(existingShipment.getApproximateWeight(), 100.0, 0.001, "Approximate weights should match");
        assertEquals(existingShipment.getName(), "Example Shipment", "Shipment names should match");
        assertEquals(existingShipment.getPickupAddress(), Address.builder()
                .id(newShipment.getPickupAddress().getId())
                .city("PickupCity")
                .streetAddress("PickupStreet")
                .country(Country.CA)
                .postalCode("PickupPostalCode")
                .build(), "Pickup addresses should match");
        assertEquals(existingShipment.getDestinationAddress(), Address.builder()
                .id(newShipment.getDestinationAddress().getId())
                .city("DestinationCity")
                .streetAddress("DestinationStreet")
                .country(Country.USA)
                .postalCode("DestinationPostalCode")
                .build(), "Destination addresses should match");
        assertEquals(existingShipment.getEmail(), "example@example.com", "Emails should match");
        assertEquals(existingShipment.getPhoneNumber(), "1234567890", "Phone numbers should match");
    }

    private Shipment buildShipment() {
        addressRepository.deleteAll();

        Address pickupAddress=Address.builder()
                .city("PickupCity")
                .streetAddress("PickupStreet")
                .country(Country.CA)
                .postalCode("PickupPostalCode")
                .build();

        Address destinationAddress=Address.builder()
                .city("DestinationCity")
                .streetAddress("DestinationStreet")
                .country(Country.USA)
                .postalCode("DestinationPostalCode")
                .build();

        addressRepository.saveAll(Arrays.asList(pickupAddress, destinationAddress));

        return Shipment.builder()
                .userId("exampleUserId")
                .status(Status.QUOTED)
                .shipmentIdentifier(new ShipmentIdentifier())
                .expectedMovingDate(LocalDate.of(2023, 1, 1))
                .actualMovingDate(LocalDate.of(2023, 1, 5))
                .approximateWeight(100.0)
                .name("Example Shipment")
                .pickupAddress(pickupAddress)
                .destinationAddress(destinationAddress)
                .email("example@example.com")
                .phoneNumber("1234567890")
                .build();
    }
}