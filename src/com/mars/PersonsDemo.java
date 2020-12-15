package com.mars;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class PersonsDemo {

	public static void main(String[] args) {
		// int option;
		List<Person> PersonList = new ArrayList<Person>();
		int option = runAgain();
		do {
			switch (option) {
			// if 1 add

			case 1:
				System.out.println("case 1");
				addPerson(PersonList);

				option = runAgain();
				break;

			// if 2 edit
			case 2:
				System.out.println("case 2:");
				editPerson(PersonList);
				option = runAgain();
				break;

			// if 3 delete

			case 3:
				System.out.println("case 3:");
				deletePerson(PersonList);
				option = runAgain();
				break;

			// if 4 no of persons

			case 4:
				System.out.println("case 4::");
				personCount(PersonList);
				option = runAgain();
				break;

			// if 5 print all persons

			case 5:
				System.out.println("case 5:");
				allPersons(PersonList);
				option = runAgain();
				break;

			default:
				option = runAgain();

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

	public static void editPerson(List<Person> PersonList) {
		Scanner scannerObj = new Scanner(System.in);
		System.out.println("please enter pid");
		String sid = scannerObj.nextLine();
		int Personid = validateIntValue(sid);
		Person p = null;
		for (Person person : PersonList) {
			if (person.getpid() == Personid) {
				p = person;
				Scanner scannerObj2 = new Scanner(System.in);
				System.out.println("please enter panme");
				String pname = scannerObj.nextLine();
				PersonList.add(new Person(Personid, pname));
				break;
			} else {
				System.out.println(" pid is not present");
			}
		}
	}

	public static void deletePerson(List<Person> PersonList) {
		Scanner scannerObj = new Scanner(System.in);
		System.out.println("please enter pid");
		String sid = scannerObj.nextLine();
		int Personid = validateIntValue(sid);
		Person p = null;

		Iterator itr = PersonList.listIterator();
		while (itr.hasNext()) {
			Person per = (Person) itr.next();
			if (per.getpid() == Personid) {
				itr.remove();
			}
		}
		System.out.println("debug::" + PersonList);
	}

	public static void addPerson(List<Person> PersonList) {
		Scanner scannerObj = new Scanner(System.in);
		System.out.println("please enter pid");
		String sid = scannerObj.nextLine();
		int Personid = validateIntValue(sid);
		Scanner scannerObj2 = new Scanner(System.in);
		System.out.println("please enter panme");
		String pname = scannerObj.nextLine();
		PersonList.add(new Person(Personid, pname));
		System.out.println("debug::" + PersonList);
	}

	public static int validateIntValue(String sc) {
		int option = 0;

		try {
			option = Integer.parseInt(sc);
		} catch (NumberFormatException ex) {
			System.out.println("Please enter non String value");
		} catch (Exception e) {
			System.out.println(e);
			/*
			 * chooseOption(); String myObj = chooseOption();
			 */

		}
		return option;
	}

	public static String chooseOption() {
		Scanner myObj = new Scanner(System.in);
		// int option=0;
		System.out.println("please enter your option from the following:");
		System.out.println("enter 1 as add the person");
		System.out.println("enter 2 as edit the person");
		System.out.println("enter 3 as delete the person");
		System.out.println("enter 4 as count  the no of person");
		System.out.println("enter 5 as to print  the persons list");
		String SringObj = myObj.nextLine();
		return SringObj;
	}

	public static void personCount(List<Person> PersonList) {
		System.out.println("persons count=" + PersonList.size());
		System.out.println("debug::" + PersonList);
	}

	public static void allPersons(List<Person> PersonList) {
		System.out.println("persons count=" + PersonList);

	}
}
