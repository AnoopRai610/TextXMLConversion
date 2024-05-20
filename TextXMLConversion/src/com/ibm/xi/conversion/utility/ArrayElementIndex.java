package com.ibm.xi.conversion.utility;


import java.util.Arrays;

/**
 * <b>Use to get index of an array element.</b><br>
 * Methods will returns the index of the first occurrence of 
 * the specified element in this Array, or -1 if this array does 
 * not contain the element.
 * 
 * @author IBM GBD India
 * @version 1.0
*/
public class ArrayElementIndex {
	
	/**
	 * @param array		Array of Object
	 * @param element	Search element
	 * @return index of the first occurrence of the specified element
	 * @since 1.0
	*/
	public int  findIndex (Object[] array, Object element) {
		return Arrays.asList(array).indexOf(element);
    }
	
	/**
	 * @param array		Array of String
	 * @param element	Search element
	 * @return index of the first occurrence of the specified element
	 * @since 1.0
	*/
	public int  findIndex (String[] array, String element) {
		for (int i = 0; i < array.length; i++)
			if (array[i].equals(element))
				return i;
		return -1;
    }
	
	/**
	 * @param array		Array of int (primitive)
	 * @param element	Search element
	 * @return index of the first occurrence of the specified element
	 * @since 1.0
	*/
	public int  findIndex (int[] array, int element) {
		for (int i = 0; i < array.length; i++)
			if (array[i]==element)
				return i;
		return -1;
    }
	
	/**
	 * @param array		Array of float (primitive)
	 * @param element	Search element
	 * @return index of the first occurrence of the specified element
	 * @since 1.0
	*/
	public int  findIndex (float[] array, float element) {
		for (int i = 0; i < array.length; i++)
			if (array[i]==element)
				return i;
		return -1;		
    }
	
	/**
	 * @param array		Array of double (primitive)
	 * @param element	Search element
	 * @return index of the first occurrence of the specified element
	 * @since 1.0
	*/
	public int  findIndex (double[] array, double element) {
		for (int i = 0; i < array.length; i++)
			if (array[i]==element)
				return i;
		return -1;		
    }
	
	/**
	 * @param array		Array of long (primitive)
	 * @param element	Search element
	 * @return index of the first occurrence of the specified element
	 * @since 1.0
	*/
	public int  findIndex (long[] array, long element) {
		for (int i = 0; i < array.length; i++)
			if (array[i]==element)
				return i;
		return -1;		
    }
	
	/**
	 * @param array		Array of char (primitive)
	 * @param element	Search element
	 * @return index of the first occurrence of the specified element
	 * @since 1.0
	*/
	public int  findIndex (char[] array, char element) {
		for (int i = 0; i < array.length; i++)
			if (array[i]==element)
				return i;
		return -1;		
    }
	
	

}
