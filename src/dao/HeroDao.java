package dao;

import pojo.Hero;

import java.util.List;

public interface HeroDao {
    public void add(Hero hero);
    public void update(Hero hero);
    public void delete(Integer id);
    public Hero get(Integer id);
    public List<Hero> findAll();
}
