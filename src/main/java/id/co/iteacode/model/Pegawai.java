package id.co.iteacode.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import id.co.iteacode.model.audit.Auditable;

@Entity
@Table(name = "PEGAWAI")
public class Pegawai extends Auditable<String> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	// Data personal

	@Column(name = "NIK", unique = true, nullable = false, length = 16)
	private String nik;

	@Column(name = "nama")
	private String nama;

	@Column
	private LocalDate tanggalLahir;

	@Column
	private String alamat;

	@Column
	private String telepon;

	@Column
	private String agama;

	@Column
	private String statusPerkawinan;

	@Column
	private String kewarganegaraan;

	// Data Karyawan

	@Column
	private String npwp;

	@Column
	private LocalDate tanggalMasuk;

	@Column
	private String statusPekerjaan;

	@Column
	private String pendidikanTerakhir;

	@OneToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
			CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID", referencedColumnName = "id")
	private User user;

	@ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
			CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "JABATAN_ID", nullable = false)
	private Jabatan jabatan;

	@OneToMany(mappedBy = "pegawai", cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
			CascadeType.REFRESH }, fetch = FetchType.LAZY)
	private Set<PegawaiCuti> pegawaiCutis = new HashSet<>();

	@OneToMany(mappedBy = "pegawai", cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
			CascadeType.REFRESH }, fetch = FetchType.LAZY)
	private Set<PengajuanCuti> pengajuanCuti = new HashSet<>();

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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Jabatan getJabatan() {
		return jabatan;
	}

	public void setJabatan(Jabatan jabatan) {
		this.jabatan = jabatan;
	}

	public Set<PegawaiCuti> getPegawaiCutis() {
		return pegawaiCutis;
	}

	public void setPegawaiCutis(Set<PegawaiCuti> pegawaiCutis) {
		this.pegawaiCutis = pegawaiCutis;
	}

	public Set<PengajuanCuti> getPengajuanCuti() {
		return pengajuanCuti;
	}

	public void setPengajuanCuti(Set<PengajuanCuti> pengajuanCuti) {
		this.pengajuanCuti = pengajuanCuti;
	}

	@Override
	public String toString() {
		return "Pegawai [id=" + id + ", nik=" + nik + ", nama=" + nama + ", tanggalLahir=" + tanggalLahir + ", alamat="
				+ alamat + ", telepon=" + telepon + ", agama=" + agama + ", statusPerkawinan=" + statusPerkawinan
				+ ", kewarganegaraan=" + kewarganegaraan + ", npwp=" + npwp + ", tanggalMasuk=" + tanggalMasuk
				+ ", statusPekerjaan=" + statusPekerjaan + ", pendidikanTerakhir=" + pendidikanTerakhir + ", user="
				+ user + ", jabatan=" + jabatan + ", pegawaiCutis=" + pegawaiCutis + ", pengajuanCuti=" + pengajuanCuti
				+ "]";
	}

}