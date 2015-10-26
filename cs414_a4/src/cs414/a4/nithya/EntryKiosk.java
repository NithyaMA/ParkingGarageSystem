package cs414.a4.nithya;

public class EntryKiosk {
	private int kioskNumber;
	private boolean entryGate;
	
	public EntryKiosk(int number)
	{
		kioskNumber=number;
		entryGate=false;
		
	}
	public Ticket generateTicketandOpenGate(Customer customer,int assignedParkingLot)
	{
		Ticket ticket= new Ticket(customer, assignedParkingLot);
		this.openEntryGate();
		try{
		Thread.sleep(3000);
		this.closeEntryGate();
		}
		catch (InterruptedException ie)
		{
			throw new CustomException(ie.getMessage());
		}
		return ticket;
	}
	
	 public void openEntryGate()
	 {
		 entryGate=true;
	 }
	 
	 public void closeEntryGate()
	 {
		 entryGate=false;
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
