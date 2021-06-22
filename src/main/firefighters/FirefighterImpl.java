package main.firefighters;

import main.api.CityNode;
import main.api.Firefighter;

import java.util.ArrayList;

public class FirefighterImpl implements Firefighter {

  // all the buildings that were visited including the firestation
  private final ArrayList<CityNode> visitedBuildings;

  public FirefighterImpl(CityNode city) {
    this.visitedBuildings = new ArrayList<>();
    this.visitedBuildings.add(city);
  }


  // Adds a burning building to the visited building list
  public void addBurningBuilding(CityNode city) {
    visitedBuildings.add(city);
  }

  @Override
  public CityNode getLocation() {
    return visitedBuildings.get(visitedBuildings.size()-1);
  }

  @Override
  public int distanceTraveled() {
    var distance = 0;
    if (visitedBuildings.size() <= 1)
      return distance;


    // calculate manhattan distance between all points
    for (var i = 1; i < visitedBuildings.size(); i++) {
      distance += Math.abs(visitedBuildings.get(i).getX()- visitedBuildings.get(i-1).getX()) + Math.abs(visitedBuildings.get(i).getY()- visitedBuildings.get(i-1).getY());
    }

    return distance;

  }


}
