package id.co.iteacode.dto.pegawaicutidetail;

public class PegawaiCutiDetailRequestAddDto {

	private Long id;
	private Integer sisaCutiTahunan;
	private Long pegawaiCutiId;
	private Long pengajuanCutiId;
	private String status;
	private Integer totalCuti;
	private Integer tahun;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getSisaCutiTahunan() {
		return sisaCutiTahunan;
	}

	public void setSisaCutiTahunan(Integer sisaCutiTahunan) {
		this.sisaCutiTahunan = sisaCutiTahunan;
	}

	public Long getPegawaiCutiId() {
		return pegawaiCutiId;
	}

	public void setPegawaiCutiId(Long pegawaiCutiId) {
		this.pegawaiCutiId = pegawaiCutiId;
	}

	public Long getPengajuanCutiId() {
		return pengajuanCutiId;
	}

	public void setPengajuanCutiId(Long pengajuanCutiId) {
		this.pengajuanCutiId = pengajuanCutiId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getTotalCuti() {
		return totalCuti;
	}

	public void setTotalCuti(Integer totalCuti) {
		this.totalCuti = totalCuti;
	}

	public Integer getTahun() {
		return tahun;
	}

	public void setTahun(Integer tahun) {
		this.tahun = tahun;
	}

	@Override
	public String toString() {
		return "PegawaiCutiDetailRequestAddDto [id=" + id + ", sisaCutiTahunan=" + sisaCutiTahunan + ", pegawaiCutiId="
				+ pegawaiCutiId + ", pengajuanCutiId=" + pengajuanCutiId + ", status=" + status + ", totalCuti="
				+ totalCuti + ", tahun=" + tahun + "]";
	}

}
