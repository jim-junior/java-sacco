package Deposit;

import java.sql.*;

public class Deposit {
  private String dbUrl = "jdbc:mysql://root:gaCv4GRenj0eTSpdSzXM@containers-us-west-103.railway.app:6179/railway";
  private String username = "root";
  private String password = "gaCv4GRenj0eTSpdSzXM";
  private Connection con = null;

  public Deposit() {
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      con = DriverManager.getConnection(dbUrl, username, password);
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  public void deposit(int amount, String date_deposited, int receipt_number) {
    try {
      Statement statement;
      statement = con.createStatement();
      ResultSet res = statement.executeQuery("SELECT * FROM receipts");

      while (res.next()) {
        if (res.getInt("receipt_number") == receipt_number) {
          System.out.println("Acknowledgement");
          return;
        }
      }
      System.out.println("Check later after new information is uploaded");
    } catch (Exception e) {
      System.out.println(e);
    }
  }
}
