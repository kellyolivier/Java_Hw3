

/**
 * Teacher represents someone over 18: the grade level they can teach, the teaching certification degree they have, name, age, phoneNumber, and the total amount of money they have
 */
public class Teacher extends Person implements Employee {

    /**
     * what grade level a teacher is certified to teach
     */
    public enum GradeLevel{
        ELEMENTARY, MIDDLESCHOOL, HIGHSCHOOL, UNIVERSITY
    }

    /**
     * what certification the teacher has to teach
     */
    public enum TeachingCertification{
        UNDERGRAD, MASTERS, DOCTORATE
    }

    private GradeLevel teachingLevel;
    private TeachingCertification certification;
    private int ID;

    /**
     * constructor for teacher
     * @param newLevel grade level they can teach based on enum options:  ELEMENTARY, MIDDLE, HIGH, UNIVERSITY
     * @param newCertification certification level they have, based on enum options: UNDERGRAD, MASTERS, DOCTORATE
     * @param newId teacher id they have
     * @param age age of teacher, for person constructor
     * @param name name of teacher, for person constructor
     * @param phoneNumber phone number of teacher, for person constructor
     * @param money amount of starting money, for person constructor
     * @throws Exception when age is below 18
     */
    public Teacher(GradeLevel newLevel,TeachingCertification newCertification, int newId, int age, String name, String phoneNumber, double money) throws Exception
    {
        super(age, name, phoneNumber, money);
        if(age <18)
        {
            throw new Exception("Non-adult employee not allowed");
        }

        teachingLevel = newLevel;
        certification = newCertification;
        ID = newId;
    }

    /**
     * implementation of employee interface
     * gets the  teacher object's pay based on their certification level and -1 if the enum gets used very incorrectly
     * @return pay based on certification level
     */
    @Override
    public double getEmployeePay() {

        switch (certification) {
            case UNDERGRAD:
            {
                return 403.90;
            }
            case MASTERS:
            {
                return 790.29;
            }
            case DOCTORATE:
            {
                return 1050.35;
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
     * gets teaching level of teacher
     * @return teaching level: ELEMENTARY, MIDDLE, HIGH, UNIVERSITY
     */
    public GradeLevel getTeachingLevel() {
        return teachingLevel;
    }

    /**
     * gets teaching certification of teacher
     * @return certification: UNDERGRAD, MASTERS, DOCTORATE
     */
    public TeachingCertification getCertification()
    {
        return certification;
    }

    /**
     * sets new teaching certification of teacher
     * @param certification can be: UNDERGRAD, MASTERS, DOCTORATE
     */
    public void setCertification(TeachingCertification certification) {
        this.certification = certification;
    }

    /**
     * sets new teaching grade level
     * @param teachingLevel can be: ELEMENTARY, MIDDLE, HIGH, UNIVERSITY
     */
    public void setTeachingLevel(GradeLevel teachingLevel) {
        this.teachingLevel = teachingLevel;
    }
}

