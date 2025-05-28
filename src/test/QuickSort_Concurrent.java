package test;

//Algoritmo QuickSort concurrente
public class QuickSort_Concurrent extends Thread {
	private int[] arr;
	private int low, high, depth;
	private static final int MAX_DEPTH = 8; // --- VALOR AUXILIAR PARA NO GENERAR OVERTHREADING --- //
		
	public QuickSort_Concurrent(int[] arr, int low, int high, int depth) {
		super();
		this.arr = arr;
		this.low = low;
		this.high = high;
		this.depth = depth;
	}
	
	@Override
	public void run() {
		quickSort(arr, low, high, depth);
	}
	
	
	// Método para realizar QuickSort en el array
	public void quickSort(int[] arr, int low, int high, int depth) {
		
		// Verifica si hay elementos para ordenar en el subarray actual
		if(low < high) {
			// Particiona el array y obtiene la posición final del pivote
			int pivot = partition(arr, low, high);
			
			// Control para evitar crear demasiados hilos (overthreading)
			if(depth < MAX_DEPTH) {
				// Crea hilos para ordenar las mitades izquierda y derecha concurrentemente
				Thread left = new QuickSort_Concurrent(arr, low, pivot - 1, depth + 1);
				Thread right = new QuickSort_Concurrent(arr, pivot + 1, high, depth + 1);
				
				// Inicia los hilos
				left.start();
				right.start();
				
				// Espera que ambos hilos terminen
				try {
					left.join();
					right.join();
				} catch (InterruptedException e) {
					e.printStackTrace(); // --- Manejo básico de error si se interrumpen los hilos --- //
				}
			} else {
				// --- Si se alcanzó el límite máximo depth, ordena secuencialmente sin crear más hilos ---
				quickSort(arr, low, pivot - 1, depth);
				quickSort(arr, pivot + 1, high, depth);
			}
		}
	}
		
	// Método para la partición del array
	public int partition(int[] arr, int low, int high) {
			
		// Elijo el último elemento como pivote
		int pivot = arr[high];
			
		// Puntero para el elemento mayor
		int i = low - 1;
			
		// Recorro todos los elementos
		// Si el elemento es menor o igual al pivote, se cambia
		for (int j = low; j <= high - 1; j++) {
			if(arr[j] < pivot) {
				i++;
				// Intercambio elementos en i y j
				swap(arr, i, j);
			}
		}
			
		// Cambio el elemento pivote con el elemento en i + j
		swap(arr, i + 1, high);
			
		// Devuelvo el índice de partición
		return i + 1;
			
	}
		
	// Método de intercambio
	public void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
		
}
