import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * creates and updates UI for the city class
 */
public class CityView extends JFrame  {
    private CityController controller;
    private JPanel peoplePanel;
    private JPanel buildingsPanel;
    private JPanel infoPanel;
    private JPanel personPanel; // form requires this to be here????
    private JList buildingList;
    private JList buildingOccupantList;
    private Person currentPersonSelected;
    private JPanel overallPanel;
    private JLabel mouseDragLabel = new JLabel();
    private JLabel bName = new JLabel();
    private JLabel bAddr = new JLabel();
    private JLabel pName = new JLabel();
    private JLabel pAge = new JLabel();
    private JLabel pPhoneNumber = new JLabel();
    private JLabel pMoney = new JLabel();
    private JLabel tGradeLevel = new JLabel();
    private JLabel tCertification = new JLabel();
    private JLabel eId = new JLabel();
    private JLabel poRole = new JLabel();
    private JLabel kFavCandy = new JLabel();
    private JLabel personType = new JLabel();
    private JLabel bOccupantCount = new JLabel();
    private JLabel policeNum = new JLabel();
    private JLabel teacherNum = new JLabel();
    private JLabel studentNum = new JLabel();
    private JLabel spacer = new JLabel(" ");
    private JLabel peopleNum = new JLabel();
    private JScrollPane buildingScroll;
    private JScrollPane peopleScroll;
    private JLayeredPane layeredPane;

    /**
     * sets up all panels and layouts
     * using built in layered panel from frame to allow for click and drag label
     */
    private void setup()
    {
        overallPanel = new JPanel();
        layeredPane = this.getLayeredPane();
        peoplePanel = new JPanel();
        buildingsPanel = new JPanel();
        buildingList = controller.getBuildingsList();
        buildingScroll = new JScrollPane(buildingList);
        infoPanel = new JPanel();
        BuildingListListener buildingListener = new BuildingListListener(this);
        BoxLayout infoGrid = new BoxLayout(infoPanel, BoxLayout.Y_AXIS);

        this.add(overallPanel);

        overallPanel.setLayout(new BorderLayout());
        peoplePanel.setLayout(new BorderLayout());
        buildingsPanel.setLayout(new BorderLayout());
        infoPanel.setLayout(infoGrid);

        peoplePanel.setBorder(BorderFactory.createLineBorder(Color.black));
        buildingsPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        infoPanel.setBorder(BorderFactory.createLineBorder(Color.black));

        overallPanel.add(peoplePanel, BorderLayout.SOUTH);
        overallPanel.add(buildingsPanel, BorderLayout.EAST);
        overallPanel.add(infoPanel, BorderLayout.CENTER);

        buildingList.addListSelectionListener(buildingListener);
        buildingList.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);
        buildingList.setFixedCellHeight(50);
        buildingList.setFixedCellWidth(100);
        buildingList.setBorder(new EmptyBorder(new Insets (0, 5,0,0)));
        infoPanel.setBorder(new EmptyBorder(new Insets(20,20,20,20)));

        buildingScroll.setHorizontalScrollBarPolicy( ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        buildingScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        buildingsPanel.add(buildingScroll, BorderLayout.CENTER);
        buildingScroll.setPreferredSize(new Dimension(120,667));
        buildingsPanel.setPreferredSize(new Dimension(120,667));

        //align all labels to center to avoid having them all in the upper left
        bName.setAlignmentX(Component.CENTER_ALIGNMENT);
        bAddr.setAlignmentX(Component.CENTER_ALIGNMENT);
        bOccupantCount.setAlignmentX(Component.CENTER_ALIGNMENT);
        policeNum.setAlignmentX(Component.CENTER_ALIGNMENT);
        studentNum.setAlignmentX(Component.CENTER_ALIGNMENT);
        teacherNum.setAlignmentX(Component.CENTER_ALIGNMENT);
        peopleNum.setAlignmentX(Component.CENTER_ALIGNMENT);
        spacer.setAlignmentX(Component.CENTER_ALIGNMENT);
        personType.setAlignmentX(Component.CENTER_ALIGNMENT);
        pName.setAlignmentX(Component.CENTER_ALIGNMENT);
        pAge.setAlignmentX(Component.CENTER_ALIGNMENT);
        pMoney.setAlignmentX(Component.CENTER_ALIGNMENT);
        pPhoneNumber.setAlignmentX(Component.CENTER_ALIGNMENT);
        tGradeLevel.setAlignmentX(Component.CENTER_ALIGNMENT);
        tCertification.setAlignmentX(Component.CENTER_ALIGNMENT);
        eId.setAlignmentX(Component.CENTER_ALIGNMENT);
        poRole.setAlignmentX(Component.CENTER_ALIGNMENT);
        kFavCandy.setAlignmentX(Component.CENTER_ALIGNMENT);

        //add all labels to infopane for later use
        infoPanel.add(bName);
        infoPanel.add(bAddr);
        infoPanel.add(studentNum);
        infoPanel.add(teacherNum);
        infoPanel.add(policeNum);
        infoPanel.add(peopleNum);
        infoPanel.add(bOccupantCount);
        infoPanel.add(spacer);
        infoPanel.add(personType);
        infoPanel.add(pName);
        infoPanel.add(pAge);
        infoPanel.add(pMoney);
        infoPanel.add(pPhoneNumber);
        infoPanel.add(tGradeLevel);
        infoPanel.add(tCertification);
        infoPanel.add(eId);
        infoPanel.add(poRole);
        infoPanel.add(kFavCandy);

        //set not visible until a person is clicked
        tGradeLevel.setVisible(false);
        tCertification.setVisible(false);
        eId.setVisible(false);
        poRole.setVisible(false);
        kFavCandy.setVisible(false);

        //setup for having name label show up while dragging a person between buildings
        layeredPane.add(mouseDragLabel, JLayeredPane.DRAG_LAYER);
        mouseDragLabel.setSize(100,100);
        mouseDragLabel.setVisible(false);
        mouseDragLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

    }

    /**
     * Constructor for the cityView
     * @param newController  city controller that view will ask for information
     */
    public CityView(CityController newController)
    {
        super();

        controller = newController;

        this.setSize(700,500);

        setup();

    }

    /**
     * handles pertinent mouse interraction with the builidng list
     */
    private class BuildingListListener implements ListSelectionListener
    {
        CityView view;

        /**
         * constructor for buildinglistlistener
         * @param nView cityview it will be working with
         */
        public BuildingListListener(CityView nView)
        {
            view = nView;
        }

        /**
         * handles when different element in the list is selected
         * @param e list selection event
         */
        @Override
        public void valueChanged(ListSelectionEvent e) {
            view.buildingSelectedChanged(e);
        }
    }

    /**
     * handles pertinent mouse interraction with person list
     * also handles all click and drag locations, since they are based on the list of origin not what they end over
     */
    private class PersonListListener extends MouseInputAdapter implements ListSelectionListener
    {
        CityView view;

        /**
         * constructor for personlistlistener
         * @param nView city view it will be working with
         */
        public PersonListListener(CityView nView)
        {
            view = nView;
        }

        /**
         * handles when different element in the list is selected
         * @param e list selection event
         */
        @Override
        public void valueChanged(ListSelectionEvent e) {
            view.personSelectedChanged(e);
        }

        /**
         * response to mouse being released after being dragged
         * @param e mouse event
         */
        @Override
        public void mouseReleased(MouseEvent e) {
            super.mouseReleased(e);
            int currentIndexMouseReleased = buildingOccupantList.locationToIndex(e.getPoint());
            view.mouseDragLabel.setVisible(false);
            view.buildingOccupantList.setEnabled(true);

            view.personClickReleased();
        }

        /**
         * mouse pressed event will activate this function and cause an update of the personclicked tracker to prep for a possible drag
         * @param e
         */
        @Override
        public void mousePressed(MouseEvent e) {
            super.mousePressed(e);
            int currentIndexMousePressed = buildingOccupantList.locationToIndex(e.getPoint());
            view.personClicked(currentIndexMousePressed);
        }

        /**
         * handles movement of the mouseDrag Label
         * sets occupant list to not allow for new selection during dragging
         * @param e
         */
        @Override
        public void mouseDragged(MouseEvent e) {
            super.mouseDragged(e);
            view.buildingOccupantList.setEnabled(false);
            view.mouseDragLabel.setText(view.currentPersonSelected.getName());
            Point mousePoint = view.getMousePosition();
            mousePoint.translate(-50,-50);
            view.mouseDragLabel.setLocation(mousePoint);
            view.mouseDragLabel.setVisible(true);
        }
    }

    /**
     * moves a person to a new building when called
     */
    public void personClickReleased()
    {
        Point mousePosition = buildingList.getMousePosition();
        if(mousePosition != null)
        {
            if(currentPersonSelected != null)
            {
                int destinationBuildingIndex = buildingList.locationToIndex(mousePosition);
                int originBuildingIndex = buildingList.getSelectedIndex();
                Building originBuilding = controller.getBuilding(originBuildingIndex);

                Building destinationBuilding = controller.getBuilding(destinationBuildingIndex);
                controller.moveOccupant(originBuilding, currentPersonSelected, destinationBuilding);
                buildingList.setSelectedIndex(destinationBuildingIndex);
            }
        }
    }

    /**
     * changes the displayed info on the infopanel
     * removes all currently displayed person info
     * changes building info to that of new selected building
     * @param e list selection event
     */
    public void buildingSelectedChanged(ListSelectionEvent e) {
        int index = buildingList.getSelectedIndex();

        if(!e.getValueIsAdjusting())
        {
            Building building = controller.getBuilding(index);
            displayBuildingInfo(building);
            setBuildingOccupantsList(building);


            pName.setVisible(false);
            pAge.setVisible(false);
            pPhoneNumber.setVisible(false);
            pMoney.setVisible(false);
            tGradeLevel.setVisible(false);
            tCertification.setVisible(false);
            eId.setVisible(false);
            poRole.setVisible(false);
            kFavCandy.setVisible(false);
            personType.setVisible(false);
        }
    }

    /**
     * setup of building occupants list,
     * needs to be called with each new building clicked on to update what person list is displayed
     * @param building
     */
    public void setBuildingOccupantsList(Building building)
    {
        PersonListListener personListener = new PersonListListener(this);
        buildingOccupantList = new JList(building.getOccupants());
        buildingOccupantList.addMouseListener(personListener);
        peoplePanel.removeAll();

        buildingOccupantList.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);
        buildingOccupantList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        buildingOccupantList.setVisibleRowCount(1);
        buildingOccupantList.addListSelectionListener(personListener);
        buildingOccupantList.addMouseMotionListener(personListener);
        buildingOccupantList.setFixedCellWidth(150);
        buildingOccupantList.setFixedCellHeight(65);
        //buildingOccupantList.setDragEnabled(true);

        peopleScroll = new JScrollPane(buildingOccupantList);

        peopleScroll.setHorizontalScrollBarPolicy( ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        peopleScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        peoplePanel.add(peopleScroll, BorderLayout.CENTER);
        peopleScroll.setPreferredSize(new Dimension(575, 75));
        peoplePanel.setPreferredSize(new Dimension(575,75));
        peopleScroll.setBorder(new EmptyBorder(new Insets(0,20,0,0)));
        peoplePanel.revalidate();
    }

    /**
     * sets info for JLabels pertaining to building
     * @param building
     */
    public void displayBuildingInfo(Building building)
    {
        bName.setText("Name: " + building.getName());
        bAddr.setText("Address: " + building.getAddress());
        bOccupantCount.setText("Total occupants: " + building.buildingOccupantCount());


        int policeCount = 0;
        int teacherCount = 0;
        int studentCount = 0;
        int peopleCount = 0;

        for(int i = 0; i<building.getOccupants().size(); i++)
        {
            if(building.getOccupant(i) instanceof Police)
            {
                policeCount++;
            }
            else if(building.getOccupant(i) instanceof Teacher)
            {
                teacherCount++;
            }
            else if(building.getOccupant(i) instanceof Kid)
            {
                studentCount++;
            }
            else
            {
                peopleCount++;
            }

        }

        if(building instanceof CityHall)
        {
            policeNum.setText("There are " + policeCount + " police in City Hall");
        }
        else if(building instanceof School)
        {
            teacherNum.setText("There are " + teacherCount + " teachers in " + building.getName());
            studentNum.setText("There are " + studentCount + " students in " + building.getName());
        }
        else
        {
            teacherNum.setText("There are " + teacherCount + " teachers in " + building.getName());
            studentNum.setText("There are " + studentCount + " students in " + building.getName());
            policeNum.setText("There are " + policeCount + " police in " + building.getName());
            peopleNum.setText("There are " + peopleCount + " other people in " + building.getName());
        }

        infoPanel.revalidate();
    }

    /**
     * sets person that is clicked so that other functions can transfer and display info about that person
     * @param personIndex index number in the person vector
     */
    public void personClicked(int personIndex)
    {
        int bIndex = buildingList.getSelectedIndex();
        Building building = controller.getBuilding(bIndex);
        currentPersonSelected  = building.getOccupant(personIndex);

    }

    /**
     * updates all labels so correct person info is displayed based on what type of person they are
     * info based on currently selected person
     * @param e
     */
    public void personSelectedChanged(ListSelectionEvent e)
    {
        int index = buildingOccupantList.getSelectedIndex();

        //check prevents double display of info
        if(!e.getValueIsAdjusting())
        {
            pName.setVisible(true);
            pAge.setVisible(true);
            pPhoneNumber.setVisible(true);
            pMoney.setVisible(true);
            personType.setVisible(true);
            int bIndex = buildingList.getSelectedIndex();
            Building building = controller.getBuilding(bIndex);
            Person person = building.getOccupant(index);

            if(person instanceof Teacher)
            {
                personType.setText("Person Info: TEACHER");
                displayTeacherExtraInfo((Teacher)person);

            }
            else if (person instanceof Police)
            {
                personType.setText("Person Info: POLICE OFFICER");
                displayPoliceExtraInfo((Police) person);
            }
            else if(person instanceof Kid)
            {
                personType.setText("Person Info: KID");
                displayKidExtraInfo((Kid) person);
            }
            else
            {
                personType.setVisible(false);
            }

            displayPersonInfo(person);
        }
    }

    /**
     * displays basic person info
     * @param person being looked at
     */
    public void displayPersonInfo(Person person)
    {
        pName.setText("Name: " + person.getName());
        pAge.setText("Age: " +  person.getAge());
        pPhoneNumber.setText("Phone number: " + person.getPhoneNumber());
        pMoney.setText("Total money: $" + person.getTotalMoney());
        //infoPanel.revalidate();
    }

    /**
     * sets labels visible/not to display teacher info, updates teacher related labels
     * @param person being looked at
     */
    public void displayTeacherExtraInfo(Teacher person)
    {
        tGradeLevel.setText("Grade level: " + person.getTeachingLevel());
        tCertification.setText("Certification level: " + person.getCertification());
        eId.setText("Employee ID: " + person.getEmployeeId());
        tGradeLevel.setVisible(true);
        tCertification.setVisible(true);
        eId.setVisible(true);

        poRole.setVisible(false);
        kFavCandy.setVisible(false);
    }

    /**
     * sets labels visible/not to display police info, updates police related labels
     * @param person being looked at
     */
    public void displayPoliceExtraInfo(Police person)
    {
        poRole.setText("Role: " + person.getPoliceRole());
        eId.setText("Employee ID: " + person.getEmployeeId());
        eId.setVisible(true);
        poRole.setVisible(true);

        tGradeLevel.setVisible(false);
        tCertification.setVisible(false);
        kFavCandy.setVisible(false);
    }

    /**
     * sets labels visible/not to display kid info, updates kid related labels
     * @param person being looked at
     */
    public void displayKidExtraInfo(Kid person)
    {
        kFavCandy.setText("Favorite candy: " + person.getFavCandy());
        kFavCandy.setVisible(true);

        eId.setVisible(false);
        poRole.setVisible(false);
        tGradeLevel.setVisible(false);
        tCertification.setVisible(false);
    }



}
