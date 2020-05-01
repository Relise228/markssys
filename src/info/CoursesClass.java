package info;

public class CoursesClass {


        private String courses;
        private int idCourse;

    public CoursesClass(String courses, int idCourse) {
        this.courses = courses;
        this.idCourse = idCourse;
    }

    public int getIdCourse() {
        return idCourse;
    }

    public void setIdCourse(int idCourse) {
        this.idCourse = idCourse;
    }

    public String getCourses() {
            return courses;
        }

        public void setCourses(String courses) {
            this.courses = courses;
        }


}
