package com.java.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.java.dto.Product;
import com.mongodb.client.result.UpdateResult;

@Repository
public class ProductRepository {

	@Autowired
	MongoTemplate template;

	public List<Product> getAllProducts() {
		return template.findAll(Product.class);
	}
	public Optional<Product> getProductById(Object pid) {
		return Optional.ofNullable(template.findById(pid, Product.class));
	}

	public Object saveProduct(Product p) {
		/* p.setProdid(template.count(new Query(), Product.class)+""); */
		// save works like insert or update.
		return template.insert(p).getProdid();
	}

	public long updateOrInsertProduct() {
		Update u = new Update();
		u.set("Mrp", Math.random() * 1000);
		UpdateResult r = template.upsert(Query.query(Criteria.where("title").is("Cooler sonata").and("description")
				.is("A brand new cooler from Sonata with mulit-speed fan")), u, Product.class);
		System.out.println(r.getModifiedCount());
		int insert=r.getUpsertedId()!=null?1:0;
		return r.getModifiedCount()+insert;
	}

	public long updateProduct() {

		Update u = Update.update("Mrp", Math.random());
		// updateFirst: to update first match
		// updateMulti: to update all matched docs.
		// new Query(): to give all documents.
		UpdateResult r = template.updateMulti(new Query(), u, Product.class);
		System.out.println(r.getModifiedCount());
		return r.getModifiedCount();
	}

	public long updateProduct(Product p) {
		p.setPrice(Math.random());
		Product p1 = template.save(p);
		return p1 != null ? 1l : 0;
	}

	public void deleteProduct(Object pId) {
		template.remove(Query.query(Criteria.where("_id").is(pId)), Product.class);
	}
/*	public void deleteProduct(int pId) {
		template.remove(Query.query(Criteria.where("_id").is(pId)), Product.class);
	}
	public void deleteProduct(double pId) {
		template.remove(Query.query(Criteria.where("_id").is(pId)), Product.class);
	}
	public void deleteProduct(BigDecimal pId) {
		template.remove(Query.query(Criteria.where("_id").is(pId)), Product.class);
	}*/
}
