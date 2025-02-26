package dev.ens.join_backend.services;

import dev.ens.join_backend.model.Category;

import java.util.List;

public interface CategoryService {

    List<Category> getAllCategories();

    Category getCategoryByName(String name);

    Category saveCategory(Category category, String username);
}
