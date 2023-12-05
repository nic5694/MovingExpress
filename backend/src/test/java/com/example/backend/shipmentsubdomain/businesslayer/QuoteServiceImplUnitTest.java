package com.example.backend.shipmentsubdomain.businesslayer;

import com.example.backend.shipmentsubdomain.datalayer.*;
import com.example.backend.shipmentsubdomain.datamapperlayer.QuoteRequestMapper;
import com.example.backend.shipmentsubdomain.datamapperlayer.QuoteResponseMapper;
import com.example.backend.shipmentsubdomain.presentationlayer.QuoteRequest;
import com.example.backend.shipmentsubdomain.presentationlayer.QuoteResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
class QuoteServiceImplUnitTest {

    @Mock
    private QuoteRepository quoteRepository;

    @Mock
    private QuoteRequestMapper quoteRequestMapper;

    @Mock
    private QuoteResponseMapper quoteResponseMapper;

    @InjectMocks
    private QuoteServiceImpl quoteService;

    private QuoteRequest sampleQuoteRequest;
    private Quote sampleQuote;
    private QuoteResponse sampleQuoteResponse;

    @BeforeEach
    void setUp() {
        sampleQuoteRequest = QuoteRequest.builder()
                .pickupStreetAddress("123 Main St")
                .pickupCity("Anytown")
                .pickupProvince("Anystate")
                .pickupCountry(Country.USA)
                .pickupPostalCode("12345")
                .pickupRoomNumber(101)
                .pickupElevator(true)
                .pickupBuildingType("Apartment")
                .destinationStreetAddress("456 Market St")
                .destinationCity("Othertown")
                .destinationProvince("Otherstate")
                .destinationCountry(Country.CA)
                .destinationPostalCode("54321")
                .destinationRoomNumber(202)
                .destinationElevator(false)
                .destinationBuildingType("House")
                .firstName("John")
                .lastName("Doe")
                .emailAddress("johndoe@example.com")
                .phoneNumber("555-1234")
                .contactMethod(ContactMethod.EMAIL)
                .expectedMovingDate(LocalDate.now())
                .comment("Moving soon")
                .build();

        sampleQuote = new Quote();
        // ... Initialize sampleQuote with appropriate data
        QuoteResponse.builder()
                .quoteId("341dbe66-36b1-4398-b708-dc55aaf60986")
                .pickupStreetAddress("789 Elm St")
                .pickupCity("Villagetown")
                .pickupProvince("Stateland")
                .pickupCountry(Country.USA)
                .pickupPostalCode("67890")
                .pickupRoomNumber(303)
                .pickupElevator(false)
                .pickupBuildingType("Condo")
                .destinationStreetAddress("321 Oak St")
                .destinationCity("Cityburgh")
                .destinationProvince("Territoryville")
                .destinationCountry(Country.CA)
                .destinationPostalCode("54321")
                .destinationRoomNumber(404)
                .destinationElevator(true)
                .destinationBuildingType("House")
                .firstName("Alice")
                .lastName("Smith")
                .phoneNumber("555-1234")
                .emailAddress("alice.smith@example.com")
                .contactMethod(ContactMethod.PHONE_NUMBER)
                .expectedMovingDate(LocalDate.parse("2023-12-15"))
                .initiationDate(LocalDateTime.parse("2023-12-04T05:08:33.500127636"))
                .comment("I need to move heavy stuff")
                .build();
    }

    @Test
    void addQuote_ShouldSucceed() {
        // Arrange
        Mockito.when(quoteRequestMapper.requestModelToEntity(any(QuoteRequest.class))).thenReturn(sampleQuote);
        Mockito.when(quoteRepository.save(any(Quote.class))).thenReturn(sampleQuote);
        Mockito.when(quoteResponseMapper.entityToResponseModel(any(Quote.class))).thenReturn(sampleQuoteResponse);

        // Act
        QuoteResponse result = quoteService.addQuote(sampleQuoteRequest);

        // Assert
        assertEquals(sampleQuoteResponse, result);
        Mockito.verify(quoteRequestMapper, Mockito.times(1)).requestModelToEntity(any(QuoteRequest.class));
        Mockito.verify(quoteRepository, Mockito.times(1)).save(any(Quote.class));
        Mockito.verify(quoteResponseMapper, Mockito.times(1)).entityToResponseModel(any(Quote.class));
    }
}
