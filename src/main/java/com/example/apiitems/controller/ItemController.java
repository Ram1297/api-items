package com.example.apiitems.controller;

import com.example.apiitems.model.Item;
import com.example.apiitems.service.ItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/items")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping
    public ResponseEntity<?> addItem(@RequestBody Item item) {

        if (item.getName() == null || item.getName().isEmpty()) {
            return ResponseEntity.badRequest().body("Item name is required");
        }

        if (item.getPrice() <= 0) {
            return ResponseEntity.badRequest().body("Price must be greater than 0");
        }

        Item savedItem = itemService.addItem(item);
        return new ResponseEntity<>(savedItem, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Item> getItemById(@PathVariable Long id) {
        return itemService.getItemById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .build());
    }
}