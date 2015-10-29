package cs414.a4.nithya;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import cs414.a4.nithya.Admin.CustomException;

public class ExitKioskTest {
	
	private Garage garage;
	private EntryKiosk entryKiosk;
	private ExitKiosk exitKiosk;
	private Register register;
	private Admin admin;
	private Customer cust1;
	
	@Before
	public void setUp()
	{
		register= new Register();
		entryKiosk= new EntryKiosk("en1",register);
		exitKiosk= new ExitKiosk("en2", register);
		admin= new Admin("admin", "admin123", register);
		cust1= new Customer("john","1245689752","john@gmail.com","aa11");
		
		
		garage= new Garage("CS414", 3, entryKiosk, exitKiosk, register, admin);
		
		
	}
	
	
	@Test
	public void testValidateTicketWhileGarageIsEmpty() {
		
		
		try
		{
			exitKiosk.validateTicket(1, "a1");
			fail("should throw a custom exception since there are no vehicles in the garage");
		}
		catch(ExitKiosk.CustomException e)
		{
			assertEquals(e.getMessage(), "There are no vehicles parked inside the garage. Sorry, you have come to the wrong garage.");
		}
		
	}


//for testing whether the same ticket generated for the customer is returned.
@Test
public void testValidateTicketForCheckingWhetherSameTicketReturned() {
	
	
	
		Ticket ticket1= garage.enterGarage(cust1);
		int ticketNum = ticket1.getTicketReferenceNumber();
		Ticket ticket2= exitKiosk.validateTicket(ticketNum, "aa11");
		assertEquals(ticket1, ticket2);
	
}

//for testing whether the time of exit is noted when the customer submits ticket for validation
@Test
public void testValidateTicketForCheckingWhetherExitTimeIsRecorded() {
	
	
	
		Ticket ticket1= garage.enterGarage(cust1);
		int ticketNum = ticket1.getTicketReferenceNumber();
		try{
		ticket1.getTimeOfExit().getTime();
		fail("should throw null pointer exception");
		}
		catch(NullPointerException e)
		{
			
		}
		exitKiosk.validateTicket(ticketNum, "aa11");
		try
		{
		ticket1.getTimeOfExit().getTime();
		
		}
		catch(NullPointerException e)
		{
			fail("should not throw a null pointer exception");
		}
	
}



}
