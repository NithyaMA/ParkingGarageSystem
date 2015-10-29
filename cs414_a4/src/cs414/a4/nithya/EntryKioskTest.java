package cs414.a4.nithya;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class EntryKioskTest {
	
	private EntryKiosk entryKiosk;
	private Customer cust1;
	private Register register;

	
	@Before
	public void setUp()
	{
		register= new Register();
		entryKiosk= new EntryKiosk("en1",register);
		cust1= new Customer("john","1245689752","john@gmail.com","aa11");
		
		
		
	}

	@Test
	public void testGenerateTicketAndOpenGate() {
		Ticket t=entryKiosk.generateTicketandOpenGate(cust1,  1);
		assertTrue(t.getTicketStatus().equals(TicketStatus.pending));
		try
		{
			t.getTimeOfEntry().getTime();
		}
		catch(NullPointerException e)
		{
			fail("should not throw a null pointer exception");
		}
		try
		{
			t.getTimeOfExit().getTime();
			fail("should throw a null pointer exception");
		}
		catch(NullPointerException e)
		{
			
		}
		assertTrue(t.getAssignedParkingLot()==1);
		assertTrue(t.getCustomer().equals(cust1));
		assertTrue(entryKiosk.getRegister().equals(t.getRegister()));
		assertTrue(entryKiosk.isEntryGate());
	}

}
