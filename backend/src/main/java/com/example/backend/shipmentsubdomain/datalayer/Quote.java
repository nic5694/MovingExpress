package com.example.backend.shipmentsubdomain.datalayer;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;

@Entity
@Table(name="Quote")
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
    private Date date;
    private String comment;

    public Quote(){
        this.quoteIdentifier=new QuoteIdentifier();
    }

    public Quote(PickupAddress pickupAddress, DestinationAddress destinationAddress, ContactDetails contactDetails, Date date, ContactMethod contactMethod, String comment) {
        this.quoteIdentifier = new QuoteIdentifier();
        this.pickupAddress = pickupAddress;
        this.destinationAddress = destinationAddress;
        this.contactDetails = contactDetails;
        this.date=date;
        this.contactMethod=contactMethod;
        this.comment=comment;
    }
}
