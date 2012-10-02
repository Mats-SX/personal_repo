package lab1_cool;

import JaCoP.core.IntDomain;
import JaCoP.core.IntVar;
import JaCoP.core.Store;

public class Main {
	
	public static void main(String[] args) {
		Store store = new Store();

		//IntDomain dom = 
		
		//IntVar a = new IntVar(store, "A", 0, 1);
		IntVar b = new IntVar(store, "B", 0, 1);
		IntVar c = new IntVar(store, "C", 0, 1);
		
		IntVar invCout = new IntVar(store, "invCout", 0, 1);
		IntVar cout = new IntVar(store, "cout", 0, 1);

		IntVar sum = new IntVar(store, "Sum", 0, 1);
		IntVar invSum = new IntVar(store, "InvSum", 0, 1);
		
		IntVar vdd = new IntVar(store, "vdd", 1, 1);
		IntVar zero = new IntVar(store, "noll", 0, 0);
	}

}
