## Rest URL Mappings



New Annotations (Spring 4,5) | Old Annotations (Spring 3+)  
----------------------------|-------------------------------    
|@GetMapping(value="/list")  | @RequestMapping(value="/list", method=RequestMethod.GET)
@PostMapping(value="/list") | @RequestMapping(value="/list",                 method=RequestMethod.POST)


When using Spring 3.x, @GetMapping, @PostMapping etc are NOT available.

When Mappings have only ONE parameter (value parameter),
then parameter name "value" is optional.

@GetMapping(value="/list")  ==> @GetMapping("/list")

When having more than one parameter, all names are mandatory.

@GetMapping(value="/list", produces={"application/json"})


