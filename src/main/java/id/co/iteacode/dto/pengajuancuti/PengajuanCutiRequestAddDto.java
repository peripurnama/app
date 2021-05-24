package id.co.iteacode.dto.pengajuancuti;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import id.co.iteacode.validator.pengajuan_cuti.ValidBody;
import id.co.iteacode.validator.pengajuan_cuti.ValidEndDate;
import id.co.iteacode.validator.pengajuan_cuti.ValidFile;
import id.co.iteacode.validator.pengajuan_cuti.ValidStartDate;
import id.co.iteacode.validator.pengajuan_cuti.ValidTitle;

@ValidFile
@ValidEndDate
public class PengajuanCutiRequestAddDto {

	private Long id;
	@ValidTitle
	private String title;
	private boolean approved;
	private String status;
	
	@ValidBody
	private String body;
	private String fileName;
	private Long pegawaiId;
	
	private Date startDate;
	private Date endDate;
	private Integer totalCuti;
	
	private MultipartFile file;

	@ValidStartDate
	private String formatStartDate;
	
	
	private String formatEndDate;

	SimpleDateFormat format;

	public PengajuanCutiRequestAddDto() {
		format = new SimpleDateFormat("MM/dd/yyyy");
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public boolean isApproved() {
		return approved;
	}

	public void setApproved(boolean approved) {
		this.approved = approved;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public Long getPegawaiId() {
		return pegawaiId;
	}

	public void setPegawaiId(Long pegawaiId) {
		this.pegawaiId = pegawaiId;
	}

	public Date getStartDate() {

		if (formatStartDate != null && !formatStartDate.equals("")) {
			try {
				startDate = format.parse(formatStartDate);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		if (formatEndDate != null && !formatEndDate.equals("")) {
			try {
				endDate = format.parse(formatEndDate);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
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

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	public String getFormatStartDate() {
		return formatStartDate;
	}

	public void setFormatStartDate(String formatStartDate) {
		this.formatStartDate = formatStartDate;
	}

	public String getFormatEndDate() {
		return formatEndDate;
	}

	public void setFormatEndDate(String formatEndDate) {
		this.formatEndDate = formatEndDate;
	}

	public void setFormatEndDate(Date date) {

		if (date != null) {
			this.formatEndDate = format.format(date);
		}
	}

	public void setFormatStartDate(Date date) {

		if (date != null) {
			this.formatStartDate = format.format(date);
		}
	}

	@Override
	public String toString() {
		return "PengajuanCutiRequestAddDto [id=" + id + ", title=" + title + ", approved=" + approved + ", status="
				+ status + ", body=" + body + ", fileName=" + fileName + ", pegawaiId=" + pegawaiId + ", startDate="
				+ startDate + ", endDate=" + endDate + ", totalCuti=" + totalCuti + ", file=" + file
				+ ", formatStartDate=" + formatStartDate + ", formatEndDate=" + formatEndDate + "]";
	}

}
