## Response Entities

ResponseEntity<> Let you define response status, response status code, response body and response header.

### Without ResponseEntity

```java
@GetMapping(value="/code/{code}")	
public Country findbycode(@PathVariable String code) {
	return service.byCode(code);
}
```

Response:   The above method would return "Country" object to client.
            It only controls "Response Body", but has no control over
            Response Header or Response CODE.


### With ResponseEntity
```java
@GetMapping(value="/code/{code}")	
public ResponseEntity<Country> findbycode(@PathVariable String code) {
    Country country = service.byCode(code);
    if(country==null) {
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<>(country, HttpStatus.FOUND);
}
```

Response:   The above method would return "Country" object (Only if found) 
            and, it would provide a custom status code or message.
            If country NOT FOUND, it would return 404 Not Found.
            If country is FOUND, it would return 302 Found.    