package id.co.iteacode.request;

public class CutiRequest {

	private Long id;
	private String type;
	private Integer jumlahCutiTahunan;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getJumlahCutiTahunan() {
		return jumlahCutiTahunan;
	}

	public void setJumlahCutiTahunan(Integer jumlahCutiTahunan) {
		this.jumlahCutiTahunan = jumlahCutiTahunan;
	}

}
