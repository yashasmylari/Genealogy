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
import genealogy.struct.RelatedPerson;

@RestController
@RequestMapping("/person")
public class PersonController {

	@Autowired
	PersonService personService;


	@RequestMapping(path = "/add", method = {RequestMethod.GET, RequestMethod.POST})
	public String addPerson(@RequestBody String reqBody) {
		try {
			JSONObject jsonData = new JSONObject(reqBody);
			JSONObject person1 = jsonData.getJSONObject("person1");
			JSONObject person2 = jsonData.getJSONObject("person2");
			String relation = jsonData.getString("relation");
			RelatedPerson<Person, Person> relatedPerson = personService.addAndRelatePerson(person1, relation, person2);
			return relatedPerson.toString();
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
			RelatedPerson<Person, Person> relatedPerson = null;
			for(int i=0; i<jsonArray.length(); i++) {
				JSONObject jsonData = jsonArray.getJSONObject(i);
				JSONObject person1 = jsonData.getJSONObject("person1");
				JSONObject person2 = jsonData.getJSONObject("person2");
				String relation = jsonData.getString("relation");
				relatedPerson = personService.addAndRelatePerson(person1, relation, person2);
				jsonOutput.put(relatedPerson);
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
