
import java.util.Scanner;

public class Mundial48 {

    // ================================================================
    //  COLORES ANSI - fondo solido
    // ================================================================
    static final String RESET  = "\u001B[0m";
    static final String BG_RED    = "\u001B[41m";
    static final String BG_GREEN  = "\u001B[42m";
    static final String BG_YELLOW = "\u001B[43m";
    static final String BG_BLUE   = "\u001B[44m";
    static final String BG_WHITE  = "\u001B[47m";
    static final String BG_BLACK  = "\u001B[40m";
    static final String BG_CYAN   = "\u001B[46m";   // celeste
    static final String BG_ORANGE = "\u001B[48;5;208m"; // naranja
    static final String BG_MAROON = "\u001B[48;5;88m";  // granate (Qatar)
    static final String BG_LGRAY  = "\u001B[48;5;250m"; // gris claro extra

    // Convierte caracter de patron a codigo ANSI de fondo
    static String colorDe(char c) {
        if (c == 'N') return BG_BLACK;
        if (c == 'R') return BG_RED;
        if (c == 'B') return BG_BLUE;
        if (c == 'V') return BG_GREEN;
        if (c == 'A') return BG_YELLOW;
        if (c == 'W') return BG_WHITE;
        if (c == 'C') return BG_CYAN;
        if (c == 'O') return BG_ORANGE;
        if (c == 'M') return BG_MAROON;
        return BG_WHITE;
    }

    // ================================================================
    //  PATRONES BASE de las banderas (6 filas x 36 cols)
    //  Cada char representa un color: N R B V A W C O M
    //  Estos patrones son la base; el metodo escalarBandera
    //  los escala a cualquiera de los 4 tamaños
    // ================================================================

    // Retorna el patron base 6x36 de cada pais
    static String[] patronBase(int idx) {
        // Formato: 6 filas, cada fila con exactamente 36 caracteres
        switch (idx) {
            // 0 ALEMANIA: negro/rojo/amarillo
            case 0: return new String[]{
                "NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN",
                "NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN",
                "RRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRR",
                "RRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRR",
                "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
                "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"};

            // 1 ARABIA SAUDITA: verde con franja blanca abajo
            case 1: return new String[]{
                "VVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVV",
                "VVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVV",
                "VVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVV",
                "VVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVV",
                "VVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVV",
                "WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW"};

            // 2 ARGELIA: verde|blanco vertical
            case 2: return new String[]{
                "VVVVVVVVVVVVVVVVVVWWWWWWWWWWWWWWWWWW",
                "VVVVVVVVVVVVVVVVVVWWWWWWWWWWWWWWWWWW",
                "VVVVVVVVVVVVVVVVVVWWWWWWWWWWWWWWWWWW",
                "VVVVVVVVVVVVVVVVVVWWWWWWWWWWWWWWWWWW",
                "VVVVVVVVVVVVVVVVVVWWWWWWWWWWWWWWWWWW",
                "VVVVVVVVVVVVVVVVVVWWWWWWWWWWWWWWWWWW"};

            // 3 ARGENTINA: celeste/blanco/celeste
            case 3: return new String[]{
                "CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC",
                "CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC",
                "WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW",
                "WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW",
                "CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC",
                "CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC"};

            // 4 AUSTRALIA: azul con canton rojo izq superior
            case 4: return new String[]{
                "RRRRRRRRRRRRRRRRRRBBBBBBBBBBBBBBBBBB",
                "RRRRRRRRRRRRRRRRRRBBBBBBBBBBBBBBBBBB",
                "RRRRRRRRRRRRRRRRRRBBBBBBBBBBBBBBBBBB",
                "BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB",
                "BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB",
                "BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB"};

            // 5 AUSTRIA: rojo/blanco/rojo
            case 5: return new String[]{
                "RRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRR",
                "RRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRR",
                "WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW",
                "WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW",
                "RRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRR",
                "RRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRR"};

            // 6 BELGICA: negro|amarillo|rojo vertical
            case 6: return new String[]{
                "NNNNNNNNNNNNAAAAAAAAAAAARRRRRRRRRRRR",
                "NNNNNNNNNNNNAAAAAAAAAAAARRRRRRRRRRRR",
                "NNNNNNNNNNNNAAAAAAAAAAAARRRRRRRRRRRR",
                "NNNNNNNNNNNNAAAAAAAAAAAARRRRRRRRRRRR",
                "NNNNNNNNNNNNAAAAAAAAAAAARRRRRRRRRRRR",
                "NNNNNNNNNNNNAAAAAAAAAAAARRRRRRRRRRRR"};

            // 7 BOSNIA: azul con diagonal amarilla
            case 7: return new String[]{
                "AAABBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB",
                "BBBAAABBBBBBBBBBBBBBBBBBBBBBBBBBBBBB",
                "BBBBBBAAABBBBBBBBBBBBBBBBBBBBBBBBBBB",
                "BBBBBBBBBAAABBBBBBBBBBBBBBBBBBBBBBBB",
                "BBBBBBBBBBBBAAABBBBBBBBBBBBBBBBBBBBB",
                "BBBBBBBBBBBBBBBAAABBBBBBBBBBBBBBBBBB"};

            // 8 BRASIL: verde, rombo amarillo, centro azul
            case 8: return new String[]{
                "VVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVV",
                "VVVVVVVVVVAAAAAAAAAAAAAAAAAAAVVVVVVVV",
                "VVVVVVAAAAAAAAAAAAABBBBBAAAAAAAAVVVVVV",
                "VVVVVVAAAAAAAAAAAAABBBBBAAAAAAAAVVVVVV",
                "VVVVVVVVVVAAAAAAAAAAAAAAAAAAAVVVVVVVV",
                "VVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVV"};

            // 9 CABO VERDE: azul/blanco/rojo/azul
            case 9: return new String[]{
                "BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB",
                "BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB",
                "WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW",
                "RRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRR",
                "BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB",
                "BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB"};

            // 10 CANADA: rojo|blanco|rojo vertical
            case 10: return new String[]{
                "RRRRRRWWWWWWWWWWWWWWWWWWWWWWWWRRRRRR",
                "RRRRRRWWWWWWWWWWWWWWWWWWWWWWWWRRRRRR",
                "RRRRRRWWWWWWWWWWWWWWWWWWWWWWWWRRRRRR",
                "RRRRRRWWWWWWWWWWWWWWWWWWWWWWWWRRRRRR",
                "RRRRRRWWWWWWWWWWWWWWWWWWWWWWWWRRRRRR",
                "RRRRRRWWWWWWWWWWWWWWWWWWWWWWWWRRRRRR"};

            // 11 COLOMBIA: amarillo(mitad)/azul/rojo
            case 11: return new String[]{
                "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
                "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
                "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
                "BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB",
                "BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB",
                "RRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRR"};

            // 12 COREA DEL SUR: blanco con marcas rojo/azul centrales
            case 12: return new String[]{
                "WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW",
                "WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW",
                "WWWWWWWWWWWWWWWWRRWWWWWWWWWWWWWWWWWW",
                "WWWWWWWWWWWWWWWWBBWWWWWWWWWWWWWWWWWW",
                "WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW",
                "WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW"};

            // 13 COSTA DE MARFIL: naranja|blanco|verde vertical
            case 13: return new String[]{
                "OOOOOOOOOOOOWWWWWWWWWWWWVVVVVVVVVVVV",
                "OOOOOOOOOOOOWWWWWWWWWWWWVVVVVVVVVVVV",
                "OOOOOOOOOOOOWWWWWWWWWWWWVVVVVVVVVVVV",
                "OOOOOOOOOOOOWWWWWWWWWWWWVVVVVVVVVVVV",
                "OOOOOOOOOOOOWWWWWWWWWWWWVVVVVVVVVVVV",
                "OOOOOOOOOOOOWWWWWWWWWWWWVVVVVVVVVVVV"};

            // 14 CROACIA: rojo/blanco/azul
            case 14: return new String[]{
                "RRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRR",
                "RRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRR",
                "WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW",
                "WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW",
                "BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB",
                "BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB"};

            // 15 CURAZAO: azul con franja amarilla
            case 15: return new String[]{
                "BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB",
                "BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB",
                "BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB",
                "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
                "BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB",
                "BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB"};

            // 16 ECUADOR: amarillo/azul/rojo
            case 16: return new String[]{
                "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
                "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
                "BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB",
                "BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB",
                "RRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRR",
                "RRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRR"};

            // 17 EGIPTO: rojo/blanco/negro
            case 17: return new String[]{
                "RRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRR",
                "RRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRR",
                "WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW",
                "WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW",
                "NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN",
                "NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN"};

            // 18 ESCOCIA: azul con aspa blanca (San Andres)
            case 18: return new String[]{
                "WBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBW",
                "BBWBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBWBB",
                "BBBBBWBBBBBBBBBBBBBBBBBBBBBBBWBBBBBB",
                "BBBBBBBBBWBBBBBBBBBBBBBBBWBBBBBBBBBB",
                "BBBBBBBBBBBBBWBBBBBBBWBBBBBBBBBBBBBB",
                "BBBBBBBBBBBBBBBBWWBBBBBBBBBBBBBBBBBB"};

            // 19 ESPANA: rojo/amarillo(ancho)/rojo
            case 19: return new String[]{
                "RRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRR",
                "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
                "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
                "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
                "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
                "RRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRR"};

            // 20 ESTADOS UNIDOS: franjas rojo/blanco
            case 20: return new String[]{
                "RRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRR",
                "WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW",
                "RRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRR",
                "WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW",
                "RRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRR",
                "WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW"};

            // 21 FRANCIA: azul|blanco|rojo vertical
            case 21: return new String[]{
                "BBBBBBBBBBBBWWWWWWWWWWWWRRRRRRRRRRRR",
                "BBBBBBBBBBBBWWWWWWWWWWWWRRRRRRRRRRRR",
                "BBBBBBBBBBBBWWWWWWWWWWWWRRRRRRRRRRRR",
                "BBBBBBBBBBBBWWWWWWWWWWWWRRRRRRRRRRRR",
                "BBBBBBBBBBBBWWWWWWWWWWWWRRRRRRRRRRRR",
                "BBBBBBBBBBBBWWWWWWWWWWWWRRRRRRRRRRRR"};

            // 22 GHANA: rojo/amarillo/verde
            case 22: return new String[]{
                "RRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRR",
                "RRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRR",
                "AAAAAAAAAAAAAAAANNAAAAAAAAAAAAAAAAAA",
                "AAAAAAAAAAAAAAAANNAAAAAAAAAAAAAAAAAA",
                "VVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVV",
                "VVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVV"};

            // 23 HAITI: azul/rojo
            case 23: return new String[]{
                "BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB",
                "BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB",
                "BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB",
                "RRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRR",
                "RRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRR",
                "RRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRR"};

            // 24 INGLATERRA: blanco con cruz roja horizontal
            case 24: return new String[]{
                "WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW",
                "WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW",
                "RRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRR",
                "RRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRR",
                "WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW",
                "WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW"};

            // 25 IRAN: verde/blanco/rojo
            case 25: return new String[]{
                "VVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVV",
                "VVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVV",
                "WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW",
                "WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW",
                "RRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRR",
                "RRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRR"};

            // 26 IRAK: rojo/blanco/negro
            case 26: return new String[]{
                "RRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRR",
                "RRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRR",
                "WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW",
                "WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW",
                "NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN",
                "NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN"};

            // 27 JAPON: blanco con circulo rojo central
            case 27: return new String[]{
                "WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW",
                "WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW",
                "WWWWWWWWWWWWWWWWRRRRRWWWWWWWWWWWWWWW",
                "WWWWWWWWWWWWWWWWRRRRRWWWWWWWWWWWWWWW",
                "WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW",
                "WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW"};

            // 28 JORDANIA: negro/blanco/verde + triangulo rojo izq
            case 28: return new String[]{
                "RRRNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN",
                "RRRRRRNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN",
                "RRRRRRRRRRWWWWWWWWWWWWWWWWWWWWWWWWWW",
                "RRRRRRRRRRWWWWWWWWWWWWWWWWWWWWWWWWWW",
                "RRRRRRRVVVVVVVVVVVVVVVVVVVVVVVVVVVVV",
                "RRRRVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVV"};

            // 29 MARRUECOS: rojo con estrella verde
            case 29: return new String[]{
                "RRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRR",
                "RRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRR",
                "RRRRRRRRRRRRRRRRRRVRRRRRRRRRRRRRRRRR",
                "RRRRRRRRRRRRRRRVVVVVVRRRRRRRRRRRRRRR",
                "RRRRRRRRRRRRRRRRRRVRRRRRRRRRRRRRRRRR",
                "RRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRR"};

            // 30 MEXICO: verde|blanco|rojo vertical
            case 30: return new String[]{
                "VVVVVVVVVVVVWWWWWWWWWWWWRRRRRRRRRRRR",
                "VVVVVVVVVVVVWWWWWWWWWWWWRRRRRRRRRRRR",
                "VVVVVVVVVVVVWWWWWWWWWWWWRRRRRRRRRRRR",
                "VVVVVVVVVVVVWWWWWWWWWWWWRRRRRRRRRRRR",
                "VVVVVVVVVVVVWWWWWWWWWWWWRRRRRRRRRRRR",
                "VVVVVVVVVVVVWWWWWWWWWWWWRRRRRRRRRRRR"};

            // 31 NORUEGA: rojo con cruz azul/blanca
            case 31: return new String[]{
                "RRRRRWWBBBWWRRRRRRRRRRRRRRRRRRRRRRRR",
                "RRRRRWWBBBWWRRRRRRRRRRRRRRRRRRRRRRRR",
                "WWWWWWWBBBWWWWWWWWWWWWWWWWWWWWWWWWWW",
                "BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB",
                "WWWWWWWBBBWWWWWWWWWWWWWWWWWWWWWWWWWW",
                "RRRRRWWBBBWWRRRRRRRRRRRRRRRRRRRRRRRR"};

            // 32 NUEVA ZELANDA: azul con canton rojo
            case 32: return new String[]{
                "RRRRRRRRRRRRRRBBBBBBBBBBBBBBBBBBBBBB",
                "RRRRRRRRRRRRRRBBBBBBBBBBBBBBBBBBBBBB",
                "RRRRRRRRRRRRRRBBBBBBBBBBBBBBBBBBBBBB",
                "BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB",
                "BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB",
                "BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB"};

            // 33 PAISES BAJOS: rojo/blanco/azul
            case 33: return new String[]{
                "RRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRR",
                "RRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRR",
                "WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW",
                "WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW",
                "BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB",
                "BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB"};

            // 34 PANAMA: cuadrantes blanco-rojo / azul-blanco
            case 34: return new String[]{
                "WWWWWWWWWWWWWWWWWWRRRRRRRRRRRRRRRRRR",
                "WWWWWWWWWWWWWWWWWWRRRRRRRRRRRRRRRRRR",
                "WWWWWWWWWWWWWWWWWWRRRRRRRRRRRRRRRRRR",
                "BBBBBBBBBBBBBBBBBBWWWWWWWWWWWWWWWWWW",
                "BBBBBBBBBBBBBBBBBBWWWWWWWWWWWWWWWWWW",
                "BBBBBBBBBBBBBBBBBBWWWWWWWWWWWWWWWWWW"};

            // 35 PARAGUAY: rojo/blanco/azul
            case 35: return new String[]{
                "RRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRR",
                "RRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRR",
                "WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW",
                "WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW",
                "BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB",
                "BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB"};

            // 36 PORTUGAL: verde(izq)|rojo(der) vertical
            case 36: return new String[]{
                "VVVVVVVVVVVVVVRRRRRRRRRRRRRRRRRRRRRRR",
                "VVVVVVVVVVVVVVRRRRRRRRRRRRRRRRRRRRRRR",
                "VVVVVVVVVVVVVVRRRRRRRRRRRRRRRRRRRRRRR",
                "VVVVVVVVVVVVVVRRRRRRRRRRRRRRRRRRRRRRR",
                "VVVVVVVVVVVVVVRRRRRRRRRRRRRRRRRRRRRRR",
                "VVVVVVVVVVVVVVRRRRRRRRRRRRRRRRRRRRRRR"};

            // 37 QATAR: granate con zigzag blanco
            case 37: return new String[]{
                "MMMMMMMWWWWWWWWWWWWWWWWWWWWWWWWWWWWW",
                "MMMMMMMMMWWWWWWWWWWWWWWWWWWWWWWWWWWW",
                "MMMMMMMWWWWWWWWWWWWWWWWWWWWWWWWWWWWW",
                "MMMMMMMMMWWWWWWWWWWWWWWWWWWWWWWWWWWW",
                "MMMMMMMWWWWWWWWWWWWWWWWWWWWWWWWWWWWW",
                "MMMMMMMMMWWWWWWWWWWWWWWWWWWWWWWWWWWW"};

            // 38 RD CONGO: azul con diagonal amarilla
            case 38: return new String[]{
                "AAABBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB",
                "BBBAAABBBBBBBBBBBBBBBBBBBBBBBBBBBBBB",
                "BBBBBBBAAABBBBBBBBBBBBBBBBBBBBBBBBBB",
                "BBBBBBBBBBBAAABBBBBBBBBBBBBBBBBBBBBB",
                "BBBBBBBBBBBBBBBAAABBBBBBBBBBBBBBBBBB",
                "BBBBBBBBBBBBBBBBBBBAAABBBBBBBBBBBBBB"};

            // 39 REP. CHECA: blanco/rojo con triangulo azul izq
            case 39: return new String[]{
                "BBBWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW",
                "BBBBBBBWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW",
                "BBBBBBBBBBBBWWWWWWWWWWWWWWWWWWWWWWWWW",
                "BBBBBBBBBBBBRRRRRRRRRRRRRRRRRRRRRRRRRR",
                "BBBBBBBRRRRRRRRRRRRRRRRRRRRRRRRRRRRR",
                "BBBRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRR"};

            // 40 SENEGAL: verde|amarillo|rojo vertical
            case 40: return new String[]{
                "VVVVVVVVVVVVAAAAAAAAAAAARRRRRRRRRRRR",
                "VVVVVVVVVVVVAAAAAAAAAAAARRRRRRRRRRRR",
                "VVVVVVVVVVVVAAAAAAAAAAAARRRRRRRRRRRR",
                "VVVVVVVVVVVVAAAAAAAAAAAARRRRRRRRRRRR",
                "VVVVVVVVVVVVAAAAAAAAAAAARRRRRRRRRRRR",
                "VVVVVVVVVVVVAAAAAAAAAAAARRRRRRRRRRRR"};

            // 41 SUDAFRICA: multicolor diagonal
            case 41: return new String[]{
                "NNNNNNNNAAAAAAARRRRRRRRRRRRRRRRRRRRRRR",
                "NNNNNNNNNNNAAAAAAARRRRRRRRRRRRRRRRRRRRR",
                "NNNNNNNNNNNNNNAAAAAAARRRRRRRRRRRRRRRRR",
                "NNNNNNNNNNNNNNAAAAAAVVVVVVVVVVVVVVVVVV",
                "NNNNNNNNNNNNAAAAAVVVVVVVVVVVVVVVVVVVVV",
                "NNNNNNNNAAAAVVVVVVVVVVVVVVVVVVVVVVVVVV"};

            // 42 SUECIA: azul con cruz amarilla
            case 42: return new String[]{
                "BBBBBBBBBBBAAABBBBBBBBBBBBBBBBBBBBBB",
                "BBBBBBBBBBBAAABBBBBBBBBBBBBBBBBBBBBB",
                "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
                "BBBBBBBBBBBAAABBBBBBBBBBBBBBBBBBBBBB",
                "BBBBBBBBBBBAAABBBBBBBBBBBBBBBBBBBBBB",
                "BBBBBBBBBBBAAABBBBBBBBBBBBBBBBBBBBBB"};

            // 43 SUIZA: rojo con cruz blanca central
            case 43: return new String[]{
                "RRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRR",
                "RRRRRRRRRRRRRRRRWWWWWWWRRRRRRRRRRRRR",
                "RRRRRRRRRRRWWWWWWWWWWWWWWWWWRRRRRRRR",
                "RRRRRRRRRRRWWWWWWWWWWWWWWWWWRRRRRRRR",
                "RRRRRRRRRRRRRRRRWWWWWWWRRRRRRRRRRRRR",
                "RRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRR"};

            // 44 TUNEZ: rojo con circulo blanco central
            case 44: return new String[]{
                "RRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRR",
                "RRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRR",
                "RRRRRRRRRRRRRRRRWWWWWRRRRRRRRRRRRRRR",
                "RRRRRRRRRRRRRRRRWWWWWRRRRRRRRRRRRRRR",
                "RRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRR",
                "RRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRR"};

            // 45 TURQUIA: rojo con luna/estrella blanca
            case 45: return new String[]{
                "RRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRR",
                "RRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRR",
                "RRRRRRRWWWWWRRRWRRRRRRRRRRRRRRRRRRRR",
                "RRRRRRRWWWWWRRWRWRRRRRRRRRRRRRRRRRRR",
                "RRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRR",
                "RRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRR"};

            // 46 URUGUAY: franjas blanco/celeste con sol amarillo izq
            case 46: return new String[]{
                "AAAWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW",
                "AAACCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC",
                "AAAWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW",
                "AAACCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC",
                "AAAWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW",
                "AAACCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC"};

            // 47 UZBEKISTAN: celeste/blanco/verde
            default: return new String[]{
                "CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC",
                "CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC",
                "WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW",
                "WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW",
                "VVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVV",
                "VVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVV"};
        }
    }

    // ================================================================
    //  ESCALAR Y PINTAR BANDERA
    // ================================================================
    static void imprimirBandera(int idx, int tam) {
        // filas y columnas destino
        int filas, cols;
        if (tam == 1) { filas = 12; cols = 36; }
        else if (tam == 2) { filas = 9;  cols = 27; }
        else if (tam == 3) { filas = 6;  cols = 18; }
        else              { filas = 3;  cols = 9;  }

        String[] patron = patronBase(idx);
        int patFilas = 6;
        int patCols  = patron[0].length();

        // Para cada fila destino mapear a fila patron
        for (int f = 0; f < filas; f++) {
            int pf = (f * patFilas) / filas;   // fila patron mapeada
            if (pf >= patFilas) pf = patFilas - 1;
            String filaPatron = patron[pf];

            String lineaSalida = "";
            char colorActual = 0;
            String bloque = "";

            for (int c = 0; c < cols; c++) {
                int pc = (c * patCols) / cols;  // col patron mapeada
                int pMax = filaPatron.length();
                if (pc >= pMax) pc = pMax - 1;
                char ch = (pc >= 0) ? filaPatron.charAt(pc) : 'W';

                if (ch != colorActual) {
                    if (colorActual != 0) {
                        lineaSalida = lineaSalida + colorDe(colorActual) + bloque + RESET;
                    }
                    colorActual = ch;
                    bloque = "  ";  // 2 espacios por celda para mejor visualizacion
                } else {
                    bloque = bloque + "  ";
                }
            }
            // Ultimo bloque
            if (colorActual != 0) {
                lineaSalida = lineaSalida + colorDe(colorActual) + bloque + RESET;
            }
            System.out.println(lineaSalida);
        }
    }

    // ================================================================
    //  MAIN
    // ================================================================
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // ------------------------------------------------------------
        //  DATOS
        // ------------------------------------------------------------
        String[] paises = {
            "Alemania",        // 0
            "Arabia Saudita",  // 1
            "Argelia",         // 2
            "Argentina",       // 3
            "Australia",       // 4
            "Austria",         // 5
            "Belgica",         // 6
            "Bosnia-Herz.",    // 7
            "Brasil",          // 8
            "Cabo Verde",      // 9
            "Canada",          // 10
            "Colombia",        // 11
            "Corea del Sur",   // 12
            "Costa de Marfil", // 13
            "Croacia",         // 14
            "Curazao",         // 15
            "Ecuador",         // 16
            "Egipto",          // 17
            "Escocia",         // 18
            "Espana",          // 19
            "Estados Unidos",  // 20
            "Francia",         // 21
            "Ghana",           // 22
            "Haiti",           // 23
            "Inglaterra",      // 24
            "Iran",            // 25
            "Irak",            // 26
            "Japon",           // 27
            "Jordania",        // 28
            "Marruecos",       // 29
            "Mexico",          // 30
            "Noruega",         // 31
            "Nueva Zelanda",   // 32
            "Paises Bajos",    // 33
            "Panama",          // 34
            "Paraguay",        // 35
            "Portugal",        // 36
            "Qatar",           // 37
            "RD Congo",        // 38
            "Rep. Checa",      // 39
            "Senegal",         // 40
            "Sudafrica",       // 41
            "Suecia",          // 42
            "Suiza",           // 43
            "Tunez",           // 44
            "Turquia",         // 45
            "Uruguay",         // 46
            "Uzbekistan"       // 47
        };

        String[] capitales = {
            "Berlin","Riad","Argel","Buenos Aires","Canberra","Viena",
            "Bruselas","Sarajevo","Brasilia","Praia","Ottawa","Bogota",
            "Seul","Yamoussoukro","Zagreb","Willemstad","Quito","El Cairo",
            "Edimburgo","Madrid","Washington D.C.","Paris","Accra",
            "Puerto Principe","Londres","Teheran","Bagdad","Tokio",
            "Aman","Rabat","Ciudad de Mexico","Oslo","Wellington",
            "Amsterdam","Ciudad de Panama","Asuncion","Lisboa","Doha",
            "Kinshasa","Praga","Dakar","Pretoria","Estocolmo","Berna",
            "Tunez ciudad","Ankara","Montevideo","Taskent"
        };

        String[] jugadores = {
            "Muller, Havertz, Kimmich",
            "Al-Dawsari, Al-Shehri",
            "Mahrez, Bennacer",
            "Messi, Di Maria, Martinez",
            "Leckie, Redmayne",
            "Sabitzer, Arnautovic",
            "De Bruyne, Lukaku, Courtois",
            "Dzeko, Sehic",
            "Vinicius, Rodrygo, Alisson",
            "Dias, Andrade",
            "Davies, Johnston",
            "James, Arias, Cordoba",
            "Son, Kim Min-jae",
            "Pepe, Zaha, Sangare",
            "Modric, Gvardiol, Livakovic",
            "Fer, Cijntje",
            "Caicedo, Valencia",
            "Salah, Trezeguet",
            "McGinn, Tierney",
            "Yamal, Pedri, Morata",
            "Pulisic, Turner, McKennie",
            "Mbappe, Griezmann, Hernandez",
            "Ayew, Kudus",
            "Geffrard, Cantave",
            "Bellingham, Kane, Saka",
            "Taremi, Azmoun",
            "Al-Hamadi, Hussein",
            "Mitoma, Endo, Maeda",
            "Al-Naimat, Bmeik",
            "Ziyech, En-Nesyri, Bounou",
            "H.Lozano, Jimenez, Ochoa",
            "Haaland, Odegaard",
            "Wood, Paasi",
            "Van Dijk, De Jong, Dumfries",
            "Blackman, Davis",
            "Almada, Gimenez",
            "Ronaldo, Bernardo, Cancelo",
            "Al-Moez, Boudiaf",
            "Tuanzebe, Mbokani",
            "Schick, Soucek",
            "Mane, Gueye, Mendy",
            "Bongani, Dolly",
            "Isak, Forsberg, Ekdal",
            "Xhaka, Shaqiri, Sommer",
            "Msakni, Khazri",
            "Calhanoglu, Guler",
            "Valverde, Nunez, Bentancur",
            "Shomurodov, Djeparov"
        };

        // Grupos: indices de paises[]
        // ID del pais en el enunciado = indice+1
        // Los grupos usan indices 0-47 (= ID-1)
        int[][] grupos = {
            {30, 41, 12, 39},  // A: Mexico(31), Sudafrica(42), Corea del Sur(13), Rep.Checa(40)
            {10,  7, 37, 43},  // B: Canada(11), Bosnia(8), Qatar(38), Suiza(44)
            { 8, 29, 18, 23},  // C: Brasil(9), Marruecos(30), Escocia(19), Haiti(24)
            {20,  3, 28,  5},  // D: EE.UU.(21), Argentina(4), Jordania(29), Austria(6)
            { 0, 13, 16, 15},  // E: Alemania(1), C.Marfil(14), Ecuador(17), Curazao(16)
            {33, 27, 44, 42},  // F: P.Bajos(34), Japon(28), Tunez(45), Suecia(43)
            { 6,  4, 17, 25},  // G: Belgica(7), Australia(5), Egipto(18), Iran(26)
            {19, 46,  9,  1},  // H: Espana(20), Uruguay(47), CaboVerde(10), Arabia(2)
            {21, 31, 40, 26},  // I: Francia(22), Noruega(32), Senegal(41), Irak(27)
            { 2, 45, 35, 32},  // J: Argelia(3), Turquia(46), Paraguay(36), N.Zelanda(33)
            {36, 11, 38, 47},  // K: Portugal(37), Colombia(12), RD Congo(39), Uzbekistan(48)
            {24, 14, 22, 34}   // L: Inglaterra(25), Croacia(15), Ghana(23), Panama(35)
        };

        // tabla[i]: PJ PG PE PP GF GC PTS TA TR
        //           0   1  2  3  4  5   6  7  8
        int[][] tabla = new int[48][9];

        // ------------------------------------------------------------
        //  MENU PRINCIPAL
        // ------------------------------------------------------------
        int op;
        do {
            System.out.println("\n╔══════════════════════════════════╗");
            System.out.println("║      MUNDIAL FIFA 2026           ║");
            System.out.println("║    USA  ·  CANADA  ·  MEXICO     ║");
            System.out.println("╠══════════════════════════════════╣");
            System.out.println("║  1. Fixture por grupos           ║");
            System.out.println("║  2. Tabla de posiciones          ║");
            System.out.println("║  3. Actualizar partido           ║");
            System.out.println("║  4. Informacion de pais          ║");
            System.out.println("║  0. Salir                        ║");
            System.out.println("╚══════════════════════════════════╝");
            System.out.print("Opcion: ");
            while (!sc.hasNextInt()) { sc.next(); }
            op = sc.nextInt();

            // ----------------------------------------------------------
            //  OPCION 1: FIXTURE
            // ----------------------------------------------------------
            if (op == 1) {
                System.out.println("\n--- FIXTURE ---");
                System.out.println("1. Ver todos los grupos");
                System.out.println("2. Ver un grupo especifico");
                System.out.print("Opcion: ");
                while (!sc.hasNextInt()) { sc.next(); }
                int opFix = sc.nextInt();

                int gInicio = 0, gFin = 12;
                if (opFix == 2) {
                    System.out.print("Grupo (A-L): ");
                    String letra = sc.next().toUpperCase().trim();
                    int g = letra.charAt(0) - 'A';
                    if (g >= 0 && g < 12) { gInicio = g; gFin = g + 1; }
                    else { System.out.println("Grupo invalido."); gFin = 0; }
                }

                for (int g = gInicio; g < gFin; g++) {
                    System.out.println("\n┌──────────────────────────────────────────────────┐");
                    System.out.println("│                   GRUPO " + (char)('A' + g) + "                        │");
                    System.out.println("│ Equipos participantes:                           │");
                    for (int x = 0; x < 4; x++) {
                        int idx = grupos[g][x];
                        System.out.printf("│   %d. %-44s│%n", x+1, paises[idx] + " (ID " + (idx+1) + ")");
                    }
                    System.out.println("├──────┬───────────────────────┬───────────────────┤");
                    System.out.println("│ Part │ Local                 │ Visitante         │");
                    System.out.println("├──────┼───────────────────────┼───────────────────┤");
                    int partido = 1;
                    for (int ii = 0; ii < 4; ii++) {
                        for (int jj = ii + 1; jj < 4; jj++) {
                            System.out.printf("│  %2d  │ %-21s │ %-17s │%n",
                                partido++,
                                paises[grupos[g][ii]],
                                paises[grupos[g][jj]]);
                        }
                    }
                    System.out.println("└──────┴───────────────────────┴───────────────────┘");
                }

            // ----------------------------------------------------------
            //  OPCION 2: TABLA DE POSICIONES
            // ----------------------------------------------------------
            } else if (op == 2) {
                System.out.println("\n--- TABLA DE POSICIONES ---");
                System.out.println("1. Ver todos los grupos");
                System.out.println("2. Ver un grupo especifico");
                System.out.print("Opcion: ");
                while (!sc.hasNextInt()) { sc.next(); }
                int opTab = sc.nextInt();

                int gInicio = 0, gFin = 12;
                if (opTab == 2) {
                    System.out.print("Grupo (A-L): ");
                    String letra = sc.next().toUpperCase().trim();
                    int g = letra.charAt(0) - 'A';
                    if (g >= 0 && g < 12) { gInicio = g; gFin = g + 1; }
                    else { System.out.println("Grupo invalido."); gFin = 0; }
                }

                // Paginar: mostrar 6 grupos por pantalla
                // Dividimos los grupos seleccionados en paginas de maximo 6
                int pagTam = 6;
                int totalGrupos = gFin - gInicio;
                int paginas = (totalGrupos + pagTam - 1) / pagTam;

                for (int pag = 0; pag < paginas; pag++) {
                    int pGini = gInicio + pag * pagTam;
                    int pGfin = pGini + pagTam;
                    if (pGfin > gFin) pGfin = gFin;

                    System.out.println("\n╔══════════════════════════════════════════════════════════════════════════════╗");
                    System.out.println("║          TABLA DE POSICIONES - Grupos " + (char)('A'+pGini) + " a " + (char)('A'+pGfin-1) + "                              ║");
                    System.out.println("╚══════════════════════════════════════════════════════════════════════════════╝");

                    for (int g = pGini; g < pGfin; g++) {
                        // Copia de indices para ordenar
                        int[] eq = new int[4];
                        for (int x = 0; x < 4; x++) eq[x] = grupos[g][x];

                        // Ordenar por puntos, luego diferencia de goles (burbuja)
                        for (int ii = 0; ii < 3; ii++) {
                            for (int jj = 0; jj < 3 - ii; jj++) {
                                boolean swap = false;
                                if (tabla[eq[jj]][6] < tabla[eq[jj+1]][6]) {
                                    swap = true;
                                } else if (tabla[eq[jj]][6] == tabla[eq[jj+1]][6]) {
                                    int dif1 = tabla[eq[jj]][4]   - tabla[eq[jj]][5];
                                    int dif2 = tabla[eq[jj+1]][4] - tabla[eq[jj+1]][5];
                                    if (dif1 < dif2) swap = true;
                                    else if (dif1 == dif2 && tabla[eq[jj]][4] < tabla[eq[jj+1]][4]) 
                                        swap = true; // Goles a favor
                                }
                                if (swap) {
                                    int tmp = eq[jj]; eq[jj] = eq[jj+1]; eq[jj+1] = tmp;
                                }
                            }
                        }

                        System.out.println("\n  GRUPO " + (char)('A' + g));
                        System.out.println("  ┌─────────────────┬──┬──┬──┬──┬──┬──┬──┬──┬───┬──────────────┐");
                        System.out.println("  │ Pais            │PJ│PG│PE│PP│GF│GC│DG│TA│ TR│  PTS         │");
                        System.out.println("  ├─────────────────┼──┼──┼──┼──┼──┼──┼──┼──┼───┼──────────────┤");

                        for (int pos = 0; pos < 4; pos++) {
                            int idx = eq[pos];
                            int dif = tabla[idx][4] - tabla[idx][5];
                            String difStr = (dif > 0 ? "+" : "") + dif;

                            // Juego limpio: icono segun tarjetas
                            String limpio;
                            if (tabla[idx][8] > 0) {
                                limpio = " ROJA! ";
                            } else if (tabla[idx][7] >= 3) {
                                limpio = "  AVISO";
                            } else if (tabla[idx][7] == 0) {
                                limpio = " LIMPIO";
                            } else {
                                limpio = "  ok   ";
                            }

                            System.out.println("  │ " + padDer(paises[idx], 15) + " │"
                                + pad2(tabla[idx][0]) + "│"
                                + pad2(tabla[idx][1]) + "│"
                                + pad2(tabla[idx][2]) + "│"
                                + pad2(tabla[idx][3]) + "│"
                                + pad2(tabla[idx][4]) + "│"
                                + pad2(tabla[idx][5]) + "│"
                                + padDer(difStr, 2) + "│"
                                + pad2(tabla[idx][7]) + "│"
                                + pad3(tabla[idx][8]) + "│ "
                                + pad3(tabla[idx][6]) + " " + limpio + " │");
                        }
                        System.out.println("  └─────────────────┴──┴──┴──┴──┴──┴──┴──┴──┴───┴──────────────┘");
                    }

                    System.out.println("\n  PJ=Jugados PG=Ganados PE=Empates PP=Perdidos");
                    System.out.println("  GF=Goles F. GC=Goles C. DG=Dif.Goles");
                    System.out.println("  TA=Tarj.Amarillas TR=Tarj.Rojas PTS=Puntos");
                    System.out.println("  LIMPIO=sin tarjetas | ok=1 amarilla | AVISO=3+ amarillas | ROJA!=roja recibida");

                    if (pag < paginas - 1) {
                        System.out.print("\n  [ENTER] para ver siguiente pagina...");
                        sc.nextLine();
                        sc.nextLine();
                    }
                }

            // ----------------------------------------------------------
            //  OPCION 3: ACTUALIZAR PARTIDO
            // ----------------------------------------------------------
            } else if (op == 3) {
                System.out.println("\n--- ACTUALIZAR PARTIDO ---");
                System.out.println("Ingrese los IDs de los equipos (1-48)");
                System.out.println("Tip: ID = numero de la lista de paises");

                System.out.print("ID equipo 1 (1-48): ");
                while (!sc.hasNextInt()) { sc.next(); }
                int e1 = sc.nextInt() - 1;

                System.out.print("ID equipo 2 (1-48): ");
                while (!sc.hasNextInt()) { sc.next(); }
                int e2 = sc.nextInt() - 1;

                if (e1 < 0 || e1 >= 48 || e2 < 0 || e2 >= 48 || e1 == e2) {
                    System.out.println("IDs invalidos.");
                } else {
                    boolean mismoGrupo = false;
                    for (int g = 0; g < 12; g++) {
                        boolean t1 = false, t2 = false;
                        for (int x = 0; x < 4; x++) {
                            if (grupos[g][x] == e1) t1 = true;
                            if (grupos[g][x] == e2) t2 = true;
                        }
                        if (t1 && t2) { mismoGrupo = true; break; }
                    }

                    if (!mismoGrupo) {
                        System.out.println("ADVERTENCIA: " + paises[e1] + " y " + paises[e2] + " no son del mismo grupo.");
                        System.out.print("Continuar de todas formas? (S/N): ");
                        String conf = sc.next().toUpperCase();
                        if (conf.equals("S")) mismoGrupo = true;
                    }

                    if (mismoGrupo) {
                        System.out.println("\n  Partido: " + paises[e1] + " vs " + paises[e2]);
                        System.out.println("  -----------------------------------------------");

                        System.out.print("  Goles " + paises[e1] + ": ");
                        while (!sc.hasNextInt()) { sc.next(); }
                        int g1 = sc.nextInt();

                        System.out.print("  Goles " + paises[e2] + ": ");
                        while (!sc.hasNextInt()) { sc.next(); }
                        int g2 = sc.nextInt();

                        System.out.print("  T.Amarillas " + paises[e1] + ": ");
                        while (!sc.hasNextInt()) { sc.next(); }
                        int ta1 = sc.nextInt();

                        System.out.print("  T.Rojas " + paises[e1] + ": ");
                        while (!sc.hasNextInt()) { sc.next(); }
                        int tr1 = sc.nextInt();

                        System.out.print("  T.Amarillas " + paises[e2] + ": ");
                        while (!sc.hasNextInt()) { sc.next(); }
                        int ta2 = sc.nextInt();

                        System.out.print("  T.Rojas " + paises[e2] + ": ");
                        while (!sc.hasNextInt()) { sc.next(); }
                        int tr2 = sc.nextInt();

                        // Actualizar estadisticas
                        tabla[e1][0]++;   tabla[e2][0]++;
                        tabla[e1][4] += g1; tabla[e1][5] += g2;
                        tabla[e2][4] += g2; tabla[e2][5] += g1;
                        tabla[e1][7] += ta1; tabla[e1][8] += tr1;
                        tabla[e2][7] += ta2; tabla[e2][8] += tr2;

                        System.out.println("\n  -----------------------------------------------");
                        System.out.println("  RESULTADO: " + paises[e1] + " " + g1 + " - " + g2 + " " + paises[e2]);

                        if (g1 > g2) {
                            tabla[e1][1]++; tabla[e1][6] += 3; tabla[e2][3]++;
                            System.out.println("  GANADOR: " + paises[e1] + " (3 puntos)");
                        } else if (g2 > g1) {
                            tabla[e2][1]++; tabla[e2][6] += 3; tabla[e1][3]++;
                            System.out.println("  GANADOR: " + paises[e2] + " (3 puntos)");
                        } else {
                            tabla[e1][2]++; tabla[e2][2]++;
                            tabla[e1][6]++;  tabla[e2][6]++;
                            System.out.println("  RESULTADO: EMPATE (1 punto cada uno)");
                        }

                        // Resumen tarjetas
                        if (ta1 > 0 || tr1 > 0) {
                            System.out.println("  " + paises[e1] + ": " + ta1 + " amarilla(s), " + tr1 + " roja(s)");
                        }
                        if (ta2 > 0 || tr2 > 0) {
                            System.out.println("  " + paises[e2] + ": " + ta2 + " amarilla(s), " + tr2 + " roja(s)");
                        }
                        System.out.println("  -----------------------------------------------");
                        System.out.println("  Partido actualizado correctamente!");
                    }
                }

            // ----------------------------------------------------------
            //  OPCION 4: INFORMACION DE PAIS + BANDERA
            // ----------------------------------------------------------
            } else if (op == 4) {
                System.out.println("\n--- INFORMACION DE PAIS ---");
                System.out.println("Lista de paises:");

                // Mostrar lista en 3 columnas
                for (int ii = 0; ii < 48; ii++) {
                    System.out.print("  " + padIzq(ii+1, 2) + ". " + padDer(paises[ii], 16));
                    if ((ii + 1) % 3 == 0) System.out.println();
                }
                if (48 % 3 != 0) System.out.println();

                System.out.print("\nID del pais (1-48): ");
                while (!sc.hasNextInt()) { sc.next(); }
                int id = sc.nextInt();

                if (id < 1 || id > 48) {
                    System.out.println("ID invalido.");
                } else {
                    int i = id - 1;

                    // Buscar grupo
                    char grupoLetra = '?';
                    for (int g = 0; g < 12; g++) {
                        for (int x = 0; x < 4; x++) {
                            if (grupos[g][x] == i) grupoLetra = (char)('A' + g);
                        }
                    }

                    // Ficha del pais
                    System.out.println("\n╔════════════════════════════════════════╗");
                    System.out.println("║  " + padDer(paises[i], 38) + "║");
                    System.out.println("╠════════════════════════════════════════╣");
                    System.out.println("║  Capital   : " + padDer(capitales[i], 26) + "║");
                    System.out.println("║  Grupo     : " + padDer("" + grupoLetra, 26) + "║");
                    String jug = jugadores[i];
                    if (jug.length() > 26) jug = jug.substring(0, 25) + ".";
                    System.out.println("║  Estrellas : " + padDer(jug, 26) + "║");
                    System.out.println("╠════════════════════════════════════════╣");
                    System.out.println("║  PJ:" + pad2(tabla[i][0]) +
                        "  PG:" + pad2(tabla[i][1]) +
                        "  PE:" + pad2(tabla[i][2]) +
                        "  PP:" + pad2(tabla[i][3]) +
                        "  PTS:" + pad3(tabla[i][6]) + "           ║");
                    System.out.println("║  GF:" + pad2(tabla[i][4]) +
                        "  GC:" + pad2(tabla[i][5]) +
                        "  TA:" + pad2(tabla[i][7]) +
                        "  TR:" + pad2(tabla[i][8]) + "                  ║");
                    System.out.println("╚════════════════════════════════════════╝");

                    // Elegir tamaño de bandera
                    System.out.println("\nTamaño de bandera:");
                    System.out.println("  1. Grande    (12x36 celdas)");
                    System.out.println("  2. Mediano   ( 9x27 celdas)");
                    System.out.println("  3. Pequeno   ( 6x18 celdas)");
                    System.out.println("  4. Miniatura ( 3x9  celdas)");
                    System.out.print("Tamaño (1-4): ");
                    while (!sc.hasNextInt()) { sc.next(); }
                    int tam = sc.nextInt();
                    if (tam < 1 || tam > 4) tam = 2;

                    System.out.println("\n--- BANDERA DE " + paises[i].toUpperCase() + " ---");
                    imprimirBandera(i, tam);
                    System.out.println(RESET);
                }

            } else if (op != 0) {
                System.out.println("Opcion invalida. Intente de nuevo.");
            }

        } while (op != 0);

        System.out.println("\nHasta el proximo partido! Viva el futbol!");
        sc.close();
    }

    // ================================================================
    //  UTILIDADES DE FORMATO (sin printf, solo concatenacion y sout)
    // ================================================================

    // Rellena con espacios a la derecha hasta longitud n
    static String padDer(String s, int n) {
        String r = s;
        for (int k = s.length(); k < n; k++) r = r + " ";
        return r;
    }

    // Rellena numero con espacio izquierda hasta 2 digitos
    static String pad2(int n) {
        if (n < 10) return " " + n;
        return "" + n;
    }

    // Rellena numero con espacio izquierda hasta 3 digitos
    static String pad3(int n) {
        if (n < 10)  return "  " + n;
        if (n < 100) return " " + n;
        return "" + n;
    }

    // Rellena numero con espacio izquierda (general)
    static String padIzq(int n, int ancho) {
        String s = "" + n;
        String r = "";
        for (int k = s.length(); k < ancho; k++) r = r + " ";
        return r + s;
    }
}