package dev.ens.join_backend.controller;

import dev.ens.join_backend.model.Category;
import dev.ens.join_backend.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping()
    public ResponseEntity<List<Category>> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    @GetMapping("/{categoryName}")
    public ResponseEntity<Category> getCategoryByName(@PathVariable String categoryName) {
        return ResponseEntity.ok(categoryService.getCategoryByName(categoryName));
    }

    @PostMapping("/add")
    public ResponseEntity<Category> addCategory(
            @RequestBody Category category,
            @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(categoryService.saveCategory(category, userDetails.getUsername()));
    }
}
