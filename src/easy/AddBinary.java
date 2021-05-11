package easy;

public class AddBinary {
    public String addBinary(String a, String b) {
        if (a.length() < b.length()) {
            a = "0".repeat(b.length()-a.length()) + a;
        } else {
            b = "0".repeat(a.length()-b.length()) + b;
        }

        boolean isCarry = false;

        StringBuilder sum = new StringBuilder();
        int i = 0;
        while (a.length() > i) {
            String aChar = String.valueOf(a.charAt(a.length()-1-i));
            String bChar = String.valueOf(b.charAt(b.length()-1-i));

            if (aChar.equals("0") && bChar.equals("0")) {
                if (isCarry) sum.insert(0, "1");
                else sum.insert(0, "0");
                isCarry = false;
            }

            if (aChar.equals("0") && bChar.equals("1") || aChar.equals("1") && bChar.equals("0")) {
                if (isCarry) {
                    sum.insert(0, "0");
                    isCarry = true;
                }
                else {
                    sum.insert(0, "1");
                    isCarry = false;
                }
            }

            if (aChar.equals("1") && bChar.equals("1")) {
                if (isCarry) {
                    sum.insert(0, "1");
                }
                else {
                    sum.insert(0, "0");
                }
                isCarry = true;
            }

            i++;
        }

        if (isCarry) sum.insert(0, "1");

        return sum.toString();
    }
}
