package com.streamlinity.ct.restService.challenge;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.streamlinity.ct.model.Item;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/*
 * Provide your implementation of the SearchSvcImpl here.
 * Also annotate your methods with Rest end point wrappers as required in the problem statement
 *
 * You can create any auxiliary classes or interfaces in this package if you need them.
 *
 * Do NOT add annotations as a Bean or Component or Service.   This is being handled in the custom Config class
 * PriceAdjustConfiguration
 */

public class SearchSvcImpl implements SearchSvcInterface {
	
	String itemPriceJsonFileName = null;
	File itemPriceJsonFile = null;
	List<Item> itemsList= null;
	
    @Override
    public void init(String itemPriceJsonFileName) {
    	this.itemPriceJsonFileName=itemPriceJsonFileName;
    }

    @Override
    public void init(File itemPriceJsonFile) {
    	this.itemPriceJsonFile= itemPriceJsonFile;
    	
    	ObjectMapper objectMapper= new ObjectMapper();
    	
    	try {
			Item[] itemsArr =objectMapper.readValue(itemPriceJsonFile, Item[].class);
			itemsList=Arrays.asList(itemsArr);
			
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	

    }

    @Override
    public List<Item> getItems() {
        return this.itemsList;
    }

    @Override
    public List<Item> getItems(String category) {
       return itemsList.parallelStream().filter(item -> item.getCategory_short_name().equals(category)).collect(Collectors.toList());
    }

    @Override
    public List<Item> getItem(String itemShortName) {
        return itemsList.parallelStream().filter(item -> item.getShort_name().equals(itemShortName)).collect(Collectors.toList());
    }
}
