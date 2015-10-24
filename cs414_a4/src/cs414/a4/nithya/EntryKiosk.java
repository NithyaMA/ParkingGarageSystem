package cs414.a4.nithya;

public class EntryKiosk {
	private int kioskNumber;
	private boolean entryGate;
	
	public EntryKiosk(int number)
	{
		kioskNumber=number;
		entryGate=false;
		
	}
	public void generateTicket(Customer customer,int assignedParkingLot)
	{
		
	}
	 public void openEntryGate()
	 {
		 entryGate=true;
	 }
	 public void closeEntryGate()
	 {
		 entryGate=false;
	 }
	
	

}
