package com.example.backend.inventorysubdomain.presentationlayer;

import com.example.backend.inventorysubdomain.buisnesslayer.InventoryService;
import com.example.backend.inventorysubdomain.utils.exceptions.InvalidInputException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

@RestController()
@RequestMapping("api/v1/movingexpress/shipments/{shipmentId}/inventories")
@CrossOrigin(origins = "http://localhost3000", allowCredentials = "true")
@RequiredArgsConstructor
public class InventoryController {
    private final InventoryService inventoryService;
    @PostMapping()
    public ResponseEntity<InventoryResponseModel> addInventory(@RequestBody InventoryRequestModel inventoryRequestModel, @PathVariable String shipmentId){
        if(!shipmentId.isEmpty() && !shipmentId.isBlank()) {
            return ResponseEntity.status(CREATED).body(inventoryService.addInventory(inventoryRequestModel, shipmentId));
        }else {
            throw new InvalidInputException("Request to addInventory to shipmentId: " + shipmentId + " is invalid");
        }
    }
}
