package com.yu.dao;

import com.yu.domain.Customer;
import com.yu.utils.HibernateUtils;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class CustomerDao {
    @Test
    /**
    *@Author: yu
    * @Description:
    * @CreateDate: 2018/3/30/030 12:05
    *添加
    */
    public void demo(){
        //加载配置文件
        Configuration configure = new Configuration().configure();
        //创建一个sessionFactory
        SessionFactory sessionFactory=configure.buildSessionFactory();
        // 3.创建Session对象.Session对象 类似Connection.
        Session session = sessionFactory.openSession();
        // 4.开启事务:
        Transaction transaction = session.beginTransaction();
        // 5.执行相关操作
        Customer cst=new Customer();
        cst.setCust_name("张三");
        session.save(cst);
        //6.提交事物
        transaction.commit();
        //关闭事物
        session.close();
    }

    @Test
    /**
    *@Author: yu
    * @Description:
    * @CreateDate: 2018/3/30/030 12:05'
    *添加
    */
    public  void save(){
        Session session = HibernateUtils.getSession();
        Transaction tr = session.beginTransaction();
        Customer c=new Customer();
        c.setCust_name("赵六");
        c.setCust_phone("1852121254");
        session.save(c);
        tr.commit();
        session.close();
    }
    /**
    *@Author: yu
    * @Description:
    * @CreateDate: 2018/3/30/030 12:06
    *根据ID获取
    */
    @Test
    public void getId(){
        Session session = HibernateUtils.getSession();

        Customer customer =session.get(Customer.class,1L);

        System.out.println(customer);
        session.close();
    }
    /**
    *@Author: yu
    * @Description:
    * @CreateDate: 2018/3/30/030 12:17
    *修改
    */
    @Test
    public void update(){
        Session session = HibernateUtils.getSession();
        Transaction transaction = session.beginTransaction();
        Customer customer = session.get(Customer.class, 1L);
        customer.setCust_phone("1582124521");
        session.update(customer);
        transaction.commit();
        session.close();
    }
    /**
    *@Author: yu
    * @Description:
    * @CreateDate: 2018/3/30/030 13:23
    *删除
    */
    @Test
    public void delete(){
        Session session = HibernateUtils.getSession();
        Transaction transaction = session.beginTransaction();
        Customer c=new Customer();
        c.setCust_id(1L);
        session.delete(c);
        transaction.commit();
        session.close();

    }
    /**
    *@Author: yu
    * @Description:
    * @CreateDate: 2018/3/30/030 13:26
    *基本查询
    */
    @Test
    public void query(){
        Session session = HibernateUtils.getSession();
        //基本查询
        /*SQLQuery sqlQuery = session.createSQLQuery("select * from cst_customer");
        List<Object[]> list = sqlQuery.list();
        for(Object[] objs : list){
            System.out.println(Arrays.toString(objs));
        }*/
        //封装到对象
        SQLQuery sqlQuery = session.createSQLQuery("select * from cst_customer");
            sqlQuery.addEntity(Customer.class);
        List<Customer> list = sqlQuery.list();
        for (Customer c:list) {
            System.out.println(c);
        }
    }
}
