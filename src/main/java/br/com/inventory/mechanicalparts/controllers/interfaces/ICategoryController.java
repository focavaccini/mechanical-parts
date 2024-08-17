package br.com.inventory.mechanicalparts.controllers.interfaces;

import br.com.inventory.mechanicalparts.dtos.CategoryDTO;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/category")
public interface ICategoryController {

    @PostMapping
    CategoryDTO insert(@RequestBody CategoryDTO categoryDTO);

    @PutMapping(value = "/{idCategory}")
    void update(@PathVariable Long idCategory, @RequestBody CategoryDTO categoryDTO);

    @GetMapping
    List<CategoryDTO> getAllCategories();

    @GetMapping(value = "/{idCategory}")
    CategoryDTO getById(@PathVariable Long idCategory);

    @GetMapping(value = "/name")
    CategoryDTO findByName(@Param("name") String name);
}
