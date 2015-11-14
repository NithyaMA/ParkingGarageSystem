package cs414.a4.nithya;

import java.util.Calendar;

public class ExitKiosk {
	private String kioskNumber;
	private boolean exitGate;
	private Register register;

	
	
	public ExitKiosk(String number, Register register)
	{
		kioskNumber=number;
		exitGate=false;
		this.register= register;
		
	}
	
	
	 public void openExitGate()
	 {
		 exitGate=true;
	 }
	 public void closeExitGate()
	 {
		 exitGate=false;
	 }
	 

		public class CustomException extends RuntimeException
		{
			
			private static final long serialVersionUID = 1L;
			
			public CustomException(String message)
			{
				super(message);
			}
		}


		public boolean isExitGate() {
			return exitGate;
		}
	
	
}
