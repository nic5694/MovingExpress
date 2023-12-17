package com.example.backend.shipmentsubdomain.datalayer.shipment;

import com.example.backend.shipmentsubdomain.datalayer.Address.Address;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;

@Data
@Entity(name = "shipments")
public class Shipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Embedded
    private ShipmentIdentifier shipmentIdentifier;

    @Nullable
    private String userId;

    @Nullable
    private String email;

    @Nullable
    @Embedded
    private TruckIdentifier truckIdentifier;

    @Enumerated(EnumType.STRING)
    private ShipmentStatus shipmentStatus;

    private LocalDate expectedMovingDate;

    @Nullable
    private LocalDate actualMovingDate;

    @Nullable
    private double approximateWeight;
    @Nullable
    private double weight;
    private String name;


    @OneToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "departure_address_id", referencedColumnName = "addressId")
    private Address departureAddress;

    @OneToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "arrival_address_id", referencedColumnName = "addressId")
    private Address arrivalAddress;

    public Shipment(String userId, TruckIdentifier truckIdentifier, ShipmentStatus shipmentStatus, LocalDate expectedMovingDate, LocalDate actualMovingDate, double approximateWeight, String name, Address departureAddress, Address arrivalAddress, String email) {
        this.userId = userId;
        this.truckIdentifier = truckIdentifier;
        this.shipmentStatus = shipmentStatus;
        this.expectedMovingDate = expectedMovingDate;
        this.actualMovingDate = actualMovingDate;
        this.approximateWeight = approximateWeight;
        this.name = name;
        this.departureAddress = departureAddress;
        this.arrivalAddress = arrivalAddress;
        this.shipmentIdentifier = new ShipmentIdentifier();
        this.email = email;

    }

    public Shipment() {
        this.shipmentIdentifier = new ShipmentIdentifier();
    }
}
