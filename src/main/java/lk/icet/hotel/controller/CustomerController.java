package lk.icet.hotel.controller;

import lk.icet.hotel.dto.Customer;
import lk.icet.hotel.service.CustomerService;
import lk.icet.hotel.util.ImgUpload;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
@CrossOrigin
@Slf4j
public class CustomerController {

	final CustomerService customerService;

	@PostMapping(value = "/save", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public void saveImage(
			@RequestParam("files") MultipartFile[] files,
			@RequestPart("customer") Customer customer) {

		String uploadDir = "ProfileImagesFolder";

		Arrays.stream(files).forEach(file -> {
			String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
			log.info(fileName);

			try {
				customer.setImage(ImgUpload.saveImg(uploadDir, fileName, file));
				log.info("Customer: " + customer);
				customerService.saveCustomer(customer);
			} catch (IOException ioException) {
				log.error("IOException: " + ioException.getMessage());
			}
		});
	}

	@GetMapping("/images/{filename}")
	public ResponseEntity<Resource> getImage(@PathVariable String filename) {
		try {
			// Log the filename being requested
			System.out.println("Requested filename: " + filename);

			// Construct the full path
			Path imagePath = Paths.get("E:\\Project_Intelij\\Frame Works (Spring Boot)\\Hotel_Management_System\\src\\main\\resources\\imagesProfileImagesFolder").resolve(filename);

			// Log the resolved path
			System.out.println("Resolved file path: " + imagePath.toString());

			// Load the file as a resource
			Resource resource = new UrlResource(imagePath.toUri());

			// Check if the file exists and is readable
			if (!resource.exists() || !resource.isReadable()) {
				System.out.println("File not found or not readable: " + imagePath.toString());
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
			}

			// Determine content type
			String contentType = Files.probeContentType(imagePath);
			if (contentType == null) {
				contentType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
			}

			return ResponseEntity.ok()
					.contentType(MediaType.parseMediaType(contentType))
					.body(resource);

		} catch (IOException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}



	@GetMapping("/all")
	public List<Customer> getAll(@RequestParam(required = false) Long id, @RequestParam(required = false) String nic) {
		List<Customer> customerList;

		if (id != null) {
			customerList = customerService.searchById(id);
			customerList.forEach(customer -> {
				String imageUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
						.path("customer/images/")
						.path(customer.getImage().substring(customer.getImage().lastIndexOf("\\") + 1))
						.toUriString();
				customer.setImage(imageUrl);
			});
		} else if (nic != null) {
			customerList = customerService.searchByNic(nic);
			customerList.forEach(customer -> {
				String imageUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
						.path("customer/images/")
						.path(customer.getImage().substring(customer.getImage().lastIndexOf("\\") + 1))
						.toUriString();
				customer.setImage(imageUrl);
			});
		} else {
			customerList = customerService.customerList();
			customerList.forEach(customer -> {

				if(customer.getImage()!=null){
				String imageUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
						.path("customer/images/")
						.path(customer.getImage().substring(customer.getImage().lastIndexOf("\\") + 1))
						.toUriString();
				customer.setImage(imageUrl);
				}
			});
		}



		return customerList;
	}


	@DeleteMapping("/delete-by-id/{id}")
	public void deleteById(@PathVariable Long id){
		customerService.deleteById(id);
	}

	@PutMapping("/update")
	public void update(
			@RequestParam("files") MultipartFile[] files,
			@RequestPart("customer") Customer customer) {

		String uploadDir = "ProfileImagesFolder";

		Arrays.stream(files).forEach(file -> {
			String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
			log.info(fileName);

			try {
				customer.setImage(ImgUpload.saveImg(uploadDir, fileName, file));
				log.info("Customer: " + customer);
				customerService.updateCustomer(customer);
			} catch (IOException ioException) {
				log.error("IOException: " + ioException.getMessage());
			}
		});

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