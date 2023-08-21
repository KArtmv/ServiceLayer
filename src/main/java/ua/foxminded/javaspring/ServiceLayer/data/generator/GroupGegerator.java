package ua.foxminded.javaspring.ServiceLayer.data.generator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import ua.foxminded.javaspring.ServiceLayer.data.ReadDataFile;
import ua.foxminded.javaspring.ServiceLayer.model.Group;

@Component
public class GroupGegerator {

	private ReadDataFile dataFile;
	private String filePath;

	private List<Group> groups = new ArrayList<>();

	public GroupGegerator(ReadDataFile dataFile, @Qualifier("groupFilePath") String filePath) {
		this.dataFile = dataFile;
		this.filePath = filePath;
	}

	public List<Group> createGroups() {
		List<String> groupNames = dataFile.scan(filePath);

		Long groupID = 1L;

		for (String string : groupNames) {
			groups.add(new Group(groupID, string));
			groupID++;
		}
		return groups;
	}
}
