package org.differnce;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

class DifferenceChecker {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		//System.out.println("Enter number of test cases: ");
		int T = Integer.parseInt(scanner.nextLine().split(" ")[0]);
		int[] ans = new int[T];
		int j = 0;
		while(j < T){
			//System.out.println("Enter M & N ");
			String inputSizes =  scanner.nextLine();
			String[] tokens = inputSizes.split(" ");
			int M = Integer.parseInt(tokens[0]);
			int N = Integer.parseInt(tokens[1]);
			//System.out.println("M = " + M + " N = " + N);
			Set<Integer> aliceSet  = new HashSet<Integer>();
			Set<Integer> bertaSet  = new HashSet<Integer>();
			//System.out.println("Enter " + M + " integers for Alice : ");
			inputSizes =  scanner.nextLine();
			tokens = inputSizes.split(" ");
			for(int i=0; i< M; i++){
				aliceSet.add(Integer.parseInt(tokens[i]));
			}
			//System.out.println("Enter " + N + " integers for Berta : ");
			inputSizes =  scanner.nextLine();
			tokens = inputSizes.split(" ");
			for(int i=0; i< N; i++){
				bertaSet.add(Integer.parseInt(tokens[i]));
			}
				
			aliceSet.removeAll(bertaSet);
			int totalRemoved = M - aliceSet.size();
			//System.out.println("Alice needs to throw away " + totalRemoved + " numbers.");
			ans[j] = totalRemoved;
			j++;
		}
		for(int i=0; i<T; i++){
			System.out.println(ans[i]);
		}
		//System.out.println("Goodbye");
	}		
}
