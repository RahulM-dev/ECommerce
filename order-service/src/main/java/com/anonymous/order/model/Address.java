package com.anonymous.order.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Address {

	private String nameForAddres;
	private long mobileNumber;
	private long pincode;
	private String address;
	private String city;
	private String state;
	private String landmark;

}
