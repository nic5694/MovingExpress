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
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;


@SpringBootTest
class ShipmentServiceImplUnitTest {
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

    //    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
    @Test
    void createShipment() {
        TruckIdentifier truckIdentifier = new TruckIdentifier();
        QuoteResponseModel quoteResponseModel = QuoteResponseModel.builder()
                .pickupCountry(Country.CA)
                .pickupBuildingType("House")
                .pickupCity("CityA")
                .pickupStreetAddress("123 Main St")
                .destinationCountry(Country.USA)
                .destinationBuildingType("House")
                .pickupElevator(false)
                .destinationCity("CityB")
                .destinationStreetAddress("456 Oak St")
                .destinationElevator(false)
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
                .departureAddress(new Address()) // replace with actual Address
                .arrivalAddress(new Address()) // replace with actual Address
                .userId("userId")
                .truckId(truckIdentifier.toString())
                .shipmentStatus(ShipmentStatus.QUOTED) // replace with actual ShipmentStatus
                .shipmentName("shipmentName")
                .weight(1000.0)
                .email("email@example.com")
                .approximateWeight(1500.0)
                .build();
        Address departureAddress = Address.builder()
                .streetAddress(quoteResponseModel.getPickupStreetAddress())
                .city(quoteResponseModel.getPickupCity())
                .country(quoteResponseModel.getPickupCountry())
                .build();
        Address destinationAddress = Address.builder()
                .streetAddress(quoteResponseModel.getDestinationStreetAddress())
                .city(quoteResponseModel.getDestinationCity())
                .country(quoteResponseModel.getDestinationCountry())
                .build();
        Shipment shipment = new Shipment();
        shipment.setShipmentIdentifier(new ShipmentIdentifier());
        shipment.setDepartureAddress(departureAddress);
        shipment.setArrivalAddress(destinationAddress);
        shipment.setUserId(shipmentResponseModel.getUserId());
        shipment.setTruckIdentifier(truckIdentifier);
        shipment.setShipmentStatus(shipmentResponseModel.getShipmentStatus());
        shipment.setName(shipmentResponseModel.getShipmentName());
        shipment.setApproximateWeight(shipmentResponseModel.getApproximateWeight());
        shipment.setWeight(shipmentResponseModel.getWeight());

        Mockito.when(addressMapper.toAddress(quoteResponseModel.getPickupStreetAddress(),quoteResponseModel.getPickupCity(), quoteResponseModel.getPickupCountry())).thenReturn(departureAddress);
        Mockito.when(addressRepository.save(departureAddress)).thenReturn(departureAddress);
        Mockito.when(addressMapper.toAddress(quoteResponseModel.getDestinationStreetAddress(), quoteResponseModel.getDestinationCity(), quoteResponseModel.getDestinationCountry())).thenReturn(destinationAddress);
        Mockito.when(addressRepository.save(destinationAddress)).thenReturn(destinationAddress);
        Mockito.when(quoteResponseToShipmentMapper.toShipment(quoteResponseModel, addressMapper)).thenReturn(shipment);
        shipment.setDepartureAddress(departureAddress);
        shipment.setArrivalAddress(destinationAddress);
        shipment.setShipmentStatus(ShipmentStatus.QUOTED);
        shipment.setEmail(quoteResponseModel.getEmailAddress());
        Mockito.when(shipmentRepository.save(shipment)).thenReturn(shipment);
        Mockito.when(shipmentResponseMapper.entityToResponseModel(shipment)).thenReturn(shipmentResponseModel);
        shipmentService.createShipment(quoteResponseModel);
        Mockito.verify(addressMapper, Mockito.times(1)).toAddress(quoteResponseModel.getPickupStreetAddress(),quoteResponseModel.getPickupCity(), quoteResponseModel.getPickupCountry());
        Mockito.verify(addressRepository, Mockito.times(1)).save(departureAddress);
        Mockito.verify(addressMapper, Mockito.times(1)).toAddress(quoteResponseModel.getDestinationStreetAddress(), quoteResponseModel.getDestinationCity(), quoteResponseModel.getDestinationCountry());
        Mockito.verify(addressRepository, Mockito.times(1)).save(destinationAddress);
        Mockito.verify(shipmentRepository, Mockito.times(1)).save(shipment);
        Mockito.verify(shipmentRequestMapper, Mockito.times(1)).quoteRequestToShipmentMapper(Mockito.any());
        Mockito.verify(shipmentResponseMapper, Mockito.times(1)).entityToResponseModel(Mockito.any());
    }

//        @Test
//        void createShipment() {
//            // Arrange
//            QuoteResponseModel quoteResponseModel = Mockito.mock(QuoteResponseModel.class);
//            Address address = Mockito.mock(Address.class);
//            Shipment shipment = new Shipment();
//            ShipmentResponseModel shipmentResponseModel = Mockito.mock(ShipmentResponseModel.class);
//
//            when(addressMapper.toAddress(any(), any(), any())).thenReturn(address);
//            when(addressRepository.save(any())).thenReturn(address);
//            when(quoteResponseToShipmentMapper.toShipment(any(), any())).thenReturn(shipment); // Return the non-null Shipment object
//            when(shipmentRepository.save(any())).thenReturn(shipment);
//            when(shipmentResponseMapper.entityToResponseModel(any())).thenReturn(shipmentResponseModel);
//
//            // Act
//            ShipmentResponseModel result = shipmentService.createShipment(quoteResponseModel);
//
//            // Assert
//            assertEquals(shipmentResponseModel, result);
//        }
}






