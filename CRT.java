public class CRT {

	public long[] EGCD(long a, long b) {
		long aTemp = Math.max(a, b), bTemp = Math.min(b, a);
		a = aTemp;
		b = bTemp;
		// Using the equation d = a * S2 + b * T2
		long s1 = 1, s2 = 0, t1 = 0, t2 = 1, temp = 0; // S1,T1 are coefficents from previous equation - S2,T2 are current Coefficents for the equation
		long q = a / b, r = a % b, d = r; // Beginining Value for q "quotient",r "reminder",d "GCD Value" 
		a = b;
		b = r;
		while (r != 0) {
			temp = s2;
			s2 = s1 - s2 * q; // calculting the new value for S2
			s1 = temp; // S1 gains value of previous S2  
			temp = t2;
			t2 = t1 - t2 * q;// calculting the new value for T2
			t1 = temp; // T1 gains value of previous T2  
			d = r; 
			r = a % b;
			q = a / b;
			a = b;
			b = r;
		}
		long[] ans = { d, s2, t2 };
		return ans;
	}

	public long[] Chinese(long[] mods,long[] a) {
		long M = 1, x = 0;
		int sz = a.length;
		long[][] ans = new long[a.length][3];
		for (int i = 0; i < sz; i++) {
			M *= mods[i];
		}
		for (int i = 0; i < sz; i++) {
			long mi = mods[i];
			ans[i][0] = a[i]; //ai
			ans[i][1] = M / mi; // Mk
			ans[i][2] = EGCD(ans[i][1] % mi, mi)[2]; // Yi = Mk^-1 (mod mi)
			x += (ans[i][0] * ans[i][1] * ans[i][2]) % M;
			x %= M;
		}
		while (x < 0)
			x += M;
		// System.out.println("Answer is "+ x + " (mod "+M+")");
		return new long[] { x%M, M };

	}

	public long[][] ChineseOperation(long[] mods, long A, long B) {
		int sz = mods.length;
		long[][] A_B = new long[2][sz];
			for (int i = 0; i < sz; i++) {
				A_B[0][i] = A % mods[i];
				A_B[1][i] = B % mods[i];
			}
		long[] add = ChineseAdd(mods, A_B), mpy = ChineseMultiply(mods, A_B);
		long[] addAns = Chinese(mods, add), mpyAns = Chinese(mods, mpy);
		System.out.println("\nC = A+B = " + A + " + " + B + " = " + addAns[0] + "(Mod " + addAns[1] + ")");
		System.out.println("D = A*B = " + A + " * " + B + " = " + mpyAns[0] + "(Mod " + mpyAns[1] + ")\n");
		return new long[][] { addAns, mpyAns };

	}

	private long[] ChineseAdd(long[] mods, long[][] A_B) {
		int sz = mods.length;
		long[] ans = new long[sz];
		for (int i = 0; i < sz; i++)
			ans[i] = (A_B[0][i] + A_B[1][i]) % mods[i];
		return ans;
	}

	private long[] ChineseMultiply(long[] mods, long[][] A_B) {
		int sz = mods.length;
		long[] ans = new long[sz];
		for (int i = 0; i < sz; i++)
			ans[i] = (A_B[0][i] * A_B[1][i]) % mods[i];
		return ans;
	}

	public static void main(String[] args) {

		CRT crt = new CRT();
		long[] a = { 2, 1, 3 };
		long[] mods =new long [] { 3, 4, 5 };
		long[] ans = crt.Chinese(mods, a);
		System.out.println("\nAnswer is " + ans[0] + " (mod " + ans[1] + ")");
		
		mods =new long [] {6548,987153,3545,11};
		crt.ChineseOperation(mods , 156,15788);
	}
}
