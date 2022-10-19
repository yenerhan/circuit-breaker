package com.yener.circuitbreaker.service;

import com.yener.circuitbreaker.client.GenericRestClient;
import com.yener.circuitbreaker.model.GetAllCustomerResponseDTO;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

@Service
public class CircuitService {

    private static final String SERVICE_NAME = "test-service";
    private static final String CUSTOMER_SERVICE_URL = "http://localhost:8080/v1/customer/getAllCustomer";


    @CircuitBreaker(name = SERVICE_NAME, fallbackMethod = "getDefaultCustomer")
    public GetAllCustomerResponseDTO getAllCustomer() throws Exception {
        ResponseErrorHandler responseHandler = new ResponseErrorHandler() {

            @Override
            public boolean hasError(ClientHttpResponse response) throws IOException {

                if (response.getStatusCode() != HttpStatus.OK) {
                    System.out.println(response.getStatusText());
                }
                return response.getStatusCode() == HttpStatus.OK ? false : true;
            }

            @Override
            public void handleError(ClientHttpResponse response) throws IOException {
                // TODO Auto-generated method stub

            }
        };

        GetAllCustomerResponseDTO responseDTO = new GenericRestClient<String, GetAllCustomerResponseDTO>().execute(CUSTOMER_SERVICE_URL, HttpMethod.GET, " ", responseHandler, GetAllCustomerResponseDTO.class);
        return responseDTO;
    }

    public GetAllCustomerResponseDTO getDefaultCustomer(Exception e) {
        return new GetAllCustomerResponseDTO();
    }
}
