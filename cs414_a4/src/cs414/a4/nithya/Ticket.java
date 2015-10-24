package cs414.a4.nithya;

import java.util.Date;

public class Ticket {
	private int assignedParkingLot;
	private Customer customer;
	private Date timeOfEntry;
	private Date timeOfExit;
	private float parkingRate;
	private TicketStatus ticketStatus;
	private float totalParkingFee;
	
	public Ticket(Customer customer,int assignedParkingLot)
	{
		ticketStatus= TicketStatus.pending;
	}
	
	public boolean makePayment(float amount)
	{
		return true;
	}

	public float calculateTotalParkingFee() {
		return (float) 0.0;
	}
	
	

}
