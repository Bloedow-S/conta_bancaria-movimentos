import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    public static void main(String args[]){
        System.out.println("\n|Bancoline|");

        int saldo= 1518;
        String nome = "Antônia";
        String tipo_conta = "Conta Corrente";

        String dados_inicias = String.format("""
                \n  Seja bem vindo(a) de volta! %s!
                   
                [%s
                [Saldo: R$%d 
                """, nome, tipo_conta, saldo);

        String menu = """
                1. Visualizar saldo
                2. Transferir valor
                3. Receber valor
                4. Sair
                """;

        LocalDateTime data_hora_atual = LocalDateTime.now();
        DateTimeFormatter formato_ptBR = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        String data_hora_atual_ptBR = data_hora_atual.format(formato_ptBR);
        Scanner scanf = new Scanner(System.in);

        //imprime a "página" principal
        System.out.println(dados_inicias);
        System.out.println(menu);
        String decoracao = "-".repeat(30);

        while (true){
            int escolha_usuario = 0;

            System.out.printf("\n%s\nDigite uma opção: ", decoracao);
            try {
                escolha_usuario = scanf.nextInt();
            } catch (Exception e) {
                scanf.nextLine();
                System.out.println("(!) Entrada inválida");
                continue;
            }

            if (escolha_usuario < 1 || escolha_usuario > 4) {
                System.out.println("(!) Erro: Apenas valores de 1 à 4.");
                continue;
            }


            if (escolha_usuario == 1) {
                System.out.printf("\n%s\nSaldo\nR$%d  [%s]", decoracao, saldo, data_hora_atual_ptBR);
            }

            else if (escolha_usuario == 2) {
                System.out.printf("\n%s\nTransferir\n", decoracao);
                int qde_a_transferir = 0;

                //padrão de código que verifica se a entrada é válida
                while (true) {
                    System.out.println("Quanto deseja enviar? ");
                    try {
                        qde_a_transferir = scanf.nextInt();
                        break;
                    } catch (Exception e) {
                        scanf.nextLine();
                        System.out.println("(!) Entrada inválida");
                    }
                }

                if (qde_a_transferir > saldo) {
                    System.out.println("(!) Saldo insuficiente!");
                }

                saldo -= qde_a_transferir;
                String tela_transferencia_enviada = """
                        -%d
                        Saldo: R$%d  [%s]
                        """;
                System.out.printf(tela_transferencia_enviada, qde_a_transferir, saldo, data_hora_atual_ptBR);
            }

            else if (escolha_usuario == 3) {
                System.out.printf("\n%s\nReceber\n", decoracao);
                int qde_a_receber = 0;

                while (true) {
                    System.out.println("Quanto irá receber? ");
                    try {
                        qde_a_receber = scanf.nextInt();
                        break;
                    } catch (Exception e) {
                        scanf.nextLine();
                        System.out.println("(!) Entrada inválida");
                    }
                }

                saldo += qde_a_receber;
                String tela_transferencia_recebida = """
                        +%d
                        Saldo: R$%d  [%s]
                        """;

                System.out.printf(tela_transferencia_recebida, qde_a_receber, saldo, data_hora_atual_ptBR);
            }

            else {
                break;
            }
        }
    }
}
