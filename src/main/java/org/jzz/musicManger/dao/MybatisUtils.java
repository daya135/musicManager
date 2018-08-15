package org.jzz.musicManger.dao;

import java.io.InputStream;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* 单例模式，解决线程冲突问题 */
public class MybatisUtils {
	private static Logger logger = LoggerFactory.getLogger(MybatisUtils.class);
	
	private static SqlSessionFactory factory = createFactory();
	
	private static SqlSessionFactory createFactory(){
        
        //加载mybatis 的配置文件（它也加载关联的映射文件）
        InputStream is = MybatisUtils.class.getClassLoader().getResourceAsStream("mybatis.xml");
        
        //构建sqlSession 的工厂
        SqlSessionFactory factory=new SqlSessionFactoryBuilder().build(is);
        
        return factory;
    }
	
	public static SqlSessionFactory getFactory() {
		return factory;
	}
	
	public static void main(String args[]) {
		SqlSessionFactory factory = MybatisUtils.getFactory();
		System.out.println(factory);
		
	}
	
}
