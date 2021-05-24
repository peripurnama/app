package id.co.iteacode.service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import id.co.iteacode.dto.cuti.CutiRequestAddDto;
import id.co.iteacode.dto.cuti.CutiRequestEditDto;
import id.co.iteacode.exception.EntityNotFoundException;
import id.co.iteacode.model.Cuti;
import id.co.iteacode.model.PegawaiCuti;
import id.co.iteacode.repository.CutiRepository;

@Service
public class CutiService {

	@Autowired
	private CutiRepository cutiRepository;

	@Autowired
	private PegawaiService pegawaiService;

	@Autowired
	private PegawaiCutiService pegawaiCutiService;

	@Autowired
	private ModelMapper modelMapper;

	public List<Integer> years = Arrays.asList(2019, 2020, 2021, 2022, 2023, 2024, 2025);

	public List<String> types = Arrays.asList("Tetap", "Kontrak", "Outsourching");

	public Cuti findById(Long id) {
		return cutiRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Cuti id [" + id + "] not found"));
	}

	public Cuti add(CutiRequestAddDto dto) {
		Long id = dto.getId();
		Integer jumlahCutiTahunan = dto.getJumlahCutiTahunan();
		
		Set<Long> pegawaiCutiIds = dto.getPegawaiCutiIds();
		
		String typeAndYear = dto.getTypeAndYear();
		
		
		
		Set<PegawaiCuti> pegawaiCutis = new HashSet<>();

		if (!pegawaiCutiIds.isEmpty()) {
			for (Long pegawaiCutiId : pegawaiCutiIds) {
				PegawaiCuti pegawaiCuti = pegawaiCutiService.findById(pegawaiCutiId);
				pegawaiCutis.add(pegawaiCuti);
			}
		}

		Cuti cuti = new Cuti();
		cuti.setJumlahCutiTahunan(jumlahCutiTahunan);
		
		if(typeAndYear != null) {
			String type = getType(typeAndYear);
			Integer tahun = Integer.valueOf(getYear(typeAndYear));
			cuti.setType(type);
			cuti.setTahun(tahun);
		}

		if (!pegawaiCutis.isEmpty()) {
			cuti.setPegawaiCutis(pegawaiCutis);
		}

		cuti = cutiRepository.save(cuti);
		return cuti;
	}

	public Cuti edit(CutiRequestEditDto dto) {
		Long id = dto.getId();
		Integer jumlahCutiTahunan = dto.getJumlahCutiTahunan();
		
		Set<Long> pegawaiCutiIds = dto.getPegawaiCutiIds();
		
		String typeAndYear = dto.getTypeAndYear();
		
		Set<PegawaiCuti> pegawaiCutis = new HashSet<>();

		if (!pegawaiCutiIds.isEmpty()) {
			for (Long pegawaiCutiId : pegawaiCutiIds) {
				PegawaiCuti pegawaiCuti = pegawaiCutiService.findById(pegawaiCutiId);
				pegawaiCutis.add(pegawaiCuti);
			}
		}

		Cuti cuti = findById(id);
		cuti.setJumlahCutiTahunan(jumlahCutiTahunan);
		
//		if(typeAndYear != null) {
//			String type = getType(typeAndYear);
//			Integer tahun = Integer.valueOf(getYear(typeAndYear));
//			cuti.setType(type);
//			cuti.setTahun(tahun);
//		}

		if (!pegawaiCutis.isEmpty()) {
			cuti.setPegawaiCutis(pegawaiCutis);
		}

		cuti = cutiRepository.save(cuti);
		return cuti;
	}

	public boolean delete(long id) {
		Cuti cuti = findById(id);
		try {
			cutiRepository.delete(cuti);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

	public Page<Cuti> pagination(String search, Pageable pageable) {
		if (search != null) {
			return cutiRepository.findByTypeContainingIgnoreCase(search, pageable);
		} else {
			return cutiRepository.findAll(pageable);
		}
	}

	public CutiRequestEditDto convertToDto(Cuti cuti) {
		CutiRequestEditDto cutiRequestEditDto = modelMapper.map(cuti, CutiRequestEditDto.class);
		cutiRequestEditDto
				.setPegawaiCutiIds(cuti.getPegawaiCutis().stream().map(PegawaiCuti::getId).collect(Collectors.toSet()));
		cutiRequestEditDto.setTypeAndYear(cuti.getType() + "-" + cuti.getTahun());
		return cutiRequestEditDto;
	}

	public List<Cuti> findAll() {
		return cutiRepository.findAll();
	}

	public Cuti filterCutiByTypeAndYear(String status) {
		LocalDate now = LocalDate.now();
		int year = now.getYear();

		Optional<Cuti> optionalCuti = findAll().stream().filter(obj -> obj.getType().equals(status))
				.filter(obj -> obj.getTahun().intValue() == year).findFirst();
		if (optionalCuti.isPresent()) {
			return optionalCuti.get();
		}

		return null;
	}

	public Map<String, Set<Integer>> availableYears() {
		Map<String, Set<Integer>> available = new HashMap<>();
		Set<Integer> availableYears;

		for (String type : types) {
			availableYears = new HashSet<>();
			for (Integer year : years) {
				if (!cutiRepository.findByTypeAndTahun(type, year).isPresent()) {
					availableYears.add(year);
				}
			}

			if (!availableYears.isEmpty()) {
				available.put(type, availableYears);
			}

		}
		return available;
	}
	
	public String getType(String typeAndYear) {
		return typeAndYear.substring(0, typeAndYear.indexOf("-"));
	}
	
	public String getYear(String typeAndYear) {
		return typeAndYear.substring(typeAndYear.indexOf("-")+1, typeAndYear.length());
	}
}
