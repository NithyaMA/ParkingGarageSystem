package cs414.a4.nithya;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Garage {
	
	private String name;
	private GarageStatus garageStatus;
	private int totalOccupiedSpaces;
	private int totalUnoccupiedSpaces;
	private Map <Integer, String>parkingLots= new HashMap<Integer, String>();
	
	private EntryKiosk entryKiosk;
	private ExitKiosk exitKiosk;
	private Register register;
	private Admin admin;
	
	
	public Garage(String name, int numberOfParkingLots, EntryKiosk entryKiosk, ExitKiosk exitKiosk, Register register, Admin admin)
	{
		this.name=name;
	
		for(int i=1; i<= numberOfParkingLots; i++)
		{
			parkingLots.put(i, "empty");
		}
		this.totalOccupiedSpaces=0;
        this.totalUnoccupiedSpaces=numberOfParkingLots;
		this.garageStatus= GarageStatus.available;
		this.entryKiosk=entryKiosk;
		this.exitKiosk=exitKiosk;
		this.register=register;
		this.admin=admin;
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
		totalOccupiedSpaces+=1;
		totalUnoccupiedSpaces-=1;
		updateStatus();
		
		return generatedTicket;
		}
		else
			throw new CustomException("The garage is full");
			
	}
	
	
	
	
	public Ticket validateTicketForExitingGarage(int ticketReferenceNumber, String vehicleNumber)
	{
		return register.validateTicket(ticketReferenceNumber,  vehicleNumber);
	
	}
	
	public float payParkingFeeByCash( Ticket ticket, float amount)
	{
		float fee=ticket.getTotalParkingFee();
		Payment payment= new Payment();
		float balanceDue=payment.makePaymentByCash(fee, amount);
		ticket.setTicketStatus(TicketStatus.paid);
		totalOccupiedSpaces-=1;
		totalUnoccupiedSpaces+=1;
		parkingLots.put(ticket.getAssignedParkingLot(),"empty");
		updateStatus();
		exitKiosk.openExitGate();
		return balanceDue;
	}
	
	public boolean payParkingFeeByCard(Ticket ticket, Long cardNumber, Date expiryDate)
	{
		Payment payment= new Payment();
		if(payment.makePaymentByCard(cardNumber, expiryDate))
		{
			ticket.setTicketStatus(TicketStatus.paid);
			totalOccupiedSpaces-=1;
			totalUnoccupiedSpaces+=1;
			parkingLots.put(ticket.getAssignedParkingLot(),"empty");
			updateStatus();
			exitKiosk.openExitGate();
			return true;
		}
		
		 return false;
	}
	
	public Set<Ticket> generateReport(String choice, Calendar start)
	{
		return register.generateReport(choice, start);
	}
	
	public void updateStatus()
	{
		if(parkingLots.containsValue("empty"))
		this.garageStatus= GarageStatus.available;
		else
			this.garageStatus= GarageStatus.full;
	}
	
	public int findBusiestHourOfMonth(Calendar month)
	{
		return register.findBusiestHourOfTheMonth(month);
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
	
	public boolean authorizeAdmin(String userName, String password)
	{
		return this.admin.authorizeAdmin(userName, password);
	}
	
	public void stimulateTime(String testingChoice, Calendar cal, int ticketNum)
	{
		if (testingChoice.equals("entry"))
		{
			admin.stimulateTimeForEntry(cal, ticketNum);
		}
		else if (testingChoice.equals("exit"))
		{
			admin.stimulateTimeForExit(cal, ticketNum);
		}
		else
			throw new CustomException("Testing choice provided is not correct. Please try again");
	}
	public class CustomException extends RuntimeException
	{
		
		private static final long serialVersionUID = 1L;
		
		public CustomException(String message)
		{
			super(message);
		}
	}

	
	public Ticket helpCustomerToReprintTicket(String vehicleNumber)
	{
		return admin.helpCustomerToRePrintTicket(vehicleNumber);
	}
	
	public float lendMoneyToCashlessCustomerToExitGarage(Integer ticketRefNum)
	{
		return admin.lendMoneyToCashlessCustomerToExitGarage(ticketRefNum);
	}
	public String getName() {
		return name;
	}
	public GarageStatus getGarageStatus() {
		return garageStatus;
	}
	public int getTotalOccupiedSpaces() {
		return totalOccupiedSpaces;
	}
	public int getTotalUnoccupiedSpaces() {
		return totalUnoccupiedSpaces;
	}
	public EntryKiosk getEntryKiosk() {
		return entryKiosk;
	}
	public ExitKiosk getExitKiosk() {
		return exitKiosk;
	}
}
