package cs414.a4.nithya;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Register {

	private Set <Ticket> tickets= new HashSet<Ticket>();
	
	public void addTicketToGeneratedTickets(Ticket ticket)
	{
		tickets.add(ticket);
	}
	public void generateHourlyReport(Date date)
	{
		
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
