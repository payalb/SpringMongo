package com.java;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.query.TextCriteria;

import com.java.dao.ProductRepositoryOne;
import com.java.dto.Product;

public class Client {

	public static void main(String[] args) {

		ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringMongoConfig.class);
		ProductRepositoryOne rep = ctx.getBean(ProductRepositoryOne.class);
		//System.out.println(rep.findAllByOrderByScoreDesc(TextCriteria.forDefaultLanguage().matching("Refurbished")));
		//System.out.println(rep.findAllByTitleLike("Refurbished"));
		Product p = new Product("oppo phone", "A9 model, grey color");
		p.setPrice(7667.22);
		
		System.out.println(rep.save(p));
		//rep.updateOrInsertProduct();
		/*for (Product p1 : rep.getAllProducts()) {
				rep.deleteProduct(p1.getProdid());
		}
		System.out.println(rep.getAllProducts());*/
		/*Product p = new Product("dettol", "an anti-fungal medicine");
		System.out.println(rep.saveProduct(p));
		rep.updateProduct(p);

		System.out.println(rep.getAllProducts());*/
		/*
		System.out.println(rep.getAllProducts());*/
	}

}
