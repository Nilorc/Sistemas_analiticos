import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Bienvenidos al juego de Esparco, la hormiga que come trigo");
        System.out.println("-----------------------------------------------------------");

        // Ingresar dimensiones de la chacra
        System.out.print("Ingrese el ancho de la chacra en hormigómetros (hmgm): ");
        int width = Integer.parseInt(scanner.nextLine());

        System.out.print("Ingrese el largo de la chacra en hormigómetros (hmgm): ");
        int length = Integer.parseInt(scanner.nextLine());

        // Ingresar posición inicial de la hormiga
        System.out.print("Ingrese la posición (x, y) de la hormiga: ");
        String[] position = scanner.nextLine().split(" ");
        int startX = Integer.parseInt(position[0]);
        int startY = Integer.parseInt(position[1]);

        // Ingresar dirección hacia la que apunta la cabeza de la hormiga
        System.out.print("Ingrese la dirección hacia la que apunta la cabeza de la hormiga (U,R,D,L): ");
        char direction = scanner.nextLine().charAt(0);

        // Ingresar número de fotos que tomará el poblador observador
        System.out.print("Ingrese el número de fotos que capturará el poblador observador: ");
        int numPhotos = Integer.parseInt(scanner.nextLine());

        // Ingresar número de ampliación de filas y columnas
        System.out.print("Ingrese el número de ampliación de filas y columnas en las que se ampliará la matriz: ");
        int expansion = Integer.parseInt(scanner.nextLine());

        // Crear objeto Chacra
        Chacra chacra = new Chacra(width, length, startX, startY, direction, expansion);

        // Tomar fotos
        for (int i = 1; i <= numPhotos; i++) {
            System.out.println("------------------------------------------------------");
            System.out.println("Foto " + i + ": Momento previo a la recolección de la hormiga:");
            System.out.println("------------------------------------------------------");
            chacra.printChacra();
            chacra.moveHormiga();
            System.out.println("-------------------------------------------------------");
            System.out.println("La foto numero " + (i+1) + " de la hormiga en la chacra:");
            System.out.println("-------------------------------------------------------");
            chacra.printChacra();
        }

        scanner.close();
    }
}
