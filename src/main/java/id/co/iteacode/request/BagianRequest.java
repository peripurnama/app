package id.co.iteacode.request;

public class BagianRequest {

	private Long id;
	private String name;
	private String deskripsi;
	private Long departementId;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	
	
}
