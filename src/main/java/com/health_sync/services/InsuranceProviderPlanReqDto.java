package com.health_sync.services;

import java.util.ArrayList;
import java.util.List;

import com.health_sync.pojos.InsuranceRequest;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class InsuranceProviderPlanReqDto {

	private Long id;
	private String companyName;
	private long contactNumber;
	private String email;
	private String address;
	private List<InsuranceRequest> insuranceRequests = new ArrayList<>();
}
