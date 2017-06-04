package org.codechef.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;


public class Map {
	private ArrayList<City> cities = new ArrayList<City>();
	private City capital;
	public ArrayList<City> getCities() {
		return cities;
	}
	public void setCities(ArrayList<City> cities) {
		this.cities = cities;
	}
	public City getCapital() {
		return capital;
	}
	public void setCapital(City capital) {
		this.capital = capital;
	}

	/*public City getCityToBuyProductFrom(City source, int product){
		int farthestDistance = 0;
		City destination = null;
		if(source.getProductSold() == product){
			farthestDistance = source.getDistanceToCapital(null);
			destination = source;
		}
		Set<City> visited = new HashSet<City>();
		visited.add(source);
		for(Edge neighbour : source.getNeigbhours()){
			if(neighbour.getY().getProductSold() == product){
				
				int dist = neighbour.getY().getDistanceToCapital(visited);
				if(farthestDistance < dist){
					destination = neighbour.getY();
					farthestDistance = dist;
				}
			}
		}
		return destination;
	}*/
	public boolean hasPath(City source, City destination, Set<City> visited){
		boolean pathExists = false;
		Set<City> neighbours = source.getNeighbouringCities();
		visited.add(source);
		if(neighbours.isEmpty()){
			return false;
		}
		Iterator<City> cityIterator = neighbours.iterator();
		while(cityIterator.hasNext()){			
			City c = cityIterator.next();
			if(visited.contains(c)){
				//System.out.println("already visited=" + c.getCityId());
				continue;
			}
			if(neighbours.contains(destination)){
				return true;
			}
			if(c.getCityId() != source.getCityId() && !pathExists){
				pathExists = hasPath(c,destination,visited);
			}
		}
		return pathExists;
	}
	
	public Set<City> getCitiesSellingProduct(int product){
		Set<City> cities = new HashSet<City>();
		for(City c : this.cities){
			if(c.getProductSold() == product){
				cities.add(c);
			}
		}
		return cities;
	}
	
}
