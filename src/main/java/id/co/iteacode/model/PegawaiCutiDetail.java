package id.co.iteacode.model;

import java.util.Date;

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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import id.co.iteacode.model.audit.Auditable;

@Entity
@Table(name = "PEGAWAI_CUTI_DETAIL")
public class PegawaiCutiDetail extends Auditable<String> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private Integer sisaCutiTahunan;

	@Column
	private Integer totalCuti;

	@Column
	private String status;

	@Column
	private Integer tahun;

	@Column
	private boolean approved;

	@Column
	private boolean discarded;
	
	@Column
	private String discardedBy;

	@ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
			CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "PEGAWAI_CUTI_ID", nullable = false)
	private PegawaiCuti pegawaiCuti;

	@ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
			CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "PENGAJUAN_CUTI_ID", nullable = false)
	private PengajuanCuti pengajuanCuti;

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

	public PegawaiCuti getPegawaiCuti() {
		return pegawaiCuti;
	}

	public void setPegawaiCuti(PegawaiCuti pegawaiCuti) {
		this.pegawaiCuti = pegawaiCuti;
	}

	public PengajuanCuti getPengajuanCuti() {
		return pengajuanCuti;
	}

	public void setPengajuanCuti(PengajuanCuti pengajuanCuti) {
		this.pengajuanCuti = pengajuanCuti;
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

	public boolean isApproved() {
		return approved;
	}

	public void setApproved(boolean approved) {
		this.approved = approved;
	}

	public boolean isDiscarded() {
		return discarded;
	}

	public void setDiscarded(boolean discarded) {
		this.discarded = discarded;
	}

	public String getDiscardedBy() {
		return discardedBy;
	}

	public void setDiscardedBy(String discardedBy) {
		this.discardedBy = discardedBy;
	}

	@Override
	public String toString() {
		return "PegawaiCutiDetail [id=" + id + ", sisaCutiTahunan=" + sisaCutiTahunan + ", totalCuti=" + totalCuti
				+ ", status=" + status + ", tahun=" + tahun + ", approved=" + approved + "]";
	}

}
