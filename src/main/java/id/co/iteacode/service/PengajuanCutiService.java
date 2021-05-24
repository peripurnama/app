package id.co.iteacode.service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import id.co.iteacode.dto.pegawaicutidetail.PegawaiCutiDetailRequestAddDto;
import id.co.iteacode.dto.pengajuancuti.PengajuanCutiRequestAddDto;
import id.co.iteacode.dto.pengajuancuti.PengajuanCutiRequestEditDto;
import id.co.iteacode.exception.EntityNotFoundException;
import id.co.iteacode.model.Cuti;
import id.co.iteacode.model.Pegawai;
import id.co.iteacode.model.PegawaiCuti;
import id.co.iteacode.model.PegawaiCutiDetail;
import id.co.iteacode.model.PengajuanCuti;
import id.co.iteacode.repository.PengajuanCutiRepository;

@Service
public class PengajuanCutiService {

	@Autowired
	private PengajuanCutiRepository pengajuanCutiRepository;

	@Autowired
	private PegawaiService pegawaiService;

	@Autowired
	private PegawaiCutiDetailService pegawaiCutiDetailService;

	private PegawaiCuti pegawaiCuti;

	@Autowired
	private FileStorageService fileStorageService;
	
	@Autowired
	private CloudianryService cloudianryService;

	@Autowired
	private ModelMapper modelMapper;

	private String messageCheckPegawaiCuti;
	
	private Long sisaCuti;

	public PengajuanCuti findById(Long id) {
		return pengajuanCutiRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Cuti id [" + id + "] not found"));
	}

	@Transactional
	public PengajuanCuti add(PengajuanCutiRequestAddDto dto) {
		boolean approved = dto.isApproved();
		String body = dto.getBody();
		Long id = dto.getId();
		Long pegawaiId = dto.getPegawaiId();
		String status = dto.getStatus();
		String title = dto.getTitle();
		Date startDate = dto.getStartDate();
		Date endDate = dto.getEndDate();

		Integer totalCuti = calculateDate(startDate, endDate).intValue();

//		String fileName = fileStorageService.storeFile(dto.getFile());
		Map<String, Object> cloudinaryURL = null; 
		

		Pegawai pegawai = pegawaiService.findById(pegawaiId);
		PengajuanCuti pengajuanCuti = null;
		if (cekCutiPegawai(pegawai)) {
			
			String fileName = null;
			if(!dto.getFile().isEmpty()) {
				try {
					cloudinaryURL = cloudianryService.uploadToCloudinary(cloudianryService.getCloudinary(), dto.getFile());
					fileName = (String) cloudinaryURL.get("url");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			pengajuanCuti = new PengajuanCuti();
			pengajuanCuti.setBody(body);
			pengajuanCuti.setFileName(fileName);
			pengajuanCuti.setPegawai(pegawai);
			pengajuanCuti.setTitle(title);
			pengajuanCuti.setStartDate(startDate);
			pengajuanCuti.setEndDate(endDate);
			pengajuanCuti.setTotalCuti(totalCuti);

			pengajuanCuti = pengajuanCutiRepository.save(pengajuanCuti);

			PegawaiCutiDetailRequestAddDto pegawaiCutiDetailRequestAddDto = new PegawaiCutiDetailRequestAddDto();
			pegawaiCutiDetailRequestAddDto.setPegawaiCutiId(pegawaiCuti.getId());
			pegawaiCutiDetailRequestAddDto.setPengajuanCutiId(pengajuanCuti.getId());
			pegawaiCutiDetailRequestAddDto.setStatus(statusCuti(1));
			pegawaiCutiDetailRequestAddDto.setTotalCuti(pengajuanCuti.getTotalCuti());
			pegawaiCutiDetailRequestAddDto.setSisaCutiTahunan(sisaCuti.intValue());
			System.out.println("pegawaiCutiDetailRequestAddDto: " + pegawaiCutiDetailRequestAddDto);
			PegawaiCutiDetail pegawaiCutiDetail = pegawaiCutiDetailService.add(pegawaiCutiDetailRequestAddDto);
			System.out.println("pegawaiCutiDetail: " + pegawaiCutiDetail);
		}

		return pengajuanCuti;
	}

	private static Long calculateDate(Date startDate, Date endDate) {
		if (endDate != null) {
			System.out.println("endDate != null");
			long diff = endDate.getTime() - startDate.getTime();

			long diffSeconds = diff / 1000 % 60;
			long diffMinutes = diff / (60 * 1000) % 60;
			long diffHours = diff / (60 * 60 * 1000) % 24;
			long diffDays = diff / (24 * 60 * 60 * 1000);

			System.out.print(diffDays + " days, ");
			System.out.print(diffHours + " hours, ");
			System.out.print(diffMinutes + " minutes, ");
			System.out.print(diffSeconds + " seconds.");

			Calendar calStartDate = Calendar.getInstance();
			calStartDate.setTime(startDate);

			long minuteForWeekend = 0;

//			System.out.println("Cal Start Date: " + format.format(calStartDate.getTime()));

			System.out.println("diffDays: " + diffDays);
			for (int i = 1; i <= (diffDays + 1); i++) {
				calStartDate.add(Calendar.DATE, 1);
				if ((calStartDate.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY)
						|| calStartDate.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {
					System.out.println();
					minuteForWeekend +=1;
				}
				
			}
			
			long calculate = (diffDays + 1) - minuteForWeekend;
			return calculate;
		} else {
			System.out.println("else");
			long diff = startDate.getTime() - startDate.getTime();

			long diffSeconds = diff / 1000 % 60;
			long diffMinutes = diff / (60 * 1000) % 60;
			long diffHours = diff / (60 * 60 * 1000) % 24;
			long diffDays = diff / (24 * 60 * 60 * 1000);

			System.out.print(diffDays + " days, ");
			System.out.print(diffHours + " hours, ");
			System.out.print(diffMinutes + " minutes, ");
			System.out.print(diffSeconds + " seconds.");

			Calendar calStartDate = Calendar.getInstance();
			calStartDate.setTime(startDate);

			long minuteForWeekend = 0;

//			System.out.println("Cal Start Date: " + format.format(calStartDate.getTime()));

			System.out.println("diffDays: " + diffDays);
			for (int i = 1; i <= (diffDays + 1); i++) {
				calStartDate.add(Calendar.DATE, 1);
				if ((calStartDate.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY)
						|| calStartDate.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {
					System.out.println();
					minuteForWeekend +=1;
				}
				
			}
			
			long calculate = (diffDays + 1) - minuteForWeekend;
			return calculate;
		}
	}

	public boolean cekCutiPegawai(Pegawai pegawai) {
		boolean check = false;

		Set<PegawaiCuti> pegawaiCutis = pegawai.getPegawaiCutis();
		String message = "";
		if (pegawaiCutis.isEmpty()) {
			message = "Anda belum mempunyai jatah cuti / cuti anda belum di setting";
		}
		LocalDate now = LocalDate.now();
		int year = now.getYear();
		if (!pegawaiCutis.isEmpty()) {
			Set<PegawaiCuti> pegawaiCituFindByYears = pegawaiCutis.stream().filter(obj -> {
				return obj.getCuti().getTahun().equals(year);
			}).collect(Collectors.toSet());

			if (pegawaiCituFindByYears.isEmpty()) {
				message = "Maaf! Anda tidak mempunyai jatah cuti tahun " + year;
			}

			if (!pegawaiCituFindByYears.isEmpty()) {
				Optional<PegawaiCuti> pegawaiCutiByStatusPekerjaan = checkStatusPekerjaan(pegawai,
						pegawaiCituFindByYears);
				if (!pegawaiCutiByStatusPekerjaan.isPresent()) {
					message = "Maaf! Status pekerjaan anda saat ini tidak mempunyai jatah cuti tahunan";
				} else {
					PegawaiCuti pegawaiCuti = pegawaiCutiByStatusPekerjaan.get();
					this.pegawaiCuti = pegawaiCuti;
					Cuti cuti = pegawaiCuti.getCuti();
					System.out.println("Cuti -> " + cuti);
					Integer jumlahCutiTahunan = cuti.getJumlahCutiTahunan();
					List<PegawaiCutiDetail> pegawaiCutiDetails = pegawaiCutiDetailService.findByPegawaiCuti(pegawaiCuti)
							.stream().filter(obj -> {
								return obj.getStatus().equals(statusCuti(2));
							}).collect(Collectors.toList());

					IntSummaryStatistics totalCutiYangSudahDiambil = pegawaiCutiDetails.stream()
							.filter(obj -> obj.getTahun().intValue() == LocalDate.now().getYear())
							.collect(Collectors.summarizingInt(PegawaiCutiDetail::getTotalCuti));
					this.sisaCuti = jumlahCutiTahunan - totalCutiYangSudahDiambil.getSum();
					if (jumlahCutiTahunan > totalCutiYangSudahDiambil.getSum()) {
						check = true;
					} else {
						message = "Maaf! Cuti tahunan anda sudah habis";
					}
				}

			}

		}

		setMessageCheckPegawaiCuti(message);
		System.out.println("messages: " + message);
		return check;
	}

	private Optional<PegawaiCuti> checkStatusPekerjaan(Pegawai pegawai, Set<PegawaiCuti> pegawaiCituFindByYears) {
		return pegawaiCituFindByYears.stream().filter(obj -> {
			boolean status = false;
			String statusPekerjaan = obj.getPegawai().getStatusPekerjaan();

			if (pegawai.getStatusPekerjaan().equals(statusPekerjaan)) {
				status = true;
			}
			return status;
		}).findFirst();
	}

	public PengajuanCuti edit(PengajuanCutiRequestEditDto dto) {
		boolean approved = dto.isApproved();
		String body = dto.getBody();
		String fileName = dto.getFileName();
		Long id = dto.getId();
		Long pegawaiId = dto.getPegawaiId();
		String status = dto.getStatus();
		String title = dto.getTitle();
		Date startDate = dto.getStartDate();
		Date endDate = dto.getEndDate();
		Integer totalCuti = dto.getTotalCuti();

		PengajuanCuti pengajuanCuti = findById(id);
		pengajuanCuti.setBody(body);
		pengajuanCuti.setFileName(fileName);
		pengajuanCuti.setPegawai(pegawaiService.findById(pegawaiId));
		pengajuanCuti.setTitle(title);
		pengajuanCuti.setStartDate(startDate);
		pengajuanCuti.setEndDate(endDate);
		pengajuanCuti.setTotalCuti(totalCuti);

		pengajuanCuti = pengajuanCutiRepository.save(pengajuanCuti);
		return pengajuanCuti;
	}

	public void delete(long id) {
		PengajuanCuti pengajuanCuti = findById(id);
		pengajuanCuti.setDeleted(true);
		pengajuanCutiRepository.save(pengajuanCuti);
	}

	public Page<PengajuanCuti> pagination(String search, Pageable pageable) {
		if (search != null) {
			return pengajuanCutiRepository
					.findByTitleContainingIgnoreCaseOrBodyContainingIgnoreCaseOrPegawaiNamaContainingIgnoreCase(
							search, search, search, pageable);
		} else {
			return pengajuanCutiRepository.findAll(pageable);
		}
	}

	public PengajuanCutiRequestEditDto convertToDto(PengajuanCuti pengajuanCuti) {
		PengajuanCutiRequestEditDto pengajuanCutiRequestEditDto = modelMapper.map(pengajuanCuti,
				PengajuanCutiRequestEditDto.class);
		pengajuanCutiRequestEditDto.setPegawaiId(pengajuanCuti.getPegawai().getId());
		pengajuanCutiRequestEditDto.setFormatEndDate(pengajuanCuti.getEndDate());
		pengajuanCutiRequestEditDto.setFormatStartDate(pengajuanCuti.getStartDate());
		return pengajuanCutiRequestEditDto;
	}

	public String getMessageCheckPegawaiCuti() {
		return messageCheckPegawaiCuti;
	}

	public void setMessageCheckPegawaiCuti(String messageCheckPegawaiCuti) {
		this.messageCheckPegawaiCuti = messageCheckPegawaiCuti;
	}

	public String statusCuti(int status) {

		String cutiStatus = "";

		switch (status) {
		case 1:
			cutiStatus = "Menunggu Persetujuan";
			break;
		case 2:
			cutiStatus = "Disetujui";
			break;
		case 3:
			cutiStatus = "Ditolak";
			break;
		default:

			break;
		}

		return cutiStatus;
	}

	public PegawaiCuti getPegawaiCuti() {
		return pegawaiCuti;
	}

	public void setPegawaiCuti(PegawaiCuti pegawaiCuti) {
		this.pegawaiCuti = pegawaiCuti;
	}

	public List<PengajuanCuti> findAll() {
		return pengajuanCutiRepository.findAll();
		
	}

}
