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
@Table(name = "PENGAJUAN_CUTI")
public class PengajuanCuti extends Auditable<String> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private String title;

	@Lob
	@Column
	private String body;

	@Lob
	@Column
	private String fileName;

	@Temporal(TemporalType.DATE)
	@Column
	private Date startDate;

	@Temporal(TemporalType.DATE)
	@Column
	private Date endDate;

	@Column
	private Integer totalCuti;

	@ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
			CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "PEGAWAI_ID", nullable = false)
	private Pegawai pegawai;

	@OneToMany(mappedBy = "pengajuanCuti", cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
			CascadeType.REFRESH }, fetch = FetchType.LAZY)
	private Set<PegawaiCutiDetail> pegawaiCutiDetails;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Pegawai getPegawai() {
		return pegawai;
	}

	public void setPegawai(Pegawai pegawai) {
		this.pegawai = pegawai;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Integer getTotalCuti() {
		return totalCuti;
	}

	public void setTotalCuti(Integer totalCuti) {
		this.totalCuti = totalCuti;
	}

	public Set<PegawaiCutiDetail> getPegawaiCutiDetails() {
		return pegawaiCutiDetails;
	}

	public void setPegawaiCutiDetails(Set<PegawaiCutiDetail> pegawaiCutiDetails) {
		this.pegawaiCutiDetails = pegawaiCutiDetails;
	}

	@Override
	public String toString() {
		return "PengajuanCuti [id=" + id + ", title=" + title + ", body=" + body + ", fileName=" + fileName
				+ ", startDate=" + startDate + ", endDate=" + endDate + ", totalCuti=" + totalCuti + "]";
	}

}
