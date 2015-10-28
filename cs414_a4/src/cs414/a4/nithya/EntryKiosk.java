package cs414.a4.nithya;

public class EntryKiosk {
	private String kioskNumber;
	private boolean entryGate;
	private Register register;
	
	public EntryKiosk(String number, Register register)
	{
		kioskNumber=number;
		entryGate=false;
		this.register=register;
		
	}
	public Ticket generateTicketandOpenGate(Customer customer,int assignedParkingLot)
	{
		Ticket ticket= new Ticket(customer, assignedParkingLot, this.register);
		
		this.openEntryGate();
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


	public boolean isEntryGate() {
		return entryGate;
	}
	
	
	

}
