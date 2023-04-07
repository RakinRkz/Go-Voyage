package newproject;

public class PaymentInfo {

    String userID;
    int paymentID;
    String amount_paid,payment_date;

    public PaymentInfo(int paymentID, String amount_paid, String payment_date) {
        this.paymentID = paymentID;
        this.amount_paid = amount_paid;
        this.payment_date = payment_date;
    }

    public PaymentInfo() {
    }
 
    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public int getPaymentID() {
        return paymentID;
    }

    public void setPaymentID(int paymentID) {
        this.paymentID = paymentID;
    }

    public String getAmount_paid() {
        return amount_paid;
    }

    public void setAmount_paid(String amount_paid) {
        this.amount_paid = amount_paid;
    }

    public String getPayment_date() {
        return payment_date;
    }

    public void setPayment_date(String payment_date) {
        this.payment_date = payment_date;
    }

    
    
    
    public static void main(String args[]) {
        // TODO code application logic here
    }
}
