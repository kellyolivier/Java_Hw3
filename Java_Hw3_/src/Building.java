
import java.util.Vector;

/**
 * Building base class, has a list of occupants, a name and an address
 * building address allowed to change in case of street name change
 * occupants of a building are there until removed; occupancy based on working/going to school in the building. Not based on instant that the building is checked
 * @author Kelly Olivier
 * @version 1.0
 */
public class Building {
    private String name;
    private String address;
    protected Vector<Person> occupants = new Vector<Person>();;

    /**
     * building constructor
     * @param newName name of building
     * @param newAddress address of building
     */
    public Building(String newName, String newAddress)
    {
        name = newName;
        address = newAddress;
    }

    /**
     * returns the size of the occupant vector
     * @return how many occupants the ubilding has
     */
    public int buildingOccupantCount()
    {
        return occupants.size();
    }

    /**
     * override of to_string to allow name of building to be displayed in jlist
     * @return name of building
     */
    @Override
    public String toString()
    {
        return this.getName();
    }

    /**
     * finds a person by name in the occupants list, assumes all names different
     * @param personName name of person that is being searched for
     * @return index of the person whose name was searched for, -1 if not found
     */
    public int getOccupantsIndex(String personName)
    {
        for(int i = 0; i < occupants.size(); i++)
        {
            if(occupants.get(i).getName() == personName)
            {
                return i;
            }
        }
        return -1;
    }


    /**
     * get single building occupant from the building based on location in vector
     * @param index of person
     * @return person object at index
     */
    public Person getOccupant(int index)
    {
        Person person = occupants.get(index);
        return person;
    }

    /**
     * adds a person occupant to the occupants list
     * @param person person object that should be added
     */
    public void addOccupant(Person person)
    {
        occupants.add(person);
    }

    /**
     * removes an occupant at a specific index
     * @param index index of person object in the occupants array
     */
    public void removeOccupant(int index)
    {
        occupants.remove(index);
    }

    /**
     * allows building name to be changed
     * @param name new name of building
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * allows building address to be changed
     * @param address new address of building
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * gets building name
     * @return building name
     */
    public String getName() {
        return name;
    }

    /**
     * gets building address
     * @return building address
     */
    public String getAddress() {
        return address;
    }

    /**
     * gets full current occupants list from building
     * @return arraylist of occupants
     */
    public Vector<Person> getOccupants() {
        return occupants;
    }
}

