package cs414.a4.nithya;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Register {

	private Set <Ticket> tickets= new HashSet<Ticket>();
	
	public void addTicketToGeneratedTickets(Ticket ticket)
	{
		tickets.add(ticket);
	}
	
	
	public Set<Ticket> generateReport(String choice,Calendar start )
	{	
		System.out.println("1");
		Calendar stop=(Calendar)start.clone();
		System.out.println("stop is" + stop);
		switch(choice)
		{ case "hourly" :
			System.out.println("entered choice");
			stop.add(Calendar.HOUR, 1);
			break;
			
		case "daily" :
			stop.add(Calendar.HOUR, 24);
			
		case "weekly" :
			
			stop.add(Calendar.DATE, 7);
			
		case "monthly" :
			stop.add(Calendar.MONTH, 1);
		
		}
				
		Set <Ticket> reportTickets= new HashSet<Ticket>();
		
		for(Ticket ticket: tickets)
		{
			Calendar entry= (Calendar)ticket.getTimeOfEntry().clone();
			System.out.println("entry" + entry);
			
			if(ticket.getTicketStatus().equals(TicketStatus.pending))
			{
				if( (entry.getTime().before(start.getTime())))
					System.out.println("aaa");
					reportTickets.add(ticket);
			}
			else
			{
			Calendar exit= (Calendar)ticket.getTimeOfExit().clone();
			System.out.println("exit" + exit);
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
		int sum=0;
		for (startOfMonth.getTime(); startOfMonth.getTime().before(stopOfMonth.getTime()); startOfMonth.add(Calendar.DATE, 1)) 
		    
			{
				
				int occupanyNum= (generateReport("daily", startOfMonth)).size();
				occupancyNumbers[dat]= occupanyNum;  
				dat++;
			}
			
			int closest=0;
			for(int j=0; j<30; j++)
			{
				sum=sum + occupancyNumbers[j];
				float avg= (float) (sum/30.00);
				int find= (int) avg;
				closest = occupancyNumbers[0];
			    int distance = Math.abs(closest - find);
			    for(int i: occupancyNumbers)	
			    {
			       int distanceI = Math.abs(i - find);
			       if(distance > distanceI) 
			       {
			           closest = i;
			           distance = distanceI;
			       }
			    }
			}
			int requireday=0;
				for(int avgDay=0; avgDay<30; avgDay++)
				{
					if (occupancyNumbers[avgDay]==closest)
						requireday= avgDay +1;
				}
			   
			   
			   cal.set(Calendar.DATE, requireday);
			   Calendar cal2 = (Calendar) cal.clone();
			   cal.set(Calendar.HOUR, 24);
			   
			   int [] occupants= new int[24];
			   int hour=0;
			   for ( cal.getTime(); cal.getTime().before(cal2.getTime()); cal.add(Calendar.HOUR, 1)) 
			   {
				   
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
			   return busiestHour;
			   
		}
		
		
			   
		
				
	
	public Set<Ticket> getTickets() {
		return tickets;
	}
	
}
