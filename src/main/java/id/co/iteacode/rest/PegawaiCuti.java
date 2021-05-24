package id.co.iteacode.rest;

import java.security.Principal;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import id.co.iteacode.model.PegawaiCutiDetail;
import id.co.iteacode.model.Role;
import id.co.iteacode.service.PegawaiCutiDetailService;
import id.co.iteacode.service.PengajuanCutiService;

@RestController
@RequestMapping("/api/pengajuan-cuti")
public class PegawaiCuti {

	@Autowired
	private PegawaiCutiDetailService pegawaiCutiDetailService;

	@Autowired
	private PengajuanCutiService pengajuanCutiService;

	@GetMapping("/{id}")
	public String isApproved(@PathVariable("id") long id, @RequestParam("approved") String approved,
			Principal principal, Authentication authentication) {
		PegawaiCutiDetail pegawaiCutiDetail = pegawaiCutiDetailService.findById(id);
		System.out.println("ID: " + id);
		System.out.println("approved: " + approved);
		System.out.println("pegawaiCutiDetail: " + pegawaiCutiDetail);
		
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
		
		Role role = pegawaiCutiDetail.getPegawaiCuti().getPegawai().getUser().getRoles().iterator().next();
		String name = role.getName();
		
		String msg = "";
		if (pengajuanCutiService.statusCuti(2).equals(approved)) {
			String username = pegawaiCutiDetail.getPegawaiCuti().getPegawai().getUser().getUsername();
			System.out.println("USERNAME: " + username);
			if (!username.equals(principal.getName())) {
				System.out.println("execute->!username.equals(principal.getName()");
				if(name.equalsIgnoreCase("ROLE_HR") && !isPimpinan) {
					msg = "Maaf! anda tidak bisa menyetujui cuti ini, silakan hubungi pimpinan untuk menyetujui cuti ini.";
				} else {
					pegawaiCutiDetail.setApproved(true);
					pegawaiCutiDetail.setStatus(pengajuanCutiService.statusCuti(2));
					pegawaiCutiDetail
							.setSisaCutiTahunan(pegawaiCutiDetail.getSisaCutiTahunan() - pegawaiCutiDetail.getTotalCuti());
					pegawaiCutiDetailService.edit(pegawaiCutiDetail);
					msg = "Sukses! cuti berhasil disetujui";
				}
			} else {
				msg = "Maaf! anda tidak bisa menyetujui cuti sendiri, silakan hubungi pimpinan untuk menyetujui cuti anda.";
			}
			
		} else if (pengajuanCutiService.statusCuti(3).equals(approved)) {
			String username = pegawaiCutiDetail.getPegawaiCuti().getPegawai().getUser().getUsername();
			if (!username.equals(principal.getName())) {
				System.out.println("pengajuanCutiService.statusCuti(3).equals(approved): " + pengajuanCutiService.statusCuti(3).equals(approved));
				if(name.equalsIgnoreCase("ROLE_HR") && !isPimpinan) {
					msg = "Maaf! anda tidak bisa menolak cuti ini, silakan hubungi pimpinan untuk menolak cuti ini.";
				} else {
					pegawaiCutiDetail.setDiscarded(true);
					pegawaiCutiDetail.setStatus(pengajuanCutiService.statusCuti(3));
					pegawaiCutiDetailService.edit(pegawaiCutiDetail);
					msg = "Sukses! cuti telah ditolak";
				}
				
			} else {
				msg = "Maaf! anda tidak bisa menolak cuti sendiri, silakan hubungi pimpinan untuk menolak cuti anda.";
			}
		}
		return msg;
	}

}
