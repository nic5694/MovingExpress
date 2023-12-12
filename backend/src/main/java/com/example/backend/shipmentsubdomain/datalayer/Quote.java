package com.example.backend.shipmentsubdomain.datalayer;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name="quotes")
@Data
public class Quote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Embedded
    private QuoteIdentifier quoteIdentifier;
    @Embedded
    private PickupAddress pickupAddress;
    @Embedded
    private DestinationAddress destinationAddress;
    @Embedded
    private ContactDetails contactDetails;
    @Enumerated(EnumType.STRING)
    private ContactMethod contactMethod;
    private LocalDate expectedMovingDate;
    private LocalDateTime initiationDate;
    private String comment;
    @Enumerated(EnumType.STRING)
    private QuoteStatus quoteStatus;
    private String shipmentName;

    public Quote(){
        this.quoteIdentifier=new QuoteIdentifier();
    }

    public Quote(PickupAddress pickupAddress, DestinationAddress destinationAddress, ContactDetails contactDetails, ContactMethod contactMethod, LocalDate expectedMovingDate,  String comment, String shipmentName) {
        this.quoteIdentifier = new QuoteIdentifier();
        this.pickupAddress = pickupAddress;
        this.destinationAddress = destinationAddress;
        this.contactDetails = contactDetails;
        this.expectedMovingDate=expectedMovingDate;
        this.initiationDate=LocalDateTime.now();
        this.contactMethod=contactMethod;
        this.comment=comment;
        this.quoteStatus=QuoteStatus.CREATED;
        this.shipmentName=shipmentName;
    }
}
