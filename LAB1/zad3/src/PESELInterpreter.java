import java.time.Year;
import java.util.Scanner;

public class PESELInterpreter {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        System.out.print("Wprowadź PESEL: ");
        String pes = scn.nextLine();
        try {
            PersonalData personalData = getPersonalData(pes);
            System.out.println(personalData.toString());
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    private static PersonalData getPersonalData(String pes) throws IllegalArgumentException {
        //validate pesel regex
        if (!pes.matches("[0-9]{11}"))
            throw new IllegalArgumentException("Wrong PESEL - less or more then 11 digits, or some character aren't digits");

        int eternity = 19;
        int birthYear = Integer.parseInt(pes.substring(0, 2));
        int birthMonth = Integer.parseInt(pes.substring(2, 4));
        int birthDay = Integer.parseInt(pes.substring(4, 6));

        //identify eternity
        if (birthMonth > 80) {
            eternity--;
            birthMonth -= 80;
        }
        while (birthMonth > 20) {
            eternity++;
            birthMonth -= 20;
        }

        //validate month
        if (birthMonth > 12)
            throw new IllegalArgumentException("Wrong PESEL - wrong month of birth");

        //validate birthday
        if (birthDay > 31)
            throw new IllegalArgumentException("Wrong PESEL - wrong day of birth");

        birthYear += eternity * 100;

        //validate birthday v1.1
        switch (birthMonth) {
            case 4:
            case 6:
            case 9:
            case 11:
                if (birthDay > 30)
                    throw new IllegalArgumentException("Wrong PESEL - wrong day of birth");
                break;
            case 2:
                if (birthDay > 29 || (birthDay > 28 && !Year.isLeap(birthYear)))
                    throw new IllegalArgumentException("Wrong PESEL - wrong day of birth");
        }

        //validate control number
        char[] pesDigits = pes.toCharArray();
        int controlNumberFromPes = Character.getNumericValue(pesDigits[pesDigits.length - 1]);
        int controlNumber = 0;
        int[] weighs = {9, 7, 3, 1, 9, 7, 3, 1, 9, 7};
        for (int i = 0; i < pesDigits.length - 1; i++) {
            controlNumber += weighs[i] * Character.getNumericValue(pesDigits[i]);
        }
        controlNumber %= 10;
        if (controlNumber != controlNumberFromPes)
            throw new IllegalArgumentException("Wrong PESEL - wrong control number");

        String[] months = {"styczeń", "luty", "marzec", "kwiecień", "maj", "czerwiec", "lipiec", "sierpień", "wrzesień",
                "październik", "listopad", "grudzień"};

        String birthDate = birthDay + " " + months[birthMonth - 1] + " " + birthYear;

        int genderNumber = Integer.parseInt(pes.substring(9, 10));
        String gender = genderNumber % 2 != 0 ? "mężczyzna" : "kobieta";

        return new PersonalData(birthDate, gender);
    }
}
