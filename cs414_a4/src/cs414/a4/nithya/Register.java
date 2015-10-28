package cs414.a4.nithya;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Register {

	private Set <Ticket> tickets= new HashSet<Ticket>();
	
	public boolean addTicketToGeneratedTickets(Ticket ticket)
	{
		tickets.add(ticket);
		return true;
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
				if( (entry.getTime().before(stop.getTime()))  )
					
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
	
}
