public class Grafo {

    // Matriz de adjacência fornecida na Parte 3
    private int[][] matrizAdjacencia = {
        // 0  1  2  3  4  5  6  7
        { 0, 1, 1, 0, 0, 0, 0, 0 }, // 0
        { 1, 0, 2, 0, 1, 0, 0, 0 }, // 1
        { 1, 2, 0, 1, 0, 0, 0, 0 }, // 2
        { 0, 0, 1, 0, 0, 0, 0, 3 }, // 3
        { 0, 1, 0, 0, 0, 1, 0, 1 }, // 4
        { 0, 0, 0, 0, 1, 1, 1, 0 }, // 5
        { 0, 0, 0, 0, 0, 1, 0, 1 }, // 6
        { 0, 0, 0, 3, 1, 0, 1, 0 }  // 7
    };
    
    private final int NUM_VERTICES = matrizAdjacencia.length;


    // Implementação Requerida 1: Determinar o Grau de um Vértice
    /**
     * Retorna o grau de um vértice em um grafo não direcionado.
     * O grau é a soma dos valores da linha correspondente,
     * onde laços (diagonal principal) contribuem com 2.
     * @param vertice O índice do vértice (0 a 7 neste caso).
     * @return O grau do vértice.
     */
    public int getGrau(int vertice) {
        if (vertice < 0 || vertice >= NUM_VERTICES) {
            System.out.println("Erro: Vértice inválido.");
            return -1; // Indica erro
        }

        int grau = 0;
        // O grau é a soma dos valores da linha correspondente.
        for (int peso : matrizAdjacencia[vertice]) {
            grau += peso;
        }

        // Se houver um laço, ele já foi contado UMA vez na soma acima, mas deve contribuir com 2.
        // Portanto, somamos o valor do laço novamente.
        // Variável 'laço' alterada para 'laco' para evitar erro de codificação.
        int laco = matrizAdjacencia[vertice][vertice];
        grau += laco;
        
        return grau;
    }


    // Implementação Requerida 2: Contar a Quantidade de Laços
    /**
     * Retorna o número total de laços no grafo.
     * Laços são representados por valores na diagonal principal (matriz[i][i]).
     * @return O número total de laços.
     */
    public int getQuantidadeLacos() {
        int totalLacos = 0;
        // Percorre a diagonal principal
        for (int i = 0; i < NUM_VERTICES; i++) {
            totalLacos += matrizAdjacencia[i][i];
        }
        return totalLacos;
    }


    // Implementação Requerida 3: Contar a Quantidade de Arestas Paralelas
    /**
     * Retorna o número total de arestas paralelas no grafo.
     * Arestas paralelas ocorrem quando matriz[i][j] (i != j) > 1.
     * Cada valor n > 1 representa n-1 arestas paralelas.
     * Percorre-se apenas o triângulo superior (i < j) para não contar duas vezes.
     * @return O número total de arestas paralelas.
     */
    public int getQuantidadeArestasParalelas() {
        int totalArestasParalelas = 0;
        
        // Percorre apenas o triângulo superior da matriz (i < j)
        for (int i = 0; i < NUM_VERTICES; i++) {
            for (int j = i + 1; j < NUM_VERTICES; j++) {
                int peso = matrizAdjacencia[i][j];
                
                // Se o peso for maior que 1, existem (peso - 1) arestas paralelas
                if (peso > 1) {
                    totalArestasParalelas += (peso - 1);
                }
            }
        }
        return totalArestasParalelas;
    }
    
    // Método principal para demonstrar o uso
    public static void main(String[] args) {
        Grafo grafo = new Grafo();
        
        System.out.println("--- Análise do Grafo (Matriz da Parte 3) ---");
        
        // a) Determinar o Grau de um Vértice
        System.out.println("\n== a) Grau de Cada Vértice ==");
        for (int v = 0; v < grafo.NUM_VERTICES; v++) {
            System.out.println("Grau do vértice " + v + ": " + grafo.getGrau(v));
        }

        // b) Contar a Quantidade de Laços
        System.out.println("\n== b) Quantidade Total de Laços ==");
        int lacos = grafo.getQuantidadeLacos();
        System.out.println("Total de laços no grafo: " + lacos);
        
        // c) Arestas Paralelas (Extra: Identificação)
        System.out.println("\n== c) Arestas Paralelas ==");
        // Vamos replicar a identificação feita na resposta para ilustrar
        for (int i = 0; i < grafo.NUM_VERTICES; i++) {
            for (int j = i + 1; j < grafo.NUM_VERTICES; j++) {
                if (grafo.matrizAdjacencia[i][j] > 1) {
                    System.out.println("Existem " + grafo.matrizAdjacencia[i][j] + " arestas entre " + i + " e " + j + 
                                       ", sendo " + (grafo.matrizAdjacencia[i][j] - 1) + " arestas paralelas.");
                }
            }
        }
        
        // Contagem Total de Arestas Paralelas
        int arestasParalelas = grafo.getQuantidadeArestasParalelas();
        System.out.println("Total de arestas paralelas (contadas uma vez): " + arestasParalelas);

        // d) Quantidade Total de Arestas (EXTRA - Demonstração)
        System.out.println("\n== d) Quantidade Total de Arestas ==");
        
        // Soma do Triângulo Superior (arestas não-laço)
        int arestasNaoLaco = 0;
        for (int i = 0; i < grafo.NUM_VERTICES; i++) {
            for (int j = i + 1; j < grafo.NUM_VERTICES; j++) {
                arestasNaoLaco += grafo.matrizAdjacencia[i][j];
            }
        }
        
        int totalArestas = arestasNaoLaco + lacos;
        System.out.println("Soma das arestas (Triângulo Superior): " + arestasNaoLaco);
        System.out.println("Soma dos laços: " + lacos);
        System.out.println("Total de arestas no grafo: " + totalArestas);
    }
}