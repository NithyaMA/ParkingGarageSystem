package cs414.a4.nithya;

public class ExitKiosk {
	private int kioskNumber;
	private boolean exitGate;
	private Register register;
	
	
	public ExitKiosk(int number, Register register)
	{
		kioskNumber=number;
		exitGate=false;
		this.register= register;
		
	}
	public float acceptTicket(Ticket ticket)
	{
	    if(register.getTickets().contains(ticket))
	    {
		return ticket.calculateTotalParkingFee();
	    }
	    else
	    	throw new CustomException("The ticket is fake");
		
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
