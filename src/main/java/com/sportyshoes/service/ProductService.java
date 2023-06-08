package com.sportyshoes.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sportyshoes.model.Category;
import com.sportyshoes.model.Product;
import com.sportyshoes.repository.CategoryRepository;
import com.sportyshoes.repository.ProductRepository;
import com.sportyshoes.util.ImageUtil;
@Service
public class ProductService {
	
	@Autowired
	ProductRepository productRepository;
	@Autowired
	CategoryRepository categoryRepository;
	
	
	
	public void addProduct(Product product,int categoryId,MultipartFile file) throws IOException {
		
		Category category = categoryRepository.findById(categoryId).get(); // Fetch the desired category from the repository
		product.setCategory(category);
		
//		product.setName(file.getOriginalFilename());
//		product.setType(file.getContentType());
//		product.setImageData(ImageUtil.compressImage(file.getBytes()));
//		
//		productRepository.save(product);
		
		 product.setName(file.getOriginalFilename());
	        product.setType(file.getContentType());

	        // Read the image data from the file
	        byte[] imageData = file.getBytes();

	        // Compress the image data if necessary
	        byte[] compressedImageData = ImageUtil.compressImage(imageData);

	        // Set the compressed image data in the product
	        product.setImageData(compressedImageData);

	        // Save the product to the repository
	        productRepository.save(product);
	}
	
	public List<Product> viewAllProduct() {
		return productRepository.findAll();
	}

	public void deleteProduct(int product_id) {
		productRepository.deleteById(product_id);
	}
	
	 public Product getProductById(int productId) {
		    Product product = productRepository.findById(productId).get();
		    return product;
		  }
	
	////////////////////////////delete this method if serves no purpose
	 public byte[] downloadImage(String fileName){
	        Optional<Product> imageData = productRepository.findByName(fileName);
	        return ImageUtil.decompressImage(imageData.get().getImageData());
	    }
	 //////////////////////////////////
	
}
