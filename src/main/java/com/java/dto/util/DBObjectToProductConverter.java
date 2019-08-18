package com.java.dto.util;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.java.dto.Product;
import com.mongodb.DBObject;

@Component
public class DBObjectToProductConverter implements Converter<DBObject, Product>{

	public Product convert(DBObject object) {
		Product p= new Product();
		p.setProdid(object.get("id").toString());
		p.setTitle(object.get("name").toString());
		p.setPrice(Float.parseFloat(object.get("price").toString()));
		return p;
	}

}
