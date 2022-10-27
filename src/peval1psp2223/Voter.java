package peval1psp2223;

/**
 * @author Javier Tienda González
 * @version 1.0
 * @info The thread of voters
 */

public class Voter extends Thread{
	
	/**
	 * General methods
	 */
	private GMethods method = new GMethods();
	
	/**
	 * My pipe
	 */
	private ElectoralCollege myCollege;
	
	/**
	 * The key to identify a person
	 */
	private int dni;
	
	/**
	 * To know if a voter can vote
	 */
	private boolean canVote = false;
	
	/**
	 * Class constructor
	 * @param myCollege
	 * @param dni
	 */
	Voter(ElectoralCollege myCollege, int dni){
		this.myCollege = myCollege;
		this.dni = dni;
	}
	
	/**
	 * Method to run the thread
	 */
	public void run() {
		method.println("La persona con dni: " + this.dni + " ha entrado al colegio electoral");
		canVote = this.myCollege.checkDni(this.dni);
		if(canVote) {
			myCollege.vote(this.dni);
		}
		method.println("La persona con dni: " + this.dni + " ha salido al colegio electoral");
	}
	
}
