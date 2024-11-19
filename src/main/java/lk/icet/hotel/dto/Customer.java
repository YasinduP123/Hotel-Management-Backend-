package lk.icet.hotel.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.nio.file.Path;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
	private Long id;
	private String name;
	private String nic;
	private String address;
	private String contactNumber;
	private String email;
	private String dob;
	private String gender;
	private String image;
	private Path imageGet;
	private byte[] imageArray;

	public Customer(Long id, String name, String nic, String address, String contactNumber, String email, String dob, String gender, String image) {
		this.id = id;
		this.name = name;
		this.nic = nic;
		this.address = address;
		this.contactNumber = contactNumber;
		this.email = email;
		this.dob = dob;
		this.gender = gender;
		this.image = image;
	}
	public Customer(Long id, String name, String nic, String address, String contactNumber, String email, String dob, String gender, Path image) {
		this.id = id;
		this.name = name;
		this.nic = nic;
		this.address = address;
		this.contactNumber = contactNumber;
		this.email = email;
		this.dob = dob;
		this.gender = gender;
		this.imageGet = image;
	}


}