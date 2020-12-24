package mpc.objTest;

import java.util.HashMap;
import java.util.Map;

/**
 * hashCode和equals测试
 *
 * @author: 孟鹏程
 * @date: 2020/12/24 10:26
 */
public class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        if (age != person.age) return false;
        return name != null ? name.equals(person.name) : person.name == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + age;
        return result;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public static void main(String[] args) {
        Person p = new Person("111", 12);
        System.out.println(p.hashCode());
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put(p, 1);
        System.out.println(map.get(p));
        p.setAge(13);
        System.out.println(map.get(p));
    }
}
