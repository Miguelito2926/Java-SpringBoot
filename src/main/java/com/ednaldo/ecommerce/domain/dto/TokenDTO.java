package com.ednaldo.ecommerce.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TokenDTO {

    private String login;
    private String token;
}
