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

import id.co.iteacode.dto.departement.DepartementRequestAddDto;
import id.co.iteacode.dto.departement.DepartementRequestEditDto;
import id.co.iteacode.model.Departement;
import id.co.iteacode.service.DepartementService;

@Controller
@RequestMapping("/departement")
public class DepartementController {
	
	@Autowired
	private DepartementService departementService;

	@GetMapping
	public String index(Model model, @SortDefault("id") @PageableDefault(size = 5) Pageable pageable,
			@RequestParam(name = "search", required = false) String search) {
		model.addAttribute("key", search);
		model.addAttribute("page", departementService.pagination(search, pageable));
		return "departement/index";
	}

	@GetMapping("add")
	public String add(Model model, DepartementRequestAddDto departementRequestAddDto) {
		return "departement/add";
	}

	@PostMapping("add")
	public String add(Model model, @Valid DepartementRequestAddDto departementRequestAddDto, BindingResult result) {
		System.out.println(result);
		if(result.hasErrors()) {
			return "departement/add";
		}
		
		departementService.add(departementRequestAddDto);
		return "redirect:/departement";
	}

	@GetMapping("{id}/edit")
	public String edit(@PathVariable("id") long id, Model model, DepartementRequestEditDto departementRequestEditDto) {
		Departement departement = departementService.findById(id);
		model.addAttribute("departementRequestEditDto", departementService.convertToDto(departement));
		return "departement/edit";
	}

	@PostMapping("{id}/edit")
	public String edit(@PathVariable("id") long id, Model model, @Valid DepartementRequestEditDto departementRequestEditDto,
			BindingResult result) {
		System.out.println(result);
		if(result.hasErrors()) {
			Departement departement = departementService.findById(id);
			model.addAttribute("departementRequestEditDto", departementService.convertToDto(departement));
			return "departement/add";
		}
		departementService.edit(departementRequestEditDto);
		return "redirect:/departement";
	}

	@GetMapping("{id}")
	public String findById(@PathVariable("id") long id, Model model,
			DepartementRequestEditDto departementRequestEditDto) {
		Departement departement = departementService.findById(id);
		model.addAttribute("departementRequestEditDto", departementService.convertToDto(departement));
		model.addAttribute("departement", departement);
		return "departement/info";
	}

	@GetMapping("{id}/delete")
	@ResponseBody
	public boolean delete(@PathVariable("id") long id, Model model) {
		boolean delete = departementService.delete(id);
		return delete;
	}
}
