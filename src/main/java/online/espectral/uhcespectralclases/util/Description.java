package online.espectral.uhcespectralclases.util;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.util.ArrayList;
import java.util.List;

public class Description {
    public static List<String> helpMessage() {
        List<String> msg = new ArrayList<>();
        msg.add("=====(" + ChatColor.AQUA + ChatColor.BOLD + "UHC CLASES " + ChatColor.RED + "V.2" + ChatColor.RESET + ")=====");
        msg.add("");
        msg.add(ChatColor.GOLD + "/class menu" + ChatColor.RESET + ": Muestra el panel de control de hosts. (/class solo tambien lo mostrará)");
        msg.add(ChatColor.GOLD + "/class selector" + ChatColor.RESET + ": Reparte el selector de clases a los jugadores que no lo tengan");
        msg.add(ChatColor.GOLD + "/class list" + ChatColor.RESET + ": Lista el ID de las clases con el que funcionan los comandos");
        msg.add(ChatColor.GOLD + "/class give <Jugador> <UhcClass>" + ChatColor.RESET + ": Otorga una clase al jugador");
        msg.add(ChatColor.GOLD + "/class get <Jugador>" + ChatColor.RESET + ": Te muestra la clase que lleva equipada el jugador");
        msg.add(ChatColor.GOLD + "/class start" + ChatColor.RESET + ": Reparte el selector de clases a los jugadores que no lo tengan");
        msg.add(ChatColor.GOLD + "/class reload" + ChatColor.RESET + ": Borra las clases y efectos aplicados de todos los jugadores");
        msg.add(ChatColor.GOLD + "/class help" + ChatColor.RESET + ": Mostrar esta lista");
        msg.add(ChatColor.GOLD + "/class credits" + ChatColor.RESET + ": Creditos e Informacion del Plugin");
        msg.add("");
        msg.add("============================");
        return msg;
    }

    public static List<String> witch() {
        List<String> msg = new ArrayList<>();
        msg.add(ChatColor.BLACK + "===== (" + ChatColor.LIGHT_PURPLE + ChatColor.BOLD + " BRUJA " + ChatColor.RESET + ChatColor.BLACK + ") =====");
        msg.add("");
        msg.add("Las brujas reciben una poderosa varita con 4 Hechizos:");
        msg.add("");
        msg.add(ChatColor.RED + "Curación" + ChatColor.RESET + ": Aplica " + ChatColor.RED + ChatColor.BOLD + "VIDA INSTANTANEA II" + ChatColor.RESET + " a todas las entidades en un radio de 3 bloques. Un proposito mas para Supoort del equipo");
        msg.add("");
        msg.add(ChatColor.DARK_RED + "Daño" + ChatColor.RESET + ": Aplica " + ChatColor.DARK_RED + ChatColor.BOLD + "DAÑO INSTANTANEO I" + ChatColor.RESET + " a todas las entidades en un radio de 3 bloques. Toda bruja debe poder defenderse ¿no?");
        msg.add("");
        msg.add(ChatColor.GOLD + "Fireball" + ChatColor.RESET + ": Lanza una bola de fuego en la direccion que miras. Un ataque a distancia, que no se queda corto...");
        msg.add("");
        msg.add(ChatColor.YELLOW + "Revelo" + ChatColor.RESET + ": Desvela la posicion de todos los jugadoes en un radio de 32 bloques.");
        msg.add("");
        msg.add("Todos los efectos afectan a los jugadores y entidades cercanas independientemente del equipo al que pertenezcan, pero ninguna actua sobre la propia bruja.");
        msg.add("");
        msg.add(ChatColor.BLACK + "===================");
        return msg;
    }
    public static List<String> breeze() {
        List<String> msg = new ArrayList<>();
        msg.add(ChatColor.BLACK + "===== (" + net.md_5.bungee.api.ChatColor.of(new Color(178, 242, 245)) + ChatColor.BOLD + " BREEZE " + ChatColor.RESET + ChatColor.BLACK + ") =====");
        msg.add("");
        msg.add("El Breeze es una clase con un estilo mas escurridizo, con una movilidad mas potente.");
        msg.add("");
        msg.add("Los Breezes tienen " + ChatColor.AQUA + ChatColor.BOLD + "VELOCIDAD II" + ChatColor.RESET + " permanente, y recibiran una " + net.md_5.bungee.api.ChatColor.of(new Color(178, 242, 245)) + "Varita de Breeze" + ChatColor.RESET + " que cuenta cuenta con dos habilidades");
        msg.add("");
        msg.add(ChatColor.YELLOW + "Click Derecho" + ChatColor.RESET + ": Lanza una Carga de viento en la direccion que miras. Usando esta varita, las cargas son infinitas. " + ChatColor.DARK_GRAY + "(1s Cooldown)");
        msg.add("");
        msg.add(ChatColor.YELLOW + "Click Izquierdo" + ChatColor.RESET + ": Impulsa al jugador en la direccion a la que mira. Al caer crea una explosion de viento que impulsa a las entidades cercanas y cancelando el daño de caida. " + ChatColor.DARK_GRAY + "(60s Cooldown)");
        msg.add("");
        msg.add(ChatColor.BLACK + "===================");
        return msg;
    }
    public static List<String> spider() {
        List<String> msg = new ArrayList<>();
        msg.add(ChatColor.BLACK + "===== (" + ChatColor.DARK_RED + ChatColor.BOLD + " ARAÑA " + ChatColor.RESET + ChatColor.BLACK + ") =====");
        msg.add("");
        msg.add("Las Arañas son algo mas ofensivas y molestas. Estas recibiran una pack de " + ChatColor.BOLD + "Bombas de Telarañas");
        msg.add("");
        msg.add("Cuando estas bombas impacten en el suelo, clocaran cobwebs en el suelo y asi atrapar a las entidades cercanas");
        msg.add("");
        msg.add("Las Arañas tambien completamente inmunes al " + ChatColor.DARK_GREEN + ChatColor.BOLD + "VENENO" + ChatColor.RESET + ", y cuentan con efecto " + ChatColor.BOLD + "WEAVING" + ChatColor.RESET + " permanente");
        msg.add("");
        msg.add("Las Arañas se ven afectadas por el encantamiento " + ChatColor.RED + ChatColor.BOLD + "Perdicion de los Artropodos" + ChatColor.RESET + " por lo que recibiran mas daño por este encantamiento");
        msg.add("");
        msg.add(ChatColor.BLACK + "===================");
        return msg;
    }
    public static List<String> dolphin() {
        List<String> msg = new ArrayList<>();
        msg.add(ChatColor.BLACK + "===== (" + ChatColor.AQUA + ChatColor.BOLD + " DELFÍN " + ChatColor.RESET + ChatColor.BLACK + ") =====");
        msg.add("");
        msg.add("Los Delfines os beneficiais de entornos acuaticos, un spawn en oceano no sera tan malo por una vez...");
        msg.add("");
        msg.add("Los Delfines recibirán " + ChatColor.BLUE + ChatColor.BOLD + "CONDUIT POWER" + ChatColor.RESET + " y " + ChatColor.AQUA + ChatColor.BOLD + "GRACIA DEL DELFIN" + ChatColor.RESET + " infinito. Junto a un casco equivalente a Hierro con Afinidad Acuatica y Proteccion 2, y un tridente con Leatad III irrompible.");
        msg.add("");
        msg.add("Los delfines se moveran muy bien por el overworld, pero en el nether se ven afectados por el ambiente tan seco. Si un Delfin entra al nether recibe " + ChatColor.GRAY + ChatColor.BOLD + "LENTITUD I" + ChatColor.RESET + " y " + ChatColor.DARK_GRAY  + ChatColor.BOLD + "DEBILIDAD I" + ChatColor.RESET + " durante su estancia en la dimension.");
        msg.add("");
        msg.add(ChatColor.BLACK + "===================");
        return msg;
    }
    public static List<String> iron_golem() {
        List<String> msg = new ArrayList<>();
        msg.add(ChatColor.BLACK + "===== (" + ChatColor.GRAY + ChatColor.BOLD + " GOLEM DE HIERRO " + ChatColor.RESET + ChatColor.BLACK + ") =====");
        msg.add("");
        msg.add("Los Golems sois los tanques del equipo en pocas palabras. Si un golem consigue Proteccion IV ya puede chupar todo el daño que quiera.");
        msg.add("");
        msg.add("Todos los Golems cuentan con una reduccion de daño del 50% permanente, pero ser tan robustos os causa ser lentos, por loque tambien recibis " + ChatColor.GRAY + ChatColor.BOLD + "LENTITUD I" + ChatColor.RESET + " permanente.");
        msg.add("");
        msg.add("Tambien empezaran con un " + ChatColor.GRAY + ChatColor.BOLD + "Martillo de Golem" + ChatColor.RESET + ", con el que podran pegar un golpe al suelo y levantar a todas las entidades cercanas al impacto. usar la habilidad otorgará al jugador 5 segundos de " + ChatColor.YELLOW + ChatColor.BOLD + "ABSORCIÓN III" + ChatColor.RESET + ChatColor.DARK_GRAY + " (30s Cooldown)");
        msg.add("");
        msg.add("La clase añade al juego un nuevo item: " + ChatColor.GRAY + ChatColor.BOLD + "Hierro Concentrado" + ChatColor.RESET + ". Se Craftea con 4 lingotes de oro y un bloque de hierro. Si un Golem se come este item, le regenrara 1 corazon al instante, pero si lo hace otro jugador puede que no le siente tan bien comer tanto hierro");
        msg.add("");
        msg.add(ChatColor.BLACK + "===================");
        return msg;
    }
    public static List<String> warrior() {
        List<String> msg = new ArrayList<>();
        msg.add(ChatColor.BLACK + "===== (" + net.md_5.bungee.api.ChatColor.of(new Color(203,109,81)) + ChatColor.BOLD + " GUERRERO " + ChatColor.RESET + ChatColor.BLACK + ") =====");
        msg.add("Los Guerreros podrán demostrar su valia cuando van a las batallas cuerpo a cuerpo.");
        msg.add("");
        msg.add("Los Guerreros reciben un " + ChatColor.GOLD + ChatColor.BOLD + "Hacha de Guerrero" + ChatColor.RESET + " que al hacer Shift + Click Derecho desatar un habilidad de " + ChatColor.LIGHT_PURPLE + ChatColor.BOLD + "IRA CANDY");
        msg.add("");
        msg.add("Cuando un Guerrero desata su " + ChatColor.LIGHT_PURPLE + "Ira Candy" + ChatColor.RESET + ", recibe 10 segundos de " + ChatColor.DARK_RED + ChatColor.BOLD + "FUERZA II" + ChatColor.RESET + ", " + ChatColor.AQUA + ChatColor.BOLD + "VELOCIDAD II" + ChatColor.RESET + " y " + ChatColor.YELLOW + ChatColor.BOLD + "GLOWING");
        msg.add("El Guerrero tiene 10 segundos antes de que se le vayan los efectos. Si el Guerrero golpea a un jugador, o recibe un golpe durante la " + ChatColor.LIGHT_PURPLE + "Ira Candy" + ChatColor.RESET + ", esta se apagará");
        msg.add("");
        msg.add("Los Guerreros no estan tan preparados para un combate a distancia, por lo que reciben un 25% mas de daño de los proyectiles procedentes de Jugadores.");
        msg.add("");
        msg.add(ChatColor.BLACK + "===================");
        return msg;
    }
    public static List<String> gunner() {
        List<String> msg = new ArrayList<>();
        msg.add(ChatColor.BLACK + "===== (" + ChatColor.RED + ChatColor.BOLD + " CAÑONERO " + ChatColor.RESET + ChatColor.BLACK + ") =====");
        msg.add("");
        msg.add("El Cañonero esta hecho para tomar distancias con un bombardeo estrategico a los enemigos");
        msg.add("");
        msg.add("Los Cañoneros empezais con un casco de Hierro equipado con " + ChatColor.RED + ChatColor.BOLD + "Proteccion contra explosiones V" + ChatColor.RESET + " y un " + ChatColor.RED + "Cañon de Polvora");
        msg.add("");
        msg.add("Este " + ChatColor.RED + "Cañon de Polvora" + ChatColor.RESET + " consume Polvora de tu inventario para disparar Mini-TNTs que explotaran en cuento toquen algo. " + ChatColor.DARK_GRAY + "(1s Cooldown entre disparos)");
        msg.add("");
        msg.add(ChatColor.BLACK + "===================");
        return msg;
    }
    public static List<String> builder() {
        List<String> msg = new ArrayList<>();
        msg.add(ChatColor.BLACK + "===== (" + ChatColor.BLUE + ChatColor.BOLD + " ARQUITECTO " + ChatColor.RESET + ChatColor.BLACK + ") =====");
        msg.add("");
        msg.add("El Arquitecto plantea implementar un ambiente mas estrategico en el equipo.");
        msg.add("");
        msg.add("Los Arquitectos reciben una " + ChatColor.BLUE + "Herramienta de Arquitecto" + ChatColor.RESET + " que les dará la opcion de colocar las estructura que ellos elijan. " + ChatColor.DARK_GRAY + "(30s Cooldown)");
        msg.add("");
        msg.add("La " + ChatColor.BLUE + "Herramienta del Arquitecto" + ChatColor.RESET + " le permite colocar al jugador 3 estructuras diferentes hechas de bloques de Ladrillo: Una Pared " + ChatColor.BLUE + "⬛" + ChatColor.RESET + ", una Escalera " + ChatColor.BLUE + "◣" + ChatColor.RESET + " o una MiniTorre " + ChatColor.BLUE + "♜" + ChatColor.RESET + ".");
        msg.add("");
        msg.add("Tambien recibiran un stack de " + ChatColor.BLUE + "Bloques de Constructor" + ChatColor.RESET + ", bloques que el Arquitecto puede colocar y nunca se le gastarán");
        msg.add("");
        msg.add(ChatColor.BLACK + "===================");
        return msg;
    }
    public static List<String> miner() {
        List<String> msg = new ArrayList<>();
        msg.add(ChatColor.BLACK + "===== (" + ChatColor.YELLOW + ChatColor.BOLD + " MINERO " + ChatColor.RESET + ChatColor.BLACK + ") =====");
        msg.add("");
        msg.add("Los Mineros son clave para aquellos que controleis bien las cuevas. Si lo haceis, esto será otra forma nueva de jugar.");
        msg.add("");
        msg.add("Los Mineros reciben " + ChatColor.BLUE + ChatColor.BOLD + "VISION NOCTURNA" + ChatColor.RESET + " y " + ChatColor.YELLOW + ChatColor.BOLD + "PRISA MINERA I" + ChatColor.RESET + " de forma pemanante, y " + ChatColor.AQUA + ChatColor.BOLD + "VELOCIDAD II" + ChatColor.RESET + " mientras esten bajo tierra.");
        msg.add("");
        msg.add("Todos los mineros recibiran un " + ChatColor.YELLOW + "Pico Espectral" + ChatColor.RESET + " que les permite alcanzar los bloques desde mas lejos y picarlos con mayor seguridad. Ademas, siempre que piquen minerales con este pico, tienen un 10% de conseguir un drop extra del mineral.");
        msg.add("");
        msg.add(ChatColor.BLACK + "===================");
        return msg;
    }
    public static List<String> antman() {
        List<String> msg = new ArrayList<>();
        msg.add(ChatColor.BLACK + "===== (" + ChatColor.GREEN + ChatColor.BOLD + " ANTMAN " + ChatColor.RESET + ChatColor.BLACK + ") =====");
        msg.add("");
        msg.add("Los Antmans podran enfocar las partidas desde \"otro punto de vista\"");
        msg.add("");
        msg.add("Los Antmans reciben un dispositivo llamado " + ChatColor.LIGHT_PURPLE + "Cambia Formas" + ChatColor.RESET + " con el que podrán elegir entre " + net.md_5.bungee.api.ChatColor.of(new Color(255, 152, 28)) + "AUMENTAR" + ChatColor.RESET + " o " + net.md_5.bungee.api.ChatColor.of(new Color(28, 255,231)) + "menguar" + ChatColor.RESET + " su tamaño original. " + ChatColor.DARK_GRAY + "(60s de Cooldown)");
        msg.add("");
        msg.add("Si el jugador " + net.md_5.bungee.api.ChatColor.of(new Color(255, 152, 28)) + "AUMENTA" + ChatColor.RESET + " su tamaño, recibira 6 corazones de Absorcion y mayor rango de alcance, será un poco mas lento pero podrá saltar bloques de 1.5 de alto.");
        msg.add("");
        msg.add("Si el jugador " + net.md_5.bungee.api.ChatColor.of(new Color(28, 255,231)) + "mengua" + ChatColor.RESET + " su tamaño, será mas debil a la hora de golpear y le costará mas saltar los bloques.");
        msg.add("");
        msg.add(ChatColor.BLACK + "===================");
        return msg;
    }
    public static List<String> wither() {
        List<String> msg = new ArrayList<>();
        msg.add(ChatColor.BLACK + "===== (" + ChatColor.DARK_GRAY + ChatColor.BOLD + " WITHER " + ChatColor.RESET + ChatColor.BLACK + ") =====");
        msg.add("");
        msg.add("Un Wither bien jugador es el mejor DPS de un equipo");
        msg.add("");
        msg.add("Los jugadores Wither son completamente inmunes al efecto " + ChatColor.DARK_GRAY + ChatColor.BOLD + "WITHER");
        msg.add("");
        msg.add("Recibiran una espada de piedra irrompible llamada " + ChatColor.DARK_GRAY + "Espada Putrefacta" + ChatColor.RESET + " con la que, al pegar a los enemigos, les aplicara " + ChatColor.DARK_GRAY + ChatColor.BOLD + "WITHER II" + ChatColor.RESET + " durante 3 segundos");
        msg.add("");
        msg.add("Si un enemigo golpea a un jugador Wither usando " + ChatColor.RED + ChatColor.BOLD + "Golpeo" + ChatColor.RESET + ", recibira el daño extra del encantamiento.");
        msg.add("");
        msg.add(ChatColor.BLACK + "===================");
        return msg;
    }

    public static List<String> blaze() {
        List<String> msg = new ArrayList<>();
        msg.add(ChatColor.BLACK + "===== (" + net.md_5.bungee.api.ChatColor.of(new Color(249,195,13)) + ChatColor.BOLD + " WITHER " + ChatColor.RESET + ChatColor.BLACK + ") =====");
        msg.add("");
        msg.add("Un Blaze te ahorrará muchos problemas durante tu estancia en el Nether, el mejor DPS si te mueves mucho en ese entorno.");
        msg.add("");
        msg.add("Los Blazes contais con " + ChatColor.GOLD + ChatColor.BOLD + "RESISTENCIA AL FUEGO" + ChatColor.RESET + " permanente y recibireis un item con el que podreis activar vuestra habilidad " + ChatColor.GOLD + ChatColor.BOLD + "Puños De Fuego");
        msg.add("");
        msg.add("Mientras esta habilidad este activa, el jugador se vera envuelto en fuego, y todos los golpes que realice (tanto a melee como arco) prenderan en fuego al enemigo. " + ChatColor.DARK_GRAY + "(60s Cooldown)");
        msg.add("");
        msg.add("Al ser Blazes, el agua no será vuestra mejor amiga. Mientras que un blaze este en contancto con el agua, este recibe " + ChatColor.GRAY + ChatColor.BOLD + "LENTITUD I" + ChatColor.RESET + " y " + ChatColor.DARK_GRAY + ChatColor.BOLD + "CEGUERA");
        msg.add("");
        msg.add(ChatColor.BLACK + "===================");
        return msg;
    }
    public static List<String> creditsMessage() {
        List<String> msg = new ArrayList<>();
        msg.add(ChatColor.BLACK + "===== ( " + ChatColor.GOLD + ChatColor.BOLD + "UHC" + ChatColor.AQUA + ChatColor.BOLD + " CLASES " + ChatColor.RED + ChatColor.BOLD + "V.2 " + ChatColor.RESET + ChatColor.BLACK + ") =====");
        msg.add("");
        msg.add("Plugin Creado por: " + ChatColor.BLUE + "MarkManFlame_55");
        msg.add("");
        msg.add("Redes Sociales:");
        msg.add("");
        msg.add(ChatColor.AQUA + "" +  ChatColor.BOLD + "Twitter: "  + ChatColor.RESET +  ChatColor.GRAY + ChatColor.UNDERLINE + "https://twitter.com/MarkManFlame_55");
        msg.add(ChatColor.RED + "" + ChatColor.BOLD + "YouTube: " + ChatColor.RESET + ChatColor.GRAY + ChatColor.UNDERLINE +"https://youtube.com/@markmanflame_5542?si=QdTiH4Zzv12eTlqM");
        msg.add(ChatColor.DARK_GRAY + "" + ChatColor.BOLD + "GitHub: " + ChatColor.RESET + ChatColor.GRAY + ChatColor.UNDERLINE + "https://github.com/MarkManFlame55");
        msg.add("");
        msg.add("Modalidad creada para la Comunidad de " + ChatColor.GOLD + ChatColor.BOLD + "UHC Espectral");
        msg.add("");
        msg.add("Puedes unirte a su " + ChatColor.BLUE + ChatColor.BOLD + "Discord" + ChatColor.RESET + " usando este enlace:");
        msg.add(ChatColor.UNDERLINE + "https://discord.gg/espectral-648307511648321536");
        msg.add("");
        msg.add(ChatColor.YELLOW + "" + ChatColor.UNDERLINE +  "Creditos Extra:");
        msg.add("");
        msg.add(ChatColor.AQUA + "CarlosDiamon" + ChatColor.RESET + ": Implementacion de World Edit en el codigo.");
        msg.add(ChatColor.DARK_GRAY + "(https://github.com/carlosdiamon)");
        msg.add("");
        msg.add("Para mas informacion sobre futuras versiones o Bug Fixes, puedes unirte al Discord de UHC Espectral o revisar el repositorio de GitHub");
        msg.add(ChatColor.DARK_GRAY + "" + ChatColor.UNDERLINE + "https://github.com/MarkManFlame55/UhcEspectralClasesV2");
        msg.add("");
        msg.add(ChatColor.BLACK + "===================");
        return msg;




    }
}
