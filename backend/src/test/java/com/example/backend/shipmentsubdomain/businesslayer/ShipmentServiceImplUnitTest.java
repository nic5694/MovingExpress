package com.example.backend.shipmentsubdomain.businesslayer;

import com.example.backend.shipmentsubdomain.datalayer.Address.Address;
import com.example.backend.shipmentsubdomain.datalayer.shipment.Shipment;
import com.example.backend.shipmentsubdomain.datalayer.shipment.ShipmentRepository;
import com.example.backend.shipmentsubdomain.datalayer.shipment.ShipmentStatus;
import com.example.backend.shipmentsubdomain.datamapperlayer.shipment.ShipmentResponseMapper;
import com.example.backend.shipmentsubdomain.presentationlayer.shipment.ShipmentResponseModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ShipmentServiceImplUnitTest {

//    @Mock
//    private ShipmentRepository shipmentRepository;
//
//    @Mock
//    private ShipmentResponseMapper shipmentResponseMapper;
//
//    @InjectMocks
//    private ShipmentServiceImpl shipmentService;
//
//    private Shipment sampleShipment;
//    private ShipmentResponseModel sampleShipmentResponseModel;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//
//        Address departureAddress = new Address("DepartureCity", "123 Departure St", "DepartureCountry", "12345");
//        Address arrivalAddress = new Address("ArrivalCity", "456 Arrival St", "ArrivalCountry", "67890");
//
//        // Sample shipment entity setup
//        sampleShipment = new Shipment();
//        sampleShipment.setId(1);
//        sampleShipment.setShipmentStatus(ShipmentStatus.QUOTED);
//        sampleShipment.setName("Test Shipment");
//        sampleShipment.setEmail("test@example.com");
//        sampleShipment.setDepartureAddress(departureAddress);
//        sampleShipment.setArrivalAddress(arrivalAddress);
//        sampleShipment.setExpectedMovingDate(LocalDate.now());
//
//        // Sample shipment response model setup
//        sampleShipmentResponseModel = ShipmentResponseModel.builder()
//                .shipmentId("1")
//                .departureAddress(departureAddress)
//                .arrivalAddress(arrivalAddress)
//                .userId("testUser")
//                .truckId("truck123")
//                .shipmentStatus(sampleShipment.getShipmentStatus())
//                .shipmentName(sampleShipment.getName())
//                .shipmentWeight(1000.0)
//                .email(sampleShipment.getEmail())
//                .build();
//
//        when(shipmentResponseMapper.entityToResponseModel(any(Shipment.class)))
//                .thenReturn(sampleShipmentResponseModel);
//    }
//
//    @Test
//    void getAllShipments_WhenUserIdPresent_ShouldReturnShipments() {
//        when(shipmentRepository.findShipmentByUserId(any(String.class)))
//                .thenReturn(Collections.singletonList(sampleShipment));
//
//        List<ShipmentResponseModel> result = shipmentService.getAllShipments(Optional.of("userId"), Optional.empty());
//
//        assertEquals(Collections.singletonList(sampleShipmentResponseModel), result);
//        verify(shipmentRepository).findShipmentByUserId(any(String.class));
//        verify(shipmentResponseMapper).entityToResponseModel(any(Shipment.class));
//    }
//
//    @Test
//    void getAllShipments_WhenEmailPresent_ShouldReturnShipments() {
//        when(shipmentRepository.findShipmentByEmail(any(String.class)))
//                .thenReturn(Collections.singletonList(sampleShipment));
//
//        List<ShipmentResponseModel> result = shipmentService.getAllShipments(Optional.empty(), Optional.of("email@example.com"));
//
//        assertEquals(Collections.singletonList(sampleShipmentResponseModel), result);
//        verify(shipmentRepository).findShipmentByEmail(any(String.class));
//        verify(shipmentResponseMapper).entityToResponseModel(any(Shipment.class));
//    }
//
//    @Test
//    void getAllShipments_WhenNoFilter_ShouldReturnAllShipments() {
//        when(shipmentRepository.findAll())
//                .thenReturn(Collections.singletonList(sampleShipment));
//
//        List<ShipmentResponseModel> result = shipmentService.getAllShipments(Optional.empty(), Optional.empty());
//
//        assertEquals(Collections.singletonList(sampleShipmentResponseModel), result);
//        verify(shipmentRepository).findAll();
//        verify(shipmentResponseMapper).entityToResponseModel(any(Shipment.class));
//    }
}
