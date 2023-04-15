package br.com.inventory.mechanicalparts.controllers.interfaces;

import br.com.inventory.mechanicalparts.dtos.ProductDTO;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/product")
public interface IProductController {

    @PostMapping
    ProductDTO insert(@RequestBody ProductDTO productDTO);

    @PutMapping(value = "/{idProduct}")
    void update(@PathVariable Long idProduct, @RequestBody ProductDTO productDTO);
}
