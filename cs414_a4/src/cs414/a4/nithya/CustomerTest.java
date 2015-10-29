package cs414.a4.nithya;

import static org.junit.Assert.*;

import org.junit.Test;

import cs414.a4.nithya.Customer.CustomException;

public class CustomerTest {

	@Test
	public void testInValidEmailId() {
		try{
		Customer cust1= new Customer("john","1245689752","johngmail.com","aa11");
		fail("should throw exception");
	}
		catch(CustomException e)
		{
			assertEquals(e.getMessage(),"E mail id is not valid");
		}
		try{
			Customer cust1= new Customer("john","1245689752","john@gmailcom","aa11");
			fail("should throw exception");
		}
			catch(CustomException e)
			{
				assertEquals(e.getMessage(),"E mail id is not valid");
			}

}
	@Test
	public void testInvalidPhoneNumber()
	{
		try{
			Customer cust1= new Customer("john","12","john@gmail.com","aa11");
			fail("should throw exception");
		}
			catch(CustomException e)
			{
				assertEquals(e.getMessage(),"Phone number is not valid");
			}
	}
	
}
