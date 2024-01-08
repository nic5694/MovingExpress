package com.example.backend.inventorysubdomain.buisnesslayer;

import com.example.backend.inventorysubdomain.datalayer.Inventory;
import com.example.backend.inventorysubdomain.datalayer.InventoryRepository;
import com.example.backend.inventorysubdomain.datamapperlayer.InventoryRequestMapper;
import com.example.backend.inventorysubdomain.datamapperlayer.InventoryResponseMapper;
import com.example.backend.inventorysubdomain.presentationlayer.InventoryRequestModel;
import com.example.backend.inventorysubdomain.presentationlayer.InventoryResponseModel;
import com.example.backend.shipmentsubdomain.datalayer.Address.Address;
import com.example.backend.shipmentsubdomain.datalayer.shipment.Shipment;
import com.example.backend.shipmentsubdomain.datalayer.shipment.ShipmentRepository;
import com.example.backend.shipmentsubdomain.datalayer.shipment.Status;
import com.example.backend.shipmentsubdomain.datalayer.shipment.TruckIdentifier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class InventoryServiceImplUnitTest {
    @Mock
    private ShipmentRepository shipmentRepository;
    @Mock
    private InventoryResponseMapper inventoryResponseMapper;
    @Mock
    private InventoryRequestMapper inventoryRequestMapper;
    @Mock
    private InventoryRepository inventoryRepository;
    @InjectMocks
    private InventoryServiceImpl inventoryService;
    private String dummyShipmentId;

    private String dummyDescription = "Kitchen inventory holding all the kitchen items.";
    private String dummyInventoryName = "Kitchen";

    private Inventory sampleInventory;
    private InventoryRequestModel requestInventory;
    private InventoryResponseModel responseInventory;
    private Shipment mockShipment;
    @BeforeEach
    void setUp(){
        Address pickupAddress = new Address();
        Address destinationAddress = new Address();
        TruckIdentifier truckIdentifier = new TruckIdentifier();
        Status status = Status.QUOTED;
        mockShipment =  new Shipment(
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
       // dummyShipmentId = mockShipment.getShipmentIdentifier().getShipmentId();
        sampleInventory =  new Inventory(mockShipment.getShipmentIdentifier(), dummyInventoryName, dummyDescription);
        requestInventory = InventoryRequestModel.builder()
                .shipmentId(sampleInventory.getShipmentIdentifier().getShipmentId())
                .description(sampleInventory.getDescription())
                .name(sampleInventory.getName())
                .build();
        responseInventory = InventoryResponseModel.builder()
                .inventoryId(sampleInventory.getInventoryIdentifier().getInventoryId())
                .description(sampleInventory.getDescription())
                .shipmentId(sampleInventory.getShipmentIdentifier().getShipmentId())
                .name(sampleInventory.getName())
                .build();

    }

    @Test
    void addQuoteWithValidShipmentIdAndBody_ShouldSucceed(){
        //Arrange
        Mockito.when(shipmentRepository.findShipmentByShipmentIdentifier_ShipmentId(mockShipment.getShipmentIdentifier().getShipmentId())).thenReturn(mockShipment);
        Mockito.when(inventoryRequestMapper.requestModelToEntity(requestInventory)).thenReturn(sampleInventory);
        Mockito.when(inventoryResponseMapper.entityToResponseModel(sampleInventory)).thenReturn(responseInventory);
        Mockito.when(inventoryRepository.save(sampleInventory)).thenReturn(sampleInventory);

        //Act
        InventoryResponseModel inventoryResponseModel = inventoryService.addInventory(requestInventory, mockShipment.getShipmentIdentifier().getShipmentId());

        //Assert
        assertThat(inventoryResponseModel.getInventoryId()).isEqualTo(responseInventory.getInventoryId());
        assertThat(inventoryResponseModel.getShipmentId()).isEqualTo(responseInventory.getShipmentId());
        assertThat(inventoryResponseModel.getName()).isEqualTo(responseInventory.getName());
        assertThat(inventoryResponseModel.getDescription()).isEqualTo(responseInventory.getDescription());
        Mockito.verify(inventoryRepository, Mockito.times(1)).save(sampleInventory);
        Mockito.verify(shipmentRepository, Mockito.times(1)).findShipmentByShipmentIdentifier_ShipmentId(mockShipment.getShipmentIdentifier().getShipmentId());
        Mockito.verify(inventoryRequestMapper, Mockito.times(1)).requestModelToEntity(requestInventory);
        Mockito.verify(inventoryResponseMapper, Mockito.times(1)).entityToResponseModel(sampleInventory);
    }
}