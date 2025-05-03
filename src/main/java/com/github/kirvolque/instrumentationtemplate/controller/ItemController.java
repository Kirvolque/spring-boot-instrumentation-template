package com.github.kirvolque.instrumentationtemplate.controller;

import com.github.kirvolque.instrumentationtemplate.dto.Item;
import com.github.kirvolque.instrumentationtemplate.service.ItemService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/items")
public class ItemController {

    private final ItemService service;

    public ItemController(ItemService service) {
        this.service = service;
    }

    @GetMapping
    public List<Item> getItems() {
        return service.getItems();
    }

    @GetMapping("/{id}")
    public Item getItemById(@PathVariable Long id) {
        return service.getItemById(id);
    }

    @PostMapping
    public Item createItem(@RequestBody String name) {
        return service.createItem(name);
    }
}
