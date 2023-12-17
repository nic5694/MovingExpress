package com.example.backend.customersubdomain.buisnesslayer;

import com.example.backend.customersubdomain.datalayer.Customer;
import com.example.backend.customersubdomain.datalayer.CustomerRepository;
import com.example.backend.customersubdomain.datamapperlayer.CustomerRequestMapper;
import com.example.backend.customersubdomain.datamapperlayer.CustomerResponseMapper;
import com.example.backend.customersubdomain.presentationlayer.CustomerRequestModel;
import com.example.backend.customersubdomain.presentationlayer.CustomerResponseModel;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class CustomerServiceImplTest {

    @Mock
    private CustomerRepository customerRepository;
    @Mock
    private CustomerResponseMapper customerResponseMapper;
    @Mock
    private CustomerRequestMapper customerRequestMapper;
    @InjectMocks
    private CustomerServiceImpl customerService;

    @Test
    void getCustomerByUserId() {
        String userId = "auth|123456789";

        CustomerResponseModel mockCustomerResponse = CustomerResponseModel.builder()
                .firstName("Alice")
                .lastName("Doe")
                .email("alice.doe@gmail.com")
                .phoneNumber("+1234567890")
                .streetAddress("123 Main St")
                .profilePictureUrl("https://www.google.com")
                .country("Canada")
                .postalCode("A1B 2C3")
                .city("Cityville")
                .userId(userId)
                .build();

        Customer mockCustomer = Customer.builder()
                .firstName("Alice")
                .lastName("Doe")
                .email("alice.doe@gmail.com")
                .phoneNumber("+1234567890")
                .streetAddress("123 Main St")
                .profilePictureUrl("https://www.google.com")
                .postalCode("A1B 2C3")
                .country("Canada")
                .city("Cityville")
                .userId(userId)
                .build();

        Mockito.when(customerRepository.findCustomerByUserId(userId)).thenReturn(mockCustomer);

        Mockito.when(customerResponseMapper.toCustomerResponse(mockCustomer)).thenReturn(mockCustomerResponse);

        CustomerResponseModel result = customerService.getCustomerByUserId(userId);


        assertEquals(mockCustomer.getPostalCode(), result.getPostalCode());
        assertEquals(mockCustomer.getStreetAddress(), result.getStreetAddress());

        assertEquals(mockCustomer.getCountry(), result.getCountry());

        assertEquals(mockCustomer.getCity(), result.getCity());

        assertEquals(mockCustomer.getEmail(), result.getEmail());

        assertEquals(mockCustomer.getFirstName(), result.getFirstName());
        assertEquals(mockCustomer.getLastName(), result.getLastName());

        assertEquals(mockCustomer.getPhoneNumber(), result.getPhoneNumber());
        assertEquals(mockCustomer.getProfilePictureUrl(), result.getProfilePictureUrl());
        assertEquals(mockCustomer.getUserId(), result.getUserId());

    }

    @Test
    void createCustomer() {

        String userId = "google|123456789";

        CustomerRequestModel mockCustomerRequest = CustomerRequestModel.builder()
                .firstName("Alice")
                .lastName("Doe")
                .email("test@email.com")
                .phoneNumber("+1234567890")
                .postalCode("A1B 2C3")
                .streetAddress("123 Main St")
                .country("Canada")
                .profilePictureUrl("https://www.google.com")
                .city("Cityville")
                .userId(userId)
                .build();

        Customer mockCustomer = Customer.builder()
                .firstName("Alice")
                .lastName("Doe")
                .profilePictureUrl("https://www.google.com")
                .email("test@email.com")
                .phoneNumber("+1234567890")
                .postalCode("A1B 2C3")
                .streetAddress("123 Main St")
                .country("Canada")
                .city("Cityville")
                .userId(userId)
                .build();

        CustomerResponseModel mockCustomerResponse = CustomerResponseModel.builder()
                .firstName("Alice")
                .lastName("Doe")
                .profilePictureUrl("https://www.google.com")
                .email("test@email.com")
                .phoneNumber("+1234567890")
                .postalCode("A1B 2C3")
                .streetAddress("123 Main St")
                .country("Canada")
                .city("Cityville")
                .userId(userId)
                .build();

        Mockito.when(customerRepository.existsByUserId(userId)).thenReturn(false);

        Mockito.when(customerRequestMapper.toCustomer(mockCustomerRequest)).thenReturn(mockCustomer);

        Mockito.when(customerRepository.save(mockCustomer)).thenReturn(mockCustomer);

        Mockito.when(customerResponseMapper.toCustomerResponse(mockCustomer)).thenReturn(mockCustomerResponse);

        CustomerResponseModel result = customerService.addCustomer(mockCustomerRequest);

        assertEquals(mockCustomer.getPostalCode(), result.getPostalCode());
        assertEquals(mockCustomer.getStreetAddress(), result.getStreetAddress());

        assertEquals(mockCustomer.getCountry(), result.getCountry());

        assertEquals(mockCustomer.getCity(), result.getCity());

        assertEquals(mockCustomer.getEmail(), result.getEmail());

        assertEquals(mockCustomer.getFirstName(), result.getFirstName());
        assertEquals(mockCustomer.getLastName(), result.getLastName());

        assertEquals(mockCustomer.getPhoneNumber(), result.getPhoneNumber());
        assertEquals(mockCustomer.getProfilePictureUrl(), result.getProfilePictureUrl());
        assertEquals(mockCustomer.getUserId(), result.getUserId());




    }

    @Test
    void updateCustomer() {

        String userId = "google|123456789";

        CustomerRequestModel mockCustomerRequest = CustomerRequestModel.builder()
                .firstName("Alice")
                .lastName("Doe")
                .email("test@email.com")
                .phoneNumber("+1234567890")
                .postalCode("A1B 2C3")
                .streetAddress("123 Main St")
                .country("Canada")
                .profilePictureUrl("https://www.google.com")
                .city("Cityville")
                .userId(userId)
                .build();

        Customer mockCustomerExisting = Customer.builder()
                .firstName("Old")
                .lastName("Doe")
                .profilePictureUrl("https://www.google.com")
                .email("new@email.com")
                .phoneNumber("+1235367890")
                .postalCode("V1B 2C3")
                .streetAddress("123 New St")
                .country("USA")
                .city("NEWVILLE")
                .userId(userId)
                .build();

        CustomerResponseModel mockCustomerResponse = CustomerResponseModel.builder()
                .firstName("Alice")
                .lastName("Doe")
                .email("test@email.com")
                .phoneNumber("+1234567890")
                .postalCode("A1B 2C3")
                .streetAddress("123 Main St")
                .country("Canada")
                .profilePictureUrl("https://www.google.com")
                .city("Cityville")
                .userId(userId)
                .build();



        Mockito.when(customerRepository.findCustomerByUserId(userId)).thenReturn(mockCustomerExisting);

        Mockito.when(customerRequestMapper.toCustomer(mockCustomerRequest)).thenReturn(mockCustomerExisting);

        Mockito.when(customerRepository.save(mockCustomerExisting)).thenReturn(mockCustomerExisting);

        Mockito.when(customerResponseMapper.toCustomerResponse(mockCustomerExisting)).thenReturn(mockCustomerResponse);

        CustomerResponseModel result = customerService.updateCustomer(mockCustomerRequest, userId);



        assertEquals(mockCustomerExisting.getPostalCode(), result.getPostalCode());
        assertEquals(mockCustomerExisting.getStreetAddress(), result.getStreetAddress());

        assertEquals(mockCustomerExisting.getCountry(), result.getCountry());

        assertEquals(mockCustomerExisting.getCity(), result.getCity());

        assertEquals(mockCustomerExisting.getEmail(), result.getEmail());

        assertEquals(mockCustomerExisting.getFirstName(), result.getFirstName());
        assertEquals(mockCustomerExisting.getLastName(), result.getLastName());

        assertEquals(mockCustomerExisting.getPhoneNumber(), result.getPhoneNumber());
        assertEquals(mockCustomerExisting.getProfilePictureUrl(), result.getProfilePictureUrl());
        assertEquals(mockCustomerExisting.getUserId(), result.getUserId());

    }

    @Test
    void updateManyFieldsForCustomer() {

        String userId = "google|123456789";

        CustomerRequestModel mockCustomerRequest = CustomerRequestModel.builder()
                .firstName("Alice")
                .lastName("Doe")
                .email("fresh@email.com")
                .phoneNumber("+1234569870")
                .postalCode("T6H 2C3")
                .streetAddress("123 Main St")
                .country("Canada")
                .profilePictureUrl("https://www.google.com")
                .city("NewVille")
                .userId(userId)
                .build();

        Customer mockCustomerExisting = Customer.builder()
                .firstName("Old")
                .lastName("Doe")
                .profilePictureUrl("https://www.google.com")
                .email("new@email.com")
                .phoneNumber("+1235367890")
                .postalCode("V1B 2C3")
                .streetAddress("123 New St")
                .country("USA")
                .city("NEWVILLE")
                .userId(userId)
                .build();


        CustomerResponseModel mockCustomerResponse = CustomerResponseModel.builder()
                .firstName("Alice")
                .lastName("Doe")
                .email("fresh@email.com")
                .phoneNumber("+1234569870")
                .postalCode("T6H 2C3")
                .streetAddress("123 Main St")
                .country("Canada")
                .profilePictureUrl("https://www.google.com")
                .city("NewVille")
                .userId(userId)
                .build();

        Mockito.when(customerRepository.findCustomerByUserId(userId)).thenReturn(mockCustomerExisting);

        Mockito.when(customerRequestMapper.toCustomer(mockCustomerRequest)).thenReturn(mockCustomerExisting);

        Mockito.when(customerRepository.save(mockCustomerExisting)).thenReturn(mockCustomerExisting);

        Mockito.when(customerResponseMapper.toCustomerResponse(mockCustomerExisting)).thenReturn(mockCustomerResponse);

        CustomerResponseModel result = customerService.updateCustomer(mockCustomerRequest, userId);

        assertEquals(mockCustomerExisting.getPostalCode(), result.getPostalCode());
        assertEquals(mockCustomerExisting.getStreetAddress(), result.getStreetAddress());

        assertEquals(mockCustomerExisting.getCountry(), result.getCountry());

        assertEquals(mockCustomerExisting.getCity(), result.getCity());

        assertEquals(mockCustomerExisting.getEmail(), result.getEmail());

        assertEquals(mockCustomerExisting.getFirstName(), result.getFirstName());
        assertEquals(mockCustomerExisting.getLastName(), result.getLastName());

        assertEquals(mockCustomerExisting.getPhoneNumber(), result.getPhoneNumber());
        assertEquals(mockCustomerExisting.getProfilePictureUrl(), result.getProfilePictureUrl());
        assertEquals(mockCustomerExisting.getUserId(), result.getUserId());

    }

//    @Test
//    void deleteCustomer() {
//        // Given
//        String userId = "auth|123456789";
//        Mockito.when(customerRepository.existsByUserId(userId)).thenReturn(true, false);
//        // When
//        customerService.deleteCustomer(userId);
//        // Then
//        Mockito.verify(customerRepository, Mockito.times(2)).existsByUserId(userId);
//        Mockito.verify(customerRepository, Mockito.times(1)).deleteCustomerByUserId(userId);
//    }



//    @Test
//    void deleteFailsForCustomer() {
//
//        String userId = "auth|123456789";
//
//        Mockito.when(customerRepository.existsByUserId(userId)).thenReturn(true);
//
//        assertThrows(InvalidRequestException.class, () -> customerService.deleteCustomer(userId));
//
//    }
    @Test
    void checkIfCustomerExists() {

        String userId = "auth|123456789";

        Mockito.when(customerRepository.existsByUserId(userId)).thenReturn(true);

        boolean result = customerService.checkIfCustomerExists(userId);

        assertTrue(result);
    }


}