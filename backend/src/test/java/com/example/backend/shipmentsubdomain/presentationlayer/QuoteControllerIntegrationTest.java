package com.example.backend.shipmentsubdomain.presentationlayer;

import com.example.backend.shipmentsubdomain.datalayer.ContactMethod;
import com.example.backend.shipmentsubdomain.datalayer.Country;
import com.example.backend.shipmentsubdomain.datalayer.QuoteRepository;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.LocalDate;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureWebClient
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles("test")
class QuoteControllerIntegrationTest {
    private final String BASE_URI_QUOTES = "/api/v1/movingexpress/quotes/request";

    @Autowired
    WebTestClient webTestClient;

    @Autowired
    QuoteRepository quoteRepository;

    @BeforeEach
    public void setUp(){

    }

    @Test
    public void whenAddQuoteWithValidValues_thenReturnNewQuote(){
        //arrange
        QuoteRequest quoteRequest = QuoteRequest.builder()
                .pickupStreetAddress("123 Main St")
                .pickupCity("CityA")
                .pickupCountry(Country.CA)
                .pickupPostalCode("12345")
                .pickupNumberOfRooms(4)
                .pickupElevator(true)
                .pickupBuildingType("Apartment")
                .destinationStreetAddress("456 Oak St")
                .destinationCity("CityB")
                .destinationCountry(Country.USA)
                .destinationPostalCode("54321")
                .destinationNumberOfRooms(4)
                .destinationElevator(false)
                .destinationBuildingType("House")
                .firstName("John")
                .lastName("Doe")
                .emailAddress("john.doe@example.com")
                .phoneNumber("123-456-7890")
                .expectedMovingDate(LocalDate.of(2023, 1, 1))
                .contactMethod(ContactMethod.EMAIL)
                .comment("Additional comments go here")
                .build();

        //act and assert
        webTestClient.post()
                .uri(BASE_URI_QUOTES)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(quoteRequest)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isCreated()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.quoteId").isNotEmpty()
                .jsonPath("$.pickupStreetAddress").isEqualTo("123 Main St")
                .jsonPath("$.pickupCity").isEqualTo("CityA")
                .jsonPath("$.pickupCountry").isEqualTo("CA")
                .jsonPath("$.pickupPostalCode").isEqualTo("12345")
                .jsonPath("$.pickupNumberOfRooms").isEqualTo(4)
                .jsonPath("$.pickupElevator").isEqualTo(true)
                .jsonPath("$.pickupBuildingType").isEqualTo("Apartment")
                .jsonPath("$.destinationStreetAddress").isEqualTo("456 Oak St")
                .jsonPath("$.destinationCity").isEqualTo("CityB")
                .jsonPath("$.destinationCountry").isEqualTo("USA")
                .jsonPath("$.destinationPostalCode").isEqualTo("54321")
                .jsonPath("$.destinationNumberOfRooms").isEqualTo(4)
                .jsonPath("$.destinationElevator").isEqualTo(false)
                .jsonPath("$.destinationBuildingType").isEqualTo("House")
                .jsonPath("$.firstName").isEqualTo("John")
                .jsonPath("$.lastName").isEqualTo("Doe")
                .jsonPath("$.emailAddress").isEqualTo("john.doe@example.com")
                .jsonPath("$.phoneNumber").isEqualTo("123-456-7890")
                .jsonPath("$.contactMethod").isEqualTo("EMAIL")
                .jsonPath("$.expectedMovingDate").isEqualTo("2023-01-01")
                .jsonPath("$.comment").isEqualTo("Additional comments go here");

    }
}