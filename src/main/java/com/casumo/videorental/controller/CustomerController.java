package com.casumo.videorental.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.casumo.videorental.dto.CustomerRequest;
import com.casumo.videorental.dto.CustomerResponse;
import com.casumo.videorental.model.Customer;
import com.casumo.videorental.service.CustomerService;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

//@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/video-rental-store/api/v1/customer")
@RequiredArgsConstructor
//@SecurityRequirement(name = "BEARER-AUTH")//To bypass security for a single controller
@Tag(name = "Customer module")
public class CustomerController {

	private final CustomerService customerService;

	@Operation(description = "Post Endpoint for Customer", summary = "This is a summary for Post customer endpoint", responses = {
			@ApiResponse(description = "Created", responseCode = "201"),
			@ApiResponse(description = "Unauthorized / Invalide Token", responseCode = "403"),

	})
	@PostMapping
	public ResponseEntity<CustomerResponse> createCustomer(@RequestBody @Valid CustomerRequest customerRequest) {
		CustomerResponse createdCustomer = customerService.createCustomer(customerRequest);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdCustomer);
	}

	@Hidden // To hide this endpoint from Swagger UI
	@GetMapping
	public void hiddenRESTApiUsingSwagger() {

	}

	@GetMapping("/{customerId}")
	public ResponseEntity<CustomerResponse> getCustomerById(@PathVariable(name = "customerId") Long customerId) {
		CustomerResponse customer = customerService.getCustomerById(customerId);
		if (customer != null) {
			return ResponseEntity.status(HttpStatus.FOUND).body(customer);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping
	public ResponseEntity<List<Customer>> getAllCustomers() {
		List<Customer> customers = customerService.getAllCustomers();
		return ResponseEntity.ok(customers);
	}

	@PutMapping("/{id}")
	public ResponseEntity<CustomerResponse> updateCustomer(@PathVariable(name = "id") Long id,
			@RequestBody CustomerRequest customerRequest) {
		CustomerResponse updatedCustomer = customerService.updateCustomer(id, customerRequest);
		if (updatedCustomer != null) {
			return ResponseEntity.ok(updatedCustomer);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteCustomer(@PathVariable(name = "id") Long id) {
		boolean deleted = customerService.deleteCustomer(id);
		if (deleted) {
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
