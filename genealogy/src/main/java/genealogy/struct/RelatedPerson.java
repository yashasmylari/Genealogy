package genealogy.struct;

import org.json.JSONException;
import org.json.JSONObject;

import genealogy.dto.Person;

public class RelatedPerson<P1 extends Person, P2 extends Person> {

	private P1 p1;
	private P2 p2;
	private String relation;
	private JSONObject jsonRelation;

	public RelatedPerson()  {}

	public RelatedPerson(P1 p1, P2 p2)  {
		this.p1 = p1;
		this.p2 = p2;
	}

	public P1 getP1() {
		return p1;
	}
	public RelatedPerson<P1, P2> setP1(P1 p1) {
		this.p1 = p1;
		return this;
	}

	public P2 getP2() {
		return p2;
	}
	public RelatedPerson<P1, P2> setP2(P2 p2) {
		this.p2 = p2;
		return this;
	}

	public void generateRelationship() {
		relation = toJSON().toString();
	}


	public JSONObject toJSON() {
		if(jsonRelation!=null)
			return jsonRelation;

		JSONObject json = new JSONObject();
		try {
			json.put("(person1)-[relation]->(person2)",
				new JSONObject()
				.put("person1", p1.getName())
				.put("relation", p1.getRelation())
				.put("person2", p2.getName())
			);
			json.put("(person2)-[relation]->(person1)",
				new JSONObject()
				.put("person2", p2.getName())
				.put("relation", p1.getRelation())
				.put("person1", p1.getName())
			);
		}
		catch (JSONException e) {
			e.printStackTrace();
		}
		jsonRelation = json;
		return jsonRelation;
	}


	@Override
	public String toString() {
		return relation!=null ? relation : toJSON().toString();
	}

}
