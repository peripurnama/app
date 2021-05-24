package id.co.iteacode.controller;

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

import id.co.iteacode.dto.jabatan.JabatanRequestAddDto;
import id.co.iteacode.dto.jabatan.JabatanRequestEditDto;
import id.co.iteacode.model.Jabatan;
import id.co.iteacode.service.BagianService;
import id.co.iteacode.service.JabatanService;


@Controller
@RequestMapping("/jabatan")
public class JabatanController {

	@Autowired
	private JabatanService jabatanService;
	
	@Autowired
	private BagianService bagianService;
	
	@GetMapping
	public String index(Model model, @SortDefault("id") @PageableDefault(size = 5) Pageable pageable,
			@RequestParam(name = "search", required = false) String search) {
		model.addAttribute("key", search);
		model.addAttribute("page", jabatanService.pagination(search, pageable));
		return "jabatan/index";
	}

	@GetMapping("add")
	public String add(Model model, JabatanRequestAddDto jabatanRequestAddDto) {
		model.addAttribute("bagians", bagianService.findAll());
		return "jabatan/add";
	}

	@PostMapping("add")
	public String add(Model model, @Valid JabatanRequestAddDto jabatanRequestAddDto, BindingResult result) {
		
		System.out.println(result);
		if(result.hasErrors()) {
			model.addAttribute("bagians", bagianService.findAll());
			return "jabatan/add";
		}
		jabatanService.add(jabatanRequestAddDto);
		return "redirect:/jabatan";
	}

	@GetMapping("{id}/edit")
	public String edit(@PathVariable("id") long id, Model model, JabatanRequestEditDto jabatanRequestEditDto) {
		Jabatan jabatan = jabatanService.findById(id);
		model.addAttribute("bagians", bagianService.findAll());
		model.addAttribute("jabatanRequestEditDto", jabatanService.convertToDto(jabatan));
		
		System.out.println(jabatanService.convertToDto(jabatan));
		return "jabatan/edit";
	}

	@PostMapping("{id}/edit")
	public String edit(@PathVariable("id") long id, Model model, @Valid JabatanRequestEditDto jabatanRequestEditDto,
			BindingResult result) {
		System.out.println(result);
		if(result.hasErrors()) {
			Jabatan jabatan = jabatanService.findById(id);
			model.addAttribute("bagians", bagianService.findAll());
			model.addAttribute("jabatanRequestEditDto", jabatanService.convertToDto(jabatan));
			return "jabatan/edit";
		}
		jabatanService.edit(jabatanRequestEditDto);
		return "redirect:/jabatan";
	}

	@GetMapping("{id}")
	public String findById(@PathVariable("id") long id, Model model,
			JabatanRequestEditDto jabatanRequestEditDto) {
		Jabatan jabatan = jabatanService.findById(id);
		model.addAttribute("jabatan", jabatan);
		model.addAttribute("bagians", bagianService.findAll());
		model.addAttribute("jabatanRequestEditDto", jabatanService.convertToDto(jabatan));
		return "jabatan/info";
	}

	@GetMapping("{id}/delete")
	@ResponseBody
	public boolean delete(@PathVariable("id") long id, Model model) {
		boolean delete = jabatanService.delete(id);
		return delete;
	}
	
}
