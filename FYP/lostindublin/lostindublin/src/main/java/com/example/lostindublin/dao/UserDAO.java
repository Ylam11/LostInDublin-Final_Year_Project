package com.example.lostindublin.dao;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import com.example.lostindublin.model.User;


public class UserDAO {
	

	protected static EntityManagerFactory emf = 
			Persistence.createEntityManagerFactory("mydb");


	public UserDAO() {
		// TODO Auto-generated constructor stub
	}
	
	
	public void persist(User user) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(user);
		em.getTransaction().commit();
		em.close();

	}
	
	
	public void remove(User user) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.remove(em.merge(user));
		em.getTransaction().commit();
		em.close();
	}
	
	
	public User merge(User user) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		User updatedMember = em.merge(user);
		em.getTransaction().commit();
		em.close();
		return updatedMember;	
	}
	
	
	public List<User> getAllUsers(){
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		List<User> users = new ArrayList<User>();
		users = em.createQuery("from User").getResultList();
		em.getTransaction().commit();
		em.close();
		return users;
		
	}
	
	public User getUserById(int userId) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		User uId = em.createQuery("SELECT p FROM User p WHERE p.userId = :userId", User.class)
				.setParameter("userId", userId)
				.getSingleResult();
		em.getTransaction().commit();
		em.close();
		return uId;
	}
	
	
	public User getUserByEmail(String userEmail) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		User uEmail = em.createQuery("SELECT u FROM User u WHERE u.userEmail = :userEmail", User.class)
				.setParameter("userEmail", userEmail)
				.getSingleResult();
		em.getTransaction().commit();
		em.close();
		return uEmail;
	}
	
	
	
	


}
