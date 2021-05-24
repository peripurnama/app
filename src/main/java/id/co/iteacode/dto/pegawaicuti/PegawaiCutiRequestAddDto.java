package id.co.iteacode.dto.pegawaicuti;

import java.util.HashSet;
import java.util.Set;

public class PegawaiCutiRequestAddDto {

	private Long id;
	private Long pegawaiId;
	private Long cutiId;
	private Set<Long> pegawaiCutiDetailIds = new HashSet<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPegawaiId() {
		return pegawaiId;
	}

	public void setPegawaiId(Long pegawaiId) {
		this.pegawaiId = pegawaiId;
	}

	public Long getCutiId() {
		return cutiId;
	}

	public void setCutiId(Long cutiId) {
		this.cutiId = cutiId;
	}

	public Set<Long> getPegawaiCutiDetailIds() {
		return pegawaiCutiDetailIds;
	}

	public void setPegawaiCutiDetailIds(Set<Long> pegawaiCutiDetailIds) {
		this.pegawaiCutiDetailIds = pegawaiCutiDetailIds;
	}

	@Override
	public String toString() {
		return "PegawaiCutiRequestAddDto [id=" + id + ", pegawaiId=" + pegawaiId + ", cutiId=" + cutiId
				+ ", pegawaiCutiDetailIds=" + pegawaiCutiDetailIds + "]";
	}

}
