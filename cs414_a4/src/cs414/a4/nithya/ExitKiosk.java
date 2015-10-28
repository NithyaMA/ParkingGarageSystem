package cs414.a4.nithya;

import java.util.Calendar;

public class ExitKiosk {
	private String kioskNumber;
	private boolean exitGate;
	private Register register;

	
	
	public ExitKiosk(String number, Register register)
	{
		kioskNumber=number;
		exitGate=false;
		this.register= register;
		
	}
	public Ticket validateTicket(int ticketReferenceNumber, String vehicleNumber)
	{	
		boolean flag=false;
		Ticket submittedTicket=null;
		
		for(Ticket ticket: register.getTickets())
		{
			if(ticket.getTicketReferenceNumber()==ticketReferenceNumber)
			{	
				flag=true;
				if(ticket.getCustomer().getvehicleNumber().equals(vehicleNumber))
				{
					 Calendar cal = Calendar.getInstance();
					ticket.setTimeOfExit(cal);
					ticket.calculateTotalParkingFee();
					submittedTicket=ticket;
				
				break;
				}
				else 
					throw new CustomException("The ticket does not match with the parked vehicle. Ticket is fake");
				
			}
			if(flag==false)
				
				throw new CustomException("The ticket reference number does not exist. Ticket is fake");
		
	  
		}
		if (register.getTickets().isEmpty())
			throw new CustomException("There are no vehicles parked inside the garage. Sorry, you have come to the wrong garage.");
		return submittedTicket;
		
	}
	
	
	 public void openExitGate()
	 {
		 exitGate=true;
	 }
	 public void closeExitGate()
	 {
		 exitGate=false;
	 }
	 

		public class CustomException extends RuntimeException
		{
			
			private static final long serialVersionUID = 1L;
			
			public CustomException(String message)
			{
				super(message);
			}
		}


		public boolean isExitGate() {
			return exitGate;
		}
	
	
}
