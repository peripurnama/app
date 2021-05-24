package id.co.iteacode.controller;

import java.util.List;

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

import id.co.iteacode.dto.cuti.CutiRequestAddDto;
import id.co.iteacode.dto.cuti.CutiRequestEditDto;
import id.co.iteacode.model.Cuti;
import id.co.iteacode.service.CutiService;

@Controller
@RequestMapping("/cuti")
public class CutiController {

	@Autowired
	private CutiService cutiService;

	@GetMapping
	public String index(Model model, @SortDefault("id") @PageableDefault(size = 5) Pageable pageable,
			@RequestParam(name = "search", required = false) String search) {

		model.addAttribute("key", search);
		model.addAttribute("page", cutiService.pagination(search, pageable));

		return "cuti/index";
	}

	@GetMapping("add")
	public String add(Model model, CutiRequestAddDto cutiRequestAddDto,
			@SortDefault("id") @PageableDefault(size = 5) Pageable pageable) {

		if (cutiService.availableYears().isEmpty()) {
			model.addAttribute("messages", "Type dan periode yang tersedia sudah di diterapkan");
			return index(model, pageable, null);
		}

		model.addAttribute("typesAndYears", cutiService.availableYears());
		model.addAttribute("messages", "");
		return "cuti/add";
	}

	@PostMapping("add")
	public String add(Model model, @Valid CutiRequestAddDto cutiRequestAddDto, BindingResult result,
			@SortDefault("id") @PageableDefault(size = 5) Pageable pageable) {
		System.out.println(result);
		if (result.hasErrors()) {
			if (cutiService.availableYears().isEmpty()) {
				model.addAttribute("messages", "Type dan periode yang tersedia sudah di diterapkan");
				return index(model, pageable, null);
			}

			model.addAttribute("typesAndYears", cutiService.availableYears());
			model.addAttribute("messages", "");
			return "cuti/add";
		}

		cutiService.add(cutiRequestAddDto);

		return "redirect:/cuti";
	}

	@GetMapping("{id}/edit")
	public String edit(@PathVariable("id") long id, Model model, CutiRequestEditDto cutiRequestEditDto) {
		Cuti cuti = cutiService.findById(id);
		model.addAttribute("typesAndYears", cutiService.availableYears());
		model.addAttribute("cutiRequestEditDto", cutiService.convertToDto(cuti));
		System.out.println(cutiService.convertToDto(cuti));
		return "cuti/edit";
	}

	@PostMapping("{id}/edit")
	public String edit(@PathVariable("id") long id, Model model, CutiRequestEditDto cutiRequestEditDto,
			BindingResult result) {
		System.out.println(result);
		if (result.hasErrors()) {
			Cuti cuti = cutiService.findById(id);
			model.addAttribute("typesAndYears", cutiService.availableYears());
			model.addAttribute("cutiRequestEditDto", cutiService.convertToDto(cuti));
			return "cuti/edit";
		}

		cutiService.edit(cutiRequestEditDto);

		return "redirect:/cuti";
	}

	@GetMapping("{id}")
	public String findById(@PathVariable("id") long id, Model model, CutiRequestEditDto cutiRequestEditDto) {
		Cuti cuti = cutiService.findById(id);
		model.addAttribute("cuti", cuti);
		model.addAttribute("cutiRequestEditDto", cutiService.convertToDto(cuti));
		return "cuti/info";
	}

	@GetMapping("{id}/delete")
	@ResponseBody
	public boolean delete(@PathVariable("id") long id, Model model) {
		boolean delete = cutiService.delete(id);
		return delete;
	}
}