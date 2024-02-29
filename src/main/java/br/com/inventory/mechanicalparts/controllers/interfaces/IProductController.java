package br.com.inventory.mechanicalparts.controllers.interfaces;

import br.com.inventory.mechanicalparts.dtos.ProductDTO;
import br.com.inventory.mechanicalparts.entities.ProductImages;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequestMapping("/product")
public interface IProductController {

    @PostMapping
    ProductDTO insert(@RequestBody ProductDTO productDTO);

    @PutMapping(value = "/{idProduct}")
    void update(@PathVariable Long idProduct, @RequestBody ProductDTO productDTO);

    @PostMapping(value = "/{idProduct}")
    void insertImage(@PathVariable Long idProduct, @RequestParam(name = "file") MultipartFile multipartFile);

    @GetMapping
    List<ProductDTO> getAll();

    @GetMapping(value = "/{idProduct}")
    ProductDTO getById(@PathVariable("idProduct") Long idProduct);

    @GetMapping(value = "all-images/{idProduct}")
    List<ProductImages> getAllByProduct(@PathVariable("idProduct") Long idProduct);
}
