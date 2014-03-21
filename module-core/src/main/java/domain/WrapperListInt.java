package domain;

import java.util.List;

public class WrapperListInt {
	private List list;
	private long integer;
	public WrapperListInt() {
		super();
		// TODO Auto-generated constructor stub
	}
	public WrapperListInt(List list, long integer) {
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
	public long getInteger() {
		return integer;
	}
	public void setInteger(long integer) {
		this.integer = integer;
	}
	
	
}
