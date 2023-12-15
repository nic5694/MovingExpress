package com.example.backend.shipmentsubdomain.datalayer.shipment;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;
import java.util.Date;

@Data
@Entity(name = "shipments")
public class Shipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Embedded
    private ShipmentIdentifier shipmentIdentifier;

    @Nullable
    public String userId;

    @Embedded
    private TruckIdentifier truckIdentifier;

    @Enumerated(EnumType.STRING)
    private ShipmentStatus shipmentStatus;
    private LocalDate expectedMovingDate;
    private LocalDate actualMovingDate;
    private double approximateWeight;
    private String name;


    @OneToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "departure_address_id", referencedColumnName = "addressId")
    private Address departureAddress;

    @OneToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "arrival_address_id", referencedColumnName = "addressId")
    private Address arrivalAddress;
}
