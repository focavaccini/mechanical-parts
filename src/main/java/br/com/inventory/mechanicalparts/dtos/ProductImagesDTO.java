package br.com.inventory.mechanicalparts.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
public class ProductImagesDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;

    private String path;

    @JsonIgnore
    private ProductDTO product;

    private LocalDateTime registrationDate;

    private LocalDateTime updateDate;
}
