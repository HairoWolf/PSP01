package peval1psp2223;

/**
 * @author Javier Tienda González
 * @version 1.0
 * @info Program to get the results from a referendum
 */

public class AppMain {

	public static void main(String[] args) {
		ElectoralCollege myCollege = new ElectoralCollege();
		Recount myRecount = new Recount(myCollege);

        for (int i = 1; i <= 30; i++) {
            Thread Votante = new Thread(new Voter(myCollege, i));
            Votante.start();
        }

        myRecount.start();
	}

}
