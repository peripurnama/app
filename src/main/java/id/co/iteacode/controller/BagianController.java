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

import id.co.iteacode.dto.bagian.BagianRequestAddDto;
import id.co.iteacode.dto.bagian.BagianRequestEditDto;
import id.co.iteacode.model.Bagian;
import id.co.iteacode.service.BagianService;
import id.co.iteacode.service.DepartementService;

@Controller
@RequestMapping("/bagian")
public class BagianController {
	
	@Autowired
	private BagianService bagianService;
	
	@Autowired
	private DepartementService departementService;

	@GetMapping
	public String index(Model model, @SortDefault("id") @PageableDefault(size = 5) Pageable pageable,
			@RequestParam(name = "search", required = false) String search) {
		model.addAttribute("key", search);
		model.addAttribute("page", bagianService.pagination(search, pageable));
		return "bagian/index";
	}

	@GetMapping("add")
	public String add(Model model, BagianRequestAddDto bagianRequestAddDto) {
		model.addAttribute("departements", departementService.findAll());
		return "bagian/add";
	}

	@PostMapping("add")
	public String add(Model model, @Valid BagianRequestAddDto bagianRequestAddDto, BindingResult result) {
		
		System.out.println(result);
		if(result.hasErrors()) {
			model.addAttribute("departements", departementService.findAll());
			return "departement/add";
		}
		
		bagianService.add(bagianRequestAddDto);
		
		return "redirect:/bagian";
	}

	@GetMapping("{id}/edit")
	public String edit(@PathVariable("id") long id, Model model, BagianRequestEditDto bagianRequestEditDto) {
		Bagian bagian = bagianService.findById(id);
		model.addAttribute("bagianRequestEditDto", bagianService.convertToDto(bagian));
		model.addAttribute("departements", departementService.findAll());
		return "bagian/edit";
	}

	@PostMapping("{id}/edit")
	public String edit(@PathVariable("id") long id, Model model, BagianRequestEditDto bagianRequestEditDto,
			BindingResult result) {
		System.out.println(result);
		if(result.hasErrors()) {
			Bagian bagian = bagianService.findById(id);
			model.addAttribute("bagianRequestEditDto", bagianService.convertToDto(bagian));
			model.addAttribute("departements", departementService.findAll());
			return "bagian/edit";
		}
		bagianService.edit(bagianRequestEditDto);
		
		return "redirect:/bagian";
	}

	@GetMapping("{id}")
	public String findById(@PathVariable("id") long id, Model model,
			BagianRequestEditDto bagianRequestEditDto) {
		Bagian bagian = bagianService.findById(id);
		model.addAttribute("bagianRequestEditDto", bagianService.convertToDto(bagian));
		model.addAttribute("departements", departementService.findAll());
		model.addAttribute("bagian", bagian);
		return "bagian/info";
	}

	@GetMapping("{id}/delete")
	@ResponseBody
	public boolean delete(@PathVariable("id") long id, Model model) {
		boolean delete = bagianService.delete(id);
		System.out.println(delete);
		return delete;
	}
	
}
