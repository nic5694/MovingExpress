package com.example.backend.shipmentsubdomain.businesslayer;

import com.example.backend.shipmentsubdomain.datalayer.Address.Address;
import com.example.backend.shipmentsubdomain.datalayer.Address.AddressRepository;
import com.example.backend.shipmentsubdomain.datalayer.ContactMethod;
import com.example.backend.shipmentsubdomain.datalayer.Country;
import com.example.backend.shipmentsubdomain.datalayer.QuoteStatus;
import com.example.backend.shipmentsubdomain.datalayer.shipment.*;
import com.example.backend.shipmentsubdomain.datamapperlayer.shipment.AddressMapper;
import com.example.backend.shipmentsubdomain.datamapperlayer.shipment.QuoteResponseToShipmentMapper;
import com.example.backend.shipmentsubdomain.datamapperlayer.shipment.ShipmentRequestMapper;
import com.example.backend.shipmentsubdomain.datamapperlayer.shipment.ShipmentResponseMapper;
import com.example.backend.shipmentsubdomain.presentationlayer.QuoteResponseModel;
import com.example.backend.shipmentsubdomain.presentationlayer.shipment.ShipmentResponseModel;
import com.example.backend.util.EmailUtil;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.thymeleaf.TemplateEngine;

import java.time.LocalDate;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;


@SpringBootTest
class ShipmentServiceImplUnitTest {
    @Mock
    private TemplateEngine templateEngine;
    @Mock
    private ShipmentRepository shipmentRepository;
    @Mock
    private ShipmentRequestMapper shipmentRequestMapper;
    @Mock
    private ShipmentResponseMapper shipmentResponseMapper;
    @InjectMocks
    private ShipmentServiceImpl shipmentService;

    @Mock
    private QuoteResponseToShipmentMapper quoteResponseToShipmentMapper;
    @Mock
    private AddressMapper addressMapper;

    @Mock
    private AddressRepository addressRepository;

    @Mock
    private EmailUtil emailUtil;
    @Test
    void createShipment() {
        TruckIdentifier truckIdentifier = new TruckIdentifier();
        QuoteResponseModel quoteResponseModel = QuoteResponseModel.builder()
                .pickupCountry(Country.CA)
                .pickupBuildingType("House")
                .pickupCity("CityA")
                .pickupStreetAddress("123 Main St")
                .pickupPostalCode("12345")
                .destinationCountry(Country.USA)
                .destinationBuildingType("House")
                .pickupElevator(false)
                .destinationCity("CityB")
                .destinationStreetAddress("456 Oak St")
                .destinationElevator(false)
                .destinationPostalCode("54321")
                .pickupNumberOfRooms(3)
                .destinationNumberOfRooms(4)
                .firstName("John")
                .lastName("Doe")
                .emailAddress("user@gmail.com")
                .phoneNumber("1234567890")
                .expectedMovingDate(LocalDate.now())
                .contactMethod(ContactMethod.PHONE_NUMBER)
                .comment("basic comments")
                .quoteStatus(QuoteStatus.PENDING)
                .name("shipmentName")
                .build();
        ShipmentResponseModel shipmentResponseModel = ShipmentResponseModel.builder()
                .shipmentId("shipmentId")
                .pickupAddress(new Address()) // replace with actual Address
                .destinationAddress(new Address()) // replace with actual Address
                .userId("userId")
                .truckId(truckIdentifier.toString())
                .status(Status.QUOTED) // replace with actual Status
                .shipmentName("shipmentName")
                .weight(1000.0)
                .email("email@example.com")
                .approximateWeight(1500.0)
                .build();
        Address departureAddress = new Address(quoteResponseModel.getPickupStreetAddress(),
                quoteResponseModel.getPickupCity(),
                quoteResponseModel.getPickupCountry(),
                quoteResponseModel.getPickupPostalCode());
        Address destinationAddress = new Address(quoteResponseModel.getDestinationStreetAddress(),
                quoteResponseModel.getDestinationCity(),
                quoteResponseModel.getDestinationCountry(),
                quoteResponseModel.getDestinationPostalCode());
        Shipment shipment = new Shipment();
        shipment.setShipmentIdentifier(new ShipmentIdentifier());
        shipment.setPickupAddress(departureAddress);
        shipment.setDestinationAddress(destinationAddress);
        shipment.setUserId(shipmentResponseModel.getUserId());
        shipment.setTruckIdentifier(truckIdentifier);
        shipment.setStatus(shipmentResponseModel.getStatus());
        shipment.setName(shipmentResponseModel.getShipmentName());
        shipment.setApproximateWeight(shipmentResponseModel.getApproximateWeight());
        shipment.setWeight(shipmentResponseModel.getWeight());

        when(addressMapper.toAddress(quoteResponseModel.getPickupStreetAddress(),
                quoteResponseModel.getPickupCity(),
                quoteResponseModel.getPickupPostalCode(),
                quoteResponseModel.getPickupCountry())).thenReturn(departureAddress);
        when(addressRepository.save(departureAddress)).thenReturn(departureAddress);
        when(addressMapper.toAddress(quoteResponseModel.getDestinationStreetAddress(),
                quoteResponseModel.getDestinationCity(),
                quoteResponseModel.getDestinationPostalCode(),
                quoteResponseModel.getDestinationCountry())).thenReturn(destinationAddress);
        when(addressRepository.save(destinationAddress)).thenReturn(destinationAddress);

        doNothing().when(emailUtil).SslEmail(quoteResponseModel.getEmailAddress(), "Shipment Creation Confirmation", shipmentService.generateShipmentConfirmationEmailContentString(shipment.getShipmentIdentifier().getShipmentId()));
        when(quoteResponseToShipmentMapper.toShipment(quoteResponseModel, addressMapper)).thenReturn(shipment);
        shipment.setPickupAddress(departureAddress);
        shipment.setDestinationAddress(destinationAddress);
        shipment.setStatus(Status.QUOTED);
        shipment.setEmail(quoteResponseModel.getEmailAddress());
        when(shipmentRepository.save(shipment)).thenReturn(shipment);
        when(shipmentResponseMapper.entityToResponseModel(shipment)).thenReturn(shipmentResponseModel);
        shipmentService.createShipment(quoteResponseModel);
        Mockito.verify(addressMapper, Mockito.times(1)).toAddress(
                quoteResponseModel.getPickupStreetAddress(),
                quoteResponseModel.getPickupCity(),
                quoteResponseModel.getPickupPostalCode(),
                quoteResponseModel.getPickupCountry());
        Mockito.verify(addressRepository, Mockito.times(1)).save(departureAddress);
        Mockito.verify(addressMapper, Mockito.times(1)).toAddress(
                quoteResponseModel.getDestinationStreetAddress(),
                quoteResponseModel.getDestinationCity(),
                quoteResponseModel.getDestinationPostalCode(),
                quoteResponseModel.getDestinationCountry());
        Mockito.verify(addressRepository, Mockito.times(1)).save(destinationAddress);
        Mockito.verify(shipmentRepository, Mockito.times(1)).save(shipment);
        Mockito.verify(quoteResponseToShipmentMapper, Mockito.times(1)).toShipment(quoteResponseModel, addressMapper);
        Mockito.verify(shipmentResponseMapper, Mockito.times(1)).entityToResponseModel(shipment);
    }
}







