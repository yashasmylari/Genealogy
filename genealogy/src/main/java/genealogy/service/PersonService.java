package genealogy.service;

import org.json.JSONObject;

import genealogy.dto.Person;
import genealogy.struct.RelatedPerson;

/**
 * Service the CRUD operation request for the 'Person' node.
 * @author Vivek Yadav
 */
public interface PersonService {

	/**
	 * Delete all the nodes of the person
	 */
	void deleteAll();

	/**
	 * Add Person node in the database using the JSONObject containing the data of the person
	 * @param personJson
	 * @return person
	 */
	Person addPerson(JSONObject person);

	/**
	 * Relate nodes of the person by creating a relationship between them
	 * @param person1
	 * @param person2
	 * @param relation
	 * @return relatedPerson
	 */
	RelatedPerson<Person, Person> relatePerson(String person1, String person2, String relation);

	/**
	 * Find relation between the nodes of the two person.
	 * @param person1
	 * @param person2
	 * @return strRelationship
	 */
	String findRelation(String person1, String person2);

	/**
	 * Finds the Person node with the given name
	 * @param name
	 * @return person
	 */
	Person findPerson(String name);

}
