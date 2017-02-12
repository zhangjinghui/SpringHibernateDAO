package com.zjh.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import com.zjh.po.User;

public class UserDAOSpring3Impl extends HibernateDaoSupport implements IUserDAO {
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		UserDAOSpring3Impl hudao = ctx.getBean("hudao", UserDAOSpring3Impl.class);
		hudao.addUser();
	}
	@Override
	public void addUser() {
		// TODO Auto-generated method stub
		User user=new User();
		user.setUsername("111");
		user.setPassword("111");
		this.getHibernateTemplate().save(user);
		
	}

	@Override
	public void delUser() {
		// TODO Auto-generated method stub
		User user=this.getHibernateTemplate().get(User.class, 6);
		this.getHibernateTemplate().delete(user);
	}

	@Override
	public void updateUser() {
		// TODO Auto-generated method stub
		User user=this.getHibernateTemplate().get(User.class, 7);
		user.setUsername("修改");
		user.setPassword("完毕");
		this.getHibernateTemplate().saveOrUpdate(user);
	}

	@Override
	public void queryById() {
		// TODO Auto-generated method stub
		User user=this.getHibernateTemplate().get(User.class, 7);
		System.out.println(user.getUsername()+"---"+user.getPassword());
	}

	@Override
	public void queryAll() {
		// TODO Auto-generated method stub
		List<User> list=this.getHibernateTemplate().execute(new HibernateCallback<List<User>>(){
			@Override
			public List<User> doInHibernate(Session session)
					throws HibernateException {
				// TODO Auto-generated method stub
				return session.createQuery("from User").list();
			}
		});
		for(User user:list){
			System.out.println(user.getUsername()+"---"+user.getPassword());
		}
	}

}
