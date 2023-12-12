package com.example.backend.shipmentsubdomain.datalayer.shipment;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity(name = "shipments")
public class Shipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;
    @Embedded
    public ShipmentIdentifier shipmentIdentifier;
    @Embedded
    public ClientIdentifier clientIdentifier;
    @Embedded
    public TruckIdentifier truckIdentifier;
    @Enumerated(EnumType.STRING)
    public ShipmentStatus shipmentStatus;
    @OneToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "departureAddressId", referencedColumnName = "addressId")
    public Address departureAddressId;
    @OneToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "arrivalAddressId", referencedColumnName = "addressId")
    public Address arrivalAddressId;
}
