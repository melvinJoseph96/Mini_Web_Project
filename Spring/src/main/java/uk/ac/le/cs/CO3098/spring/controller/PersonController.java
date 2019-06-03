package uk.ac.le.cs.CO3098.spring.controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Arrays;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.validation.ValidationUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import org.springframework.boot.json.*;

import uk.ac.le.cs.CO3098.spring.domain.Person2;
import uk.ac.le.cs.CO3098.spring.domain.JSONResponse;
import uk.ac.le.cs.CO3098.spring.domain.Person;
import uk.ac.le.cs.CO3098.spring.service.PersonService;

@Controller
@RequestMapping(value = {"/GE"})
public class PersonController {
	  @Autowired
	  PersonService as;
	  
	
	  @RequestMapping(value = {"/listAll"})
	    public ModelAndView listAll(Model model){
	        System.out.println(as.findAllpeople());
	        return new ModelAndView("listAll","people",as.findAllpeople());
	    }  
	  @RequestMapping(value = {"/listAllJson"})
	    public @ResponseBody Object listAllJson(Model model){
	        Object o=as.findAllpeople();
	        return o;
	    }
	  
	  @RequestMapping(value = "person/add", method = RequestMethod.GET,produces = {"application/json"} )
	    public  ModelAndView addPerson(@RequestParam(value ="key") Integer key,@RequestParam(value ="name")String name,
	    								@RequestParam(value ="m", required = false) Integer mKey, @RequestParam(value ="f", required = false) Integer fKey,
	    								@RequestParam(value="dob", required = false)Integer dob,@RequestParam(value ="g", required = false) String gender){
	        Person pers = new Person(key,name,dob,gender,mKey,fKey);
	        as.save(pers);
	        return new ModelAndView("listAll","people",as.findAllpeople());
	  }
	  @RequestMapping(value = "person/addJSON", method = RequestMethod.POST,consumes="application/json",produces = {"application/json"} )
	    public @ResponseBody Object addPerson1(@RequestBody Person json){
		  String o;
		  JSONResponse res;
		  System.out.println(json.getKey());
		  if(as.doesIDExist(json.getKey())==true) {
			  System.out.println(json.getKey()+"true");
			  res = new JSONResponse("false", "Key "+json.getKey()+" "+"already exists"); 
			  o=gson.toJson(res);
			  
		  }else {
			  System.out.println(json.getKey()+"false");
		  as.save(json);
		  res = new JSONResponse("true");
		  o=gson.toJson(res);
		  }
		
			return o;
	     
	  }
	  
	    @RequestMapping(value = "/create")
	    public ModelAndView create(){	
	    	  return new ModelAndView("create");    
	    } 
	    
	    @RequestMapping(value = "/test")
	    public ModelAndView test(Model model){
	    	
	    	
	    	  return new ModelAndView("test");    
	    }
	    
	    
	    public List<Person2> jsonfortree() {
	    	Person2 p2 = new Person2();
	    	List<Person2> toRet = new ArrayList<>();
	    	List<Person> people =  (ArrayList<Person>) as.findAllpeople();
	    	for(Person person : people){
	    		
	    		try {
	    			p2.setKey(person.getKey());
		    		p2.setName(person.getName());
		    		p2.setGender(person.getG());
		    		p2.setFather(person.getF());
		    		p2.setMother(person.getM());
		    		if(person.getG()=="male") {
		    			p2.setWife(getSpouse(person.getKey()));
		    		}else p2.setHusband(getSpouse(person.getKey()));
		    		
	    			toRet.add(p2);
	    			
	    		}catch(Exception e) {
	    			
	    			System.out.println(e);
	    		}
	    		
	    	}
	    	
	    	return toRet;
	    	
	    }
	    
	    
	    
	    public int getSpouse(Integer id) {
	    	int toRet=0;
	    	
	    	List<Person> people =  (ArrayList<Person>) as.findAllpeople();
	    	for(Person person : people){
	    		
	    		try {	    			
	    			if(person.getF()!=null &&person.getF()==id ) {		    			
		    			toRet = person.getM();		    			
		    		}else if (person.getM()!= null && person.getM()==id) {
		    			toRet = person.getF();		    			
		    		}    			
	    			
	    		}catch(Exception e) {	    			
	    			System.out.println(e);	    			
	    		}   		
	    	
	    	}	    	
	    	return toRet;   	
	    	
	    }
	    
	    @RequestMapping(value = "/save", method = RequestMethod.POST)
	    public ModelAndView save(@ModelAttribute(value="user") Person a){
	        as.save(a); 
	        return new ModelAndView("redirect:listAll");

	    }
	    
	    
	    @RequestMapping(value = "/edit/{id}")
	    public ModelAndView edit(@PathVariable Integer id){
	        return new ModelAndView("edit","person",as.findById(id));
	    } 
	    
	    
	    @RequestMapping(value = "person/delete/{id}", method = RequestMethod.GET)
	    public @ResponseBody String delete(@PathVariable Integer id){
	    	String json = null;
	    	Object obj = null;
	    	if(as.findById(id)!=null) {
	    		as.deleteById(id);
	    		JSONResponse response = new JSONResponse("True");
	    		obj = response;
	    		json = gson.toJson(obj);
	    		
	    	}else {
	    		JSONResponse response = new JSONResponse("false", "Key "+id+" "+"does not exist");
	    		obj = response;
	    		json = gson.toJson(obj);
	    	}
	    	return json;
	    	
	    	
	        
	        
	        //return new ModelAndView("listAll","people",as.findAllpeople());
	    }
	    
	    @RequestMapping(value = "person/decendants/{id}", method = RequestMethod.GET, produces = {"application/json"})
	    public @ResponseBody Object getDecendants(@PathVariable Integer id){
	    		    	
	    	Map<String,Object> d = new LinkedHashMap<>();
	    	
	    	if(as.findById(id)!=null) {
	    		d = makeDecendants(id);	    		
	    		
	    	}else {
	    		JSONResponse res = new JSONResponse("false", "id "+ id+ " does not exist!");
	    		d.put("error", res);
	    	}
	    	String json;
	    	json = gson.toJson(d);
	    	return json;
	    }
	    
	    
	    public Map<String,Object> makeDecendants(int id){
	    	Person p = as.findById(id); 
	    	Map<String,Object> d = new LinkedHashMap<>();
	    	d.put("key", id);
	    	List<Person> people =  (ArrayList<Person>) as.findAllpeople();
	    	int i = 0;
	    	for(Person person:people) {
	    		if(person.getM()==p.getKey() || person.getF()==p.getKey()) {
	    			i+=1;
	    		}
	    	}
	    	
	    	if(i!=0) {
	    		d.put("children", findChildren(id));
	    	}
	    		   
	    	    	
	    	return d;	    	
	    }
	    
	    public List<Object> findChildren(int id){
	    	
	    	Person p = as.findById(id);
	    	List<Object> toRet = new ArrayList<>();
	    	List<Person> people =  (ArrayList<Person>) as.findAllpeople();
	    	
	    	for(Person person : people){	    		
	    		
	    		try {
	    			
	    			if(person.getM()==id || person.getF() == id) {
	    		toRet.add(makeDecendants(person.getKey()));	    			
	    		}	
	    			
	    		}catch(Exception e){
	    			System.out.println(e);
	    			
	    			
	    		}
	    		    			    		
	    	}	    	
	    	return toRet;	    	
	    }
	    
	    
	    
	    @RequestMapping(value = "person/ancestors/{id}", method = RequestMethod.GET, produces = {"application/json"})
	    public @ResponseBody Object getAncestors(@PathVariable Integer id){
	    		    	
	    	Map<String,Object> d = new LinkedHashMap<>();
	    	
	    	if(as.findById(id)!=null) {
	    		d = makeAncestors(id);	    		
	    		
	    	}else {
	    		JSONResponse res = new JSONResponse("false", "id "+ id+ " does not exist!");
	    		d.put("error", res);
	    	}
	    	String json;
	    	json = gson.toJson(d);
	    	return json;
	    }
	    
	    
	    public Map<String,Object> makeAncestors(int id){
	    	Person p = as.findById(id); 
	    	Map<String,Object> d = new LinkedHashMap<>();
	    	Map<String,Object> mf = new LinkedHashMap<>();
	    	List<Person> people =  (ArrayList<Person>) as.findAllpeople();

	    	
	    	if(p.getM()!=null && p.getF()!=null) {
	    		d.put("key", id);
	    		mf.put("m", findMother(p.getM()));
	    		mf.put("f", findFather(p.getF()));
	    		d.put("parents", mf);
	    	}
	    	else if(p.getF()!=null) {
	    			d.put("key", id);
	    			mf.put("f", findFather(p.getF()));	
	    			d.put("parents", mf);
    			
	    	}else if(p.getM()!=null) {
	    			d.put("key", id);
	    			mf.put("m", findMother(p.getM()));	
	    			d.put("parents", mf);
	    	} else {
	    		d.put("key", p.getKey());
	    	}
	    	    	
	    	return d;	    	
	    }
	    
	    public List<Object> findMother(int id){
	    	
	    	Person p = as.findById(id);
	    	List<Object> toRet = new ArrayList<>();    	
	    	
    		toRet.add(makeAncestors(p.getKey()));

	    	return toRet;	    	
	    }
	    
	    public List<Object> findFather(int id){
	    	
	    	Person p = as.findById(id);
	    	List<Object> toRet = new ArrayList<>();	    	
    		toRet.add(makeAncestors(p.getKey()));    	

	    	return toRet;	    	
	    }
	    
	    
	    @RequestMapping(value = "person/delete2/{id}", method = RequestMethod.GET)
	    public ModelAndView delete2(@PathVariable Integer id){
	        as.deleteById(id); 
	        return new ModelAndView("listAll","people",as.findAllpeople());
	    }
	 
	    Gson gson = new GsonBuilder().setPrettyPrinting().create();  
	    
	    @RequestMapping(value = "person/get/{id}", method = RequestMethod.GET, produces = {"application/json"})
	    public @ResponseBody String getId(@PathVariable Integer id){
	    	Object obj = null;
	    	String json = null;
	    	if(as.findById(id)!=null) {
	        obj = as.findById(id);
	        json = gson.toJson(obj);
	        //
	    	}else {
	    		JSONResponse response = new JSONResponse("false", "Key "+id+" "+"does not exist");
	    		obj = response;
	    		json = gson.toJson(obj);
	    	}
	    	return json;
	    }
	    @RequestMapping(value = "/update",method = RequestMethod.POST)
	    public ModelAndView update(Person per){
	    	as.save(per);  	
	    	
	        
	        return new ModelAndView("listAll","people",as.findAllpeople());
	    }

}
