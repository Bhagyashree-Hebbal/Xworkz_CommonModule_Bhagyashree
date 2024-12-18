package com.xworkz.commonmodule.repository;

import com.xworkz.commonmodule.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.*;

@Repository
public class UserRepositoryImpl implements UserRepository{
    @Autowired
    EntityManagerFactory emf;

    @Override
    public boolean save(UserEntity userEntity) {

        System.out.println("running in repository implementation");
        EntityManager em =emf.createEntityManager();
        EntityTransaction et=em.getTransaction();
        try{
            et.begin();
            em.persist(userEntity);
            et.commit();
        }catch (Exception e){
            if(et.isActive()){
                et.rollback();
            }
        }finally {
            em.close();
        }
        return true;
    }

//    @Override
//    public String getNameByEmailAndPassword(String email, String password) {
//        EntityManager em =emf.createEntityManager();
//        EntityTransaction et=em.getTransaction();
//        String name=null;
//        try{
//            et.begin();
//            Query query=em.createNamedQuery("getNameByEmailAndPassword");
//            query.setParameter("byEmail",email).setParameter("byPassword",password);
//            name=(String)query.getSingleResult();
//            et.commit();
//        }catch (Exception e){
//            if(et.isActive()){
//                et.rollback();
//            }
//        }finally {
//            em.close();
//        }
//        return name;
//    }
}
