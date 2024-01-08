package com.example.backend.shipmentsubdomain.presentationlayer.shipment;

import com.example.backend.shipmentsubdomain.datalayer.ContactMethod;
import com.example.backend.shipmentsubdomain.datalayer.Country;
import com.example.backend.shipmentsubdomain.datalayer.QuoteStatus;
import com.example.backend.shipmentsubdomain.datalayer.shipment.Status;
import com.example.backend.shipmentsubdomain.presentationlayer.QuoteResponseModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureWebClient
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles("test")
@Sql({"/data-mysql.sql"})
class ShipmentControllerIntegrationTest {

    @Autowired
    WebTestClient webTestClient;

    private final String BASE_URI_SHIPMENTS = "/api/v1/movingexpress/shipments";
    private final String VALID_SHIPMENT_ID="c0a80121-7f5e-4d77-a5a4-5d41b04f5a57";

    @BeforeEach
    public void setUp() {

    }

//    @Test
//    public void getAllShipmentsWithoutParams() {
//        webTestClient.get().uri(BASE_URI)
//                .accept(MediaType.APPLICATION_JSON)
//                .exchange()
//                .expectStatus().isOk()
//                .expectHeader().contentType(MediaType.APPLICATION_JSON)
//                .expectBody()
//                .jsonPath("$").isArray();
//    }

    @Test
    public void getAllShipmentsWithUserId() {
        String userId = "someUserId";
        webTestClient.get().uri(uriBuilder -> uriBuilder.path(BASE_URI_SHIPMENTS)
                        .queryParam("userId", userId)
                        .build())
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$").isArray();
    }

    @Test
    public void getAllShipmentsWithEmail() {
        String email = "example@example.com";
        webTestClient.get().uri(uriBuilder -> uriBuilder.path(BASE_URI_SHIPMENTS)
                        .queryParam("email", email)
                        .build())
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$").isArray();
    }

    @Test
    public void whenGetShipmentWithValidShipmentIdExists_thenReturnShipment(){
        webTestClient.get()
                .uri(BASE_URI_SHIPMENTS +"/"+VALID_SHIPMENT_ID)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.shipmentId").isEqualTo(VALID_SHIPMENT_ID)
                .jsonPath("$.pickupAddress.streetAddress").isEqualTo("4 Oak Ave")
                .jsonPath("$.pickupAddress.city").isEqualTo("Fotville")
                .jsonPath("$.pickupAddress.country").isEqualTo("CA")
                .jsonPath("$.pickupAddress.postalCode").isEqualTo("V3V 1W1")
                .jsonPath("$.destinationAddress.streetAddress").isEqualTo("555 Pine Ave")
                .jsonPath("$.destinationAddress.city").isEqualTo("NwHoke")
                .jsonPath("$.destinationAddress.country").isEqualTo("USA")
                .jsonPath("$.destinationAddress.postalCode").isEqualTo("M4S 7H6")
                .jsonPath("$.userId").isEmpty()
                .jsonPath("$.status").isEqualTo("QUOTED")
                .jsonPath("$.shipmentName").isEqualTo("ShipmentPQR")
                .jsonPath("$.approximateWeight").isEqualTo(1500.0)
                .jsonPath("$.weight").isEqualTo(1575.0)
                .jsonPath("$.email").isEqualTo("nicholasmartoccia04@icloud.com")
                .jsonPath("$.phoneNumber").isEqualTo("789-555-3123")
                .jsonPath("$.expectedMovingDate").isEqualTo("2023-12-30")
                .jsonPath("$.actualMovingDate").isEqualTo("2024-01-02");
    }

    @Test
    public void whenGetShipmentWithInvalidShipmentId_thenReturnShipmentNotFoundException(){
        String invalidShipmentId = "123";

        webTestClient.get()
                .uri(BASE_URI_SHIPMENTS + "/" + invalidShipmentId)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.NOT_FOUND)
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.message").isEqualTo("shipmentId not found: " + invalidShipmentId);
    }

    @Test
    public void createNewShipment_WithValidQuoteResponseModel() {
        QuoteResponseModel quoteResponseModel = QuoteResponseModel.builder()
                .pickupCountry(Country.CA)
                .pickupBuildingType("House")
                .pickupCity("CityA")
                .pickupStreetAddress("123 Main St")
                .pickupPostalCode("12345")
                .destinationCountry(Country.USA)
                .destinationBuildingType("House")
                .pickupElevator(false)
                .destinationCity("CityB")
                .destinationStreetAddress("456 Oak St")
                .destinationElevator(false)
                .destinationPostalCode("54321")
                .pickupNumberOfRooms(3)
                .destinationNumberOfRooms(4)
                .firstName("John")
                .lastName("Doe")
                .emailAddress("user@gmail.com")
                .phoneNumber("1234567890")
                .expectedMovingDate(LocalDate.now())
                .contactMethod(ContactMethod.PHONE_NUMBER)
                .comment("basic comments")
                .quoteStatus(QuoteStatus.PENDING)
                .name("shipmentName")
                .build();

        webTestClient.post()
                .uri(BASE_URI_SHIPMENTS)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(quoteResponseModel)
                .exchange()
                .expectStatus()
                .isCreated()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody(ShipmentResponseModel.class)
                .value((shipmentResponseModel1) -> {
                    assertNotNull(shipmentResponseModel1);
                    assertNotNull(shipmentResponseModel1.getShipmentId());
                    assertNotNull(shipmentResponseModel1.getPickupAddress());
                    assertNotNull(shipmentResponseModel1.getDestinationAddress());
                    assertEquals(quoteResponseModel.getPickupCountry(), shipmentResponseModel1.getPickupAddress().getCountry());
                    assertEquals(quoteResponseModel.getPickupStreetAddress(), shipmentResponseModel1.getPickupAddress().getStreetAddress());
                    assertEquals(quoteResponseModel.getPickupCity(), shipmentResponseModel1.getPickupAddress().getCity());
                    assertEquals(quoteResponseModel.getPickupPostalCode(), shipmentResponseModel1.getPickupAddress().getPostalCode());
                    assertEquals(quoteResponseModel.getDestinationCountry(), shipmentResponseModel1.getDestinationAddress().getCountry());
                    assertEquals(quoteResponseModel.getDestinationStreetAddress(), shipmentResponseModel1.getDestinationAddress().getStreetAddress());
                    assertEquals(quoteResponseModel.getDestinationCity(), shipmentResponseModel1.getDestinationAddress().getCity());
                    assertEquals(quoteResponseModel.getDestinationPostalCode(), shipmentResponseModel1.getDestinationAddress().getPostalCode());
                    assertEquals(Status.QUOTED, shipmentResponseModel1.getStatus());
                    assertEquals(quoteResponseModel.getName(), shipmentResponseModel1.getShipmentName());
                    assertEquals(quoteResponseModel.getEmailAddress(), shipmentResponseModel1.getEmail());
                    assertEquals(quoteResponseModel.getPhoneNumber(), shipmentResponseModel1.getPhoneNumber());
                });
    }

}