package übungen.ex05_bankkonten;

public class Girokonto extends Konto {
	
	Girokonto(double balance, int overdrawLimit, float debtInterestRate, float interestRate) {
		super(balance, overdrawLimit, debtInterestRate, interestRate);
	}
	
	Girokonto() {
		super(0, 1000, 0.0125f, 0f);
	}
}
