package br.com.inventory.mechanicalparts.controllers;

import br.com.inventory.mechanicalparts.controllers.interfaces.ICategoryController;
import br.com.inventory.mechanicalparts.dtos.CategoryDTO;
import br.com.inventory.mechanicalparts.entities.Category;
import br.com.inventory.mechanicalparts.services.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class CategoryController extends AbstractController<CategoryService> implements ICategoryController {

    private CategoryService categoryService;

    @Override
    public CategoryDTO insert(@RequestBody CategoryDTO categoryDTO) {
        Category category = convert(categoryDTO, Category.class);
        categoryService.insert(category);
        return convert(category, CategoryDTO.class);
    }

    @Override
    public void update(@PathVariable Long idCategory, @RequestBody CategoryDTO categoryDTO) {
        Category category = convert(categoryDTO, Category.class);
        categoryService.update(idCategory, category);
    }

    @Override
    public List<CategoryDTO> getAllCategories() {
        return convert(categoryService.getAll(), CategoryDTO.class);
    }

    @Override
    public CategoryDTO getById(Long idCategory) {
        return convert(categoryService.getById(idCategory), CategoryDTO.class);
    }

    @Override
    public CategoryDTO findByName(String name) {
        return convert(categoryService.findByName(name), CategoryDTO.class);
    }
}
