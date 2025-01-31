package com.health_sync.services;

import java.util.List;

import com.health_sync.dto.DoctorRecordDto;
import com.health_sync.dto.InsuranceProviderPlansDto;
import com.health_sync.dto.PatientInsuranceReqDto;
import com.health_sync.dto.RegisterInsurancesProviderDto;
import com.health_sync.dto.SignInDto;
import com.health_sync.dto.SignInResponseJwtDto;

public interface InsuranceProviderServices {
	public String registerInsuranceProvider(RegisterInsurancesProviderDto dto);
	 public SignInResponseJwtDto insuranceProviderLogin(SignInDto dto);
	public InsuranceProviderPlansDto getMyInsurancePlan(Long id);
	public PatientInsuranceReqDto getInsuranceRequests(Long providerId);
}
