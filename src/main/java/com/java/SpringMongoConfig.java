package com.java;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.convert.MongoTypeMapper;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;

import com.mongodb.MongoClient;

@ComponentScan(basePackages="com.java.dao")
public class SpringMongoConfig {

	@Bean("mongoClient")
	public MongoClient client() {
		return new MongoClient("localhost",27017);
	}
	
	@Bean("mongoDbFactory")
	public MongoDbFactory factory() {
		MongoDbFactory factory= new SimpleMongoDbFactory(client(), "shoppingApp");
		return factory;
			
	}
	//For example there is object A and object B (extending A without any extra fields). In this point while you query for all objects of type A (type A and B should be returned, because B is also A) there is no way to determine object’s type while deserialization. This is known as polymorphism in Object oriented programming.

//To solve that issue Spring Data Mongo Template adds an extra _class field where the canonical name of class is stored.
	//To avoid storing _class field, simply innject DefaultMongoTypeMapper with extra class field name set to null in into MongoTemplate:
	@Bean("mongoTemplate")
	public MongoTemplate getTemplate() {
		/*MongoTypeMapper typeMapper = new DefaultMongoTypeMapper(null);
        MappingMongoConverter converter = new MappingMongoConverter(factory(), new MongoMappingContext());
        converter.setTypeMapper(typeMapper);
		MongoTemplate t= new MongoTemplate(factory(),converter);*/
		MongoTemplate t= new MongoTemplate(factory());
		return t;
	}
}
