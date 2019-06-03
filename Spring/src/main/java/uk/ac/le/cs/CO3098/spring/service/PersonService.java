package uk.ac.le.cs.CO3098.spring.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uk.ac.le.cs.CO3098.spring.domain.Person;
import uk.ac.le.cs.CO3098.spring.repository.PersonRepository;

@Service
public class PersonService {
	
	@Autowired
	private PersonRepository personRepository;
	
	
	public Object findAllpeople(){
		return personRepository.findAll();
	}
	
	
	public ArrayList<Person> findAncestors(Integer i) {
		
		ArrayList<Person> ancestors = new ArrayList<>();
		
		Person p = personRepository.findOne(i);
		if(p.getF()!=null) {
			Integer id = p.getF();
			Person f = personRepository.findOne(id);
			ancestors.add(f);
			System.out.println("Im in");
			findAncestors(f.getKey());
			
			
		}
		
		return ancestors;
		
	}
	
	public boolean doesIDExist(Integer id) {
		if(personRepository.findOne(id)!=null) {
			
			return false;
		}else return true;
		
	}
	
	
	
	
	public void updateUser(Person a) {
		
		Person b = personRepository.findOne(a.getKey());
		b.setDob(a.getDob());
		b.setF(a.getF());
		b.setG(a.getG());
		b.setM(a.getM());
		personRepository.save(b);	
		
	}
	
	public Person findById(Integer i){
		return personRepository.findOne(i);
	}
	
	
		
		
	
	
	public void deleteById(Integer id){
		 personRepository.delete(id);;
	}
	
	
	public void save(Person a){
		personRepository.save(a);
	}

}
