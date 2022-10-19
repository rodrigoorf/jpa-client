package com.rodrigo.jpaclient.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.List;

import static com.rodrigo.jpaclient.utils.AddressUtils.*;

@Entity
@Data
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Pattern(regexp = ZIP_CODE_REGEXP, message = ZIP_CODE_PATTERN_MESSAGE)
    @NotBlank(message = ZIP_CODE_EMPTY_MESSAGE)
    private String zipCode;
    @Pattern(regexp = NUMBER_REGEXP, message = NUMBER_MESSAGE)
    private String number;
    @ManyToMany(mappedBy = "addresses")
    @JsonBackReference
    @Schema(hidden = true)
    private List<Customer> customers;
}
