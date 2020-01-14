package org.javastart.demo.controller;

import org.javastart.demo.model.Product;
import org.javastart.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ProductController {

    private ProductRepository productRepository;

    @Autowired
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/findBy")
    @ResponseBody
    public String findBy(@RequestParam(name = "letter") String letter) {
        List<Product> all = productRepository.getProductsWithoutSpringStartingWithNative(letter);
        String result = all.stream()
            .map(prod -> prod.getName())
            .collect(Collectors.joining(", "));
        return result;
    }

    @GetMapping("/delete")
    @ResponseBody
    public String deleteByEnding(@RequestParam(name = "letter") String letter) {

        productRepository.deleteByNameEndingWith(letter);

        return "Success";
    }

    @GetMapping("/findAll")
    @ResponseBody
    public String findProduct() {
        List<Product> all = productRepository.findAll();

        String result = all.stream()
            .map(product -> product.getName())
            .collect(Collectors.joining(", "));

        return result;
    }

    @GetMapping("/add")
    @ResponseBody
    public String addData() {
        Product chleb = new Product("Chleb", 2.99);
        productRepository.save(chleb);

        Product ser = new Product("Ser", 3.99);
        productRepository.save(ser);

        return "Success";
    }
}
