## Rest Demo3 / Implementing ResponseEntity

1.  Copy project `RestDemo2' created by [rest-demo2](./spring-rest-methods.md)

    New project should be renamed as 'rest-demo3'

2.  Open `pom.xml` and replace all occurrances of 'rest-demo2' with 'rest-demo3'
3.  Open `com.cg.services.CountryService.java` and modify following TWO methods:

    ```java
    public void create(Country country) {
		Country temp = dao.findByCode(country.getCode());
		if(temp==null)
		{
			dao.save(country);
		}else
			throw new RuntimeException("Country "+country.getCode()+" already exists!");
	}
	
	public void update(Country country) {
		Country temp = dao.findByCode(country.getCode());
		if(temp!=null)
		{
			dao.update(country);
		}else //TODO: Please throw an Exception is record DO NOT exists
			System.out.println("Country does not exists!");
	}
    ```
4.  Modify the controller `com.cg.controllers.CountryController`

    ```java
    @RestController
    @RequestMapping("/countries")
    public class CountryController {

        @Autowired CountryService service;
        
        @GetMapping(value="/code/{code}")	
        public ResponseEntity<Country> findbycode(@PathVariable String code) {
            Country country = service.byCode(code);
            if(country==null) {
                return new ResponseEntity(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(country, HttpStatus.FOUND);
        }
        
        @GetMapping(value="/continent-{continent}")
        public ResponseEntity<List<Country>> findbycontinent(@PathVariable String continent){
            List<Country> countries = service.byContinent(continent);
            if(countries ==null || countries.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(countries,HttpStatus.OK);
        }
        
        @PostMapping(value="/new",consumes= {"application/json"})
        public ResponseEntity<String> save(@RequestBody Country country) {
            try {
            service.create(country);
            }catch(RuntimeException ex) {
                return new ResponseEntity<>(ex.getMessage(),HttpStatus.CONFLICT);
            }
            return new ResponseEntity<String>("Record created",HttpStatus.OK);
        }
        
        //TODO: Implement ResponseEntity to return SUCCESS when record is updated
        //		Else, return an error
        @PutMapping(value="/update",consumes= {"application/json"})
        public String update(@RequestBody Country country) {
            service.update(country);
            return "country updated";
        }
        
    }
    ```
5.  Try following URLs from Postman:

    http://localhost:5000/countries/code/IN

    It should fetch country details for India, now copy the response (JSON) and use it as INPUT for next URL:

    http://localhost:5000/countries/new    

> Click [here](./demo-sources/rest-demo3) for source code.