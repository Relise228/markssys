package info;

public class StudentsFullInfoClass {
    private int idStudent;
    private String nameStudent;
    private int markStudent;

    public StudentsFullInfoClass(int idStudent, String nameStudent, int markStudent) {
        this.idStudent = idStudent;
        this.nameStudent = nameStudent;
        this.markStudent = markStudent;
    }

    public int getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(int idStudent) {
        this.idStudent = idStudent;
    }

    public String getNameStudent() {
        return nameStudent;
    }

    public void setNameStudent(String nameStudent) {
        this.nameStudent = nameStudent;
    }

    public int getMarkStudent() {
        return markStudent;
    }

    public void setMarkStudent(int markStudent) {
        this.markStudent = markStudent;
    }
}
