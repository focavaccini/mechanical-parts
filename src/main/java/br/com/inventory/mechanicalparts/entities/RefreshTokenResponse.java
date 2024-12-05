package br.com.inventory.mechanicalparts.entities;

import br.com.inventory.mechanicalparts.controllers.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class RefreshTokenResponse extends AbstractEntity<Long> implements Serializable {

    private String token;
    private String refreshToken;
    private String expiredAt;

    public RefreshTokenResponse(String token, String refreshToken, String expiredAt) {
        this.token = token;
        this.refreshToken = refreshToken;
        this.expiredAt = expiredAt;
    }

}
