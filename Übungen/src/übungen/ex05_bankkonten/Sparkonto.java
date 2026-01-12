package übungen.ex05_bankkonten;

public class Sparkonto extends Konto {
	
	Sparkonto(double balance, int overdrawLimit, float debtInterestRate, float interestRate) {
		super(balance, overdrawLimit, debtInterestRate, interestRate);
	}
	
	Sparkonto() {
		super(0, 0, 0f, 0.003f);
	}
}
