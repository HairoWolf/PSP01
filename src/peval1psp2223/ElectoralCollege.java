package peval1psp2223;

import java.util.ArrayList;
import java.util.Arrays;
import static java.lang.Thread.sleep;

/**
 * @author Javier Tienda González
 * @version 1.0
 * @info The pipe of the program
 */

public class ElectoralCollege {
	
	/**
	 * General methods
	 */
	private GMethods method = new GMethods();
	
	/**
	 * The dni of the people that can vote
	 */
	private Integer[] census = {1, 2, 3, 4, 6, 8, 10, 11, 13, 14, 15, 18, 19, 20, 22, 23, 24, 28, 29, 30};
	
	/**
	 * Arraylist to save the order of arrival
	 */
	private ArrayList<Integer> queue = new ArrayList<>();
	
	/**
     * Integer to count how many people have voted
     */
    private int numVotes = 0;
    
    /**
     * Integer to count how many people have voted
     */
    private boolean isCounting = false;

	/**
	 * Class constructor without params
	 */
	ElectoralCollege(){
		method.println("El colegio electoral acaba de abrir");
	}
	
	/**
	 * Method to check if the dni is contained in the array of census
	 * @param dni
	 * @return true/false if the dni is on the census
	 */
	public boolean checkDni(int dni) {
		if (Arrays.asList(census).contains(dni)) {
            method.printSuccess("La persona con DNI: " + dni + " puede votar");
            addToQueue(dni);
            return true;
        } 
		else {
            method.printError("La persona con DNI: " + dni + " no puede votar");
            return false;
        }
	}
	
	/**
	 * Method to add person to the queue
	 * @param dni
	 */
	private synchronized void addToQueue(int dni) {
        this.queue.add(dni);
        method.println("La persona con DNI: " + dni + " se ha puesto en la cola || Posicion en cola: " + this.queue.indexOf(dni));
    }
	
	/**
	 * Method to vote with conditions to limit the flow of threads
	 * @param dni
	 */
    public synchronized void vote(int dni) {
        while (!(dni == this.queue.get(0)) || isCounting()) {
            method.printError("Se ha intentado colar la persona con DNI: " + dni);
            try {
                wait();
            } 
            catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        method.println("----Comienza a votar a persona con DNI: " + dni + "----");
        try {
        	sleep((int) (Math.random() * 3000 + 1000));
        } 
        catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        numVotes++;
        method.println("----Termina de votar a persona con DNI: " + dni + "----");
        this.queue.remove(0);
        notifyAll();
    }
    

	/**
	 * Getters and setters
	 * @return
	 */
	public Integer[] getCensus() {
		return census;
	}

	public void setCensus(Integer[] census) {
		this.census = census;
	}

	public int getNumVotes() {
		return numVotes;
	}

	public void setNumVotes(int numVotes) {
		this.numVotes = numVotes;
	}

	public boolean isCounting() {
		return isCounting;
	}

	public void setCounting(boolean isCounting) {
		this.isCounting = isCounting;
	}
	
}
