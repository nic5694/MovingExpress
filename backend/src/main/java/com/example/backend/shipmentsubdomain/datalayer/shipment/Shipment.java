package com.example.backend.shipmentsubdomain.datalayer.shipment;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Date;

@Entity(name = "shipments")
public class Shipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;
    @Embedded
    public ShipmentIdentifier shipmentIdentifier;
    @Nullable
    public String userId;
    @Embedded
    public TruckIdentifier truckIdentifier;
    @Enumerated(EnumType.STRING)
    public ShipmentStatus shipmentStatus;
    public Date expectedMovingDate;
    public Date actualMovingDate;
    public double approximateWeight;
    public String name;


    @OneToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "departure_address_id", referencedColumnName = "addressId")
    public Address departureAddressId;
    @OneToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "arrival_address_id", referencedColumnName = "addressId")
    public Address arrivalAddressId;
}
