package com.phamtrong.app.controller;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.phamtrong.app.dto.CustomerDto;
import com.phamtrong.app.entity.Customer;
import com.phamtrong.app.entity.Email;
import com.phamtrong.app.entity.Response;
import com.phamtrong.app.exception.ConflictException;
import com.phamtrong.app.exception.EntityNotfoundException;
import com.phamtrong.app.repository.CustomerRepository;
import com.phamtrong.app.service.CustomerService;
/**
 * 
 * @author phamtrong
 *
 */

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class CustomerController {

//	private static final Logger LOG = Logger.getLogger(CustomerController.class);
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	/**
	 * 
	 * @param pageable
	 * @return
	 */
	
	@GetMapping("/customers")
	public ResponseEntity<?> findAll(Pageable pageable){
		return ResponseEntity.ok(customerService.findAll(null, pageable));
	}
	
	
	@PostMapping("/customers")
	public ResponseEntity<?> create(@RequestBody CustomerDto customerDto) {

		try {
			return ResponseEntity.ok(customerService.create(customerDto));
		} catch (ConflictException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getPlayload());
		}
	}

	@PutMapping("/customers/{id}")
	public ResponseEntity<?> update(@PathVariable(value="id") Integer id, @RequestBody CustomerDto customerDto) {
		try {
			customerDto.setId(id);
			return ResponseEntity.ok(customerService.update(customerDto));
		} catch (EntityNotfoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getPlayload());
		}
	}
	
	@GetMapping("/customers/{id}")
	public ResponseEntity<?> findOne(@PathVariable(value="id") Integer id) {
		try {
			return ResponseEntity.ok(customerService.findOne(id));
		} catch (EntityNotfoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getPlayload());
		}
	}
	
	@DeleteMapping("/customers/{id}")
	public ResponseEntity<?> delete(@PathVariable(value="id") Integer id) {
		try {
			return ResponseEntity.ok(customerService.deleteOrUndelete(id, null));
		} catch (EntityNotfoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getPlayload());
		}
	}
	
	@PostMapping("/customers/import")
	public ResponseEntity<?> importFile(@RequestBody List<CustomerDto> lstCustomerDto) {

		return ResponseEntity.ok(customerService.importFile(lstCustomerDto));
	}
	
	@PostMapping("/customer/upload")
	public ResponseEntity<?> importCsv(@RequestParam("file") MultipartFile file) {
		return ResponseEntity.ok(customerService.importCsv(file));
	}
	
	
	@PostMapping(value = "/customer/searches")
	public ResponseEntity<?> filter(@RequestBody CustomerDto projectCustomerDto, Pageable pageable) {

		return ResponseEntity.ok(customerService.filter(projectCustomerDto, pageable));
	}
	
	
	 @GetMapping(value = "customers/name/{name}")
	 public ResponseEntity<?> findAll(Pageable pageable, @PathVariable String name){
			return ResponseEntity.ok(customerService.findName(null, pageable, name));
		}
	 
	
	 
	 
//	 public ResponseEntity<List<Customer>> findByAge(@PathVariable String name) {
//		    try {
//		      List<Customer> customers = customerRepository.findByName(name);
//		 
//		      if (customers.isEmpty()) {
//		        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//		      }
//		      return new ResponseEntity<>(customers, HttpStatus.OK);
//		    } catch (Exception e) {
//		      return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
//		    }
//		  }
	 
	
	 
//	@RequestMapping(value = "/customer/upload", method = RequestMethod.POST)
//    public List<Customer> uploadingPost(@RequestParam("file") MultipartFile[] file) throws IOException {
//		
//		List<Customer> result = new ArrayList<>();
//        for(MultipartFile uploadedFile : file) {
//        	Customer c = new Customer();
//        	result = getCsvData(uploadedFile);     
//        	customerRepository.save(c);
//        	
//        }
//        return result;
//    }
	
//	private List<Customer> getCsvData(MultipartFile multipart ) {
//		BufferedReader br;
//		Pattern pattern = Pattern.compile(",");
//		List<Customer> result = new ArrayList<>();
//		try {
//		     String line;
//		     InputStream is = multipart.getInputStream();
//		     br = new BufferedReader(new InputStreamReader(is));
//		     int index = 0;
//		     
//		     while ((line = br.readLine()) != null) {
//		    	 String[] x = pattern.split(line);
//		    	 if(index!=0)
//		    	 result.add(new Customer(x[0],x[1],true,new Date())); 
//		    	 index++;
//		     }
//		  } catch (IOException e) {
//		    System.err.println(e.getMessage());       
//		  }
//		
//		return result;
//		
//	}
	 
	 @PostMapping("/sendMail")
	 public Response sendEmail(@RequestBody Email email) {
		 return customerService.sendEmail(email);
	 }
	
}
