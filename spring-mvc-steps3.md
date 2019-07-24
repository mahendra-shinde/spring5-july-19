## Spring MVC Validation


1. Modify `pom.xml` file

	Add new dependency "hibernate-validator"

	```xml
  	<dependency>
  		<groupId>org.hibernate.validator</groupId>
  		<artifactId>hibernate-validator</artifactId>
  		<version>6.0.16.Final</version>
  	</dependency>
	```

2.	Modify the model class `com.cg.entities.Product`

	```java
	import javax.validation.constraints.*;

	public class Product {

	@NotNull
	@Max(value=10000,message="Product ID Cannot be greater than 10000")
	private Integer productId;
	
	@NotEmpty(message="Name cannot be empty!")
	private String name;
	
	@Size(min=3,max=100,message="Must have atleast 3 characters and max 100 characters")
	private String description;
	
	@NotNull
	private Double price;
	//Getters, Setters & Constructuctors....
	}

	```

3.	Modify the form page `/WEB-INF/pages/form.jsp`

	Replace the existing FORM with NEW one:

	```html
	<s:form method="post" modelAttribute="product">
	Product ID : <s:input path="productId" />
		<s:errors path="productId" />
		<br />
	Name : <s:input path="name" />
		<s:errors path="name" />
		<br />
	Description: <s:input path="description" />
		<s:errors path="description" />
		<br />
	Price: <s:input path="price" />
		<s:errors path="price" />
		<br />
		<input type="submit" value="Save" />
	</s:form>
	```

4.	Modify Controller class `com.cg.controllers.ProductController`

	replace submit method with new one:

	```java
	@PostMapping
	public String submit(Model model,
			//ModelAttribute : Collect multiple parameters and group them
			//  				into an Object (Object class must be POJO)
				@Valid @ModelAttribute("product") Product product,
			// Collect all Conversion & Validation errors
				BindingResult results) {
		String msg1="";
		System.out.println("Processing "+product.getName());
		if(!results.hasErrors()) {
			msg1="No Errors found!";
			model.addAttribute("msg",msg1);
			model.addAttribute("productname",product.getName());
			return "success";
		}
		else {
			msg1=results.getErrorCount()+ " errors occurred!";
			model.addAttribute("msg",msg1);
			model.addAttribute(results.getAllErrors());
			return "form";
		}
	}
	```