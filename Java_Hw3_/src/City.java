
import java.util.Vector;

/**
 * Represents a basic city and citizens
 * Will at least have a city hall and one school
 * City is created ground up by adding in more citizens and buildings after city hall and initial school
 * @author Kelly Olivier
 * @version 1.0
 */
public class City {
    protected Vector<Person> citizens = new Vector<Person>();
    protected Vector<Building> buildings = new Vector<Building>() ;

    public Vector<Building> getBuildings() {
        return buildings;
    }

    public Vector<Person> getCitizens() {
        return citizens;
    }

    /**
     * Constructor for City class, will create a city hall and a starting school,
        *  as well as a "none" building that houses all persons who don't belong on any other occupants list
     * @param schoolName name of starting school
     * @param schoolAddress address of starting school
     * @param cityHallAddress address of city hall, name set to city hall
     */
    public City(String schoolName, String schoolAddress, String cityHallAddress)
    {
        CityHall cityHall = new CityHall("City Hall", cityHallAddress);
        School firstSchool = new School(schoolName, schoolAddress);
        Building cityGeneral = new Building("None", "Everywhere");

        buildings.add(cityGeneral);
        buildings.add(cityHall);
        buildings.add(firstSchool);

    }

    /**
     * moves a person from one building to another, no constraints on what person type can be in what kind of building
     * @param currentBuilding building the person is currently in
     * @param person the person being moved from currentbuilding to destinationbuilding
     * @param destinationBuilding building person should end up in
     * @return true/false on if operation was successful
     */

    public boolean changeOccupantBuilding(Building currentBuilding, Person person, Building destinationBuilding)
    {
        int currBuildIndexOfPerson = currentBuilding.getOccupantsIndex(person.getName());

        if(currBuildIndexOfPerson == -1)
        {
            return false;
        }

        currentBuilding.occupants.remove(currentBuilding.occupants.get(currBuildIndexOfPerson));
        destinationBuilding.occupants.add(person);

        return true;
    }

    /**
     * Adds a school to the buildings arrayList
     * @param schoolName name of building, for building constructor
     * @param schoolAddress address of building, for building constructor
     */
    public void addSchool(String schoolName, String schoolAddress)
    {
        School newSchool = new School(schoolName, schoolAddress);
        buildings.add(newSchool);
    }

    /**
     * Adds a building to the buildings arrayList
     * @param buildingName name of building, for building constructor
     * @param buildingAddress address of building, for building constructor
     */
    public void addBuilding(String buildingName, String buildingAddress)
    {
        Building newBuilding = new Building(buildingName, buildingAddress);
        buildings.add(newBuilding);
    }

    /**
     * Find specific building by name, requires all buildings have valid names
     * @param name name of building that is being searched for
     * @return index of desired building in the building arraylist, -1 if not found
     */
    public int findBuilding(String name)
    {
        for(int i = 0; i < buildings.size(); i++)
        {
            if(buildings.get(i).getName().equals(name))
            {
                return i;
            }
        }
        return -1;
    }

    /**
     * calls police constructor
     * allows for the new police object to immediately be assigned to a building's occupant list, if the buildingName is not set to null
     * always adds new police object to citizens list of City
     * @param role police role, for police constructor
     * @param id police id, for police constructor and employee implementation
     * @param age person age, for constructor
     * @param name person name, for constructor
     * @param phoneNumber person's phone number, for constructor
     * @param money person's starting money, for constructor
     * @param buildingName name of building they should occupy, if one is desired when calling method
     * @throws Exception carries on the age constraint exception that can be thrown by police for age being under 18
     */
    public void addPolice(Police.Role role, int id, int age, String name, String phoneNumber, double money, String buildingName) throws Exception
    {
        Police police = new Police(role, id, age, name, phoneNumber, money);
        citizens.add(police);
        if(buildingName == null)
        {
            buildingName = "None";
        }
        buildings.get(findBuilding(buildingName)).addOccupant(police);

    }

    /**
     * calls teacher constructor
     * allows for the new teacher object to immediately be assigned to a building's occupant list, if the buildingName is not set to null
     * always adds new teacher object to citizens list of City
     * @param newLevel for teacher constructor, the grade level that a teacher teaches; choices are in an enum: ELEMENTARY, MIDDLE, HIGH, UNIVERSITY
     * @param newCertification for teacher constructor, the certification that the teacher has, this determines pay. choices in an enum: UNDERGRADUATE, MASTERS, DOCTORATE
     * @param newId the teacher id, for teacher constructor and employee implementation
     * @param age the person's age, for constructor
     * @param name the person's name, for constructor
     * @param phoneNumber the person's phoneNumber, for constructor
     * @param money the person's starting money, for constructor
     * @param buildingName name of building they should occupy, if one is desired when calling the method
     * @throws Exception if age is set below 18, carried from teacher constructor
     */
    public void addTeacher(Teacher.GradeLevel newLevel, Teacher.TeachingCertification newCertification, int newId, int age, String name, String phoneNumber, double money, String buildingName) throws Exception
    {
        Teacher teacher = new Teacher(newLevel, newCertification, newId, age, name, phoneNumber, money);
        citizens.add(teacher);
        if(buildingName == null)
        {
            buildingName = "None";
        }
        buildings.get(findBuilding(buildingName)).addOccupant(teacher);

    }

    /**
     * calls kid constructor
     * allows for the new kid object to immediately be assigned to a building's occupant list, if the buildingName is not set to null
     * always adds new kid object to citizens list of City
     * @param newFavCandy the child's favorite candy, for constructor; set to string because too many options to use an enum
     * @param age the person's age, for constructor
     * @param name the person's name, for constructor
     * @param phoneNumber the person's phoneNumber, for constructor
     * @param money the person's starting money, for constructor
     * @param buildingName name of building they should occupy, if one is desired when calling the method
     * @throws Exception if age set above 18, carried from kid constructor
     */
    public void addKid(String newFavCandy, int age, String name, String phoneNumber, double money, String buildingName) throws Exception
    {
        Kid kid = new Kid(newFavCandy, age, name, phoneNumber, money);
        citizens.add(kid);
        if(buildingName == null)
        {
            buildingName = "None";
        }
        buildings.get(findBuilding(buildingName)).addOccupant(kid);

    }

    /**
     * calls person constructor
     * allows for the new person object to immediately be assigned to a building's occupant list, if the buildingName is not set to null
     * always adds new person object to citizens list of City
     * @param age the person's age, for constructor
     * @param name the person's name, for constructor
     * @param phoneNumber the person's phoneNumber, for constructor
     * @param money the person's starting money, for constructor
     * @param buildingName name of building they should occupy, if one is desired when calling the method
     */
    public void addPerson(int age, String name, String phoneNumber, double money, String buildingName)
    {
        Person person = new Person(age, name, phoneNumber, money);
        citizens.add(person);
        if(buildingName == null)
        {
            buildingName = "None";
        }
        buildings.get(findBuilding(buildingName)).addOccupant(person);
    }

    /**
     * Prints out all names of citizens from citizens arrayList
     */
    public void getCitizenNames(){
        System.out.printf("Here is the list of %d citizens: \n", citizens.size());
        for(int i = 0; i < citizens.size(); i++)
        {
            System.out.println(citizens.get(i).getName());
        }
    }

    /**
     * Prints out all names of buildings from buildings arrayList
     */
    public void getBuildingNames(){
        System.out.printf("Here is the list of %d buildings: \n", buildings.size());
        for(int i = 0; i < buildings.size(); i++)
        {
            System.out.println(buildings.get(i).getName());
        }
    }

    /**
     * Will add pay to all objects in the arrayList citizens that implement the employee interface
     *
     */
    public void payAllEmployees()
    {
        for(int i = 0; i < citizens.size(); i++)
        {
            double pay = 0;

            //get person object
            Person person = citizens.get(i);

            //if employed, get pay
            if(person instanceof Employee)
            {
                pay = ((Employee) person).getEmployeePay();
            }

            //add pay to person's total money
            person.addMoney(pay);
        }
    }

    /**
     * pays a single employee when passed their object
     * @param person is an employee object, aka implements employee interface and is a person
     * @throws Exception if the person passed in
     */
    public void payEmployee(Person person) throws Exception
    {
        if(!(person instanceof Employee))
        {
            throw new Exception("cannot give pay to unemployed person");
        }
        double pay = ((Employee) person).getEmployeePay();
        person.addMoney(pay);
    }

    /**
     * displays all students in each school in the city
     */
    public void displayStudentsInSchools()
    {
        //outer loop through buildings arrayList
        for(int i = 0; i < buildings.size(); i++)
        {
            Building school = buildings.get(i);
            Vector<Person> occupants = school.getOccupants();

            if (school instanceof School)
            {
                //print out header for student list with the school name, not indented
                System.out.printf("Students in school %s :\n", school.getName());

                //inner loop through occupant array of that school
                for(int j = 0; j < occupants.size(); j++)
                {
                    //print out student's name with some indentation for easy reading
                    if(occupants.get(j) instanceof Kid)
                    {
                        System.out.printf("    %s\n",occupants.get(j).getName());
                    }
                }
            }
        }
    }

    /**
     * displays the names of all teachers in each school in the city
     */
    public void displayTeachersInSchools()
    {
        //outer loop through building arrayList
        for(int i = 0; i < buildings.size(); i++)
        {
            Building current = buildings.get(i);
            Vector<Person> occupants = current.getOccupants();

            if (current instanceof School)
            {
                //print out header for teachers list with the school name, not indented
                System.out.printf("Teachers in school %s :\n", current.getName());

                //inner loop through occupant array of that school
                for(int j = 0; j < occupants.size(); j++)
                {
                    if(occupants.get(j) instanceof Teacher)
                    {
                        //print out teacher's name with some indentation for easy reading
                        System.out.printf("    %s\n",occupants.get(j).getName());
                    }
                }
            }
        }
    }

    /**
     * Displays the names of all police in the City Hall, with no indentation (only one city hall per city)
     */
    public void displayPoliceInCityHall()
    {
        for(int i = 0; i < buildings.size(); i++)
        {
            //outer loop through building arraylist
            Building current = buildings.get(i);
            Vector<Person> occupants = current.getOccupants();

            if (current instanceof CityHall)
            {
                //print out generic header, since only one city hall per city
                System.out.println("Police in City Hall");
                for(int j = 0; j < occupants.size(); j++)
                {
                    if(occupants.get(j) instanceof Police)
                    {
                        //print out police's name with some indentation for easy reading
                        System.out.printf("    %s\n",occupants.get(j).getName());
                    }
                }
            }
        }
    }


}
