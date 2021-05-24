package id.co.iteacode.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.stream.Collectors;

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
import id.co.iteacode.dto.user.ChangeStaffAccountRequestDto;
import id.co.iteacode.dto.user.CreateStaffAccountRequestDto;
import id.co.iteacode.dto.user.UserRequestAddDto;
import id.co.iteacode.dto.user.UserRequestEditDto;
import id.co.iteacode.model.Pegawai;
import id.co.iteacode.model.User;
import id.co.iteacode.service.CutiService;
import id.co.iteacode.service.JabatanService;
import id.co.iteacode.service.PegawaiCutiService;
import id.co.iteacode.service.PegawaiService;
import id.co.iteacode.service.RoleService;
import id.co.iteacode.service.UserService;

@Controller
@RequestMapping("/pegawai")
public class PegawaiController {

	@Autowired
	private PegawaiService pegawaiService;

	@Autowired
	private JabatanService jabatanService;


	@Autowired
	private CutiService cutiService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;

	@Autowired
	private PegawaiCutiService pegawaiCutiService;

	@GetMapping
	public String index(Model model, @SortDefault("id") @PageableDefault(size = 5) Pageable pageable,
			@RequestParam(name = "search", required = false) String search) {
		model.addAttribute("key", search);
		model.addAttribute("page", pegawaiService.pagination(search, pageable));
		return "pegawai/index";
	}

	@GetMapping("add")
	public String add(Model model, PegawaiRequestAddDto pegawaiRequestAddDto) {
		model.addAttribute("jabatans", jabatanService.findAll());
		model.addAttribute("roles", roleService.findAll());
		return "pegawai/add";
	}

	@PostMapping("add")
	public String add(Model model, @Valid PegawaiRequestAddDto pegawaiRequestAddDto, BindingResult result) {

		System.out.println(result);
		if (result.hasErrors()) {
			model.addAttribute("jabatans", jabatanService.findAll());
			model.addAttribute("roles", roleService.findAll());
			return "pegawai/add";
		}
		
		Pegawai pegawai = pegawaiService.add(pegawaiRequestAddDto);
		System.out.println("Pegawai -> " + pegawai);
		return "redirect:/pegawai";
	}

	@GetMapping("{id}/edit")
	public String edit(@PathVariable("id") long id, Model model, PegawaiRequestEditDto pegawaiRequestEditDto) {

		Pegawai pegawai = pegawaiService.findById(id);
		model.addAttribute("pegawaiRequestEditDto", pegawaiService.convertToDto(pegawai));
		model.addAttribute("jabatans", jabatanService.findAll());
		model.addAttribute("roles", roleService.findAll());
		System.out.println(pegawaiService.convertToDto(pegawai));
		return "pegawai/edit";
	}

	@PostMapping("{id}/edit")
	public String edit(@PathVariable("id") long id, Model model, @Valid PegawaiRequestEditDto pegawaiRequestEditDto,
			BindingResult result) {
		System.out.println(result);
		if (result.hasErrors()) {
			Pegawai pegawai = pegawaiService.findById(id);
			model.addAttribute("pegawaiRequestEditDto", pegawaiService.convertToDto(pegawai));
			model.addAttribute("jabatans", jabatanService.findAll());
			model.addAttribute("roles", roleService.findAll());
			return "pegawai/edit";
		}
		Pegawai pegawai = pegawaiService.edit(pegawaiRequestEditDto);
		System.out.println("Pegawai -> " + pegawai);
		return "redirect:/pegawai";
	}

	@GetMapping("{id}")
	public String findById(@PathVariable("id") long id, Model model) {
		Pegawai pegawai = pegawaiService.findById(id);
		model.addAttribute("pegawai", pegawai);
		return "pegawai/info";
	}

	@GetMapping("{id}/delete")
	@ResponseBody
	public boolean delete(@PathVariable("id") long id, Model model) {
		boolean delete = pegawaiService.delete(id);
		return delete;
	}
 
}
