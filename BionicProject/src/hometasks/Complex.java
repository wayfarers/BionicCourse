package hometasks;

public class Complex {
	private double realPart;
	private double imaginePart;

	public Complex(double realPart, double imaginePart) {
		this.realPart = realPart;
		this.imaginePart = imaginePart;
	}

	public Complex add(Complex num) {
		return new Complex(realPart + num.realPart, imaginePart
				+ num.imaginePart);
	}

	public Complex mult(Complex num) {
		return new Complex((realPart * num.realPart - imaginePart
				* num.imaginePart), realPart * num.imaginePart + num.realPart
				* imaginePart);
	}

	public double abs() {
		return Math.sqrt(realPart + imaginePart);
	}

	@Override
	public String toString() {
		if (imaginePart > 0) {
			return "" + realPart + " + " + imaginePart + "*i";
		} else {
			return "" + realPart + " - " + (-imaginePart) + "*i";
		}
	}

	public static void main(String[] args) {
		System.out.println(new Complex(1, -10).add(new Complex(1, 2)).add(
				new Complex(3, 2)));
	}
}
