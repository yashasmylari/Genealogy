package genealogy.service;

import org.json.JSONObject;

import genealogy.dto.Person;
import genealogy.struct.RelatedPerson;

public interface PersonService {

	void deleteAll();

	Person addPerson(JSONObject person);

	boolean relatePerson(RelatedPerson<Person, Person> relatedPerson);

	String findRelation(String person1, String person2);

}
