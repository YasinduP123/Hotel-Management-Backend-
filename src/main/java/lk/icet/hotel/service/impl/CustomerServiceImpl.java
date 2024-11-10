package lk.icet.hotel.service.impl;

import lk.icet.hotel.entity.CustomerEntity;
import lk.icet.hotel.dto.Customer;
import lk.icet.hotel.repository.CustomerRepository;
import lk.icet.hotel.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

	final CustomerRepository repository;
	final ModelMapper mapper;

	@Override
	public void saveCustomer(Customer customer){
		repository.save(mapper.map(customer,CustomerEntity.class));
	}

	@Override
	public List<Customer> customerList() {
		List<Customer> customerList = new ArrayList<>();
		repository.findAll().forEach(customer ->customerList.add(mapper.map(customer,Customer.class)));
		return customerList;
	}

	@Override
	public void deleteById(Long id) {
		repository.deleteById(id);
	}

	@Override
	public void updateCustomer(Customer customer) {
		repository.save(mapper.map(customer,CustomerEntity.class));
	}

	@Override
	public List<Customer> searchById(Long id){
		List<Customer> customers = new ArrayList<>();
		repository.findById(id).map(customerEntity -> customers.add(mapper.map(customerEntity,Customer.class)));
		return customers;
	}

	@Override
	public List<Customer> searchByName(String name) {
		List<Customer> customerList = new ArrayList<>();
		repository.findByName(name).forEach(customerEntity -> customerList.add(mapper.map(customerEntity,Customer.class)));
		return customerList;
	}

	@Override
	public List<Customer> searchByNic(String nic) {
		List<Customer> customerList = new ArrayList<>();
		repository.findByNic(nic).forEach(customerEntity -> customerList.add(mapper.map(customerEntity,Customer.class)));
		return customerList;
	}


}
