package id.co.iteacode.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import id.co.iteacode.dto.jabatan.JabatanRequestAddDto;
import id.co.iteacode.dto.jabatan.JabatanRequestEditDto;
import id.co.iteacode.exception.EntityNotFoundException;
import id.co.iteacode.model.Jabatan;
import id.co.iteacode.repository.JabatanRepository;

@Service
public class JabatanService {

	@Autowired
	private JabatanRepository jabatanRepository;

	@Autowired
	private BagianService bagianService;
	
	@Autowired
	private ModelMapper modelMapper;

	public Jabatan findById(Long id) {
		return jabatanRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Jabatan id [" + id + "] not found"));
	}

	public Jabatan add(JabatanRequestAddDto dto) {
		Long bagianId = dto.getBagianId();
		String deskripsi = dto.getDeskripsi();
		Long id = dto.getId();
		String name = dto.getNama();

		Jabatan jabatan = new Jabatan();
		jabatan.setBagian(bagianService.findById(bagianId));
		jabatan.setDeskripsi(deskripsi);
		jabatan.setNama(name);
		jabatan = jabatanRepository.save(jabatan);

		return jabatan;
	}

	public Jabatan edit(JabatanRequestEditDto dto) {
		Long bagianId = dto.getBagianId();
		String deskripsi = dto.getDeskripsi();
		Long id = dto.getId();
		String nama = dto.getNama();

		Jabatan jabatan = findById(id);
		jabatan.setBagian(bagianService.findById(bagianId));
		jabatan.setDeskripsi(deskripsi);
		jabatan.setNama(nama);
		jabatan = jabatanRepository.save(jabatan);

		return jabatan;
	}

	public boolean delete(long id) {
		Jabatan jabatan = findById(id);
		try {
			jabatanRepository.delete(jabatan);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}
	
	public List<Jabatan> findAll() {
		return jabatanRepository.findAll();
	}

	public Page<Jabatan> pagination(String search, Pageable pageable) {
		if (search != null) {
			return jabatanRepository
					.findByNamaContainingIgnoreCaseOrDeskripsiContainingIgnoreCaseOrBagianNamaContainingIgnoreCase(
							search, search, search, pageable);
		} else {
			return jabatanRepository.findAll(pageable);
		}
	}
	
	public JabatanRequestEditDto convertToDto(Jabatan jabatan) {
		JabatanRequestEditDto jabatanRequestEditDto = modelMapper.map(jabatan, JabatanRequestEditDto.class);
		jabatanRequestEditDto.setBagianId(jabatan.getBagian().getId());
		return jabatanRequestEditDto;
	}
}
