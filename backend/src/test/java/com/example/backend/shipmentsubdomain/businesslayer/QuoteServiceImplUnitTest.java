package com.example.backend.shipmentsubdomain.businesslayer;

import com.example.backend.shipmentsubdomain.datalayer.*;
import com.example.backend.shipmentsubdomain.datamapperlayer.quote.QuoteRequestMapper;
import com.example.backend.shipmentsubdomain.datamapperlayer.quote.QuoteResponseMapper;
import com.example.backend.shipmentsubdomain.presentationlayer.QuoteRequestModel;
import com.example.backend.shipmentsubdomain.presentationlayer.QuoteResponseModel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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

    private QuoteRequestModel sampleQuoteRequestModel;
    private Quote sampleQuote;
    private QuoteResponseModel sampleQuoteResponseModel;

    @BeforeEach
    void setUp() {
        sampleQuoteRequestModel = QuoteRequestModel.builder()
                .pickupStreetAddress("123 Main St")
                .pickupCity("Anytown")
                .pickupCountry(Country.USA)
                .pickupPostalCode("12345")
                .pickupNumberOfRooms(5)
                .pickupElevator(true)
                .pickupBuildingType("Apartment")
                .destinationStreetAddress("456 Market St")
                .destinationCity("Othertown")
                .destinationCountry(Country.CA)
                .destinationPostalCode("54321")
                .destinationNumberOfRooms(4)
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
        QuoteResponseModel.builder()
                .quoteId("341dbe66-36b1-4398-b708-dc55aaf60986")
                .pickupStreetAddress("789 Elm St")
                .pickupCity("Villagetown")
                .pickupCountry(Country.USA)
                .pickupPostalCode("67890")
                .pickupNumberOfRooms(3)
                .pickupElevator(false)
                .pickupBuildingType("Condo")
                .destinationStreetAddress("321 Oak St")
                .destinationCity("Cityburgh")
                .destinationCountry(Country.CA)
                .destinationPostalCode("54321")
                .destinationNumberOfRooms(6)
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

    @AfterEach
    void tearDown(){
        quoteRepository.deleteAll();
    }

    @Test
    void addQuote_ShouldSucceed() {
        // Arrange
        when(quoteRequestMapper.requestModelToEntity(any(QuoteRequestModel.class))).thenReturn(sampleQuote);
        when(quoteRepository.save(any(Quote.class))).thenReturn(sampleQuote);
        when(quoteResponseMapper.entityToResponseModel(any(Quote.class))).thenReturn(sampleQuoteResponseModel);

        // Act
        QuoteResponseModel result = quoteService.addQuote(sampleQuoteRequestModel);

        // Assert
        assertEquals(sampleQuoteResponseModel, result);
        verify(quoteRequestMapper, Mockito.times(1)).requestModelToEntity(any(QuoteRequestModel.class));
        verify(quoteRepository, Mockito.times(1)).save(any(Quote.class));
        verify(quoteResponseMapper, Mockito.times(1)).entityToResponseModel(any(Quote.class));
    }

    @Test
    void getAllQuotesByQuoteStatus_ShouldSucceed(){

        Quote quote1 = new Quote(
                new PickupAddress("123 Main St", "CityA", Country.CA, "12345", 101, true, "Apartment"),
                new DestinationAddress("456 Oak St", "CityB", Country.USA, "54321", 202, false, "House"),
                new ContactDetails("John", "Doe", "john@example.com", "123-456-7890"),
                ContactMethod.EMAIL,
                LocalDate.of(2023, 1, 1),
                "Additional comments go here",
                "quote1"
        );

        Quote quote2 = new Quote(
                new PickupAddress("123 Main St", "CityA", Country.CA, "12345", 101, true, "Apartment"),
                new DestinationAddress("456 Oak St", "CityB", Country.USA, "54321", 202, false, "House"),
                new ContactDetails("John", "Doe", "j@example.com", "123-456-7890"),
                ContactMethod.EMAIL,
                LocalDate.of(2023, 1, 1),
                "Additional comments go here",
                "quote2"
        );

        List<Quote> listOfQuotes = List.of(quote1,quote2);


        QuoteResponseModel QRM1 = QuoteResponseModel.builder()
                .quoteId("341dbe66-36b1-4398-b708-dc55aaf60900")
                .pickupStreetAddress("789 Elm St")
                .pickupCity("Villagetown")
                .pickupCountry(Country.USA)
                .pickupPostalCode("67890")
                .pickupNumberOfRooms(3)
                .pickupElevator(false)
                .pickupBuildingType("Condo")
                .destinationStreetAddress("321 Oak St")
                .destinationCity("Cityburgh")
                .destinationCountry(Country.CA)
                .destinationPostalCode("54321")
                .destinationNumberOfRooms(6)
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
                .quoteStatus(QuoteStatus.PENDING)
                .name("Shipment Name1")
                .build();

        QuoteResponseModel QRM2 = QuoteResponseModel.builder()
                .quoteId("341dbe66-36b1-4398-b708-dc55aaf60955")
                .pickupStreetAddress("789 Elm St")
                .pickupCity("Villagetown")
                .pickupCountry(Country.USA)
                .pickupPostalCode("67890")
                .pickupNumberOfRooms(3)
                .pickupElevator(false)
                .pickupBuildingType("Condo")
                .destinationStreetAddress("321 Oak St")
                .destinationCity("Cityburgh")
                .destinationCountry(Country.CA)
                .destinationPostalCode("54321")
                .destinationNumberOfRooms(6)
                .destinationElevator(true)
                .destinationBuildingType("House")
                .firstName("Alice")
                .lastName("Smith")
                .phoneNumber("555-1234")
                .emailAddress("test@example.com")
                .contactMethod(ContactMethod.PHONE_NUMBER)
                .expectedMovingDate(LocalDate.parse("2023-12-15"))
                .initiationDate(LocalDateTime.parse("2023-12-04T05:08:33.500127636"))
                .comment("I need to move heavy stuff")
                .quoteStatus(QuoteStatus.PENDING)
                .name("Shipment Name2")
                .build();

        List<QuoteResponseModel> listOfQuoteResponseModels = List.of(QRM1,QRM2);

        when(quoteResponseMapper.entitiesListToResponseList(anyList())).thenReturn(listOfQuoteResponseModels);
        List<QuoteResponseModel> convertedQuotesToResponseModels = quoteResponseMapper.entitiesListToResponseList(listOfQuotes);
        assertEquals(convertedQuotesToResponseModels.size(),listOfQuoteResponseModels.size());
        assertEquals(convertedQuotesToResponseModels.get(0).getQuoteStatus(), QuoteStatus.PENDING);



        List<QuoteResponseModel> listOfQuoteResponseModelsResult = quoteService.getAllQuotes(QuoteStatus.PENDING);
        when(quoteService.getAllQuotes(QuoteStatus.PENDING)).thenReturn(listOfQuoteResponseModels);
        assertEquals(listOfQuoteResponseModelsResult.size(),listOfQuoteResponseModels.size());
        assertEquals(listOfQuoteResponseModelsResult.get(0).getQuoteStatus(), QuoteStatus.PENDING);


    }
}
