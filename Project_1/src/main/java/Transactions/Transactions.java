package Transactions;

import net.bytebuddy.implementation.bind.annotation.Default;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.UpdateTimestamp;

import javax.naming.Reference;
import javax.persistence.*;
import java.text.DecimalFormat;
import java.util.Date;

@Entity
public class Transactions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int TransactionID;
    @Column(name = "employeeEmail", columnDefinition = "VARCHAR(255) REFERENCES users(Email)")
    private String employeeEmail;
    private double reimbursementAmount;
    @Column(name = "managerEmail", columnDefinition = "VARCHAR(255) REFERENCES users(Email)")
    private String managerEmail;
    private String status;
    //@CreationTimestamp
    //@Temporal(TemporalType.TIMESTAMP)
    private String timestamp;
    private String notify;
    private String proof;

    @Override
    public String toString() {
        return "Transactions{" +
                "TransactionID=" + TransactionID +
                ", employeeEmail='" + employeeEmail + '\'' +
                ", reimbursementAmount=" + reimbursementAmount +
                ", managerEmail='" + managerEmail + '\'' +
                ", status='" + status + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", notify='" + notify + '\'' +
                ", proof='" + proof + '\'' +
                '}';
    }

    public int getTransactionID() {
        return TransactionID;
    }

    public void setTransactionID(int transactionID) {
        TransactionID = transactionID;
    }

    public String getEmployeeEmail() {
        return employeeEmail;
    }

    public void setEmployeeEmail(String employeeEmail) {
        this.employeeEmail = employeeEmail;
    }

    public double getReimbursementAmount() {
        return reimbursementAmount;
    }

    public void setReimbursementAmount(double reimbursementAmount) {
        this.reimbursementAmount = reimbursementAmount;
    }

    public String getManagerEmail() {
        return managerEmail;
    }

    public void setManagerEmail(String managerEmail) {
        this.managerEmail = managerEmail;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getNotify() {
        return notify;
    }

    public void setNotify(String notify) {
        this.notify = notify;
    }

    public String getProof() {
        return proof;
    }

    public void setProof(String proof) {
        this.proof = proof;
    }

    public Transactions(int transactionID, String employeeEmail, double reimbursementAmount, String managerEmail, String status, String timestamp, String notify, String proof) {
        TransactionID = transactionID;
        this.employeeEmail = employeeEmail;
        this.reimbursementAmount = reimbursementAmount;
        this.managerEmail = managerEmail;
        this.status = status;
        this.timestamp = timestamp;
        this.notify = notify;
        this.proof = proof;
    }

    public Transactions(String employeeEmail, double reimbursementAmount, String proof) {
        this.employeeEmail = employeeEmail;
        this.reimbursementAmount = reimbursementAmount;
        this.proof = proof;
    }



    public Transactions(){

    }

}
