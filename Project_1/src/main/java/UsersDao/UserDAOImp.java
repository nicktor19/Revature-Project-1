package UsersDao;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.persistence.Query;
import java.sql.*;
import java.util.*;

public class UserDAOImp {
    static Configuration config;
    static SessionFactory factory;
    static Session session;
    static Transaction trans;

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


    public static Users login(String Email, String password) {
        OpenConnect();
        String sql = "call Login(:Email, :Pass);";
        Query search = session.createSQLQuery(sql).addEntity(Users.class)
                .setParameter("Email", Email)
                .setParameter("Pass", password);

        List<Users> login = search.getResultList();
        if(!login.isEmpty()) {
            session.close();
            return login.get(0);
        }
        session.close();
        return null;
    }

    public static boolean UserEmailExists(String email) {
        OpenConnect();
        String sql = "SELECT Email FROM users WHERE email = :email";

        Query search = session.createSQLQuery(sql).setParameter("email", email);
        List<Users> list = search.getResultList();
        if(!list.isEmpty()) {
            //System.out.println(list.get(0));
            session.close();
            return true;
        }
        System.out.println("Finished email check");
        session.close();
        return false;
    }

    public static void Register(String LastN, String FirstN, String email, String password) {
        OpenConnect();
        String sql = "CALL RegisterForSite(:last, :first, :email, :passcode)";
        Query register = session.createSQLQuery(sql).addEntity(Users.class)
                .setParameter("last", LastN)
                .setParameter("first", FirstN)
                .setParameter("email", email)
                .setParameter("passcode", password);
        int rows = register.executeUpdate();
        //Users user = new Users(LastN, FirstN, email, password, "EmpDashboardServlet");
        //session.save(user);
        //trans.commit();
        session.close();



    }

    public static List<Users> FindUsers(String employee) throws SQLException{
        OpenConnect();
        String sql = "call FindUsersByNameFirstLast(:Search)";
        Query FLSearch = session.createSQLQuery(sql).addEntity(Users.class)
                .setParameter("Search", employee);
        HashSet<Users> total = new HashSet<>();

        ArrayList<Users> first = (ArrayList<Users>) FLSearch.getResultList();
        if(!first.isEmpty()){;
            for(Users fSet : first){
                total.add(fSet);
            }
        }

        String sql2 = "call FindUsersByNameLastFirst(:Search)";
        Query LFSearch = session.createSQLQuery(sql2).addEntity(Users.class)
                .setParameter("Search", employee);

        ArrayList<Users> Last = (ArrayList<Users>) FLSearch.getResultList();
        if(!Last.isEmpty()){
            for(Users LSet : Last){
                total.add(LSet);
            }
        }

        ArrayList<Users> result = new ArrayList<>();

        for(Users emp : total){
            result.add(emp);
        }

        session.close();

        return result;
    }
    public static Users UpdateUserAccount(Users original, String FN, String LN, String oldPassword, String NewP) {
        Users userUpdate = login(original.getEmail(), oldPassword);
        if (userUpdate != null && !userUpdate.equals(null)){
            OpenConnect();
            userUpdate.setFirstName(FN);
            userUpdate.setLastName(LN);
            session.update(userUpdate);
            trans.commit();
            session.close();
            if (!oldPassword.equals(NewP) && !NewP.isEmpty() && NewP != null) {
                return UpdatePassEncrypt(userUpdate, NewP);
            } else {
                //Returns Updated User Object with original (old) password
                //Store into existing store into session
                return login(userUpdate.getEmail(), oldPassword);
            }
        }
        System.out.println("Old Password incorrect");
        return original;
    }
    private static Users UpdatePassEncrypt(Users updated, String NewP){
        OpenConnect();
        String sql = "Update users SET passcode = sha2(:pass,224) WHERE ID = :UID";
        Query upPass = session.createSQLQuery(sql)
                .setParameter("pass", NewP)
                .setParameter("UID", updated.getID());
        int rows = upPass.executeUpdate();

        if(rows > 0){
            session.close();
            updated = login(updated.getEmail(), NewP);
        }
        return updated;
    }
    public static List<Users> AllUsers(){
        OpenConnect();
        String sql = "SELECT * FROM users";
        Query getUsers = session.createSQLQuery(sql).addEntity(Users.class);
        //Note: After testing the query. I found you need to include password in the selection
        //or else the program would break
        List<Users> userList = getUsers.getResultList();
        if (!userList.isEmpty()){
            session.close();
            return (ArrayList<Users>) userList;
        }
        session.close();
        return null;
    }

}
