package id.co.iteacode.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
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

import id.co.iteacode.dto.pegawai.PegawaiRequestAddDto;
import id.co.iteacode.dto.pegawai.PegawaiRequestEditDto;
import id.co.iteacode.exception.EntityNotFoundException;
import id.co.iteacode.model.Cuti;
import id.co.iteacode.model.Pegawai;
import id.co.iteacode.model.PegawaiCuti;
import id.co.iteacode.model.User;
import id.co.iteacode.repository.PegawaiRepository;

@Service
public class PegawaiService {

	@Autowired
	private PegawaiRepository pegawaiRepository;

	@Autowired
	private JabatanService jabatanService;

	@Autowired
	private CutiService cutiService;

	@Autowired
	private PegawaiCutiService pegawaiCutiService;

	@Autowired
	private UserService userService;

	public List<String> statusPekerjaan = Arrays.asList("Tetap", "Kontrak", "Outsourching");

	@Autowired
	private ModelMapper modelMapper;

	public Pegawai findById(Long id) {
		return pegawaiRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Pegawai id [" + id + "] not found"));
	}

	public Pegawai add(PegawaiRequestAddDto dto) {

		System.out.println(dto);

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		String formatTanggalLahir = dto.getFormatTanggalLahir();
		String formatTanggalMasuk = dto.getFormatTanggalMasuk();

		String agama = dto.getAgama();
		String alamat = dto.getAlamat();
		Long id = dto.getId();
		String kewarganegaraan = dto.getKewarganegaraan();
		String nama = dto.getNama();
		String nik = dto.getNik();
		String statusPerkawinan = dto.getStatusPerkawinan();
		LocalDate tanggalLahir = LocalDate.parse(formatTanggalLahir, formatter);
		String telepon = dto.getTelepon();

		Long jabatanId = dto.getJabatanId();
		String npwp = dto.getNpwp();
		String pendidikanTerakhir = dto.getPendidikanTerakhir();
		String statusPekerjaan = dto.getStatusPekerjaan();
		LocalDate tanggalMasuk = LocalDate.parse(formatTanggalMasuk, formatter);

		Pegawai pegawai = new Pegawai();
		pegawai.setJabatan(jabatanService.findById(jabatanId));
		pegawai.setNpwp(npwp);
		pegawai.setPendidikanTerakhir(pendidikanTerakhir);
		pegawai.setStatusPekerjaan(statusPekerjaan);
		pegawai.setTanggalMasuk(tanggalMasuk);
		pegawai.setAgama(agama);
		pegawai.setAlamat(alamat);
		pegawai.setKewarganegaraan(kewarganegaraan);
		pegawai.setNama(nama);
		pegawai.setNik(nik);
		pegawai.setStatusPerkawinan(statusPerkawinan);
		pegawai.setTanggalLahir(tanggalLahir);
		pegawai.setTelepon(telepon);

		pegawai = pegawaiRepository.save(pegawai);
		return pegawai;
	}

	public Pegawai edit(PegawaiRequestEditDto dto) {

		System.out.println(dto);

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		String formatTanggalLahir = dto.getFormatTanggalLahir();
		String formatTanggalMasuk = dto.getFormatTanggalMasuk();

		String agama = dto.getAgama();
		String alamat = dto.getAlamat();
		Long id = dto.getId();
		String kewarganegaraan = dto.getKewarganegaraan();
		String nama = dto.getNama();
		String nik = dto.getNik();
		String statusPerkawinan = dto.getStatusPerkawinan();
		LocalDate tanggalLahir = LocalDate.parse(formatTanggalLahir, formatter);
		String telepon = dto.getTelepon();

		Long jabatanId = dto.getJabatanId();
		String npwp = dto.getNpwp();
		String pendidikanTerakhir = dto.getPendidikanTerakhir();
		String statusPekerjaan = dto.getStatusPekerjaan();
		LocalDate tanggalMasuk = LocalDate.parse(formatTanggalMasuk, formatter);

		Pegawai pegawai = findById(id);
		pegawai.setJabatan(jabatanService.findById(jabatanId));
		pegawai.setNpwp(npwp);
		pegawai.setPendidikanTerakhir(pendidikanTerakhir);
		pegawai.setStatusPekerjaan(statusPekerjaan);
		pegawai.setTanggalMasuk(tanggalMasuk);
		pegawai.setAgama(agama);
		pegawai.setAlamat(alamat);
		pegawai.setKewarganegaraan(kewarganegaraan);
		pegawai.setNama(nama);
		pegawai.setNik(nik);
		pegawai.setStatusPerkawinan(statusPerkawinan);
		pegawai.setTanggalLahir(tanggalLahir);
		pegawai.setTelepon(telepon);

		pegawai = pegawaiRepository.save(pegawai);
		return pegawai;
	}
	
	public void createUser(Pegawai pegawai, User user) {
		pegawai.setUser(user);
		pegawaiRepository.save(pegawai);
	}

	public boolean delete(long id) {
		Pegawai pegawai = findById(id);
		try {
			pegawaiRepository.delete(pegawai);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

	public Page<Pegawai> pagination(String search, Pageable pageable) {
//		Page<Pegawai> pegawai = pegawaiRepository
//		.findByDeletedFalseAndNpwpContainingIgnoreCaseOrStatusPekerjaanContainingIgnoreCaseOrPendidikanTerakhirContainingIgnoreCase(
//				search, search, search, pageable);
//		
//		pegawai.getContent().forEach(obj -> {
//			System.out.println(obj);
//		});
		if (search != null) {
			return pegawaiRepository
					.findByDeletedFalseAndNpwpContainingIgnoreCaseOrStatusPekerjaanContainingIgnoreCaseOrPendidikanTerakhirContainingIgnoreCase(
							search, search, search, pageable);
		} else {
			return pegawaiRepository.findByDeletedFalse(pageable);
		}
	}

	public PegawaiRequestEditDto convertToDto(Pegawai pegawai) {
		PegawaiRequestEditDto pegawaiRequestEditDto = modelMapper.map(pegawai, PegawaiRequestEditDto.class);
		pegawaiRequestEditDto.setJabatanId(pegawai.getJabatan().getId());
		return pegawaiRequestEditDto;
	}

	public List<Pegawai> findAll() {
		return pegawaiRepository.findAll();
	}

	public List<Pegawai> filterByStatusPekerjaan(String statusPekerjaan) {
		return pegawaiRepository.findByStatusPekerjaanContainingIgnoreCase(statusPekerjaan);
	}

	public List<Pegawai> findByUserIsNull() {
		return pegawaiRepository.findByUserIsNull();
	}

	public Set<Pegawai> filterPegawaiAndCutiIsReadyPegawaiCuti(String statusPekerjaan, Cuti cuti) {

		Set<Pegawai> pegawais = new HashSet<>();
		if (!filterByStatusPekerjaan(statusPekerjaan).isEmpty()) {
			System.out.println("!filterByStatusPekerjaan(statusPekerjaan).isEmpty()");
			if (cuti != null) {
				System.out.println("cuti != null");
				for (Pegawai pegawai : filterByStatusPekerjaan(statusPekerjaan)) {
					if (!pegawaiCutiService.findByPegawaiAndCuti(pegawai, cuti).isPresent()) {
						pegawais.add(pegawai);
					}
				}
			}
		}

		return pegawais;
	}
}
