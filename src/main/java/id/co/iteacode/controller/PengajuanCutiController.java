package id.co.iteacode.controller;

import java.security.Principal;
import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import id.co.iteacode.dto.pegawaicutidetail.PegawaiCutiDetailRequestAddDto;
import id.co.iteacode.dto.pengajuancuti.PengajuanCutiRequestAddDto;
import id.co.iteacode.dto.pengajuancuti.PengajuanCutiRequestEditDto;
import id.co.iteacode.model.Pegawai;
import id.co.iteacode.model.PengajuanCuti;
import id.co.iteacode.model.User;
import id.co.iteacode.service.PegawaiCutiDetailService;
import id.co.iteacode.service.PegawaiService;
import id.co.iteacode.service.PengajuanCutiService;
import id.co.iteacode.service.UserService;

@Controller
@RequestMapping("/pengajuan-cuti")
public class PengajuanCutiController {

	@Autowired
	private PengajuanCutiService pengajuanCutiService;

	@Autowired
	private PegawaiCutiDetailService pegawaiCutiDetailService;

	@Autowired
	private PegawaiService pegawaiService;

	@Autowired
	private UserService userService;

	@GetMapping
	public String index(Model model, @SortDefault("id") @PageableDefault(size = 5) Pageable pageable,
			@RequestParam(name = "search", required = false) String search, Principal principal,
			Authentication authentication) {

		boolean isPimpinan = false;
		boolean isHr = false;
		boolean isKaryawan = false;
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		for (GrantedAuthority grantedAuthority : authorities) {
			if (grantedAuthority.getAuthority().equals("ROLE_PIMPINAN")) {
				System.out.println("ROLE_PIMPINAN");
				isPimpinan = true;
				break;
			} else if (grantedAuthority.getAuthority().equals("ROLE_HR")) {
				System.out.println("ROLE_HR");
				isHr = true;
				break;
			} else if (grantedAuthority.getAuthority().equals("ROLE_KARYAWAN")) {
				System.out.println("ROLE_KARYAWAN");
				isKaryawan = true;
				break;
			}
		}

		if (isKaryawan) {
			return "error/403";
		}
		
		User user = userService.findByUsername(principal.getName());
		Pegawai pegawai = user.getPegawai();
		boolean cekCutiPegawai = pengajuanCutiService.cekCutiPegawai(pegawai);
		model.addAttribute("cekCutiPegawai", cekCutiPegawai);
		model.addAttribute("messages", pengajuanCutiService.getMessageCheckPegawaiCuti());

		model.addAttribute("key", search);
		model.addAttribute("page", pengajuanCutiService.pagination(search, pageable));
		return "pengajuan-cuti/index";
	}

	@GetMapping("add")
	public String add(Model model, PengajuanCutiRequestAddDto pengajuanCutiRequestAddDto, Principal principal) {
		User user = userService.findByUsername(principal.getName());
		boolean cekCutiPegawai = pengajuanCutiService.cekCutiPegawai(user.getPegawai());
		if (!cekCutiPegawai) {
			model.addAttribute("message", pengajuanCutiService.getMessageCheckPegawaiCuti());
		}

		return "pengajuan-cuti/add";
	}

	@PostMapping("add")
	public String add(Model model, @Valid PengajuanCutiRequestAddDto pengajuanCutiRequestAddDto, BindingResult result,
			Principal principal) {

		System.out.println(result);
		if (result.hasErrors()) {
			return "pengajuan-cuti/add";
		}

		User user = userService.findByUsername(principal.getName());
		pengajuanCutiRequestAddDto.setPegawaiId(user.getPegawai().getId());
		System.out.println(pengajuanCutiRequestAddDto);
		PengajuanCuti pengajuanCuti = pengajuanCutiService.add(pengajuanCutiRequestAddDto);

		return "redirect:/pengajuan-cuti-saya";
	}

	@GetMapping("{id}/edit")
	public String edit(@PathVariable("id") long id, Model model,
			PengajuanCutiRequestEditDto pengajuanCutiRequestEditDto) {
		PengajuanCuti pengajuanCuti = pengajuanCutiService.findById(id);
		model.addAttribute("pengajuanCutiRequestEditDto", pengajuanCutiService.convertToDto(pengajuanCuti));
		return "pengajuan-cuti/edit";
	}

	@PostMapping("{id}/edit")
	public String edit(@PathVariable("id") long id, Model model,
			@Valid PengajuanCutiRequestEditDto pengajuanCutiRequestEditDto, BindingResult result) {
		if (result.hasErrors()) {
			PengajuanCuti pengajuanCuti = pengajuanCutiService.findById(id);
			model.addAttribute("pengajuanCutiRequestEditDto", pengajuanCutiService.convertToDto(pengajuanCuti));
			return "pengajuan-cuti/edit";
		}

		pengajuanCutiService.edit(pengajuanCutiRequestEditDto);

		return "redirect:/pengajuan-cuti";
	}

	@GetMapping("{id}")
	public String findById(@PathVariable("id") long id, Model model,
			PengajuanCutiRequestEditDto pengajuanCutiRequestEditDto) {
		PengajuanCuti pengajuanCuti = pengajuanCutiService.findById(id);
		model.addAttribute("pengajuanCutiRequestEditDto", pengajuanCutiService.convertToDto(pengajuanCuti));
		return "pengajuan-cuti/info";
	}

	@GetMapping("{id}/delete")
	public String delete(@PathVariable("id") long id, Model model) {
		pengajuanCutiService.delete(id);
		return "redirect:/pengajuan-cuti";
	}

}
