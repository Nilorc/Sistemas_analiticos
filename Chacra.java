public class Chacra {
    private int width;
    private int length;
    private char[][] grid;
    private int startX;
    private int startY;
    private char direction;
    private int expansion;
    private int photosCaptured; // Contador de fotos capturadas

    public Chacra(int width, int length, int startX, int startY, char direction, int expansion) {
        this.width = width;
        this.length = length;
        this.grid = new char[length][width];
        this.startX = startX;
        this.startY = startY;
        this.direction = direction;
        this.expansion = expansion;
        this.photosCaptured = 0;

        // Inicializar la matriz con trigo
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                grid[i][j] = '•';
            }
        }

        // Colocar a la hormiga en la posición inicial
        grid[startY][startX] = 'H';
    }

    public void moveHormiga() {
        int x = startX;
        int y = startY;
        char currentDirection = direction;

        while (true) {
            if (grid[y][x] == '•') { // Si la hormiga encuentra trigo
                grid[y][x] = 'h'; // Deja una feromona
                currentDirection = turnRight(currentDirection); // Gira 90 grados hacia la derecha
                moveForward(currentDirection, x, y); // Avanza 1 hormigómetro de distancia
            } else if (grid[y][x] == 'h') { // Si la hormiga encuentra una feromona
                grid[y][x] = '■'; // Deja una pieza de trigo
                currentDirection = turnLeft(currentDirection); // Gira 90 grados hacia la izquierda
                moveForward(currentDirection, x, y); // Avanza 1 hormigómetro de distancia
            }

            // Verificar si la hormiga ha llegado al límite de la chacra
            if (x < 0 || y < 0 || x >= width || y >= length) {
                expandChacra();
            }

            // Si se han capturado suficientes fotos, salir del bucle
            if (numFotosCapturadas() >= 1000) {
                break;
            }
        }
        photosCaptured++;
    }

    private char turnRight(char currentDirection) {
        if (currentDirection == 'U') {
            return 'R';
        } else if (currentDirection == 'R') {
            return 'D';
        } else if (currentDirection == 'D') {
            return 'L';
        } else {
            return 'U';
        }
    }

    private char turnLeft(char currentDirection) {
        if (currentDirection == 'U') {
            return 'L';
        } else if (currentDirection == 'R') {
            return 'U';
        } else if (currentDirection == 'D') {
            return 'R';
        } else {
            return 'D';
        }
    }

    private void moveForward(char direction, int x, int y) {
        switch (direction) {
            case 'U':
                startY = y - 1;
                break;
            case 'R':
                startX = x + 1;
                break;
            case 'D':
                startY = y + 1;
                break;
            case 'L':
                startX = x - 1;
                break;
        }
    }

    private void expandChacra() {
        // Expandir la matriz
        char[][] newGrid = new char[length + expansion * 2][width + expansion * 2];

        // Llenar la nueva matriz con trigo
        for (int i = 0; i < length + expansion * 2; i++) {
            for (int j = 0; j < width + expansion * 2; j++) {
                newGrid[i][j] = '•';
            }
        }

        // Copiar la matriz original a la nueva matriz
        for (int i = 0; i < length; i++) {
            System.arraycopy(grid[i], 0, newGrid[i + expansion], expansion, width);
        }

        // Actualizar las posiciones de la hormiga
        startX += expansion;
        startY += expansion;

        // Actualizar la matriz original
        grid = newGrid;

        // Actualizar las dimensiones de la chacra
        width += expansion * 2;
        length += expansion * 2;
    }

    private int numFotosCapturadas() {
        return photosCaptured; // Devolver el valor actual del contador de fotos
        }

    public void printChacra() {
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
    }
}
