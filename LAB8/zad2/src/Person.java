import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Objects;

public class Person {
    private int age;
    private String name;
    private char sex;
    private String number;

    public Person() {
        this("Jon", 'm', "empty", -1);
    }

    public Person(String name) {
        this(name, 'm', "empty", -1);
    }

    public Person(String name, char sex) {
        this(name, sex, "empty", -1);
    }

    public Person(String name, char sex, String number) {
        this(name, sex, number, -1);
    }

    public Person(String name, char sex, String number, int age) {
        this.name = name;
        this.sex = sex;
        this.number = number;
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    public char getSex() {
        return sex;
    }

    @IgnoreEquals
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return getAge() == person.getAge() &&
                getSex() == person.getSex() &&
                getName().equals(person.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAge(), getName(), getSex());
    }

    public boolean equalsWithAnnotation(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Arrays.stream(person.getClass().getDeclaredMethods())
                .filter(e -> !e.isAnnotationPresent(IgnoreEquals.class))
                .filter(e -> e.getName().contains("get"))
                .allMatch(e -> {
                    try {
                        return e.invoke(this).equals(e.invoke(person));
                    } catch (IllegalAccessException | InvocationTargetException ex) {
                        ex.printStackTrace();
                    }
                    return false;
                });
    }
}
