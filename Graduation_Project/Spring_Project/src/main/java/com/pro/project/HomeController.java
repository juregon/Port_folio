package com.pro.project;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
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

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.taglibs.standard.extra.spath.ParseException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
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

@CrossOrigin(maxAge = 3600)
@Controller
public class HomeController {
	private static String chronosAddr = null;
	private static int chronosPort = 0;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String Home(Model model, HttpSession session, HttpServletRequest request){
		session = request.getSession();
		return "home";
	}
	
	@RequestMapping(value = "/scriptupload", method = RequestMethod.POST) 
	public void ScriptUpload(HttpServletRequest request, HttpServletResponse response, 
			@RequestParam("script") String script, @RequestParam("script_name") 
			String script_name) throws Exception{
		String path = "Upload//Script";
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
	
	@RequestMapping(value = "/jobcreate", method = RequestMethod.POST) 
	public void jobCreate(HttpServletRequest request, HttpServletResponse response, 
			@RequestBody HashMap<String, String> info) throws Exception{
		String url = "/mongoContext.xml";      
		AbstractApplicationContext ctx = new ClassPathXmlApplicationContext(url);
		MongoDAO dao = ctx.getBean("mongoDAO", MongoDAO.class);
		JSONParser parser2 = new JSONParser();
		JSONObject jsonobj2  = new JSONObject();
		try {
			JSONArray obj = (JSONArray)parser2.parse(new FileReader("/ichthys/sshmodule/ip.json"));		
			jsonobj2 = (JSONObject) obj.get(0);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		HttpClient httpClient2 = HttpClientBuilder.create().build();
	    HttpGet httpget = new HttpGet("http://" + jsonobj2.get("Addr") + "/marathon/v2/tasks");
	    HttpResponse httpresponse2 = httpClient2.execute(httpget);
	
		BufferedReader reader = new BufferedReader(new InputStreamReader(
	            httpresponse2.getEntity().getContent()));
	
	    String inputLine;
	    StringBuffer response3 = new StringBuffer();
	    while ((inputLine = reader.readLine()) != null) {
	        response3.append(inputLine);
	    }
	    reader.close();

	    Object objList = JSONValue.parse(response3.toString());
	    JSONObject JSONObjList = (JSONObject)objList;
	    
	    JSONArray jsontask = (JSONArray) JSONObjList.get("tasks");
	    JSONObject jsonAddrs = (JSONObject) jsontask.get(0);
	    JSONArray jsonAddrs2 = (JSONArray) jsonAddrs.get("ipAddresses");
	    JSONObject jsonAddr = (JSONObject) jsonAddrs2.get(0);
	    chronosAddr = (String) jsonAddr.get("ipAddress");
	    
        JSONArray jsontask2 = (JSONArray) JSONObjList.get("tasks");
        JSONObject jsonports = (JSONObject) jsontask.get(0);
        JSONArray jsonport = (JSONArray) jsonports.get("ports");
        chronosPort =  ((Long) jsonport.get(0)).intValue();
	    
        System.out.println("Address : " + chronosAddr);
		System.out.println("Port : " + chronosPort);
		
		JSONParser parser = new JSONParser();
		JSONObject jsonobj  = new JSONObject();
		try {
			JSONObject obj = (JSONObject)parser.parse(new FileReader("/job.json"));		
			obj.put("cpus", info.get("cpus"));
			obj.put("mem", info.get("mem"));
			obj.put("disk", info.get("disk"));
			obj.put("name", info.get("jobname"));
			JSONObject obj2 = (JSONObject) obj.get("container");
			obj2.put("image", dao.Upload_Image_Find("name", info.get("imagename")));
			obj.put("container", obj2);
			FileWriter file = new FileWriter("/job2.json");
			file.write(obj.toJSONString());
			file.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("json file create");
		/*String Command = "curl -L -H 'Content-Type: application/json' -X POST -d @/job2.json http://220.230.121.161:24848/scheduler/iso8601";
		CommandInput(Command, "none");*/
		try {
			jsonobj = (JSONObject)parser.parse(new FileReader("/job2.json"));						
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		HttpClient httpClient = HttpClientBuilder.create().build(); //Use this instead 
	    HttpPost request2 = new HttpPost("http://175.158.15.45:" + chronosPort + "/scheduler/iso8601");
	    StringEntity params =new StringEntity(jsonobj.toJSONString());
	    request2.addHeader("Content-Type", "application/json");
	    request2.setEntity(params);
	    HttpResponse response2 = httpClient.execute(request2);
	    //response2.get
	    //handle response here...		
		dao.Upload_Job_Insert(info.get("jobname"), Date_Compute(), "Done", info.get("description"));
		System.out.println("json push success");	
	}
	
		 
	@ResponseBody
	@RequestMapping(value = "/imagebuild", method= RequestMethod.POST)
	public HashMap<String, String> Docker_ImageBuild(@RequestBody HashMap<String, String> info) throws Exception{
		System.out.println("hiru");
		HashMap<String, String> hashmap = new HashMap<String, String>();
		File dir = new File("ImageBuild");
		if(!dir.isDirectory()) {
			dir.mkdirs();
		}
		
		FileWriter Dockerfile = new FileWriter("ImageBuild//Dockerfile");
		String url = "/mongoContext.xml";      
		AbstractApplicationContext ctx = new ClassPathXmlApplicationContext(url);
		MongoDAO dao = ctx.getBean("mongoDAO", MongoDAO.class);
		
		if(info.get("image").equals("UBUNTU")) {
			Dockerfile.write("FROM" + " " + "ubuntu:14.04" + "\r\n");
		}
		System.out.println("2");
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
			Command = "cp ./Upload/Data/" + info.get("file_data") + " ./ImageBuild/";
			CommandInput(Command, "none");
			Dockerfile.write("ADD " + info.get("file_data") + " /test/" + "\r\n");
		}
		
		if(dao.Upload_Exec_Check("name", info.get("file_execute")) == true) {
			Command = "cp ./Upload/Exec/" + info.get("file_execute") + " ./ImageBuild/";
			CommandInput(Command, "none");
			Dockerfile.write("ADD " + info.get("file_execute") + " /test/" + "\r\n");
			Dockerfile.write("RUN chmod 777 /test/" + info.get("file_execute") + "\r\n");
		}
		if(dao.Upload_Script_Check("name", info.get("script")) == true) {
			Command = "cp ./Upload/Script/" + info.get("script") + " ./ImageBuild/";
			CommandInput(Command, "none");
			Dockerfile.write("ADD " + info.get("script") + " /test/" + "\r\n");
			Dockerfile.write("RUN chmod 777 /test/" + info.get("script") + "\r\n");
			Dockerfile.write("RUN apt-get install dos2unix \r\n");
			Dockerfile.write("RUN dos2unix ./test/" + info.get("script") + "\r\n");
			//System.out.println
			Dockerfile.write("ENTRYPOINT [\"/test/" + info.get("script") + "\"]");
		}
		Dockerfile.close();
		System.out.println("Dokcerfile make before");
		JSONParser parser = new JSONParser();
		JSONObject jsonobj  = new JSONObject();
		try {
			JSONArray obj = (JSONArray)parser.parse(new FileReader("/ichthys/sshmodule/ip.json"));		
			jsonobj = (JSONObject) obj.get(0);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		Command = "docker build -t " + jsonobj.get("Addr") + ":" + "5000" + "/" + info.get("image_name") + " ./ImageBuild/";
		CommandInput(Command, "none");	
		System.out.println("Dokcerfile make");
		dao.Upload_Image_Insert(info.get("image_name"), jsonobj.get("Addr") + ":" + "5000" + "/" + info.get("image_name"), Date_Compute(), "Done", "Image_hiru");
		CommandInput("rm -rf ./ImageBuild/", "none");
		Command = "docker push " + jsonobj.get("Addr") + ":" + "5000" + "/" + info.get("image_name");
		CommandInput(Command, "none");
		System.out.println("push success");
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
			System.out.println(hashmap.toString());
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
	
	@RequestMapping(value = "/fileupload", method = RequestMethod.POST)  
	public void FileUpload(MultipartHttpServletRequest request, HttpServletResponse response) {
		String Filepath = "Upload//Exec";  
		String Datapath = "Upload//Data";
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
			System.out.println("dwa : " + path);
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
	    
	    
	    String resultString = null;
	    
	    
	    if(uploadInfo != null)
	    {
	    	resultString = uploadInfo.toString();
	        //jsonResult = (JSONObject)uploadInfo;
	    }
	    else
	    {
	        jsonResult = new JSONObject();
	        jsonResult.put("bytesRead", 0);
	        jsonResult.put("contentLength", 0);
	    }
	 
	    try {
	    	//System.out.println("uploadId : " + uploadId);
	    	//System.out.println("ProgressChcek" + jsonResult.toString());
	        String jsonStr = resultString;
	        response.setContentType("text/xml; charset=UTF-8");
	        PrintWriter out = response.getWriter();
	        out.println(jsonStr);
	        out.flush();
	        out.close();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	
	public static void CommandInput(String Command, String check) {
		StringBuffer output = new StringBuffer();
		Process p;
		try {
			p = Runtime.getRuntime().exec(Command);
			p.waitFor();
			BufferedReader reader =
	                        new BufferedReader(new InputStreamReader(p.getInputStream()));
	        if(check.equals("none")) {
				String line = "";      
				while ((line = reader.readLine())!= null) {
					output.append(line + "\n");
				}
				System.out.println(output);
	        }
	        else if(check.equals("ipParse")) {
				JSONParser parser = new JSONParser();
		        Object obj = parser.parse( reader.readLine() );
		        JSONObject jsonObj = (JSONObject) obj;
		        JSONArray jsontask = (JSONArray) jsonObj.get("tasks");
		        JSONObject jsonAddrs = (JSONObject) jsontask.get(0);
		        JSONArray jsonAddrs2 = (JSONArray) jsonAddrs.get("ipAddresses");
		        JSONObject jsonAddr = (JSONObject) jsonAddrs2.get(0);
		        chronosAddr = (String) jsonAddr.get("ipAddress");
	        }
	        else {
	        	JSONParser parser = new JSONParser();
		        Object obj = parser.parse( reader.readLine() );
		        JSONObject jsonObj = (JSONObject) obj;
		        JSONArray jsontask = (JSONArray) jsonObj.get("tasks");
		        JSONObject jsonports = (JSONObject) jsontask.get(0);
		        JSONArray jsonport = (JSONArray) jsonports.get("ports");
		        System.out.println("ih : " + jsonport.get(0));
		        chronosPort =  ((Long) jsonport.get(0)).intValue();
		        
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
		int Date = cal.get(cal.DATE)+1;
		return Year + "-" + Month + "-" + Date;
	}

}
