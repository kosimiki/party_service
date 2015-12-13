package hu.unideb.inf.it.main.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class StockItem {
	
	private String name;
	private String type;
	private String state;
	private Integer rentCount;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Integer getRentCount() {
		return rentCount;
	}

	public void setRentCount(Integer rentCount) {
		this.rentCount = rentCount;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
