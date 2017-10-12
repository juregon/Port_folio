package com.pro.project;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import net.sf.json.JSONObject;

@CrossOrigin(maxAge = 3600)
@Controller
public class HomeController {
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String Home(Model model, HttpSession session, HttpServletRequest request) {
		session = request.getSession();
		return "home";
	}
	
	@RequestMapping(value = "/scriptUpload", method = RequestMethod.POST)
	public String ScriptUpload(@RequestParam("script") String script, @RequestParam("script_name") String script_name) {
		String path = "c/Script";
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
            dao.Upload_Script_Insert(script_name +".txt", TotalPath);
                    
        }catch(Exception e){
            e.printStackTrace();
        }
		
		return "home";
	}
	
		 
	@ResponseBody
	@RequestMapping(value = "/dockertest", method= RequestMethod.POST)
	public HashMap<String, String> Docker_ImageBuild(@RequestBody HashMap<String, String> info) throws IOException{
		HashMap<String, String> hashmap = new HashMap<String, String>();
		CommandInput("mkdir ./c/DockerFile");
		FileWriter Dockerfile = new FileWriter("c//DockerFile//Dockerfile");
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
		if(dao.Upload_File_Check("name", info.get("file_data")) == true) {
			Command = "cp ./c/Data/" + info.get("file_data") + " ./c/DockerFile";
			CommandInput(Command);
			Dockerfile.write("ADD " + info.get("file_data") + " /usr/" + "\r\n");
		}
		
		if(dao.Upload_File_Check("name", info.get("file_execute")) == true) {
			Command = "cp ./c/File/" + info.get("file_execute") + " ./c/DockerFile";
			CommandInput(Command);
			Dockerfile.write("ADD " + info.get("file_execute") + " /usr/" + "\r\n");
		}
		
		if(dao.Upload_Script_Check("name", info.get("script")) == true) {
			Command = "cp ./c/Script/" + info.get("script") + " ./c/DockerFile";
			CommandInput(Command);
			Dockerfile.write("ADD " + info.get("script") + " /usr/" + "\r\n");
			Dockerfile.write("RUN chmod 777 /usr/" + info.get("script") + "\r\n");
			Dockerfile.write("RUN apt-get install dos2unix \r\n");
			Dockerfile.write("RUN dos2unix ./usr/" + info.get("script") + "\r\n");
			Dockerfile.write("ENTRYPOINT [\"/usr/" + info.get("script") + "\"]");
		}
		Dockerfile.close();
		Command = "docker build -t " + info.get("image_name") + " ./c/DockerFile";
		CommandInput(Command);
		CommandInput("cp ./c/DockerFile/Dockerfile ./c");
		dao.Upload_Image_Insert(info.get("image_name"));
		CommandInput("rm -rf ./c/DockerFile");
		
		return hashmap;
	}
	

	@RequestMapping(value = "/fileupload", method = RequestMethod.POST)  
	public void FileUpload(MultipartHttpServletRequest request, HttpServletResponse response) {
		String Filepath = "Upload/File";  
		String Datapath = "Upload/Data";
		String path = null;
		HashMap<String, Object> hashmap = null; 
		try {
			MultipartHttpServletRequest mhsr = (MultipartHttpServletRequest) request; 
			Iterator iter = mhsr.getFileNames(); 
			String type = (String)mhsr.getParameter("abc");
			*if(type.equals("File")) 
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
				dao.Upload_File_Insert(origName, "File", TotalPath);

				hashmap = dao.Upload_File_List();
				
				try {
			        String jsonStr = hashmap.toString();
			        response.setContentType("text/xml; charset=UTF-8");
			        PrintWriter out = response.getWriter();
			        out.println(jsonStr);
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

}
