package hu.unideb.inf.it.main.Model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class PartyEvent {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String name;
	private Long placeID;
	private Integer price;
	@ManyToMany(fetch=FetchType.EAGER)
	 @JoinTable(
		      name="PartyEvent_StockItem",
		      joinColumns={@JoinColumn(name="PartyEvent_id", referencedColumnName="id")},
		      inverseJoinColumns={@JoinColumn(name="items_id", referencedColumnName="id")})
	private List<StockItem> items;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getPlaceID() {
		return placeID;
	}
	public void setPlaceID(Long placeID) {
		this.placeID = placeID;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public List<StockItem> getItems() {
		return items;
	}
	public void setItems(List<StockItem> items) {
		this.items = items;
	}
	
	
	
}
