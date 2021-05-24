package id.co.iteacode.dto.pegawai;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class PegawaiRequestAddDto {

	private Long id;

	private String nik;
	private String nama;
	private LocalDate tanggalLahir;
	private String alamat;
	private String telepon;
	private String agama;
	private String statusPerkawinan;
	private String kewarganegaraan;

	private String npwp;
	private LocalDate tanggalMasuk;
	private String statusPekerjaan;
	private String pendidikanTerakhir;
	private Long jabatanId;
	private String formatTanggalMasuk;
	private String formatTanggalLahir;

	public PegawaiRequestAddDto() {
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

	public LocalDate getTanggalLahir() {
		return tanggalLahir;
	}

	public void setTanggalLahir(LocalDate tanggalLahir) {
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

	public String getNpwp() {
		return npwp;
	}

	public void setNpwp(String npwp) {
		this.npwp = npwp;
	}

	public LocalDate getTanggalMasuk() {
		return tanggalMasuk;
	}

	public void setTanggalMasuk(LocalDate tanggalMasuk) {
		this.tanggalMasuk = tanggalMasuk;
	}

	public String getStatusPekerjaan() {
		return statusPekerjaan;
	}

	public void setStatusPekerjaan(String statusPekerjaan) {
		this.statusPekerjaan = statusPekerjaan;
	}

	public String getPendidikanTerakhir() {
		return pendidikanTerakhir;
	}

	public void setPendidikanTerakhir(String pendidikanTerakhir) {
		this.pendidikanTerakhir = pendidikanTerakhir;
	}

	public Long getJabatanId() {
		return jabatanId;
	}

	public void setJabatanId(Long jabatanId) {
		this.jabatanId = jabatanId;
	}

	public String getFormatTanggalMasuk() {
		return formatTanggalMasuk;
	}

	public void setFormatTanggalMasuk(String formatTanggalMasuk) {
		this.formatTanggalMasuk = formatTanggalMasuk;
	}

	public String getFormatTanggalLahir() {
		return formatTanggalLahir;
	}

	public void setFormatTanggalLahir(String formatTanggalLahir) {
		this.formatTanggalLahir = formatTanggalLahir;
	}

	@Override
	public String toString() {
		return "PegawaiRequestAddDto [id=" + id + ", nik=" + nik + ", nama=" + nama + ", tanggalLahir=" + tanggalLahir
				+ ", alamat=" + alamat + ", telepon=" + telepon + ", agama=" + agama + ", statusPerkawinan="
				+ statusPerkawinan + ", kewarganegaraan=" + kewarganegaraan + ", npwp=" + npwp + ", tanggalMasuk="
				+ tanggalMasuk + ", statusPekerjaan=" + statusPekerjaan + ", pendidikanTerakhir=" + pendidikanTerakhir
				+ ", jabatanId=" + jabatanId + ", formatTanggalMasuk=" + formatTanggalMasuk + ", formatTanggalLahir="
				+ formatTanggalLahir + "]";
	}

}
