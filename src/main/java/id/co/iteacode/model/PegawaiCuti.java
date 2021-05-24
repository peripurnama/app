package id.co.iteacode.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import id.co.iteacode.model.audit.Auditable;

@Entity
@Table(name = "PEGAWAI_CUTI")
public class PegawaiCuti extends Auditable<String> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
			CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "PEGAWAI_ID", nullable = false)
	private Pegawai pegawai;
	
	@ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
			CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "CUTI_ID", nullable = false)
	private Cuti cuti;

	@OneToMany(mappedBy = "pegawaiCuti", cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
			CascadeType.REFRESH }, fetch = FetchType.LAZY)
	private Set<PegawaiCutiDetail> pegawaiCutiDetails;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Pegawai getPegawai() {
		return pegawai;
	}

	public void setPegawai(Pegawai pegawai) {
		this.pegawai = pegawai;
	}

	public Cuti getCuti() {
		return cuti;
	}

	public void setCuti(Cuti cuti) {
		this.cuti = cuti;
	}

	public Set<PegawaiCutiDetail> getPegawaiCutiDetails() {
		return pegawaiCutiDetails;
	}

	public void setPegawaiCutiDetails(Set<PegawaiCutiDetail> pegawaiCutiDetails) {
		this.pegawaiCutiDetails = pegawaiCutiDetails;
	}
	
}
