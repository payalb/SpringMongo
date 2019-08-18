package com.java;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.java.dao.ProductRepository;
import com.java.dto.Product;

public class Client {

	public static void main(String[] args) {

		ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringMongoConfig.class);
		ProductRepository rep = ctx.getBean(ProductRepository.class);
		System.out.println(rep.getProductById(1));
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
