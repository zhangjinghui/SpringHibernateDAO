package com.zjh.dao;
/*
 * 1. Spring4弃用HibernateTemplate
 * 2. 依赖注入关系:dataSouce->sessionFactory->DAO组件
 * 3. DAO组件需要添加成员变量sessionFactory及其getter、setter方法
 * 4. this.getSessionFactory().getCurrentSession()获取session，调用相关操作
 */
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import com.zjh.po.User;

public class UserDAOSpring4Impl implements IUserDAO {
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext1.xml");
		UserDAOSpring4Impl hudao = ctx.getBean("hudao", UserDAOSpring4Impl.class);
		hudao.addUser();
	}
	@Override
	public void addUser() {
		// TODO Auto-generated method stub
		User user=new User();
		user.setUsername("111");
		user.setPassword("111");
		this.getSessionFactory().getCurrentSession().save(user);
		
	}

	@Override
	public void delUser() {
		// TODO Auto-generated method stub
		User user=(User)this.getSessionFactory().getCurrentSession().get(User.class, 6);
		this.getSessionFactory().getCurrentSession().delete(user);
	}

	@Override
	public void updateUser() {
		// TODO Auto-generated method stub
		User user=(User)this.getSessionFactory().getCurrentSession().get(User.class, 7);
		user.setUsername("修改");
		user.setPassword("完毕");
		this.getSessionFactory().getCurrentSession().saveOrUpdate(user);
	}

	@Override
	public void queryById() {
		// TODO Auto-generated method stub
		User user=(User)this.getSessionFactory().getCurrentSession().get(User.class, 7);
		System.out.println(user.getUsername()+"---"+user.getPassword());
	}

	@Override
	public void queryAll() {
		// TODO Auto-generated method stub
		List<User> list=this.getSessionFactory().getCurrentSession().createQuery("from User").list();
		for(User user:list){
			System.out.println(user.getUsername()+"---"+user.getPassword());
		}
	}

}
