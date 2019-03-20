package br.com.m2yconductorservices.utils;

public class M2YCDTCpf {

    /* Matches
    00000000000 | 123.456.789-98 | 12345678998
    Non-Matches
    0000.000.000-00
    */
    private static final String CPF_PATTERN = "^(\\d{3}.\\d{3}.\\d{3}-\\d{2})|(\\d{11})$";

    public static boolean isValid(String text) {
        if (text.matches(CPF_PATTERN)) {
            return isCpf(text);
        }
        return false;
    }

    private static boolean isCpf(String cpf) {
        String toBeChecked = cpf.replaceAll("[.]", "").replaceAll("[-]", "");
        int sum = 0;
        for (int i = 0; i < 9; i++) {
            char m = toBeChecked.charAt(i);
            sum += (10 - i) * Character.getNumericValue(m);
        }
        if (checkResto(sum, toBeChecked, 9)) {
            sum = 0;

            for (int i = 0; i < 10; i++) {
                char m = toBeChecked.charAt(i);
                sum += (11 - i) * Character.getNumericValue(m);
            }
            if (checkResto(sum, toBeChecked, 10)) {
                return true;
            }
        }

        return false;
    }

    private static boolean checkResto(int sum, String toBeChecked, int positionToCheck) {
        int resto = sum % 11;
        if (resto < 2) {
            if (Character.getNumericValue(toBeChecked.charAt(positionToCheck)) == 0) {
                return true;
            }
        } else {
            resto = 11 - resto;
            if (Character.getNumericValue(toBeChecked.charAt(positionToCheck)) == resto) {
                return true;
            }
        }

        return false;
    }

}
