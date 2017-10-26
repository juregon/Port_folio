package com.pro.project;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@CrossOrigin(maxAge = 3600)
@Controller
public class HomeController {
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String Home(Model model, HttpSession session, HttpServletRequest request) {
		session = request.getSession();
		return "home";
	}
	
	@RequestMapping(value = "/scriptupload", method = RequestMethod.POST)
	public void ScriptUpload(HttpServletRequest request, HttpServletResponse response, @RequestParam("script") String script, @RequestParam("script_name") String script_name) {
		String path = "Upload\\Script";
		String Fullpath = path + "/" + script_name + ".txt";
		String url = "/mongoContext.xml";      
		AbstractApplicationContext ctx = new ClassPathXmlApplicationContext(url);
		MongoDAO dao = ctx.getBean("mongoDAO", MongoDAO.class);
		
		try{
			File dir = new File(path);
			if(!dir.isDirectory()) {
				dir.mkdirs();
			}
            File file = new File(Fullpath) ;
            FileWriter fw = new FileWriter(file,true) ;
            fw.write(script);
            fw.flush();
            fw.close();
            String TotalPath;
			TotalPath = System.getProperty("user.dir") + "/" + path + "/";
            dao.Upload_Script_Insert(script_name +".txt", TotalPath, Date_Compute(), "Done", "hiru");     
        }catch(Exception e){
            e.printStackTrace();
        }
	}
	
		 
	@ResponseBody
	@RequestMapping(value = "/dockertest", method= RequestMethod.POST)
	public HashMap<String, String> Docker_ImageBuild(@RequestBody HashMap<String, String> info) throws IOException{
		HashMap<String, String> hashmap = new HashMap<String, String>();
		//CommandInput("mkdir ./c/DockerFile");
		//FileWriter Dockerfile = new FileWriter("c//DockerFile//Dockerfile");
		FileWriter Dockerfile = new FileWriter("Dockerfile\\Dockerfile.txt");
		String url = "/mongoContext.xml";      
		AbstractApplicationContext ctx = new ClassPathXmlApplicationContext(url);
		MongoDAO dao = ctx.getBean("mongoDAO", MongoDAO.class);

		if(info.get("image").equals("ubuntu:16.04") == true  &&  dao.Upload_Image_Check("name", info.get("image")) == false) {
			dao.Upload_Image_Insert(info.get("image"));
		}
		
		if(info.get("image") != ""){
			if(dao.Upload_Image_Check("name", info.get("image")) == true) {
				Dockerfile.write("FROM" + " " + info.get("image") + "\r\n");
			}
			else {
				hashmap.put("name", "fail");
				return hashmap;
			}
		}
		else {
			hashmap.put("name", "fail2");
			return hashmap;
		}
		if(info.get("component").equals("java")) {
			Dockerfile.write("RUN apt-get update && \\\n" + 
					"    apt-get upgrade -y && \\\n" + 
					"    apt-get install -y software-properties-common && \\\n" + 
					"    add-apt-repository ppa:webupd8team/java -y && \\\n" + 
					"    apt-get update && \\\n" + 
					"    echo oracle-java8-installer shared/accepted-oracle-license-v1-1 select true | /usr/bin/debconf-set-selections && \\\n" + 
					"    apt-get install -y oracle-java8-installer && \\\n" + 
					"    apt-get clean\n" + 
					" \n" + 
					"ENV JAVA_HOME /usr/lib/jvm/java-8-oracle\r\n");
		}
		
		String Command;
		if(dao.Upload_Data_Check("name", info.get("file_data")) == true) {
			Command = "cp ./c/Data/" + info.get("file_data") + " ./c/DockerFile";
			//CommandInput(Command);
			Dockerfile.write("ADD " + info.get("file_data") + " /usr/" + "\r\n");
		}
		
		if(dao.Upload_Exec_Check("name", info.get("file_execute")) == true) {
			Command = "cp ./c/File/" + info.get("file_execute") + " ./c/DockerFile";
			//CommandInput(Command);
			Dockerfile.write("ADD " + info.get("file_execute") + " /usr/" + "\r\n");
		}
		
		if(dao.Upload_Script_Check("name", info.get("script")) == true) {
			Command = "cp ./c/Script/" + info.get("script") + " ./c/DockerFile";
			//CommandInput(Command);
			Dockerfile.write("ADD " + info.get("script") + " /usr/" + "\r\n");
			Dockerfile.write("RUN chmod 777 /usr/" + info.get("script") + "\r\n");
			Dockerfile.write("RUN apt-get install dos2unix \r\n");
			Dockerfile.write("RUN dos2unix ./usr/" + info.get("script") + "\r\n");
			Dockerfile.write("ENTRYPOINT [\"/usr/" + info.get("script") + "\"]");
		}
		Dockerfile.close();
		Command = "docker build -t " + info.get("image_name") + " ./c/DockerFile";
		//CommandInput(Command);
		//CommandInput("cp ./c/DockerFile/Dockerfile ./c");
		dao.Upload_Image_Insert(info.get("image_name"));
		//CommandInput("rm -rf ./c/DockerFile");
		
		return hashmap;
	}
	
	@RequestMapping(value = "/list/{type}", method = RequestMethod.GET)
	public void FileList(HttpServletRequest request, HttpServletResponse response, @PathVariable String type) {
		HashMap<Integer, Object> hashmap = null;
		String url = "/mongoContext.xml";      
		AbstractApplicationContext ctx = new ClassPathXmlApplicationContext(url);
		MongoDAO dao = ctx.getBean("mongoDAO", MongoDAO.class);
		hashmap = dao.Upload_List(type);
		try {
			JSONArray json2 = new JSONArray();
			for(int i = 0; i < hashmap.size(); i++) {
				json2.add(hashmap.get(i));
			}
	        response.setContentType("application/json; charset=UTF-8");
	        PrintWriter out = response.getWriter();
	        out.println(json2);
	        out.flush();
	        out.close();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}

	/*@RequestMapping(value = "/fileuploading", method = RequestMethod.POST){
	public void FileUpload(MultipartHttpServletRequest request, HttpServletResponse response) {
		String Filepath = "Upload\\File";  
		String Datapath = "Upload\\Data";
		String path = "Upload/File";
		HashMap<String, Object> hashmap = null; 
		try {
			System.out.println("test_fileupload");
			MultipartHttpServletRequest mhsr = (MultipartHttpServletRequest) request; 
			Iterator iter = mhsr.getFileNames(); 
			String type = (String)mhsr.getParameter("abc");
			if(type.equals("File")) 
				path = Filepath;	
			else if(type.equals("Data")) 
				path = Datapath;
			MultipartFile mfile = null; 
			String fieldName = ""; 
			File dir = new File(path); 
			if (!dir.isDirectory()) { 
				dir.mkdirs(); 
			} 
			while (iter.hasNext()) { 
				fieldName = (String) iter.next(); 
				mfile = mhsr.getFile(fieldName);
				String origName; 
				origName = new String(mfile.getOriginalFilename().getBytes("8859_1"), "UTF-8"); 
				if ("".equals(origName)) { 
					continue; 
				}
				String TotalPath;
				TotalPath = System.getProperty("user.dir") + "/" + path + "/";
				File serverFile = new File(path + File.separator + origName); 
				mfile.transferTo(serverFile); 
				String url = "/mongoContext.xml";      
				AbstractApplicationContext ctx = new ClassPathXmlApplicationContext(url);
				MongoDAO dao = ctx.getBean("mongoDAO", MongoDAO.class);
				dao.Upload_File_Insert(origName, "File", TotalPath, Date_Compute(), "Done", "hiru");
				//hashmap = dao.Upload_File_List();
				try {
					JSONArray json2 = new JSONArray();
					JSONObject json = new JSONObject();
				    json.putAll(hashmap);
			        response.setContentType("text/xml; charset=UTF-8");
			        PrintWriter out = response.getWriter();
			        out.println(json);
			        out.flush();
			        out.close();
			    } catch (IOException e) {
			        e.printStackTrace();
			    }
			} 	    	
						
		}catch (UnsupportedEncodingException e) {  
			e.printStackTrace(); 
		}catch (IllegalStateException e) { 
			e.printStackTrace(); 
		} catch (IOException e) { 
			e.printStackTrace(); 
		}
	}*/
	
	@RequestMapping(value = "/fileupload", method = RequestMethod.POST)  
	public void FileUpload(MultipartHttpServletRequest request, HttpServletResponse response) {
		String Filepath = "Upload\\Exec";  
		String Datapath = "Upload\\Data";
		String path = null;
		HashMap<String, Object> hashmap = null; 
		try {
			System.out.println("test_fileupload");
			MultipartHttpServletRequest mhsr = (MultipartHttpServletRequest) request; 
			Iterator iter = mhsr.getFileNames(); 
			String type = (String)mhsr.getParameter("type");
			if(type.equals("Exec")) 
				path = Filepath;	
			else if(type.equals("Data")) 
				path = Datapath;
			MultipartFile mfile = null; 
			String fieldName = ""; 
			File dir = new File(path); 
			if (!dir.isDirectory()) { 
				dir.mkdirs(); 
			} 
			while (iter.hasNext()) { 
				fieldName = (String) iter.next(); 
				mfile = mhsr.getFile(fieldName);
				String origName; 
				origName = new String(mfile.getOriginalFilename().getBytes("8859_1"), "UTF-8"); 
				if ("".equals(origName)) { 
					continue; 
				}
				String TotalPath;
				TotalPath = System.getProperty("user.dir") + "/" + path + "/";
				File serverFile = new File(path + File.separator + origName); 
				mfile.transferTo(serverFile); 
				String url = "/mongoContext.xml";      
				AbstractApplicationContext ctx = new ClassPathXmlApplicationContext(url);
				MongoDAO dao = ctx.getBean("mongoDAO", MongoDAO.class);
				dao.Upload_File_Insert(origName, type, TotalPath, Date_Compute(), "Done", request.getParameter("descrip"));
				//hashmap = dao.Upload_File_List();
				/*try {
					JSONArray json2 = new JSONArray();
					JSONObject json = new JSONObject();
				    json.putAll(hashmap);
			        response.setContentType("text/xml; charset=UTF-8");
			        PrintWriter out = response.getWriter();
			        out.println(json);
			        out.flush();
			        out.close();
			    } catch (IOException e) {
			        e.printStackTrace();
			    }*/
			} 	    	
						
		}catch (UnsupportedEncodingException e) {  
			e.printStackTrace(); 
		}catch (IllegalStateException e) { 
			e.printStackTrace(); 
		} catch (IOException e) { 
			e.printStackTrace(); 
		}
	} 

	@ResponseBody
	@RequestMapping(value = "/progresscheck", method = RequestMethod.GET)
	public void UploadProgress(
			HttpSession session
	        ,@RequestParam("uploadId") String uploadId
	        ,HttpServletRequest request
	        ,HttpServletResponse response) {
	    JSONObject jsonResult = null;
	    session = request.getSession();
	    Object uploadInfo = session.getAttribute(uploadId);
	    if(uploadInfo != null)
	    {
	        jsonResult = (JSONObject)uploadInfo;
	    }
	    else
	    {
	        jsonResult = new JSONObject();
	        jsonResult.put("bytesRead", 0);
	        jsonResult.put("contentLength", 0);
	    }
	 
	    try {
	    	System.out.println("uploadId : " + uploadId);
	    	System.out.println("ProgressChcek" + jsonResult.toString());
	        String jsonStr = jsonResult.toString();
	        response.setContentType("text/xml; charset=UTF-8");
	        PrintWriter out = response.getWriter();
	        out.println(jsonStr);
	        out.flush();
	        out.close();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	
	public static void CommandInput(String Command) {
		StringBuffer output = new StringBuffer();
		Process p;
		try {
			p = Runtime.getRuntime().exec(Command);
			p.waitFor();
			BufferedReader reader =
	                        new BufferedReader(new InputStreamReader(p.getInputStream()));
	        String line = "";
			while ((line = reader.readLine())!= null) {
				output.append(line + "\n");
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static boolean NullCheck(String CheckString) {		
		if(CheckString != "")
			return false;
		else
			return true;
	}
	
	
	public String Date_Compute() {
		Calendar cal = Calendar.getInstance();
		int Year = cal.get(cal.YEAR);
		int Month = cal.get(cal.MONTH)+1;
		int Date = cal.get(cal.DATE);
		return Year + "-" + Month + "-" + Date;
	}

}
