package cs414.a4.nithya;

public class ExitKiosk {
	private int kioskNumber;
	private boolean exitGate;
	
	
	public ExitKiosk(int number)
	{
		kioskNumber=number;
		exitGate=false;
		
		
	}
	public float acceptTicket(Ticket ticket)
	{
		return ticket.calculateTotalParkingFee();
		
	}
	
	
	 public void openExitGate()
	 {
		 exitGate=true;
	 }
	 public void closeExitGate()
	 {
		 exitGate=false;
	 }
	
	
}
