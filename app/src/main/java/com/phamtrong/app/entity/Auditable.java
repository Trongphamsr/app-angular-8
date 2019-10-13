package com.phamtrong.app.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Auditable<U> {

	@CreatedBy
	@Column(name = "created_by")
	private U createdBy;

	@CreatedDate
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_date")
	private Date createdDate;

	@LastModifiedBy
	@Column(name = "last_modified_by")
	private U lastModifiedBy;

	@LastModifiedDate
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_modified_date")
	private Date lastModifiedDate;
}


