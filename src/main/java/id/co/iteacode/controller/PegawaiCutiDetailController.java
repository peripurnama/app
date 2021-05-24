package id.co.iteacode.controller;

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

import id.co.iteacode.dto.pegawaicutidetail.PegawaiCutiDetailRequestAddDto;
import id.co.iteacode.dto.pegawaicutidetail.PegawaiCutiDetailRequestEditDto;
import id.co.iteacode.service.PegawaiCutiDetailService;
import id.co.iteacode.service.PegawaiCutiService;
import id.co.iteacode.service.PengajuanCutiService;


@Controller
@RequestMapping("/pegawai-cuti-detail")
public class PegawaiCutiDetailController {
	
	@Autowired
	private PegawaiCutiDetailService pegawaiCutiDetailService;
	
	@Autowired
	private PegawaiCutiService pegawaiCutiService;
	
	@Autowired
	private PengajuanCutiService pengajuanCutiService;
	
	@GetMapping
	public String index(Model model, @SortDefault("id") @PageableDefault(size = 5) Pageable pageable,
			@RequestParam(name = "search", required = false) String search) {
		
		model.addAttribute("key", search);
		model.addAttribute("page", pegawaiCutiDetailService.pagination(search, pageable));
		return "pegawai-cuti-detail/index";
	}

	@GetMapping("add")
	public String add(Model model, PegawaiCutiDetailRequestAddDto pegawaiCutiDetailRequestAddDto) {
		return "pegawai-cuti-detail/add";
	}

	@PostMapping("add")
	public String add(Model model, PegawaiCutiDetailRequestAddDto pegawaiCutiDetailRequestAddDto, BindingResult result) {
		return "pegawai-cuti-detail/add";
	}

	@GetMapping("{id}/edit")
	public String edit(@PathVariable("id") long id, Model model, PegawaiCutiDetailRequestEditDto pegawaiCutiDetailRequestEditDto) {

		return "pegawai-cuti-detail/edit";
	}

	@PostMapping("{id}/edit")
	public String edit(@PathVariable("id") long id, Model model, PegawaiCutiDetailRequestEditDto pegawaiCutiDetailRequestEditDto,
			BindingResult result) {

		return "pegawai-cuti-detail/edit";
	}

	@GetMapping("{id}")
	public String findById(@PathVariable("id") long id, Model model,
			PegawaiCutiDetailRequestEditDto pegawaiCutiDetailRequestEditDto) {
		model.addAttribute("pegawaiCutiDetail", pegawaiCutiDetailService.findById(id));
		return "pegawai-cuti-detail/info";
	}

	@GetMapping("{id}/delete")
	public String delete(@PathVariable("id") long id, Model model) {

		return "pegawai-cuti-detail/info";
	}
}
