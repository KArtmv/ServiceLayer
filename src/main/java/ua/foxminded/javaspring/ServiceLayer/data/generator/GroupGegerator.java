package ua.foxminded.javaspring.ServiceLayer.data.generator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import ua.foxminded.javaspring.ServiceLayer.config.ResourcesFilesDatabaseData;
import ua.foxminded.javaspring.ServiceLayer.data.ReadDataFile;
import ua.foxminded.javaspring.ServiceLayer.model.Group;

@Component
public class GroupGegerator {

	private ReadDataFile dataFile;

	private List<Group> groups = new ArrayList<>();

	public GroupGegerator(ReadDataFile dataFile) {
		this.dataFile = dataFile;
	}

	public List<Group> createGroups() {
		ResourcesFilesDatabaseData resourcesFiles = new ResourcesFilesDatabaseData();
		List<String> groupNames = dataFile.scan(resourcesFiles.getGroupsFile());

		Long groupID = 1L;

		for (String string : groupNames) {
			groups.add(new Group(groupID, string));
			groupID++;
		}
		return groups;
	}
}
