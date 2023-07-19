package Deposit;

import java.sql.*;

public class Deposit {
  private String dbUrl = "jdbc:mysql://root:M3Sk65aoEEvPCnyxoTzX@containers-us-west-207.railway.app:6325/railway";
  private String username = "root";
  private String password = "M3Sk65aoEEvPCnyxoTzX";
  private Connection con = null;

  public Deposit() {
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      con = DriverManager.getConnection(dbUrl, username, password);
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  public void deposit(String[] commandArgs) {
    try {
      String receiptId = commandArgs[1];
      Integer amount = Integer.parseInt(commandArgs[2]);
      String query = "SELECT * FROM receipts WHERE receipt_number = ? and amount = ?";

      PreparedStatement preparedStatement = con.prepareStatement(query);
      preparedStatement.setString(1, receiptId);
      preparedStatement.setInt(2, amount);
      ResultSet resultSet = preparedStatement.executeQuery();

      if (resultSet.next()) {
        System.out.println("Deposit Successfull");
      } else {
        System.out.println("No Reciept Found. Please try again later after new information is uploaded.");
      }

    } catch (Exception e) {
      System.out.println(e);
    }
  }

  public static void main(String[] args) {
    Deposit deposit = new Deposit();
    String[] commandArgs = { "deposit", "R001", "20000" };
    deposit.deposit(commandArgs);
  }
}
