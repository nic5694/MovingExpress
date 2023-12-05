package com.example.backend.shipmentsubdomain.datalayer;

import com.example.backend.shipmentsubdomain.presentationlayer.QuoteResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.*;

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
                new PickupAddress("123 Main St", "CityA", "ProvinceA", Country.CA, "12345", 101, true, "Apartment"),
                new DestinationAddress("456 Oak St", "CityB", "ProvinceB", Country.USA, "54321", 202, false, "House"),
                new ContactDetails("John", "Doe", "john.doe@example.com", "123-456-7890"),
                ContactMethod.EMAIL,
                LocalDate.of(2023, 1, 1),
                "Additional comments go here"
        );

        // Act
        Quote savedQuote = quoteRepository.save(quote);

        // Asser
        assertThat(savedQuote.getExpectedMovingDate()).isEqualTo(LocalDate.of(2023, 1, 1));
        assertThat(savedQuote.getPickupAddress().getPickupProvince()).isEqualTo("ProvinceA");
        assertThat(savedQuote.getContactMethod()).isEqualTo(ContactMethod.EMAIL);
        assertThat(savedQuote.getComment()).isEqualTo("Additional comments go here");

    }


}