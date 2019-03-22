import static org.junit.Assert.*;

import org.junit.Test;

public class CalcANumberTest {

	CalcANumber MeinObjekt = new CalcANumber();

	@Test
	public void it_should_make_a_One_from_a_I() {
		assertEquals(1, MeinObjekt.rechne("I"));
	}
	
	@Test
	public void it_should_make_a_Two_from_a_II() {
		assertEquals(2, MeinObjekt.rechne("II"));
	}
	
	@Test
	public void it_should_make_a_Three_from_a_III() {
		assertEquals(3, MeinObjekt.rechne("III"));
	}
		
	@Test
	public void it_should_make_a_Four_from_a_IV() {
		assertEquals(4, MeinObjekt.rechne("IV"));
	}

}
