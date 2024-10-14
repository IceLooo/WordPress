package kz.zhanayev.spring.controller;

import io.swagger.v3.oas.annotations.Operation;

import io.swagger.v3.oas.annotations.tags.Tag;
import kz.zhanayev.spring.Model.Category;
import kz.zhanayev.spring.Model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/api/wordpress")
public class WordPressController {

    private Map<Long, User> users = new HashMap<>();
    private Map<Long, Category> categories = new HashMap<>();
    private AtomicLong userCounter = new AtomicLong();
    private AtomicLong categoryCounter = new AtomicLong();

    // ------ User Management ------

    @Operation(summary = "Get all users", description = "Fetches all users")
    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(new ArrayList<>(users.values()));
    }

    @Operation(summary = "Get user by ID", description = "Fetches a user by their ID")
    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = users.get(id);
        if (user != null) {
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Create a new user", description = "Creates a new user")
    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        long newId = userCounter.incrementAndGet();
        user.setId(newId);
        users.put(newId, user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @Operation(summary = "Update a user", description = "Updates an existing user by ID")
    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
        User existingUser = users.get(id);
        if (existingUser != null) {
            existingUser.setUsername(updatedUser.getUsername());
            existingUser.setEmail(updatedUser.getEmail());
            return ResponseEntity.ok(existingUser);
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Delete a user", description = "Deletes a user by ID")
    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        if (users.remove(id) != null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    // ------ Category Management ------

    @Operation(summary = "Get all categories", description = "Fetches all categories")
    @GetMapping("/categories")
    public ResponseEntity<List<Category>> getAllCategories() {
        return ResponseEntity.ok(new ArrayList<>(categories.values()));
    }

    @Operation(summary = "Get category by ID", description = "Fetches a category by its ID")
    @GetMapping("/categories/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id) {
        Category category = categories.get(id);
        if (category != null) {
            return ResponseEntity.ok(category);
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Create a new category", description = "Creates a new category")
    @PostMapping("/categories")
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        long newId = categoryCounter.incrementAndGet();
        category.setId(newId);
        categories.put(newId, category);
        return ResponseEntity.status(HttpStatus.CREATED).body(category);
    }

    @Operation(summary = "Update a category", description = "Updates an existing category by ID")
    @PutMapping("/categories/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable Long id, @RequestBody Category updatedCategory) {
        Category existingCategory = categories.get(id);
        if (existingCategory != null) {
            existingCategory.setName(updatedCategory.getName());
            existingCategory.setDescription(updatedCategory.getDescription());
            return ResponseEntity.ok(existingCategory);
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Delete a category", description = "Deletes a category by ID")
    @DeleteMapping("/categories/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        if (categories.remove(id) != null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}

