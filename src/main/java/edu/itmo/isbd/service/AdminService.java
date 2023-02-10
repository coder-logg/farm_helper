package edu.itmo.isbd.service;

import edu.itmo.isbd.entity.Admin;
import edu.itmo.isbd.entity.User;
import edu.itmo.isbd.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService extends UserService{
	@Autowired
	private AdminRepository adminRepository;

//	@Override
	public Admin registration(Admin user){
		super.registration(user);
		return adminRepository.save(user);
	}
}
