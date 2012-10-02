package lab1;

import java.util.ArrayList;

import JaCoP.constraints.*;
import JaCoP.core.*;
import JaCoP.search.*;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Store store = new Store();
		IntVar in1 = new IntVar(store, "In1", 0, 1);
		IntVar in2 = new IntVar(store, "In2", 0, 1);
		IntVar c = new IntVar(store, "c", 0, 1);
		IntVar s = new IntVar(store, "S", 0, 1);
		IntVar carry = new IntVar(store, "carry", 0, 1);
		
		IntVar x1 = new IntVar(store, "x1", 0, 1);
		IntVar x2 = new IntVar(store, "x2", 0, 1);
		IntVar a1 = new IntVar(store, "a1", 0, 1);
		IntVar a2 = new IntVar(store, "a2", 0, 1);
		IntVar o1 = new IntVar(store, "o1", 0, 1);
		
		store.impose(new XeqY(o1, carry));
		store.impose(new XeqY(x2, s));
		
		// AND gates
		Constraint and1 = 
			new IfThenElse(new And(
					new XeqC(in1, 1), new XeqC(in2, 1)),
					new XeqC(a1, 1),
					new XeqC(a1, 0));
		Constraint and2 = 
			new IfThenElse(new And(
					new XeqC(c, 1), new XeqC(x1, 1)),
					new XeqC(a2, 1),
					new XeqC(a2, 0));
		store.impose(and1);
		store.impose(and2);
		
		// OR gate
		Constraint or = 
			new IfThenElse(new Or(
					new XeqC(a1, 1), new XeqC(a2, 1)),
					new XeqC(o1, 1),
					new XeqC(o1, 0));
		store.impose(or);
		
		// XOR gates
		Constraint xor1 =
			new IfThenElse(new XeqY(in1, in2),
					new XeqC(x1, 0),
					new XeqC(x1, 1));
		Constraint xor2 =
			new IfThenElse(new XeqY(x1, c),
					new XeqC(x2, 0),
					new XeqC(x2, 1));
		store.impose(xor1);
		store.impose(xor2);
		
		IntVar[] array = {in1, in2, c, carry, s};
		
		Search<IntVar> label = new DepthFirstSearch<IntVar>();
		SelectChoicePoint<IntVar> select = new SimpleSelect<IntVar>(array,
		new SmallestDomain<IntVar>(),
		new IndomainMin<IntVar>());
		label.setSolutionListener(new PrintOutListener<IntVar>());
		label.getSolutionListener().searchAll(true);
		label.labeling(store, select);		
		
	}

}
