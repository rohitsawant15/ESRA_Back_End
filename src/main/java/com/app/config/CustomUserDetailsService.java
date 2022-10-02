package com.app.config;

import com.app.Exception.UserNotFoundException;
import com.app.Repository.AdminRepository;
import com.app.Repository.HospitalRepository;
import com.app.Repository.PoliceStationRepository;
import com.app.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	private AdminRepository adminRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private HospitalRepository hospitalRepository;
	@Autowired
	private PoliceStationRepository policeStationRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		if (adminRepository.findAdminByEmail(email) != null) {
			System.out.println(adminRepository.findAdminByEmail(email));
			return new CustomUserDetails(adminRepository.findAdminByEmail(email));
		} else if (hospitalRepository.findByEmail(email) != null) {
			return new CustomUserDetails(hospitalRepository.findByEmail(email));
		} else if (policeStationRepository.findByEmail(email) != null) {
			return new CustomUserDetails(policeStationRepository.findByEmail(email));
		} else {
			throw new UserNotFoundException("No user");
		}
	}
}
