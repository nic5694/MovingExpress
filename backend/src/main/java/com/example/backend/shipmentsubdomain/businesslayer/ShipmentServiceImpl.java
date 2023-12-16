package com.example.backend.shipmentsubdomain.businesslayer;

import com.example.backend.customersubdomain.buisnesslayer.CustomerService;
import com.example.backend.shipmentsubdomain.datalayer.Address.Address;
import com.example.backend.shipmentsubdomain.datalayer.Address.AddressRepository;
import com.example.backend.shipmentsubdomain.datalayer.shipment.Shipment;
import com.example.backend.shipmentsubdomain.datalayer.shipment.ShipmentRepository;
import com.example.backend.shipmentsubdomain.datalayer.shipment.ShipmentStatus;
import com.example.backend.shipmentsubdomain.datamapperlayer.shipment.AddressMapper;
import com.example.backend.shipmentsubdomain.datamapperlayer.shipment.QuoteResponseToShipmentMapper;
import com.example.backend.shipmentsubdomain.datamapperlayer.shipment.ShipmentResponseMapper;
import com.example.backend.shipmentsubdomain.presentationlayer.QuoteResponseModel;
import com.example.backend.shipmentsubdomain.presentationlayer.shipment.ShipmentResponseModel;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.StringWriter;
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
    @Override
    public String generateShipmentConfirmationEmail(String shipmentId) {
        // Create a Thymeleaf context and add variables
        Context context = new Context();

        context.setVariable("shipmentID", shipmentId != null ? String.valueOf(shipmentId) : "");

        // Process the Thymeleaf template
        StringWriter stringWriter = new StringWriter();
        templateEngine.process("shipmentConfirmation", context, stringWriter);

        log.info("stringWriter: " + stringWriter.toString());
        log.info("shipmentId: " + shipmentId);

        // Get the final HTML content as a string
        return stringWriter.toString();
    }


    @Override
    @Transactional
    public ShipmentResponseModel createShipment(QuoteResponseModel quoteResponseModel) {
        // TODO: Add validation

        // Create and save the departure address
        Address departureAddress = addressMapper.toAddress(
                quoteResponseModel.getPickupStreetAddress(),
                quoteResponseModel.getPickupCity(),
                quoteResponseModel.getPickupPostalCode(),
                quoteResponseModel.getPickupCountry());
        Address savedDepartureAddress = addressRepository.save(departureAddress);

        // Create and save the arrival address
        Address arrivalAddress = addressMapper.toAddress(
                quoteResponseModel.getDestinationStreetAddress(),
                quoteResponseModel.getDestinationCity(),
                quoteResponseModel.getDestinationPostalCode(),
                quoteResponseModel.getDestinationCountry());
        Address savedArrivalAddress = addressRepository.save(arrivalAddress);

        // Map the QuoteResponseModel to Shipment using your mapper
        Shipment shipment = quoteResponseToShipmentMapper.toShipment(quoteResponseModel, addressMapper);

        // Set the saved addresses in the Shipment entity
        shipment.setDepartureAddress(savedDepartureAddress);
        shipment.setArrivalAddress(savedArrivalAddress);
        shipment.setShipmentStatus(ShipmentStatus.QUOTED);

        // Save the shipment
        Shipment savedShipment = shipmentRepository.save(shipment);

        return shipmentResponseMapper.entityToResponseModel(savedShipment);
    }

    @Override
    public List<ShipmentResponseModel> getAllShipments(Optional<String> userId) {
        List<Shipment> shipments;

        if (userId.isPresent()) {
            shipments = shipmentRepository.findShipmentByUserId(userId.get());

        } else {
            shipments = shipmentRepository.findAll();
        }

        return shipments.stream()
                .map(shipmentResponseMapper::entityToResponseModel)
                .collect(Collectors.toList());
    }


}
