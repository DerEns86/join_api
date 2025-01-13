package dev.ens.join_backend.services.impl;

import dev.ens.join_backend.model.Category;
import dev.ens.join_backend.model.enums.UpdateMessage;
import dev.ens.join_backend.model.User;
import dev.ens.join_backend.repository.CategoryRepository;
import dev.ens.join_backend.repository.UserRepository;
import dev.ens.join_backend.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getCategoryByName(String name) {
        return categoryRepository.findByName(name)
                .orElseThrow(()-> new NoSuchElementException("Category not found with name: " + name));
    }

    @Override
    public Category saveCategory(Category category, String username) {
        User user = userRepository.findByUserName(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        category.setCreatedBy(user.getUserId());
        category.setUsed(false);
        category.setUpdatedBy(user.getUserId());
        category.setUpdatedAt(LocalDate.now());
        category.setUpdateMessage(UpdateMessage.CREATED);
        return categoryRepository.save(category);
    }
}
