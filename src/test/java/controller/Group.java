package controller;

import java.util.HashMap;
import java.util.Map;

public class Group {
	public Map<String, Object> resultMap(Map<String, String> param){
		Map<String, Object> map = new HashMap<String, Object>();
		
		String title = param.get("title");
		Integer groupId = Integer.valueOf(param.get("groupId"));
		map.put("groupId", groupId);
		
		return map;
	}
}
