package com.java;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.convert.CustomConversions;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.data.repository.init.Jackson2RepositoryPopulatorFactoryBean;

import com.java.dto.util.DBObjectToProductConverter;
import com.java.dto.util.ProductToDbObjectConverter;
import com.mongodb.MongoClient;

@ComponentScan(basePackages = "com.java")
@EnableMongoRepositories(basePackages = "com.java.dao")
public class SpringMongoConfig {

	// At start of application, this file would be loaded.

	@Autowired
	ProductToDbObjectConverter converter;
	
	@Autowired DBObjectToProductConverter converter1;

	@Bean
	@Profile("!prod")
	public Jackson2RepositoryPopulatorFactoryBean repositoryPopulator() {
		Jackson2RepositoryPopulatorFactoryBean factory = new Jackson2RepositoryPopulatorFactoryBean();
		factory.setResources(new Resource[] { new ClassPathResource("product1.json") });
		return factory;
	}

	@Bean("mongoClient")
	public MongoClient client() {
		return new MongoClient("localhost", 27017);
	}

	@Bean("mongoDbFactory")
	public MongoDbFactory factory() {
		MongoDbFactory factory = new SimpleMongoDbFactory(client(), "shoppingApp");
		return factory;

	}
	// For example there is object A and object B (extending A without any extra
	// fields). In this point while you query for all objects of type A (type A and
	// B should be returned, because B is also A) there is no way to determine
	// object’s type while deserialization. This is known as polymorphism in Object
	// oriented programming.

//To solve that issue Spring Data Mongo Template adds an extra _class field where the canonical name of class is stored.
	// To avoid storing _class field, simply innject DefaultMongoTypeMapper with
	// extra class field name set to null in into MongoTemplate:
	@Bean("mongoTemplate")
	public MongoTemplate getTemplate() {
		/*
		 * MongoTypeMapper typeMapper = new DefaultMongoTypeMapper(null);
		 * MappingMongoConverter converter = new MappingMongoConverter(factory(), new
		 * MongoMappingContext()); converter.setTypeMapper(typeMapper); MongoTemplate t=
		 * new MongoTemplate(factory(),converter);
		 */
		MongoTemplate mongoTemplate = new MongoTemplate(factory());
		MappingMongoConverter mongoMapping = (MappingMongoConverter) mongoTemplate.getConverter();
		mongoMapping.setCustomConversions(new CustomConversions(Arrays.asList(converter,converter1))); // tell mongodb to use the
																							// custom converters
		mongoMapping.afterPropertiesSet();
		return mongoTemplate;
	}
}
