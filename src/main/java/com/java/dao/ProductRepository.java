package com.java.dao;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.java.dto.Product;

@Repository
public class ProductRepository {

	@Autowired MongoTemplate template;
	
	
	public List<Product> getAllProducts(){
		return template.findAll(Product.class);
	}
	
	public Object saveProduct(Product p) {
		p.setProdid(template.count(new Query(),Product.class));
		return template.save(p).getProdid();
	}
	
	
}
