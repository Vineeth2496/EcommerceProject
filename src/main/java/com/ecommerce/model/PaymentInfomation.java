package com.ecommerce.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class PaymentInfomation {
	@Column(name = "cardholder_name")
	private String cardholderName;
	
	@Column(name = "card_number")
	private String cardNumber;
	
	@Column(name = "expiration_date")
	private String expirationDate;
	
	@Column(name = "cvv")
	private String cvv;
	
}
