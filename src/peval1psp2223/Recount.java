package peval1psp2223;

/**
 * @author Javier Tienda González
 * @version 1.0
 * @info The thread of the recount of votes
 */

public class Recount extends Thread {

	/**
	 * General methods
	 */
	private GMethods method = new GMethods();
	
	/**
	 * My pipe
	 */
	private ElectoralCollege myCollege;
	
	/**
	 * To continue the loop if is true
	 */
	private boolean loop = true;
	
	/**
	 * Class constructor
	 * @param myCollege
	 */
	Recount(ElectoralCollege myCollege){
		this.myCollege = myCollege;
	}
	
	/**
	 * Method to run the thread
	 */
	public void run() {
		while (loop) {
            try {
                sleep(10000);
            	this.myCollege.setCounting(true);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            method.printSuccess("Han habido " + myCollege.getNumVotes() + " votos");
        	this.myCollege.setCounting(false);

            if ((myCollege.getNumVotes() == myCollege.getCensus().length)) { 
                method.printSuccess("Han habido en el referendum " + myCollege.getNumVotes() + " votos");
                loop = false;
            }
        }
	}
}
