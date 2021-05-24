package id.co.iteacode.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import id.co.iteacode.dto.pegawaicuti.PegawaiCutiRequestAddDto;
import id.co.iteacode.dto.pengajuancuti.PegawaiCutiRequestEditDto;
import id.co.iteacode.exception.EntityNotFoundException;
import id.co.iteacode.model.Cuti;
import id.co.iteacode.model.Pegawai;
import id.co.iteacode.model.PegawaiCuti;
import id.co.iteacode.model.PegawaiCutiDetail;
import id.co.iteacode.repository.PegawaiCutiRepository;

@Service
public class PegawaiCutiService {

	@Autowired
	private PegawaiCutiRepository pegawaiCutiRepository;

	@Autowired
	private PegawaiService pegawaiService;

	@Autowired
	private CutiService cutiService;
	
	@Autowired
	private PegawaiCutiDetailService pegawaiCutiDetailService;

	@Autowired
	private ModelMapper modelMapper;

	public PegawaiCuti findById(Long id) {
		return pegawaiCutiRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("PegawaiCuti id [" + id + "] not found"));
	}

	public PegawaiCuti add(PegawaiCutiRequestAddDto dto) {

		Long cutiId = dto.getCutiId();
		Long id = dto.getId();
		Set<Long> pegawaiCutiDetailIds = dto.getPegawaiCutiDetailIds();
		Long pegawaiId = dto.getPegawaiId();
		
		Set<PegawaiCutiDetail> pegawaiCutiDetails = extractedIdToObject(pegawaiCutiDetailIds);

		PegawaiCuti pegawaiCuti = new PegawaiCuti();
		pegawaiCuti.setCuti(cutiService.findById(cutiId));
		pegawaiCuti.setPegawai(pegawaiService.findById(pegawaiId));
		
		if(!pegawaiCutiDetails.isEmpty()) {
			pegawaiCuti.setPegawaiCutiDetails(pegawaiCutiDetails);
		}
		
		pegawaiCuti = pegawaiCutiRepository.save(pegawaiCuti);

		return pegawaiCuti;
	}

	public PegawaiCuti edit(PegawaiCutiRequestEditDto dto) {
		Long cutiId = dto.getCutiId();
		Long id = dto.getId();
		Set<Long> pegawaiCutiDetailIds = dto.getPegawaiCutiDetailIds();
		Long pegawaiId = dto.getPegawaiId();
		
		Set<PegawaiCutiDetail> pegawaiCutiDetails = extractedIdToObject(pegawaiCutiDetailIds);

		PegawaiCuti pegawaiCuti = findById(id);
		pegawaiCuti.setCuti(cutiService.findById(cutiId));
		pegawaiCuti.setPegawai(pegawaiService.findById(pegawaiId));
		
		if(!pegawaiCutiDetails.isEmpty()) {
			pegawaiCuti.setPegawaiCutiDetails(pegawaiCutiDetails);
		}
		
		pegawaiCuti = pegawaiCutiRepository.save(pegawaiCuti);

		return pegawaiCuti;
	}

	private Set<PegawaiCutiDetail> extractedIdToObject(Set<Long> pegawaiCutiDetailIds) {
		Set<PegawaiCutiDetail> pegawaiCutiDetails = new HashSet<>();
		
		if(!pegawaiCutiDetailIds.isEmpty()) {
			for (Long pegawaiCutiDetailId : pegawaiCutiDetailIds) {
				PegawaiCutiDetail pegawaiCutiDetail = pegawaiCutiDetailService.findById(pegawaiCutiDetailId);
				pegawaiCutiDetails.add(pegawaiCutiDetail);
			}
		}
		return pegawaiCutiDetails;
	}

	public boolean delete(long id) {
		PegawaiCuti pegawai = findById(id);
		try {
			pegawaiCutiRepository.delete(pegawai);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}

	public Page<PegawaiCuti> pagination(String search, Pageable pageable) {
		return pegawaiCutiRepository.findAll(pageable);
	}

	public PegawaiCutiRequestEditDto convertToDto(PegawaiCuti pegawaiCuti) {
		PegawaiCutiRequestEditDto pegawaiCutiRequestEditDto = modelMapper.map(pegawaiCuti, PegawaiCutiRequestEditDto.class);
		pegawaiCutiRequestEditDto.setCutiId(pegawaiCuti.getCuti().getId());
		pegawaiCutiRequestEditDto.setPegawaiId(pegawaiCuti.getPegawai().getId());
		pegawaiCutiRequestEditDto.setPegawaiCutiDetailIds(
				pegawaiCuti.getPegawaiCutiDetails().stream().map(PegawaiCutiDetail::getId).collect(Collectors.toSet()));
		return pegawaiCutiRequestEditDto;
	}

	public Optional<PegawaiCuti> findByPegawaiAndCuti(Pegawai pegawai, Cuti cuti) {
		return pegawaiCutiRepository.findByPegawaiAndCuti(pegawai, cuti);
	}
	
}
