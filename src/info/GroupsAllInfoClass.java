package info;

public class GroupsAllInfoClass {
    private int idGroup;
    private String nameGroup;
    private int year;
    private int studentCount;
    private int avgMark;

    public GroupsAllInfoClass(int idGroup, String nameGroup, int year, int studentCount, int avgMark) {
        this.idGroup = idGroup;
        this.nameGroup = nameGroup;
        this.year = year;
        this.studentCount = studentCount;
        this.avgMark = avgMark;
    }

    public int getIdGroup() {
        return idGroup;
    }

    public void setIdGroup(int idGroup) {
        this.idGroup = idGroup;
    }

    public String getNameGroup() {
        return nameGroup;
    }

    public void setNameGroup(String nameGroup) {
        this.nameGroup = nameGroup;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getStudentCount() {
        return studentCount;
    }

    public void setStudentCount(int studentCount) {
        this.studentCount = studentCount;
    }

    public int getAvgMark() {
        return avgMark;
    }

    public void setAvgMark(int avgMark) {
        this.avgMark = avgMark;
    }
}
