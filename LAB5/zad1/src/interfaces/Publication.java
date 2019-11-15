package interfaces;

import java.util.List;

public interface Publication {
    String getAuthor();
    String getTitle();
    int getPages();
    boolean contains(Class c);
    void addClass(Class c);
    List<Class> getListOfClasses();
}
