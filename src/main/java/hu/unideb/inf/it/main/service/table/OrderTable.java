package hu.unideb.inf.it.main.service.table;

public class OrderTable {
		
		private Long orderID;
		private String partyName;
		private String userName;
		private String userPhone;
		private String userEmail;
		public String getPartyName() {
			return partyName;
		}
		public void setPartyName(String partyName) {
			this.partyName = partyName;
		}
		public String getUserName() {
			return userName;
		}
		public void setUserName(String userName) {
			this.userName = userName;
		}
		public String getUserPhone() {
			return userPhone;
		}
		public void setUserPhone(String userPhone) {
			this.userPhone = userPhone;
		}
		public String getUserEmail() {
			return userEmail;
		}
		public void setUserEmail(String userEmail) {
			this.userEmail = userEmail;
		}
		public Long getOrderID() {
			return orderID;
		}
		public void setOrderID(Long orderID) {
			this.orderID = orderID;
		}
		
}
