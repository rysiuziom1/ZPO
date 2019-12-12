import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PersonTest {
    Person person1;
    Person person2;

    @Before
    public void setPersons() {
        person1 = new Person();
        person2 = new Person();
    }

    @Test
    public void testEqualsDefaultPerson() {
        assertTrue(person1.equals(person2));
    }

    @Test
    public void testEqualsDifferentPersons() {
        person2 = new Person("Kevin");
        assertFalse(person1.equals(person2));
    }

    @Test
    public void testEqualsDifferentNumbersOnly() {
        person1 = new Person("Jacek", 'm', "1337", 22);
        person2.setName("Jacek");
        person2.setSex('m');
        person2.setNumber("222");
        person2.setAge(22);
        assertTrue(person1.equals(person2));
    }

    @Test
    public void testEqualsWithAnnotationDefaultPersons() {
        assertTrue(person1.equalsWithAnnotation(person2));
    }

    @Test
    public void testEqualsWithAnnotationDifferentPersons() {
        person2 = new Person("Kevin");
        assertFalse(person1.equalsWithAnnotation(person2));
    }

    @Test
    public void testEqualsWithAnnotationDifferentNumbersOnly() {
        person1 = new Person("Jacek", 'm', "1337", 22);
        person2.setName("Jacek");
        person2.setSex('m');
        person2.setNumber("222");
        person2.setAge(22);
        assertTrue(person1.equalsWithAnnotation(person2));
    }
}