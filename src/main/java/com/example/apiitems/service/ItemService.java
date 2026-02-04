package com.example.apiitems.service;

import com.example.apiitems.model.Item;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

	
	@Service
	public class ItemService {

	    private List<Item> items = new ArrayList<>();
	    private Long idCounter = 1L;

	    public Item addItem(Item item) {
	        item.setId(idCounter++);
	        items.add(item);
	        return item;
	    }

	    public Optional<Item> getItemById(Long id) {
	        return items.stream()
	                .filter(item -> item.getId().equals(id))
	                .findFirst();
	    }

}
