package br.com.inventory.mechanicalparts.controllers.interfaces;

import br.com.inventory.mechanicalparts.dtos.ProductDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/product")
public interface IProductController {

    @PostMapping
    ProductDTO insert(@RequestBody ProductDTO productDTO);

    @PutMapping(value = "/{idProduct}")
    void update(@PathVariable Long idProduct, @RequestBody ProductDTO productDTO);

    @GetMapping
    List<ProductDTO> getAll();

    @GetMapping(value = "/{idProduct}")
    ProductDTO getById(@PathVariable("idProduct") Long idProduct);
}
