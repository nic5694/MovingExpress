package com.example.backend.shipmentsubdomain.businesslayer;

import com.example.backend.shipmentsubdomain.datalayer.*;
import com.example.backend.shipmentsubdomain.datamapperlayer.QuoteRequestMapper;
import com.example.backend.shipmentsubdomain.datamapperlayer.QuoteResponseMapper;
import com.example.backend.shipmentsubdomain.exceptions.NotFoundException;
import com.example.backend.shipmentsubdomain.presentationlayer.QuoteRequestModel;
import com.example.backend.shipmentsubdomain.presentationlayer.QuoteResponseModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
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

        sampleQuoteResponse = buildQuoteResponse();

        Mockito.when(quoteResponseMapper.entityToResponseModel(sampleQuote)).thenReturn(sampleQuoteResponse);
        Mockito.when(quoteRepository.findByQuoteIdentifier_QuoteId(quoteId)).thenReturn(sampleQuote);

        //Act
        QuoteResponse quoteResponse=quoteService.getQuote(quoteId);

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

        sampleQuoteResponse = buildQuoteResponse();

        Mockito.when(quoteRepository.findByQuoteIdentifier_QuoteId(quoteId)).thenThrow(new NotFoundException("quoteId not found: "+ quoteId));

        try {
            QuoteResponse quoteResponse = quoteService.getQuote(quoteId);

            fail("Expected NotFoundException, but got QuoteResponse: " + quoteResponse);
        } catch (NotFoundException e) {
            assertThat(e.getMessage()).contains("quoteId not found: " + quoteId);
        }
    }

    @Test
    void addQuote_ShouldSucceed() {
        // Arrange
        Mockito.when(quoteRequestMapper.requestModelToEntity(any(QuoteRequestModel.class))).thenReturn(sampleQuote);
        Mockito.when(quoteRepository.save(any(Quote.class))).thenReturn(sampleQuote);
        Mockito.when(quoteResponseMapper.entityToResponseModel(any(Quote.class))).thenReturn(sampleQuoteResponseModel);

        // Act
        QuoteResponseModel result = quoteService.addQuote(sampleQuoteRequestModel);

        // Assert
        assertEquals(sampleQuoteResponseModel, result);
        Mockito.verify(quoteRequestMapper, Mockito.times(1)).requestModelToEntity(any(QuoteRequestModel.class));
        Mockito.verify(quoteRepository, Mockito.times(1)).save(any(Quote.class));
        Mockito.verify(quoteResponseMapper, Mockito.times(1)).entityToResponseModel(any(Quote.class));
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

    private QuoteResponse buildQuoteResponse(){
        return QuoteResponse.builder()
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
                .shipmentName("Sample Shipment")
                .build();
    }
}
