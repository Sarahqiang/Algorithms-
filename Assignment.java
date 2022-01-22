mport java.util.*;
 
class Assignment implements Comparator<Assignment>{
	int number;
	int weight;
	int deadline;
	
	protected Assignment() {
	}
	
	protected Assignment(int number, int weight, int deadline) {
		this.number = number;
		this.weight = weight;
		this.deadline = deadline;
	}
	
	/**
	 * This method is used to sort to compare assignment objects for sorting. 
	 */
	@Override
	public int compare(Assignment a1, Assignment a2) {
		int pority = 0;
			/*compare a1 and a2 when they have the same deadlines*/
		if(a1.weight == a2.weight){
			pority = 0;
		}else if(a1.weight < a2.weight){
			pority = 1;
		}else if(a1.weight > a2.weight){
			pority = -1;
		}
		
		
		
		return pority;
	}
}
 
