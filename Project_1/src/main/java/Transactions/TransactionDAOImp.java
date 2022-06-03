package Transactions;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.persistence.PersistenceException;
import javax.persistence.Query;
import java.util.*;

public class TransactionDAOImp {
    static Configuration config;
    static SessionFactory factory;
    static Session session;
    static org.hibernate.Transaction trans;

    static void OpenConnect() {
        //Create configuration
        config = new Configuration();
        config.configure("hibernate.cfg.xml");

        //Create factory
        factory = config.buildSessionFactory();
        //Open Session
        session = factory.openSession();

        //Transcation
        trans = session.beginTransaction();
    }

    public static void ReimbursementReq(String EmpEmail, double amount, String proof) {
        OpenConnect();
        String SQL = "call Reimbursement(:EmpDashboardServlet, :Reimbursement, :proof)";
        Query reimburse = session.createSQLQuery(SQL).addEntity(Transactions.class)
                .setParameter("EmpDashboardServlet", EmpEmail)
                .setParameter("Reimbursement", amount)
                .setParameter("proof", proof);
        reimburse.executeUpdate();
        session.close();
    }

    public static void ChangeReimbursementStatus(int transID, String mangerEmail, String status) {
        OpenConnect();
        String SQL = "call ChangeTransactionStatus(:transID, :ManagerEmail, :Status)";
        Query changeStatus = session.createSQLQuery(SQL)
                .setParameter("transID", transID)
                .setParameter("ManagerEmail", mangerEmail)
                .setParameter("Status", status);
        changeStatus.executeUpdate();
        session.close();
    }

    public static ArrayList<Transactions> pendingTransactions() {
        OpenConnect();
        String SQL = "SELECT * FROM transactions WHERE status='Pending'";
        Query Search = session.createSQLQuery(SQL).addEntity(Transactions.class);
        try {
            List<Transactions> list = Search.getResultList();
            if (!list.isEmpty()) {
                session.close();
                return (ArrayList<Transactions>) list;
            }
        }catch (PersistenceException p){
            System.out.println("Pending Transactions " + p);
        }catch (NullPointerException e){
            System.out.println("Pending Transactions" + e);
        }
        session.close();
        return null;
    }

    public static ArrayList<Transactions> FindTransByEmpEmail(String EmpEmail) {
        OpenConnect();
        String SQL = "call FindTransByEmployeeEmail(:Email)";
        Query Search = session.createSQLQuery(SQL).addEntity(Transactions.class)
                .setParameter("Email", EmpEmail);
        try {
            List<Transactions> list = Search.getResultList();
            if (!list.isEmpty()) {
                session.close();
                return (ArrayList<Transactions>) list;
            }
        }
        catch (PersistenceException p){
            System.out.println("Search by Email" + p);
        }catch (NullPointerException e){
            System.out.println("Search by Email" + e);
        }
        session.close();
        return null;
    }

    public static ArrayList<Transactions> GetUserTypeTrans(String EmpEmail, String Type) {
        OpenConnect();
        String SQL = "call GetUserTypeTransactions(:Email, :Type)";
        Query Search = session.createSQLQuery(SQL).addEntity(Transactions.class)
                .setParameter("Email", EmpEmail)
                .setParameter("Type", Type);
        try {
            List<Transactions> list = Search.getResultList();
            if (!list.isEmpty()) {
                session.close();
                return (ArrayList<Transactions>) list;
            }
        }catch (PersistenceException p){
            System.out.println("Search by Email" + p);
        }catch (NullPointerException e){
            System.out.println("Search by Email" + e);
        }
        session.close();
        return null;
    }

    public static ArrayList<Transactions> FindTransMangerEmail(String manager) {
        OpenConnect();
        String SQL = "call FindTransByManagerEmail(:ManagerEmail)";
        Query search = session.createSQLQuery(SQL).addEntity(Transactions.class)
                .setParameter("ManagerEmail", manager);
        try {
            List<Transactions> list = search.getResultList();
            if (!list.isEmpty()) {
                session.close();
                return (ArrayList<Transactions>) list;
            }
        }catch (PersistenceException p){
            System.out.println("Search by Email" + p);
        }catch (NullPointerException e){
            System.out.println("Search by Email" + e);
        }
        session.close();
        return null;
    }

    public static ArrayList<Transactions> getTrans() {
        OpenConnect();
        String sql = "call GetTransactions()";
        Query get = session.createSQLQuery(sql).addEntity(Transactions.class);
        try {
            List<Transactions> list = get.getResultList();
            if (!list.isEmpty()) {
                session.close();
                return (ArrayList<Transactions>) list;
            }
        }catch (PersistenceException p){
            System.out.println("Search by Email" + p);
        }catch (NullPointerException e){
            System.out.println("Search by Email" + e);
        }
        session.close();
        return null;
    }

    public static ArrayList<Transactions> getUserNotifyTransactions(String EmpEmail, String note) {
        OpenConnect();
        String sql = "call GetUserNotifyTransactions(:Email, :Notified)";
        Query get_notifed = session.createSQLQuery(sql).addEntity(Transactions.class)
                .setParameter("Email", EmpEmail)
                .setParameter("Notified", note);
        try {
            List<Transactions> list = get_notifed.getResultList();
            if (!list.isEmpty()) {
                session.close();
                return (ArrayList<Transactions>) list;
            }
        }catch (PersistenceException p){
            System.out.println("Search by Email" + p);
        }catch (NullPointerException e){
            System.out.println("Search by Email" + e);
    }
        session.close();
        return null;
    }

    public static void AknowledgeTransaction(String email, int transID) {
        OpenConnect();
        String sql = "call UserAckTransactions(:email, :tID)";
        Query ack = session.createSQLQuery(sql).addEntity(Transactions.class)
                .setParameter("email", email)
                .setParameter("tID", transID);

        int update = ack.executeUpdate();

        if(update !=0 ){
            System.out.println("Ack sucessful");
        }

    }
    public static ArrayList<Transactions> resolvedTransactions() {
        OpenConnect();
        String SQL = "SELECT * FROM transactions WHERE status='Approved' OR status='Rejected'";
        Query Search = session.createSQLQuery(SQL).addEntity(Transactions.class);
        try {
            List<Transactions> list = Search.getResultList();
            if (!list.isEmpty()) {
                session.close();
                return (ArrayList<Transactions>) list;
            }
        }catch (PersistenceException p){
            System.out.println("Search by Email" + p);
        }catch (NullPointerException e){
            System.out.println("Search by Email" + e);
        }
        session.close();
        return null;
    }
}
