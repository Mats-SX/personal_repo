package onlyPackage;

public class theclass {
	
	private int lol;
	private String lol2;
	private String lol3;
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + lol;
		result = prime * result + ((lol2 == null) ? 0 : lol2.hashCode());
		result = prime * result + ((lol3 == null) ? 0 : lol3.hashCode());
		return result;
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		theclass other = (theclass) obj;
		if (lol != other.lol)
			return false;
		if (lol2 == null) {
			if (other.lol2 != null)
				return false;
		} else if (!lol2.equals(other.lol2))
			return false;
		if (lol3 == null) {
			if (other.lol3 != null)
				return false;
		} else if (!lol3.equals(other.lol3))
			return false;
		return true;
	}

}
