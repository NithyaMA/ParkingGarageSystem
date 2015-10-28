package cs414.a4.nithya;

import java.util.Calendar;

public class Ticket {
	private static int ticketNumber=0;
	private int ticketReferenceNumber;
	private int assignedParkingLot;
	private Customer customer;
	private Calendar timeOfEntry;
	private Calendar timeOfExit;
	private float parkingRate;
	private TicketStatus ticketStatus;
	private float totalParkingFee;
	private Register register;
	
	public Ticket(Customer customer,int assignedParkingLot, Register register)
	{
		ticketStatus= TicketStatus.pending;
		this.customer= customer;
		this.assignedParkingLot=assignedParkingLot;
		this.parkingRate=10;
		ticketNumber+=1;
		this.ticketReferenceNumber=ticketNumber;
		 Calendar cal = Calendar.getInstance();
		this.timeOfEntry=cal;
		this.register=register;
		this.register.addTicketToGeneratedTickets(this);
		
		
		
	}
	
	

	public void calculateTotalParkingFee() {
		
		long timeDiffInMs= timeOfExit.getTimeInMillis()  -   timeOfEntry.getTimeInMillis();
		System.out.println(timeDiffInMs);
		 float timeDiffInHours =(float) timeDiffInMs/(float)(1000*60*60);
		 System.out.println(timeDiffInHours);
		 System.out.println(parkingRate);
		this.totalParkingFee= (timeDiffInHours * parkingRate);
		
		
	}


	public Calendar getTimeOfEntry() {
		return timeOfEntry;
	}


	public void setTimeOfEntry(Calendar timeOfEntry) {
		this.timeOfEntry = timeOfEntry;
	}


	public Calendar getTimeOfExit() {
		return timeOfExit;
	}


	public void setTimeOfExit(Calendar timeOfExit) {
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
