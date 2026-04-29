import java.util.Scanner;

public class Mundial2026 {

    // ===================== DATOS DEL MUNDIAL =====================
    // grupos[grupo][equipo] = nombre del pais
    static String[][][] grupos = {
        // Grupo A
        {{"Mexico","Corea del Sur","Sudafrica","Rep. Checa"}},
        // Grupo B
        {{"Canada","Suiza","Catar","Bosnia y Herz."}},
        // Grupo C
        {{"Brasil","Marruecos","Escocia","Haiti"}},
        // Grupo D
        {{"Estados Unidos","Australia","Paraguay","Turquia"}},
        // Grupo E
        {{"Alemania","Ecuador","Costa de Marfil","Curazao"}},
        // Grupo F
        {{"Paises Bajos","Japon","Tunez","Suecia"}},
        // Grupo G
        {{"Belgica","Iran","Egipto","Nueva Zelanda"}},
        // Grupo H
        {{"Espana","Uruguay","Cabo Verde","Arabia Saudita"}},
        // Grupo I
        {{"Francia","Senegal","Noruega","Irak"}},
        // Grupo J
        {{"Argentina","Argelia","Austria","Jordania"}},
        // Grupo K
        {{"Portugal","Colombia","Uzbekistan","R.D. del Congo"}},
        // Grupo L
        {{"Inglaterra","Croacia","Panama","Ghana"}}
    };

    static String[] nombresGrupos = {"A","B","C","D","E","F","G","H","I","J","K","L"};

    // Tabla de posiciones: [48 equipos][10 stats]: PJ,PG,PE,PP,GF,GC,DG,TA,TR,Pts
    static int[][] tablaPos = new int[48][10];
    static String[] nombresEquipos = new String[48];

    // Fixture: [partido][0=grupoIdx][1=eq1Idx][2=eq2Idx] y fechas
    // 12 grupos x 6 partidos = 72 partidos
    static int[][] fixture = new int[72][3];
    static String[] fechasFixture = new String[72];
    static String[] horasFixture = new String[72];
    static String[] sedesFixture = new String[72];

    // Info paises: [48][0=capital, 1=jugadores, 2=apariciones]
    static String[][] infoPaises = new String[48][3];

    public static void main(String[] args) {
        inicializarDatos();
        menuPrincipal();
    }

    // ===================== INICIALIZACIÓN =====================
    static void inicializarDatos() {
        // Llenar nombres de equipos en orden
        int idx = 0;
        for (int g = 0; g < 12; g++) {
            for (int e = 0; e < 4; e++) {
                nombresEquipos[idx] = grupos[g][0][e];
                idx++;
            }
        }

        // Inicializar tabla de posiciones en cero
        for (int i = 0; i < 48; i++) {
            for (int j = 0; j < 10; j++) {
                tablaPos[i][j] = 0;
            }
        }

        // Inicializar fixture: cada grupo tiene 6 partidos (round-robin 4 equipos)
        // Partidos: (0v1),(0v2),(0v3),(1v2),(1v3),(2v3)
        int pIdx = 0;
        String[] fechas = {"11 Jun","11 Jun","14 Jun","15 Jun","18 Jun","24 Jun"};
        String[] horas  = {"15:00","22:00","18:00","21:00","20:00","21:00"};
        String[] sedes  = {"Ciudad de Mexico","Guadalajara","Dallas","Los Angeles","Atlanta","Nueva York"};
        int[][] combos = {{0,1},{2,3},{0,2},{1,3},{0,3},{1,2}};

        for (int g = 0; g < 12; g++) {
            for (int c = 0; c < 6; c++) {
                fixture[pIdx][0] = g;
                fixture[pIdx][1] = combos[c][0];
                fixture[pIdx][2] = combos[c][1];
                fechasFixture[pIdx] = fechas[c];
                horasFixture[pIdx]  = horas[c];
                sedesFixture[pIdx]  = sedes[c % sedes.length];
                pIdx++;
            }
        }

        // Info de paises (capital, jugadores destacados, apariciones mundiales)
        String[][] info = {
            // A: Mexico, Corea del Sur, Sudafrica, Rep Checa
            {"(Ciudad de México) Ochoa/C.Montes/Gallardo/J.Vasquez/Araujo/E.Alvarez/H.Herrera/Lozano/Orbelín/S.Gimenez/R.Jimenez","17"},
            {"(Seúl) Jo Hyeon-woo/Kim Min-jae/Kim Young-gwon/Kim Jin-su/Lee Ki-je/Jung Woo-young/Lee Jae-sung/Lee Kang-in/Hwang In-beom/Son Heung-min/Hwang Hee-chan","11"},
            {"(Pretoria) R.Williams/S.Xulu/R.De Reuck/R.Frosler/T.Mashego/T.Mokoena/E.Ntsiza/P.Tau/E.Mokwana/L.Foster/T.Zwane","3"},
            {"(Praga) Vaclik/Holes/Zima/Coufal/Boril/Soucek/Provod/Sadilek/Hlozek/P.Schick/Jurasek","9"},

            // B: Canada, Suiza, Catar, Bosnia
            {"(Ottawa) St.Clair/K.Miller/Cornelius/Laryea/Bassong/Eustaquio/I.Kone/Buchanan/Osorio/J.David/C.Larin","3"},
            {"(Berna) Y.Sommer/Akanji/F.Schar/R.Rodriguez/Elvedi/G.Xhaka/Freuler/Zakaria/Shaqiri/Embolo/Okafor","13"},
            {"(Doha) M.Barsham/Pedro Miguel/Al-Rawi/T.Salman/A.Hassan/Boudiaf/Al-Ahrak/Ismaeel/Al-Haydos/Almoez Ali/Akram Afif","2"},
            {"(Sarajevo) I.Sehic/Kolasinac/Bicakcic/Ahmedhodzic/Hajradinovic/Pjanic/S.Lukic/B.Tahirovic/E.Visca/E.Dzeko/A.Hodzic","1"},
            // C: Brasil, Marruecos, Escocia, Haiti
            {"(Brasilia) Ederson/Marquinhos/E.Militao/G.Magalhaes/Danilo/Casemiro/B.Guimaraes/L.Paqueta/Vinicius Jr/Richarlison/M.Cunha","22"},
            {"(Rabat) Y.Bounou/A.Hakimi/N.Aguerd/R.Saiss/N.Mazraoui/S.Amrabat/A.Ounahi/H.Ziyech/I.Chair/En-Nesyri/Ezzalzouli","7"},
            {"(Edimburgo) A.Gunn/A.Robertson/G.Hanley/K.Tierney/A.Ralston/McTominay/J.McGinn/B.Gilmour/R.Christie/C.Adams/L.Shankland","9"},
            {"(Puerto Príncipe) J.Placide/C.Arcus/K.Thermoncy/H.Delcroix/R.Ade/J.Bellegarde/D.Jean Jacques/L.Pierre/D.Etienne Jr/D.Nazon/F.Pierrot","2"},
            // D: EEUU, Australia, Paraguay, Turquia
            {"(Washington D.C.) M.Turner/S.Dest/M.Robinson/T.Ream/A.Robinson/McKennie/T.Adams/Y.Musah/C.Pulisic/R.Pepi/F.Balogun","11"},
            {"(Canberra) M.Ryan/M.Degenek/C.Burgess/K.Rowles/L.Miller/J.Irvine/R.McGree/C.Metcalfe/C.Goodwin/M.Boyle/N.Irankunda","6"},
            {"(Asunción) A.Silva/G.Gomez/O.Alderete/F.Balbuena/S.Arzamendia/M.Almiron/M.Villasanti/A.Cubas/A.Cardozo/A.Romero/A.Sanabria","9"},
            {"(Ankara) U.Cakir/M.Demiral/S.Akaydin/F.Kadioglu/Z.Celik/H.Calhanoglu/K.Ayhan/S.Ozcan/A.Güler/K.Akturkoglu/B.Yilmaz","5"},
            // E: Alemania, Ecuador, Costa de Marfil, Curazao
            {"(Berlín) O.Baumann/J.Tah/N.Schlotterbeck/D.Raum/W.Anton/J.Kimmich/L.Goretzka/F.Wirtz/L.Sane/N.Woltemade/J.Burkardt","20"},
            {"(Quito) H.Galindez/P.Hincapie/F.Torres/W.Pacho/A.Preciado/M.Caicedo/A.Franco/K.Paez/G.Plata/K.Rodriguez/L.Campana","4"},
            {"(Yamusukro) Y.Fofana/O.Kossounou/O.Diomande/E.Ndicka/G.Konan/F.Kessie/S.Fofana/I.Sangare/A.Diallo/S.Haller/E.Guessand","3"},
            {"(Willemstad) E.Room/A.Obispo/S.Sambo/J.Brenet/S.Floranus/L.Bacuna/J.Bacuna/T.Chong/L.Comenencia/S.Hansen/K.Gorre","1"},
            // F: Paises Bajos, Japon, Tunez, Suecia
            {"(Ámsterdam) M.Flekken/V.van Dijk/N.Ake/M.de Ligt/D.Dumfries/F.de Jong/T.Reijnders/T.Koopmeiners/C.Gakpo/M.Depay/W.Weghorst","11"},
            {"(Tokio) T.Hayakawa/K.Itakura/S.Taniguchi/Y.Sugawara/T.Watanabe/R.Doan/K.Mitoma/J.Ito/H.Morita/A.Ueda/D.Kamada","7"},
            {"(Túnez) A.Dahmen/M.Talbi/D.Bronn/M.Draeger/A.Maaloul/E.Skhiri/A.Ben Slimane/A.Laidouni/Y.Msakni/S.Jaziri/H.Rafia","6"},
            {"(Estocolmo) R.Olsen/L.Augustinsson/I.Hien/V.Lindelof/E.Krafth/A.Ekdal/V.Claesson/D.Kulusevski/J.Karlsson/A.Isak/Z.Ibrahimovic","12"},
            // G: Belgica, Iran, Egipto, Nueva Zelanda
            {"(Bruselas) M.Sels/A.Theate/B.Mechele/T.Castagne/M.De Cuyper/Y.Tielemans/A.Witsel/H.Vanaken/J.Doku/L.Openda/C.De Ketelaere","14"},
            {"(Teherán) A.Beiranvand/M.Hosseini/S.Khalilzadeh/M.Mohammadi/H.Kanaanizadegan/S.Ezatolahi/S.Ghoddos/A.Jahanbakhsh/A.Gholizadeh/M.Taremi/S.Azmoun","6"},
            {"(El Cairo) M.Awad/El-Wensh/K.Fouad/A.Hany/Y.Zakaria/M.Elneny/Afsha/M.Saad/A.Tawfik/M.Salah/M.Sherif","3"},
            {"(Wellington) O.Sail/W.Reid/M.Boxall/C.Howieson/L.Cacace/B.Tuiloma/S.Marinovic/R.Thomas/A.Greive/C.Wood/C.Elliot","3"},

            // H: Espana, Uruguay, Cabo Verde, Arabia Saudita
            {"(Madrid) D.Raya/D.Carvajal/A.Laporte/R.Le Normand/A.Grimaldo/Rodri/Pedri/Gavi/N.Williams/A.Morata/M.Oyarzabal","16"},
            {"(Montevideo) S.Rochet/D.Godin/R.Araujo/M.Olivera/M.Caceres/F.Valverde/R.Bentancur/M.Ugarte/F.Pellistri/D.Nunez/L.Suarez","14"},
            {"(Praia) Vozinha/Stopira/R.Santos/W.Pina/K.Pires/J.Monteiro/K.Pina/L.Duarte/D.Duarte/G.Rodrigues/N.da Costa","1"},
            {"(Riad) N.Al-Aqidi/H.Al-Tambakti/A.Majrashi/A.Al-Amri/N.Boushal/M.Kanno/S.Al-Dawsari/N.Al-Dawsari/A.Al-Khaibari/F.Al-Buraikan/S.Al-Shehri","7"},

            // I: Francia, Senegal, Noruega, Irak
            {"(París) M.Maignan/W.Saliba/D.Upamecano/I.Konate/T.Hernandez/N.Kante/W.Zaire-Emery/K.Thuram/M.Olise/K.Mbappe/C.Nkunku","16"},
            {"(Dakar) E.Mendy/K.Koulibaly/A.Diallo/F.Mendy/Y.Sabaly/I.Gueye/P.Sarr/N.Mendy/S.Mane/H.Diallo/N.Jackson","4"},
            {"(Oslo) O.Nyland/L.Ostigard/A.Hanche-Olsen/B.Meling/S.Gregersen/M.Odegaard/S.Berge/M.Thorsby/A.Sorloth/E.Haaland/A.Nusa","4"},
            {"(Bagdad) J.Hassan/A.Ibrahim/A.Adnan/R.Sulaka/S.Natiq/A.Attwan/B.Resan/H.Tariq/A.Hussein/M.Ali/A.Hussain","4"},

            // J: Argentina, Argelia, Austria, Jordania
            {"(Buenos Aires) G.Rulli/N.Otamendi/N.Tagliafico/M.Senesi/V.Barco/R.De Paul/A.Mac Allister/L.Paredes/N.Paz/L.Messi/L.Martinez","18"},
            {"(Argel) F.Chaal/Y.Atal/H.Baouche/A.Bedrane/R.Halaimia/S.Bendebka/V.Lekhal/Y.Brahimi/A.Ounas/I.Slimani/Y.Benzia","4"},
            {"(Viena) A.Schlager/D.Alaba/K.Danso/M.Friedl/S.Posch/X.Schlager/M.Sabitzer/C.Baumgartner/K.Laimer/M.Arnautovic/M.Gregoritsch","8"},
            {"(Amán) Y.Abulaila/B.Barakat/A.Ibrahim/E.Haddad/O.Rashid/Y.Al-Naimat/M.Al-Taamari/A.Nasib/M.Abu Zema/B.Faisal/H.Al-Dardour","2"},

            // K: Portugal, Colombia, Uzbekistan, RD Congo
            {"(Lisboa) D.Costa/R.Dias/Pepe/N.Mendes/J.Cancelo/B.Fernandes/Vitinha/J.Palhinha/B.Silva/C.Ronaldo/R.Leao","8"},
            {"(Bogotá) D.Ospina/D.Sanchez/Y.Mina/D.Munoz/J.Mojica/J.Rodriguez/J.Lerma/R.Rios/J.Arias/L.Diaz/R.Santos Borre","6"},
            {"(Taskent) U.Yusupov/K.Alijonov/H.Norchaev/U.Rakhmatullayev/J.Sidiqov/J.Masharipov/E.Shomurodov/O.Shukurov/J.Iskanderov/D.Khamdamov/A.Tursunov","1"},
            {"(Kinshasa) J.Kiassumbua/M.Tisserand/C.Mbemba/Y.Bolasie/N.Kamoa/C.Bakambu/W.Zaha/P.Mpoku/J.Botaka/D.Mbokani/R.Bamenga","2"},

            // L: Inglaterra, Croacia, Panama, Ghana
            {"(Londres) J.Pickford/J.Stones/M.Guehi/R.James/E.Konsa/D.Rice/J.Bellingham/B.Saka/P.Foden/H.Kane/M.Rashford","16"},
            {"(Zagreb) D.Livakovic/J.Gvardiol/D.Caleta-Car/J.Sutalo/J.Stanisic/L.Modric/M.Pasalic/N.Vlasic/P.Sucic/A.Kramaric/I.Perisic","6"},
            {"(Ciudad de Panamá) L.Mejia/F.Escobar/E.Davis/A.Andrade/H.Cummings/A.Machado/A.Carrasquilla/A.Godoy/I.Diaz/R.Blackburn/E.Barcenas","3"},
            {"(Acra) L.Ati-Zigi/A.Seidu/G.Mensah/D.Kohn/J.Adjetey/T.Partey/K.Sulemana/C.Bonsu Baah/A.Semenyo/J.Ayew/M.Kudus","4"},
        };

        for (int i = 0; i < 48; i++) {
            infoPaises[i][0] = info[i][0];
            infoPaises[i][1] = info[i][1];
            infoPaises[i][2] = info[i][2];
        }
    }

    // ===================== MENU PRINCIPAL =====================
    static void menuPrincipal() {
        Scanner sc = new Scanner(System.in);
        int opcion = -1;
        while (opcion != 0) {
            imprimirBanner();
            System.out.println(ConsoleColors.CYAN_BOLD + "╔══════════════════════════════════════╗" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.CYAN_BOLD + "║     MENU PRINCIPAL - MUNDIAL 2026    ║" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.CYAN_BOLD + "╠══════════════════════════════════════╣" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.WHITE + "║  1. Ver banderas de un grupo         ║" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.WHITE + "║  2. Tabla de posiciones              ║" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.WHITE + "║  3. Fixture de partidos              ║" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.WHITE + "║  4. Informacion de un pais           ║" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.WHITE + "║  0. Salir                            ║" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.CYAN_BOLD + "╚══════════════════════════════════════╝" + ConsoleColors.RESET);
            System.out.print(ConsoleColors.YELLOW + "Seleccione una opcion: " + ConsoleColors.RESET);
            opcion = ConsoleInput.readInt();

            if (opcion == 1) menuBanderas(sc);
            else if (opcion == 2) menuTabla();
            else if (opcion == 3) menuFixture(sc);
            else if (opcion == 4) menuInfoPais(sc);
            else if (opcion != 0) System.out.println(ConsoleColors.RED + "Opcion invalida." + ConsoleColors.RESET);
        }
        System.out.println(ConsoleColors.GREEN_BOLD + "¡Hasta pronto! Viva el Mundial 2026!" + ConsoleColors.RESET);
    }

    // ===================== BANNER =====================
    static void imprimirBanner() {
        System.out.println(ConsoleColors.YELLOW_BOLD);
        System.out.println("  ╔╦╗╦ ╦╔╗╔╔╦╗╦╔═╗╦    ");
        System.out.println("  ║║║║ ║║║║ ║║║╠═╣║    ");
        System.out.println("  ╩ ╩╚═╝╝╚╝═╩╝╩╩ ╩╩═╝   ");
        System.out.println("  COPA MUNDIAL DE FUTBOL   ");
        System.out.println("  USA - CANADA - MEXICO    ");
        System.out.println("                           ");
        System.out.println(ConsoleColors.RESET);
    }

    // ===================== PASO 1: BANDERAS =====================
    static void menuBanderas(Scanner sc) {
        System.out.println(ConsoleColors.CYAN_BOLD + "\n=== BANDERAS POR GRUPO ===" + ConsoleColors.RESET);
        System.out.println("Grupos disponibles: A B C D E F G H I J K L");
        System.out.print("Ingrese la letra del grupo (o 'T' para todos): ");
        String entrada = ConsoleInput.readString().toUpperCase();

        System.out.print(ConsoleColors.YELLOW + "Factor de escala (1=Icono, 2=Pequeno, 3=Mediano, 4=Grande): " + ConsoleColors.RESET);
        int escala = ConsoleInput.readInt();
        if (escala < 1) escala = 1;
        if (escala > 4) escala = 4;

        if (entrada.equals("T")) {
            for (int g = 0; g < 12; g++) {
                System.out.println(ConsoleColors.YELLOW_BOLD + "\n--- GRUPO " + nombresGrupos[g] + " ---" + ConsoleColors.RESET);
                for (int e = 0; e < 4; e++) {
                    int eqIdx = g * 4 + e;
                    System.out.println(ConsoleColors.WHITE_BOLD + grupos[g][0][e] + ":" + ConsoleColors.RESET);
                    imprimirBandera(eqIdx, escala);
                }
            }
        } else {
            int gIdx = -1;
            for (int g = 0; g < 12; g++) {
                if (nombresGrupos[g].equals(entrada)) gIdx = g;
            }
            if (gIdx == -1) {
                System.out.println(ConsoleColors.RED + "Grupo no encontrado." + ConsoleColors.RESET);
                return;
            }
            System.out.println(ConsoleColors.YELLOW_BOLD + "\n--- GRUPO " + nombresGrupos[gIdx] + " ---" + ConsoleColors.RESET);
            for (int e = 0; e < 4; e++) {
                int eqIdx = gIdx * 4 + e;
                System.out.println(ConsoleColors.WHITE_BOLD + grupos[gIdx][0][e] + ":" + ConsoleColors.RESET);
                imprimirBandera(eqIdx, escala);
            }
        }
        System.out.print(ConsoleColors.GREEN + "\nPresione ENTER para continuar..." + ConsoleColors.RESET);
        ConsoleInput.readString();
    }

    static void imprimirBandera(int equipoIdx, int escala) {
        // escala: 1=Icono(3x5), 2=Pequeno(6x10), 3=Mediano(9x15), 4=Grande(12x20)
        int filas = 3 * escala;
        int cols  = 5 * escala;
        char[][] bandera = new char[filas][cols];

        // Determinar patron segun equipo
        // 0=Mexico,1=Corea Sur,2=Sudafrica,3=Rep Checa
        // 4=Canada,5=Suiza,6=Catar,7=Bosnia
        // 8=Brasil,9=Marruecos,10=Escocia,11=Haiti
        // 12=EEUU,13=Australia,14=Paraguay,15=Turquia
        // 16=Alemania,17=Ecuador,18=CDMarfil,19=Curazao
        // 20=PBajos,21=Japon,22=Tunez,23=Suecia
        // 24=Belgica,25=Iran,26=Egipto,27=NZelanda
        // 28=Espana,29=Uruguay,30=CaboVerde,31=ASaudita
        // 32=Francia,33=Senegal,34=Noruega,35=Irak
        // 36=Argentina,37=Argelia,38=Austria,39=Jordania
        // 40=Portugal,41=Colombia,42=Uzbekistan,43=RDCongo
        // 44=Inglaterra,45=Croacia,46=Panama,47=Ghana

        // Patrones de banderas usando caracteres:
        // R=Rojo, G=Verde, B=Blanco, N=Negro, Y=Amarillo,
        // U=Azul, O=Naranja, M=Magenta/Rosa, C=Cyan

        if (equipoIdx == 0) { // Mexico: Verde-Blanco-Rojo vertical
            for (int i = 0; i < filas; i++) {
                for (int j = 0; j < cols; j++) {
                    if (j < cols/3) bandera[i][j] = 'G';
                    else if (j < 2*cols/3) bandera[i][j] = 'B';
                    else bandera[i][j] = 'R';
                }
            }
        } else if (equipoIdx == 1) { // Corea del Sur: Blanco con cruz roja
            for (int i = 0; i < filas; i++) {
                for (int j = 0; j < cols; j++) {
                    bandera[i][j] = 'B';
                }
            }
            int midI = filas/2;
            int midJ = cols/2;
            for (int i = 0; i < filas; i++) bandera[i][midJ] = 'R';
            for (int j = 0; j < cols; j++) bandera[midI][j] = 'R';
        } else if (equipoIdx == 2) { // Sudafrica: horizontal Verde-Amarillo-Rojo con negro
            for (int i = 0; i < filas; i++) {
                for (int j = 0; j < cols; j++) {
                    if (j < cols/4) bandera[i][j] = 'N';
                    else if (i < filas/3) bandera[i][j] = 'G';
                    else if (i < 2*filas/3) bandera[i][j] = 'Y';
                    else bandera[i][j] = 'R';
                }
            }
        } else if (equipoIdx == 3) { // Rep Checa: Blanco arriba, Rojo abajo, Azul triangulo izq
            for (int i = 0; i < filas; i++) {
                for (int j = 0; j < cols; j++) {
                    if (j < (i+1)) bandera[i][j] = 'U';
                    else if (i < filas/2) bandera[i][j] = 'B';
                    else bandera[i][j] = 'R';
                }
            }
        } else if (equipoIdx == 4) { // Canada: Rojo-Blanco-Rojo vertical
            for (int i = 0; i < filas; i++) {
                for (int j = 0; j < cols; j++) {
                    if (j < cols/4 || j >= 3*cols/4) bandera[i][j] = 'R';
                    else bandera[i][j] = 'B';
                }
            }
            // Hoja maple aproximada: centro rojo
            bandera[filas/2][cols/2] = 'R';
        } else if (equipoIdx == 5) { // Suiza: Rojo con cruz blanca
            for (int i = 0; i < filas; i++) {
                for (int j = 0; j < cols; j++) {
                    bandera[i][j] = 'R';
                }
            }
            int midI = filas/2, midJ = cols/2;
            for (int i = 1; i < filas-1; i++) bandera[i][midJ] = 'B';
            for (int j = 1; j < cols-1; j++) bandera[midI][j] = 'B';
        } else if (equipoIdx == 6) { // Catar: Maroon con franja blanca
            for (int i = 0; i < filas; i++) {
                for (int j = 0; j < cols; j++) {
                    if (j < cols/4) bandera[i][j] = 'B';
                    else bandera[i][j] = 'M';
                }
            }
        } else if (equipoIdx == 7) { // Bosnia: Azul con triangulo amarillo
            for (int i = 0; i < filas; i++) {
                for (int j = 0; j < cols; j++) {
                    bandera[i][j] = 'U';
                }
            }
            for (int i = 0; i < filas; i++) {
                if (i < cols) bandera[i][i < cols ? i : cols-1] = 'Y';
            }
        } else if (equipoIdx == 8) { // Brasil: Verde con rombo amarillo y circulo azul
            for (int i = 0; i < filas; i++) {
                for (int j = 0; j < cols; j++) {
                    int di = Math.abs(i - filas/2);
                    int dj = Math.abs(j - cols/2);
                    if (di + dj < filas/2 + cols/4) {
                        if (di*di + dj*dj < (filas/4)*(filas/4) + 1) bandera[i][j] = 'U';
                        else bandera[i][j] = 'Y';
                    } else bandera[i][j] = 'G';
                }
            }
        } else if (equipoIdx == 9) { // Marruecos: Rojo con estrella verde
            for (int i = 0; i < filas; i++) {
                for (int j = 0; j < cols; j++) {
                    bandera[i][j] = 'R';
                }
            }
            bandera[filas/2][cols/2] = 'G';
            if (filas > 2) {
                bandera[filas/2-1][cols/2] = 'G';
                bandera[filas/2][cols/2-1] = 'G';
                bandera[filas/2][cols/2+1] = 'G';
            }
        } else if (equipoIdx == 10) { // Escocia: Azul con aspa blanca (X)
            for (int i = 0; i < filas; i++) {
                for (int j = 0; j < cols; j++) {
                    bandera[i][j] = 'U';
                }
            }
            for (int i = 0; i < filas; i++) {
                int j1 = (int)((double)i / filas * cols);
                int j2 = cols - 1 - j1;
                if (j1 >= 0 && j1 < cols) bandera[i][j1] = 'B';
                if (j2 >= 0 && j2 < cols) bandera[i][j2] = 'B';
            }
        } else if (equipoIdx == 11) { // Haiti: Azul-Rojo horizontal
            for (int i = 0; i < filas; i++) {
                for (int j = 0; j < cols; j++) {
                    if (i < filas/2) bandera[i][j] = 'U';
                    else bandera[i][j] = 'R';
                }
            }
        } else if (equipoIdx == 12) { // EEUU: Rojo y blanco horizontal con azul
            for (int i = 0; i < filas; i++) {
                for (int j = 0; j < cols; j++) {
                    if (j < cols/3 && i < filas/2) bandera[i][j] = 'U';
                    else if (i % 2 == 0) bandera[i][j] = 'R';
                    else bandera[i][j] = 'B';
                }
            }
        } else if (equipoIdx == 13) { // Australia: Azul con cruz roja-blanca
            for (int i = 0; i < filas; i++) {
                for (int j = 0; j < cols; j++) {
                    bandera[i][j] = 'U';
                }
            }
            int midI = filas/2, midJ = cols/4;
            for (int i = 0; i < filas; i++) bandera[i][midJ] = 'R';
            for (int j = 0; j < cols/2; j++) bandera[midI][j] = 'R';
        } else if (equipoIdx == 14) { // Paraguay: Rojo-Blanco-Azul horizontal
            for (int i = 0; i < filas; i++) {
                for (int j = 0; j < cols; j++) {
                    if (i < filas/3) bandera[i][j] = 'R';
                    else if (i < 2*filas/3) bandera[i][j] = 'B';
                    else bandera[i][j] = 'U';
                }
            }
        } else if (equipoIdx == 15) { // Turquia: Rojo con luna y estrella blanca
            for (int i = 0; i < filas; i++) {
                for (int j = 0; j < cols; j++) {
                    bandera[i][j] = 'R';
                }
            }
            bandera[filas/2][cols/3] = 'B';
            bandera[filas/2][cols/2] = 'B';
        } else if (equipoIdx == 16) { // Alemania: Negro-Rojo-Amarillo horizontal
            for (int i = 0; i < filas; i++) {
                for (int j = 0; j < cols; j++) {
                    if (i < filas/3) bandera[i][j] = 'N';
                    else if (i < 2*filas/3) bandera[i][j] = 'R';
                    else bandera[i][j] = 'Y';
                }
            }
        } else if (equipoIdx == 17) { // Ecuador: Amarillo-Azul-Rojo horizontal
            for (int i = 0; i < filas; i++) {
                for (int j = 0; j < cols; j++) {
                    if (i < filas/2) bandera[i][j] = 'Y';
                    else if (i < 3*filas/4) bandera[i][j] = 'U';
                    else bandera[i][j] = 'R';
                }
            }
        } else if (equipoIdx == 18) { // Costa de Marfil: Naranja-Blanco-Verde vertical
            for (int i = 0; i < filas; i++) {
                for (int j = 0; j < cols; j++) {
                    if (j < cols/3) bandera[i][j] = 'O';
                    else if (j < 2*cols/3) bandera[i][j] = 'B';
                    else bandera[i][j] = 'G';
                }
            }
        } else if (equipoIdx == 19) { // Curazao: Azul con franjas amarillas
            for (int i = 0; i < filas; i++) {
                for (int j = 0; j < cols; j++) {
                    bandera[i][j] = 'U';
                }
            }
            if (filas > 2) bandera[filas-2][0] = 'Y';
            bandera[filas-1][0] = 'Y';
        } else if (equipoIdx == 20) { // Paises Bajos: Rojo-Blanco-Azul horizontal
            for (int i = 0; i < filas; i++) {
                for (int j = 0; j < cols; j++) {
                    if (i < filas/3) bandera[i][j] = 'R';
                    else if (i < 2*filas/3) bandera[i][j] = 'B';
                    else bandera[i][j] = 'U';
                }
            }
        } else if (equipoIdx == 21) { // Japon: Blanco con circulo rojo
            for (int i = 0; i < filas; i++) {
                for (int j = 0; j < cols; j++) {
                    int di = i - filas/2, dj = j - cols/2;
                    if (di*di*4 + dj*dj < (filas/2)*(filas/2)+1) bandera[i][j] = 'R';
                    else bandera[i][j] = 'B';
                }
            }
        } else if (equipoIdx == 22) { // Tunez: Rojo con circulo blanco y luna
            for (int i = 0; i < filas; i++) {
                for (int j = 0; j < cols; j++) {
                    bandera[i][j] = 'R';
                }
            }
            bandera[filas/2][cols/2] = 'B';
        } else if (equipoIdx == 23) { // Suecia: Azul con cruz amarilla
            for (int i = 0; i < filas; i++) {
                for (int j = 0; j < cols; j++) {
                    bandera[i][j] = 'U';
                }
            }
            int midI = filas/2, midJ = cols/3;
            for (int i = 0; i < filas; i++) bandera[i][midJ] = 'Y';
            for (int j = 0; j < cols; j++) bandera[midI][j] = 'Y';
        } else if (equipoIdx == 24) { // Belgica: Negro-Amarillo-Rojo vertical
            for (int i = 0; i < filas; i++) {
                for (int j = 0; j < cols; j++) {
                    if (j < cols/3) bandera[i][j] = 'N';
                    else if (j < 2*cols/3) bandera[i][j] = 'Y';
                    else bandera[i][j] = 'R';
                }
            }
        } else if (equipoIdx == 25) { // Iran: Verde-Blanco-Rojo horizontal
            for (int i = 0; i < filas; i++) {
                for (int j = 0; j < cols; j++) {
                    if (i < filas/3) bandera[i][j] = 'G';
                    else if (i < 2*filas/3) bandera[i][j] = 'B';
                    else bandera[i][j] = 'R';
                }
            }
        } else if (equipoIdx == 26) { // Egipto: Rojo-Blanco-Negro horizontal
            for (int i = 0; i < filas; i++) {
                for (int j = 0; j < cols; j++) {
                    if (i < filas/3) bandera[i][j] = 'R';
                    else if (i < 2*filas/3) bandera[i][j] = 'B';
                    else bandera[i][j] = 'N';
                }
            }
        } else if (equipoIdx == 27) { // Nueva Zelanda: Azul con cruz roja
            for (int i = 0; i < filas; i++) {
                for (int j = 0; j < cols; j++) {
                    bandera[i][j] = 'U';
                }
            }
            int midI = filas/2, midJ = cols/4;
            for (int i = 0; i < filas; i++) bandera[i][midJ] = 'R';
            for (int j = 0; j < cols/2; j++) bandera[midI][j] = 'R';
        } else if (equipoIdx == 28) { // Espana: Rojo-Amarillo-Rojo horizontal
            for (int i = 0; i < filas; i++) {
                for (int j = 0; j < cols; j++) {
                    if (i < filas/4 || i >= 3*filas/4) bandera[i][j] = 'R';
                    else bandera[i][j] = 'Y';
                }
            }
        } else if (equipoIdx == 29) { // Uruguay: Blanco y Azul con sol
            for (int i = 0; i < filas; i++) {
                for (int j = 0; j < cols; j++) {
                    if ((i / Math.max(1, filas/9)) % 2 == 0) bandera[i][j] = 'B';
                    else bandera[i][j] = 'U';
                }
            }
            bandera[filas/2][cols/4] = 'Y';
        } else if (equipoIdx == 30) { // Cabo Verde: Azul con franja roja y estrellas
            for (int i = 0; i < filas; i++) {
                for (int j = 0; j < cols; j++) {
                    bandera[i][j] = 'U';
                }
            }
            int midI = 2*filas/3;
            if (midI < filas) bandera[midI][cols/2] = 'R';
        } else if (equipoIdx == 31) { // Arabia Saudita: Verde con sable blanco
            for (int i = 0; i < filas; i++) {
                for (int j = 0; j < cols; j++) {
                    bandera[i][j] = 'G';
                }
            }
            bandera[filas/2][cols/2] = 'B';
            bandera[filas/2][cols/2+1 < cols ? cols/2+1 : cols/2] = 'B';
        } else if (equipoIdx == 32) { // Francia: Azul-Blanco-Rojo vertical
            for (int i = 0; i < filas; i++) {
                for (int j = 0; j < cols; j++) {
                    if (j < cols/3) bandera[i][j] = 'U';
                    else if (j < 2*cols/3) bandera[i][j] = 'B';
                    else bandera[i][j] = 'R';
                }
            }
        } else if (equipoIdx == 33) { // Senegal: Verde-Amarillo-Rojo vertical
            for (int i = 0; i < filas; i++) {
                for (int j = 0; j < cols; j++) {
                    if (j < cols/3) bandera[i][j] = 'G';
                    else if (j < 2*cols/3) bandera[i][j] = 'Y';
                    else bandera[i][j] = 'R';
                }
            }
            bandera[filas/2][cols/2] = 'G';
        } else if (equipoIdx == 34) { // Noruega: Rojo con cruz azul-blanca
            for (int i = 0; i < filas; i++) {
                for (int j = 0; j < cols; j++) {
                    bandera[i][j] = 'R';
                }
            }
            int midI = filas/2, midJ = cols/3;
            for (int i = 0; i < filas; i++) bandera[i][midJ] = 'U';
            for (int j = 0; j < cols; j++) bandera[midI][j] = 'U';
            if (midJ > 0) for (int i = 0; i < filas; i++) bandera[i][midJ > 0 ? midJ-1 : 0] = 'B';
        } else if (equipoIdx == 35) { // Irak: Rojo-Blanco-Negro horizontal con verde
            for (int i = 0; i < filas; i++) {
                for (int j = 0; j < cols; j++) {
                    if (i < filas/3) bandera[i][j] = 'R';
                    else if (i < 2*filas/3) bandera[i][j] = 'B';
                    else bandera[i][j] = 'N';
                }
            }
            bandera[filas/2][cols/2] = 'G';
        } else if (equipoIdx == 36) { // Argentina: Celeste-Blanco-Celeste horizontal con sol
            for (int i = 0; i < filas; i++) {
                for (int j = 0; j < cols; j++) {
                    if (i < filas/3 || i >= 2*filas/3) bandera[i][j] = 'C';
                    else bandera[i][j] = 'B';
                }
            }
            bandera[filas/2][cols/2] = 'Y';
        } else if (equipoIdx == 37) { // Argelia: Blanco-Verde vertical con luna roja
            for (int i = 0; i < filas; i++) {
                for (int j = 0; j < cols; j++) {
                    if (j < cols/2) bandera[i][j] = 'B';
                    else bandera[i][j] = 'G';
                }
            }
            bandera[filas/2][cols/2] = 'R';
        } else if (equipoIdx == 38) { // Austria: Rojo-Blanco-Rojo horizontal
            for (int i = 0; i < filas; i++) {
                for (int j = 0; j < cols; j++) {
                    if (i < filas/3 || i >= 2*filas/3) bandera[i][j] = 'R';
                    else bandera[i][j] = 'B';
                }
            }
        } else if (equipoIdx == 39) { // Jordania: Negro-Blanco-Verde horizontal con triangulo
            for (int i = 0; i < filas; i++) {
                for (int j = 0; j < cols; j++) {
                    if (j < cols/4) bandera[i][j] = 'R';
                    else if (i < filas/3) bandera[i][j] = 'N';
                    else if (i < 2*filas/3) bandera[i][j] = 'B';
                    else bandera[i][j] = 'G';
                }
            }
        } else if (equipoIdx == 40) { // Portugal: Verde-Rojo vertical
            for (int i = 0; i < filas; i++) {
                for (int j = 0; j < cols; j++) {
                    if (j < cols/3) bandera[i][j] = 'G';
                    else bandera[i][j] = 'R';
                }
            }
            bandera[filas/2][cols/3] = 'Y';
        } else if (equipoIdx == 41) { // Colombia: Amarillo-Azul-Rojo horizontal
            for (int i = 0; i < filas; i++) {
                for (int j = 0; j < cols; j++) {
                    if (i < filas/2) bandera[i][j] = 'Y';
                    else if (i < 3*filas/4) bandera[i][j] = 'U';
                    else bandera[i][j] = 'R';
                }
            }
        } else if (equipoIdx == 42) { // Uzbekistan: Azul-Blanco-Verde horizontal
            for (int i = 0; i < filas; i++) {
                for (int j = 0; j < cols; j++) {
                    if (i < filas/3) bandera[i][j] = 'U';
                    else if (i < 2*filas/3) bandera[i][j] = 'B';
                    else bandera[i][j] = 'G';
                }
            }
        } else if (equipoIdx == 43) { // RD Congo: Azul con diagonal amarilla y estrella roja
            for (int i = 0; i < filas; i++) {
                for (int j = 0; j < cols; j++) {
                    bandera[i][j] = 'U';
                }
            }
            for (int i = 0; i < filas; i++) {
                int j = (int)((double)i / filas * cols);
                if (j < cols) bandera[i][j] = 'Y';
            }
            bandera[0][0] = 'R';
        } else if (equipoIdx == 44) { // Inglaterra: Blanco con cruz roja
            for (int i = 0; i < filas; i++) {
                for (int j = 0; j < cols; j++) {
                    bandera[i][j] = 'B';
                }
            }
            int midI = filas/2, midJ = cols/2;
            for (int i = 0; i < filas; i++) bandera[i][midJ] = 'R';
            for (int j = 0; j < cols; j++) bandera[midI][j] = 'R';
        } else if (equipoIdx == 45) { // Croacia: Rojo-Blanco-Azul horizontal con escudo
            for (int i = 0; i < filas; i++) {
                for (int j = 0; j < cols; j++) {
                    if (i < filas/3) bandera[i][j] = 'R';
                    else if (i < 2*filas/3) bandera[i][j] = 'B';
                    else bandera[i][j] = 'U';
                }
            }
            // Cuadros ajedrez en centro
            bandera[filas/2][cols/2] = (filas/2 + cols/2) % 2 == 0 ? 'R' : 'B';
        } else if (equipoIdx == 46) { // Panama: Blanco con cuadros rojo y azul
            for (int i = 0; i < filas; i++) {
                for (int j = 0; j < cols; j++) {
                    if (i < filas/2 && j < cols/2) bandera[i][j] = 'B';
                    else if (i >= filas/2 && j >= cols/2) bandera[i][j] = 'R';
                    else bandera[i][j] = 'B';
                }
            }
        } else { // Ghana (47): Rojo-Amarillo-Verde horizontal con estrella negra
            for (int i = 0; i < filas; i++) {
                for (int j = 0; j < cols; j++) {
                    if (i < filas/3) bandera[i][j] = 'R';
                    else if (i < 2*filas/3) bandera[i][j] = 'Y';
                    else bandera[i][j] = 'G';
                }
            }
            bandera[filas/2][cols/2] = 'N';
        }

        // Imprimir bandera
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < cols; j++) {
                char c = bandera[i][j];
                if      (c == 'R') System.out.print(ConsoleColors.RED_BACKGROUND + "   ");
                else if (c == 'G') System.out.print(ConsoleColors.GREEN_BACKGROUND + "   ");
                else if (c == 'B') System.out.print(ConsoleColors.WHITE_BACKGROUND + "   ");
                else if (c == 'N') System.out.print(ConsoleColors.BLACK_BACKGROUND + "   ");
                else if (c == 'Y') System.out.print(ConsoleColors.YELLOW_BACKGROUND + "   ");
                else if (c == 'U') System.out.print(ConsoleColors.BLUE_BACKGROUND + "   ");
                else if (c == 'O') System.out.print(ConsoleColors.RED_BACKGROUND_BRIGHT + "   ");
                else if (c == 'M') System.out.print(ConsoleColors.PURPLE_BACKGROUND + "   ");
                else if (c == 'C') System.out.print(ConsoleColors.CYAN_BACKGROUND + "   ");
                else               System.out.print(ConsoleColors.WHITE_BACKGROUND + "   ");
                System.out.print(ConsoleColors.RESET);
            }
            System.out.println();
        }
    }

    // ===================== PASO 2: TABLA DE POSICIONES =====================
    static void menuTabla() {
        Scanner sc = new Scanner(System.in);
        int pagina = 0;
        int equiposPorPagina = 16;
        int totalPaginas = (48 + equiposPorPagina - 1) / equiposPorPagina;
        String continuar = "s";

        while (!continuar.equalsIgnoreCase("n")) {
            System.out.println(ConsoleColors.CYAN_BOLD + "\n=== TABLA DE POSICIONES - Pagina " + (pagina+1) + "/" + totalPaginas + " ===" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.YELLOW_BOLD +
                String.format("%-4s %-20s %4s %4s %4s %4s %4s %4s %4s %4s %4s %4s",
                "Grp","Equipo","PJ","PG","PE","PP","GF","GC","DG","TA","TR","Pts") + ConsoleColors.RESET);
            System.out.println(ConsoleColors.CYAN + "─".repeat(80) + ConsoleColors.RESET);

            int inicio = pagina * equiposPorPagina;
            int fin = inicio + equiposPorPagina;
            if (fin > 48) fin = 48;

            for (int i = inicio; i < fin; i++) {
                int g = i / 4;
                String grupoLetter = nombresGrupos[g];
                int[] stats = tablaPos[i];
                System.out.println(
                    ConsoleColors.WHITE +
                    String.format("%-4s %-20s %4d %4d %4d %4d %4d %4d %4d %4d %4d %4d",
                        grupoLetter, nombresEquipos[i],
                        stats[0], stats[1], stats[2], stats[3],
                        stats[4], stats[5], stats[6],
                        stats[7], stats[8], stats[9]) +
                    ConsoleColors.RESET
                );
            }

            System.out.println(ConsoleColors.CYAN + "─".repeat(80) + ConsoleColors.RESET);
            System.out.println("1. Siguiente pagina  2. Anterior  3. Editar datos  0. Volver");
            System.out.print(ConsoleColors.YELLOW + "Opcion: " + ConsoleColors.RESET);
            int op = ConsoleInput.readInt();

            if (op == 1) { if (pagina < totalPaginas-1) pagina++; }
            else if (op == 2) { if (pagina > 0) pagina--; }
            else if (op == 3) editarTabla();
            else if (op == 0) continuar = "n";
        }
    }

    static void editarTabla() {
        System.out.println(ConsoleColors.CYAN_BOLD + "\n=== EDITAR TABLA ===" + ConsoleColors.RESET);
        System.out.print("Numero de equipo (1-48): ");
        int n = ConsoleInput.readInt();
        if (n < 1 || n > 48) { System.out.println(ConsoleColors.RED + "Invalido." + ConsoleColors.RESET); return; }
        int idx = n - 1;
        System.out.println("Editando: " + ConsoleColors.YELLOW + nombresEquipos[idx] + ConsoleColors.RESET);
        String[] labels = {"PJ","PG","PE","PP","GF","GC","DG","TA","TR","Pts"};
        for (int j = 0; j < 10; j++) {
            System.out.print(labels[j] + " [actual=" + tablaPos[idx][j] + "]: ");
            tablaPos[idx][j] = ConsoleInput.readInt();
        }
        System.out.println(ConsoleColors.GREEN + "Datos actualizados." + ConsoleColors.RESET);
    }

    // ===================== PASO 3: FIXTURE =====================
    static void menuFixture(Scanner sc) {
        int op = -1;
        while (op != 0) {
            System.out.println(ConsoleColors.CYAN_BOLD + "\n=== FIXTURE DE PARTIDOS ===" + ConsoleColors.RESET);
            System.out.println("1. Ver partidos por grupo");
            System.out.println("2. Ver partido especifico");
            System.out.println("0. Volver");
            System.out.print(ConsoleColors.YELLOW + "Opcion: " + ConsoleColors.RESET);
            op = ConsoleInput.readInt();

            if (op == 1) {
                System.out.print("Grupo (A-L) o T para todos: ");
                String g = ConsoleInput.readString().toUpperCase();
                int gIdx = -1;
                if (g.equals("T")) gIdx = -2;
                else {
                    for (int i = 0; i < 12; i++) if (nombresGrupos[i].equals(g)) gIdx = i;
                }
                if (gIdx == -1) { System.out.println(ConsoleColors.RED + "Grupo no encontrado." + ConsoleColors.RESET); continue; }

                for (int p = 0; p < 72; p++) {
                    int pg = fixture[p][0];
                    if (gIdx == -2 || pg == gIdx) {
                        int e1 = pg*4 + fixture[p][1];
                        int e2 = pg*4 + fixture[p][2];
                        System.out.println(ConsoleColors.YELLOW + "Grupo " + nombresGrupos[pg] + " | " + ConsoleColors.RESET +
                            ConsoleColors.WHITE + nombresEquipos[e1] + " vs " + nombresEquipos[e2] +
                            ConsoleColors.CYAN + "  [" + fechasFixture[p] + " " + horasFixture[p] + "] " +
                            sedesFixture[p] + ConsoleColors.RESET);
                    }
                }
            } else if (op == 2) {
                System.out.print("Numero de partido (1-72): ");
                int pn = ConsoleInput.readInt();
                if (pn < 1 || pn > 72) { System.out.println(ConsoleColors.RED + "Invalido." + ConsoleColors.RESET); continue; }
                int p = pn - 1;
                int pg = fixture[p][0];
                int e1 = pg*4 + fixture[p][1];
                int e2 = pg*4 + fixture[p][2];
                System.out.println(ConsoleColors.CYAN_BOLD + "\n=== PARTIDO #" + pn + " ===" + ConsoleColors.RESET);
                System.out.println("Grupo : " + ConsoleColors.YELLOW + nombresGrupos[pg] + ConsoleColors.RESET);
                System.out.println("Equipo 1: " + ConsoleColors.WHITE_BOLD + nombresEquipos[e1] + ConsoleColors.RESET);
                System.out.println("Equipo 2: " + ConsoleColors.WHITE_BOLD + nombresEquipos[e2] + ConsoleColors.RESET);
                System.out.println("Fecha : " + ConsoleColors.GREEN + fechasFixture[p] + ConsoleColors.RESET);
                System.out.println("Hora  : " + ConsoleColors.GREEN + horasFixture[p] + ConsoleColors.RESET);
                System.out.println("Sede  : " + ConsoleColors.GREEN + sedesFixture[p] + ConsoleColors.RESET);

                System.out.println(ConsoleColors.CYAN + "\n-- Banderas --" + ConsoleColors.RESET);
                System.out.print(nombresEquipos[e1] + ": ");
                System.out.println();
                imprimirBandera(e1, 2);
                System.out.print(nombresEquipos[e2] + ": ");
                System.out.println();
                imprimirBandera(e2, 2);
            }
        }
    }

    // ===================== PASO 4: INFO PAISES =====================
    static void menuInfoPais(Scanner sc) {
        System.out.println(ConsoleColors.CYAN_BOLD + "\n=== INFORMACION DE PAISES ===" + ConsoleColors.RESET);
        System.out.println("Equipos disponibles:");
        for (int i = 0; i < 48; i++) {
            System.out.print(ConsoleColors.WHITE + (i+1) + "." + nombresEquipos[i] + "  " + ConsoleColors.RESET);
            if ((i+1) % 4 == 0) System.out.println();
        }
        System.out.print(ConsoleColors.YELLOW + "\nNumero de equipo (1-48): " + ConsoleColors.RESET);
        int n = ConsoleInput.readInt();
        if (n < 1 || n > 48) { System.out.println(ConsoleColors.RED + "Invalido." + ConsoleColors.RESET); return; }
        int idx = n - 1;
        int g = idx / 4;

        System.out.println(ConsoleColors.YELLOW_BOLD);
        System.out.println("  ╔══════════════════════════════════╗");
        System.out.println("  ║  FICHA DEL PAIS                 ║");
        System.out.println("  ╚══════════════════════════════════╝");
        System.out.println(ConsoleColors.RESET);

        imprimirBandera(idx, 3);

        System.out.println(ConsoleColors.CYAN_BOLD + "\nPais    : " + ConsoleColors.WHITE_BOLD + nombresEquipos[idx] + ConsoleColors.RESET);
        System.out.println(ConsoleColors.CYAN + "Grupo   : " + ConsoleColors.WHITE + nombresGrupos[g] + ConsoleColors.RESET);
        System.out.println(ConsoleColors.CYAN + "Capital : " + ConsoleColors.WHITE + infoPaises[idx][0] + ConsoleColors.RESET);
        System.out.println(ConsoleColors.CYAN + "Jugadores destacados: " + ConsoleColors.WHITE + infoPaises[idx][1] + ConsoleColors.RESET);
        System.out.println(ConsoleColors.CYAN + "Apariciones en Mundiales: " + ConsoleColors.WHITE + infoPaises[idx][2] + ConsoleColors.RESET);

        System.out.println(ConsoleColors.CYAN + "\nCompaneros de grupo:" + ConsoleColors.RESET);
        for (int e = 0; e < 4; e++) {
            int eIdx = g*4 + e;
            if (eIdx != idx) {
                System.out.println(ConsoleColors.WHITE + "  - " + nombresEquipos[eIdx] + ConsoleColors.RESET);
            }
        }
        System.out.print(ConsoleColors.GREEN + "\nPresione ENTER para continuar..." + ConsoleColors.RESET);
        ConsoleInput.readString();
    }
}
