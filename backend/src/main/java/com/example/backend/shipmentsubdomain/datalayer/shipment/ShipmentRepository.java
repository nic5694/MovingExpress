package com.example.backend.shipmentsubdomain.datalayer.shipment;

import com.example.backend.shipmentsubdomain.datalayer.shipment.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShipmentRepository extends JpaRepository<Shipment, Integer>{
    List<Shipment> findShipmentByUserId(String userId);

    List<Shipment> findShipmentByEmail(String email);

}
