//package com.phamtrong.app.dao;
//
//import java.util.List;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import javax.persistence.Query;
//
//import org.springframework.stereotype.Repository;
//
//import com.phamtrong.app.entity.User;
//
//@Repository
//public class UserDao {
//
//	@PersistenceContext
//	EntityManager entityManager;
//	
//	
//	public User loadUserByUsername(final String username) {
////		StringBuilder st = new StringBuilder();
////		st.append("")
//	      Query query = entityManager.createQuery("SELECT * FROM users u WHERE u.username = :username");
//	      
//	      query.setParameter("username", username);
//	      
//	      List<User> users = query.getResultList();
//		
//	      if(users != null && users.size() > 0) {
//	    	  return users.get(0);
//	      }else {
//	    	  return null;
//	      }
//	}
//	
//	
//	public boolean checkLogin(User userForm) {
//	      Query query = entityManager.createQuery("SELECT * FROM users u WHERE u.username = :username and u.password= :password");
//	      query.setParameter("username", userForm.getUsername());
//	      query.setParameter("password", userForm.getPassword());
//		List<User> users = query.getResultList();
//		
//	      if(users != null && users.size() > 0) {
//	    	  return true;
//	      }else {
//	    	  return false;
//	      }
//	}
//}
//
