package id.co.iteacode.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import id.co.iteacode.model.audit.Auditable;

@Entity
@Table(name = "CUTI")
public class Cuti extends Auditable<String> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private String type;

	@Column
	private Integer jumlahCutiTahunan;

	@Column
	private Integer tahun;

	@OneToMany(mappedBy = "cuti", cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
			CascadeType.REFRESH }, fetch = FetchType.LAZY)
	private Set<PegawaiCuti> pegawaiCutis;

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

	public Set<PegawaiCuti> getPegawaiCutis() {
		return pegawaiCutis;
	}

	public void setPegawaiCutis(Set<PegawaiCuti> pegawaiCutis) {
		this.pegawaiCutis = pegawaiCutis;
	}

	public Integer getTahun() {
		return tahun;
	}

	public void setTahun(Integer tahun) {
		this.tahun = tahun;
	}

	@Override
	public String toString() {
		return "Cuti [id=" + id + ", type=" + type + ", jumlahCutiTahunan=" + jumlahCutiTahunan + ", tahun=" + tahun
				+ "]";
	}

}
