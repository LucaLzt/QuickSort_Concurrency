package test;

//Algoritmo QuickSort secuencial
public class QuickSort_Sequential {

	// Método para realizar QuickSort en el array
	public static void quickSort(int[] arr, int low, int high) {
		if(low < high) {
			// Particiona el array y obtiene el índice de pivote
			int pivotIndex = partition(arr, low, high);
				
			// Ordena recursivamente los elementos antes y después
			// de la partición
			quickSort(arr, low, pivotIndex - 1);
			quickSort(arr, pivotIndex + 1, high);
		}
	}
		
	// Método para la partición del array
	public static int partition(int[] arr, int low, int high) {
			
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
	public static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

}
