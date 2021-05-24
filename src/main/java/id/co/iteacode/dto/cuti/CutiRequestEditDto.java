package id.co.iteacode.dto.cuti;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CutiRequestEditDto {

	private Long id;
	private String type;
	
	@NotNull(message = "Cuti tahunan tidak boleh kosong")
	@Min(value = 1, message = "Minimal cuti tahunan 1")
	@Max(value = 30, message = "Maksimal cuti tahunan 30")
	private Integer jumlahCutiTahunan;
	private Integer tahun;
	private Set<Long> pegawaiCutiIds = new HashSet<>();
	private String typeAndYear;

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

	public Integer getTahun() {
		return tahun;
	}

	public void setTahun(Integer tahun) {
		this.tahun = tahun;
	}

	public Set<Long> getPegawaiCutiIds() {
		return pegawaiCutiIds;
	}

	public void setPegawaiCutiIds(Set<Long> pegawaiCutiIds) {
		this.pegawaiCutiIds = pegawaiCutiIds;
	}

	public String getTypeAndYear() {
		return typeAndYear;
	}

	public void setTypeAndYear(String typeAndYear) {
		this.typeAndYear = typeAndYear;
	}

	@Override
	public String toString() {
		return "CutiRequestEditDto [id=" + id + ", type=" + type + ", jumlahCutiTahunan=" + jumlahCutiTahunan
				+ ", tahun=" + tahun + ", pegawaiCutiIds=" + pegawaiCutiIds + ", typeAndYear=" + typeAndYear + "]";
	}

}
