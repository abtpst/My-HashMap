package abtmap;

public class AbtHashMap implements ItContainer {
	private static final int size = 10;
	private AbtMapEn[][] entries = new AbtMapEn[size][];

	public AbtMapEn get(String k) {
		int hash = k.hashCode() % size - 1;

		int i = 0;
		AbtMapEn e = entries[hash][i];

		while (e != null) {
			if (e.getKey().equals(k))
				return e;

			i++;

			if (i >= size)
				return null;

			e = entries[hash][i];
		}

		return null;
	}

	public void put(String k, String v) {
		System.out.println("Adding " + k + " " + v);
		int hash = k.hashCode() % size - 1;

		if (entries[hash] == null) {
			entries[hash] = new AbtMapEn[10];
			entries[hash][0] = new AbtMapEn(k, v);
		}

		else {
			int i = 0;
			AbtMapEn e = entries[hash][i];

			if (e != null) {
				while (e != null) {
					if (e.key.equals(k)) {
						e.setValue(v);
						break;
					}

					else

						e = entries[hash][++i];

				}

				if (e == null) {

					entries[hash][i] = new AbtMapEn(k, v);
				}
			}

			else
				entries[hash][i] = new AbtMapEn(k, v);
		}
	}

	public AbtMapIterator getIt() {

		return new AbtMapIt(entries);
	}

	private class AbtMapIt implements AbtMapIterator {
		int index = 0, itsize = 0;
		private AbtMapEn[][] itentries;
		int[] visIndex;
		int currI = 0;

		AbtMapIt(AbtMapEn[][] entries) {
			itentries = entries;
			itsize = itentries[0].length;
			visIndex = new int[itsize];

			for (int i = 0; i < itsize; i++)
				visIndex[i] = -1;
		}

		public boolean hasNext() {

			while (index < itsize) {
				if (itentries[index] == null) {
					index++;
					continue;
				} else {
					if (itentries[index][0] != null) {
						int i = 0;

						i = visIndex[index] + 1;

						if (itentries[index][i] != null) {
							currI = i;
							return true;
						} else {
							index++;
							continue;
						}
						/*
						 * while(runnow!=null) { if(runnow.getVisited())
						 * runnow=itentries[index][++i]; else break; }
						 * 
						 * if(runnow==null) { index++; continue; }
						 * 
						 * else { currI=i; return true; }
						 */
					}

					else
						index++;
				}
			}

			return false;
		}

		public AbtMapEn next() {

			if (this.hasNext()) {

				AbtMapEn runner = itentries[index][currI];

				runner.setVisited();

				visIndex[index] = currI;

				return runner;
			}

			return null;
		}

	}
}