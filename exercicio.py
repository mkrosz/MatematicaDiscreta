def potencia_recursiva(base, expoente):
    """
    Calcula a potência de um número base elevado a um expoente usando recursão.

    Args:
        base (int/float): O número base.
        expoente (int): O expoente (deve ser um número inteiro não negativo
                        para esta implementação simples).

    Returns:
        int/float: O resultado da base elevada ao expoente.
    """
    # 1. Caso Base: Se o expoente for 0, o resultado é 1 (b^0 = 1)
    if expoente == 0:
        return 1

    # 2. Passo Recursivo: b^e = b * b^(e-1)
    return base * potencia_recursiva(base, expoente - 1)

# --- Solicitação de Números ao Usuário ---

# Solicita a base e converte o texto para número inteiro (int)
try:
    base_usuario = int(input("Digite a base (o número que será multiplicado): "))
    expoente_usuario = int(input("Digite o expoente (o número de vezes): "))

    # Verifica se o expoente é negativo, já que esta função lida apenas com positivos/zero
    if expoente_usuario < 0:
        print("\nEsta função recursiva simples só aceita expoentes não negativos (0, 1, 2, ...).")
    else:
        # Chama a função com os números fornecidos
        resultado = potencia_recursiva(base_usuario, expoente_usuario)

        # Exibe o resultado
        print(f"\nO cálculo é {base_usuario} elevado a {expoente_usuario}.")
        print(f"Resultado: {resultado}")

except ValueError:
    print("\nEntrada inválida. Por favor, digite apenas números inteiros.")