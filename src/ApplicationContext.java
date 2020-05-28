import annotation.Autowired;
import annotation.Bean;
import annotation.Mapper;
import annotation.Service;
import dao.HeroDao;
import pojo.Hero;
import service.HeroService;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * 应用上下文
 */

public class ApplicationContext {

    private static Map<Class,Object> beanFactory=new HashMap<>();//用哈希表存储bean对象,ioc容器的核心

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IOException {
        InitByAnnotation();    //将bean注入进IOC容器
        DI();  //依赖注入
        HeroService heroService =(HeroService) beanFactory.get(HeroService.class);
        heroService.findAll();
    }

    public static void init() throws IOException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        InputStream resourceAsStream = Hero.class.getResourceAsStream("/bean.properties");
        Properties properties=new Properties();
        properties.load(resourceAsStream);   //将配置文件读取进properties中
        for (Object o : properties.keySet()) {
            Object bean=Class.forName(properties.getProperty(o.toString())).newInstance();
            beanFactory.put(Class.forName(o.toString()),bean);
        }
    }

    public static void InitByAnnotation() throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        String file = ApplicationContext.class.getClassLoader().getResource("").getFile();//获得该类文件编译后的路径
        File f=new File(file);
        if(f.exists()==false)
        {
            return;   //子目录下没有文件
        }else{
            findAndRegister(f,file.length());   //递归查找其中的bean注解并将其注入到ioc容器
        }
    }

    public static void findAndRegister(File f,Integer pathLength) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        File[] files = f.listFiles();
        if(files==null||files.length==0)
        {
            return;
        }  //判空
        for (File file : files) {
            if(file.isDirectory()&&!file.getAbsolutePath().contains("annotation"))   //文件为文件夹
            {
                findAndRegister(file,pathLength);
            }else if(file.getAbsolutePath().endsWith(".class")){       //文件为类文件
                String classpath = file.getAbsolutePath()
                        .substring(pathLength-1)
                        .replace(".class","")
                        .replaceAll("\\\\", ".");//将文件路径改为类对象可反射的类路径
                Class aClass = Class.forName(classpath);
                if(aClass.getAnnotation(Bean.class)!=null||aClass.getAnnotation(Service.class)!=null||aClass.getAnnotation(Mapper.class)!=null)
                {
                    if(aClass.getInterfaces().length>0)   //该类是一个接口的实现类
                    {
                        beanFactory.put(aClass.getInterfaces()[0],aClass.newInstance());   //实例化时注册的是接口
                    }else{
                        beanFactory.put(aClass,aClass.newInstance());
                    }
                }
            }
        }
    }

    public static void DI() throws IllegalAccessException//将IOC容器中的值依赖注入进去
    {
        Set<Class> classes = beanFactory.keySet();
        for (Class aClass : classes) {   //遍历注册的所有bean
            Object obj=beanFactory.get(aClass);  //查看该接口所对应的bean
            Field[] fields =obj.getClass().getDeclaredFields();   //这个bean所有的字段 (注意这里用getDeclared获得所有的字段)
            for (Field field : fields) {
                System.out.println(field.getName());
                if(field.getAnnotation(Autowired.class)!=null)  //查看该字段上是否有Autowired注解
                {
                    System.out.println("给"+obj.getClass().getName()+"依赖注入"+field.getType());
                    field.setAccessible(true);    //给该对象暴力注入IOC容器上找到的bean
                    field.set(obj,beanFactory.get(field.getType()));   //通过getType获得当前总段的类名称
                    //从而通过该接口名称从IOC容器中寻找并注入
                }
            }
        }
    }
}
