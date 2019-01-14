import javax.swing.*;

/**
 * controller for cityView
 */
public class CityController extends JFrame{
    private JList<Person> occupantList;
    private JList<Building> buildingsList;
    private City city;

    /**
     * constructor for cityController
     * @param newCity city that is being worked with
     */
    public CityController(City newCity)
    {
        super();
        city = newCity;

        createBuildingList();

    }

    /**
     * creates a building Jlist from the vector of buildings that city has
     */
    public void createBuildingList()
    {
        buildingsList = new JList(city.getBuildings());
    }

    /**
     * gets single building using index in buildingsList from the city's buildingslist
     * @param index of building
     * @return desired buliding based on index
     */
    public Building getBuilding(int index)
    {
        Building building = city.buildings.get(index);
        return building;
    }

    /**
     * returns the buildingslist
     * @return buildingsList as a whole
     */
    public JList<Building> getBuildingsList()
    {
        return buildingsList;
    }

    /**
     * moves a single person between two buildings
     * @param origin building
     * @param person being moved
     * @param destination building
     */
    public void moveOccupant(Building origin, Person person, Building destination)
    {
        city.changeOccupantBuilding(origin, person, destination);
    }
}
