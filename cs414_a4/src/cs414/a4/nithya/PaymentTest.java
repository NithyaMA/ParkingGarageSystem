package cs414.a4.nithya;

import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import cs414.a4.nithya.Payment.CustomException;

public class PaymentTest {
	
	private Payment payment;

	
	@Before
	public void setUp()
	{
	
		
		payment= new Payment();
		
	}

	@Test
	public void testPaymentByCash() {
		
		assertTrue(payment.makePaymentByCash(0, 0)==0.0);
		try
		{
			payment.makePaymentByCash(10, 5);
			fail("should throw an exception");
		}
		catch(CustomException e)
		{
			assertEquals(e.getMessage(),"Cash provided is less than the parking fee. Transaction is cancelled");
		}
		
		
		assertTrue(payment.makePaymentByCash(10, 15)==5.0);
	}
	
	
	@Test
	public void testPaymentByCardInvalidCard() {
		
		DateFormat format= new SimpleDateFormat("MM/dd/yyyy");
		Date dateOfExpiry=null;
		try {
			dateOfExpiry = format.parse("10/20/2018");
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
		long cardNumber= Long.parseLong("2222222222222222");
		try{
		payment.makePaymentByCard(cardNumber, dateOfExpiry);
		fail("should throw exception");
		}
		catch(cs414.a4.nithya.Payment.CustomException e)
		{
			assertEquals(e.getMessage(),"The card number is not valid. Transaction is cancelled");
		}
	}

	
	@Test
	public void testPaymentByCardInvalidDate() {
		
		DateFormat format= new SimpleDateFormat("MM/dd/yyyy");
		Date dateOfExpiry=null;
		try {
			dateOfExpiry = format.parse("10/20/2014");
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
		long cardNumber= Long.parseLong("5500000000000004");
		try{
		payment.makePaymentByCard(cardNumber, dateOfExpiry);
		fail("should throw exception");
		}
		catch(cs414.a4.nithya.Payment.CustomException e)
		{
			assertEquals(e.getMessage(),"The validity of the card is expired. Transaction is cancelled");
		}
	}
	
	
	@Test
	public void testPaymentByCard() {
		
		DateFormat format= new SimpleDateFormat("MM/dd/yyyy");
		Date dateOfExpiry=null;
		try {
			dateOfExpiry = format.parse("10/20/2019");
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
		long cardNumber= Long.parseLong("5500000000000004");
		try{
		payment.makePaymentByCard(cardNumber, dateOfExpiry);
		
		}
		catch(cs414.a4.nithya.Payment.CustomException e)
		{
			fail("should not throw exception");
		}
	}
}
