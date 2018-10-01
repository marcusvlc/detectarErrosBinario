package paritydetect;

public class Hamming {
	
	
	public boolean detectErrorHamming(int[] binary) {
		
		for(int i = 0; i < binary.length; i++) {
			int parityNumber =  (int) Math.pow(2, i);
			
			if(parityNumber < binary.length) {
				int acum = 0;
				int comecarAcumular = parityNumber + 1;
				boolean devoAcumular = true;
				int qntAcumulada = 1;
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
	
					} else {
						qntAcumulada--;
					}
					
				
					
					comecarAcumular++;
					
				}
				
				
				 if(possuiErro(binary[parityNumber], acum)) {
					 return true;
				 }
					 
			}
			
			
		}
		
		
		return false;
	}
	
	
	public boolean possuiErro(int binarioDaParidade, int acumulador) {
		boolean retorno = true;
		
		if(binarioDaParidade == 0 && (acumulador % 2 == 0)) {
			retorno = false;
		} else if(binarioDaParidade == 1 & (acumulador % 2 == 1)) {
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
		int[] binario = {naoConsiderar,0,1,1,0,0,1,1};
		
		System.out.println(h.detectErrorHamming(binario));
	}

}
