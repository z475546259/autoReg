package util;

public class cnUser {
	public String telephone;
	public String password;
	public String cnuserID;
	public String accept_secret;
	public String deviceID;
	public String user_agent;
	public int score;
	public int earn;
	public String user_name;
	public String randomPwd;
	public cnUser() {
		super();
	}

	public cnUser(String telephone, String password, String cnuserID, String accept_secret, String deviceID,
			String user_agent, String user_name) {
		super();
		this.telephone = telephone;
		this.password = password;
		this.cnuserID = cnuserID;
		this.accept_secret = accept_secret;
		this.deviceID = deviceID;
		this.user_agent = user_agent;
		this.user_name = user_name;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getUser_agent() {
		return user_agent;
	}
	public void setUser_agent(String user_agent) {
		this.user_agent = user_agent;
	}
	public String getDeviceID() {
		return deviceID;
	}
	public void setDeviceID(String deviceID) {
		this.deviceID = deviceID;
	}
	public String getAccept_secret() {
		return accept_secret;
	}
	public void setAccept_secret(String accept_secret) {
		this.accept_secret = accept_secret;
	}
	public String getCnuserID() {
		return cnuserID;
	}
	public void setCnuserID(String cnuserID) {
		this.cnuserID = cnuserID;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getEarn() {
		return earn;
	}

	public void setEarn(int earn) {
		this.earn = earn;
	}

	public String getRandomPwd() {
		return randomPwd;
	}

	public void setRandomPwd(String randomPwd) {
		this.randomPwd = randomPwd;
	}
}
