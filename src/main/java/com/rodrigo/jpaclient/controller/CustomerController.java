package com.rodrigo.jpaclient.controller;

import com.rodrigo.jpaclient.entity.Customer;
import com.rodrigo.jpaclient.exception.CustomerNotFoundException;
import com.rodrigo.jpaclient.exception.InvalidZipCodeException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@Tag(name = "Customer API")
public interface CustomerController {
    @Operation(summary = "Creates a customer")
    @RequestBody(content = {
            @Content(examples = {
                    @ExampleObject(
                            value =
                                    "{\n" +
                                            "    \"name\": \"John Lennon\",\n" +
                                            "    \"age\": 40,\n" +
                                            "    \"addresses\": [\n" +
                                            "        {\n" +
                                            "            \"zipCode\": \"80420-050\",\n" +
                                            "            \"number\": \"434\"\n" +
                                            "        }\n" +
                                            "    ]\n" +
                                            "}")
            })
    })
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Created",
                            content = {
                                    @Content(mediaType = "application/json", schema = @Schema(implementation = Customer.class), examples = {
                                        @ExampleObject(value = "{\n" +
                                                "  \"documentId\": 3,\n" +
                                                "  \"name\": \"John Lennon\",\n" +
                                                "  \"age\": \"40\",\n" +
                                                "  \"registrationDate\": \"2022-10-17\",\n" +
                                                "  \"lastUpdateInfo\": \"2022-10-17T22:55:55.950+00:00\",\n" +
                                                "  \"addresses\": [\n" +
                                                "    {\n" +
                                                "      \"id\": 3,\n" +
                                                "      \"zipCode\": \"80420-050\",\n" +
                                                "      \"number\": \"434\"\n" +
                                                "    }\n" +
                                                "  ]\n" +
                                                "}")
                                    })
                            }),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Request incomplete",
                            content = {
                                    @Content(mediaType = "application/json", examples = {
                                            @ExampleObject(value = "[\n" +
                                                    "    {\n" +
                                                    "        \"requestId\": \"39424807-75e7-492b-9493-6cd67d1a4325\",\n" +
                                                    "        \"error\": \"addresses[0].zipCode\",\n" +
                                                    "        \"description\": \"Zip code should follow pattern XXXXX-XXX with numbers (e.g.: 80420-050)\"\n" +
                                                    "    }\n" +
                                                    "]")
                                    })
                            }
                    )
            }
    )
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createCustomer(Customer customer);

    @Operation(summary = "Get customers", parameters = {
            @Parameter(in = ParameterIn.QUERY, name = "zipCode", description = "It is possible to filter customers using postal code", example = "64080-960")
    })
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successful request",
                            content = {
                                    @Content(mediaType = "application/json", examples = @ExampleObject(value = "{\n" +
                                            "  \"content\": [\n" +
                                            "    {\n" +
                                            "      \"documentId\": 2,\n" +
                                            "      \"name\": \"José\",\n" +
                                            "      \"age\": \"50\",\n" +
                                            "      \"registrationDate\": \"2022-10-17\",\n" +
                                            "      \"lastUpdateInfo\": \"2022-10-17T21:36:30.593+00:00\",\n" +
                                            "      \"addresses\": [\n" +
                                            "        {\n" +
                                            "          \"id\": 2,\n" +
                                            "          \"zipCode\": \"77777-777\",\n" +
                                            "          \"number\": \"12b\"\n" +
                                            "        }\n" +
                                            "      ]\n" +
                                            "    },\n" +
                                            "    {\n" +
                                            "      \"documentId\": 3,\n" +
                                            "      \"name\": \"John Lennon\",\n" +
                                            "      \"age\": \"40\",\n" +
                                            "      \"registrationDate\": \"2022-10-17\",\n" +
                                            "      \"lastUpdateInfo\": \"2022-10-17T22:55:55.950+00:00\",\n" +
                                            "      \"addresses\": [\n" +
                                            "        {\n" +
                                            "          \"id\": 3,\n" +
                                            "          \"zipCode\": \"80420-050\",\n" +
                                            "          \"number\": \"434\"\n" +
                                            "        }\n" +
                                            "      ]\n" +
                                            "    }\n" +
                                            "  ],\n" +
                                            "  \"pageable\": {\n" +
                                            "    \"sort\": {\n" +
                                            "      \"empty\": true,\n" +
                                            "      \"sorted\": false,\n" +
                                            "      \"unsorted\": true\n" +
                                            "    },\n" +
                                            "    \"offset\": 0,\n" +
                                            "    \"pageNumber\": 0,\n" +
                                            "    \"pageSize\": 20,\n" +
                                            "    \"paged\": true,\n" +
                                            "    \"unpaged\": false\n" +
                                            "  },\n" +
                                            "  \"last\": true,\n" +
                                            "  \"totalPages\": 1,\n" +
                                            "  \"totalElements\": 2,\n" +
                                            "  \"size\": 20,\n" +
                                            "  \"number\": 0,\n" +
                                            "  \"sort\": {\n" +
                                            "    \"empty\": true,\n" +
                                            "    \"sorted\": false,\n" +
                                            "    \"unsorted\": true\n" +
                                            "  },\n" +
                                            "  \"first\": true,\n" +
                                            "  \"numberOfElements\": 2,\n" +
                                            "  \"empty\": false\n" +
                                            "}"))
                            }
                    ),
                    @ApiResponse(responseCode = "400", description = "Incomplete Request",
                    content = {
                            @Content(mediaType = "application/json", examples = {
                                    @ExampleObject(value = "{\n" +
                                            "  \"requestId\": \"b8afdad9-df20-4e37-9da1-b487d3ef511e\",\n" +
                                            "  \"error\": \"Invalid zip code\",\n" +
                                            "  \"description\": \"Zip code should follow pattern XXXXX-XXX with numbers (e.g.: 80420-050)\"\n" +
                                            "}")
                            })
                    })
            }
    )
    @GetMapping
    public ResponseEntity getCustomers(Pageable pageable, Optional<String> zipCode) throws InvalidZipCodeException;

    @Operation(summary = "Get customer by id", parameters = {
            @Parameter(in = ParameterIn.PATH, name = "id", example = "1")
    })
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Request successful",
                    content = {
                            @Content(mediaType = "application/json", examples = {
                                    @ExampleObject(value = "{\n" +
                                            "  \"documentId\": 3,\n" +
                                            "  \"name\": \"John Lennon\",\n" +
                                            "  \"age\": \"40\",\n" +
                                            "  \"registrationDate\": \"2022-10-17\",\n" +
                                            "  \"lastUpdateInfo\": \"2022-10-17T22:55:55.950+00:00\",\n" +
                                            "  \"addresses\": [\n" +
                                            "    {\n" +
                                            "      \"id\": 3,\n" +
                                            "      \"zipCode\": \"80420-050\",\n" +
                                            "      \"number\": \"434\"\n" +
                                            "    }\n" +
                                            "  ]\n" +
                                            "}")
                            })
                    }),
                    @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = {
                            @Content(mediaType = "application/json", examples = {
                                    @ExampleObject(value = "{\n" +
                                            "    \"timestamp\": \"2022-10-17T23:34:25.646+00:00\",\n" +
                                            "    \"status\": 400,\n" +
                                            "    \"error\": \"Bad Request\",\n" +
                                            "    \"path\": \"/customers/asd\"\n" +
                                            "}")
                            })
                    }),
                    @ApiResponse(responseCode = "404", description = "Customer not found",
                    content = {
                            @Content(mediaType = "application/json", examples = {
                                    @ExampleObject(value = "{\n" +
                                            "  \"requestId\": \"7cda31a5-d69e-40ee-884c-ece8b5348755\",\n" +
                                            "  \"error\": \"Customer not Found\",\n" +
                                            "  \"description\": \"No customer was found for the requested id.\"\n" +
                                            "}")
                            })
                    })
            }
    )
    @GetMapping(path = "/{id}")
    public ResponseEntity getCustomerById(int id) throws CustomerNotFoundException;

    @Operation(summary = "Update customer", parameters = {
            @Parameter(in = ParameterIn.PATH, name = "id", example = "1")
    })
    @RequestBody(content = {
            @Content(examples = {
                    @ExampleObject(
                            value =
                                    "{\n" +
                                            "    \"name\": \"Paul McCartney\",\n" +
                                            "    \"age\": 80,\n" +
                                            "    \"addresses\": [\n" +
                                            "        {\n" +
                                            "            \"zipCode\": \"80420-050\",\n" +
                                            "            \"number\": \"337b\"\n" +
                                            "        }\n" +
                                            "    ]\n" +
                                            "}")
            })
    })
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Request successful",
                            content = {
                                    @Content(mediaType = "application/json", examples = {
                                            @ExampleObject(value = "{\n" +
                                                    "  \"documentId\": 3,\n" +
                                                    "  \"name\": \"Paul McCartney\",\n" +
                                                    "  \"age\": \"80\",\n" +
                                                    "  \"registrationDate\": \"2022-10-17\",\n" +
                                                    "  \"lastUpdateInfo\": \"2022-10-17T22:55:55.950+00:00\",\n" +
                                                    "  \"addresses\": [\n" +
                                                    "    {\n" +
                                                    "      \"id\": 3,\n" +
                                                    "      \"zipCode\": \"80420-050\",\n" +
                                                    "      \"number\": \"337b\"\n" +
                                                    "    }\n" +
                                                    "  ]\n" +
                                                    "}")
                                    })
                            }),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Request incomplete/bad request",
                            content = {
                                    @Content(mediaType = "application/json", examples = {
                                            @ExampleObject(value = "[\n" +
                                                    "    {\n" +
                                                    "        \"requestId\": \"39424807-75e7-492b-9493-6cd67d1a4325\",\n" +
                                                    "        \"error\": \"addresses[0].zipCode\",\n" +
                                                    "        \"description\": \"Zip code should follow pattern XXXXX-XXX with numbers (e.g.: 80420-050)\"\n" +
                                                    "    }\n" +
                                                    "]"),
                                    })
                            }
                    ),
                    @ApiResponse(responseCode = "404", description = "Customer not found",
                            content = {
                                    @Content(mediaType = "application/json", examples = {
                                            @ExampleObject(value = "{\n" +
                                                    "  \"requestId\": \"7cda31a5-d69e-40ee-884c-ece8b5348755\",\n" +
                                                    "  \"error\": \"Customer not Found\",\n" +
                                                    "  \"description\": \"No customer was found for the requested id.\"\n" +
                                                    "}")
                                    })
                            })
            }
    )
    @PutMapping(path = "/{id}")
    public ResponseEntity updateCustomer(int id, Customer customer) throws CustomerNotFoundException;

    @Operation(summary = "Delete customer", parameters = {
            @Parameter(in = ParameterIn.PATH, name = "id", example = "1")
    })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Customer deleted"),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = {
                            @Content(mediaType = "application/json", examples = {
                                    @ExampleObject(value = "{\n" +
                                            "    \"timestamp\": \"2022-10-17T23:34:25.646+00:00\",\n" +
                                            "    \"status\": 400,\n" +
                                            "    \"error\": \"Bad Request\",\n" +
                                            "    \"path\": \"/customers/asd\"\n" +
                                            "}")
                            })
                    }),
            @ApiResponse(responseCode = "404", description = "Customer not found",
                    content = {
                            @Content(mediaType = "application/json", examples = {
                                    @ExampleObject(value = "{\n" +
                                            "  \"requestId\": \"7cda31a5-d69e-40ee-884c-ece8b5348755\",\n" +
                                            "  \"error\": \"Customer not Found\",\n" +
                                            "  \"description\": \"No customer was found for the requested id.\"\n" +
                                            "}")
                            })
                    })
    })
    @DeleteMapping(path = "/{id}")
    public ResponseEntity deleteCustomer(int id) throws CustomerNotFoundException;
}