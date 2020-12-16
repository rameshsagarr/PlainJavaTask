package com.mars;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PersonsDemo {
	private static final Logger logger = LogManager.getLogger(PersonsDemo.class);

	public static void main(String[] args) throws SQLException {

		DatabaseUtility.createPersonTable();
		int option = runAgain();
		Connection con = null;
		Statement st = null;
		PreparedStatement pst = null;
		do {
			switch (option) {

			case 1:
				logger.debug("case 1");

				con = DatabaseUtility.getDBConnection();
				st = con.createStatement();
				addPerson(con, st);
				con = DatabaseUtility.getDBConnection();
				allPersons(con, pst);
				option = runAgain();
				break;

			case 2:
				logger.debug("case 2:");
				con = DatabaseUtility.getDBConnection();
				st = con.createStatement();
				logger.debug("before editing person list::");
				allPersons(con, pst);
				con = DatabaseUtility.getDBConnection();
				st = con.createStatement();
				editPerson(con, st, pst);
				logger.debug("after editing person list::");
				con = DatabaseUtility.getDBConnection();
				st = con.createStatement();
				allPersons(con, pst);
				option = runAgain();
				break;

			case 3:
				logger.debug("case 3:");
				con = DatabaseUtility.getDBConnection();
				st = con.createStatement();
				logger.debug("before deleting person list::");
				allPersons(con, pst);
				con = DatabaseUtility.getDBConnection();
				st = con.createStatement();
				deletePerson(con, pst);
				logger.debug("after deleting person list::");
				allPersons(con, pst);
				option = runAgain();
				break;

			// if 4 no of persons

			case 4:
				logger.debug("case 4::");
				con = DatabaseUtility.getDBConnection();
				st = con.createStatement();
				personCount(con, pst);
				option = runAgain();
				break;

			case 5:
				logger.debug("case 5:");
				con = DatabaseUtility.getDBConnection();
				st = con.createStatement();
				allPersons(con, pst);
				option = runAgain();
				break;

			default:
				System.exit(6);
				logger.debug("exit from program");

			}

		} while (option <= 5);

	}

	public static int runAgain() {
		String myObj = chooseOption();
		int option = 0;
		option = validateIntValue(myObj);
		System.out.println("your chossen option is: " + option);
		return option;
	}

	public static void editPerson(Connection con, Statement st, PreparedStatement ps) {
		Scanner scannerObj = new Scanner(System.in);
		System.out.println("please enter pid");
		String sid = scannerObj.nextLine();
		int Personid = validateIntValue(sid);
		Scanner scannerObj2 = new Scanner(System.in);
		System.out.println("please enter firstname");
		String firstname = scannerObj.nextLine();
		Scanner scannerObj3 = new Scanner(System.in);
		System.out.println("please enter surname");
		String surname = scannerObj3.nextLine();
		Person p = null;
		String sql = "SELECT * FROM persons where pid=?";
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, Personid);

			ResultSet rs = pst.executeQuery();
			Person dbPerson = null;
			while (rs.next()) {

				String updateuserquery = "UPDATE persons SET firstname=? , surname=? where pid=?";

				PreparedStatement pstt = con.prepareStatement(updateuserquery);
				pstt.setString(1, firstname);
				pstt.setString(2, surname);
				pstt.setInt(3, Personid);
				int rowsUpdated = pstt.executeUpdate();
				if (rowsUpdated > 0) {
					logger.debug("An existing person was updated successfully!");
				} else {
					logger.debug("update not done");
				}

			}
			rs.close();
			st.close();
			pst.close();
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}

	}

	public static void deletePerson(Connection con, PreparedStatement st) {
		Scanner scannerObj = new Scanner(System.in);
		System.out.println("please enter pid");
		String sid = scannerObj.nextLine();
		int Personid = validateIntValue(sid);
		Person p = null;

		try {
			String sql = "DELETE FROM persons " + "WHERE pid = ?";
			PreparedStatement pstt = con.prepareStatement(sql);
			pstt.setInt(1, Personid);
			int rowsUpdated = pstt.executeUpdate();
			if (rowsUpdated > 0) {
				logger.debug("An existing person was deleted successfully!");
			} else {
				logger.debug("delete not done");
			}

		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
	}

	public static void addPerson(Connection con, Statement st) {

		Scanner scannerObj = new Scanner(System.in);
		System.out.println("please enter pid");
		String sid = scannerObj.nextLine();
		int Personid = validateIntValue(sid);
		Scanner scannerObj2 = new Scanner(System.in);
		System.out.println("please enter firstname");
		String firstname = scannerObj2.nextLine();
		Scanner scannerObj3 = new Scanner(System.in);
		System.out.println("please enter surname");
		String surname = scannerObj3.nextLine();
		int m = 0;
		try {
			m = st.executeUpdate(
					"insert into PERSONS (firstname,surname)values('" + firstname + "','" + surname + "')");
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		if (m == 1)
			logger.debug("inserted successfully : " + m);
		else
			logger.debug("insertion failed");
		try {
			con.close();
			st.close();
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
	}

	public static int validateIntValue(String sc) {
		int option = 0;

		try {
			option = Integer.parseInt(sc);
		} catch (NumberFormatException ex) {
			System.out.println("Please enter non String value");
		} catch (Exception e) {
			logger.error(e.getMessage());

		}
		return option;
	}

	public static String chooseOption() {
		Scanner myObj = new Scanner(System.in);
		System.out.println("please enter your option from the following:");
		System.out.println("enter 1 as add the person");
		System.out.println("enter 2 as edit the person");
		System.out.println("enter 3 as delete the person");
		System.out.println("enter 4 as count  the no of person");
		System.out.println("enter 5 as to print  the persons list");
		String SringObj = myObj.nextLine();
		return SringObj;
	}

	public static void personCount(Connection con, PreparedStatement pst) {
		List<Person> personList = new ArrayList<Person>();
		personList = allPersons(con, pst);
		logger.debug("persons count=" + personList.size());
	}

	public static List<Person> allPersons(Connection con, PreparedStatement pst) {
		String sql = "SELECT pid,firstname,surname FROM persons";
		List<Person> personList = new ArrayList<Person>();
		try {
			pst = con.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				Person dbPerson = new Person();
				int pid = rs.getInt(1);
				dbPerson.setPid(pid);
				String fname = rs.getString(2);
				dbPerson.setFirstname(fname);
				dbPerson.setSurname(rs.getString(3));
				personList.add(dbPerson);

			}
			logger.debug("list of persons data=" + personList);
			rs.close();
			pst.close();
			con.close();
		}

		catch (Exception e) {
			logger.error(e.getMessage());
		}
		return personList;

	}

}
