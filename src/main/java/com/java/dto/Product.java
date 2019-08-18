package com.java.dto;

import org.springframework.data.annotation.Id;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.TextScore;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.java.dto.util.PointDeserializer;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
//If we don't use @Document, will store collection with name product.
@Document(collection="products")
@NoArgsConstructor
public class Product {
	//@Autowired
//	MongoTemplate template;
	@Id
	//spring data can automatically convert between string and objectid
	private String prodid;
	//if prodid null, mogodb will autopopulate the value.
	@TextIndexed(weight=1)
	private String description;
	@TextIndexed(weight=5)
	private String title;
	//To rename field
	@Field("Mrp")
	private double price;
	@JsonDeserialize(using=PointDeserializer.class)
	private Point coords;
	@TextScore
	private float score;

	public Product(String title, String description) {
		this.title=title;
		this.description= description;
	}
	/*class ProdIdGenerator {
		public int getId() {
			Integer id = template.findAndModify(Query.query(Criteria.where("_id")), new Update().inc("_id", 1),
					Integer.class, "seqGenerator");
			return id;
		}
	}*/
}
