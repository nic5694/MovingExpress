package com.example.backend.shipmentsubdomain.presentationlayer;

import com.example.backend.shipmentsubdomain.datalayer.ContactMethod;
import com.example.backend.shipmentsubdomain.datalayer.Country;
import com.example.backend.shipmentsubdomain.datalayer.QuoteRepository;
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
import org.springframework.test.context.jdbc.Sql;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureWebClient
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles("test")
@Sql({"/data-mysql.sql"})
class QuoteControllerIntegrationTest {
    private final String BASE_URI_QUOTES_REQUEST = "/api/v1/movingexpress/quotes/request";
    private final String BASE_URI_QUOTES_RETRIEVE="/api/v1/movingexpress/quotes/retrieve";

    private final String VALID_QUOTE_ID="a1b2c3d4-e5f6-g7h8-i9j0-k1l2m3n4o5p6";

    @Autowired
    WebTestClient webTestClient;
    @Autowired
    QuoteRepository quoteRepository;

    @BeforeEach
    public void setUp(){

    }

    @Test
    public void whenQuoteWithValidQuoteIdExists_thenReturnQuote(){
        webTestClient.get()
                .uri(BASE_URI_QUOTES_RETRIEVE+"/"+VALID_QUOTE_ID)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.quoteId").isEqualTo("a1b2c3d4-e5f6-g7h8-i9j0-k1l2m3n4o5p6")
                .jsonPath("$.expectedMovingDate").isEqualTo("2023-12-15")
                .jsonPath("$.contactMethod").isEqualTo("EMAIL")
                .jsonPath("$.comment").isEqualTo("Moving details for John Doe")

                .jsonPath("$.pickupStreetAddress").isEqualTo("123 Main St")
                .jsonPath("$.pickupCity").isEqualTo("Cityville")
                .jsonPath("$.pickupCountry").isEqualTo("CA")
                .jsonPath("$.pickupPostalCode").isEqualTo("M1A 1A1")
                .jsonPath("$.pickupNumberOfRooms").isEqualTo(3)
                .jsonPath("$.pickupElevator").isEqualTo(true)
                .jsonPath("$.pickupBuildingType").isEqualTo("Apartment")

                .jsonPath("$.destinationStreetAddress").isEqualTo("456 Oak St")
                .jsonPath("$.destinationCity").isEqualTo("Townsville")
                .jsonPath("$.destinationCountry").isEqualTo("USA")
                .jsonPath("$.destinationPostalCode").isEqualTo("M5V 2H1")
                .jsonPath("$.destinationNumberOfRooms").isEqualTo(4)
                .jsonPath("$.destinationElevator").isEqualTo(false)
                .jsonPath("$.destinationBuildingType").isEqualTo("House")

                .jsonPath("$.firstName").isEqualTo("John")
                .jsonPath("$.lastName").isEqualTo("Doe")
                .jsonPath("$.emailAddress").isEqualTo("john.doe@example.com")
                .jsonPath("$.phoneNumber").isEqualTo("123-555-1234");


    }

    @Test
    public void whenAddQuoteWithValidValues_thenReturnNewQuote(){
        //arrange
        QuoteRequestModel quoteRequestModel = QuoteRequestModel.builder()
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
                .uri(BASE_URI_QUOTES_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(quoteRequestModel)
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