package genealogy.controller;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import genealogy.dto.Person;
import genealogy.service.PersonService;

@RestController
@RequestMapping("/person")
public class PersonController {

	@Autowired
	PersonService personService;


	@RequestMapping(path = "/add", method = {RequestMethod.GET, RequestMethod.POST})
	public String addPerson(@RequestBody String reqBody) {
		try {
			JSONObject person = new JSONObject(reqBody);
			Person personModel = personService.addPerson(person);
			return personModel.jsonString();
		}
		catch(Exception ex) {
			System.out.println(ex);
			return "error";
		}
	}



	@RequestMapping(path = "/addAll", method = {RequestMethod.GET, RequestMethod.POST})
	public String addAllPerson(@RequestBody String reqBody) {
		try {
			JSONArray jsonOutput = new JSONArray();
			JSONArray jsonArray = new JSONArray(reqBody);
			for(int i=0; i<jsonArray.length(); i++) {
				JSONObject person = jsonArray.getJSONObject(i);
				Person personModel = personService.addPerson(person);
				jsonOutput.put(personModel);
			}
			return jsonOutput.toString();
		}
		catch(Exception ex) {
			System.out.println(ex);
			return "error";
		}
	}



	@RequestMapping(path = "/findRelation", method = {RequestMethod.GET, RequestMethod.POST})
	public String findRelation(@RequestBody String reqBody) {
		try {
			JSONObject jsonData = new JSONObject(reqBody);
			String person1 = jsonData.getString("person1");
			String person2 = jsonData.getString("person2");
			String relation = personService.findRelation(person1, person2);
			return relation;
		}
		catch(Exception ex) {
			System.out.println(ex);
			return "error";
		}
	}

}
