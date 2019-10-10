package ruler;

public class Ruler {

    /**
     * Rysuje dwustronna linijke o zadanych dlugosci i poziomie zagniezdzenia
     *
     * @param length dlugosc linijki
     * @param nesting poziom zagniezdzenia
     */
    public static void drawRuler(int length, int nesting) {
        if(length > 0) {
            drawRuler(length - 1, nesting);
            drawTicks(nesting * 2 - 2, nesting);
            drawOneTick(nesting * 2, nesting, length);
        } else if(length == 0)
            drawOneTick(nesting * 2, nesting, length);


        /*
        int majorLength = nesting * 2;
        drawOneTick(majorLength, nesting, 0);
        for (int i = 1; i <= length; i++) {
            drawTicks(majorLength - 2, nesting);
            drawOneTick(majorLength, nesting, i);
        }
        */
    }

    /**
     * Rekurencyjnie rysuje podzialke pomiedzy glownymi wartosciami na linijce
     *
     * @param tickLength dlugosc podzialki
     * @param nesting poziom zagniezdzenia linijki
     */
    static void drawTicks(int tickLength, int nesting) {
        if (tickLength > 0) {
            drawTicks(tickLength - 2, nesting);
            drawOneTick(tickLength, nesting);
            drawTicks(tickLength - 2, nesting);
        }
    }

    /**
     * Rysuje pojedynczy element podzialki wraz z wartoscia
     *
     * @param tickLength dlugosc podzialki
     * @param nesting zagniezdzenie
     * @param label wartosc
     */
    static void drawOneTick(int tickLength, int nesting, int label) {
        /*
        for (int i = 0; i < nesting - tickLength / 2; i++)
            System.out.print(" ");
        for (int i = 0; i < tickLength; i++)
            System.out.print("-");
        for (int i = 0; i < nesting - tickLength / 2; i++)
            System.out.print(" ");
        */

        /*
        System.out.print(stringRepeater(" ", nesting - tickLength / 2));
        System.out.print(stringRepeater("-", tickLength));
        System.out.print(stringRepeater(" ", nesting - tickLength / 2));
        */

        System.out.print(" ".repeat(nesting - tickLength / 2));
        System.out.print("-".repeat(tickLength));
        System.out.print(" ".repeat(nesting - tickLength / 2));
        if (label >= 0) System.out.print(" " + label);
        System.out.print("\n");
    }

    /**
     * Rysuje pojedynczy element podzialki bez wartosci
     *
     * @param tickLength dlugosc podzialki
     * @param nesting zagniezdzenie
     */
    static void drawOneTick(int tickLength, int nesting) {
        drawOneTick(tickLength, nesting, -1);
    }


    /**
     * Powtarza podany String n razy
     *
     * @param toRepeat napis do powtorzenia
     * @param n ilosc powtorzen
     * @return toRepeat powtorzone n razy
     */
    static String stringRepeater(String toRepeat, int n) {
        if(n > 0) return stringRepeater(toRepeat, n - 1) + toRepeat;
        return "";
    }
}
