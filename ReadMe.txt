Requirement tool generate a rest controller: project target pb360-STI, database is used DB2 EXCEEDDB.
The tool is used to read the parameters are passing by UI. Then read the JSON file to get data and write it to output file respectively.

	- Input: controller name (String), controller URL (String).
	- Output: need generate common files base on the structure for a rest Controller as below:
	
	Rest Controller Structure:
	Application:
		Securities validator:
			STI Rest API:
			Main {
					
					Controller.
					Model.
					Service. -->  ServiceImpl
					Validator.
					Options.
			},
			Test {
					ControllerTest.
			}
	
	
	The Detail of STI Rest API:
	
	I.	Controller file: generate a common controller file, is a Java Class, place in package pb360.controller.
		1.	Need define under @RestController, and get by URL is provided under @RequestMapping(value = "URL").
		2.	Need import validate, service and options under @Autowire.
		3.	Have to 6 basic methods of rest controller: GET, GET ALL, POST, PATCH, DELETE and OPTIONS. 
		4.	The function name should be same <function prefix name + controller name>: read JSON file.			
		5.	Above each function need add a @ annotation @UserAuthorization(“function name + ControllerName”) and @RequestMapping(method = POST, GET, DELETE, PATCH,OPTIONS)
		6.	Always define 2 static variable below:
			a.	private static final int INITIAL_PAGE = 1;
			b.	private static final int INITIAL_PAGE_SIZE = 10;

	II.	Model file: generate a common model file, is a Java class, place on package pb360.model. 
		1.	Have name is {same controller name}, a class must be extent from ResourceSupport (hateoas).
		2.	Have default constructor.

	III.	Validator file: generate a common validator file, is a Java Class file, place in pb360.validation package
		1.	Need include annotation @Component above class, 
		2.	Implement validator (spring), and must to Override all of method of Spring Validator


	IV.	Options file: generate a Options common file, is a Java Class, and place in pb360.service.options
		1.	Need extends from AbstractOptionsImpl (core) and implements from OptionsService(core)
		2.	Override all of methods of OptionsService, body always empty on each function
		3.	Create 2 default methods as below: read JSON file.
			
	V.	Service file: generate a service common file, is a Java Interface file, place in pb360.service
		1.	Need have 5 basic methods: function name {function name + controller name} (save + Controller Name) 
		Read JSON file.
			
	VI.	UJnit test file: generate a UJnit Test common file, is a Java Class file, place on pb360.controller.test
		Need import the default header of SpringJUnit4Test
		Read JSON file.
			

