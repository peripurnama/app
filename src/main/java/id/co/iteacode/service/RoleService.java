package id.co.iteacode.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import id.co.iteacode.exception.EntityNotFoundException;
import id.co.iteacode.model.Role;
import id.co.iteacode.repository.RoleRepository;

@Service
public class RoleService {

	@Autowired
	private RoleRepository roleRepository;
	
	public Role findById(Long id) {
		return roleRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Role id [" + id + "] not found"));
	}
	
	public List<Role> findAll() {
		return roleRepository.findAll();
	}
}
