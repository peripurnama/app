package id.co.iteacode.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import id.co.iteacode.dto.pegawaicutidetail.PegawaiCutiDetailRequestAddDto;
import id.co.iteacode.dto.pegawaicutidetail.PegawaiCutiDetailRequestEditDto;
import id.co.iteacode.exception.EntityNotFoundException;
import id.co.iteacode.model.Pegawai;
import id.co.iteacode.model.PegawaiCuti;
import id.co.iteacode.model.PegawaiCutiDetail;
import id.co.iteacode.repository.PegawaiCutiDetailRepository;

@Service
public class PegawaiCutiDetailService {

	@Autowired
	private PegawaiCutiDetailRepository pegawaiCutiDetailRepository;

	@Autowired
	private PegawaiCutiService pegawaiCutiService;

	@Autowired
	private PengajuanCutiService pengajuanCutiService;

	public PegawaiCutiDetail findById(Long id) {
		return pegawaiCutiDetailRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Pegawai Cuti Detail id [" + id + "] not found"));
	}

	public PegawaiCutiDetail add(PegawaiCutiDetailRequestAddDto dto) {
		Long id = dto.getId();
		Long pegawaiCutiId = dto.getPegawaiCutiId();
		Long pengajuanCutiId = dto.getPengajuanCutiId();
		Integer sisaCutiTahunan = dto.getSisaCutiTahunan();
		String status = dto.getStatus();
		Integer tahun = LocalDate.now().getYear();
		Integer totalCuti = dto.getTotalCuti();

		PegawaiCutiDetail pegawaiCutiDetail = new PegawaiCutiDetail();
		pegawaiCutiDetail.setSisaCutiTahunan(sisaCutiTahunan);
		pegawaiCutiDetail.setPegawaiCuti(pegawaiCutiService.findById(pegawaiCutiId));
		pegawaiCutiDetail.setPengajuanCuti(pengajuanCutiService.findById(pengajuanCutiId));
		pegawaiCutiDetail.setStatus(status);
		pegawaiCutiDetail.setTahun(tahun);
		pegawaiCutiDetail.setTotalCuti(totalCuti);
		pegawaiCutiDetail.setApproved(false);
		pegawaiCutiDetail = pegawaiCutiDetailRepository.save(pegawaiCutiDetail);

		return pegawaiCutiDetail;
	}

	public PegawaiCutiDetail edit(PegawaiCutiDetailRequestEditDto dto) {
		Long id = dto.getId();
		Long pegawaiCutiId = dto.getPegawaiCutiId();
		Long pengajuanCutiId = dto.getPengajuanCutiId();
		Integer sisaCutiTahunan = dto.getSisaCutiTahunan();

		PegawaiCutiDetail pegawaiCutiDetail = findById(id);
		pegawaiCutiDetail.setSisaCutiTahunan(sisaCutiTahunan);
		pegawaiCutiDetail.setPegawaiCuti(pegawaiCutiService.findById(pegawaiCutiId));
		pegawaiCutiDetail.setPengajuanCuti(pengajuanCutiService.findById(pengajuanCutiId));

		pegawaiCutiDetail = pegawaiCutiDetailRepository.save(pegawaiCutiDetail);

		return pegawaiCutiDetail;
	}
	
	public PegawaiCutiDetail edit(PegawaiCutiDetail pegawaiCutiDetail) {
		pegawaiCutiDetail = pegawaiCutiDetailRepository.save(pegawaiCutiDetail);
		return pegawaiCutiDetail;
	}

	public void delete(long id) {
		PegawaiCutiDetail pegawaiCutiDetail = findById(id);
		pegawaiCutiDetail.setDeleted(true);
		pegawaiCutiDetailRepository.save(pegawaiCutiDetail);
	}

	public Page<PegawaiCutiDetail> pagination(String search, Pageable pageable) {
		return pegawaiCutiDetailRepository.findAll(pageable);
	}

	List<PegawaiCutiDetail> findByPegawaiCuti(PegawaiCuti pegawaiCuti) {
		return pegawaiCutiDetailRepository.findByPegawaiCuti(pegawaiCuti);
	}

	public Page<PegawaiCutiDetail> pagination(String search, String username, Pageable pageable) {
		return pegawaiCutiDetailRepository.findAllByPegawaiCutiPegawaiUserUsername(username, pageable);
	}
	
	public List<PegawaiCutiDetail> findByStatus(String status) {
		return pegawaiCutiDetailRepository.findByStatus(status);
	}
	
	public List<PegawaiCutiDetail> findAll() {
		return pegawaiCutiDetailRepository.findAll();
	}
}
