package springapp.domain;

import java.util.List;

public class WrapperListInt {
	private List list;
	private int integer;
	public WrapperListInt() {
		super();
		// TODO Auto-generated constructor stub
	}
	public WrapperListInt(List list, int integer) {
		super();
		this.list = list;
		this.integer = integer;
	}
	public List getList() {
		return list;
	}
	public void setList(List list) {
		this.list = list;
	}
	public int getInteger() {
		return integer;
	}
	public void setInteger(int integer) {
		this.integer = integer;
	}
	
	
}
