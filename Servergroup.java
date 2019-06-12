package com.intellect.olive.apimanager.jpaentity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the SERVERGROUP database table.
 * 
 */
@Entity
@NamedQuery(name="Servergroup.findAll", query="SELECT s FROM Servergroup s")
public class Servergroup implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="SERVER_GROUP_ID")
	private String serverGroupId;

	@Column(name="SERVER_GROUP_DESC")
	private String serverGroupDesc;

	//bi-directional one-to-one association to Apidoc
	@OneToOne(mappedBy="servergroup")
	private Apidoc apidoc;

	//bi-directional one-to-one association to Apioperation
	@OneToOne(mappedBy="servergroup")
	private Apioperation apioperation;

	//bi-directional one-to-one association to Apirep
	@OneToOne(mappedBy="servergroup")
	private Apirep apirep;

	//bi-directional many-to-one association to Servergroupdtl
	@OneToMany(mappedBy="servergroup", fetch=FetchType.EAGER)
	private Set<Servergroupdtl> servergroupdtls;

	public Servergroup() {
	}

	public String getServerGroupId() {
		return this.serverGroupId;
	}

	public void setServerGroupId(String serverGroupId) {
		this.serverGroupId = serverGroupId;
	}

	public String getServerGroupDesc() {
		return this.serverGroupDesc;
	}

	public void setServerGroupDesc(String serverGroupDesc) {
		this.serverGroupDesc = serverGroupDesc;
	}

	public Apidoc getApidoc() {
		return this.apidoc;
	}

	public void setApidoc(Apidoc apidoc) {
		this.apidoc = apidoc;
	}

	public Apioperation getApioperation() {
		return this.apioperation;
	}

	public void setApioperation(Apioperation apioperation) {
		this.apioperation = apioperation;
	}

	public Apirep getApirep() {
		return this.apirep;
	}

	public void setApirep(Apirep apirep) {
		this.apirep = apirep;
	}

	public Set<Servergroupdtl> getServergroupdtls() {
		return this.servergroupdtls;
	}

	public void setServergroupdtls(Set<Servergroupdtl> servergroupdtls) {
		this.servergroupdtls = servergroupdtls;
	}

	public Servergroupdtl addServergroupdtl(Servergroupdtl servergroupdtl) {
		getServergroupdtls().add(servergroupdtl);
		servergroupdtl.setServergroup(this);

		return servergroupdtl;
	}

	public Servergroupdtl removeServergroupdtl(Servergroupdtl servergroupdtl) {
		getServergroupdtls().remove(servergroupdtl);
		servergroupdtl.setServergroup(null);

		return servergroupdtl;
	}

}