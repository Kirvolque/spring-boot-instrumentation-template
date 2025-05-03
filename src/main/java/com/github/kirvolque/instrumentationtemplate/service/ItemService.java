package com.github.kirvolque.instrumentationtemplate.service;

import com.github.kirvolque.instrumentationtemplate.dto.Item;
import com.github.kirvolque.instrumentationtemplate.entity.ItemEntity;
import com.github.kirvolque.instrumentationtemplate.repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemService {

    private final ItemRepository repository;

    public ItemService(ItemRepository repository) {
        this.repository = repository;
    }

    public List<Item> getItems() {
        return repository.findAll().stream()
                .map(entity -> new Item(entity.getId(), entity.getName()))
                .collect(Collectors.toList());
    }

    public Item getItemById(Long id) {
        ItemEntity entity = repository.findById(id).orElseThrow();
        return new Item(entity.getId(), entity.getName());
    }

    public Item createItem(String name) {
        ItemEntity entity = new ItemEntity();
        entity.setName(name);
        ItemEntity saved = repository.save(entity);
        return new Item(saved.getId(), saved.getName());
    }
}
