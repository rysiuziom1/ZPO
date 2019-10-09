public class Ruler {
    public static void main(String[] args) {
        drawRuler(2, 5);
    }

    /**
     * Rysuje dwustronna linijke o zadanych dlugosci i poziomie zagniezdzenia
     *
     * @param length dlugosc linijki
     * @param nesting poziom zagniezdzenia
     */
    private static void drawRuler(int length, int nesting) {
        int majorLength = nesting * 2;
        drawOneTick(majorLength, nesting, 0);
        for (int i = 1; i <= length; i++) {
            drawTicks(majorLength - 2, nesting);
            drawOneTick(majorLength, nesting, i);
        }
    }

    /**
     * Rekurencyjnie rysuje podzialke pomiedzy glownymi wartosciami na linijce
     *
     * @param tickLength dlugosc podzialki
     * @param nesting poziom zagniezdzenia linijki
     */
    private static void drawTicks(int tickLength, int nesting) {
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
    private static void drawOneTick(int tickLength, int nesting, int label) {
        for (int i = 0; i < nesting - tickLength  / 2; i++)
            System.out.print(" ");
        for (int i = 0; i < tickLength; i++)
            System.out.print("-");
        for (int i = 0; i < nesting - tickLength / 2; i++)
            System.out.print(" ");
        if (label >= 0) System.out.print(" " + label);
        System.out.print("\n");
    }

    /**
     * Rysuje pojedynczy element podzialki bez wartosci
     *
     * @param tickLength dlugosc podzialki
     * @param nesting zagniezdzenie
     */
    private static void drawOneTick(int tickLength, int nesting) {
        drawOneTick(tickLength, nesting, -1);
    }
}
