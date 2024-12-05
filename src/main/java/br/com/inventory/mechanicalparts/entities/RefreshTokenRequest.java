package br.com.inventory.mechanicalparts.entities;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class RefreshTokenRequest {

    @NotBlank
    private String refreshToken;

}
