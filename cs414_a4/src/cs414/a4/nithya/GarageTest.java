package cs414.a4.nithya;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import cs414.a4.nithya.Admin.CustomException;

public class GarageTest {
	
	private Garage garage;
	private EntryKiosk entryKiosk;
	private ExitKiosk exitKiosk;
	private Register register;
	private Admin admin;
	private Customer cust1;
	private Customer cust2;
	private Customer cust3;
	private Customer cust4;

	
	@Before
	public void setUp()
	{
		register= new Register();
		entryKiosk= new EntryKiosk("en1",register);
		exitKiosk= new ExitKiosk("en2", register);
		admin= new Admin("admin", "admin123", register);
		cust1= new Customer("john","1245689752","john@gmail.com","aa11");
		cust2= new Customer("jay","1245649765","jay@gmail.com","aa12");
		cust3= new Customer("jung","1245689769","jung@gmail.com","aa13");
		cust4= new Customer("jose","7245689769","jose@gmail.com","aa14");
		
		garage= new Garage("CS414", 3, entryKiosk, exitKiosk, register, admin);
		
	}
	@Test
	public void testEnterGarage() {
		
		//test for checking the updates status of garage
		garage.enterGarage(cust1);
		assertTrue(garage.getGarageStatus().equals(GarageStatus.available));
		assertTrue(garage.getTotalOccupiedSpaces()==1);
		assertTrue(garage.getTotalUnoccupiedSpaces()==2);
		
		//test for checking the condition of entry gate
		garage.enterGarage(cust2);
		assertTrue(entryKiosk.isEntryGate());
		
		//test for checking whether the garage status is full
		garage.enterGarage(cust3);
		assertTrue(garage.getGarageStatus().equals(GarageStatus.full));
		
		//test for car entering while garage is full
		try{
		garage.enterGarage(cust4);
		fail("Should throw custom exception");
		}
		catch(Garage.CustomException e)
		{
			assertEquals(e.getMessage(), "The garage is full");
		}
		
	}
	
	
	@Test
	public void testValidateTicketForExitingGarage()
	{
		Ticket t=garage.enterGarage(cust1);
		
		int ticketNum= t.getTicketReferenceNumber();
			
		
		//test for a ticket with wrong vehicle number
		try{
			garage.validateTicketForExitingGarage(ticketNum, "aa");
			fail("should throw an exception");
			}
			catch(ExitKiosk.CustomException e)
			{
				assertEquals(e.getMessage(), "The ticket does not match with the parked vehicle. Ticket is fake");
			}
		
		
		
		//test for a ticket with wrong reference number
				try{
					garage.validateTicketForExitingGarage(00, "aa");
					fail("should throw an exception");
					}
					catch(ExitKiosk.CustomException e)
					{
						assertEquals(e.getMessage(), "The ticket reference number does not exist. Ticket is fake");
					}
				
				
		//test for a correct ticket
		try{
		garage.validateTicketForExitingGarage(ticketNum, "aa11");
		
		}
		catch(Exception e)
		{
			fail("should not throw an exception");
		}
	
		
	}
	
	@Test
	public void testactivateSensor()
	{
		garage.activateSensor("entry");
		assertFalse(entryKiosk.isEntryGate());
		garage.activateSensor("exit");
		assertFalse(exitKiosk.isExitGate());
	}

}
