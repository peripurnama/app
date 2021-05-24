package id.co.iteacode.controller;

import java.util.stream.Collectors;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import id.co.iteacode.dto.pegawai.PegawaiRequestAddDto;
import id.co.iteacode.dto.pegawai.PegawaiRequestEditDto;
import id.co.iteacode.dto.person.PersonRequestEditDto;
import id.co.iteacode.dto.user.CreateAccountRequestDto;
import id.co.iteacode.dto.user.EditAccountRequestDto;
import id.co.iteacode.dto.user.UserRequestAddDto;
import id.co.iteacode.dto.user.UserRequestEditDto;
import id.co.iteacode.model.Pegawai;
import id.co.iteacode.model.Role;
import id.co.iteacode.model.User;
import id.co.iteacode.service.JabatanService;
import id.co.iteacode.service.PegawaiService;
import id.co.iteacode.service.RoleService;
import id.co.iteacode.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private PegawaiService pegawaiService;

	@Autowired
	private RoleService roleService;

	@Autowired
	private JabatanService jabatanService;

	@GetMapping
	public String index(Model model, @SortDefault("username") @PageableDefault(size = 5) Pageable pageable,
			@RequestParam(name = "search", required = false) String search) {
		model.addAttribute("page", userService.pagination(search, pageable));
		model.addAttribute("pegawais", pegawaiService.findByUserIsNull());
		return "user/index";
	}

	@GetMapping("/{id}")
	public String findById(@PathVariable("id") long id, Model model) {
		User user = userService.findById(id);
		model.addAttribute("user", user);
		return "user/info";
	}

	@GetMapping("/{id}/edit")
	public String edit(@PathVariable("id") long id, Model model, UserRequestEditDto userRequestEditDto) {
		
		User user = userService.findById(id);
		UserRequestEditDto convertToDto = userService.convertToDto(user);
		model.addAttribute("roles", roleService.findAll());
		model.addAttribute("pegawai", pegawaiService.findById(convertToDto.getPegawaiId()));
		model.addAttribute("userRequestEditDto", convertToDto);
		
		return "user/edit";
	}
	
	@PostMapping("/{id}/edit")
	public String edit(@PathVariable("id") long id, Model model, @Valid UserRequestEditDto userRequestEditDto,
			BindingResult result) {
		
		if(result.hasErrors()) {
			User user = userService.findById(id);
			model.addAttribute("roles", roleService.findAll());
			model.addAttribute("pegawais", pegawaiService.findByUserIsNull());
			model.addAttribute("userRequestEditDto", userService.convertToDto(user));
			return "user/edit";
		}
		
		userService.edit(userRequestEditDto);
		
		return "redirect:/user";
	}

	@GetMapping("/{id}/delete")
	@ResponseBody
	public boolean delete(@PathVariable("id") long id, Model model) {
		boolean delete = userService.delete(id);
		
		return delete;
	}

	@GetMapping("add")
	public String add(Model model, UserRequestAddDto userRequestAddDto) {
		model.addAttribute("roles", roleService.findAll());
		model.addAttribute("jabatans", jabatanService.findAll());
		model.addAttribute("pegawais", pegawaiService.findByUserIsNull());
		return "user/add";
	}

	@Transactional
	@PostMapping("add")
	public String addOrSave(Model model, @Valid UserRequestAddDto userRequestAddDto, BindingResult result) {

		model.addAttribute("roles", roleService.findAll());
		model.addAttribute("jabatans", jabatanService.findAll());
		model.addAttribute("pegawais", pegawaiService.findByUserIsNull());
		if (result.hasErrors()) {
			return "user/add";
		}
		
		userService.add(userRequestAddDto);
		
		return "redirect:/user";
	}

}
