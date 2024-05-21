package com.example.lesson;

import com.example.lesson.entity.Product;
import com.example.lesson.service.ProductService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class Main {

	public static void main(String[] args) {

		SpringApplication.run(Main.class, args);

//		var context = SpringApplication.run(Main.class, args);

//		var productService = context.getBean(ProductService.class);
//		var list = productService.findAll();
//		list.stream().forEach(System.out::println);

//		System.out.println(productService.findById(2));

//		System.out.println(productService.insert(new Product(0,"さんぴん茶",100)));

//		System.out.println(productService.update(new Product(4,"ウーロン茶",200)));

//		System.out.println(productService.delete(4));

//		var p_list = List.of(new Product(1,"緑茶",100),new Product(2,"麦茶",120));
//		System.out.println(productService.bulkInsert(p_list));

	}

}
