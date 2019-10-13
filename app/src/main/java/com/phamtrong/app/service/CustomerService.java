package com.phamtrong.app.service;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.phamtrong.app.dto.CustomerDto;
import com.phamtrong.app.dto.MessageDto;
import com.phamtrong.app.entity.Customer;
import com.phamtrong.app.entity.Email;
import com.phamtrong.app.entity.Response;
import com.phamtrong.app.enums.ActiveEnum;
import com.phamtrong.app.enums.MessageEnum;
import com.phamtrong.app.enums.PatternDateTime;
import com.phamtrong.app.exception.ConflictException;
import com.phamtrong.app.exception.EntityNotfoundException;
import com.phamtrong.app.repository.CustomerRepository;
import com.phamtrong.app.repository.custom.CustomerRepositoryCustom;
import com.phamtrong.app.utils.BeanUtil;
import com.phamtrong.app.utils.DateUtil;
import com.phamtrong.app.utils.MessageUtil;
import com.phamtrong.app.utils.PageUtil;


@Service
public class CustomerService extends AbstractService<CustomerDto> implements CrudService<CustomerDto> {

	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private CustomerRepositoryCustom customerRepositoryCustom;
	
	@Autowired
	public JavaMailSender javaMailSender;
	@Override
	public Page<CustomerDto> findAll(Boolean active, Pageable pageable) {
		pageable = PageUtil.getPage(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("id").descending());
		Page<Customer> pageCustomer = customerRepository.findAll(pageable);
		return PageUtil.copy(pageCustomer, CustomerDto.class, pageable);
	}

	@Override
	public CustomerDto findOne(int id) {
		// TODO Auto-generated method stub
		Customer customer = customerRepository.findById(id).orElseThrow(()->{
			return new EntityNotfoundException(MessageUtil.notFound(Customer.class.getSimpleName(), id));
		});
		return BeanUtil.createAndCopy(customer, CustomerDto.class);
	}

	@Override
	public MessageDto create(CustomerDto object) {
		boolean existsCustomerId = customerRepository.existsByCustomerDate(object.getCustomerDate());
		if(existsCustomerId) {
			throw new ConflictException(MessageUtil.conflict(Customer.class.getSimpleName(), "customer id ", 
					DateUtil.convertDateToString(object.getCustomerDate(), PatternDateTime.YYYYMMDD_HYPHEN)));
		}
		
		Customer customer = new Customer();
		customer.setName(object.getName());
		customer.setAddress(object.getAddress());
		customer.setCustomerDate(object.getCustomerDate());
		customerRepository.save(customer);
		// TODO Auto-generated method stub
		return MessageUtil.ok(MessageEnum.SUCCESS_ADD,Customer.class.getSimpleName());
		
	}

	@Override
	public MessageDto update(CustomerDto object) {
		// TODO Auto-generated method stub
		
		Customer customer = customerRepository.findById(object.getId()).orElseThrow(()->{
			return new EntityNotfoundException(MessageUtil.notFound(Customer.class.getSimpleName(), object.getId()));
		});
		
		customer.setName(object.getName());
		customer.setAddress(object.getAddress());
		customer.setCustomerDate(new Date());
		return MessageUtil.ok(MessageEnum.SUCCESS_EDIT, Customer.class.getSimpleName());
	}

	
	
	@Override
	public MessageDto deleteOrUndelete(int id, ActiveEnum activeEnum) {
		boolean existsId = customerRepository.existsById(id);
		if (!existsId) {
			throw new EntityNotfoundException(MessageUtil.notFound(Customer.class.getSimpleName(), id));
		}

		// delete holiday by id
		customerRepository.deleteById(id);

		return MessageUtil.ok(MessageEnum.SUCCESS_DELETE, Customer.class.getSimpleName());

	}

	

	@Override
	public Page<CustomerDto> filter(CustomerDto object, Pageable pageable) {
		Page<Customer> pageOfHoliday = customerRepositoryCustom.findByConditions(object, pageable);
		return PageUtil.copy(pageOfHoliday, CustomerDto.class, pageable);
	}
	
	
//	@Transactional
//	public MessageDto importCsv(MultipartFile file) {
//		try {
//			InputStreamReader reader = new InputStreamReader(file.getInputStream());
//			CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build();
//			List<String[]> rows = csvReader.readAll();
//			for(String[] row : rows) {
////				Customer c = new Customer();
////				c.setName(row[0]);
////				c.setAddress(row[1]);
////				customerRepository.save(c);
//				customerRepository.save(new Customer(row[0], row[1]));
//			}
//			
//			
//			
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		
//		return MessageUtil.ok(MessageEnum.SUCCESS_IMPORT_FILE, Customer.class.getSimpleName());
//	}
	
	
	
	private final static int START_LINE = 1;
	
	@Transactional
	public MessageDto importCsv(MultipartFile file) {
		String line;
		String path = "/Users/phamtrong/Desktop/";
		int counter = START_LINE;
		
		String[] header = { "name", "address"};
		try {
			String filename = path + file.getOriginalFilename();
			BufferedReader br = new BufferedReader(new FileReader(filename));
			while((line = br.readLine()) != null) {
				if(counter > START_LINE) {
					String[] data = line.split(",");
					Customer c = new Customer();
					c.setName(data[0]);
					c.setAddress(data[1]);
					customerRepository.save(c);
				}
				counter++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return MessageUtil.ok(MessageEnum.SUCCESS_IMPORT_FILE, Customer.class.getSimpleName());
	}
	
	
	
//	@Transactional
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
	
	// 
	@Transactional
	public MessageDto importFile(List<CustomerDto> lstCustomerDto) {
		List<Date> lsDate = lstCustomerDto.stream().map(d -> d.getCustomerDate()).collect(Collectors.toList());
		List<Customer> lstCustomer = customerRepository.findByCustomerDateIn(lsDate);
		// Make new list Holiday
				List<Customer> newListCustomer = new ArrayList<>();
				for (CustomerDto customerDto : lstCustomerDto) {
					boolean isExists = false;
					for (Customer customer : lstCustomer) {
						if (DateUtil.convertDateToString(customerDto.getCustomerDate(), PatternDateTime.YYYYMMDD_HYPHEN).equals(
								DateUtil.convertDateToString(customer.getCustomerDate(), PatternDateTime.YYYYMMDD_HYPHEN))) {
							
							
							customer.setName(customerDto.getName());
							customer.setAddress(customerDto.getAddress());
							customer.setCustomerDate(new Date());
							
							newListCustomer.add(customer);
							isExists = true;
							break;
						}
					}

					if (!isExists) {
						Customer newCustomer = new Customer();
						
						newCustomer.setName(customerDto.getName());
						newCustomer.setAddress(customerDto.getAddress());
						newCustomer.setCustomerDate(new Date());
						newListCustomer.add(newCustomer);
					}
				}

				// Save new list
				customerRepository.saveAll(newListCustomer);

				return MessageUtil.ok(MessageEnum.SUCCESS_IMPORT_FILE, Customer.class.getSimpleName());
			}

	@Override
	public Page<CustomerDto> findName(Boolean active, Pageable pageable, String name) {
		pageable = PageUtil.getPage(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("id").descending());
		Page<Customer> pageCustomer = customerRepository.findByName(pageable, name);
		return PageUtil.copy(pageCustomer, CustomerDto.class, pageable);
	}

	@Override
	public Response sendEmail(Email mail) {
		Response response = new Response();
		try {
			SimpleMailMessage message = new SimpleMailMessage();
			message.setTo(mail.getTo());
			message.setSubject(mail.getSubject());
			message.setText(mail.getText());
			javaMailSender.send(message);
			response.setCode(0);
			response.setMessage("email send success");
		} catch (Exception e) {
			response.setCode(1);
			response.setMessage("error send email "+e.getMessage());
			
		}
		return response;
	}
	

}
