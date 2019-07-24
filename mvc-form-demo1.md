## Form Handling with @ModelAttribute
```
  Model class
  Product { productId, name, description, price }

  Set URL /add-product

  GET /add-product		Show a form page
  POST /add-product		Accept the form values and process the data.

  Views:
    form.jsp		With HTML form
    success.jsp		Result of form submission	
```

### Steps

1. Modify "home.jsp" page, add hyperlink:
  ```html	
	<a href="add-product">Add product</a>
  ```

2. Create new Model Class "com.cg.entities.Product"
  
  ```java
  private Integer productId;
  private String name;
  private String description;
  private Double price;
  ```
		
3. Create controller class "com.cg.controllers.ProductController"
	
	```java
	@Controller
	@RequestMapping("/add-product")
	public class ProductController {

		@GetMapping
		public String showForm(Model model) {
			Product p = new Product();
			p.setPrice(100D);
			// Creating and storing new empty product object
			// THIS object would be PASSED to FORM page as "modelAttribute"
			model.addAttribute("product",p);
			return "form";
		}
		
		@PostMapping
		public String submit(Model model,
				//ModelAttribute : Collect multiple parameters and group them
				//  				into an Object (Object class must be POJO)
					@ModelAttribute("product") Product product,
				// Collect all Conversion & Validation errors
					BindingResult results) {
			String msg1="";
			System.out.println("Processing "+product.getName());
			if(!results.hasErrors()) {
				msg1="No Errors found!";
				model.addAttribute("productname",product.getName());
			}
			else {
				msg1=results.getErrorCount()+ " errors occurred!";
			}
			model.addAttribute("msg",msg1);
			model.addAttribute("errors",results.getAllErrors());
			
			return "success";
		}
	}

	```
4.	Create new JSP file `WEB-INF/pages/form.jsp`
	
	```html
	<%@ taglib uri="http://www.springframework.org/tags/form" prefix="s" %>
	<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Add product page</title>
	</head>
	<body>
	<!-- modelAttribute MUST MATCH with attribute defined in Controller's GET method! -->
	<s:form method="post" modelAttribute="product" >
		Product ID : <input type="text" name="productId"/><br/>
		Name : <input type="text" name="name"/><br/>
		Description: <input type="text" name="description"/><br/>
		Price: <input type="text" name="price"/><br/>
		<input type="submit" value="Save"/>
	</s:form>
	</body>
	</html>
	```

5.	Create new JSP file `WEB-INF/pages/success.jsp`
	
	```html
	<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Adding Product</title>
	</head>
	<body>
	<h3>${msg}</h3>

	</body>
	</html>
	```
