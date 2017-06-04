package org.codechef.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class City {
	private int cityId;
	private int productSold;
	private boolean capitalCity;
	private ArrayList<Edge> neigbhours = new ArrayList<Edge>();

	public ArrayList<Edge> getNeigbhours() {
		return neigbhours;
	}
	public void setNeigbhours(ArrayList<Edge> neigbhours) {
		this.neigbhours = neigbhours;
	}
	public int getProductSold() {
		return productSold;
	}
	public void setProductSold(int productSold) {
		this.productSold = productSold;
	}
	public boolean isCapitalCity() {
		return capitalCity;
	}
	public void setCapitalCity(boolean capitalCity) {
		this.capitalCity = capitalCity;
	}
	public int getCityId() {
		return cityId;
	}
	public void setCityId(int cityId) {
		this.cityId = cityId;
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cityId;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		City other = (City) obj;
		if (cityId != other.cityId)
			return false;
		return true;
	}
	public int getDistanceToCapital(Set<City> visited){
		if(this.isCapitalCity()){
			return 0;
		}else{
			visited.add(this);
			Set<City> neighbouringCities = this.getNeighbouringCities();
			neighbouringCities.removeAll(visited);
			if(neighbouringCities.isEmpty()){
				return -1;
			}
			int minDist = 0;
			for(City c : neighbouringCities){
				if(!visited.contains(c)){
					int dist = c.getDistanceToCapital(visited);
					if(dist == -1){
						continue;
					}
					dist += 1;
					if(minDist == 0 || minDist > dist){
						minDist = dist;
					}
				}
			}
			return minDist;
		}		
	}
	
	public Set<City> getNeighbouringCities(){
		Set<City> neighbouringCities = new HashSet<City>();
		for(Edge e: neigbhours){
			neighbouringCities.add(e.getY());
		}
		return neighbouringCities;
	}

}
