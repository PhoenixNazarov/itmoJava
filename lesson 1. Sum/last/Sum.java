public class Sum {

	public static int[] addString(int[] ints, String value){
		int newValue = Integer.parseInt(value);

		for (int i = 0; i < ints.length; i++){
			if (ints[i] == 0){
				ints[i] = newValue;
				return ints;
			}
		}
		return ints;
	}

	public static int[] parseString(String string){
		int[] ints = new int[string.length()];

		int index = 0;
		int lastSpace = -1;
		while (index < string.length()) {

			if (Character.isWhitespace(string.charAt(index)) == true) {
//				System.out.println("find space "+index);
				if (lastSpace == -1) {
					lastSpace = index;
				}
				else{
					String value = string.substring(lastSpace + 1, index);
//					System.out.println(value);
					ints = addString(ints, value);
					lastSpace = index;
				}
			}
			index++;
		}

		return ints;
	}

	public static String removeDoubleSpace(String string){
		String newString = "";
		for (int i = 0; i < string.length() - 1; i++){
			if (Character.isWhitespace(string.charAt(i)) == true) {
				if (Character.isWhitespace(string.charAt(i + 1)) == false) {
					newString = newString + " ";
				}
			}
			else{
				newString = newString + string.charAt(i);
			}
		}
		return newString;
	}

	public static int[] parseStrings(String[] strings){
		String oneString = "";

		for (int i = 0; i < strings.length; i++) {
			oneString = oneString + " " + strings[i];
		}
		oneString = removeDoubleSpace(oneString + " ");
		oneString = oneString + " ";

		int[] ints = parseString(oneString);
		return ints;
	}

	public static int findSum(int[] ints){
		int sum = 0;
		for (int i = 0; i < ints.length; i++){
			sum = sum + ints[i];
		}
		return sum;
	}


	public static void main(String[] args){
		int[] integers = parseStrings(args);
		System.out.println(findSum(integers));
	}
}
