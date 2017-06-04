package org.codechef.client;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

import org.codechef.entity.City;
import org.codechef.entity.Edge;
import org.codechef.entity.Map;

public class Main {
	static int N,K;
	static Map map;
	

	/**
	 * Problem statement: https://www.codechef.com/problems/CHEFPRES
	 */
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);		
		String[] tokens = readLine(scanner);
		N = Integer.parseInt(tokens[0]);
		K = Integer.parseInt(tokens[1]);
		tokens = readLine(scanner);
		int B = Integer.parseInt(tokens[0]) - 1;
		buildMap(N,B);
		for(int i=0; i< N-1; i++){
			tokens = readLine(scanner);
			int X = Integer.parseInt(tokens[0]);
			int Y = Integer.parseInt(tokens[1]);
			buildpath(X,Y);
		}
		for(int i=0; i< N; i++){
			tokens = readLine(scanner);
			int F = Integer.parseInt(tokens[0]);
			setProduct(i,F);			
		}
		tokens = readLine(scanner);
		int Q = Integer.parseInt(tokens[0]);
		
		ArrayList<Integer> result = new ArrayList<Integer>(Q);
		for(int i=0; i< Q; i++){
			tokens = readLine(scanner);
			int A = Integer.parseInt(tokens[0])-1;
			int P = Integer.parseInt(tokens[1]);
			ArrayList<City> fromCities = new ArrayList<City>(Q);
			fromCities.addAll(map.getCitiesSellingProduct(P));
			int maxDistFromCapital = -1;
			int destCity = -1;
			//System.out.println("dest dist");
			for(City c : fromCities){				
				boolean pathExist = map.hasPath(map.getCities().get(A), c, new HashSet<City>());
				if(pathExist){
					int newDist = c.getDistanceToCapital(new HashSet<City>());
					if(newDist > maxDistFromCapital){
						maxDistFromCapital = newDist;
						destCity = c.getCityId();
					}
				}
				//System.out.println(c.getCityId() + " " + maxDistFromCapital);
			}
			result.add( destCity);
			//System.out.println(A + " " + P + " " + destCity);
		}
		for(int i=0; i< Q; i++){
			System.out.println(result.get(i)+1);
		}
		
		/*System.out.println("Distance to Capital");
		for(City c: map.getCities()){
			int d = c.getDistanceToCapital(new HashSet<City>());
			System.out.println(c.getCityId() + " = " + d);
			System.out.println(c.getCityId() + " has path to B ?" + map.hasPath(c, map.getCapital(),new HashSet<City>()));
			//break;
		}*/
	}
	private static void buildMap(int numberOfCities, int capital) {
		ArrayList<City> cities = new ArrayList<City>(numberOfCities);
		for(int i=0; i<numberOfCities; i++){
			City city = new City();
			city.setCityId(i);
			if(i == capital){
				city.setCapitalCity(true);
			}
			cities.add(city);
		}
		map = new Map();
		map.setCities(cities );
		map.setCapital(cities.get(capital-1));
	}
	private static String[] readLine(Scanner scanner) {
		String line;
		String[] tokens;
		line = scanner.nextLine();
		tokens = line.split(" ");
		return tokens;
	}
	private static void setProduct(int index, int f) {
		map.getCities().get(index).setProductSold(f);

	}

	private static void buildpath(int x, int y) {
		ArrayList<Edge> neighboursOfX = map.getCities().get(x-1).getNeigbhours();
		Edge edge = new Edge();
		edge.setX(map.getCities().get(x-1));
		edge.setY(map.getCities().get(y-1));
		neighboursOfX.add(edge);
		
		ArrayList<Edge> neighboursOfY = map.getCities().get(y-1).getNeigbhours();
		edge = new Edge();
		edge.setX(map.getCities().get(y-1));
		edge.setY(map.getCities().get(x-1));
		neighboursOfY.add(edge);
	}

}