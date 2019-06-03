package uk.ac.le.cs.CO3098.spring.domain;

public class Person2 {
	
	Integer key;
	String name;
	String gender;
	Integer mother;
	Integer father;
	Integer wife;
	Integer husband;
	
	
	
	
	public Person2() {
		super();
	}



	public Person2(Integer key, String name, String gender) {
		super();
		this.key = key;
		this.name = name;
		this.gender = gender;
	}
	
	
	
	public Person2(Integer key, String name, String gender, Integer mother, Integer father) {
		super();
		this.key = key;
		this.name = name;
		this.gender = gender;
		this.mother = mother;
		this.father = father;
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
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Integer getMother() {
		return mother;
	}
	public void setMother(Integer mother) {
		this.mother = mother;
	}
	public Integer getFather() {
		return father;
	}
	public void setFather(Integer father) {
		this.father = father;
	}
	public Integer getWife() {
		return wife;
	}
	public void setWife(Integer wife) {
		this.wife = wife;
	}
	public Integer getHusband() {
		return husband;
	}
	public void setHusband(Integer husband) {
		this.husband = husband;
	}
	
	
	

}
