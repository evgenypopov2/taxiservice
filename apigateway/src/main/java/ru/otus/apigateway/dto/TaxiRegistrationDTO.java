package ru.otus.apigateway.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class TaxiRegistrationDTO extends ClientRegistrationDTO {
    private String carVendor;
    private String carModel;
    private String carNumber;
    private String carColor;
}
