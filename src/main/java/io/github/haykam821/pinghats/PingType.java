package io.github.haykam821.pinghats;

public enum PingType {
	DIRECT(6),
	EVERYONE(15),
	HERE(12),
	SELF(3),
	SOMEONE(9);

	private final int power;

	private PingType(int power) {
		this.power = power;
	}

	public int getPower() {
		return this.power;
	}
}