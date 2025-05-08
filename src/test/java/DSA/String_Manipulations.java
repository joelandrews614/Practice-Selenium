package DSA;

public class String_Manipulations {

	public static void main(String[] args) {
		
		System.out.println(reverse_a_string("Joel Andrews"));
		System.out.println(isPalindrome("madam"));
		
	}
	
	public static String reverse_a_string(String word) {
		
		String reversed = "";
		
		for(int i = word.length() - 1; i >= 0; i--) {
			reversed += word.charAt(i);
		}
		
		return reversed;
	
	}

	public static Boolean isPalindrome(String word) {
		
		String rev = reverse_a_string(word);
		
		return rev.equalsIgnoreCase(word);
		
	}
	
}