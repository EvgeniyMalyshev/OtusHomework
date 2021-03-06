package orm.main.entity;


import orm.main.annotation.Id;

public class User {

    @Id
    private final long id;
    private final String name;
    private final int age;

    public User(long id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString(){
        return "UserRow: [" +
                "id=" + id + " | " +
                "name=" + (name == null ? "null" : name) + " | " +
                "age=" + age + "]";
    }

}
