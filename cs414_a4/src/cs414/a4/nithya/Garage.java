package cs414.a4.nithya;

public class Garage {
	
	private String name;
	private GarageStatus garageStatus;
	private int totalOccupiedSpaces;
	private int[] parkingLots;
	private EntryKiosk entryKiosk;
	private ExitKiosk exitKiosk;
	private Register register;
	
	public Garage(String name, int numberOfParkingLots, EntryKiosk entryKiosk, ExitKiosk exitKiosk, Register register)
	{
		this.name=name;

		for(int i=1; i<= numberOfParkingLots; i++)
		{
			this.parkingLots[i]=i;
		}
		this.totalOccupiedSpaces=0;
		this.garageStatus= GarageStatus.available;
		this.entryKiosk=entryKiosk;
		this.exitKiosk=exitKiosk;
		this.register=register;
	}
	public Ticket enterGarage(Customer customer)

	{	
		if(garageStatus.equals(GarageStatus.available))
		{
		int assignedParkingLot=0;
		int totalLots= parkingLots.length;
		for(int i=1; i<=totalLots ; i++)
		{
			if(parkingLots[i]==i)
			{ 
				assignedParkingLot= i;
				break;	
			}
			
		}
		
		Ticket generatedTicket= this.entryKiosk.generateTicketandOpenGate(customer, assignedParkingLot);
		
		return generatedTicket;
		}
		else
			throw new CustomException("The garage is full");
			
	}
	
	public float provideTicketForExitingGarage(Ticket ticket)
	{
		float amountToBePaid=  exitKiosk.acceptTicket(ticket);
		return amountToBePaid;
	}
	
	public void payParkingFee()
	{
		
	}
	
	public void generateReport(int choiceKey)
	{
		
	}
	
	public void updateStatus(GarageStatus status)
	{
		garageStatus=status;
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
