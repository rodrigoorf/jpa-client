package com.rodrigo.jpaclient.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.sql.Timestamp;
import java.util.List;

import static com.rodrigo.jpaclient.utils.CustomerUtils.*;

@Entity
@Getter
@Setter
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int documentId;
    @NotBlank(message = NAME_EMPTY_MESSAGE)
    @Pattern(regexp = NAME_REGEXP, message = NAME_LETTERS_MESSAGE)
    private String name;
    @NotNull(message = AGE_MESSAGE)
    @Pattern(regexp = AGE_REGEXP, message = AGE_MESSAGE)
    private String age;
    private LocalDate registrationDate;
    private Timestamp lastUpdateInfo;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "customer_addresses",
        joinColumns = @JoinColumn(name = "address_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "customer_id", referencedColumnName = "id"))
    @NotNull(message = ADDRESS_MESSAGE)
    @NotEmpty(message = ADDRESS_MESSAGE)
    @Valid
    private List<Address> addresses;
}
