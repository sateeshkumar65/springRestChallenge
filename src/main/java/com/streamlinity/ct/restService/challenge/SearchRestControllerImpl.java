package com.streamlinity.ct.restService.challenge;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.streamlinity.ct.model.Item;

/*
 * This controller needs to expose the following rest endpoints.  You need to fill in the implementation here
 *
 * Required REST Endpoints
 *
 *      /item                       Get all items
 *      /item?category=C            Get all items in category specified by Category shortName
 *      /item/{itemShortName}       Get item that matches the specified Item shortName
 */

@Profile("default")
@RestController
@RequestMapping("/item")
public class SearchRestControllerImpl {
	
	@Autowired
	private SearchSvcInterface searchSvc;
	
	// Get all items
	@GetMapping
	public List<Item> getAllItemsList(){
		return searchSvc.getItems();
	}
	
	// Get all items in category specified by Category shortName
	@GetMapping(params="category")
	public List<Item> getItemsByCategory(@RequestParam("category") String category){
		return searchSvc.getItems(category);
		
	}
	
	// Get all items in category matches the specified Item  shortName
	@GetMapping("/{itemShortName}")
	public List<Item> getItemsByShortName(@PathVariable("itemShortName") String shortName){
		return searchSvc.getItem(shortName);
		
	}
	
	
	
}
