package cs414.a4.nithya;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Garage {
	
	private String name;
	private GarageStatus garageStatus;
	private int totalOccupiedSpaces;
	private Map <Integer, String>parkingLots= new HashMap<Integer, String>();
	
	private EntryKiosk entryKiosk;
	private ExitKiosk exitKiosk;
	private Register register;
	
	
	public Garage(String name, int numberOfParkingLots, EntryKiosk entryKiosk, ExitKiosk exitKiosk, Register register)
	{
		this.name=name;
	
		for(int i=1; i<= numberOfParkingLots; i++)
		{
			parkingLots.put(i, "empty");
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
		
		for(int key: parkingLots.keySet())
		{
			if(parkingLots.get(key).equals("empty"))
			{ 
				assignedParkingLot= key;
				parkingLots.put(key, "full");
				break;	
			}
			
		}
		
		Ticket generatedTicket= this.entryKiosk.generateTicketandOpenGate(customer, assignedParkingLot);
		
		return generatedTicket;
		}
		else
			throw new CustomException("The garage is full");
			
	}
	
	
	
	
	public Ticket validateTicketForExitingGarage(int ticketReferenceNumber, String liscenceNumber)
	{
		return exitKiosk.validateTicket(ticketReferenceNumber,  liscenceNumber);
	
	}
	
	public float payParkingFeeByCash( Ticket ticket, float amount)
	{
		Payment payment= new Payment();
		float balanceDue=payment.makePaymentByCash(ticket.getTotalParkingFee(), amount);
		exitKiosk.openExitGate();
		return balanceDue;
	}
	
	public boolean payParkingFeeByCard(Ticket ticket, Long cardNumber, Date expiryDate)
	{
		Payment payment= new Payment();
		if(payment.makePaymentByCard(cardNumber, expiryDate))
		{
			exitKiosk.openExitGate();
			return true;
		}
		
		 return false;
	}
	
	public void generateReport(int choiceKey)
	{
		
	}
	
	public void updateStatus(GarageStatus status)
	{
		garageStatus=status;
	}
	
	public void activateSensor(String choiceOfGate)
	{
		if (choiceOfGate.equals("entry"))
		{
			entryKiosk.closeEntryGate();
		}
		else if(choiceOfGate.equals("exit"))
		{
			exitKiosk.closeExitGate();
		}
	}
	
	public class CustomException extends RuntimeException
	{
		
		private static final long serialVersionUID = 1L;
		
		public CustomException(String message)
		{
			super(message);
		}
	}

	public String getName() {
		return name;
	}
}
