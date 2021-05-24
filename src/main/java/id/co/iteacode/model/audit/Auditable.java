package id.co.iteacode.model.audit;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreType;

@JsonIgnoreType
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class Auditable<U> {
	@CreatedBy
	@Column(name = "created_by")
	protected U createdBy;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MMMM-yyyy HH:mm:ss")
	@CreatedDate
	@Column(name = "created_date")
	protected LocalDateTime createdLocalDateTime;

	@LastModifiedBy
	@Column(name = "last_modified_by")
	protected U lastModifiedBy;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MMMM-yyyy HH:mm:ss")
	@LastModifiedDate
	@Column(name = "last_modified_date")
	protected LocalDateTime lastModifiedLocalDateTime;

	@Column(name = "deleted")
	protected boolean deleted;

	public U getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(U createdBy) {
		this.createdBy = createdBy;
	}

	public LocalDateTime getCreatedLocalDateTime() {
		return createdLocalDateTime;
	}

	public void setCreatedLocalDateTime(LocalDateTime createdLocalDateTime) {
		this.createdLocalDateTime = createdLocalDateTime;
	}

	public U getLastModifiedBy() {
		return lastModifiedBy;
	}

	public void setLastModifiedBy(U lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

	public LocalDateTime getLastModifiedLocalDateTime() {
		return lastModifiedLocalDateTime;
	}

	public void setLastModifiedLocalDateTime(LocalDateTime lastModifiedLocalDateTime) {
		this.lastModifiedLocalDateTime = lastModifiedLocalDateTime;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

}