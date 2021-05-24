package id.co.iteacode.controller;

import java.time.Month;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import id.co.iteacode.model.PengajuanCuti;
import id.co.iteacode.service.PegawaiCutiDetailService;
import id.co.iteacode.service.PengajuanCutiService;

@Controller
public class HomeController {

	@Autowired
	private PengajuanCutiService pengajuanCutiService;
	
	@Autowired
	private PegawaiCutiDetailService pegawaiCutiDetailService;

	private String arrMonth[] = { "Januari", "Februari", "Maret", "April", "Mei", "Juni", "Juli", "Agustus",
			"September", "Oktober", "November", "Desember" };

	@GetMapping("/")
	public String index(Model model, Authentication authentication) {

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
			return "redirect:/pengajuan-cuti/add";
		}
		
		model.addAttribute("disetujui", pegawaiCutiDetailService.findByStatus("Disetujui").size());
		model.addAttribute("ditolak", pegawaiCutiDetailService.findByStatus("Ditolak").size());
		model.addAttribute("menunggu", pegawaiCutiDetailService.findByStatus("Menunggu Persetujuan").size());
		model.addAttribute("allStatus", pegawaiCutiDetailService.findAll().size());

		return "index";
	}

	@GetMapping("/grap")
	@ResponseBody
	public GraphPengajuanCuti graphPengajuanCuti() {
		List<PengajuanCuti> listPengajuanCuti = pengajuanCutiService.findAll();

		Map<String, List<PengajuanCuti>> collect = listPengajuanCuti.stream().map(obj -> {
			obj.getCreatedLocalDateTime().getMonth();
			return obj;
		}).collect(Collectors.groupingBy(obj -> obj.getCreatedLocalDateTime().getMonth().getDisplayName(TextStyle.FULL,
				new Locale("in", "ID"))));

		collect.entrySet().forEach(obj -> {
			System.out.println(obj.getKey());
			System.out.println(obj.getValue().size());
		});

		GraphPengajuanCuti g = new GraphPengajuanCuti();
		g.setLabel("Pengajuan Cuti");
		for (int i = 0; i < arrMonth.length; i++) {

			if (collect.containsKey(arrMonth[i])) {
				
				List<PengajuanCuti> list = collect.get(arrMonth[i]);
				switch (arrMonth[i]) {
				case "Januari":
					g.setB1(list.size());
					break;
				case "Februari":
					g.setB2(list.size());
					break;
				case "Maret":
					g.setB3(list.size());
					break;
				case "April":
					g.setB4(list.size());
					break;
				case "Mei":
					g.setB5(list.size());
					break;
				case "Juni":
					g.setB6(list.size());
					break;
				case "Juli":
					g.setB7(list.size());
					break;
				case "Agustus":
					g.setB8(list.size());
					break;
				case "September":
					g.setB9(list.size());
					break;
				case "Oktober":
					g.setB10(list.size());
					break;
				case "November":
					g.setB11(list.size());
					break;
				case "Desember":
					g.setB12(list.size());
					break;

				}
			}
		}

		System.out.println(g);

		return g;
	}
	
}

class GraphPengajuanCuti {
	private String label;
	private int b1;
	private int b2;
	private int b3;
	private int b4;
	private int b5;
	private int b6;
	private int b7;
	private int b8;
	private int b9;
	private int b10;
	private int b11;
	private int b12;
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public int getB1() {
		return b1;
	}
	public void setB1(int b1) {
		this.b1 = b1;
	}
	public int getB2() {
		return b2;
	}
	public void setB2(int b2) {
		this.b2 = b2;
	}
	public int getB3() {
		return b3;
	}
	public void setB3(int b3) {
		this.b3 = b3;
	}
	public int getB4() {
		return b4;
	}
	public void setB4(int b4) {
		this.b4 = b4;
	}
	public int getB5() {
		return b5;
	}
	public void setB5(int b5) {
		this.b5 = b5;
	}
	public int getB6() {
		return b6;
	}
	public void setB6(int b6) {
		this.b6 = b6;
	}
	public int getB7() {
		return b7;
	}
	public void setB7(int b7) {
		this.b7 = b7;
	}
	public int getB8() {
		return b8;
	}
	public void setB8(int b8) {
		this.b8 = b8;
	}
	public int getB9() {
		return b9;
	}
	public void setB9(int b9) {
		this.b9 = b9;
	}
	public int getB10() {
		return b10;
	}
	public void setB10(int b10) {
		this.b10 = b10;
	}
	public int getB11() {
		return b11;
	}
	public void setB11(int b11) {
		this.b11 = b11;
	}
	public int getB12() {
		return b12;
	}
	public void setB12(int b12) {
		this.b12 = b12;
	}
	@Override
	public String toString() {
		return "GraphPengajuanCuti [label=" + label + ", b1=" + b1 + ", b2=" + b2 + ", b3=" + b3 + ", b4=" + b4
				+ ", b5=" + b5 + ", b6=" + b6 + ", b7=" + b7 + ", b8=" + b8 + ", b9=" + b9 + ", b10=" + b10 + ", b11="
				+ b11 + ", b12=" + b12 + "]";
	}

}