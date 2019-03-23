package client;

import automatons.TestAutomaton;

public class TestDemo {
	public static void main(String[] args) {
		TestAutomaton test = new TestAutomaton();
		test.connection();
		test.testMessage();
	}
}
