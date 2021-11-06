package methodsCliente;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ModScanner {
	private static Pattern pattern = Pattern.compile("[a-zA-Z�-��-�\\s]{0,50}");
	private static Matcher matcher = pattern.matcher("");
	private static Scanner scan = new Scanner(System.in);
	public static final String volta = "0";

	{
		scan.useDelimiter("\r\n");
	}

	public String scanNome() {
		while (true) {
			String nome = scan.next();
			if (nome.matches("\\s{0,}([A-Z�-�][a-z�-�]+\\s{0,1})+")) {
				nome = nome.trim();
				return nome;
			}
			if(nome.equals(volta)) {
				throw new IllegalArgumentException();
			}
			System.out.println("\nNome Inv�lido, Tente Novamente...");
		}
	}

	public String scanCpf() {
		while (true) {
			String cpf = scan.next();

			if (cpf.matches("\\d{11}")) {
				return cpf;
			}
			if (cpf.matches("\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}")) {
				cpf = cpf.replace(".", "").replace("-", "");
				return cpf;
			}
			if(cpf.equals(volta)) {
				throw new IllegalArgumentException();
			}

			System.out.println("\nCPF Inv�lido, Tente Novamente...");
		}
	}

	public static String scanData() {
		while (true) {
			String data = scan.next();

			if (data.matches("\\d{2}/\\d{2}/\\d{4}")) {
				return data;
			}
			if(data.equals(volta)) {
				throw new IllegalArgumentException();
			}
			System.out.println("\nData Invalida, Tente Novamente...");
		}
	}

	public String scanTelefone() {
		while (true) {
			String telefone = scan.next();
			if (telefone.matches("\\d{11}")) { // (74)9884-21783
				return telefone;
			}
			if(telefone.equals(volta)) {
				throw new IllegalArgumentException();
			}
			System.out.println("\nTelefone invalido, Tente Novamente...");
		}
	}

	public String scanCep() {
		while (true) {
			String cep = scan.next();
			if (cep.matches("[0-9]{8}")) {
				return cep;
			}
			if(cep.equals(volta)) {
				throw new IllegalArgumentException();
			}
			System.out.println("\nCEP Inv�lido, Tente Novamente...");
		}
	}

	public String scanBairro() {
		while (true) {
			String bairro = scan.next();
			if (matcher.reset(bairro).matches()) {
				return bairro;
			}
			if(bairro.equals(volta)) {
				throw new IllegalArgumentException();
			}
			System.out.println("\nBairro Inv�lido, Tente Novamente...");
		}
	}

	public String scanLogradouro() {
		while (true) {
			String logradouro = scan.next();
			if (matcher.reset(logradouro).matches()) {
				return logradouro;
			}
			if(logradouro.equals(volta)) {
				throw new IllegalArgumentException();
			}
			System.out.println("\nLogradouro Inv�lido, Tente Novamente...");
		}
	}

	public String scanNumero() {
		while (true) {
			String numero = scan.next();
			if (numero.matches("[a-zA-Z0-9]{1,7}")) {
				return numero;
			}
			if(numero.equals(volta)) {
				throw new IllegalArgumentException();
			}
			System.out.println("\nnumero Inv�lido, Tente Novamente...");
		}
	}
}
