package com.example.backend.shipmentsubdomain.businesslayer;

import com.example.backend.shipmentsubdomain.datalayer.*;
import com.example.backend.shipmentsubdomain.datamapperlayer.quote.QuoteRequestMapper;
import com.example.backend.shipmentsubdomain.datamapperlayer.quote.QuoteResponseMapper;
import com.example.backend.shipmentsubdomain.exceptions.NotFoundException;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.anyString;

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

    }

    @Test
    void getQuoteWithValidQuoteId_ShouldSucceed(){
        //Arrange
        String quoteId="7d08a2a7-368c-49e8-9f1d-42f1b0196bc5";

        sampleQuote = buildQuote();

        sampleQuoteResponseModel = buildQuoteResponse();

        Mockito.when(quoteResponseMapper.entityToResponseModel(sampleQuote)).thenReturn(sampleQuoteResponseModel);
        Mockito.when(quoteRepository.findByQuoteIdentifier_QuoteId(quoteId)).thenReturn(sampleQuote);

        //Act
        QuoteResponseModel quoteResponse=quoteService.getQuote(quoteId);

        assertThat(quoteResponse.getExpectedMovingDate()).isEqualTo(sampleQuote.getExpectedMovingDate());
        assertThat(quoteResponse.getContactMethod()).isEqualTo(sampleQuote.getContactMethod());
        assertThat(quoteResponse.getComment()).isEqualTo(sampleQuote.getComment());

        assertThat(quoteResponse.getPickupStreetAddress()).isEqualTo(sampleQuote.getPickupAddress().getPickupStreetAddress());
        assertThat(quoteResponse.getPickupCity()).isEqualTo(sampleQuote.getPickupAddress().getPickupCity());
        assertThat(quoteResponse.getPickupCountry()).isEqualTo(sampleQuote.getPickupAddress().getPickupCountry());
        assertThat(quoteResponse.getPickupPostalCode()).isEqualTo(sampleQuote.getPickupAddress().getPickupPostalCode());
        assertThat(quoteResponse.getPickupNumberOfRooms()).isEqualTo(sampleQuote.getPickupAddress().getPickupNumberOfRooms());
        assertThat(quoteResponse.isPickupElevator()).isEqualTo(sampleQuote.getPickupAddress().isPickupElevator());
        assertThat(quoteResponse.getPickupBuildingType()).isEqualTo(sampleQuote.getPickupAddress().getPickupBuildingType());

        assertThat(quoteResponse.getDestinationStreetAddress()).isEqualTo(sampleQuote.getDestinationAddress().getDestinationStreetAddress());
        assertThat(quoteResponse.getDestinationCity()).isEqualTo(sampleQuote.getDestinationAddress().getDestinationCity());
        assertThat(quoteResponse.getDestinationCountry()).isEqualTo(sampleQuote.getDestinationAddress().getDestinationCountry());
        assertThat(quoteResponse.getDestinationPostalCode()).isEqualTo(sampleQuote.getDestinationAddress().getDestinationPostalCode());
        assertThat(quoteResponse.getDestinationNumberOfRooms()).isEqualTo(sampleQuote.getDestinationAddress().getDestinationNumberOfRooms());
        assertThat(quoteResponse.isDestinationElevator()).isEqualTo(sampleQuote.getDestinationAddress().isDestinationElevator());
        assertThat(quoteResponse.getDestinationBuildingType()).isEqualTo(sampleQuote.getDestinationAddress().getDestinationBuildingType());

        assertThat(quoteResponse.getFirstName()).isEqualTo(sampleQuote.getContactDetails().getFirstName());
        assertThat(quoteResponse.getLastName()).isEqualTo(sampleQuote.getContactDetails().getLastName());
        assertThat(quoteResponse.getEmailAddress()).isEqualTo(sampleQuote.getContactDetails().getEmailAddress());
        assertThat(quoteResponse.getPhoneNumber()).isEqualTo(sampleQuote.getContactDetails().getPhoneNumber());

        Mockito.verify(quoteRepository, Mockito.times(1)).findByQuoteIdentifier_QuoteId(quoteId);
        Mockito.verify(quoteResponseMapper, Mockito.times(1)).entityToResponseModel(sampleQuote);
    }

    @Test
    void getQuoteWithInvalidQuoteId_ShouldNotSucceed(){
        //Act
        String quoteId="123";

        sampleQuote = buildQuote();

        sampleQuoteResponseModel = buildQuoteResponse();

        Mockito.when(quoteRepository.findByQuoteIdentifier_QuoteId(quoteId)).thenThrow(new NotFoundException("quoteId not found: "+ quoteId));

        try {
            QuoteResponseModel quoteResponse = quoteService.getQuote(quoteId);

            fail("Expected NotFoundException, but got QuoteResponse: " + quoteResponse);
        } catch (NotFoundException e) {
            assertThat(e.getMessage()).contains("quoteId not found: " + quoteId);
        }
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

    private Quote buildQuote(){
        return Quote.builder()
                .pickupAddress(
                        PickupAddress.builder()
                                .pickupStreetAddress("123 Main St")
                                .pickupCity("Anytown")
                                .pickupCountry(Country.USA)
                                .pickupPostalCode("12345")
                                .pickupNumberOfRooms(5)
                                .pickupElevator(true)
                                .pickupBuildingType("Apartment")
                                .build()
                )
                .destinationAddress(
                        DestinationAddress.builder()
                                .destinationStreetAddress("456 Market St")
                                .destinationCity("Othertown")
                                .destinationCountry(Country.CA)
                                .destinationPostalCode("54321")
                                .destinationNumberOfRooms(4)
                                .destinationElevator(false)
                                .destinationBuildingType("House")
                                .build()
                )
                .contactDetails(
                        ContactDetails.builder()
                                .firstName("John")
                                .lastName("Doe")
                                .emailAddress("johndoe@example.com")
                                .phoneNumber("555-1234")
                                .build()
                )
                .contactMethod(ContactMethod.EMAIL)
                .expectedMovingDate(LocalDate.of(2023, 1, 1))
                .initiationDate(LocalDateTime.now())
                .comment("Moving soon")
                .quoteStatus(QuoteStatus.PENDING)
                .shipmentName("Sample Shipment")
                .build();
    }

    private QuoteResponseModel buildQuoteResponse(){
        return QuoteResponseModel.builder()
                .quoteId("341dbe66-36b1-4398-b708-dc55aaf60986")
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
                .phoneNumber("555-1234")
                .emailAddress("johndoe@example.com")
                .contactMethod(ContactMethod.EMAIL)
                .expectedMovingDate(LocalDate.of(2023, 1, 1))
                .initiationDate(LocalDateTime.now())
                .comment("Moving soon")
                .quoteStatus(QuoteStatus.PENDING)
                .name("Sample Shipment")
                .build();
    }
}
