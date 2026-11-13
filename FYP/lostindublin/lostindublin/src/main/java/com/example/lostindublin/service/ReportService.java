package com.example.lostindublin.service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;



import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.example.lostindublin.dao.ItemDAO;
import com.example.lostindublin.dao.ReportDAO;
import com.example.lostindublin.model.Item;
import com.example.lostindublin.model.Report;

@RestController
@RequestMapping("/ReportService")
public class ReportService {

	public ReportService() {
		// TODO Auto-generated constructor stub
	}

	
	@PostMapping("/createReport")
	public RedirectView creatReport(
			@RequestParam("itemId")int itemId,
			@RequestParam("reportType")String reportType,
			@RequestParam("reportedAt")String reportedAt,
			@RequestParam("reportNote")String reportNote) throws URISyntaxException {
		
		//URI uriItemLostAlr = new URI("http://localhost:8080/FYP/reports.jsp");
		//URI uriReported = new URI("http://localhost:8080/FYP/home.jsp");

		
		ItemDAO itemDao = new ItemDAO();
		ReportDAO reportDao = new ReportDAO();
		Item item = itemDao.getItemById(itemId);
		
		if(reportType.equals("LOST") && item.getItemStatus().equals("LOST")) {
			return new RedirectView("/reports.jsp");				
		}
		
		
		
		
		Report report = new Report(
				item,
				reportType,
				reportedAt,
				reportNote);
		
		reportDao.persist(report);
		item.setItemStatus(reportType);
		itemDao.merge(item);
		return new RedirectView("/home.jsp");					
		
	}
	
	@GetMapping("/item/{itemId}")
	public List<Report> getReportsById(
			@PathVariable int itemId){
		ReportDAO dao = new ReportDAO();
		
		return dao.getReportsByItem(itemId);
	}
	
	
	@PostMapping("/update")
	public String updateReport(Report report) {
		ReportDAO dao = new ReportDAO();
		dao.merge(report);
		
		return "report updated";
	}
	
	
	@GetMapping("/delete/{reportId}")
	public String deleteReportById(
			@PathVariable int reportId) {
		ReportDAO dao = new ReportDAO();
		Report report = dao.getReportById(reportId);
		dao.remove(report);
		
		return "report deleted";
	}
	
}
