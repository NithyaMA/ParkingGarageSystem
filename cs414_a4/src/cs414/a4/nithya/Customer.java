package cs414.a4.nithya;

public class Customer {
	
	private String name;
	private String phoneNumber;
	private String emailId;
	private String vehicleNumber;
	
	public Customer(String name, String phoneNumber, String emailId, String vehicleNumber)
	{
		this.name=name;
		if(emailId.indexOf('@')==-1)
			throw new CustomException("E mail id is not valid");
		if(emailId.indexOf('.')==-1)
			throw new CustomException("E mail id is not valid");
		this.emailId=emailId;
		if(phoneNumber.length()<6)
			throw new CustomException("Phone number is not valid");
			
		this.phoneNumber=phoneNumber;
		this.vehicleNumber=vehicleNumber;
	}
	public String getName() {
		return name;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public String getEmailId() {
		return emailId;
	}
	
	public String getvehicleNumber() {
		return vehicleNumber;
	}
	
	
	public class CustomException extends RuntimeException
	{
		
		private static final long serialVersionUID = 1L;
		
		public CustomException(String message)
		{
			super(message);
		}
	}
	
}
