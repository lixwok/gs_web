package cn.com.wonder.xlab.entity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by Administrator on 2015/5/7.
 */
public class Main {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        System.out.println("entityManagerFactory = " + entityManagerFactory);
        System.out.println("entityManager = " + entityManager);
        entityManager.close();
        entityManagerFactory.close();


    }
}
