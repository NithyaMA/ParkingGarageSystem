package cs414.a4.nithya;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;

public class ParkingGarage {

	public static void main(String[] args) {
		
			
		Register register= new Register();
		EntryKiosk entryKiosk= new EntryKiosk("en1",register);
		ExitKiosk exitKiosk= new ExitKiosk("en2", register);
		Admin admin= new Admin("admin", "admin123", register);
		
		
		Garage garage= new Garage("CS414", 2, entryKiosk, exitKiosk, register, admin);
		
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		
		System.out.println("Welcome to " + garage.getName() +" Parking Garage");
		
		Outer:
			while(true){
			
		
			System.out.println("Garage status : " + garage.getGarageStatus());
			System.out.println("Total Occupied Spaces : " + garage.getTotalOccupiedSpaces());
			System.out.println("Total Unoccupied Spaces : " + garage.getTotalUnoccupiedSpaces());
		System.out.println("We have the following options. Please enter the number of your choice");
		System.out.println("1. Enter Garage");
		System.out.println("2. Exit Garage");
		System.out.println("3. Generate Reports(only for authorized pesons)"); 
		System.out.println("4. Stuck in Garage. Need help");
		System.out.println("5. Exit Menu");
	
		Inner:
		while(true){
		
		try{
		
		int input1= Integer.parseInt(in.readLine());
		switch(input1)
		{
		case 1:
		{
			if(garage.getGarageStatus().equals(GarageStatus.full))
				System.out.println("Sorry, You cannot enter garage. Garage is full");
			else{
			System.out.println("Please enter your name");
			String name=in.readLine();
			System.out.println("Please enter your phone number");
			String phoneNumber=in.readLine();
			System.out.println("Please enter your email id");
			String emailId=in.readLine();
			System.out.println("Please enter the number on the number plate of the vehicle to be parked");
			String vehicleNumber=in.readLine();
			Customer customer= new Customer(name,phoneNumber,emailId,vehicleNumber);
			Ticket ticket=garage.enterGarage(customer);
			System.out.println("A ticket has been generated. Please use the same ticket for exiting the garage");
			System.out.println("*********************************");
			System.out.println("TICKET");
			System.out.println("*********************************");
			System.out.println("Ticket Number                            " + ticket.getTicketReferenceNumber());
			System.out.println("Assigned Lot                             " + ticket.getAssignedParkingLot());
			System.out.println(("Time of entry                           " + ticket.getTimeOfEntry().getTime()));
			System.out.println("Parking Rate per Hour                    " + ticket.getParkingRate() + "$");
			System.out.println("*********************************");
			System.out.println("Please pay the ticket when you leave the garage");
			System.out.println("Thank You, Have a great parking!!!");
			System.out.println("***********************************");
			System.out.println("Entry gate is opened, Please park your vehicle in the assigned parking number");
			if(garage.getEntryKiosk().isEntryGate())
				System.out.println("ENTRY GATE IS OPENED");
			try{
			Thread.sleep(3000);
			garage.activateSensor("entry");
			}
			catch(InterruptedException ie)
			{
				ie.printStackTrace();
			}
			if(!garage.getEntryKiosk().isEntryGate())
					System.out.println("ENTRY GATE IS CLOSED");
			}
			break Inner;
			
		
		}
		
		case 2:
		{
			
			System.out.println("You should provide the ticket generated at the entrance for exiting the garage");
			System.out.println("Please provide the ticket reference number");
		    int ticketReferenceNumber= Integer.parseInt(in.readLine());
		    System.out.println("Please provide the number of the vehicle parked");
		    String liscenceNumber= in.readLine();
		    Ticket submittedTicket= garage.validateTicketForExitingGarage(ticketReferenceNumber, liscenceNumber);
		    System.out.println(String.format("%.2f", submittedTicket.getTotalParkingFee()) + " $ is the total amount to be paid for parking");
			System.out.println("Please enter your choice of payment: 1. Card  2. Cash");
			int choiceOfPayment= Integer.parseInt(in.readLine());
			if(choiceOfPayment==1)
			{
				System.out.println("Please enter the card number");
				Long cardNumber= Long.parseLong(in.readLine());
				System.out.println("Please enter the date of expiry in the format MM/dd/yyyy such as 03/13/2015");
				DateFormat format= new SimpleDateFormat("MM/dd/yyyy");
				Date dateOfExpiry=null;
				try {
					dateOfExpiry = format.parse(in.readLine());
				} catch (ParseException e) {
					
					e.printStackTrace();
				}
				if(garage.payParkingFeeByCard(submittedTicket, cardNumber, dateOfExpiry))
					System.out.println("The transaction is successfull. The exit gate will be opened.");
			
			}
			else if(choiceOfPayment==2)
			{
			System.out.println("Please provide the cash to pay");	
			Float amount= Float.parseFloat(in.readLine());
			System.out.println("The balance due is " + String.format("%.2f",garage.payParkingFeeByCash(submittedTicket, amount)) + "$");
			System.out.println("The transaction is successfull. Exit gate will be opened");
			
			}
			if(garage.getExitKiosk().isExitGate())
				System.out.println("EXIT GATE IS OPENED");
			try{
			Thread.sleep(3000);
			garage.activateSensor("exit");
			}
			catch(InterruptedException ie)
			{
				ie.printStackTrace();
			}
			if(! garage.getExitKiosk().isExitGate())
					System.out.println("EXIT GATE IS CLOSED");
			
			break Inner;
		}
		
		case 3:
		{
			System.out.println("Please enter the username");
			String userName= in.readLine();
			System.out.println("Please ener the password");
			String password= in.readLine();
			if(garage.authorizeAdmin(userName, password))
			{
				
				while(true)
				{
				System.out.println("Do you want to change the entry and exit times of cars for testing purposes? Please enter yes or no");
				String testingChoice= in.readLine();
				if(testingChoice.equals("yes"))
				{
					System.out.println("Please type the ticket number of the car you wish to change");
					int num= Integer.parseInt(in.readLine());
					System.out.println("Please enter 1 to change the entry time and 2 to change the exit time ");
					int ch= Integer.parseInt(in.readLine());
					System.out.println("Please provide the date you wish to change in the form of 'dd-M-yyyy HH:mm:ss'  such as '31-08-1982 13:20:56'");
					String str= in.readLine();
					SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy HH:mm:ss");
					Date date= sdf.parse(str);
					Calendar call= Calendar.getInstance();
					call.setTime(date);
					if(ch==1)
						{garage.stimulateTime("entry", call, num);
						System.out.println("Changes made succesfully");
						}
					else if(ch==2)
						{garage.stimulateTime("exit", call, num);
						System.out.println("changes made successfuly");
						}
					else
						break;
					
				}
				else
					break;
				}
				while (true) {
				System.out.println("Please select the report to be generated");
				System.out.println("1. Hourly Report Generation");
				System.out.println("2. Daily Report Generation");
				System.out.println("3. Weekly Report Generation");
				System.out.println("4. Monthly Report Generation");
				System.out.println("5. Find the busiest hour of an average day of a month");
				System.out.println("6. Reprint ticket to release customer who lost the ticket");
				System.out.println("7. Lend money to help customer who can't pay");
				System.out.println("8. Exit from the Administartor options");
				int choice=Integer.parseInt( in.readLine());
				AdminLoop:
					while(true){
				if(choice==1)
				{
					System.out.println("Please provide the starting hour in the form of 'dd-M-yyyy HH:mm:ss'  such as '31-08-1982 13:20:56'");
					String str= in.readLine();
					
					SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy HH:mm:ss");
					Date date= sdf.parse(str);
					Calendar start= Calendar.getInstance();
					start.setTime(date);
			
					Set<Ticket> reportTickets = garage.generateReport("hourly", start);
					System.out.println("Number of cars during the time : " + reportTickets.size());
					System.out.println("The name of customers and their vehicle numbers are as follows");
					for(Ticket t: reportTickets)
					{
						System.out.println(t.getCustomer().getName()  + ":" + t.getCustomer().getvehicleNumber());
						
					}
						
				}
				else if(choice==2)
				{
					System.out.println("Please provide the required date in the form of 'dd-M-yyyy'  such as '31-08-1982'");
					String str= in.readLine();
					SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy");
					Date date= sdf.parse(str);
					Calendar start= Calendar.getInstance();
					start.setTime(date);
					Set<Ticket> reportTickets=garage.generateReport("daily", start);
					System.out.println("Number of cars during the time : " + reportTickets.size());
					System.out.println("The name of customers and their vehicle numbers are as follows");
					for(Ticket t: reportTickets)
					{
						System.out.println(t.getCustomer().getName()  + ":" + t.getCustomer().getvehicleNumber());
						
					}
				}
				
				else if (choice==3)
				{
					System.out.println("Please provide the starting date of the week in the form of 'dd-M-yyyy'  such as '31-08-1982'");
					String str= in.readLine();
					SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy");
					Date date= sdf.parse(str);
					Calendar start= Calendar.getInstance();
					start.setTime(date);
					Set<Ticket> reportTickets=garage.generateReport("weekly", start);
					System.out.println("Number of cars during the time : " + reportTickets.size());
					System.out.println("The name of customers and their vehicle numbers are as follows");
					for(Ticket t: reportTickets)
					{
						System.out.println(t.getCustomer().getName()  + ":" + t.getCustomer().getvehicleNumber());
						
					}
					
				}
				else if(choice==4)
				{
					System.out.println("Please provide the month in the form of 'M-yyyy'  such as '08-1982'");
					String str= in.readLine();
					SimpleDateFormat sdf = new SimpleDateFormat("M-yyyy");
					Date date= sdf.parse(str);
					Calendar start= Calendar.getInstance();
					start.setTime(date);
					Set<Ticket> reportTickets=garage.generateReport("monthly", start);
					System.out.println("Number of cars during the time : " + reportTickets.size());
					System.out.println("The name of customers and their vehicle numbers are as follows");
					for(Ticket t: reportTickets)
					{
						System.out.println(t.getCustomer().getName()  + ":" + t.getCustomer().getvehicleNumber());
						
					}
				}
				else if(choice==5)
				
				{
		
					System.out.println("Please provide the month in the form of 'M-yyyy'  such as '08-1982'");
					String str= in.readLine();
					SimpleDateFormat sdf = new SimpleDateFormat("M-yyyy");
					Date date= sdf.parse(str);
					Calendar start= Calendar.getInstance();
					start.setTime(date);
					int hour=garage.findBusiestHourOfMonth(start);
					System.out.println("Busiest hour is from " + hour + " to " + (hour+1));
				}
				else if(choice==6)
				{
					System.out.println("Please enter the vehicle number of the customer you wish to release from garage");
					String str= in.readLine();
					Ticket t=garage.helpCustomerToReprintTicket(str);
					System.out.println("Customer's ticket reference number is " + t.getTicketReferenceNumber());
					System.out.println("Customer can exit the garage now by selecting the exit option");
					
				}
				else if(choice==7)
				{
					System.out.println("Please enter the ticket reference number");
					Integer ticketNum= Integer.parseInt(in.readLine());
					Float loanAmount= garage.lendMoneyToCashlessCustomerToExitGarage(ticketNum);
					System.out.println("You are authorized to lend " + loanAmount + " dollars to the customer for help");
					
				}
				else if(choice==8)
				{
					System.out.println("Thank you");
					break Inner;
					
				}
				else
					{
					System.out.println("Invalid choice");
					
					}
			
			
			break AdminLoop;
			
			}
				}
				
		}
			break Inner;
		}
		
		case 4:
		{
			System.out.println("Enter 1 if you have lost the ticket");
			System.out.println("Enter 2 if you dont have cash to pay");
			Integer c= Integer.parseInt(in.readLine());
			if(c==1)
				System.out.println("Don't worry. Admiistartor can reprint the ticket. Please be careful the next time");
			else if(c==2)
				System.out.println("Don't worry. Administartor can lend you the parking fee. Please return back the money in one week");
			else
				System.out.println("Invalid choice");
			break Inner;
		}
		case 5:
		{
			
			System.out.println("Garage status " + garage.getGarageStatus());
			System.out.println("Total Occupied Spaces " + garage.getTotalOccupiedSpaces());
			System.out.println("Total Unoccupied Spaces " + garage.getTotalUnoccupiedSpaces());
			System.out.println("Thank you. Please come again");
			break Outer ;
		}
		
		
		default:
		{
			System.out.println("Not a valid option. Please try again");
			break Inner;
		}
			
			
		
		}}
		
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			break;
		}}}
		}
		
		
}


	


