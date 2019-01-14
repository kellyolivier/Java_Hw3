

/**
 * Police represents someone over 18: their police role, ID, name, age, phoneNumber, and the total amount of money they have
 */
public class Police extends Person implements Employee {

    /**
     * enum of roles a police officer can have, akin to ranking
     */
    public enum Role{
        PATROL, SARGENT, CAPTAIN, CHIEF
    }
    private Role policeRole;
    private int ID;

    /**
     * Constructor for police
     * @param newRole the role they have in their police officer job
     * @param newId the employee Id they have
     * @param age their age, used in person constructor
     * @param name their name, used in person constructor
     * @param phoneNumber their phone number, used in person constructor
     * @param money their total starting money, used in person constructor
     * @throws Exception if age under 18...dont want an underage police officer
     */
    public Police (Role newRole, int newId, int age, String name, String phoneNumber, double money) throws Exception
    {
        super(age, name, phoneNumber,money);
        if(age < 18)
        {
            throw new Exception("Non-adult employee not allowed");
        }

        policeRole = newRole;
        ID = newId;
    }

    /**
     * implementation of employee interface
     * gets the police person object's pay based on their role and -1 if the enum gets used very incorrectly
     * @return pay based on police role
     */
    @Override
    public double getEmployeePay() {

        switch (policeRole) {
            case CHIEF:
            {
                return 1500.30;
            }
            case PATROL:
            {
                return 1200.99;
            }
            case CAPTAIN:
            {
                return 1444.35;
            }
            case SARGENT:
            {
                return 1300.34;
            }
        }
        return -1; //if things go terribly wrong
    }

    /**
     * implementation of employee interface
     * @return id
     */
    @Override
    public int getEmployeeId() {
        return ID;
    }

    /**
     * gets police role
     * @return police role, all caps
     */
    public Role getPoliceRole()
    {
        return policeRole;
    }

    /**
     * allows role to be changed
     * @param policeRole new role from enum list: CHIEF, PATROL, CAPTAIN, SARGENT
     */
    public void setPoliceRole(Role policeRole) {
        this.policeRole = policeRole;
    }
}

