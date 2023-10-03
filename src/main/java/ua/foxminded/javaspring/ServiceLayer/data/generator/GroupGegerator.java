package ua.foxminded.javaspring.ServiceLayer.data.generator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import ua.foxminded.javaspring.ServiceLayer.data.ReadResourcesFile;
import ua.foxminded.javaspring.ServiceLayer.data.resources.ResourcesFilesDatabaseData;
import ua.foxminded.javaspring.ServiceLayer.model.Group;

@Component
public class GroupGegerator {

    private ReadResourcesFile readFile;
    private ResourcesFilesDatabaseData resourcesFiles;

    private List<Group> groups = new ArrayList<>();

    public GroupGegerator(ReadResourcesFile readFile, ResourcesFilesDatabaseData resourcesFiles) {
        this.readFile = readFile;
        this.resourcesFiles = resourcesFiles;
    }

    public List<Group> generate() {
        List<String> groupNames = readFile.getData(resourcesFiles.getGroupsFile());

        Long groupID = 1L;

        for (String string : groupNames) {
            groups.add(new Group(groupID, string));
            groupID++;
        }
        return groups;
    }
}
