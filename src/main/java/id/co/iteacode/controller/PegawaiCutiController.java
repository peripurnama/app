package id.co.iteacode.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
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

import id.co.iteacode.dto.pegawaicuti.PegawaiCutiRequestAddDto;
import id.co.iteacode.dto.pengajuancuti.PegawaiCutiRequestEditDto;
import id.co.iteacode.model.Cuti;
import id.co.iteacode.model.Pegawai;
import id.co.iteacode.model.PegawaiCuti;
import id.co.iteacode.service.CutiService;
import id.co.iteacode.service.PegawaiCutiDetailService;
import id.co.iteacode.service.PegawaiCutiService;
import id.co.iteacode.service.PegawaiService;

@Controller
@RequestMapping("/pegawai-cuti")
public class PegawaiCutiController {

	@Autowired
	private PegawaiCutiService pegawaiCutiService;

	@Autowired
	private PegawaiService pegawaiService;

	@Autowired
	private CutiService cutiService;

	@Autowired
	private PegawaiCutiDetailService pegawaiCutiDetailService;

	@GetMapping
	public String index(Model model, @SortDefault("id") @PageableDefault(size = 5) Pageable pageable,
			@RequestParam(name = "search", required = false) String search) {
		model.addAttribute("key", search);
		model.addAttribute("page", pegawaiCutiService.pagination(search, pageable));
		return "pegawai-cuti/index";
	}

	@GetMapping("add")
	public String add(Model model, PegawaiCutiRequestAddDto pegawaiCutiRequestAddDto,
			@RequestParam(name = "status", required = false) String status, Pageable pageable) {
//		Map<Integer, Cuti> cutiGrups = cutiService.findAll().stream()
//				.collect(Collectors.toMap(Cuti::getTahun, (value) -> value, (oldValue, newValue) -> oldValue));

		Map<Integer, List<Cuti>> groupedByTahun = cutiService.findAll().stream()
				.collect(Collectors.groupingBy(Cuti::getTahun));

		// model.addAttribute("pegawais", pegawaiService.findAll());
		model.addAttribute("cutis", groupedByTahun);

		if (!pegawaiService.statusPekerjaan.contains(status)) {
			// model.addAttribute("messages", "Request tidak valid");
			return index(model, pageable, null);
		}
		if (cutiService.filterCutiByTypeAndYear(status) == null) {
			model.addAttribute("messages", "Management cuti belum di setting");
			return index(model, pageable, null);
		}
		Set<Pegawai> isReadyPegawaiCuti = pegawaiService.filterPegawaiAndCutiIsReadyPegawaiCuti(status,
				cutiService.filterCutiByTypeAndYear(status));
		if (pegawaiService.filterByStatusPekerjaan(status).isEmpty()) {
			model.addAttribute("messages", "Tidak ada pegawai dengan status pekerjaan [" + status + "]");
			return index(model, pageable, null);
		}

		if (isReadyPegawaiCuti.isEmpty()) {
			System.out.println(isReadyPegawaiCuti.size());
			model.addAttribute("messages", "Semua pegawai dengan status pekerjaan [" + status
					+ "] saat ini sudah di setting untuk cuti tahunan");
			return index(model, pageable, null);
		}
		pegawaiCutiRequestAddDto.setCutiId(cutiService.filterCutiByTypeAndYear(status).getId());
		model.addAttribute("isReadyPegawaiCuti", isReadyPegawaiCuti);
		model.addAttribute("cuti", cutiService.filterCutiByTypeAndYear(status));
		model.addAttribute("messages", "");
		return "pegawai-cuti/add";
	}

	@PostMapping("add")
	public String add(Model model, @Valid PegawaiCutiRequestAddDto pegawaiCutiRequestAddDto, BindingResult result) {
		System.out.println(pegawaiCutiRequestAddDto);
		System.out.println(result);
		if (result.hasErrors()) {
			Map<Integer, List<Cuti>> groupedByTahun = cutiService.findAll().stream()
					.collect(Collectors.groupingBy(Cuti::getTahun));

			model.addAttribute("pegawais", pegawaiService.findAll());
			model.addAttribute("cutis", groupedByTahun);
			return "pegawai-cuti/add";
		}
		pegawaiCutiService.add(pegawaiCutiRequestAddDto);
		return "redirect:/pegawai-cuti";
	}

	@GetMapping("{id}/edit")
	public String edit(@PathVariable("id") long id, Model model, PegawaiCutiRequestEditDto pegawaiCutiRequestEditDto) {
		PegawaiCuti pegawaiCuti = pegawaiCutiService.findById(id);
		Map<Integer, List<Cuti>> groupedByTahun = cutiService.findAll().stream()
				.collect(Collectors.groupingBy(Cuti::getTahun));

		model.addAttribute("pegawais", pegawaiService.findAll());
		model.addAttribute("cutis", groupedByTahun);
		model.addAttribute("pegawaiCutiRequestEditDto", pegawaiCutiService.convertToDto(pegawaiCuti));

		System.out.println(pegawaiCutiService.convertToDto(pegawaiCuti));
		return "pegawai-cuti/edit";
	}

	@PostMapping("{id}/edit")
	public String edit(@PathVariable("id") long id, Model model,
			@Valid PegawaiCutiRequestEditDto pegawaiCutiRequestEditDto, BindingResult result) {
		System.out.println(result);
		if (result.hasErrors()) {
			PegawaiCuti pegawaiCuti = pegawaiCutiService.findById(id);
			Map<Integer, List<Cuti>> groupedByTahun = cutiService.findAll().stream()
					.collect(Collectors.groupingBy(Cuti::getTahun));

			model.addAttribute("pegawais", pegawaiService.findAll());
			model.addAttribute("cutis", groupedByTahun);
			model.addAttribute("pegawaiCutiRequestEditDto", pegawaiCutiService.convertToDto(pegawaiCuti));
			return "pegawai-cuti/edit";
		}
		PegawaiCuti pegawaiCuti = pegawaiCutiService.findById(id);
		pegawaiCutiRequestEditDto.setPegawaiId(pegawaiCuti.getPegawai().getId());
		pegawaiCutiService.edit(pegawaiCutiRequestEditDto);
		return "redirect:/pegawai-cuti";
	}

	@GetMapping("{id}")
	public String findById(@PathVariable("id") long id, Model model,
			PegawaiCutiRequestEditDto pegawaiCutiRequestEditDto) {
		PegawaiCuti pegawaiCuti = pegawaiCutiService.findById(id);
		Map<Integer, List<Cuti>> groupedByTahun = cutiService.findAll().stream()
				.collect(Collectors.groupingBy(Cuti::getTahun));

		model.addAttribute("pegawais", pegawaiService.findAll());
		model.addAttribute("cutis", groupedByTahun);
		model.addAttribute("pegawaiCutiRequestEditDto", pegawaiCutiService.convertToDto(pegawaiCuti));
		model.addAttribute("pegawaiCuti", pegawaiCuti);
		return "pegawai-cuti/info";
	}

	@GetMapping("{id}/delete")
	@ResponseBody
	public boolean delete(@PathVariable("id") long id, Model model) {
		boolean delete = pegawaiCutiService.delete(id);
		return delete;
	}
}
