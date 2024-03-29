package com.nayab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nayab.datamodel.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
	public Item findByName(String name);
}
