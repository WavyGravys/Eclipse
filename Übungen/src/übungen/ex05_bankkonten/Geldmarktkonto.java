package übungen.ex05_bankkonten;

public class Geldmarktkonto extends Konto {

	Geldmarktkonto(double balance, int overdrawLimit, float debtInterestRate, float interestRate) {
		super(balance, overdrawLimit, debtInterestRate, interestRate);
	}
	
	Geldmarktkonto() {
		super(0, 0, 0f, 0.0055f);
	}
}
