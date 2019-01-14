
/**
 * Kid represents someone under 18: their favorite candy, name, age, phoneNumber, and the total amount of money they have
 * @author Kelly Olivier
 * @version 1.0
 */
public class Kid extends Person {
    private String favCandy;

    /**
     * Constructor for Kid
     * @param newFavCandy string of the Kid's favorite candy, not an enum because there's too many possibilities
     * @param age age of kid, for constructor; exception will be thrown if set below 18
     * @param name name of kid, for person constructor
     * @param phoneNumber phoneNumber of kid, for person constructor
     * @param money starting money of kid, for person constructor
     * @throws Exception to avoid adult children....
     */
    public Kid (String newFavCandy, int age, String name, String phoneNumber, double money) throws Exception
    {
        super(age, name, phoneNumber, money);
        if(age>18)
        {
            throw new Exception("Non-kid age entered for a kid");
        }
        favCandy = newFavCandy;
    }

    /**
     * get's Kid's currently stored favorite candy
     * @return child's favorite candy
     */
    public String getFavCandy() {
        return favCandy;
    }

    /**
     * changes kid's stored favorite candy
     * @param favCandy string containing name of a new favorite candy
     */
    public void setFavCandy(String favCandy) {
        this.favCandy = favCandy;
    }
}
