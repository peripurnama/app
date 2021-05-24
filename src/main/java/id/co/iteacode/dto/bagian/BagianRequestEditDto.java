package id.co.iteacode.dto.bagian;

public class BagianRequestEditDto {

	private Long id;
	private String nama;
	private String deskripsi;
	private Long departementId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public String getDeskripsi() {
		return deskripsi;
	}

	public void setDeskripsi(String deskripsi) {
		this.deskripsi = deskripsi;
	}

	public Long getDepartementId() {
		return departementId;
	}

	public void setDepartementId(Long departementId) {
		this.departementId = departementId;
	}

	@Override
	public String toString() {
		return "BagianRequestAddDto [id=" + id + ", nama=" + nama + ", deskripsi=" + deskripsi + ", departementId="
				+ departementId + "]";
	}

}
