package com.rahulshettyacademy.controller;

import java.util.List;

import com.rahulshettyacademy.component.AllCourseDetailsComponent;
import com.rahulshettyacademy.component.GreetingComponent;
import com.rahulshettyacademy.component.ProductsPricesComponent;
import com.rahulshettyacademy.component.SpecificProductComponent;
import com.rahulshettyacademy.entity.LibraryEntity;
import com.rahulshettyacademy.responseDto.AddResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rahulshettyacademy.repository.LibraryRepository;
import com.rahulshettyacademy.service.LibraryService;

@Tag(
		name = "Library API's",
		description = "We have the following API: Create user, update user, delete user, read user details"
)
@RestController
public class LibraryController {

	@Autowired
	LibraryRepository repository;
	
	@Autowired
	ProductsPricesComponent productPrices;
	
	@Autowired
	LibraryService libraryService;
	@Autowired
	GreetingComponent greetingComponent;
//	@Autowired
//	SpecificProduct specificProduct;
	

	String baseUrl ="http://localhost:8002";
	
	private static final Logger logger=  LoggerFactory.getLogger(LibraryController.class);
	
	@PostMapping("/addBook")
	public ResponseEntity addBookImplementation(@RequestBody LibraryEntity libraryEntity)
	{
		String id =libraryService.buildId(libraryEntity.getIsbn(), libraryEntity.getAisle());//dependenyMock
		AddResponse ad =new AddResponse();
		
		if(!libraryService.checkBookAlreadyExist(id))//mock
		{
			logger.info("Book do not exist so creating one");
			libraryEntity.setId(id);
		repository.save(libraryEntity);//mock
		HttpHeaders headers =new HttpHeaders();
		headers.add("unique", id);
		
		ad.setMsg("Success Book is Added");
		ad.setId(id);
		//return ad;
		return new ResponseEntity<AddResponse>(ad,headers,HttpStatus.CREATED);
		}
		else
		{
			logger.info("Book  exist so skipping creation");
			ad.setMsg("Book already exist");
			ad.setId(id);
			return new ResponseEntity<AddResponse>(ad,HttpStatus.ACCEPTED);
		}
			//write the code to tell book already exist
		
		}
	@CrossOrigin
	@RequestMapping("/getBooks/{id}")
	public LibraryEntity getBookById(@PathVariable(value="id")String id)
	{
		try {
		LibraryEntity lib =repository.findById(id).get();
		return lib;
		}
		catch(Exception e)
		{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}
	@CrossOrigin
	@GetMapping("getBooks/author")
	public List<LibraryEntity> getBookByAuthorName(@RequestParam(value="authorname")String authorname)
	{
		return repository.findAllByAuthor(authorname);
	}
	
	@PutMapping("/updateBook/{id}")
	public ResponseEntity<LibraryEntity> updateBook(@PathVariable(value="id")String id, @RequestBody LibraryEntity libraryEntity)
	{
	//	Library existingBook = repository.findById(id).get();//mock
		LibraryEntity existingBook =libraryService.getBookById(id);
		
		existingBook.setAisle(libraryEntity.getAisle());//mock
		existingBook.setAuthor(libraryEntity.getAuthor());
		existingBook.setBook_name(libraryEntity.getBook_name());
		repository.save(existingBook);//
		//
		return new ResponseEntity<LibraryEntity>(existingBook,HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteBook")
	public ResponseEntity<String> deleteBookById(@RequestBody LibraryEntity libraryEntity)
	{
	//	Library libdelete =repository.findById(library.getId()).get();
		LibraryEntity libdelete =libraryService.getBookById(libraryEntity.getId());//mock
		repository.delete(libdelete);
		
		logger.info("Book  is deleted ");
		return new ResponseEntity<>("Book is deleted",HttpStatus.CREATED);
		
		}
	
	@GetMapping("/getBooks")
	public List<LibraryEntity> getBooks()
	{
		return repository.findAll();
	}
	
	
	@GetMapping("/getProductDetails/{name}")
	public SpecificProductComponent getProductFullDetails(@PathVariable(value="name")String name) throws JsonMappingException, JsonProcessingException
	{
		
		SpecificProductComponent specificProductComponent = new SpecificProductComponent();
		TestRestTemplate restTemplate =new TestRestTemplate();
		LibraryEntity lib = repository.findByName(name);
		specificProductComponent.setProduct(lib);
		ResponseEntity<String>	response =	restTemplate.getForEntity(baseUrl+"/getCourseByName/"+name, String.class);
		if(response.getStatusCode().is4xxClientError())
		{
			specificProductComponent.setMsg(name +"Category and price details are not available at this time");
		}
		else
		{
		ObjectMapper mapper = new ObjectMapper();
	
		AllCourseDetailsComponent allCourseDetailsComponent = mapper.readValue(response.getBody(), AllCourseDetailsComponent.class);
		
		
		specificProductComponent.setCategory(allCourseDetailsComponent.getCategory());
		specificProductComponent.setPrice(allCourseDetailsComponent.getPrice());
	
		}
		return specificProductComponent;
	}
	
	
	@CrossOrigin
	@GetMapping("/getProductPrices")
	public ProductsPricesComponent getProductPrices() throws JsonMappingException, JsonProcessingException
	{
		productPrices.setBooksPrice(250);
	

		long sum = 0;
		for(int i=0;i<getAllCoursesDetails().length;i++)
		{
			sum = sum + getAllCoursesDetails()[i].getPrice();
		}
		
		productPrices.setCoursesPrice(sum);
		
	return productPrices;
	}
	public void setBaseUrl(String url)
	{
		baseUrl = url;
	}
	
	public AllCourseDetailsComponent[] getAllCoursesDetails() throws JsonMappingException, JsonProcessingException
	{
		TestRestTemplate restTemplate =new TestRestTemplate();
		
		ResponseEntity<String>	response =restTemplate.getForEntity(baseUrl+"/allCourseDetails", String.class);
		ObjectMapper mapper = new ObjectMapper();
	
		AllCourseDetailsComponent[] allCourseDetailComponents = mapper.readValue(response.getBody(), AllCourseDetailsComponent[].class);
		return allCourseDetailComponents;
	}
	
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

