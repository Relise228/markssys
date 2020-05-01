package info;

public class GroupsClass {

    private String groups;
    private int id;

    public GroupsClass(String groups, int id) {
        this.groups = groups;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGroups() {
        return groups;
    }

    public void setGroups(String groups) {
        this.groups = groups;
    }
}
