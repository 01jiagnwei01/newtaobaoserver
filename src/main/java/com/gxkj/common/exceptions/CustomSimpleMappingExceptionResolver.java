package com.gxkj.common.exceptions;

import groovy.json.JsonOutput;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gxkj.taobaoservice.dto.EntityReturnData;

public class CustomSimpleMappingExceptionResolver extends SimpleMappingExceptionResolver  {
	
	protected ModelAndView doResolveException(HttpServletRequest request,  
            HttpServletResponse response, Object handler, Exception ex) { 
		
		 
		 String viewName = determineViewName(ex, request); 
		 if (viewName != null) {// JSP格式返回  
	            if (!(request.getHeader("accept").indexOf("application/json") > -1 || (request  
	                    .getHeader("X-Requested-With")!= null && request  
	                    .getHeader("X-Requested-With").indexOf("XMLHttpRequest") > -1))) {  
	                // 如果不是异步请求  
	                // Apply HTTP status code for error views, if specified.  
	                // Only apply it if we're processing a top-level request.  
	                Integer statusCode = determineStatusCode(request, viewName);  
	                if (statusCode != null) {  
	                    applyStatusCodeIfPossible(request, response, statusCode);  
	                }  
	                return getModelAndView(viewName, ex, request);  
	            } else {// JSON格式返回  
	                try { 
	                	ObjectMapper mapper = new ObjectMapper();
	                	EntityReturnData json = new EntityReturnData();
	                	response.setStatus(601);
	                	response.setContentType("text/javascript;charset=UTF-8"); 
	                	PrintWriter writer = response.getWriter(); 
	                   
	                    if (ex instanceof  BusinessException){
	                    	json.setMsg(  ex.getMessage());
	                    	json.setSiteFlag(((BusinessException) ex).getSiteFlag());
	    				}else if (ex instanceof  MaxUploadSizeExceededException){
	    					json.setMsg(  "上传文件大小超限了，"+ex.getMessage());
	    				}else if (ex instanceof  BindException){
	    					List<FieldError> filedErrors = ((BindException) ex).getFieldErrors();
	    					 
	    					List<Map<String,String>> listErrors = new ArrayList<Map<String,String>>();
	    					for(FieldError error : filedErrors){
	    						 Map<String,String> errMap = new HashMap<String,String>();
	    						 errMap.put("key", error.getField());
	    						 errMap.put("errortype",error.getCode());
	    						 errMap.put("errorMsg", error.getDefaultMessage());
	    						 listErrors.add(errMap);
	    						 
	    					}
	                    	json.setMsg( JsonOutput.toJson(listErrors));
	    				}else{
	    					json.setMsg(   "系统问题,请找技术人员解决");
	    				}
	                    if(json.getEntity() == null){
	                    	json.setEntity("");
	                    }
	                    writer.write(mapper.writeValueAsString(json));  
	                    writer.flush();  
	                } catch (IOException e) {  
	                    e.printStackTrace();  
	                }  
	                return null;  
	            }  
	        } else {  
	            return null;  
	        }  
	}


}
