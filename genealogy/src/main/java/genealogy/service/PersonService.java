package genealogy.service;

import org.json.JSONObject;

import genealogy.dto.Person;
import genealogy.struct.RelatedPerson;

public interface PersonService {

	void deleteAll();

	RelatedPerson<Person, Person> relatePerson(String person1, String person2, String relation);

	Person addPerson(JSONObject person);

	String findRelation(String person1, String person2);

}
