package errordetect;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Hamming {
	
   /**
    * Verifica se têm bits de pariade errados num array e, se sim, quais. E imprime o binário correto, se possível.
    */
	public boolean detectaErroHamming(int[] binary) {
		ArrayList<Integer> paridadesErradas = new ArrayList<Integer>();
		
		boolean temErro = false;
			
		for(int i = 0; i < binary.length; i++) {
			int parityNumber =  (int) Math.pow(2, i);
			ArrayList<Integer> indicesTemporarios = new ArrayList<Integer>();
			if(parityNumber < binary.length) {
				
				int acum = 0;
				int comecarAcumular = parityNumber + 1;
				boolean devoAcumular = true;
				int qntAcumulada = 1;
				
				indicesTemporarios.add(parityNumber);
				
				while(comecarAcumular < binary.length) {
					if(qntAcumulada == parityNumber) {
						devoAcumular = false;
					}
					
					if(qntAcumulada == 0) {
						devoAcumular = true;
					}
					
					if(devoAcumular) {
						acum += binary[comecarAcumular];
						qntAcumulada++;
						indicesTemporarios.add(comecarAcumular);
	
					} else {
						qntAcumulada--;
					}
	
					comecarAcumular++;
					
				}
				 if(possuiErro(binary[parityNumber], acum)) {
					 temErro =  true;
					 paridadesErradas.add(parityNumber);			 
				 }		 
			}
		}
		
		if(temErro) 
			corrige(paridadesErradas, binary);
		 else 
			System.out.println("Bloco correto");
		
		return temErro;
	}
	
	/**
	 * Recebe um conjunto de bits de paridade que estão errados em um array de bits codificados em código de hamming.
	 * Se for possível corrigir um bit, ele é invertido. Se o número de erros for maior, nada pode ser feito.
	 */
	private void corrige(ArrayList<Integer> paridadesErradas, int[] binary) {
		if (paridadesErradas.size()  % 2 == 0) {
			int acum = 0;
			
			for(int i = 0 ; i < paridadesErradas.size();i++) {
				acum+= paridadesErradas.get(i);
			}
			
			if(acum < binary.length) {
				if(binary[acum] == 0) 
					binary[acum] = 1;
				 else 
					binary[acum] = 0;
			}
			
			System.out.println("O binario corrigido (com as paridades) eh: " + Arrays.toString(binary));
						
		} else {
			System.out.println("Nao eh possivel corrigir, numero de paridades erradas eh impar");
		}
	}

	/**
	 Verifica se um número está contido num ArrayList
	 */
	public boolean estaContido(int numero, ArrayList<Integer> lista) {
		for(int i = 0; i < lista.size(); i++) {
			if(lista.get(i) == numero) 
				return true;
		}
		return false;
	}

	public boolean possuiErro(int binarioDaParidade, int acumulador) {
		boolean retorno = true;
		
		if(binarioDaParidade == 0 && (acumulador % 2 == 0)) {
			retorno = false;
		} else if(binarioDaParidade == 1 && (acumulador % 2 == 1)) {
			retorno = false;
		} else {
			retorno = true;
		}
		
		return retorno;	
	}

	
	public static void main(String[] args) {
		Hamming h = new Hamming();
		// Para a implementacao detectar corretamente algum erro, 
		//favor desconsiderar a posicao 0 do array
		
		// Considerar o binario passado ja no padrao de hamming
		int naoConsiderar = 0;

		int[] binario = {naoConsiderar,0,1,1,1,1,0,0,1,1,1,1,1};
		h.detectaErroHamming(binario);
		int[] binario1 = {naoConsiderar,0,1,0,0,1,1,1};
		h.detectaErroHamming(binario1);
		int[] binario2 = {naoConsiderar,0};
		h.detectaErroHamming(binario2);
	}

}
