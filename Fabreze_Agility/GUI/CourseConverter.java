package Fabreze.bots.Fabreze_Agility.GUI;

import javafx.util.StringConverter;

public class CourseConverter extends StringConverter<Course> {

    @Override
    public String toString(Course course) {
        return course.getname();
    }

    @Override
    public Course fromString(String name) {
        return null;
    }
}
