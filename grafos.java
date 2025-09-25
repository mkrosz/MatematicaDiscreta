public class GrafoAnalise {

    // A matriz de adjacência do grafo a ser analisado
    private int[][] matrizAdjacencia;
    // O número de vértices no grafo
    private int numVertices;

    /**
     * Construtor que inicializa a classe com a matriz de adjacência.
     * @param matrizAdjacencia A matriz quadrada que representa o grafo.
     */
    public GrafoAnalise(int[][] matrizAdjacencia) {
        this.matrizAdjacencia = matrizAdjacencia;
        // Assume que a matriz é quadrada e o número de vértices é o tamanho da dimensão.
        this.numVertices = matrizAdjacencia.length;
    }

    /**
     * Determina o grau de um vértice específico.
     * O grau é a soma dos valores na linha (ou coluna) do vértice.
     * Laços (matriz[v][v]) contribuem com 2 para o grau. [cite: 22, 23]
     * @param vertice O índice do vértice (0 a numVertices - 1).
     * @return O grau do vértice.
     */
    public int getGrau(int vertice) {
        // Verifica se o vértice é válido
        if (vertice < 0 || vertice >= numVertices) {
            throw new IllegalArgumentException("Vértice inválido. O índice deve estar entre 0 e " + (numVertices - 1) + ".");
        }

        int grau = 0;
        
        // O grau é a soma de todos os valores na linha do vértice.
        for (int j = 0; j < numVertices; j++) {
            grau += matrizAdjacencia[vertice][j];
        }

        /* * Na soma simples (acima), um laço (matriz[v][v]) conta apenas 1 vez.
         * Pela definição (e dica do exercício), um laço contribui com 2 para o grau.
         * Como o laço já foi contado 1 vez na soma, adicionamos 1 extra aqui.
         * Se o valor na diagonal for N, significa N laços. A soma já contou N.
         * Precisamos adicionar N para que cada laço conte 2 * N.
         * Por exemplo, se matriz[v][v] = 1 (1 laço), soma é 1. Adicionamos 1. Grau = 2.
         * Se matriz[v][v] = 2 (2 laços), soma é 2. Adicionamos 2. Grau = 4.
         */
        grau += matrizAdjacencia[vertice][vertice]; // Adiciona a contribuição extra do laço.

        return grau;
    }

    /**
     * Retorna o número total de laços no grafo.
     * Laços são representados por valores na diagonal principal (matriz[i][i]). [cite: 26]
     * @return A quantidade total de laços (soma dos valores na diagonal).
     */
    public int getQuantidadeLacos() {
        int totalLacos = 0;
        
        // Percorre a diagonal principal da matriz
        for (int i = 0; i < numVertices; i++) {
            totalLacos += matrizAdjacencia[i][i]; // Soma o número de laços em cada vértice
        }
        
        return totalLacos;
    }

    /**
     * Retorna o número total de arestas paralelas no grafo.
     * Arestas paralelas ocorrem quando matriz[i][j] (para i != j) é maior que 1.
     * Cada valor n > 1 representa (n - 1) arestas paralelas entre i e j. [cite: 29]
     * Para não contar duas vezes, percorremos apenas o triângulo superior (i < j). [cite: 30]
     * @return A quantidade total de arestas paralelas.
     */
    public int getQuantidadeArestasParalelas() {
        int arestasParalelas = 0;
        
        // Percorre apenas o triângulo superior da matriz (i < j)
        for (int i = 0; i < numVertices; i++) {
            for (int j = i + 1; j < numVertices; j++) {
                // Consideramos apenas arestas entre vértices diferentes (i != j).
                int numArestas = matrizAdjacencia[i][j];
                
                // Se o valor for > 1, há arestas paralelas.
                // Se houver N arestas, (N-1) delas são paralelas à primeira.
                if (numArestas > 1) {
                    arestasParalelas += (numArestas - 1);
                }
            }
        }
        
        return arestasParalelas;
    }
    
    // --- Método Main para Teste ---
    public static void main(String[] args) {
        // Matriz de exemplo fornecida no exercício (Parte 3) 
        int[][] matrizExemplo = {
            // Vértices: 0  1  2  3  4  5  6  7
            /* 0 */ { 0, 1, 1, 0, 0, 0, 0, 0 },
            /* 1 */ { 1, 0, 2, 0, 1, 0, 0, 0 },
            /* 2 */ { 1, 2, 0, 1, 0, 0, 0, 0 },
            /* 3 */ { 0, 0, 1, 0, 0, 0, 0, 3 },
            /* 4 */ { 0, 1, 0, 0, 0, 1, 0, 1 },
            /* 5 */ { 0, 0, 0, 0, 1, 1, 1, 0 },
            /* 6 */ { 0, 0, 0, 0, 0, 1, 0, 1 },
            /* 7 */ { 0, 0, 0, 3, 1, 0, 1, 0 }
        };

        GrafoAnalise grafo = new GrafoAnalise(matrizExemplo);

        System.out.println("--- Análise do Grafo (Exemplo do Exercício) ---");
        System.out.println("Matriz de Adjacência: " + grafo.numVertices + "x" + grafo.numVertices);

        // a) Qual é o grau de cada vértice? [cite: 13]
        System.out.println("\n--- Grau de Cada Vértice ---");
        for (int i = 0; i < grafo.numVertices; i++) {
            System.out.println("Grau do Vértice " + i + ": " + grafo.getGrau(i));
        }

        // b) Quantos laços o grafo possui? [cite: 14]
        int lacos = grafo.getQuantidadeLacos();
        System.out.println("\nQuantidade Total de Laços (loops): " + lacos);

        // c) Existem arestas paralelas? Se sim, entre quais vértices? [cite: 15]
        int paralelas = grafo.getQuantidadeArestasParalelas();
        System.out.println("Quantidade Total de Arestas Paralelas: " + paralelas);
        
        // Exemplo: identificar onde estão as arestas paralelas (extra)
        System.out.println("Arestas paralelas ocorrem entre:");
        for (int i = 0; i < grafo.numVertices; i++) {
            for (int j = i + 1; j < grafo.numVertices; j++) {
                if (grafo.matrizAdjacencia[i][j] > 1) {
                    System.out.println("  - Vértice " + i + " e Vértice " + j + " (Total de arestas: " + grafo.matrizAdjacencia[i][j] + ")");
                }
            }
        }

        // d) Quantas arestas totais o grafo possui? [cite: 16]
        // Fórmula para grafo não direcionado: (Soma de todos os graus + Soma dos Laços) / 2
        // OU: (Soma do triângulo superior + Soma da diagonal)
        int totalArestas = 0;
        for (int i = 0; i < grafo.numVertices; i++) {
            for (int j = i; j < grafo.numVertices; j++) {
                totalArestas += grafo.matrizAdjacencia[i][j];
            }
        }
        System.out.println("\nQuantidade Total de Arestas: " + totalArestas);
        
    }
}