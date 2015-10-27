package cs414.a4.nithya;

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
	public float acceptTicket(int ticketReferenceNumber, String liscenceNumber)
	{	
		boolean flag=false;
		float totalParkingFee=(float) 0.0;
		for(Ticket ticket: register.getTickets())
		{
			if(ticket.getTicketReferenceNumber()==ticketReferenceNumber)
			{	
				flag=true;
				if(ticket.getCustomer().getLiscenceNumber().equals(liscenceNumber))
				{
				totalParkingFee=ticket.calculateTotalParkingFee();
				break;
				}
				else 
					throw new CustomException("The ticket does not match with the parked vehicle. Ticket is fake");
				
			}
			if(flag==false)
				
				throw new CustomException("The ticket reference number does not exist. Ticket is fake");
		
	  
		}
		return totalParkingFee;
		
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
	
	
}
