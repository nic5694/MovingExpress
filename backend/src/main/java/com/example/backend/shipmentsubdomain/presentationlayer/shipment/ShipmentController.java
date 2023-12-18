package com.example.backend.shipmentsubdomain.presentationlayer.shipment;

import com.example.backend.shipmentsubdomain.businesslayer.ShipmentService;
import com.example.backend.shipmentsubdomain.presentationlayer.QuoteResponseModel;
import com.example.backend.util.EmailUtil;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;


@RestController
@RequestMapping(path = "api/v1/movingexpress/shipments")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:3000",allowCredentials = "true")
public class ShipmentController {
    private final EmailUtil emailUtil;
    private final ShipmentService shipmentService;

    @GetMapping
    public ResponseEntity<List<ShipmentResponseModel>> getAllShipments(@RequestParam Map<String, String> requestParams) {
        Optional<String> userId = Optional.ofNullable(requestParams.get("userId"));
        Optional<String> email = Optional.ofNullable(requestParams.get("email"));

        List<ShipmentResponseModel> shipments = shipmentService.getAllShipments(userId, email);
        return ResponseEntity.ok(shipments);
    }

    @PostMapping()
    public ResponseEntity<ShipmentResponseModel> createShipment(@RequestBody QuoteResponseModel quoteResponseModel){
        return ResponseEntity.status(CREATED).body(shipmentService.createShipment(quoteResponseModel));
    }
}
