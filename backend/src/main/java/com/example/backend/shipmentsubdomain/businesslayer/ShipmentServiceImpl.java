package com.example.backend.shipmentsubdomain.businesslayer;

import com.example.backend.customersubdomain.buisnesslayer.CustomerService;
import com.example.backend.shipmentsubdomain.datalayer.Address.Address;
import com.example.backend.shipmentsubdomain.datalayer.Address.AddressRepository;
import com.example.backend.shipmentsubdomain.datalayer.shipment.Shipment;
import com.example.backend.shipmentsubdomain.datalayer.shipment.ShipmentIdentifier;
import com.example.backend.shipmentsubdomain.datalayer.shipment.ShipmentRepository;
import com.example.backend.shipmentsubdomain.datalayer.shipment.Status;
import com.example.backend.shipmentsubdomain.datamapperlayer.shipment.AddressMapper;
import com.example.backend.shipmentsubdomain.datamapperlayer.shipment.QuoteResponseToShipmentMapper;
import com.example.backend.shipmentsubdomain.datamapperlayer.shipment.ShipmentResponseMapper;
import com.example.backend.shipmentsubdomain.presentationlayer.QuoteResponseModel;
import com.example.backend.shipmentsubdomain.presentationlayer.shipment.ShipmentResponseModel;
import com.example.backend.util.EmailUtil;
import com.example.backend.util.exceptions.ShipmentNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Generated;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
@Slf4j
public class ShipmentServiceImpl implements ShipmentService{
    private final TemplateEngine templateEngine;
    private final ShipmentRepository shipmentRepository;
    private final QuoteResponseToShipmentMapper quoteResponseToShipmentMapper;
    private final ShipmentResponseMapper shipmentResponseMapper;
    private final AddressMapper addressMapper;
    private final CustomerService customerService;
    private final AddressRepository addressRepository;
    private final EmailUtil emailUtil;

    @Generated
    String generateShipmentConfirmationEmailContentString(String shipmentId) {
        try {
            Context context = new Context();
            context.setVariable("shipmentID", shipmentId != null ? shipmentId : "");

            return templateEngine.process("shipmentConfirmation", context);
        } catch (Exception e) {
            log.error("Error while generating shipment confirmation email", e);
            return "";
        }
    }


    @Override
    @Transactional
    public ShipmentResponseModel createShipment(QuoteResponseModel quoteResponseModel) {
        // TODO: Add validation
        // Create and save the departure address
        Address pickupAddress = addressMapper.toAddress(
                quoteResponseModel.getPickupStreetAddress(),
                quoteResponseModel.getPickupCity(),
                quoteResponseModel.getPickupPostalCode(),
                quoteResponseModel.getPickupCountry());
        Address savedDepartureAddress = addressRepository.save(pickupAddress);

        // Create and save the arrival address
        Address destinationAddress = addressMapper.toAddress(
                quoteResponseModel.getDestinationStreetAddress(),
                quoteResponseModel.getDestinationCity(),
                quoteResponseModel.getDestinationPostalCode(),
                quoteResponseModel.getDestinationCountry());
        Address savedArrivalAddress = addressRepository.save(destinationAddress);

        // Map the QuoteResponseModel to Shipment using your mapper
        Shipment shipment = quoteResponseToShipmentMapper.toShipment(quoteResponseModel, addressMapper);

        // Set the saved addresses in the Shipment entity
        shipment.setPickupAddress(savedDepartureAddress);
        shipment.setDestinationAddress(savedArrivalAddress);
        shipment.setStatus(Status.QUOTED);
        shipment.setEmail(quoteResponseModel.getEmailAddress());
        shipment.setShipmentIdentifier(new ShipmentIdentifier());

        // Save the shipment
        Shipment savedShipment = shipmentRepository.save(shipment);
        //send email to user
        emailUtil.SslEmail(shipment.getEmail(), "Shipment Creation Confirmation", generateShipmentConfirmationEmailContentString(savedShipment.getShipmentIdentifier().getShipmentId()));
        return shipmentResponseMapper.entityToResponseModel(savedShipment);
    }

    @Override
    public List<ShipmentResponseModel> getAllShipments(Optional<String> userId, Optional<String> email) {
        List<Shipment> shipments;

        if (userId.isPresent()) {
            shipments = shipmentRepository.findShipmentByUserId(userId.get());

        }
        else if (email.isPresent()) {
            shipments = shipmentRepository.findShipmentByEmail(email.get());

        }else {
            shipments = shipmentRepository.findAll();
            log.info("Shipments: {}", shipments);
            log.info("This is after the find all", shipments.get(0).getStatus());
        }

        return shipments.stream()
                .map(shipmentResponseMapper::entityToResponseModel)
                .collect(Collectors.toList());
    }

    @Override
    public ShipmentResponseModel getShipment(String shipmentId) {
        Shipment existingShipment=shipmentRepository.findByShipmentIdentifier_ShipmentId(shipmentId);
        if(existingShipment==null){
            throw new ShipmentNotFoundException("shipmentId not found: "+shipmentId);
        }

        return shipmentResponseMapper.entityToResponseModel(existingShipment);
    }


}
