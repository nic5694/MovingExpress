package com.example.backend.customersubdomain.presentationlayer;

import com.example.backend.customersubdomain.datalayer.Customer;
import com.example.backend.customersubdomain.datalayer.CustomerRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureWebClient
@AutoConfigureMockMvc//(addFilters = false)
@ActiveProfiles("test")
class CustomerControllerIntegrationTest {
    private final String BASE_URI_CUSTOMERS = "/api/v1/movingexpress/customers";
    @Autowired
    WebTestClient webTestClient;
    @Autowired
    MockMvc mockMvc;
    @Autowired
    CustomerRepository customerRepository;

    Customer testCustomer;

    @BeforeEach
    public void setUp(){
        testCustomer = new Customer();
        testCustomer.setUserId("auth0|123456789");
        testCustomer.setFirstName("Alice");
        // Set other properties of the customer as needed

        testCustomer = customerRepository.save(testCustomer);
    }
    @Test
    void getCustomerByUserIdWithSimpleCheck() throws Exception {
       //Arrange
        mockMvc.perform(get(BASE_URI_CUSTOMERS + "?simpleCheck=true")
                        .with(SecurityMockMvcRequestPostProcessors.oidcLogin().idToken(i -> i.subject(testCustomer.getUserId())))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getCustomerByUserIdWithSimpleCheckNotFound() throws Exception {
        mockMvc.perform(get(BASE_URI_CUSTOMERS + "?simpleCheck=true")
                        .with(SecurityMockMvcRequestPostProcessors.oidcLogin().idToken(i -> i.subject("oauth|notuser")))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void getCustomerByUserId() throws Exception {
        mockMvc.perform(get(BASE_URI_CUSTOMERS)
                        .with(SecurityMockMvcRequestPostProcessors.oidcLogin().idToken(i -> i.subject(testCustomer.getUserId())))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("Alice"));
    }

//    @Test
//    @Transactional
//    void deleteCustomer() throws Exception {
//        mockMvc.perform(delete(BASE_URI_CUSTOMERS)
//                .with(SecurityMockMvcRequestPostProcessors.oidcLogin().idToken(i -> i.subject(testCustomer.getUserId())).authorities(new SimpleGrantedAuthority("ShipmentOwner")))
//                .with(csrf())
//                .accept(MediaType.APPLICATION_JSON));
//        //check if the customer is deleted
//        mockMvc.perform(get(BASE_URI_CUSTOMERS)
//                        .with(SecurityMockMvcRequestPostProcessors.oidcLogin().idToken(i -> i.subject(testCustomer.getUserId())))
//                        .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isNotFound());
//    }

    @Test
    void createCustomer() throws Exception {
        CustomerRequestModel customerRequestModel = CustomerRequestModel.builder()
                .firstName("testName")
                .email("email@gmail.com")
                .profilePictureUrl("https://www.google.com")
                .userId("auth0|123456989")
                .build();

        ObjectMapper objectMapper = new ObjectMapper();
        String customerRequestModelJson = objectMapper.writeValueAsString(customerRequestModel);

        mockMvc.perform(post(BASE_URI_CUSTOMERS)
                        .with(SecurityMockMvcRequestPostProcessors.oidcLogin().idToken(i -> i.subject(customerRequestModel.getUserId())).authorities(new SimpleGrantedAuthority("ShipmentOwner")))
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(customerRequestModelJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName").value(customerRequestModel.getFirstName()))
                .andExpect(jsonPath("$.userId").value(customerRequestModel.getUserId()))
                .andExpect(jsonPath("$.email").value(customerRequestModel.getEmail()))
                .andExpect(jsonPath("$.profilePictureUrl").value(customerRequestModel.getProfilePictureUrl()));

        mockMvc.perform(get(BASE_URI_CUSTOMERS)
                        .with(SecurityMockMvcRequestPostProcessors.oidcLogin().idToken(i -> i.subject(customerRequestModel.getUserId())).authorities(new SimpleGrantedAuthority("ShipmentOwner")))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value(customerRequestModel.getFirstName()))
                .andExpect(jsonPath("$.userId").value(customerRequestModel.getUserId()))
                .andExpect(jsonPath("$.email").value(customerRequestModel.getEmail()))
                .andExpect(jsonPath("$.profilePictureUrl").value(customerRequestModel.getProfilePictureUrl()));
    }

    @Test
    void postWithInvalidBody() throws Exception {
        CustomerRequestModel customerRequestModel = CustomerRequestModel.builder()
                .userId("auth0|123456989")
                .build();

        ObjectMapper objectMapper = new ObjectMapper();
        String customerRequestModelJson = objectMapper.writeValueAsString(customerRequestModel);

        mockMvc.perform(post(BASE_URI_CUSTOMERS)
                        .with(SecurityMockMvcRequestPostProcessors.oidcLogin().idToken(i -> i.subject(customerRequestModel.getUserId())))
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(customerRequestModelJson))
                .andExpect(status().isBadRequest());

    }


    @Test
    void updateCustomer() throws Exception {
        CustomerRequestModel customerRequestModel = CustomerRequestModel.builder()
                .firstName("testName")
                .email("email@gmail.com")
                .profilePictureUrl("https://www.google.com")
                .userId("auth0|123456989")
                .build();

        ObjectMapper objectMapper = new ObjectMapper();
        String customerRequestModelJson = objectMapper.writeValueAsString(customerRequestModel);

        mockMvc.perform(get(BASE_URI_CUSTOMERS)
                        .with(SecurityMockMvcRequestPostProcessors.oidcLogin().idToken(i -> i.subject(testCustomer.getUserId())))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value(testCustomer.getFirstName()));

        mockMvc.perform(put(BASE_URI_CUSTOMERS)
                        .with(SecurityMockMvcRequestPostProcessors.oidcLogin().idToken(i -> i.subject(testCustomer.getUserId())).authorities(new SimpleGrantedAuthority("Customer")))
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(customerRequestModelJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value(customerRequestModel.getFirstName()))
                //userId should never change
                .andExpect(jsonPath("$.userId").value(testCustomer.getUserId()))
                .andExpect(jsonPath("$.email").value(customerRequestModel.getEmail()))
                .andExpect(jsonPath("$.profilePictureUrl").value(customerRequestModel.getProfilePictureUrl()));


        mockMvc.perform(get(BASE_URI_CUSTOMERS)
                        .with(SecurityMockMvcRequestPostProcessors.oidcLogin().idToken(i -> i.subject(testCustomer.getUserId())))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value(customerRequestModel.getFirstName()))
                .andExpect(jsonPath("$.userId").value(testCustomer.getUserId()))
                .andExpect(jsonPath("$.email").value(customerRequestModel.getEmail()))
                .andExpect(jsonPath("$.profilePictureUrl").value(customerRequestModel.getProfilePictureUrl()));
    }


    @Test
    void updateAlmostNoInfoForCustomer() throws Exception {
        CustomerRequestModel customerRequestModel = CustomerRequestModel.builder()
                .firstName("testName")
                .email("email@gmail.com")
                .profilePictureUrl("https://www.google.com")
                .userId("auth0|123456989")
                .build();

        ObjectMapper objectMapper = new ObjectMapper();
        String customerRequestModelJson = objectMapper.writeValueAsString(customerRequestModel);
        mockMvc.perform(put(BASE_URI_CUSTOMERS)
                        .with(csrf())
                        .with(SecurityMockMvcRequestPostProcessors.oidcLogin().idToken(i -> {
                            i.subject(testCustomer.getUserId());

                        }).authorities(new SimpleGrantedAuthority("ShipmentOwner")))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(customerRequestModelJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value(customerRequestModel.getFirstName()))
                .andExpect(jsonPath("$.userId").value(testCustomer.getUserId()))
                .andExpect(jsonPath("$.email").value(customerRequestModel.getEmail()))
                .andExpect(jsonPath("$.profilePictureUrl").value(customerRequestModel.getProfilePictureUrl()));
    }

}