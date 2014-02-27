package com.computerdatabase.taglibs;

import java.util.List;

import com.computerdatabase.classes.Computer;
import com.computerdatabase.services.ComputerService;

public class Paginator {
	private static int itemsPerPage;
	private static int limitMin=0;
	
	public Paginator() {
		super();
		// TODO Auto-generated constructor stub
	}
	public static int getItemsPerPage() {
		return itemsPerPage;
	}
	public static void setItemsPerPage(int itemsPerPage) {
		Paginator.itemsPerPage = itemsPerPage;
	}
	public static int getLimitMin() {
		return limitMin;
	}
	public static void setLimitMin(int limitMin) {
		Paginator.limitMin = limitMin;
	}

	
	public static List<Computer> nextPage() {
		limitMin += itemsPerPage;
		
		ComputerService cs = ComputerService.getInstance();
		int countComputer = cs.countComputer();
		if ((limitMin+itemsPerPage)>countComputer) limitMin = countComputer-itemsPerPage;
		return cs.getComputersInLimit(limitMin,itemsPerPage);
	}
	
	public static List<Computer> previousPage() {
		limitMin -= itemsPerPage;
		
		if (limitMin<0) limitMin=0;
		
		ComputerService cs = ComputerService.getInstance();
		return cs.getComputersInLimit(limitMin, itemsPerPage);
	}
	
	

}
