package service.impl;

import annotation.Autowired;
import annotation.Bean;
import annotation.Service;
import dao.HeroDao;
import dao.impl.HeroDaoImpl;
import pojo.Hero;
import service.HeroService;

import java.util.List;

@Service
public class HeroServiceImpl implements HeroService {

    @Autowired
    private HeroDao heroDao;

    @Override
    public void add(Hero hero) {
        heroDao.add(hero);
    }

    @Override
    public void update(Hero hero) {
        heroDao.update(hero);
    }

    @Override
    public void delete(Integer id) {
        heroDao.delete(id);
    }

    @Override
    public Hero get(Integer id) {
        return heroDao.get(id);
    }

    @Override
    public List<Hero> findAll() {
        return heroDao.findAll();
    }
}
