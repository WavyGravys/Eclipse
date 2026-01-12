package übungen.ex05_bankkonten;

public class Konto {
	//private int number;
	//private String owner;
	private double balance = 0;
	private int overdrawLimit = 1000;
	private float debtInterestRate = 0.0125f;
	private float interestRate = 0.005f;
	
	Konto(double deposit, int overdrawLimit, float debtInterestRate, float interestRate) {
		this.balance = deposit;
		this.overdrawLimit = overdrawLimit;
		this.debtInterestRate = debtInterestRate;
		this.interestRate = interestRate;
	}
	
	
	public boolean deposit(int amount) {
		if (amount < 0) return false;
		
		balance += amount;
		return true;
	}
	
	
	public boolean withdraw(int amount) {
		if (amount < 0 || amount >= balance + overdrawLimit) return false;
		
		balance -= amount;
		return true;
	}
	
	
	public double getBalance() {
		return balance;
	}
	
	
	public boolean newYear(int amount) {
		double debtInterest = 0;
		double interest = 0;
		double newDeposit = balance;
		
		if (balance < 0) {
			debtInterest *= debtInterestRate;
			newDeposit -= balance * debtInterestRate;
		} else {
			interest *= interestRate;
			newDeposit += balance * interestRate;
		}
		
		if (newDeposit < -overdrawLimit) return false;
			
		balance = balance - debtInterest + interest; 
		return true;
	}
}
