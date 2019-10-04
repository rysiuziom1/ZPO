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
        if (!pes.matches("[0-9]{11}"))
            throw new IllegalArgumentException("Wrong PESEL");

        int eternity = 19;
        int birthYear = Integer.parseInt(pes.substring(0, 2));
        int birthMonth = Integer.parseInt(pes.substring(2, 4));
        int birthDay = Integer.parseInt(pes.substring(4, 6));

        if (birthMonth > 80) {
            if (birthMonth > 92)
                throw new IllegalArgumentException("Wrong PESEL");
            eternity--;
            birthMonth -= 80;
        }

        while (birthMonth > 20) {
            eternity++;
            birthMonth -= 20;
        }

        if (birthMonth > 12)
            throw new IllegalArgumentException("Wrong PESEL");

        if (birthDay > 31)
            throw new IllegalArgumentException("Wrong PESEL");

        birthYear += eternity * 100;

        switch (birthMonth) {
            case 4:
            case 6:
            case 9:
            case 11:
                if (birthDay > 30)
                    throw new IllegalArgumentException("Wrong PESEL");
                break;
            case 2:
                if (birthDay > 29 || (birthDay > 28 && !Year.isLeap(birthYear)))
                    throw new IllegalArgumentException("Wrong PESEL");
        }

        String[] months = {"styczeń", "luty", "marzec", "kwiecień", "maj", "czerwiec", "lipiec", "sierpień", "wrzesień",
                "październik", "listopad", "grudzień"};

        String birthDate = birthDay + " " + months[birthMonth - 1] + " " + birthYear;

        int genderNumber = Integer.parseInt(pes.substring(9, 10));

        String gender = genderNumber % 2 != 0 ? "mężczyzna" : "kobieta";

        return new PersonalData(birthDate, gender);
    }
}
