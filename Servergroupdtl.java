package com.intellect.olive.apimanager.jpaentity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the SERVERGROUPDTL database table.
 * 
 */
@Entity
@NamedQuery(name="Servergroupdtl.findAll", query="SELECT s FROM Servergroupdtl s")
public class Servergroupdtl implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ServergroupdtlPK id;

	//bi-directional many-to-one association to Servergroup
	@ManyToOne
	@JoinColumn(name="SERVER_GROUP_ID" ,nullable=false, insertable=false, updatable=false)
	private Servergroup servergroup;

	public Servergroupdtl() {
	}

	public ServergroupdtlPK getId() {
		return this.id;
	}

	public void setId(ServergroupdtlPK id) {
		this.id = id;
	}

	public Servergroup getServergroup() {
		return this.servergroup;
	}

	public void setServergroup(Servergroup servergroup) {
		this.servergroup = servergroup;
	}
	

	@Column(name="ENABLE")
	private String enable;

	public String getEnable() {
		return enable;
	}

	public void setEnable(String enable) {
		this.enable = enable;
	}

}