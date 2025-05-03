package com.github.kirvolque.instrumentationtemplate.repository;

import com.github.kirvolque.instrumentationtemplate.entity.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<ItemEntity, Long> {
}
