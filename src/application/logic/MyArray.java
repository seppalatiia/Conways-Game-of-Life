package application.logic;

public class MyArray {
	
	/**
	 * Reverse given array
	 * @param array
	 * @return	reversed array
	 */
	public static int[] reverse(int[] array) {
		
		int[] newArray = new int[array.length];
		
		for (int i = 0; i < array.length; i++) {
			newArray[i] = array[array.length-1-i];
		}
		
		return newArray;
	}
	
	/**
	 * Count the amount of active handles in given array
	 * @param handles
	 * @return	amount of active handles in array
	 */
	public static int countActiveHandles(int[] handles) {
		int count = 0;
		
		for (int i = 0; i < handles.length; i++) {
			if (handles[i] == 0) {
				count++;
			}
		}
		return count;
	}
}
