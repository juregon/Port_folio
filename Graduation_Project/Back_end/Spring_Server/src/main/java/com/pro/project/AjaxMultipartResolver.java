package com.pro.project;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileUpload;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

public class AjaxMultipartResolver extends CommonsMultipartResolver {
    
	private static ThreadLocal<AjaxProgressListener> progressListener = new ThreadLocal<AjaxProgressListener>();

    @Override
    protected FileUpload prepareFileUpload(String encoding) {
        FileUpload fileUpload = getFileUpload();
        FileUpload actualFileUpload = fileUpload;
        actualFileUpload = newFileUpload(getFileItemFactory());
        actualFileUpload.setSizeMax(fileUpload.getSizeMax());
        actualFileUpload.setHeaderEncoding(encoding);
        actualFileUpload.setProgressListener(progressListener.get());
        return actualFileUpload;
    }
 
    public MultipartHttpServletRequest resolveMultipart(HttpServletRequest request) throws MultipartException {
        try {
        	progressListener.set(new AjaxProgressListener(request)); 
            return super.resolveMultipart(request);               
        } catch(MaxUploadSizeExceededException ex) {
             throw new MultipartException(ex.getMessage());
        } catch (Exception ex) {
            throw new MultipartException(ex.getMessage());
        }
    }   
}
