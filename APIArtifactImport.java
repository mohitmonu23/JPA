package com.intellect.olive.artifacts;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.Root;

import com.intellect.commons.holder.DataHolder;
import com.intellect.commons.holder.Field;
import com.intellect.commons.holder.MatrixField;
import com.intellect.commons.jpa.JPAUtility;
import com.intellect.olive.api.constants.APIArtifactConstant;
import com.intellect.olive.apimanager.jpaentity.Servergroup;

import com.intellect.olive.apimanager.jpaentity.Apidoc;
import com.intellect.olive.apimanager.jpaentity.Apioperation;
import com.intellect.olive.apimanager.jpaentity.Apiparam;
import com.intellect.olive.apimanager.jpaentity.Apiparamdtl;
import com.intellect.olive.apimanager.jpaentity.Apirep;
import com.intellect.olive.apimanager.jpaentity.Apireqtemplate;
import com.intellect.olive.apimanager.jpaentity.Apiresptemplate;
import com.intellect.olive.apimanager.jpaentity.Servergroupdtl;
import com.intellect.olive.apimanager.jpaentity.ServergroupdtlPK;
import com.intellect.olive.apimanager.jpaentity.Serverrep;

public class APIArtifactImport {
	
	public void presistapidoc (Apidoc apiDoc,EntityManager entitymanager)
	{
	try
		{
			entitymanager.joinTransaction();
			entitymanager.merge(apiDoc);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void presistserverc (Servergroup servergroup,EntityManager entitymanager)
	{
	try
		{
			entitymanager.joinTransaction();
			entitymanager.merge(servergroup);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void presistservergroupdtl(Servergroupdtl serverGroupDtl,EntityManager entitymanager)
	{
		try
		{
			entitymanager.joinTransaction();
			entitymanager.merge(serverGroupDtl);
		}
		catch(Exception e)
		{
			 
		}
	}
	
	public void presistserverrep(Serverrep serverrep,EntityManager entitymanager)
	{
		try
		{
			entitymanager.joinTransaction();
			entitymanager.merge(serverrep);
		}
		catch(Exception e)
		{
			
		}
	}
	
	public void deleteapidoc (String apiDocId,EntityManager entitymanager)
	{
		CriteriaBuilder builder = null;
		builder = entitymanager.getCriteriaBuilder();
		try
		{
			CriteriaDelete<Apidoc> apidelete = builder.createCriteriaDelete(Apidoc.class);
			Root<Apidoc> apireqapidocRoot = apidelete.from(Apidoc.class);
			apidelete.where(builder.equal(apireqapidocRoot.get("apidocId"),apiDocId ));
			entitymanager.joinTransaction();
			entitymanager.createQuery(apidelete).executeUpdate();
		}
		catch(Exception e)
		{
			
		}
	}
	
	public void deleteapiservergroup (String serverGroupId,EntityManager entitymanager)
	{
		CriteriaBuilder builder = null;
		builder = entitymanager.getCriteriaBuilder();
		try
		{
			CriteriaDelete<Servergroup> apidelete = builder.createCriteriaDelete(Servergroup.class);
			Root<Servergroup> apireqservergroupRoot = apidelete.from(Servergroup.class);
			apidelete.where(builder.equal(apireqservergroupRoot.get("serverGroupId"),serverGroupId));
			entitymanager.joinTransaction();
			entitymanager.createQuery(apidelete).executeUpdate();
		}
		catch(Exception e)
		{
			
		}
	}
	
	public void  deleteserverGroupDtl(String severGroupDtlId,String serverGroupId,EntityManager entitymanager)
	{
		CriteriaBuilder builder = null;
		builder = entitymanager.getCriteriaBuilder();
		try
		{
			CriteriaDelete<Servergroupdtl> apidelete = builder.createCriteriaDelete(Servergroupdtl.class);
			Root<Servergroupdtl> apireqservergroupdtlRoot = apidelete.from(Servergroupdtl.class);
			apidelete.where(builder.equal(apireqservergroupdtlRoot.get("id").get("serverId"), severGroupDtlId));
			entitymanager.joinTransaction();
			entitymanager.createQuery(apidelete).executeUpdate();
		}
		catch(Exception e)
		{
			
		}
	}
	
	public void deleteserverrep(String serverrepid,EntityManager entitymanager)
	{
		CriteriaBuilder builder = null;
		builder = entitymanager.getCriteriaBuilder();
		try
		{
			CriteriaDelete<Serverrep> apidelete = builder.createCriteriaDelete(Serverrep.class);
			Root<Serverrep> apireqserverrepRoot = apidelete.from(Serverrep.class);
			apidelete.where(builder.equal(apireqserverrepRoot.get("serverId"),serverrepid ));
			entitymanager.joinTransaction();
			entitymanager.createQuery(apidelete).executeUpdate();
		}
		catch(Exception e)
		{
			
		}
	}
	
	public void importapi (DataHolder apiDataHolder)
	{
		EntityManager entitymanager = null;
		try
		{
			entitymanager = JPAUtility.getInstance().getEntityManager(APIArtifactConstant.APIManagerCacheUnit);
			saveapidoc(apiDataHolder,entitymanager);
			//saveapirep(apiDataHolder,entitymanager);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			if (entitymanager != null)
				entitymanager.close();
		}
	}
	
	public void saveapidoc(DataHolder apidocDataHolder,EntityManager entitymanager)
	{
		Apidoc apiDoc = new Apidoc();
		Servergroup servergroup = new Servergroup();
		try
		{
			//apiDoc.setApidocId(apidocDataHolder.getField("APIDocRepository.ID", ".").toString());
			
			apiDoc.setApidocId("12134");
			apiDoc.setApidocDesc(apidocDataHolder.getField("APIDocRepository.Description", ".").toString());
			apiDoc.setApidocType(apidocDataHolder.getField("APIDocRepository.Type", ".").toString());
			apiDoc.setFileName(apidocDataHolder.getField("APIDocRepository.FileName", ".").toString());
			apiDoc.setLogstoragePolicy(apidocDataHolder.getField("APIDocRepository.LogStoragePolicy", ".").toString());
			
			servergroup = saveapiservergroup(apidocDataHolder,entitymanager,0);
			apiDoc.setServergroup(servergroup);
			
			//deleteapidoc (apidocDataHolder.getField("APIDocRepository.ID", ".").toString(),entitymanager);
			
			deleteapidoc ("12134",entitymanager);
			presistapidoc (apiDoc,entitymanager);
		}
		catch(Exception e)
		{
			
		}
	}
	
	public void saveapirep(DataHolder apidocDataHolder,EntityManager entitymanager)
	{
		Apirep apirep = new Apirep();
		Servergroup servergroup = new Servergroup();
		try
		{
			apirep.setApiId(apidocDataHolder.getField("APIRepository.ID",".").toString());
			apirep.setApidoc(apidocDataHolder.getField("APIRepository.APIDocID",".").toString());
			apirep.setApiDesc(apidocDataHolder.getField("APIRepository.Description",".").toString());
			apirep.setResourceUrl(apidocDataHolder.getField("APIRepository.URL",".").toString());
			servergroup = saveapiservergroup(apidocDataHolder,entitymanager,1);
			apirep.setServergroup(servergroup);
		}
		catch(Exception e)
		{
			
		}
	}
	
	public Servergroup saveapiservergroup(DataHolder apidocDataHolder,EntityManager entitymanager,int flag)
	{
		Servergroup servergroup = new Servergroup(); 
		Set<Servergroupdtl> servergroupdtls = null;
		String serverGroupId = "";
		String apidocorrep="";
		try
		{
			if(flag==0){
				apidocorrep = "APIDocRepository";
			}
			else
			{
				apidocorrep = "APIRepository";
			}
			serverGroupId = apidocDataHolder.getField("APIDocRepository.ServerGroup.GroupID",".").toString();
			
			//servergroup.setServerGroupId(serverGroupId);
			
			servergroup.setServerGroupId("12341567");
			servergroup.setServerGroupDesc(apidocDataHolder.getField("APIDocRepository.ServerGroup.Description",".").toString());
		
			servergroupdtls = saveservergroupdtl(apidocDataHolder,entitymanager,serverGroupId);
			
			servergroup.setServergroupdtls(servergroupdtls);
			deleteapiservergroup(serverGroupId,entitymanager);
			presistserverc(servergroup,entitymanager);
		
		}
		catch(Exception e)
		{
			
		}
		return servergroup;
	}
	
	public Set<Servergroupdtl> saveservergroupdtl(DataHolder apidocDataHolder,EntityManager entitymanager,String serverGroupId)
	{
		Servergroupdtl serverGroupDtl = new Servergroupdtl();
		ServergroupdtlPK serverGroupDtlPk =new ServergroupdtlPK();
		Set<Servergroupdtl> servergroupdtls = new HashSet<Servergroupdtl>();
		MatrixField servergroupdtlMatrixField = new MatrixField();
		LinkedList<LinkedHashMap<String, Field>> matrixData=null;
		try
		{
			Field serverGroupDtlField = apidocDataHolder.getField("APIDocRepository.ServerGroup.ServerGroupDetails",".");
			if(serverGroupDtlField instanceof MatrixField)
			{
				servergroupdtlMatrixField =  (MatrixField) serverGroupDtlField;
				matrixData = servergroupdtlMatrixField.getMatrixData();
				
			}
			for(LinkedHashMap<String, Field> serverGroupDtlList : matrixData)
			{
				serverGroupDtl.setEnable(serverGroupDtlList.get("Enable").toString());
				String severId = serverGroupDtlList.get("ServerID").toString();
				serverGroupDtlPk.setServerId(severId);
				serverGroupDtlPk.setServerGroupId(serverGroupId);
				serverGroupDtl.setId(serverGroupDtlPk);
				servergroupdtls.add(serverGroupDtl);
				saveserverrep(serverGroupDtlList,entitymanager);
				deleteserverGroupDtl(severId,serverGroupId,entitymanager);
				presistservergroupdtl(serverGroupDtl,entitymanager);
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return servergroupdtls;
	}
	
	public void saveserverrep(LinkedHashMap<String, Field> serverGroupDtlList,EntityManager entitymanager)
	{
		Serverrep serverrep = new Serverrep();
		String serverrepid = "";
		try
		{
			serverrepid = serverGroupDtlList.get("ServerID").toString();
			serverrep.setServerId(serverGroupDtlList.get("ServerID").toString());
			serverrep.setServerDesc(serverGroupDtlList.get("Description").toString());
			serverrep.setServerUrl(serverGroupDtlList.get("URL").toString());
			deleteserverrep(serverrepid,entitymanager);
			presistserverrep(serverrep,entitymanager);
		}
		catch(Exception e)
		{
			
		}
	}
	
	
	
}
