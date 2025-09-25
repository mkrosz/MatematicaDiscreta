public class GrafoAnalise {

    private int[][] matrizAdjacencia;
    private int numVertices;

    public GrafoAnalise(int[][] matrizAdjacencia) {
        this.matrizAdjacencia = matrizAdjacencia;
        this.numVertices = matrizAdjacencia.length;
    }

    /**
     * Calcula o grau de um vértice específico.
     * Laços (matriz[v][v]) contribuem com 2 para o grau.
     */
    public int getGrau(int vertice) {
        int grau = 0;
        // 1. Soma de todos os valores na linha (conta laço 1 vez)
        for (int j = 0; j < numVertices; j++) {
            grau += matrizAdjacencia[vertice][j];
        }
        // 2. Adiciona a contribuição extra dos laços (conta o laço a segunda vez)
        grau += matrizAdjacencia[vertice][vertice]; 
        return grau;
    }

    /**
     * Retorna a quantidade total de laços (soma da diagonal).
     */
    public int getQuantidadeLacos() {
        int totalLacos = 0;
        for (int i = 0; i < numVertices; i++) {
            totalLacos += matrizAdjacencia[i][i];
        }
        return totalLacos;
    }

    /**
     * Retorna a quantidade total de arestas paralelas (n-1 se n > 1) para i != j.
     */
    public int getQuantidadeArestasParalelas() {
        int arestasParalelas = 0;
        for (int i = 0; i < numVertices; i++) {
            for (int j = i + 1; j < numVertices; j++) {
                int numArestas = matrizAdjacencia[i][j];
                if (numArestas > 1) {
                    arestasParalelas += (numArestas - 1);
                }
            }
        }
        return arestasParalelas;
    }

    public static void main(String[] args) {
        // Matriz do Exercício - Parte 3 (8 vértices: 0 a 7)
        int[][] matrizExemplo = {
            { 0, 1, 1, 0, 0, 0, 0, 0 }, 
            { 1, 0, 2, 0, 1, 0, 0, 0 }, 
            { 1, 2, 0, 1, 0, 0, 0, 0 }, 
            { 0, 0, 1, 0, 0, 0, 0, 3 }, 
            { 0, 1, 0, 0, 0, 1, 0, 1 }, 
            { 0, 0, 0, 0, 1, 1, 1, 0 }, // Laço aqui (1)
            { 0, 0, 0, 0, 0, 1, 0, 1 }, 
            { 0, 0, 0, 3, 1, 0, 1, 0 }  // Paralelas aqui (3)
        };

        GrafoAnalise grafo = new GrafoAnalise(matrizExemplo);

        // Imprime os graus no console
        System.out.println("--- GRAUS ---");
        for (int i = 0; i < grafo.numVertices; i++) {
            // Imprime o grau no formato "V<indice>: <grau>" para facilitar o parse
            System.out.println("V" + i + ": " + grafo.getGrau(i));
        }

        // Imprime as estatísticas gerais
        System.out.println("--- ESTATISTICAS ---");
        System.out.println("LACOS: " + grafo.getQuantidadeLacos());
        System.out.println("PARALELAS: " + grafo.getQuantidadeArestasParalelas());
        
        // Laços por Vértice (para a cor na visualização)
        System.out.println("--- LACOS_POR_VERTICE ---");
        for (int i = 0; i < grafo.numVertices; i++) {
             // Imprime o número de laços no formato "L<indice>: <quantidade>"
            System.out.println("L" + i + ": " + matrizExemplo[i][i]);
        }
    }
}