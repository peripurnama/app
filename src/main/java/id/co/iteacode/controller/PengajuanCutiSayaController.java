package id.co.iteacode.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import id.co.iteacode.service.PegawaiCutiDetailService;

@Controller
@RequestMapping("/pengajuan-cuti-saya")
public class PengajuanCutiSayaController {

	@Autowired
	private PegawaiCutiDetailService pegawaiCutiDetailService;
	
	@GetMapping
	public String index(Model model, @SortDefault("id") @PageableDefault(size = 5) Pageable pageable,
			@RequestParam(name = "search", required = false) String search, Principal principal) {
		model.addAttribute("key", search);
		model.addAttribute("page", pegawaiCutiDetailService.pagination(search, principal.getName(), pageable));
		return "pengajuan-cuti-saya/index";
	}
	
}
