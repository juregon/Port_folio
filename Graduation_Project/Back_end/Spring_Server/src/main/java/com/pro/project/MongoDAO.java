package com.pro.project;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import net.sf.json.JSONObject;

public class MongoDAO {
	private MongoTemplate mongoTemplate;
	
	public void setMongoTemplate(MongoTemplate mogoTemplate) {
		this.mongoTemplate = mogoTemplate;
	}
	
	public void Upload_File_Insert(String Filename, String type, String path, String date, String status, String description){
	    DataVO testVO = new DataVO();
	    testVO.setName(Filename);
	    testVO.setType(type);
	    testVO.setPath(path);
	    testVO.setDate(date);
	    testVO.setStatus(status);
	    testVO.setDescription(description);
	       
	    mongoTemplate.insert(testVO, "Upload_" + type);
	}
	
	public void Upload_Script_Insert(String Filename, String path, String date, String status, String description){
	    DataVO testVO = new DataVO();
	    testVO.setName(Filename);
	    testVO.setPath(path);
	    testVO.setDate(date);
	    testVO.setStatus(status);
	    testVO.setDescription(description);
	        
	    mongoTemplate.insert(testVO, "Upload_Script");
	}
	
	public void Upload_Image_Insert(String Filename){
	    DataVO testVO = new DataVO();
	    testVO.setName(Filename);
	        
	    mongoTemplate.insert(testVO, "Upload_Image");
	}
	
	public HashMap<Integer, Object> Upload_List(String ListType) {
		List<DataVO> testVO = null;
		switch (Integer.parseInt(ListType)) {
			case 1 : {
				testVO = mongoTemplate.findAll(DataVO.class, "Upload_Data");
				System.out.println("1");
				break;
			}
			case 2 : {
				testVO = mongoTemplate.findAll(DataVO.class, "Upload_Exec");
				System.out.println("2");
				break;
			}
			case 3 : {
				testVO = mongoTemplate.findAll(DataVO.class, "Upload_Script");
				System.out.println("3");
				break;
			}
			case 4 : {
				testVO = mongoTemplate.findAll(DataVO.class, "Upload_JobList");
				System.out.println("4");
				break;
			}
			default : {
				break;
			}
		}
		ArrayList<Object> Datalist = new ArrayList<Object>();	
		JSONObject DataObj;
		HashMap<Integer, Object> hashmap = new HashMap<Integer, Object>();
		for(DataVO DataVO : testVO) {
			DataObj = new JSONObject();
			DataObj.put("Name", DataVO.getName());
			DataObj.put("Date", DataVO.getDate());
			DataObj.put("Status", DataVO.getStatus());
			DataObj.put("Description", DataVO.getDescription());
			Datalist.add(DataObj);		
		}
		for(int i = 0; i < testVO.size(); i++) {
			hashmap.put(i,Datalist.get(i));
		}
		return hashmap;		
	}
	
	public boolean Upload_Exec_Check(String Key, String Value) {
		Criteria criteria = new Criteria(Key);
		criteria.is(Value);
		Query query = new Query(criteria);
		DataVO DataVO = mongoTemplate.findOne(query, DataVO.class, "Upload_Exec");
		if(DataVO == null) {
			return false;
		}
		else
			return true;
	}
	
	public boolean Upload_Data_Check(String Key, String Value) {
		Criteria criteria = new Criteria(Key);
		criteria.is(Value);
		Query query = new Query(criteria);
		DataVO DataVO = mongoTemplate.findOne(query, DataVO.class, "Upload_Data");
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
	    private String date;
	    private String status;
	    private String description;
	    
	  
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
	    public String getDate() {
	        return date;
	    }
	    public void setDate(String date) {
	        this.date = date;
	    }
	    public String getStatus() {
	        return status;
	    }
	    public void setStatus(String status) {
	        this.status = status;
	    }
	    public String getDescription() {
	        return description;
	    }
	    public void setDescription(String description) {
	        this.description = description;
	    }
	}


}
