package com.example.lostindublin.dao;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import com.example.lostindublin.model.Item;
import com.example.lostindublin.model.Report;

public class ReportDAO {
	
	//1. Get the EntityManagerFactory 
	//2. Get the EntityManager from the Factory when we need to 
	 //talk to the DB
	//3. Call one of the operations on the EntityManager i.e., persist

	protected static EntityManagerFactory emf = 
		Persistence.createEntityManagerFactory("mydb");


	public ReportDAO() {
		// TODO Auto-generated constructor stub
	}

	public void persist(Report report) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(report);
		em.getTransaction().commit();
		em.close();

	}
	
	
	public void remove(Report report) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.remove(em.merge(report));
		em.getTransaction().commit();
		em.close();
	}
	
	
	public Report merge(Report report) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Report updateReport = em.merge(report);
		em.getTransaction().commit();
		em.close();
		return updateReport;	
	}
	
	
	public List<Report> getAllReports(){
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		List<Report> reports = new ArrayList<Report>();
		reports = em.createQuery("from Report").getResultList();
		em.getTransaction().commit();
		em.close();
		return reports;
		
	}
	
	
	public Report getReportById(int reportId) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Report rId = em.createQuery("SELECT p FROM Report p WHERE p.reportId = :reportId", Report.class)
				.setParameter("reportId", reportId)
				.getSingleResult();
		em.getTransaction().commit();
		em.close();
		return rId;
	}
	
	
	public List<Report> getReportsByItem(int itemId) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		List<Report> reports = em.createQuery("SELECT r FROM Report r WHERE r.item.itemId = :itemId", Report.class)
				.setParameter("itemId", itemId)
				.getResultList();
		em.getTransaction().commit();
		em.close();
		return reports;
	}
	
	
}
