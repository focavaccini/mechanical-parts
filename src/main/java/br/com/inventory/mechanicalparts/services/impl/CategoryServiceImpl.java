package br.com.inventory.mechanicalparts.services.impl;

import br.com.inventory.mechanicalparts.Utils.Util;
import br.com.inventory.mechanicalparts.entities.Category;
import br.com.inventory.mechanicalparts.exceptions.ObjectNotFound;
import br.com.inventory.mechanicalparts.exceptions.UniqueException;
import br.com.inventory.mechanicalparts.repositories.CategoryRepository;
import br.com.inventory.mechanicalparts.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category insert(Category category) {
        onPrepareInsertOrUpdate(category);
        return categoryRepository.save(category);
    }

    @Override
    public void update(Long idCategory, Category category) {
        Category categoryManaged = getById(idCategory);

        categoryManaged.setName(Util.nvl(category.getName(), categoryManaged.getName()));
        categoryManaged.setCode(Util.nvl(category.getCode(), categoryManaged.getCode()));

        onPrepareInsertOrUpdate(categoryManaged);
        categoryRepository.save(categoryManaged);
    }

    @Override
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getById(Long idCategory) {
        Optional<Category> category = categoryRepository.findById(idCategory);
        return category.orElseThrow(() -> new ObjectNotFound("Object not found! Id " + idCategory + ", Type: " + Category.class.getName()));
    }

    @Override
    public Category findByName(String name) {
        return categoryRepository.findByName(name);
    }

    @Override
    public Category findByCode(String code) {
        return categoryRepository.findByCode(code);
    }

    private void onPrepareInsertOrUpdate(Category category) {
        checkIfNameAlreadyExists(category);
        checkIfCodeAlreadyExists(category);
    }

    private void checkIfNameAlreadyExists(Category category) {
        Category categoryManaged = findByName(category.getName());

        if (!Util.isEmpty(categoryManaged) && !categoryManaged.getId().equals(category.getId())) {
            throw new UniqueException("Já existe uma categoria com o nome informado");
        }
    }

    private void checkIfCodeAlreadyExists(Category category) {
        Category categoryManaged = findByCode(category.getCode());

        if (!Util.isEmpty(categoryManaged) && !categoryManaged.getId().equals(category.getId())) {
            throw new UniqueException("Já existe uma categoria com o código informado");
        }
    }

    @Override
    public JpaRepository<Category, Long> getRepository() {
        return categoryRepository;
    }
}
