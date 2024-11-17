package com.anonymous.order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDto {

	private String addressId;
	private String nameForAddres;
	private long mobileNumber;
	private long pincode;
	private String address;
	private String city;
	private String state;
	private String landmark;
}
