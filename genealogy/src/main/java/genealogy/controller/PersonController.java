package genealogy.controller;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import genealogy.dto.Person;
import genealogy.service.PersonService;
import genealogy.struct.RelatedPerson;

/**
 * Rest Controller to service the CRUD operation request for the 'Person' node.
 * @author Vivek Yadav
 */
@RestController
@RequestMapping("/person")
public class PersonController {
	
	private final static Logger log = LoggerFactory.getLogger(PersonController.class);

	@Autowired
	PersonService personService;



	/**
	 * Adds the given 'Person' as the graph node
	 * @param reqBody
	 * @return
	 */
	@RequestMapping(path = "/add", method = {RequestMethod.GET, RequestMethod.POST})
	public String addPerson(@RequestBody String reqBody) {
		try {
			JSONObject person = new JSONObject(reqBody);
			Person personModel = personService.addPerson(person);
			return personModel.toString();
		}
		catch(Exception ex) {
			log.error("An error occurred while adding person", ex);
			return new StringBuilder("{\"error\":\"").append(ex).append("\"}").toString();
		}
	}



	/**
	 * Adds the set of given 'Person' as the graph nodes
	 * @param reqBody
	 * @return
	 */
	@RequestMapping(path = "/addAll", method = {RequestMethod.GET, RequestMethod.POST})
	public String addAllPerson(@RequestBody String reqBody) {
		try {
			JSONArray jsonOutput = new JSONArray();
			JSONArray jsonArray = new JSONArray(reqBody);
			for(int i=0; i<jsonArray.length(); i++) {
				JSONObject person = jsonArray.getJSONObject(i);
				Person personModel = personService.addPerson(person);
				jsonOutput.put(personModel.json());
			}
			return jsonOutput.toString();
		}
		catch(Exception ex) {
			log.error("An error occurred while adding all person", ex);
			return new StringBuilder("{\"error\":\"").append(ex).append("\"}").toString();
		}
	}



	/**
	 * Develops the relation between the two given person.
	 * @param reqBody
	 * @return strRelation
	 */
	@RequestMapping(path = "/relate", method = {RequestMethod.GET, RequestMethod.POST})
	public String relatePerson(@RequestBody String reqBody) {
		try {
			JSONObject jsonRelation = new JSONObject(reqBody);
			String person1 = jsonRelation.getString("person1");
			String person2 = jsonRelation.getString("person2");
			String related = jsonRelation.getString("relation");
			RelatedPerson<Person, Person> relatedPerson = personService.relatePerson(person1, person2, related);
			if(relatedPerson==null)
				return "Atleast one of the person node is undefined";
			return relatedPerson.toString();
		}
		catch(Exception ex) {
			log.error("An error occurred while relating person", ex);
			return new StringBuilder("{\"error\":\"").append(ex).append("\"}").toString();
		}
	}



	/**
	 * Develops the relation between the set of two given person.
	 * @param reqBody
	 * @return strRelation
	 */
	@RequestMapping(path = "/relateAll", method = {RequestMethod.GET, RequestMethod.POST})
	public String relateAllPerson(@RequestBody String reqBody) {
		try {
			JSONArray jsonOutput = new JSONArray();
			JSONArray jsonArray = new JSONArray(reqBody);
			for(int i=0; i<jsonArray.length(); i++) {
				JSONObject jsonRelation = jsonArray.getJSONObject(i);
				String person1 = jsonRelation.getString("person1");
				String person2 = jsonRelation.getString("person2");
				String related = jsonRelation.getString("relation");
				RelatedPerson<Person, Person> relatedPerson = personService.relatePerson(person1, person2, related);
				if(relatedPerson==null)
					jsonOutput.put("Atleast one of the person node is undefined");
				else
					jsonOutput.put(relatedPerson.toJSON());
			}
			return jsonOutput.toString();
		}
		catch(Exception ex) {
			log.error("An error occurred while relating all person", ex);
			return new StringBuilder("{\"error\":\"").append(ex).append("\"}").toString();
		}
	}



	/**
	 * Finds the relation between the two given 'Person' nodes.
	 * @param reqBody
	 * @return
	 */
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
			log.error("An error occurred while finding relation between person", ex);
			return new StringBuilder("{\"error\":\"").append(ex).append("\"}").toString();
		}
	}



	/**
	 * Finds the person with the given name.
	 * @param name
	 * @return strPerson
	 */
	@RequestMapping(path = "/findPerson", method = {RequestMethod.GET, RequestMethod.POST})
	public String findPerson(@RequestParam String name) {
		try {
			Person person = personService.findPerson(name);
			if(person!=null)
				return person.toString();
			return "{}";
		}
		catch(Exception ex) {
			log.error("An error occurred while finding person", ex);
			return new StringBuilder("{\"error\":\"").append(ex).append("\"}").toString();
		}
	}


}
