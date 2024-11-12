package lk.icet.hotel.controller;

import lk.icet.hotel.dto.Customer;
import lk.icet.hotel.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.Base64;
import java.util.List;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
@CrossOrigin
@Slf4j
public class CustomerController {

	final CustomerService customerService;

	@PostMapping("/save")
	public void saveCustomer(@RequestBody Customer customer){
		customerService.saveCustomer(customer);
	}

	@GetMapping("/all")
	public List<Customer> getAll(@RequestParam(required = false) Long id , String nic){

		if (id != null){
			return customerService.searchById(id);
		} else if (nic != null) {
			return customerService.searchByNic(nic);
		}

		return customerService.customerList();

	}

	@DeleteMapping("/delete-by-id/{id}")
	public void deleteById(@PathVariable Long id){
		customerService.deleteById(id);
	}

	@PutMapping("/update")
	public void updateById(@RequestBody Customer customer){
		customerService.updateCustomer(customer);
	}

	@GetMapping("/search-by-name/{name}")
	public List<Customer> searchByName(@PathVariable String name){
		return customerService.searchByName(name);
	}

	@GetMapping("/search-by-nic/{nic}")
	public List<Customer> searchByNic(@PathVariable String nic){
		return customerService.searchByNic(nic);
	}


}