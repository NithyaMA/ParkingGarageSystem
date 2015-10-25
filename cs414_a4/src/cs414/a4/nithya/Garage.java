package cs414.a4.nithya;

public class Garage {
	
	private String name;
	private GarageStatus garageStatus;
	private int totalOccupiedSpaces;
	private int[] availableParkingLots;
	private EntryKiosk entryKiosk;
	private ExitKiosk exitKiosk;
	private Register register;
	
	public Garage(String name, int numberOfParkingLots, EntryKiosk entryKiosk, ExitKiosk exitKiosk, Register register)
	{
		this.name=name;

		for(int i=1; i<= numberOfParkingLots; i++)
		{
			availableParkingLots[i]=i;
		}
		this.totalOccupiedSpaces=0;
		this.garageStatus= GarageStatus.available;
		this.entryKiosk=entryKiosk;
		this.exitKiosk=exitKiosk;
		this.register=register;
	}
	public void enterGarage(Customer customer)
	{
		
	}
	
	public float provideTicketForExitingGarage(Ticket ticket)
	{
		return exitKiosk.acceptTicket(ticket);
	}
	public void payParkingFee(int choiceOfParking)
	{
		
	}
	
	public void generateReport(int choiceKey)
	{
		
	}
	
	public void updateStatus(GarageStatus status)
	{
		garageStatus=status;
	}
	
	public void activateSensor(String gate)
	{
		if (gate.equals("entry"))
		{
			entryKiosk.closeEntryGate();
		}
		else if(gate.equals("exit"))
		{
			exitKiosk.closeExitGate();
		}
	}
}
