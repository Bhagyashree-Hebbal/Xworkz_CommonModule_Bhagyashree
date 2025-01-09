package com.xworkz.commonmodule.repository;

import com.xworkz.commonmodule.dto.UserDTO;
import com.xworkz.commonmodule.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.time.LocalDateTime;
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
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        String name = null;
        try {
            et.begin();
            Query query = em.createNamedQuery("getNameByEmailAndPassword");
            query.setParameter("byEmail", email).setParameter("byPassword", password);
            name = (String) query.getSingleResult();
            et.commit();
        } catch (Exception e) {
            if (et.isActive()) {
                et.rollback();
            }
        } finally {
            em.close();
        }
        return null;
    }

    @Override
    public List<UserEntity> getAll(String email, String password) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        List<UserEntity> list = em.createNamedQuery("getAll").setParameter("setEmail", email).setParameter("setPassword", password).getResultList();

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
        if (!list.isEmpty()) {
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
            System.out.println("name::" + name);
            System.out.println("newPassword::" + newPassword);

            // Assuming "updatePasswordByName" is a named query in the UserEntity class
            Query query = em.createNamedQuery("updatePasswordByName");
            query.setParameter("setNewPassword", newPassword);
            query.setParameter("setCount", 0);
            query.setParameter("nameBy", name);

            int value = query.executeUpdate(); // Call executeUpdate on the Query object
            et.commit();

            if (value > 0) {
                return "password updated successfully";
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
        return "password updated successfully";
    }

    @Override
    public UserEntity getEmail(String email) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        UserEntity entity = null;
        try {
            Query query = em.createNamedQuery("getAllByEmail");
            query.setParameter("byEmail", email);

            entity = (UserEntity) query.getSingleResult();
            System.out.println("Entity from repository " + entity.toString());

        } catch (Exception e) {
            e.getStackTrace();
            return null;
        } finally {
            em.close();
        }
        return entity;

    }

    @Override
    public boolean updatePasswordAndCount(String name, String confirmPassword, int count) {
        int loginValue = count + 1;
        System.out.println(confirmPassword);
        System.out.println(loginValue);
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();

        boolean isUpdated = false;
        try {
            et.begin();
            int value = em.createNamedQuery("updatePassword").setParameter("SetPassword", confirmPassword).setParameter("SetCount", loginValue)
                    .setParameter("ByName", name).executeUpdate();
            if (value > 0) {
                isUpdated = true;

                System.out.println("updated");
            } else {
                isUpdated = false;
                System.out.println("not Updated");
            }
            et.commit();
        } catch (Exception e) {
            if (et.isActive()) {
                et.rollback();
            }

        } finally {
            em.close();
        }
        if (isUpdated) {
            System.out.println("updated");
            return true;
        } else {
            System.out.println("not Updated");
            return false;
        }
    }

    @Override
    public void updateCount(String email, int count) {
        int result = count + 1;
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        int value;
        try {
            et.begin();
            value = em.createNamedQuery("updateCount").setParameter("setCount", result).setParameter("byEmail", email).executeUpdate();
            et.commit();
        } catch (Exception e) {
            if (et.isActive()) {
                et.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    @Override
    public boolean resetCount(String email, int count) {
        int result = 0;
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        int value = 0;
        try {
            et.begin();
            value = em.createNamedQuery("resetCount").setParameter("setCount", 0).setParameter("byEmail", email).executeUpdate();
            et.commit();
        } catch (Exception e) {
            if (et.isActive()) {
                et.rollback();
            }
        } finally {
            em.close();
        }
        if (value > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public UserEntity getByNamePassword(String name, String password) {
        EntityManager  em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        UserEntity entity = null;
        try {
            Query query = em.createNamedQuery("getByNamePassword");
            query.setParameter("setName", name).setParameter("setPassword",password);

            entity = (UserEntity) query.getSingleResult();
            System.out.println("Entity from repository " + entity.toString());

        } catch (Exception e) {
            e.getStackTrace();
            return null;
        } finally {
            em.close();
        }
        return entity;
    }

    @Override
    public UserEntity findByName(String name) {
        EntityManager em = emf.createEntityManager();
        try {
            String queryStr = "getNameByUpdateDetails";
            Query query = em.createQuery(queryStr);
            query.setParameter("name", name);

            List<UserEntity> result = query.getResultList();
            if (!result.isEmpty()) {
                return result.get(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return null;
    }


@Override
public boolean updateDetails(String name, UserDTO dto,String filePath) {


    System.out.println(dto.toString());
    System.out.println(name);
    EntityManager em=emf.createEntityManager();
    EntityTransaction et=em.getTransaction();

    boolean isUpdated=false;
    try{
        et.begin();
        int value = em.createNamedQuery("updatedDetailsByName")
                .setParameter("emailBy", dto.getEmail())
                .setParameter("phoneBy", dto.getPhone())
                .setParameter("alterEmailBy", dto.getAlterEmail())
                .setParameter("alterPhoneBy", dto.getAlterPhone())
                .setParameter("locationBy", dto.getLocation())
                .setParameter("updateName", dto.getName())
                .setParameter("updatedOn", LocalDateTime.now())
                .setParameter("filePathBy" ,filePath)
                .setParameter("nameBy", name)
                .executeUpdate();
        if(value>0)
        {
            isUpdated=true;

            System.out.println("updated");
        }
        else
        {
            isUpdated=false;
            System.out.println("not Updated");
        }
        et.commit();
    }
    catch(Exception e)
    {
        if(et.isActive())
        {
            et.rollback();
        }

    }
    finally {
        em.close();
        //` emf.close();
    }
    if(isUpdated)
    {
        System.out.println("updated");
        return true;
    }
  return false;
}
}