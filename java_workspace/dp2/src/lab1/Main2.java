package lab1;

import JaCoP.constraints.And;
import JaCoP.constraints.Constraint;
import JaCoP.constraints.IfThen;
import JaCoP.constraints.IfThenElse;
import JaCoP.constraints.Or;
import JaCoP.constraints.XeqC;
import JaCoP.constraints.XeqY;
import JaCoP.core.IntVar;
import JaCoP.core.Store;
import JaCoP.search.DepthFirstSearch;
import JaCoP.search.IndomainMin;
import JaCoP.search.PrintOutListener;
import JaCoP.search.Search;
import JaCoP.search.SelectChoicePoint;
import JaCoP.search.SimpleSelect;
import JaCoP.search.SmallestDomain;

public class Main2 {
	private static Store STORE = new Store();
	
	public static void main(String[] args) {
		IntVar a = new IntVar(STORE, "A", 0, 1);
		IntVar b = new IntVar(STORE, "B", 0, 1);
		IntVar c = new IntVar(STORE, "C", 0, 1);
		
		IntVar invCout = new IntVar(STORE, "invCout", 0, 1);
		IntVar cout = new IntVar(STORE, "cout", 0, 1);

		IntVar sum = new IntVar(STORE, "Sum", 0, 1);
		IntVar invSum = new IntVar(STORE, "InvSum", 0, 1);
		
		IntVar vdd = new IntVar(STORE, "vdd", 1, 1);
		IntVar zero = new IntVar(STORE, "noll", 0, 0);
		
		IntVar[] t = new IntVar[10];
		for(int i = 0; i < 10; i++){
			t[i] = new IntVar(STORE, "t" + i, 0, 1);
		}
		
		// One Stage inverse carry module ... of DOOM
		pTrans(a, vdd, t[1]);
		pTrans(b, vdd, t[1]);
		pTrans(b, vdd, t[2]);
		inverter(c, invCout, t[1], t[4]);
		nTrans(a, t[4], zero);
		nTrans(b, t[4], zero);
		nTrans(b, t[3], zero);
		nTrans(a, invCout, t[3]);
		pTrans(a, t[2], invCout);
		
		// inverter for cout ... of DOOM
		inverter(invCout, cout, vdd, zero);
		
		// One Stage Inverse Sum module ...
		pTrans(a, vdd, t[5]);
		pTrans(b, vdd, t[5]);
		pTrans(c, vdd, t[5]);
		inverter(invCout, invSum, t[5], t[6]);
		nTrans(a, t[6], zero);
		nTrans(b, t[6], zero);
		nTrans(c, t[6], zero);

		pTrans(a, vdd, t[7]);
		pTrans(b, t[7], t[8]);
		pTrans(c, t[8], invSum);
		
		nTrans(c, invSum, t[9]);
		nTrans(b, t[9], t[0]);
		nTrans(a, t[0], zero);
		
		inverter(invSum, sum, vdd, zero);
		
		IntVar[] array = {a, b, c, cout, sum};
		
		Search<IntVar> label = new DepthFirstSearch<IntVar>();
		SelectChoicePoint<IntVar> select = new SimpleSelect<IntVar>(array,
		new SmallestDomain<IntVar>(),
		new IndomainMin<IntVar>());
		label.setSolutionListener(new PrintOutListener<IntVar>());
		label.getSolutionListener().searchAll(true);
		label.labeling(STORE, select);		
		
	}
	
	private static void inverter(IntVar in, IntVar out, IntVar top, IntVar bot) {
		pTrans(in, top, out);
		nTrans(in, out, bot);
	}
	
	private static void nTrans(IntVar b, IntVar x, IntVar y) {
		STORE.impose(new IfThen(new XeqC(b, 1),
				new XeqY(x, y)));
	}

	private static void pTrans(IntVar b, IntVar x, IntVar y) {
		STORE.impose(new IfThen(new XeqC(b, 0),
				new XeqY(x, y)));
	}

}
