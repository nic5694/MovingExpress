package com.example.backend.inventorysubdomain.buisnesslayer;

import com.example.backend.inventorysubdomain.datalayer.Inventory;
import com.example.backend.inventorysubdomain.datalayer.InventoryRepository;
import com.example.backend.inventorysubdomain.datamapperlayer.InventoryRequestMapper;
import com.example.backend.inventorysubdomain.datamapperlayer.InventoryResponseMapper;
import com.example.backend.inventorysubdomain.presentationlayer.InventoryRequestModel;
import com.example.backend.inventorysubdomain.presentationlayer.InventoryResponseModel;
import com.example.backend.inventorysubdomain.utils.exceptions.NotFoundException;
import com.example.backend.shipmentsubdomain.datalayer.shipment.Shipment;
import com.example.backend.shipmentsubdomain.datalayer.shipment.ShipmentIdentifier;
import com.example.backend.shipmentsubdomain.datalayer.shipment.ShipmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService{
    private final ShipmentRepository shipmentRepository;
    private final InventoryRequestMapper inventoryRequestMapper;
    private final InventoryResponseMapper inventoryResponseMapper;
    private final InventoryRepository inventoryRepository;
    @Override
    public InventoryResponseModel addInventory(InventoryRequestModel inventoryRequestModel, String shipmentId) {
        Shipment shipment = shipmentRepository.findShipmentByShipmentIdentifier_ShipmentId(shipmentId);
        if (shipment != null) {
            Inventory request = inventoryRequestMapper.requestModelToEntity(inventoryRequestModel);
            request.setShipmentIdentifier(new ShipmentIdentifier(shipmentId));
            return inventoryResponseMapper.entityToResponseModel(inventoryRepository.save(request));
        } else {
            throw new NotFoundException("Shipmentid with the id: " + shipmentId + " does not exist.");
        }
    }
}
