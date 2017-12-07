package tests;

import java.sql.SQLException;
import java.util.ArrayList;

import models.Reservation;
import models.ReservationDataAccessObject;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ReservationDataAccessObject reservations = new ReservationDataAccessObject() ;
		try {
			ArrayList<Reservation> reservationsDS = reservations.getReservations();
			for( Reservation r: reservationsDS ) {
				System.out.println(r.getRoom());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
