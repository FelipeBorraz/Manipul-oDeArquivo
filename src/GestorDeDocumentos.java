import java.io.*; //biblioteca necessaria para mecher com arquivos
import java.util.Scanner;

public class GestorDeDocumentos {
    
    private static final String ARQUIVO_DE_NOMES = "dados.txt"; //declara uma constante ARQUIVO_DE_NOMES que acessa o arquivo de texto

    public static void main(String[] args) {
        Scanner leitor = new Scanner(System.in);
        int escolha; //declara variavel onde será armazenada a escolha de opção do usuario
        
        //MENU
        do {
            System.out.println("\nSelecione uma operação:");
            System.out.println("1 - Adicionar nome ao arquivo");
            System.out.println("2 - Listar todos os nomes no arquivo");
            System.out.println("3 - Remover todo o conteúdo do arquivo");
            System.out.println("0 - Sair");
            
            escolha = leitor.nextInt(); //armazena a opção do usuario na variavel escolha
            leitor.nextLine(); //limpa oque o comando .nextInt não consegue armazenar
            
            switch (escolha) {
                case 1:
                    adicionarNome(leitor);
                    break;
                case 2:
                    listarNomes();
                    break;
                case 3:
                    limparArquivo();
                    break;
                case 0:
                    System.out.println("Finalizando o programa...");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        } while (escolha != 0); //enquanto o numero digitado pelo usuario for diferente de 0 o looping continuara sendo executado
        
        leitor.close();
    }
    
    private static void adicionarNome(Scanner leitor) { //solicita ao usuario um nome para ser adicionado ao arquivo
        System.out.print("Digite o nome que será registrado no arquivo: ");
        String nomeUsuario = leitor.nextLine();
        
        try (FileWriter escritor = new FileWriter(ARQUIVO_DE_NOMES, true); //abre o arquivo de texto
             BufferedWriter buffer = new BufferedWriter(escritor); //Melhora a performance de escrita no arquivo
             PrintWriter gravador = new PrintWriter(buffer)) { //Usado para escrever no arquivo de forma conveniente
            gravador.println(nomeUsuario); //adciona o nome no arquivo seguido de uma nova linha 
            System.out.println("Nome registrado com sucesso.");
        } catch (IOException erro) {
            System.out.println("Erro ao adicionar o nome: " + erro.getMessage());
        }
    }
    private static void listarNomes() {
        try (BufferedReader leitorDeArquivo = new BufferedReader(new FileReader(ARQUIVO_DE_NOMES))) { //le o arquivo linha por linha
            String linha;
            System.out.println("\nNomes registrados no arquivo:");
            while ((linha = leitorDeArquivo.readLine()) != null) { //para cada linha lida o comando imprime ele na tela do console
                System.out.println(linha);
            }
        } catch (IOException erro) {
            System.out.println("Erro ao ler o arquivo: " + erro.getMessage());
        }
    }
    private static void limparArquivo() {
        File arquivo = new File(ARQUIVO_DE_NOMES); //verifica se o arquivo existe, se existir ele o deleta em seguida. Caso contrario exibe 
        if (arquivo.exists()) { // uma mensagem de erro
            if (arquivo.delete()) {
                System.out.println("Arquivo limpo com sucesso.");
            } else {
                System.out.println("Falha ao limpar o arquivo.");
            }
        } else {
            System.out.println("O arquivo não foi encontrado.");
        }
    }
}
