package com.k4meitu.pic.controller;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.k4meitu.pic.constant.ApiConstant;
import com.k4meitu.pic.handler.ApiHandler;


@Controller
public class ApiController {
	
	@RequestMapping(value="/api")
	@ResponseBody
	public Map<String, Object> getGroupList(HttpServletRequest req, HttpServletResponse resp) throws Exception{
		URLEncoder.encode(req.getRequestURI(), "UTF-8");
		Map<String, Object> modelMap = new HashMap<String, Object>();
		
		Map<String, String> param = ApiHandler.getParameter(req.getParameterMap());
		
		if (ApiHandler.isParamCorrect(param)) {
			Map<String, Object> resultMap = ApiHandler.invokeObjectMethod(ApiHandler.findControllerByApiMethod(param.get("method")), param);
			if (resultMap.get(ApiConstant.ErrorMsg) == null) {
				modelMap.put("success", true);
				modelMap.put("res", resultMap);
				return modelMap;
			}else{
				modelMap.put("success", false);
				modelMap.put(ApiConstant.ErrorMsg, resultMap.get(ApiConstant.ErrorMsg));
				return modelMap;
			}
		}else{
			modelMap.put("success", false);
			modelMap.put(ApiConstant.ErrorMsg, param.get(ApiConstant.ErrorMsg));
			return modelMap;
		}
	}
}
