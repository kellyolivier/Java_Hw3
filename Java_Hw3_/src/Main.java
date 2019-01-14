

/**
 * contains all code and assertions used to test the classes in the package
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("main ran");

        City city = new City("Public School", "101 Park ave", "100 Park Ave");

        city.addSchool("Charter School", "105 Park Ave");
        city.addSchool("Private School", "110 Park Ave");
        city.addBuilding("Townhouse", "109 Park Ave");
        city.addPerson(30, "Billy Neo", "3457383333", 9000.38, null);

        //add teachers
        try{
            city.addTeacher(Teacher.GradeLevel.ELEMENTARY, Teacher.TeachingCertification.MASTERS, 100, 30, "Darlene Maron", "5098882838", 0, "Charter School");
            city.addTeacher(Teacher.GradeLevel.MIDDLESCHOOL, Teacher.TeachingCertification.MASTERS, 101, 40, "Sam Swanson", "7887995999", 20000, "Charter School");
            city.addTeacher(Teacher.GradeLevel.HIGHSCHOOL, Teacher.TeachingCertification.UNDERGRAD, 102, 76, "Linda Bobley", "2393934299", 1000, "Private School");
        }
        catch(Exception e)
        {
            System.out.println("Teacher age cannot be set below 18");
        }

        //add students
        try{
            city.addKid("Lemonheads", 7, "Bobby John", "4902324567", 200,"Charter School");
            city.addKid("Candy Canes", 17,"Linda Lee", "1234567890", 130, "Private School");
            city.addKid("Ice cream", 15, "Theresa Linns", "9087654321", 50, "Private School");

        }
        catch(Exception e)
        {
            System.out.println("Kid age cannot be set above 18");
        }

        //add police
        try{
            city.addPolice(Police.Role.CAPTAIN, 70, 32,"Gleeson Hedges","2344445555", 3090.00, "City Hall");
            city.addPolice(Police.Role.PATROL, 78, 26,"Sandra Benson","3939238333", 1290.00, "City Hall");
        }
        catch(Exception e)
        {
            System.out.println("Police age cannot be set below 18");
        }

        //check print functions
        city.getBuildingNames();
        System.out.println(" ");

        city.getCitizenNames();
        System.out.println(" ");

        //check who is where
        city.displayStudentsInSchools();
        System.out.println(" ");

        city.displayTeachersInSchools();
        System.out.println(" ");

        city.displayPoliceInCityHall();
        System.out.println(" ");


        CityController controller = new CityController(city);
        controller.setVisible(true);

        CityView view = new CityView(controller);
        view.setVisible(true);
    }
}