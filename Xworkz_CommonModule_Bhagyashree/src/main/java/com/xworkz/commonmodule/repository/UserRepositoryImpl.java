package com.xworkz.commonmodule.repository;

import com.xworkz.commonmodule.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {
    @Autowired
    EntityManagerFactory emf;

    @Override
    public boolean save(UserEntity userEntity) {

        System.out.println("running in repository implementation");
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            em.persist(userEntity);
            et.commit();
        } catch (Exception e) {
            if (et.isActive()) {
                et.rollback();
            }
        } finally {
            em.close();
        }
        return true;
    }

    @Override
    public UserEntity getNameByEmailAndPassword(String email, String password) {
        EntityManager em =emf.createEntityManager();
        EntityTransaction et=em.getTransaction();
       String name=null;
       try{
            et.begin();
            Query query=em.createNamedQuery("getNameByEmailAndPassword");
            query.setParameter("byEmail",email).setParameter("byPassword",password);
            name=(String)query.getSingleResult();
            et.commit();
        }catch (Exception e){
           if(et.isActive()){
               et.rollback();
            }
        }finally {
            em.close();
        }
        return null;
    }

    @Override
    public List<UserEntity> getAll(String email, String password) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        List<UserEntity> list =em.createNamedQuery("getAll").setParameter("setEmail",email).setParameter("setPassword",password).getResultList();

        try{
            et.begin();
            et.commit();
        }catch (Exception e){
            if(et.isActive()){
                et.rollback();
            }
        }finally {
            em.close();
        }
        if(!list.isEmpty()){
            return list;
        }
        return null;
    }

    @Override
               public Long getCountByName(String name) {
                   EntityManager em = emf.createEntityManager();
                   EntityTransaction et = em.getTransaction();
                   Long count = (Long) em.createNamedQuery("countByName").setParameter("SetName", name).getSingleResult();

                   try {
                       et.begin();
                       et.commit();
                   } catch (Exception e) {
                       if (et.isActive()) {
                           et.rollback();
                       }
                   } finally {
                       em.close();
                   }
                   return count;
               }

               @Override
               public Long getCountByEmail(String email) {
                   EntityManager em = emf.createEntityManager();
                   Long count = 0L;
                   try {
                       count = (Long) em.createNamedQuery("countByEmail").setParameter("SetEmail", email).getSingleResult();
                   } catch (Exception e) {
                       e.printStackTrace();
                   } finally {
                       em.close();
                   }
                   return count;
               }

               @Override
               public Long getCountByPhone(long phone) {
                   EntityManager em = emf.createEntityManager();
                   Long count = 0L;
                   try {
                       count = (Long) em.createNamedQuery("countByPhone").setParameter("SetPhone", phone).getSingleResult();
                   } catch (Exception e) {
                       e.printStackTrace(); // Log the error
                   } finally {
                       em.close();
                   }
                   return count;
               }

               @Override
               public Long getCountByAlterEmail(String alterEmail) {
                   EntityManager em = emf.createEntityManager();
                   Long count = 0L;
                   try {
                       count = (Long) em.createNamedQuery("countByAlterEmail").setParameter("SetAlterEmail", alterEmail).getSingleResult();
                   } catch (Exception e) {
                       e.printStackTrace(); // Log the error
                   } finally {
                       em.close();
                   }
                   return count;
               }

               @Override
               public Long getCountByAlterPhone(long alterPhone) {
                   EntityManager em = emf.createEntityManager();
                   Long count = 0L;
                   try {
                       count = (Long) em.createNamedQuery("countByAlterPhone").setParameter("SetAlterPhone", alterPhone).getSingleResult();
                   } catch (Exception e) {
                       e.printStackTrace(); // Log the error
                   } finally {
                       em.close();
                   }
                   return count;
               }

    @Override
    public String updatePasswordByName(String newPassword, String name) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();

        try {
            et.begin();
            System.out.println("name==" + name);

            // Assuming "updatePasswordByName" is a named query in the UserEntity class
            Query query = em.createNamedQuery("updatePasswordByName");
            query.setParameter("setNewPassword", newPassword);
            query.setParameter("setLoginCount", 0);
            query.setParameter("nameBy", name);

            int value = query.executeUpdate(); // Call executeUpdate on the Query object
            et.commit();

            if (value > 0) {
                return "Password updated successfully";
            } else {
                return "Password is not updated";
            }
        } catch (Exception e) {
            if (et.isActive()) {
                et.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
        return "An error occurred while updating the password";
    }

    @Override
               public UserEntity findByName(String name) {
                   EntityManager em = emf.createEntityManager();
                   try{
                       String queryStr = "SELECT ue FROM UserEntity ue WHERE ue.name = :name";
                       Query query = em.createQuery(queryStr);
                       query.setParameter("name", name);

                       List<UserEntity> result = query.getResultList();
                       if (!result.isEmpty()) {
                           return result.get(0);
                       }
                   } catch (Exception e){
                       e.printStackTrace();
                   }finally {
                       em.close();
                   }
                   return null;
               }


           }




