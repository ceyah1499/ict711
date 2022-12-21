public class Member {
	
	private String name;
	private String birthday;
	private String passType;
	private String mobile;
	private double fee;
	
	public Member(String name, String birthday, String passType, String mobile, double fee) {
		this.name = name;
		this.birthday = birthday;
		this.passType = passType;
		this.mobile = mobile;
		this.fee = fee;
	}
	
	public String getName() {
		return name;
	}

	public String getBirthday() {
		return birthday;
	}
	
	public String getPassType() {
		return passType;
	}

	public void setPassType(String passType) {
		this.passType = passType;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public double getFee() {
		return fee;
	}

	public void setFee(double fee) {
		this.fee = fee;
	}
	
}
