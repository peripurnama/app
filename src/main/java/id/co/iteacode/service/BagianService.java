package id.co.iteacode.service;

import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import id.co.iteacode.dto.bagian.BagianRequestAddDto;
import id.co.iteacode.dto.bagian.BagianRequestEditDto;
import id.co.iteacode.exception.EntityNotFoundException;
import id.co.iteacode.model.Bagian;
import id.co.iteacode.model.Departement;
import id.co.iteacode.repository.BagianRepository;

@Transactional
@Service
public class BagianService {

	@Autowired
	private BagianRepository bagianRepository;

	@Autowired
	private DepartementService departementService;

	@Autowired
	private ModelMapper modelMapper;

	public Bagian findById(Long id) {
		return bagianRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Bagian id [" + id + "] not found"));
	}

	public Bagian add(BagianRequestAddDto dto) {
		Long departementId = dto.getDepartementId();
		String deskripsi = dto.getDeskripsi();
		Long id = dto.getId();
		String name = dto.getNama();

		Bagian jabatan = new Bagian();
		jabatan.setDepartement(departementService.findById(departementId));
		jabatan.setDeskripsi(deskripsi);
		jabatan.setNama(name);
		jabatan = bagianRepository.save(jabatan);

		return jabatan;
	}

	public Bagian edit(BagianRequestEditDto dto) {
		Long departementId = dto.getDepartementId();
		String deskripsi = dto.getDeskripsi();
		Long id = dto.getId();
		String nama = dto.getNama();

		Bagian jabatan = findById(id);
		jabatan.setDepartement(departementService.findById(departementId));
		jabatan.setDeskripsi(deskripsi);
		jabatan.setNama(nama);
		jabatan = bagianRepository.save(jabatan);

		return jabatan;
	}

	public boolean delete(long id) {
		Bagian jabatan = findById(id);
		try {
			bagianRepository.delete(jabatan);
			return true;
		} catch (Exception e) {
			
		}
		return false;
	}

	public Page<Bagian> pagination(String search, Pageable pageable) {
		if (search != null) {
			return bagianRepository
					.findByNamaContainingIgnoreCaseOrDeskripsiContainingIgnoreCaseOrDepartementNamaContainingIgnoreCase(
							search, search, search, pageable);
		} else {
			return bagianRepository.findAll(pageable);
		}
	}
	
	public List<Bagian> findAll() {
		return bagianRepository.findAll();
	}

	public BagianRequestEditDto convertToDto(Bagian bagian) {
		BagianRequestEditDto bagianRequestEditDto = modelMapper.map(bagian, BagianRequestEditDto.class);
		bagianRequestEditDto.setDepartementId(bagian.getDepartement().getId());
		return bagianRequestEditDto;
	}
}