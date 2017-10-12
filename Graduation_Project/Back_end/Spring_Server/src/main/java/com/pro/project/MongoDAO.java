package com.pro.project;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

public class MongoDAO {
	private MongoTemplate mongoTemplate;
	
	public void setMongoTemplate(MongoTemplate mogoTemplate) {
		this.mongoTemplate = mogoTemplate;
	}
	
	public void Upload_File_Insert(String Filename, String type, String path){
	    DataVO testVO = new DataVO();
	    testVO.setName(Filename);
	    testVO.setType(type);
	    testVO.setPath(path);
	        
	    mongoTemplate.insert(testVO, "Upload_File");
	}
	
	public void Upload_Script_Insert(String Filename, String path){
	    DataVO testVO = new DataVO();
	    testVO.setName(Filename);
	    testVO.setPath(path);
	        
	    mongoTemplate.insert(testVO, "Upload_Script");
	}
	
	public void Upload_Image_Insert(String Filename){
	    DataVO testVO = new DataVO();
	    testVO.setName(Filename);
	        
	    mongoTemplate.insert(testVO, "Upload_Image");
	}
	
	public HashMap<String, Object> Upload_File_List() {
		List<DataVO> testVO = mongoTemplate.findAll(DataVO.class, "Upload_File");
		ArrayList<String> Namelist = new ArrayList<String>();
		ArrayList<String> Typelist = new ArrayList<String>();
		ArrayList<String> Pathlist = new ArrayList<String>();
		
		
		for(DataVO DataVO : testVO) {
			Namelist.add(DataVO.getName());
			Typelist.add(DataVO.getType());
			Pathlist.add(DataVO.getPath());
		}
		
		HashMap<String, Object> hashmap = new HashMap<String, Object>();
		hashmap.put("Names", Namelist);
		hashmap.put("Types", Typelist);
		hashmap.put("Paths", Pathlist);
		return hashmap;
		
	}
	
	public boolean Upload_File_Check(String Key, String Value) {
		Criteria criteria = new Criteria(Key);
		criteria.is(Value);
		Query query = new Query(criteria);
		DataVO DataVO = mongoTemplate.findOne(query, DataVO.class, "Upload_File");
		if(DataVO == null) {
			return false;
		}
		else
			return true;
	}
	
	public boolean Upload_Script_Check(String Key, String Value) {
		Criteria criteria = new Criteria(Key);
		criteria.is(Value);
		Query query = new Query(criteria);
		DataVO DataVO = mongoTemplate.findOne(query, DataVO.class, "Upload_Script");
		if(DataVO == null) {
			return false;
		}
		else
			return true;
	}
	
	public boolean Upload_Image_Check(String Key, String Value) {
		Criteria criteria = new Criteria(Key);
		criteria.is(Value);
		Query query = new Query(criteria);
		DataVO DataVO = mongoTemplate.findOne(query, DataVO.class, "Upload_Image");
		if(DataVO == null) {
			return false;
		}
		else
			return true;
	}
	
	public class DataVO {
	    @Id
	    private String id;     
	    private String name = null;
	    private String type;
	    private String path;
	  
	    public String getId() {
	        return id;
	    }
	    public void setId(String id) {
	        this.id = id;
	    }
	    public String getName() {
	        return name;
	    }
	    public void setName(String name) {
	        this.name = name;
	    }
	    public String getType() {
	        return type;
	    }
	    public void setType(String type) {
	        this.type = type;
	    }
	    public String getPath() {
	    	return path;
	    }
	    public void setPath(String path) {
	    	this.path = path;
	    }
	}


}
