import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class JuegoWordle {
    private String palabraObjetivo; 
    private String[][] tablero; 
    private List<String> palabrasPosibles; 
    private boolean juegoTerminado;

    public JuegoWordle(List<String> palabrasPosibles) {
        this.palabrasPosibles = palabrasPosibles;
        this.tablero = new String[6][5];
        this.juegoTerminado = false;
        seleccionarPalabraObjetivo();
        inicializarTablero();
    }

    private void inicializarTablero() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                tablero[i][j] = "[ ]";
            }
        }
    }

    private void seleccionarPalabraObjetivo() {
        Random rand = new Random();
        int indiceAleatorio = rand.nextInt(palabrasPosibles.size());
        palabraObjetivo = palabrasPosibles.get(indiceAleatorio);
    }

    private void mostrarTablero() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(tablero[i][j]);
            }
            System.out.println();
        }
    }

    private boolean adivinoPalabra(String palabra) {
        return palabra.equals(palabraObjetivo);
    }

    private boolean letraEnPalabraObjetivo(char letra) {
        return palabraObjetivo.indexOf(letra) != -1;
    }

    public void jugar() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Bienvenido a Wordle!");
        
        for (int fila = 0; fila < 6; fila++) {
            System.out.print("Ingresa una palabra de 5 letras: ");
            String palabra = scanner.next().toLowerCase(); 

            if (palabra.length() != 5) {
                System.out.println("La palabra debe tener exactamente 5 letras.");
                fila--; 
                continue;
            }
            
            boolean algunaLetraCorrecta = false;
            boolean[] letraCorrectaPosicion = new boolean[5];

            for (int i = 0; i < 5; i++) {
                if (palabraObjetivo.charAt(i) == palabra.charAt(i)) {
                    letraCorrectaPosicion[i] = true;
                } else if (letraEnPalabraObjetivo(palabra.charAt(i))) {
                    algunaLetraCorrecta = true;
                }
            }

            for (int i = 0; i < 5; i++) {
                if (letraCorrectaPosicion[i]) {
                    tablero[fila][i] = "[" + palabra.charAt(i) + "]";
                } else {
                    tablero[fila][i] = "[/]";
                }
            }

            mostrarTablero();

            if (algunaLetraCorrecta) {
                System.out.println("Algunas letras estan en la palabra, pero no en la posición correcta.");
            }
            
            if (adivinoPalabra(palabra)) {
                System.out.println("Feliciadades adivinaste la palabra!");
                juegoTerminado = true;
                break;
            }
        }

        if (!juegoTerminado) {
            System.out.println("\nNo has adivinado la palabra objetivo.");
            System.out.println("La palabra era: " + palabraObjetivo);
        }

        scanner.close();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> palabrasPosibles = Arrays.asList("manos", "lugar", "playa", "lugar", "arbol"); 
        JuegoWordle juegoWordle = new JuegoWordle(palabrasPosibles);
        juegoWordle.jugar();

        scanner.close();
    }
}
