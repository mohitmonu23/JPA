package com.intellect.olive.apimanager.jpaentity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the SERVERGROUPDTL database table.
 * 
 */
@Embeddable
public class ServergroupdtlPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="SERVER_GROUP_ID")
	private String serverGroupId;

	@Column(name="SERVER_ID")
	private String serverId;

	public ServergroupdtlPK() {
	}
	public String getServerGroupId() {
		return this.serverGroupId;
	}
	public void setServerGroupId(String serverGroupId) {
		this.serverGroupId = serverGroupId;
	}
	public String getServerId() {
		return this.serverId;
	}
	public void setServerId(String serverId) {
		this.serverId = serverId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ServergroupdtlPK)) {
			return false;
		}
		ServergroupdtlPK castOther = (ServergroupdtlPK)other;
		return 
			this.serverGroupId.equals(castOther.serverGroupId)
			&& this.serverId.equals(castOther.serverId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.serverGroupId.hashCode();
		hash = hash * prime + this.serverId.hashCode();
		
		return hash;
	}
}