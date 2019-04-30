package genealogy.struct;

import genealogy.dto.Person;

public class RelatedPerson<P1 extends Person, P2 extends Person> {

	private P1 p1;
	private P2 p2;

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

	@Override
	public String toString() {
		return new StringBuilder()
				.append(p1.getName())
				.append(" >> ")
				.append(p1.getRelation())
				.append(" >> ")
				.append(p2.getName())
				.append("\n")
				.append(p1.getName())
				.append(" << ")
				.append(p2.getRelation())
				.append(" << ")
				.append(p2.getName())
				.toString();
	}

}
