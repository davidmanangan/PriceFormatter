package iam.davidmanangan.priceformatter.constants;

public enum DisplayFormat {
	
	DECIMAL(1), 		// multiply raw price by 1
	PERCENTAGE(100), 	// multiply raw price by 100
	PER_MILE(1000), 	// multiply raw price by 1,000
	BASIS_POINT(10000); // multiply raw price by 10,000

	private int value;

	private DisplayFormat(int value) {
		this.value = value;
	}

	public int getValue() {
		return this.value;
	}

}
