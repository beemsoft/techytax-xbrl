//package org.techytax.service;
//
//import java.util.List;
//
//import javax.ws.rs.Consumes;
//import javax.ws.rs.GET;
//import javax.ws.rs.Path;
//import javax.ws.rs.Produces;
//import javax.ws.rs.core.MediaType;
//
//import org.techytax.domain.Project;
//import org.techytax.jpa.dao.GenericDao;
//import org.techytax.jpa.entities.EntityManagerHelper;
//
//@Consumes(MediaType.APPLICATION_JSON)
//@Produces(MediaType.APPLICATION_JSON)
//public class MobileServiceImpl implements MobileService {
//
//	private GenericDao<Project> projectDao = new GenericDao<>(EntityManagerHelper.getEntityManager(), Project.class);
//	
//	@Override
//	@GET
//	@Path("/getProjects")
//	public ProjectList getProjects() {
//		List<Project> projects = null;
//		ProjectList projectList = new ProjectList();
//		try {
//			projects = projectDao.findAll(EntityManagerHelper.getUser());
//			projectList.projects = projects;
//		} catch (IllegalAccessException e) {
//			e.printStackTrace();
//		}
//		return projectList;
//	}
//
////	@Override
////	public List<BusinessCalendarEvent> getEvents() {
////		// TODO Auto-generated method stub
////		return null;
////	}
////
////	@Override
////	public void insertEvent(BusinessCalendarEvent businessCalendarEvent) {
////		// TODO Auto-generated method stub
////
////	}
////
////	@Override
////	public List<CostType> getCostTypes() {
////		// TODO Auto-generated method stub
////		return null;
////	}
////
////	@Override
////	public List<Cost> getCosts() {
////		// TODO Auto-generated method stub
////		return null;
////	}
////
////	@Override
////	public void insertCost(Cost cost) {
////		// TODO Auto-generated method stub
////
////	}
////
////	@Override
////	public VatDeclarationData getVatDeclaration() {
////		// TODO Auto-generated method stub
////		return null;
////	}
//
//}
