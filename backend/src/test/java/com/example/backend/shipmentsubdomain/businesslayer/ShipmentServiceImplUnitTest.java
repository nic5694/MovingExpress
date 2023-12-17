package com.example.backend.shipmentsubdomain.businesslayer;

import com.example.backend.shipmentsubdomain.datalayer.shipment.ShipmentRepository;
import com.example.backend.shipmentsubdomain.datamapperlayer.shipment.ShipmentResponseMapper;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class ShipmentServiceImplUnitTest {
    @Mock
    private ShipmentRepository shipmentRepository;
    @Mock
    private ShipmentResponseMapper shipmentResponseMapper;
    private ShipmentServiceImpl shipmentService;

}
