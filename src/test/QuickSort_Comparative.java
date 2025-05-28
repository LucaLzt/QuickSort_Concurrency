package test;

import java.util.Random;

public class QuickSort_Comparative {
	
	public static void main(String[] args) {
		
		int size = 10000000;
		int[] originalArr = generateRandomArray(size);
		
		int[] arr1 = originalArr.clone();
		int[] arr2 = originalArr.clone();
		
		// QuickSort secuencial
		long startSequential = System.currentTimeMillis();
		QuickSort_Sequential.quickSort(arr1, 0, arr1.length - 1);
		long endSequential = System.currentTimeMillis();
		System.out.println("Tiempo QuickSort Secuencial: " + (endSequential - startSequential) + "ms");
		
		// QuickSort concurrente
		QuickSort_Concurrent arrConcurrent = new QuickSort_Concurrent(arr2, 0, arr2.length - 1, 0);
		long startConcurrent = System.currentTimeMillis();
		arrConcurrent.start();
		try {
			arrConcurrent.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		long endConcurrent = System.currentTimeMillis();
		System.out.println("Tiempo QuickSort Concurrente: " + (endConcurrent - startConcurrent) + "ms");
		
	
	}
	
	// -- MÉTODO AUXILIAR PARA GENERAR VALORES ALEATORIOS DENTRO DEL ARRAY --- // 
	public static int[] generateRandomArray(int size) {
		Random rand = new Random();
		int[] arr = new int[size];
		for(int i = 0; i < size; i++) {
			arr[i] = rand.nextInt(size);
		}
		return arr;
	}
	
}
