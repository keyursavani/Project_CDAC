package com.health_sync.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.health_sync.custome_exception.HealthSynsException;
import com.health_sync.dao.PatientDao;
import com.health_sync.dto.PatientInsuranceReqDto;
import com.health_sync.dto.PatientRecordDto;
import com.health_sync.dto.RegisterPatientDto;
import com.health_sync.dto.SignInDto;
import com.health_sync.dto.SignInResponseJwtDto;
import com.health_sync.pojos.Doctor;
import com.health_sync.pojos.InsuranceRequest;
import com.health_sync.pojos.Patient;
import com.health_sync.pojos.UseRole;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class PatientServicesImpl implements PatientServices{

	private PatientDao patientDao;
	private PasswordEncoder encoder;
	private ModelMapper modelMapper;

	@Override
	public String registerPatient(RegisterPatientDto dto) {
		try {
			dto.setPassword(encoder.encode(dto.getPassword()));
			Patient patient = patientDao.save( modelMapper.map(dto, Patient.class));
			return "Successfully register as a patient with id "+patient.getId();
		}catch(Exception e) {
			throw new HealthSynsException("Email is already exists");
		}
	}

	@Override
	public SignInResponseJwtDto patientLogin(SignInDto dto) {
		 Patient patient = patientDao.findByEmail(dto.getEmail())
				 .orElseThrow(() -> new HealthSynsException("Invalid email and password"));
		 if(!encoder.matches(dto.getPassword(), patient.getPassword()))
			 new HealthSynsException("Invalid email and password");
		 SignInResponseJwtDto pdto = modelMapper.map(patient, SignInResponseJwtDto.class);
		 pdto.setRole(UseRole.PATIENT);
		return pdto;
	}
	
	@Override
	public PatientRecordDto getMedicalRecord(Long id) {
		if(!patientDao.existsById(id))
			throw new HealthSynsException("Invalid patient id");
		Patient records = patientDao.getMedicalRecords(id);
		return modelMapper.map(records, PatientRecordDto.class);
	}
	
	@Override
	public PatientInsuranceReqDto getInsuranceRequests(Long patientId) {
		if(!patientDao.existsById(patientId))
			throw new HealthSynsException("Invalid patient id");
	 Patient requests = patientDao.getInsuranceRequests(patientId);
		return modelMapper.map(requests, PatientInsuranceReqDto.class);
	}
	
}
