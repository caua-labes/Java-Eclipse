package StartP;
import java.util.ArrayList;
import java.util.Scanner;

public class Program {

	public static void main(String[] args) {
		boolean loop = false;
		ArrayList<String> senhasSalvas = new ArrayList();
		//String[] senhasSalvas = new String[10];
		while(loop == false) {			
			System.out.println("\n1 - Criar Senha\n2 - Ver Senhas\n3 - Alterar Senha\n4 - Deletar Senha");
			Scanner rd = new Scanner (System.in);
			int escolha = rd.nextInt();
			switch(escolha) {
			case 1:
				/*int posicao = 0;
				while(posicao <= senhasSalvas.length && senhasSalvas[posicao] != null) {
					posicao++;
				}*/
				System.out.print("Senha: ");
				String senha = rd.next();
				//senhasSalvas[posicao] = senha;
				senhasSalvas.add(senha);
				System.out.println("Senha salva com sucesso");
				break;
				
			case 2:
				int i = 0;
				/*for(int i = 0; i < senhasSalvas.length; i++) {
					if(senhasSalvas[i] != null) {
						System.out.print(i + 1 + " ");
						System.out.println(senhasSalvas[i]);
					}
					else {
						System.out.print(i + 1 + " ");
						System.out.println("Espaço vazio");
					}
				}*/
				for (String senhas : senhasSalvas) {
					System.out.println(i + " " +senhas);
					i++;
				}
				break;
				
			case 3:
				System.out.println("Qual posição do array você deseja alterar: ");
				int pos = rd.nextInt();
				if(pos > senhasSalvas.size()) {
					System.out.println("Erro na posição do array");
					break;
				}
				else {
					System.out.println("A senha a ser alterada é " + senhasSalvas.get(pos));
				}
				
				System.out.println("Escreva a nova senha: ");
				String senhaNova = rd.next();
				senhasSalvas.set(pos, senhaNova);
				break;
				
			case 4:
				System.out.print("Qual posição do array você deseja deletar: ");
				int p = rd.nextInt();
				if(p > senhasSalvas.size()) {
					System.out.println("Erro na posição do array");
					break;
				}
				else {
					System.out.println("A senha deletada foi " + senhasSalvas.get(p));
					senhasSalvas.remove(p);
				}
				break;
				
			default:
				System.out.println("Erro, numero estranho");
			}
		}

	}
}
	