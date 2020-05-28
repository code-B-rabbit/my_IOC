package dao.impl;

import annotation.Bean;
import annotation.Mapper;
import dao.HeroDao;
import pojo.Hero;

import java.util.ArrayList;
import java.util.List;

@Mapper
public class HeroDaoImpl implements HeroDao {

    @Override
    public void add(Hero hero) {
        System.out.println("增"+hero);
    }

    @Override
    public void update(Hero hero) {
        System.out.println("改"+hero);
    }

    @Override
    public void delete(Integer id) {
        System.out.println("删"+id);
    }

    @Override
    public Hero get(Integer id) {
        System.out.println("删"+id);
        return new Hero();
    }

    @Override
    public List<Hero> findAll() {
        System.out.println("查询全部");
        return new ArrayList<Hero>();
    }
}
