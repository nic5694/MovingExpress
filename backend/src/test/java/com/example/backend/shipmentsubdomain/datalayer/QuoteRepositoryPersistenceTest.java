package com.example.backend.shipmentsubdomain.datalayer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class QuoteRepositoryPersistenceTest {

    @Autowired
    private QuoteRepository quoteRepository;

    @BeforeEach
    public void setUp() {

    }

    @Test
    public void whenSaveQuoteWithValues_thenValuesAreCorrectlyPersisted() {
        // Arrange: Create a sample Quote
        Quote quote = new Quote(
                new PickupAddress("123 Main St", "CityA", Country.CA, "12345", 101, true, "Apartment"),
                new DestinationAddress("456 Oak St", "CityB", Country.USA, "54321", 202, false, "House"),
                new ContactDetails("John", "Doe", "john.doe@example.com", "123-456-7890"),
                ContactMethod.EMAIL,
                LocalDate.of(2023, 1, 1),
                "Additional comments go here",
                "Moving out of parents house"
        );

        // Act
        Quote savedQuote = quoteRepository.save(quote);

        // Assert and check
        assertThat(savedQuote.getExpectedMovingDate()).isEqualTo(LocalDate.of(2023, 1, 1));
        assertThat(savedQuote.getContactMethod()).isEqualTo(ContactMethod.EMAIL);
        assertThat(savedQuote.getComment()).isEqualTo("Additional comments go here");

        assertThat(savedQuote.getPickupAddress().getPickupStreetAddress()).isEqualTo("123 Main St");
        assertThat(savedQuote.getPickupAddress().getPickupCity()).isEqualTo("CityA");
        assertThat(savedQuote.getPickupAddress().getPickupCountry()).isEqualTo(Country.CA);
        assertThat(savedQuote.getPickupAddress().getPickupPostalCode()).isEqualTo("12345");
        assertThat(savedQuote.getPickupAddress().getPickupNumberOfRooms()).isEqualTo(101);
        assertThat(savedQuote.getPickupAddress().isPickupElevator()).isTrue();
        assertThat(savedQuote.getPickupAddress().getPickupBuildingType()).isEqualTo("Apartment");

        assertThat(savedQuote.getDestinationAddress().getDestinationStreetAddress()).isEqualTo("456 Oak St");
        assertThat(savedQuote.getDestinationAddress().getDestinationCity()).isEqualTo("CityB");
        assertThat(savedQuote.getDestinationAddress().getDestinationCountry()).isEqualTo(Country.USA);
        assertThat(savedQuote.getDestinationAddress().getDestinationPostalCode()).isEqualTo("54321");
        assertThat(savedQuote.getDestinationAddress().getDestinationNumberOfRooms()).isEqualTo(202);
        assertThat(savedQuote.getDestinationAddress().isDestinationElevator()).isFalse();
        assertThat(savedQuote.getDestinationAddress().getDestinationBuildingType()).isEqualTo("House");

        assertThat(savedQuote.getContactDetails().getFirstName()).isEqualTo("John");
        assertThat(savedQuote.getContactDetails().getLastName()).isEqualTo("Doe");
        assertThat(savedQuote.getContactDetails().getEmailAddress()).isEqualTo("john.doe@example.com");
        assertThat(savedQuote.getContactDetails().getPhoneNumber()).isEqualTo("123-456-7890");
    }


}