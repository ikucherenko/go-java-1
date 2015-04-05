package ua.com.goit.gojava.andriidnikitin.MyShop.domain.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="GOOD")
public class Good {
	
	@Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    @Column(name="GOOD_ID")
	private Integer id;
	
	@Column(name="NAME")
	private String name;

	@ManyToOne
	@JoinTable(name = "TYPE_ID")
	private GoodType type;
	
	@OneToMany
	@JoinTable(name = "GOOD_ID")
	private Set<GoodRecord> records;

	public GoodType getType() {
		return type;
	}

	public void setType(GoodType type) {
		this.type = type;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}	

	public Set<GoodRecord> getRecords() {
		return records;
	}

	public void setRecords(Set<GoodRecord> records) {
		this.records = records;
	}
			
}
