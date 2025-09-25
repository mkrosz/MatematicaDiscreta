import networkx as nx
import matplotlib.pyplot as plt

# --- DADOS E RESULTADOS (Grafo da Parte 3) ---

# MATRIZ DE ESTRUTURA (Necessária para NetworkX montar o grafo)
# O Python só a usará para *construir* o grafo visualmente.
matriz_adjacencia = [
    # 0  1  2  3  4  5  6  7 
    [0, 1, 1, 0, 0, 0, 0, 0],
    [1, 0, 2, 0, 1, 0, 0, 0],
    [1, 2, 0, 1, 0, 0, 0, 0],
    [0, 0, 1, 0, 0, 0, 0, 3],
    [0, 1, 0, 0, 0, 1, 0, 1],
    [0, 0, 0, 0, 1, 1, 1, 0],
    [0, 0, 0, 0, 0, 1, 0, 1],
    [0, 0, 0, 3, 1, 0, 1, 0]
]
num_vertices = len(matriz_adjacencia)


# RESULTADOS CALCULADOS PELO CÓDIGO JAVA (simulando a entrada)
# Estes valores DEVEM ser obtidos executando o código Java acima!
# (Aqui estão hardcoded apenas para a demonstração visual imediata.)
resultados_graus = {
    0: 3, 1: 5, 2: 5, 3: 5, 
    4: 4, 5: 4, 6: 3, 7: 5
}
total_lacos = 1        # Contagem: matriz[5][5] = 1
total_paralelas = 2    # Contagem: 1 em A(1,2) e 1 em A(3,7)
lacos_por_vertice = [0, 0, 0, 0, 0, 1, 0, 0] # Laços no V5

# ----------------------------------------------------
# FUNÇÃO DE DESENHO (APENAS VISUALIZAÇÃO)
# ----------------------------------------------------

def desenhar_grafo_visualizacao(matriz):
    """
    Cria e desenha o grafo usando dados pré-calculados.
    """
    # Usamos MultiGraph para desenhar arestas paralelas e laços
    G = nx.MultiGraph()
    G.add_nodes_from(range(num_vertices))
    
    # 1. Monta o Grafo
    for i in range(num_vertices):
        for j in range(i, num_vertices):
            peso = matriz[i][j]
            # Adiciona 'peso' número de arestas entre i e j
            for k in range(peso):
                G.add_edge(i, j)

    # 2. Configuração e Desenho
    plt.figure(figsize=(10, 8))
    pos = nx.spring_layout(G, seed=42)
    
    # Prepara os rótulos dos nós com o Grau (DADOS DO JAVA)
    grau_labels = {}
    for i in range(num_vertices):
        grau = resultados_graus[i]
        grau_labels[i] = f"{i} (g={grau})"
        
    # Cores dos Nós: VERMELHO se houver laço (DADOS DO JAVA)
    node_colors = ['red' if lacos_por_vertice[i] > 0 else 'skyblue' for i in range(num_vertices)]
    
    # Desenha o grafo
    nx.draw(G, pos, 
            with_labels=False,         # Não usamos o rótulo padrão
            node_color=node_colors,    
            node_size=800,
            edge_color='gray',
            width=1.5)
            
    # Adiciona os rótulos personalizados (com grau)
    nx.draw_labels(G, pos, labels=grau_labels, font_size=10, font_weight='bold')

    # 3. Inserir os Resultados da Análise como Texto (DADOS DO JAVA)
    info_text = (
        f"ANÁLISE (Cálculo via Java):\n"
        f"Total de Vértices: {num_vertices}\n"
        f"Total de Laços: {total_lacos}\n"
        f"Total de Arestas Paralelas (i!=j): {total_paralelas}\n"
        f"Nós em VERMELHO: possuem laços (Ex: V5)."
    )
    
    # Adiciona o texto de análise na parte inferior/esquerda da figura
    plt.figtext(0.02, 0.02, info_text, fontsize=10, verticalalignment="bottom")

    plt.title("Visualização do Grafo com Propriedades (Análise feita em Java)")
    plt.show()

# Roda a função principal
desenhar_grafo_visualizacao(matriz_adjacencia)