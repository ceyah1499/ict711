/**
 * Function name: Member file
 * 
 * Description: 
 * 1. The method fetch the member file with basic information in a predefined format.
 * 
 * It contains variables, constructor, getters and setters
 * 
 * 
 */


public class Member

{
	// local variables
	private String name;
	private String birthday;
	private String passType;
	private String mobile;
	private double fee;
	// constructor
	public Member(String name, String birthday, String passType, String mobile, double fee) 
	{
		this.name = name;
		this.birthday = birthday;
		this.passType = passType;
		this.mobile = mobile;
		this.fee = fee;
	}
	// getters and setters
	public String getName() 
	{
		return name;
	}
	
	public void setName(String name) 
	{
		this.name = name;
	}

	public String getBirthday() 
	{
		return birthday;
	}
	
	public void setBirthday(String birthday) 
	{
		this.birthday = birthday;
	}
	
	public String getPassType() 
	{
		return passType;
	}

	public void setPassType(String passType) 
	{
		this.passType = passType;
	}

	public String getMobile() 
	{
		return mobile;
	}

	public void setMobile(String mobile) 
	{
		this.mobile = mobile;
	}

	public double getFee() 
	{
		return fee;
	}

	public void setFee(double fee) 
	{
		this.fee = fee;
	}
}
