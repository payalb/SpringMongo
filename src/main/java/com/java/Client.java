package com.java;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.java.dao.ProductRepository;
import com.java.dto.Product;

public class Client {

	
	public static void main(String[] args) {
		
		ApplicationContext ctx= new AnnotationConfigApplicationContext(SpringMongoConfig.class);
		ProductRepository rep=ctx.getBean(ProductRepository.class);
		System.out.println(rep.getAllProducts());
		System.out.println(rep.saveProduct(new Product("dettol", " an anti-fungal medicine")));
		System.out.println(rep.getAllProducts());
	}

}
