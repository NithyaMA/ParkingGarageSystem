package cs414.a4.nithya;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import cs414.a4.nithya.ExitKiosk.CustomException;

public class Register {

	private Set <Ticket> tickets= new HashSet<Ticket>();
	
	public boolean addTicketToGeneratedTickets(Ticket ticket)
	{
		tickets.add(ticket);
		return true;
	}
	
	public Ticket validateTicket(int ticketReferenceNumber, String vehicleNumber)
	{	
		boolean flag=false;
		Ticket submittedTicket=null;
		
		for(Ticket ticket: tickets)
		{
			if(ticket.getTicketReferenceNumber()==ticketReferenceNumber)
			{	
				flag=true;
				if(ticket.getCustomer().getvehicleNumber().equals(vehicleNumber))
				{
					Calendar cal = Calendar.getInstance();
					ticket.setTimeOfExit(cal);
					ticket.calculateTotalParkingFee();
					submittedTicket=ticket;
				
				break;
				}
				else 
					throw new CustomException("The ticket does not match with the parked vehicle. Ticket is fake");
				
			}
		}
		if(flag==false)
			throw new CustomException("The ticket reference number does not exist. Ticket is fake");
		if (tickets.isEmpty())
			throw new CustomException("There are no vehicles parked inside the garage. Sorry, you have come to the wrong garage.");
		return submittedTicket;
		
	}
	
	public Set<Ticket> generateReport(String choice,Calendar start )
	{	
		
		Calendar stop=(Calendar)start.clone();
		
		switch(choice)
		{ case "hourly" :
			
			stop.add(Calendar.HOUR, 1);
			break;
			
		case "daily" :
			
			stop.add(Calendar.HOUR, 24);
			break;
			
		case "weekly" :
			
			stop.add(Calendar.HOUR, 168);
			break;
			
		case "monthly" :
			
			stop.add(Calendar.MONTH, 1);
			break;
		
		}
				
		Set <Ticket> reportTickets= new HashSet<Ticket>();
		
		for(Ticket ticket: tickets)
		{
			Calendar entry= (Calendar)ticket.getTimeOfEntry().clone();
			
			
			if(ticket.getTicketStatus().equals(TicketStatus.pending))
			{
				if( (entry.getTime().before(stop.getTime())) )
					
					reportTickets.add(ticket);
			}
			else
			{
			Calendar exit= (Calendar)ticket.getTimeOfExit().clone();
			
			if( (entry.getTime().after(start.getTime()) && entry.getTime().before(stop.getTime()))  ||  (exit.getTime().after(start.getTime()) && exit.getTime().before(stop.getTime()))  ||  (entry.getTime().before(start.getTime()) && exit.getTime().after(stop.getTime())) )
				{
				reportTickets.add(ticket);
				}
				
			}
		}
		
		return reportTickets;
	}
	
	
	public int findBusiestHourOfTheMonth(Calendar startOfMonth)
	{
		
		
		Calendar stopOfMonth =(Calendar) startOfMonth.clone();
		Calendar cal = (Calendar) startOfMonth.clone();
		stopOfMonth.set(Calendar.DATE, 30);
		int[] occupancyNumbers= new int[30];
		int dat=0;
		
		for (startOfMonth.getTime(); startOfMonth.getTime().before(stopOfMonth.getTime()); startOfMonth.add(Calendar.DATE, 1)) 
		    
			{
				
				int occupanyNum= (generateReport("daily", startOfMonth)).size();
				occupancyNumbers[dat]= occupanyNum;  
				dat++;
			}
			
		
		
	
			int busiestDay=1;
			
			for(int j=2; j<occupancyNumbers.length; j++)
			{
				if(occupancyNumbers[j]> occupancyNumbers[busiestDay])
				{
					
					busiestDay=j;
				}
			   
			}
			int requireday=busiestDay;
		
			   
			
			   cal.set(Calendar.DATE, requireday);
	
			   int [] occupants= new int[24];
			   int hour=0;
			   for (int hr=0; hr<24; hr++) 
			   {
				   cal.set(Calendar.HOUR, hr);
				  int occupNum= (generateReport("hourly", cal)).size();
					occupants[hour]= occupNum;    
					hour++;
			   }
			   
			   
			   int max=0;
			   int busiestHour = 0;
			   
			   for(int count=0; count < occupants.length ; count++)
			   {
				   
				  if (occupants[count]>max)
				  {
					  max= occupants[count];
					  busiestHour= count;
					  
				  }
			   }
			   return (busiestHour-1);
			   
		}
		
		
			   
		
				
	
	public Set<Ticket> getTickets() {
		return tickets;
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
