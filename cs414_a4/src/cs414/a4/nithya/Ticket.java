package cs414.a4.nithya;

import java.util.Date;

public class Ticket {
	private static int ticketNumber=0;
	private int ticketReferenceNumber;
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
		this.parkingRate=10;
		ticketNumber+=1;
		this.ticketReferenceNumber=ticketNumber;
		
		
	}
	

	public void calculateTotalParkingFee() {
		
		this.parkingRate=(float) 0.0;
		
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





	public void setParkingRate(float parkingRate) {
		this.parkingRate = parkingRate;
	}


	public void setTicketStatus(TicketStatus ticketStatus) {
		this.ticketStatus = ticketStatus;
	}


	public TicketStatus getTicketStatus() {
		return ticketStatus;
	}


	public void setAssignedParkingLot(int assignedParkingLot) {
		this.assignedParkingLot = assignedParkingLot;
	}


	public void setCustomer(Customer customer) {
		this.customer = customer;
	}


	public void setTotalParkingFee(float totalParkingFee) {
		this.totalParkingFee = totalParkingFee;
	}


	public int getAssignedParkingLot() {
		return assignedParkingLot;
	}


	public float getParkingRate() {
		return parkingRate;
	}


	public  int getTicketReferenceNumber() {
		return ticketReferenceNumber;
	}


	public Customer getCustomer() {
		return customer;
	}


	public float getTotalParkingFee() {
		return totalParkingFee;
	}
	
	

}
