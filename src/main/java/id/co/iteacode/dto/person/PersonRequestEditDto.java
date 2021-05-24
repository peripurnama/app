package id.co.iteacode.dto.person;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PersonRequestEditDto {

	private Long id;
	private String nik;
	private String nama;
	private Date tanggalLahir;
	private String alamat;
	private String telepon;
	private String agama;
	private String statusPerkawinan;
	private String kewarganegaraan;
	private Long pegawaiId;

	private String formatTanggalLahir;

	SimpleDateFormat format;

	public PersonRequestEditDto() {
		format = new SimpleDateFormat("dd/MM/yyyy");
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNik() {
		return nik;
	}

	public void setNik(String nik) {
		this.nik = nik;
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public Date getTanggalLahir() {
		if (formatTanggalLahir != null) {
			try {
				tanggalLahir = format.parse(formatTanggalLahir);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return tanggalLahir;
	}

	public void setTanggalLahir(Date tanggalLahir) {
		this.tanggalLahir = tanggalLahir;
	}

	public String getAlamat() {
		return alamat;
	}

	public void setAlamat(String alamat) {
		this.alamat = alamat;
	}

	public String getTelepon() {
		return telepon;
	}

	public void setTelepon(String telepon) {
		this.telepon = telepon;
	}

	public String getAgama() {
		return agama;
	}

	public void setAgama(String agama) {
		this.agama = agama;
	}

	public String getStatusPerkawinan() {
		return statusPerkawinan;
	}

	public void setStatusPerkawinan(String statusPerkawinan) {
		this.statusPerkawinan = statusPerkawinan;
	}

	public String getKewarganegaraan() {
		return kewarganegaraan;
	}

	public void setKewarganegaraan(String kewarganegaraan) {
		this.kewarganegaraan = kewarganegaraan;
	}

	public Long getPegawaiId() {
		return pegawaiId;
	}

	public void setPegawaiId(Long pegawaiId) {
		this.pegawaiId = pegawaiId;
	}

	public String getFormatTanggalLahir() {
		return formatTanggalLahir;
	}

	public void setFormatTanggalLahir(String formatTanggalLahir) {
		this.formatTanggalLahir = formatTanggalLahir;
	}

	public void setFormatDate(Date date) {

		if (date != null) {
			this.formatTanggalLahir = format.format(date);
		}
	}

	@Override
	public String toString() {
		return "PersonRequestEditDto [id=" + id + ", nik=" + nik + ", nama=" + nama + ", tanggalLahir=" + tanggalLahir
				+ ", alamat=" + alamat + ", telepon=" + telepon + ", agama=" + agama + ", statusPerkawinan="
				+ statusPerkawinan + ", kewarganegaraan=" + kewarganegaraan + ", pegawaiId=" + pegawaiId
				+ ", formatTanggalLahir=" + formatTanggalLahir + "]";
	}


}
