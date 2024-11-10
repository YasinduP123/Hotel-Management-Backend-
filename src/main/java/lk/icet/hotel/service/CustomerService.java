package lk.icet.hotel.service;

import lk.icet.hotel.dto.Customer;

import java.util.List;

public interface CustomerService {
	void saveCustomer(Customer customer);

	List<Customer> customerList();
	void deleteById(Long id);
	void updateCustomer(Customer customer);
	List<Customer> searchById(Long id);

	List<Customer> searchByName(String name);

	List<Customer> searchByNic(String nic);
}
