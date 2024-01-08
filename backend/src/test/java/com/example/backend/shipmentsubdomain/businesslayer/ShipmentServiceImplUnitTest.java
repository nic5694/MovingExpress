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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.thymeleaf.TemplateEngine;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


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

    private Shipment mockShipment;

    private ShipmentResponseModel mockShipmentResponse;



    @BeforeEach
    void setUp() {

        Address pickupAddress = new Address();
        Address destinationAddress = new Address();
        TruckIdentifier truckIdentifier = new TruckIdentifier();
        Status status = Status.QUOTED;

        mockShipment = new Shipment(
                "user123",
                truckIdentifier,
                status,
                LocalDate.of(2023, 1, 1),
                LocalDate.of(2023, 1, 5),
                2000.0,
                "Sample Shipment",
                pickupAddress,
                destinationAddress,
                "email@example.com",
                "1234567890"
        );

        mockShipmentResponse = ShipmentResponseModel.builder()
                .shipmentId("shipment123")
                .pickupAddress(pickupAddress)
                .destinationAddress(destinationAddress)
                .userId("user123")
                .truckId("truck123")
                .status(status)
                .shipmentName("Sample Shipment")
                .approximateWeight(2000.0)
                .weight(1950.0)
                .email("email@example.com")
                .phoneNumber("1234567890")
                .build();
    }
    @Test
    void getAllShipmentsWithUserId() {
        String userId = "user123";
        when(shipmentRepository.findShipmentByUserId(userId)).thenReturn(Collections.singletonList(mockShipment));
        when(shipmentResponseMapper.entityToResponseModel(mockShipment)).thenReturn(mockShipmentResponse);

        List<ShipmentResponseModel> result = shipmentService.getAllShipments(Optional.of(userId), Optional.empty());

        verify(shipmentRepository).findShipmentByUserId(userId);
        verify(shipmentResponseMapper).entityToResponseModel(mockShipment);
        assertEquals(1, result.size());
        assertEquals(mockShipmentResponse, result.get(0));
    }

    @Test
    void getAllShipmentsWithEmail() {
        String email = "user@example.com";
        when(shipmentRepository.findShipmentByEmail(email)).thenReturn(Collections.singletonList(mockShipment));
        when(shipmentResponseMapper.entityToResponseModel(mockShipment)).thenReturn(mockShipmentResponse);

        List<ShipmentResponseModel> result = shipmentService.getAllShipments(Optional.empty(), Optional.of(email));

        verify(shipmentRepository).findShipmentByEmail(email);
        verify(shipmentResponseMapper).entityToResponseModel(mockShipment);
        assertEquals(1, result.size());
        assertEquals(mockShipmentResponse, result.get(0));
    }

    @Test
    void getAllShipmentsWhenNeitherUserIdNorEmailIsProvided() {
        when(shipmentRepository.findAll()).thenReturn(Collections.singletonList(mockShipment));
        when(shipmentResponseMapper.entityToResponseModel(mockShipment)).thenReturn(mockShipmentResponse);

        List<ShipmentResponseModel> result = shipmentService.getAllShipments(Optional.empty(), Optional.empty());

        verify(shipmentRepository).findAll();
        verify(shipmentResponseMapper).entityToResponseModel(mockShipment);
        assertEquals(1, result.size());
        assertEquals(mockShipmentResponse, result.get(0));
    }

    @Test
    void getShipmentWithValidShipmentId_ShouldSucceed(){
        Shipment existingShipment=buildShipment();
        ShipmentResponseModel shipmentResponseModel=buildShipmentResponseModel();

        when(shipmentRepository.findShipmentByShipmentIdentifier_ShipmentId(anyString())).thenReturn(existingShipment);
        when(shipmentResponseMapper.entityToResponseModel(existingShipment)).thenReturn(shipmentResponseModel);

        ShipmentResponseModel result=shipmentService.getShipment("c760a2f1-1981-4efb-83d1-3618f363ed27");

        assertEquals(result.getUserId(), existingShipment.getUserId());
        assertEquals(result.getStatus(), existingShipment.getStatus());
        assertEquals(result.getExpectedMovingDate(), existingShipment.getExpectedMovingDate());
        assertEquals(result.getActualMovingDate(), existingShipment.getActualMovingDate());
        assertEquals(result.getApproximateWeight(), existingShipment.getApproximateWeight());
        assertEquals(result.getShipmentName(), existingShipment.getName());
        assertEquals(result.getPickupAddress(), existingShipment.getPickupAddress());
        assertEquals(result.getDestinationAddress(), existingShipment.getDestinationAddress());
        assertEquals(result.getEmail(), existingShipment.getEmail());
        assertEquals(result.getPhoneNumber(), existingShipment.getPhoneNumber());
        assertEquals(result.getWeight(), existingShipment.getWeight());
    }


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
        verify(addressMapper, Mockito.times(1)).toAddress(
                quoteResponseModel.getPickupStreetAddress(),
                quoteResponseModel.getPickupCity(),
                quoteResponseModel.getPickupPostalCode(),
                quoteResponseModel.getPickupCountry());
        verify(addressRepository, Mockito.times(1)).save(departureAddress);
        verify(addressMapper, Mockito.times(1)).toAddress(
                quoteResponseModel.getDestinationStreetAddress(),
                quoteResponseModel.getDestinationCity(),
                quoteResponseModel.getDestinationPostalCode(),
                quoteResponseModel.getDestinationCountry());
        verify(addressRepository, Mockito.times(1)).save(destinationAddress);
        verify(shipmentRepository, Mockito.times(1)).save(shipment);
        verify(quoteResponseToShipmentMapper, Mockito.times(1)).toShipment(quoteResponseModel, addressMapper);
        verify(shipmentResponseMapper, Mockito.times(1)).entityToResponseModel(shipment);
    }

    private Shipment buildShipment() {
        return Shipment.builder()
                .userId("exampleUserId")
                .status(Status.QUOTED)
                .shipmentIdentifier(new ShipmentIdentifier())
                .expectedMovingDate(LocalDate.of(2023, 1, 1))
                .actualMovingDate(LocalDate.of(2023, 1, 5))
                .approximateWeight(100.0)
                .name("Example Shipment")
                .pickupAddress(Address.builder()
                        .city("PickupCity")
                        .streetAddress("PickupStreet")
                        .country(Country.CA)
                        .postalCode("PickupPostalCode")
                        .build())
                .destinationAddress(Address.builder()
                        .city("DestinationCity")
                        .streetAddress("DestinationStreet")
                        .country(Country.USA)
                        .postalCode("DestinationPostalCode")
                        .build())
                .email("example@example.com")
                .phoneNumber("1234567890")
                .build();
    }

    private ShipmentResponseModel buildShipmentResponseModel() {
        return ShipmentResponseModel.builder()
                .userId("exampleUserId")
                .status(Status.QUOTED)
                .shipmentId("c760a2f1-1981-4efb-83d1-3618f363ed27")
                .expectedMovingDate(LocalDate.of(2023, 1, 1))
                .actualMovingDate(LocalDate.of(2023, 1, 5))
                .pickupAddress(Address.builder()
                        .city("PickupCity")
                        .streetAddress("PickupStreet")
                        .country(Country.CA)
                        .postalCode("PickupPostalCode")
                        .build())
                .destinationAddress(Address.builder()
                        .city("DestinationCity")
                        .streetAddress("DestinationStreet")
                        .country(Country.USA)
                        .postalCode("DestinationPostalCode")
                        .build())
                .email("example@example.com")
                .phoneNumber("1234567890")
                .shipmentName("Example Shipment")
                .approximateWeight(100.0)
                .weight(0.0)
                .truckId("db6c3aef-f3f2-4a75-9cfb-67882c1e11f7\n")
                .build();
    }
}







