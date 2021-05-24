package id.co.iteacode.request;

import java.util.Date;

public class PegawaiForm {

	private Long id;
	private String npwp;
	private Date tanggalMasuk;
	private String statusPekerjaan;
	private String pendidikanTerakhir;
	private Integer limitCutiTahunan;
	private Long personId;
	private Long jabatanId;
	private Long bagianId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNpwp() {
		return npwp;
	}

	public void setNpwp(String npwp) {
		this.npwp = npwp;
	}

	public Date getTanggalMasuk() {
		return tanggalMasuk;
	}

	public void setTanggalMasuk(Date tanggalMasuk) {
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

	public Long getPersonId() {
		return personId;
	}

	public void setPersonId(Long personId) {
		this.personId = personId;
	}

	public Long getJabatanId() {
		return jabatanId;
	}

	public void setJabatanId(Long jabatanId) {
		this.jabatanId = jabatanId;
	}

	public Long getBagianId() {
		return bagianId;
	}

	public void setBagianId(Long bagianId) {
		this.bagianId = bagianId;
	}

	public Integer getLimitCutiTahunan() {
		return limitCutiTahunan;
	}

	public void setLimitCutiTahunan(Integer limitCutiTahunan) {
		this.limitCutiTahunan = limitCutiTahunan;
	}

}
