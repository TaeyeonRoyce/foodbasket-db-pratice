package springJr.foodbasket.web;

import org.junit.jupiter.api.Test;



public class PlayGround {

	@Test
	void methodTest() {
		String a = null;
		String ab = ab(a);
		if (ab == null) {
			System.out.println("Null");
		}

	}

	private String ab(String a) {
		return a;
	}

}
