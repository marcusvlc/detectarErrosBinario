package errordetect;


public class Paridade {
	/**
	 * 
	 * @param binary
	 *  Uma string que representa um numero binario
	 * @param typeOfParity
	 * Uma string que representa o tipo de paridade utilizado
	 * @return
	 * Retorna verdadeiro caso haja algum erro de paridade no binario, falso caso contrario
	 * @throws Exception
	 * Lanca um erro caso o binario nao esteja correto ou o tipo de paridade nao seja impar ou par
	 */
	public boolean detectarErroParidade(String binario, String tipoDeParidade) throws Exception {
		boolean possuiErro = false;
		int contaUm = 0;
		
		for(int i = 0; i < binario.length(); i++) {
			if(binario.charAt(i) != '1' && binario.charAt(i) != '0') {
				throw new Exception("A string binaria deve conter apenas 0 e 1");
			}
			
			
			if(binario.charAt(i) == '1') {
				contaUm++;
			}
		}
		
		if(tipoDeParidade.equals("par")) {
			if(contaUm % 2 == 1) {
				possuiErro = true;
			}
			
		} else if(tipoDeParidade.equals("impar")) {
			if(contaUm % 2 == 0) {
				possuiErro = true;
			}
			
		} else {
			throw new Exception("Tipo de paridade incorreto");
		}
		
		return possuiErro;
	}
	
	public static void main(String[] args) throws Exception {
		Paridade p = new Paridade();
		System.out.println(p.detectarErroParidade("001", "impar"));
	}

}
