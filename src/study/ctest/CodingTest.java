package study.ctest;

import java.io.*;

public class CodingTest {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		
		int len = Integer.parseInt(str);
		for(int i=1; i<=len; i++) {
			if(len%i == 0) {
				System.out.print(i+" ");
			}
		}
	}

}
