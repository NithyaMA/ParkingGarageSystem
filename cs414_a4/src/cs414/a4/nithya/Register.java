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
	public Set<Ticket> generateReport(Calendar start, int timeDiff  )
	{
		Set <Ticket> reportTickets= new HashSet<Ticket>();
		Calendar stop= start;
		stop.add(Calendar.MINUTE, timeDiff);
		for(Ticket ticket: tickets)
		{
			Calendar entry= ticket.getTimeOfEntry();
			Calendar exit= ticket.getTimeOfExit();
			
			if( (entry.after(start) && entry.before(stop))  ||  (exit.after(start) && exit.before(stop))  ||  (entry.before(start) && exit.after(stop)) )
			{
				reportTickets.add(ticket);
			}
				
		}
		return reportTickets;
	}
	
	public void generateWeeklyReport(Date date)
	{
		
	}
	public void generateMonthlyReport(Date month)
	{
		
	}
	public void generateDailyReport(Date date)
	{
		
	}
	public void findBusiestHourOfMonth(Date month)
	{
		
	}
	public Set<Ticket> getTickets() {
		return tickets;
	}
	
}
