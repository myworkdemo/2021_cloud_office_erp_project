package com.cloud.office.erp.accesspermission.controller;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cloud.office.erp.accesspermission.service.AccessResourcePermissionService;
import com.cloud.office.erp.entity.AccessResourcePermission;
import com.cloud.office.erp.entity.Resource;
import com.cloud.office.erp.entity.User;
import com.cloud.office.erp.entity.UserRole;
import com.cloud.office.erp.resource.service.ResourceService;
import com.cloud.office.erp.user.service.UserService;
import com.cloud.office.erp.userrole.service.UserRoleService;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/access-permission")
public class AccessResourcePermissionController {
	
	@Autowired
	AccessResourcePermissionService accessResourcePermissionService;
	
	@Autowired
	ResourceService resourceService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	UserRoleService userRoleService;
	
	@PostMapping(value = "/addPermission")
	public AccessResourcePermission addPermission(@RequestBody Map<String, Object> [] permission)
	{
		System.out.println("# permission : "+permission);
		AccessResourcePermission accessResourcePermission = null;
		UserRole userRole = null;
		Resource resource = null;
		String resourceName = "", addStatus="N", modifyStatus="N", deleteStatus="N";
		
		try {
		
		for(Map<String, Object> map : permission)
		{
			Iterator<Entry<String, Object>> itr = map.entrySet().iterator();
			
			while(itr.hasNext()) {
				
				Map.Entry<String, Object> pair = itr.next();
				
				System.out.println(pair.getKey() +" : "+pair.getValue());
				
				if(pair.getKey().equals("userRoleId")){
					Long userRoleId = Long.parseLong(pair.getValue().toString());
				  userRole = userRoleService.getUserRoleById(userRoleId);	
				}
				
				if(pair.getKey().equals("resourceName")) {
					resourceName = pair.getValue().toString();
					resource = resourceService.getResourceByResourceName(resourceName);
				}
				
				if(pair.getKey().equals("permission"))
				{
					String[] arr = pair.getValue().toString().replaceAll("[{}]", "").replaceAll("[\\[\\]]", "").replaceAll(",", "").split(" ");
					AccessResourcePermission temp = null;
					
					if(resourceName != "" && userRole != null){
						
						Optional<AccessResourcePermission> optional = accessResourcePermissionService.findAccessResourcePermissionByUserRole(resourceName, userRole);
					
						if(optional.isPresent() && optional != null){
							temp = optional.get();
						}
					}
					
					for(String str : arr) {
						//System.out.println(str);
						String[] arr_temp = str.split("=");
						//System.out.println(arr_temp[0]+" : "+arr_temp[1]);
						
				if(!arr_temp[0].equals("Per"))
					{
						if(arr_temp[0].equals("ADD")) {
						    addStatus = (arr_temp[1].equals("true"))? "Y" : "N";
						}
						if(arr_temp[0].equals("MODIFY")) {
							modifyStatus = (arr_temp[1].equals("true"))? "Y" : "N";
						}
						if(arr_temp[0].equals("DELETE")) {
							deleteStatus = (arr_temp[1].equals("true"))? "Y" : "N";
						}
					}
				
				}// for loop end
					
					accessResourcePermission = new AccessResourcePermission(userRole, resource, resourceName, addStatus, modifyStatus, deleteStatus);
					if(temp != null) {
						accessResourcePermission.setAccessPermissionId(temp.getAccessPermissionId());
					}
					accessResourcePermissionService.addAccessResourcePermission(accessResourcePermission);
					
			}		
		}
			
		}
		
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return accessResourcePermission;
	}
	
	@GetMapping(value = "/getAllUserPermissionByUserId/{userId}")
	public List<AccessResourcePermission> getAllUserPermissionByUserId(@PathVariable("userId") Long userId)
	{
		Optional<User> user = userService.getUserById(userId);
		UserRole userRole = new UserRole();
		userRole.setUserRoleId(user.get().getUserRoleId().getUserRoleId());
		
		return accessResourcePermissionService.findUserPermissionsByUserRole(userRole);
	}
	
	@GetMapping(value = "/getAllUserPermissionByUserRoleId/{userRoleId}")
	public List<AccessResourcePermission> getAllUserPermissionByUserRoleId(@PathVariable("userRoleId") Long userRoleId)
	{
		UserRole userRole = new UserRole();
		userRole.setUserRoleId(userRoleId);
		
		return accessResourcePermissionService.findUserPermissionsByUserRole(userRole);
	}
	
	@GetMapping(value = "/getAllUserPermissionByUserRole/{userRole}")
	public List<AccessResourcePermission> getAllUserPermissionByUserRole(@PathVariable("userRole") String userRoleName)
	{
		UserRole userRole = userRoleService.getUserRoleByUserRoleName(userRoleName);
		
		return accessResourcePermissionService.findAccessResourcePermissionByUserRole(userRole);
	}

}
