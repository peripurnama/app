package id.co.iteacode.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import id.co.iteacode.dto.user.UserRequestAddDto;
import id.co.iteacode.dto.user.UserRequestEditDto;
import id.co.iteacode.exception.EntityNotFoundException;
import id.co.iteacode.model.Pegawai;
import id.co.iteacode.model.Role;
import id.co.iteacode.model.User;
import id.co.iteacode.repository.UserRepository;

@Service
@Transactional
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleService roleService;

	@Autowired
	private PegawaiService pegawaiService;

	@Autowired
	private BCryptPasswordEncoder encryp;

	@Autowired
	private ModelMapper modelMapper;

	public User add(UserRequestAddDto dto) {

		String confirmPassword = dto.getConfirmPassword();
		String email = dto.getEmail();
		String password = dto.getPassword();
		Long pegawaiId = dto.getPegawaiId();
		Set<Long> roleIds = dto.getRoleIds();
		String username = dto.getUsername();

		Set<Role> roles = initRoleCheck(roleIds);

		User user = new User();
		user.setEmail(email);
		user.setPassword(encryp.encode(password));
		user.setRoles(roles);
		user.setUsername(username);
		user = userRepository.save(user);
		
		Pegawai pegawai = pegawaiService.findById(pegawaiId);
		pegawaiService.createUser(pegawai, user);
		
		
		return user;
	}

	public User edit(UserRequestEditDto dto) {
		String confirmPassword = dto.getConfirmPassword();
		String email = dto.getEmail();
		String password = dto.getPassword();
		Long pegawaiId = dto.getPegawaiId();
		Set<Long> roleIds = dto.getRoleIds();
		String username = dto.getUsername();
		Long id = dto.getId();

		Set<Role> roles = initRoleCheck(roleIds);

		User user = findById(id);
		user.setEmail(email);
		user.setPassword(encryp.encode(password));
		if (pegawaiId != null) {
			user.setPegawai(pegawaiService.findById(pegawaiId));
		}
		user.setRoles(roles);
		user.setUsername(username);

		user = userRepository.save(user);

		return user;
	}

	public boolean delete(Long id) {
		User user = findById(id);
		try {
			userRepository.delete(user);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

	public User findById(Long id) {
		return userRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("User id [" + id + "] not found"));
	}

	public Page<User> pagination(String search, Pageable pageable) {
		if (search != null) {
			return userRepository
					.findByDeletedFalseAndUsernameContainingIgnoreCaseOrEmailContainingIgnoreCase(
							search, search, pageable);
		} else {
			return userRepository.findByDeletedFalse(pageable);
		}
	}

	private Set<Role> initRoleCheck(Set<Long> roleIds) {
		Set<Role> roles = new HashSet<>();

		if (!roleIds.isEmpty()) {
			for (long roleId : roleIds) {
				Role role = roleService.findById(roleId);
				roles.add(role);
			}
		}
		return roles;
	}

	public UserRequestEditDto convertToDto(User user) {
		
		UserRequestEditDto userRequestEditDto = modelMapper.map(user, UserRequestEditDto.class);
		Set<Long> roleIds = user.getRoles().stream().map(Role::getId).collect(Collectors.toSet());
		userRequestEditDto.setRoleIds(roleIds);
		if (user.getPegawai() != null) {
			userRequestEditDto.setPegawaiId(user.getPegawai().getId());
		}
		System.out.println(userRequestEditDto);
		return userRequestEditDto;
	}

	public User findByUsername(String username) {
		return userRepository.findByUsername(username)
				.orElseThrow(() -> new EntityNotFoundException("User [" + username + "] not found"));
	}
	
	public List<User> findAll() {
		return userRepository.findAll();
	}
}
