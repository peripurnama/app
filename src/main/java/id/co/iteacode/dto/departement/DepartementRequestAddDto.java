package id.co.iteacode.dto.departement;

public class DepartementRequestAddDto {

	private Long id;
	private String nama;
	private String deskripsi;

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

	@Override
	public String toString() {
		return "DepartementRequestAddDto [id=" + id + ", nama=" + nama + ", deskripsi=" + deskripsi + "]";
	}

}
