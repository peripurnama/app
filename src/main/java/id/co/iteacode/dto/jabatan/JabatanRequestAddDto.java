package id.co.iteacode.dto.jabatan;

public class JabatanRequestAddDto {

	private Long id;
	private String nama;
	private String deskripsi;
	private Long bagianId;

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

	public Long getBagianId() {
		return bagianId;
	}

	public void setBagianId(Long bagianId) {
		this.bagianId = bagianId;
	}

	@Override
	public String toString() {
		return "JabatanRequestAddDto [id=" + id + ", nama=" + nama + ", deskripsi=" + deskripsi + ", bagianId="
				+ bagianId + "]";
	}

}
