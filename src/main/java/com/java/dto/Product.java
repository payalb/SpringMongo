package com.java.dto;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection="products")
public class Product {
	//@Autowired
//	MongoTemplate template;
	@Id
	private Object prodid;
	private String description;
	private String title;

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
