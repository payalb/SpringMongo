package com.java.dto.util;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.java.dto.Product;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

@Component
public class ProductToDbObjectConverter implements Converter<Product, DBObject>{

	public DBObject convert(Product prod) {
		DBObject object= new BasicDBObject();
		object.put("id", prod.getProdid());
		object.put("price", prod.getPrice());
		object.put("name", prod.getTitle());
		return object;
	}

}
