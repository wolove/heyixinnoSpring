package hyx.repository.mongo.entity;

/**
 * @author hyx(610302) on 2017/7/6 0006.
 */
public class Heros {
    private String id;
    private String name;
    private Integer age;
    private String q;
    private String role;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getQ() {
        return q;
    }

    public void setQ(String q) {
        this.q = q;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return String.format("Heros[id=%s,name=%s,age=%d,q=%s,role=%s]", id, name, age, q, role);
    }
}
