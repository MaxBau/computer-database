package com.computerdatabase.taglibs;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class Paginator extends TagSupport{
	private int itemsPerPage =10;
	private int limitMin=0;
	private int collectionSize;
	
	public int doStartTag() throws JspException
    {
		int nbPages = collectionSize/itemsPerPage;
		JspWriter out = pageContext.getOut();
		for (int i = 0; i < nbPages; i++) {
			try {
				out.write("<a href='Dashboard?limitmin="+(i*itemsPerPage)+"&limitmax="+itemsPerPage+"'>"+(i+1)+"</a> ");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
        
        return EVAL_PAGE;
    }
	
	public  int getItemsPerPage() {
		return itemsPerPage;
	}
	public  void setItemsPerPage(int itemsPerPage) {
		this.itemsPerPage = itemsPerPage;
	}
	public int getLimitMin() {
		return limitMin;
	}
	public void setLimitMin(int limitMin) {
		this.limitMin = limitMin;
	}
	
	public int getCollectionSize() {
		return collectionSize;
	}

	public void setCollectionSize(int collectionSize) {
		this.collectionSize = collectionSize;
	}
}
