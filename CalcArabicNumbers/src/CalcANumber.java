
public class CalcANumber {

public int rechne(String Wert) {
	
	int arabic=0;
	int I = 1;
	int V = 5;
	int X = 10;
	int L = 50;
	int C = 100;
	int D = 500;
	int M = 1000;
	
	
	
	if (Wert == "I")
		return arabic =I;

	if (Wert == "II")
		return	arabic=I+I;
	
	if (Wert == "III")
		return	arabic=I+I+I;
	
	if (Wert == "IV")
		return	arabic=V-I;
	
	
	return arabic;
	

}

}
