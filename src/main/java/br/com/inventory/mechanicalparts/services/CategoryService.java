package br.com.inventory.mechanicalparts.services;

import br.com.inventory.mechanicalparts.controllers.interfaces.IAbstractService;
import br.com.inventory.mechanicalparts.entities.Category;

import java.util.List;

public interface CategoryService extends IAbstractService<Category, Long> {

    Category insert(Category category);

    void update(Long idCategory, Category category);

    List<Category> getAll();

    Category getById(Long idCategory);

    Category findByName(String name);

    Category findByCode(String code);

}
