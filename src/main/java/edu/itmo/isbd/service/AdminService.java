package edu.itmo.isbd.service;

import edu.itmo.isbd.entity.Admin;
import edu.itmo.isbd.entity.Arbitration;
import edu.itmo.isbd.entity.Farmer;
import edu.itmo.isbd.exception.UserAlreadyRegisteredException;
import edu.itmo.isbd.repository.AdminRepository;
import edu.itmo.isbd.repository.ArbitrationRepository;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class AdminService{
	@Autowired
	private AdminRepository adminRepository;

	@Autowired
	private ArbitrationRepository arbitrationRepository;

	@Autowired
	@Lazy
	private PasswordEncoder passwordEncoder;

	@PersistenceContext
	private Session session;

	public Admin registration(Admin user){
		return adminRepository.save(user);
	}

	public Admin saveAdminOrThrow(Admin admin){
		if (!checkAdminExists(admin.getLogin())) {
			admin.setPassword(passwordEncoder.encode(admin.getPassword()));
			return adminRepository.save(admin);
		}
		else throw new UserAlreadyRegisteredException("Admin with username " + admin.getLogin() + " already exists.");
	}

	@Transactional
	public Optional<Admin> findAdmin(String login) {
		return adminRepository.findAdminByLogin(login);
	}

	@Transactional
	public boolean checkAdminExists(String login){
		return adminRepository.existsAdminByLogin(login);
	}

	public Admin getAdminOrThrow(String login) throws UsernameNotFoundException {
			return adminRepository
					.findAdminByLogin(login)
					.orElseThrow(() -> new UsernameNotFoundException("Admin with username " + login + " was not found"));
	}

	public Admin getRandomAdmin(){
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		Criteria criteria = session.createCriteria(Admin.class);
		criteria.add(Restrictions.sqlRestriction("1=1 order by random()"));
		criteria.setMaxResults(1);
		return (Admin) criteria.list().get(0);
	}

	public List<Arbitration> getAdminArbitrations(String login){
		return arbitrationRepository.findAllByAdminLogin(login);
	}
}
