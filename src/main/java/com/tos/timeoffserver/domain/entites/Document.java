package com.tos.timeoffserver.domain.entites;


import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "documents")
public class Document {

	private Long documentId;
	private String documentFileName;

	@ManyToMany(mappedBy = "documents")
	private Set<TimeOffRequest> requests = new HashSet<TimeOffRequest>(0);
	
	public Document() {
	}

	public Document(String documentFileName) {
		this.documentFileName = documentFileName;
	}

	@Id
	@GeneratedValue
	@Column(name = "document_id")

	public Long getDocumentId() {
		return documentId;
	}

	public void setDocumentId(Long documentId) {
		this.documentId = documentId;
	}

	@Column(name = "file_name", nullable = false)

	public String getDocumentFileName() {
		return documentFileName;
	}

	public void setDocumentFileName(String documentFileName) {
		this.documentFileName = documentFileName;
	}
}
