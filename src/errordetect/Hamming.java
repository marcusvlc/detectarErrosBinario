package errordetect;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Hamming {
	
    public static List<Integer> range(int min, int max) {
        List<Integer> list = new ArrayList();
        for (int i = min; i <= max; i++) {
            list.add(i);
        }

        return list;
    }
    
	public boolean detectErrorHamming(int[] binary) {
		
		boolean temErro = false;
		ArrayList<Integer> indicesErrados = new ArrayList<Integer>();
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
					 // PASSAR OS TEMPORARIOS PRO ERRADO
					 indicesErrados.addAll(indicesTemporarios);
					 
				 }		 
			}
		}
		
		System.out.println(getUniq(indicesErrados,range(1, binary.length)));
		System.out.println(indicesErrados);	
		
		return temErro;
	}
	
	
	private ArrayList<Integer> getUniq(ArrayList<Integer> indicesErrados, List<Integer> range) {
		ArrayList<Integer> repeated = new ArrayList<Integer>();
		for (int i = 0; i < indicesErrados.size(); i++) {
			int atual = indicesErrados.get(i);
			
			for (int j = i+1; j < indicesErrados.size(); j++) {
				if (atual == indicesErrados.get(j)) {
					repeated.add(indicesErrados.get(j));
				}
			}
		}
		return repeated;
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
		
		System.out.println(acumulador);
		return retorno;	
	}
	
	
	public static void main(String[] args) {
		Hamming h = new Hamming();
		// Para a implementacao detectar corretamente algum erro, 
		//favor desconsiderar a posicao 0 do array
		
		// Considerar o binario passado ja no padrao de hamming
		int naoConsiderar = 0;
		int[] binario = {naoConsiderar,0,1,1,1,1,0,0,1,1,1,1,1};
		
		System.out.println(h.detectErrorHamming(binario));
	}

}
