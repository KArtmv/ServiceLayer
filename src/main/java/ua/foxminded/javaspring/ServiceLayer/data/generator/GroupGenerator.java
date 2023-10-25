package ua.foxminded.javaspring.ServiceLayer.data.generator;

import ua.foxminded.javaspring.ServiceLayer.data.ReadResourcesFile;
import ua.foxminded.javaspring.ServiceLayer.data.generator.sourceData.ResourcesFilesDatabaseData;
import ua.foxminded.javaspring.ServiceLayer.model.Group;

import java.util.ArrayList;
import java.util.List;

public class GroupGenerator {

    private ReadResourcesFile readFile;
    private ResourcesFilesDatabaseData resourcesFiles;

    private List<Group> groups = new ArrayList<>();

    public GroupGenerator(ReadResourcesFile readFile, ResourcesFilesDatabaseData resourcesFiles) {
        this.readFile = readFile;
        this.resourcesFiles = resourcesFiles;
    }

    public List<Group> generate() {
        List<String> groupNames = readFile.getData(resourcesFiles.getGroupsFilePath());

        Long groupID = 1L;

        for (String string : groupNames) {
            groups.add(new Group(groupID, string));
            groupID++;
        }
        return groups;
    }
}
