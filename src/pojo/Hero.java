package pojo;

import java.io.Serializable;

public class Hero implements Serializable {
    private Integer id;
    private String name;
    private Integer age;
    private Integer hp;
    private Integer mp;

    public Hero() {
    }

    public Hero(String name, Integer age, Integer hp, Integer mp) {
        this.name = name;
        this.age = age;
        this.hp = hp;
        this.mp = mp;
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

    public Integer getHp() {
        return hp;
    }

    public void setHp(Integer hp) {
        this.hp = hp;
    }

    public Integer getMp() {
        return mp;
    }

    public void setMp(Integer mp) {
        this.mp = mp;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Hero{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", hp=" + hp +
                ", mp=" + mp +
                '}';
    }
}
