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
		this.customer= customer;
		this.assignedParkingLot=assignedParkingLot;
	}
	

	public float calculateTotalParkingFee() {
		return (float) 0.0;
	}


	public Date getTimeOfEntry() {
		return timeOfEntry;
	}


	public void setTimeOfEntry(Date timeOfEntry) {
		this.timeOfEntry = timeOfEntry;
	}


	public Date getTimeOfExit() {
		return timeOfExit;
	}


	public void setTimeOfExit(Date timeOfExit) {
		this.timeOfExit = timeOfExit;
	}


	public float getTotalParkingFee() {
		return totalParkingFee;
	}


	public void setParkingRate(float parkingRate) {
		this.parkingRate = parkingRate;
	}


	public void setTicketStatus(TicketStatus ticketStatus) {
		this.ticketStatus = ticketStatus;
	}


	public TicketStatus getTicketStatus() {
		return ticketStatus;
	}
	
	

}
