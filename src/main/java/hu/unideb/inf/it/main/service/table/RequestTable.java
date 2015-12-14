package hu.unideb.inf.it.main.service.table;

public class RequestTable {

	private Long requestID;
	private String userName;
	private String request;
	private String userEmail;
	private String userPhone;
	private String state;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getRequest() {
		return request;
	}
	public void setRequest(String request) {
		this.request = request;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Long getRequestID() {
		return requestID;
	}
	public void setRequestID(Long requestID) {
		this.requestID = requestID;
	}
	
	
}
