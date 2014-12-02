package abtmap;

public class HashMain {

	public static void main(String[] args) {

		AbtHashMap am = new AbtHashMap();

		am.put("abt", "arsenal");
		am.put("zxc", "mvirmvi");

		AbtMapIterator abit = am.getIt();
		while (abit.hasNext()) {
			AbtMapEn n = abit.next();
			System.out.println(n.getKey() + " " + n.getValue());
		}

	}

}