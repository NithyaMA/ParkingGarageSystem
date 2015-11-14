package cs414.a4.nithya;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

public class Admin {

	private String userName;
	private String password;
	private Register register;
	private ExitKiosk exitKiosk;
	
	
	public Admin(String userName, String password, Register register)
	{
		this.userName=userName;
		this.password=password;
		this.register=register;
	}
	
	public boolean authorizeAdmin(String userName, String password)
	{
		if(  (this.userName.equals(userName)) &&  (this.password.equals(password)) )
				return true;
		else 
			throw new CustomException("Username and password does not match. Please try again");
	}
	
	
	public void  stimulateTimeForEntry(Calendar cal, int ticketNum)
	{
		
		for(Ticket t: this.register.getTickets())
		{
			if(t.getTicketReferenceNumber()==ticketNum)
			{
				t.setTimeOfEntry(cal);
				break;
			}
		}
	
}
	
	public void  stimulateTimeForExit(Calendar cal, int ticketNum)
	{
		for(Ticket t: this.register.getTickets())
		{
			if(t.getTicketReferenceNumber()==ticketNum)
			{
				t.setTimeOfExit(cal);
				break;
			}
		}
	
}
	
	public Ticket helpCustomerToRePrintTicket(String vehicleNumber)
	{
		Ticket rePrintedTicket= null;
		for(Ticket t: this.register.getTickets())
		{
			if(t.getCustomer().getvehicleNumber().equals(vehicleNumber))
			{
				rePrintedTicket= t;
				break;
			}
		}
		return rePrintedTicket;
	
	}
	
	public float lendMoneyToCashlessCustomerToExitGarage(Integer ticketRefNum)
	{
		float loanAmount=(float) 0.0;
		for(Ticket t: this.register.getTickets())
		{
			if(t.getTicketReferenceNumber()==ticketRefNum)
				
			{
				loanAmount=t.getTotalParkingFee();
				
			}
			
		}
		return loanAmount;
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