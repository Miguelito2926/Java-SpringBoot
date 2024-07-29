package com.ednaldo.ecommerce.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CredenciaisDTO {

    private String login;
    private String senha;
}
