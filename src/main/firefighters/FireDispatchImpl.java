package main.firefighters;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import main.api.City;
import main.api.CityNode;
import main.api.FireDispatch;
import main.api.Firefighter;
import main.api.exceptions.NoFireFoundException;

public class FireDispatchImpl implements FireDispatch {

    // Home city that the station is based in
    private final City HomeCity;

    // All firefighters that are part of this station
    private final List<Firefighter> firefighters;

    public FireDispatchImpl(City city) {
        this.HomeCity = city;
        this.firefighters = new ArrayList<>();

    }

    @Override
    public void setFirefighters(int numFirefighters) {
        for (var i = 0; i < numFirefighters; i++) {
            this.firefighters.add(new FirefighterImpl(this.HomeCity.getFireStation().getLocation()));
        }
    }

    @Override
    public List<Firefighter> getFirefighters() {
        return this.firefighters;
    }

    @Override
    public void dispatchFirefighers(CityNode... burningBuildings) {
        // sort so that we get the firefighters who have traveled the least
        Comparator<Firefighter> byDistance = Comparator
                .comparing(Firefighter::distanceTraveled);

        // Sort so we dispatch the firefighters who have traveled the least for each dispatch
        firefighters.sort(byDistance);

        for (var i = 0; i < burningBuildings.length; i++) {

            var ffIndex = i % firefighters.size();
            // cast because we are using the interface an I added an extra function I need to access
            var ff = (FirefighterImpl) firefighters.get(ffIndex);
            ff.addBurningBuilding(burningBuildings[i]);


            var burningBuilding = this.HomeCity.getBuilding(burningBuildings[i]);

            try {
              burningBuilding.extinguishFire();
            } catch (NoFireFoundException e) {
                // tried not to change the function definition so I just return if there's no fire found
                return;
            }
        }
    }


}
