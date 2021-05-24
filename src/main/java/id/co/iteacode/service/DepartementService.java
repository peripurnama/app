package id.co.iteacode.service;

import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import id.co.iteacode.dto.departement.DepartementRequestAddDto;
import id.co.iteacode.dto.departement.DepartementRequestEditDto;
import id.co.iteacode.exception.EntityNotFoundException;
import id.co.iteacode.model.Departement;
import id.co.iteacode.repository.DepartementRepository;

@Transactional
@Service
public class DepartementService {

	@Autowired
	private DepartementRepository departementRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	public Departement findById(Long id) {
		return departementRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Departement id [" + id + "] not found"));
	}
	
	public Departement add(DepartementRequestAddDto dto) {
		String deskripsi = dto.getDeskripsi();
		Long id = dto.getId();
		String name = dto.getNama();

		Departement departement = new Departement();
		departement.setDeskripsi(deskripsi);
		departement.setNama(name);
		departement = departementRepository.save(departement);

		return departement;
	}

	public Departement edit(DepartementRequestEditDto dto) {
		String deskripsi = dto.getDeskripsi();
		Long id = dto.getId();
		String nama = dto.getNama();

		Departement departement = findById(id);
		departement.setDeskripsi(deskripsi);
		departement.setNama(nama);
		departement = departementRepository.save(departement);

		return departement;
	}

	public boolean delete(long id) {
		Departement departement = findById(id);
		try {
			departementRepository.delete(departement);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}
	
	public Page<Departement> pagination(String search, Pageable pageable) {
		if(search != null) {
			return departementRepository.findByDeletedFalseAndNamaContainingOrDeskripsiContaining(search, search, pageable);
		} else {
			return departementRepository.findAll(pageable);
		}
	}
	
	public List<Departement> findAll() {
		return departementRepository.findAll();
	}

	public DepartementRequestEditDto convertToDto(Departement departement) {
		return modelMapper.map(departement, DepartementRequestEditDto.class);
	}
}