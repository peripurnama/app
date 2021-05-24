package id.co.iteacode.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import id.co.iteacode.model.audit.Auditable;

@Entity
@Table(name = "JABATAN")
public class Jabatan extends Auditable<String> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private String nama;

	@Column
	private String deskripsi;

	@OneToMany(mappedBy = "jabatan", cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
			CascadeType.REFRESH }, fetch = FetchType.LAZY)
	private Set<Pegawai> pegawai;

	@ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
			CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "bagian_id", nullable = false)
	private Bagian bagian;

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

	public Set<Pegawai> getPegawai() {
		return pegawai;
	}

	public void setPegawai(Set<Pegawai> pegawai) {
		this.pegawai = pegawai;
	}

	public Bagian getBagian() {
		return bagian;
	}

	public void setBagian(Bagian bagian) {
		this.bagian = bagian;
	}

}
