import java.util.regex.*;

/**
 * represents a basic person of any age, their name, age, phoneNumber, and the total amount of money they have
 * no way to change age once set, since people are represented as static in time and as permanently occupying buildings (for clarity)
 * all persons have a total amount of money
 * @author Kelly Olivier
 * @version 1.0
 */
public class Person {
    private String name;
    private int age;
    private String phoneNumber;
    private double totalMoney;

    /**
     * constructor for person
     * @param newAge the age of the person
     * @param newName the name of the person
     * @param newPhone the person's phone number
     * @param newMoney the amount of money the person starts with
     */
    public Person(int newAge, String newName, String newPhone, double newMoney)
    {
        if(isValidPhone(newPhone))
        {
            phoneNumber = newPhone;
        }
        else
        {
            phoneNumber = null;
        }

        name = newName;
        age = newAge;
        totalMoney = newMoney;
    }

    /**
     * override of to_string to properly display person objects in Jlists
     * @return name of object
     */
    @Override
    public String toString()
    {
        return this.getName();
    }

    /**
     * check validity of phone number
     * @param phoneNum phone number to check
     * @return boolean based on validity of phone number
     */
    public boolean isValidPhone(String phoneNum)
    {
        int length = phoneNum.length();

        if(length != 7 && length !=10)
        {
            return false;
        }

        return true;


    }

    /**
     * allows money to be added to the total; does not simply overwrite with a new amount
     * @param money amount of money to be added
     */
    public void addMoney(double money)
    {
        totalMoney += money;
    }

    /**
     * gets person's age
     * @return age
     */
    public int getAge() {
        return age;
    }

    /**
     * gets person's name
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * gets person's phone number
     * @return phone number
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * gets the total money that a person possesses
     * @return total money
     */
    public double getTotalMoney() {
        return totalMoney;
    }

    /**
     * changes a person's phone number
     * @param phoneNumber new phone number
     */
    public void setPhoneNumber(String phoneNumber)
    {
        boolean valid = isValidPhone(phoneNumber);
        if(valid)
        {
            this.phoneNumber = phoneNumber;
        }
    }

    /**
     * changes a person's name
     * @param name new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * completely overwrites a person's current money with a new total
     * @param totalMoney new total amount of money to change current money to
     */
    public void setTotalMoney(double totalMoney) {
        this.totalMoney = totalMoney;
    }
}
