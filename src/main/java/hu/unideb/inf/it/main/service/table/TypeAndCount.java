package hu.unideb.inf.it.main.service.table;

public class TypeAndCount {
	private String type;
	private Integer count;
	public String getType() {
		return type;
	}
	public TypeAndCount(String type, Integer count) {
		this.type = type;
		this.count = count;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	
}
