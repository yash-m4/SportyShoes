package com.sportyshoes.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.sportyshoes.model.Category;
import com.sportyshoes.model.Kart;
import com.sportyshoes.model.Product;
import com.sportyshoes.model.User;
import com.sportyshoes.service.CategoryService;
import com.sportyshoes.service.KartService;
import com.sportyshoes.service.ProductService;
import com.sportyshoes.service.UserService;
import com.sportyshoes.util.ImageUtil;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(path="/sportyshoes")
public class LoginController {

	@Autowired
	UserService userService;
	@Autowired
	CategoryService categoryService;
	@Autowired
	ProductService productService;
	@Autowired
	KartService kartService;
	
	//Login Page
	@GetMapping("/Login")
	public String loginPage() {
		return "Login";
	}
	
	//Customer Login form Submit
	@PostMapping("/auth")  
	public ModelAndView auth(@RequestParam("username") String username
			,@RequestParam("password") String password,Model model) {
		int b=userService.authUser(username, password);
		ModelAndView modelv = new ModelAndView(); 
		
		if(b==1) {			
			List<Category> category=categoryService.viewAllCategory();
			List<Product> products=productService.viewAllProduct();
			User user=userService.findByUsername(username);
			modelv.addObject("user", user);
			modelv.addObject("category", category);
			modelv.addObject("products", products);
		
			modelv.setViewName("UserHome");
			return modelv;
			
		}
		else {
			modelv.setViewName("invalid");
			return modelv;
			
		}
	}
	
	//Customer Admin form Submit
		@PostMapping("/adminAuth")  
		public String authAdmin(@RequestParam("username") String username,@RequestParam("password") String password) {
			
			if(username.equals("")&&password.equals("")) {
				return "invalid";
			}
			
			else {
			User u=userService.findByUsername(username);
			if(u.getUsername().equals("admin")) {
				return "adminHome";
			}
			else
			{
				return "invalid";
			}
			}
		}
		
		//Logout
		@GetMapping("/logout")
		public String logout(HttpServletRequest request) {
			 request.getSession().invalidate();
			 return "Login";
		}
		
		//password reset
		@GetMapping("/resetPassword")
		public String resetPassword() {
			return "resetPassword";
		}
		
		//reset password submit
		@PostMapping("/resetPasswordSubmit")
		public String resetPasswordSubmit(Model m,@RequestParam("username") String username,@RequestParam("password") String password) {
			
			User u=userService.findByUsername(username);
			if(u.getUsername().equals("admin")) {
				u.setPassword(password);
				userService.updateUser(u);
				return "Login";
			}
			else
			{
				return "invalid";
			}
			
			
		}
		
	
	//go back to admin home
	@GetMapping("/adminHome")   
	public String adminHome() {
		return "adminHome";
	}
	
	//Login Form page to New user registration page
	@GetMapping("/signUp")   
	public String signUp() {
		return "registerUser";
	}
	
	//new user form submit
	@PostMapping("/addUser")  
	public String addUser(@ModelAttribute User user)
		 {
		userService.addUser(user);
		return "UserHome";
	}
	
	//add new category form
	@GetMapping("/addCategory")
	public String addCategory() {
		return "addCategory";
	}
	
	//category form submit
	@PostMapping("/saveCategory")
	public String saveCategory(@ModelAttribute Category category,Model model) {
		System.out.println(category);
		categoryService.addCategory(category);
		model.addAttribute("confirm", "Category Added");
		return "addCategory";
	}
	
	//manage category page
	@GetMapping("/manageCategory")
	public ModelAndView manageCategory() {
		ModelAndView model = new ModelAndView(); 
		List<Category> category=categoryService.viewAllCategory();
		model.addObject("category", category);
		model.setViewName("manageCategory");
		return model;
	}
	
	//delete category form submit
	@PostMapping("/deleteCategory")
	public String deleteCategory(@RequestParam("category_id") int category_id,Model model) {
		categoryService.deleteCategoryById(category_id);
		String d="Category with id: "+category_id+" deleted";
		model.addAttribute("deleted", d);
		List<Category> category=categoryService.viewAllCategory();
		model.addAttribute("category", category);
		return "manageCategory";
	}
	
	//show products on basis of selected category
	@PostMapping("/showProductsByCategory")
	public ModelAndView showProductsByCategory(@RequestParam("category") int category_id) {
		Category c =categoryService.viewById(category_id);
		ModelAndView model = new ModelAndView(); 
		model.addObject("category", c);
		
		model.setViewName("showProductsByCategory");
		return model;
	}
	
	//add  product form
	@GetMapping("/addProducts")
	public ModelAndView addProducts() {
		ModelAndView model = new ModelAndView(); 
		List<Category> category=categoryService.viewAllCategory();
		model.addObject("category", category);
		model.setViewName("addProducts");
		
		return model;
	}
	
	// add product form submit
	@PostMapping("/saveProduct")
	public ModelAndView saveProduct (@RequestParam("product_name")String product_name
			,@RequestParam("product_description")String product_description
			,@RequestParam("product_price")int product_price
			,@RequestParam("product_addedBy")String product_addedBy
			,@RequestParam("product_addedDate")Date product_addedDate
			,@RequestParam("productImage")MultipartFile file
			,@RequestParam("category") int category_id,Model model) throws IOException {
		
		Product product=new Product();
		
		product.setProduct_name(product_name);
		product.setProduct_description(product_description);
		product.setProduct_price(product_price);
		product.setProduct_addedDate(product_addedDate);
		product.setProduct_addedBy(product_addedBy);
		
		
		productService.addProduct(product,category_id,file);
		
		String a="Product is succesfully Added";
		model.addAttribute("added", a);
		
		ModelAndView modelv = new ModelAndView(); 
		List<Category> category=categoryService.viewAllCategory();
		modelv.addObject("category", category);
		modelv.setViewName("addProducts");
		return modelv;
	}
	
	//manage products
	@GetMapping("/manageProduct")
	public ModelAndView manageProduct() {
		ModelAndView model = new ModelAndView(); 
		List<Category> category=categoryService.viewAllCategory();
		model.addObject("category", category);
		model.setViewName("manageProduct");
		return model;
		
	
	}
	
	//under manage product, show products by category
	@PostMapping("/showProductsByCategoryToDelete")
	public ModelAndView showProductsByCategoryToDelete(@RequestParam("category") int category_id) {
		Category c =categoryService.viewById(category_id);
		ModelAndView model = new ModelAndView(); 
		model.addObject("category", c);
		
		model.setViewName("showProductsByCategoryToDelete");
		return model;
		
		
	}
	
	//delete product by id
	@PostMapping("/deleteProduct")
	public String deleteProduct(@RequestParam("product_id") int product_id) {
		productService.deleteProduct(product_id);
		return "deletedProduct";
	}
	
	//search user
	@GetMapping("/searchUser")
	public ModelAndView searchUser() {
		
		List<User> user=  userService.viewAllUsers();
		ModelAndView model = new ModelAndView(); 
		model.addObject("user", user);
		model.setViewName("searchUser");
		return model;
		
	}
	
	//show products by category for user
	@PostMapping("/showProductsByCategoryUserSide")
	public ModelAndView showProductsByCategoryUserSide(@RequestParam("category") int category_id,@RequestParam("username") String username) {
		Category c =categoryService.viewById(category_id);
		User user=userService.findByUsername(username);
		ModelAndView model = new ModelAndView(); 
		model.addObject("category", c);
		 model.addObject("user", user);
		model.setViewName("showProductsByCategoryUserSide");
		return model;
	}
	
	
	@PostMapping("/cart")
	public ModelAndView addToCart(@RequestParam("productId") int productId,@RequestParam("username") String username) {
		Product product = productService.getProductById(productId);
		User user=userService.findByUsername(username);
		ModelAndView model = new ModelAndView(); 
        model.addObject("product", product);
        model.addObject("user", user);
		model.setViewName("cartPage");
		return model;
	  
	}
	
	@PostMapping("/checkout")
	public ModelAndView checkout(@RequestParam("productId") int productId, @RequestParam("username") String username) {
	    Product product = productService.getProductById(productId);
	    User user = userService.findByUsername(username);
	    Date purchaseDate = new Date(System.currentTimeMillis()); // Get the current date

	    Kart kart = new Kart(product, user, purchaseDate);
	    kartService.addKart(kart);

	    ModelAndView model = new ModelAndView();
	    model.addObject("user", user);
	    model.addObject("product", product);
	    model.setViewName("payementPage");
	    return model;
	}


	@GetMapping("/viewPurchaseList")
	public ModelAndView viewPurchaseList(@RequestParam(value = "username", required = false) String username) {
	    List<Kart> kartList = kartService.viewAll();
	    Map<User, List<Product>> userProductMap = new HashMap<>();

	    for (Kart kart : kartList) {
	        User user = kart.getUser();
	        Product product = kart.getProduct();

	        if (!userProductMap.containsKey(user)) {
	            userProductMap.put(user, new ArrayList<>());
	        }

	        List<Product> productList = userProductMap.get(user);
	        productList.add(product);
	    }

	    if (username != null) {
	        userProductMap.entrySet().removeIf(entry -> !entry.getKey().getUsername().equals(username));
	    }

	    ModelAndView model = new ModelAndView();
	    model.addObject("userProductMap", userProductMap);
	    model.setViewName("viewPurchaseList");
	    return model;
	}
	
	@GetMapping("/viewUserList")
	public ModelAndView viewUserList(@RequestParam(value = "username", required = false) String username) {
	    List<User> userList = userService.viewAllUsers();

	    if (username != null) {
	        userList = userList.stream()
	                .filter(user -> user.getUsername().equals(username))
	                .collect(Collectors.toList());
	    }

	    ModelAndView model = new ModelAndView();
	    model.addObject("userList", userList);
	    model.setViewName("userList");
	    return model;
	}
///////////////////////////////delete this
	@GetMapping("/download/{fileName}")
	public ResponseEntity<byte[]> downloadImage(@PathVariable String fileName) {
		byte[] image = productService.downloadImage(fileName);
		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("image/png")).body(image);
	}
	////////////////////////////

}
