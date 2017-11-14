package com.pro.project;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.ProgressListener;

import net.sf.json.JSONObject;

public class AjaxProgressListener implements ProgressListener {
 
	private long num100Ks;
    private HttpSession session;
    private String uploadId;
    private String Read_contentLength;
    
    public AjaxProgressListener(HttpServletRequest request) {
		session = request.getSession();
		String get_uploadId = request.getParameter("uploadId");
        Read_contentLength = request.getHeader("Content-Length");
        uploadId = get_uploadId;
    } 
    public void setSession(HttpSession session){
        this.session = session;
    }

    public void setUploadId(String uploadId) {
    	this.uploadId = uploadId;
    }
 
    public void update(long bytesRead, long contentLength, int item) {
        JSONObject progressMap = new JSONObject();
       
        long nowNum100Ks = bytesRead / 5000000;
        if(nowNum100Ks > num100Ks) {
        	num100Ks = nowNum100Ks;
        	progressMap.put("bytesRead", bytesRead);
            progressMap.put("contentLength", Read_contentLength);
            session.setAttribute(uploadId, progressMap);
        }
    }
}

