package com.cloud.office.erp;

import java.io.FileReader;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.cloud.office.erp.accesspermission.service.AccessResourcePermissionService;
import com.cloud.office.erp.entity.AccessResourcePermission;
import com.cloud.office.erp.entity.Resource;
import com.cloud.office.erp.entity.StateAndCities;
import com.cloud.office.erp.entity.UserRole;
import com.cloud.office.erp.memberdetails.service.MemberDetailsService;
import com.cloud.office.erp.resource.service.ResourceService;
import com.cloud.office.erp.stateandcities.service.StateAndCitiesService;
import com.cloud.office.erp.userrole.service.UserRoleService;

@Component
public class CommandLineAppStartupRunner implements CommandLineRunner {

	@Autowired
	StateAndCitiesService stateAndCitiesService;

	@Autowired
	ResourceService resourceService;

	@Autowired
	UserRoleService userRoleService;

	@Autowired
	AccessResourcePermissionService accessResourcePermissionService;

	@Autowired
	MemberDetailsService memberDetailsService;

	@Override
	public void run(String... args) throws Exception {

		System.out.println("## CommandLineRunner : run() calling...");

		// stateAndCitiesService.truncateStateAndCitiesTable();
		loadStateAndCities();
		// accessResourcePermissionService.truncateAccessResourcePermissionTable();
		loadAllUserRoleWithDefaultPermissions();
	}

	private void loadStateAndCities() {
		System.out.println("## CommandLineRunner : loadStateAndCities() calling...");

		FileReader cities_reader = null;
		FileReader state_reader = null;
		StateAndCities stateAndCities = null;

		try {
			cities_reader = new FileReader("src/main/resources/cities.properties");
			state_reader = new FileReader("src/main/resources/state.properties");

			Properties cities_prop = new Properties();
			cities_prop.load(cities_reader);

			Properties state_prop = new Properties();
			state_prop.load(state_reader);

			Iterator<Entry<Object, Object>> cities_itr = cities_prop.entrySet().iterator();
			Iterator<Entry<Object, Object>> state_itr = state_prop.entrySet().iterator();

			while (cities_itr.hasNext()) {

				String city = cities_itr.next().getValue().toString();
				city = city.replace("=", " ");
				city = city.trim();

				String state = state_itr.next().getValue().toString();
				state = state.replace("=", " ");
				state = state.trim();

				List<StateAndCities> stateAndCitiesList = stateAndCitiesService.getAllStateAndCities();

				if (!stateAndCitiesList.contains(state) && !stateAndCitiesList.contains(city)) {
					// System.out.println(key+" : "+value);
					stateAndCities = new StateAndCities(city, state);
					stateAndCitiesService.addStateAndCities(stateAndCities);
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void loadAllUserRoleWithDefaultPermissions() {

		System.out.println("## CommandLineRunner : loadAllUserRoleWithDefaultPermissions() calling...");

		// load default user role
		UserRole userRole_temp = userRoleService.loadDefaultUserRole();

		// load default resource's
		resourceService.loadDefaultResource();

		// load default member details
		memberDetailsService.loadDefaultMemberDetails(userRole_temp);

		List<Resource> resourceList = resourceService.getAllResource();
		List<UserRole> userRoleList = userRoleService.getAllUserRole();
		AccessResourcePermission accessResourcePermission = null;

		String addPermission = "", modifyPermission = "", deletePermission = "";

		for (Resource resource : resourceList) {

			for (UserRole userRole : userRoleList) {

				accessResourcePermission = accessResourcePermissionService.findUserByUserIdAndResourceName(userRole,
						resource.getResourceName());

				if (accessResourcePermission == null) {

					addPermission = modifyPermission = deletePermission = (userRole.getUserRole()
							.equalsIgnoreCase("Admin")) ? "Y" : "N";

					accessResourcePermission = new AccessResourcePermission(userRole, resource,
							resource.getResourceName(), addPermission, modifyPermission, deletePermission);
					accessResourcePermissionService.addAccessResourcePermission(accessResourcePermission);
				}

			}

		}

	}

}
