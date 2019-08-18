package com.java.dao;

import java.util.List;

import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.java.dto.Product;

public interface ProductRepositoryOne extends MongoRepository<Product, String>{

	public List<Product> findAllByTitleLike(String title);
	
	public List<Product> findAllByOrderByScoreDesc(TextCriteria criteria);
	
	//just return title and price
	@Query(value="{'Mrp': {$gt: ?0}}",fields="{'title':1, 'Mrp':1}")
	public List<Product> findProductsByQuery(float Mrp);
}
