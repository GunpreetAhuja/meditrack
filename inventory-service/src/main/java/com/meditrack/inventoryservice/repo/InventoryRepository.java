package com.meditrack.inventoryservice.repo;

import com.meditrack.inventoryservice.model.InventoryItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<InventoryItem, Long> {}
