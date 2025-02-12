package com.ontariotechu.sofe3980U;

public class Binary {
	private String number = "0";

	public Binary(String number) {
		if (number == null || number.isEmpty()) {
			this.number = "0";
			return;
		}

		for (int i = 0; i < number.length(); i++) {
			char ch = number.charAt(i);
			if (ch != '0' && ch != '1') {
				this.number = "0";
				return;
			}
		}

		int beg;
		for (beg = 0; beg < number.length(); beg++) {
			if (number.charAt(beg) != '0') {
				break;
			}
		}

		this.number = (beg == number.length()) ? "0" : number.substring(beg);

		if (this.number.isEmpty()) {
			this.number = "0";
		}
	}

	public String getValue() {
		return this.number;
	}

	public static Binary add(Binary num1, Binary num2) {
		int ind1 = num1.number.length() - 1;
		int ind2 = num2.number.length() - 1;
		int carry = 0;
		String num3 = "";
		while (ind1 >= 0 || ind2 >= 0 || carry != 0) {
			int sum = carry;
			if (ind1 >= 0) {
				sum += (num1.number.charAt(ind1) == '1') ? 1 : 0;
				ind1--;
			}
			if (ind2 >= 0) {
				sum += (num2.number.charAt(ind2) == '1') ? 1 : 0;
				ind2--;
			}
			carry = sum / 2;
			sum = sum % 2;
			num3 = ((sum == 0) ? "0" : "1") + num3;
		}
		Binary result = new Binary(num3);
		return result;
	}

	public static Binary and(Binary num1, Binary num2) {
		int ind1 = num1.number.length() - 1;
		int ind2 = num2.number.length() - 1;
		String num3 = "";

		while (ind1 >= 0 && ind2 >= 0) {
			int bit1 = (num1.number.charAt(ind1) == '1') ? 1 : 0;
			int bit2 = (num2.number.charAt(ind2) == '1') ? 1 : 0;

			num3 = ((bit1 & bit2) == 1 ? "1" : "0") + num3;

			ind1--;
			ind2--;
		}
		return new Binary(num3.isEmpty() ? "0" : num3);
	}

	public static Binary or(Binary num1, Binary num2) {
		int ind1 = num1.number.length() - 1;
		int ind2 = num2.number.length() - 1;
		String num3 = "";

		while (ind1 >= 0 || ind2 >= 0) {
			int bit1 = (ind1 >= 0 && num1.number.charAt(ind1) == '1') ? 1 : 0;
			int bit2 = (ind2 >= 0 && num2.number.charAt(ind2) == '1') ? 1 : 0;

			num3 = ((bit1 | bit2) == 1 ? "1" : "0") + num3;

			if (ind1 >= 0)
				ind1--;
			if (ind2 >= 0)
				ind2--;
		}

		return new Binary(num3);
	}

	public static Binary multiply(Binary num1, Binary num2) {
		String num1Str = num1.number;
		String num2Str = num2.number;
		String result = "0";

		for (int i = num1Str.length() - 1; i >= 0; i--) {
			if (num1Str.charAt(i) == '1') {
				StringBuilder zeros = new StringBuilder();
				for (int j = 0; j < num1Str.length() - 1 - i; j++) {
					zeros.append("0");
				}
				String shiftedNum2 = num2Str + zeros.toString();
				result = add(new Binary(result), new Binary(shiftedNum2)).getValue();
			}
		}

		return new Binary(result);
	}
}
