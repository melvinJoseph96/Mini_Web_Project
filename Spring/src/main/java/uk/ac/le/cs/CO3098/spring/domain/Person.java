package uk.ac.le.cs.CO3098.spring.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
public class Person {
	

    public Person(Integer key, String name,Integer dob,String g, Integer m, Integer f ) {
		super();
		this.key = key;
		this.name = name;
		this.g = g;
		this.dob = dob;
		this.m = m;
		this.f = f;
		
		
	}
    
    
	public Person() {
		super();
	}


	
	public Integer getKey() {
		return key;
	}


	public void setKey(Integer key) {
		this.key = key;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Integer getM() {
		return m;
	}


	public void setM(Integer m) {
		this.m = m;
	}


	public Integer getF() {
		return f;
	}


	public void setF(Integer f) {
		this.f = f;
	}


	public String getG() {
		return g;
	}


	public void setG(String g) {
		this.g = g;
	}


	public Integer getDob() {
		return dob;
	}


	public void setDob(Integer dob) {
		this.dob = dob;
	}



	@Id
    //@GeneratedValue
    @Column(name = "`key`")
    Integer key;
    
    @Column(name = "name")
	String name;
    @Column(name = "dateOfBirth")
	Integer dob;
    @Column(name = "gender")
	String g;
    @Column(name = "motherId")
	Integer m;
    @Column(name = "fatherId")
	Integer f;
    

    
    

}
