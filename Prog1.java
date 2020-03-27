import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Prog1 extends JFrame {

	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private Statement stmt = null;
	private ResultSet rs = null;

	private static final String UserName = "";
	private static final String PassWord = "";
	private static final String URL = "";

	// private static final String UserName = "";
	// private static final String PassWord = "";
	// private static final String URL = "";

	private Action action = new Action();

	private JPanel thisPanel;
	private JTextArea output = new JTextArea();
	private JScrollPane scrollPane = new JScrollPane(output);
	private JButton[] myUpdate = new JButton[2];
	private JButton logout = new JButton("logout");
	private JPanel backgroundPanel = new JPanel();

	private JTextField idText;
	private JPasswordField pwText;
	private JButton login;
	private JButton initialization;

	private int updateFlag;

	private int mySnumber;
	private JLabel[] studentLabel = new JLabel[8];
	private JTextField[] studentField = new JTextField[8];
	private JButton showApplicationLecture = new JButton("All Lecture");
	private JButton myCourse = new JButton("<html>my Course<br>List</html>");
	private JTextField myCourseField = new JTextField();
	private JTextField myProfessorField = new JTextField();
	private JButton insertCourse = new JButton("Insert");
	private JButton deleteCourse = new JButton("Delete");

	private int myPnumber;
	private JLabel[] professorLabel = new JLabel[7];
	private JTextField[] professorField = new JTextField[7];
	private JButton myLectures = new JButton("my Course");
	private JButton myStudents = new JButton("<html>Manage<br>Student</html>");
	private JButton studentScore = new JButton("<html>Student<br>Score</html>");
	private JPanel scorePanel = new JPanel();
	private JTextField[] scoreField = new JTextField[6];
	private JButton scoreUpdate = new JButton("Update");

	private JButton[] mButton = new JButton[6];
	private JButton back = new JButton("Back");
	private JButton initData;

	private JButton showDepartment = new JButton("<html>All<br>Department</html>");
	private JButton departmentPerson = new JButton("<html>Department<br>Person</html>");
	private JRadioButton[] departmentRadio = new JRadioButton[3];
	private JLabel[] departmentLabel = new JLabel[3];
	private JTextField[] departmentField = new JTextField[3];
	private JButton departmentInsert = new JButton("Insert");
	private JButton departmentUpdate = new JButton("Update");
	private JButton departmentDelete = new JButton("Delete");

	private JButton showProfessor = new JButton("<html>All<br>Professor</html>");
	private ButtonGroup professorGroup = new ButtonGroup();
	private JRadioButton[] professorRadio = new JRadioButton[3];
	private JLabel[] mprofessorLabel = new JLabel[8];
	private JTextField[] mprofessorField = new JTextField[8];
	private JButton professorInsert = new JButton("Insert");
	private JButton professorUpdate = new JButton("Update");
	private JButton professorDelete = new JButton("Delete");

	private JButton showStudent = new JButton("<html>All<br>Student</html>");
	private JButton totalStudentCredit = new JButton("<html>Student<br>Credit</html>");
	private ButtonGroup studentGroup = new ButtonGroup();
	private JRadioButton[] studentRadio = new JRadioButton[3];
	private JLabel[] mstudentLabel = new JLabel[9];
	private JTextField[] mstudentField = new JTextField[9];
	private JButton studentInsert = new JButton("Insert");
	private JButton studentUpdate = new JButton("Update");
	private JButton studentDelete = new JButton("Delete");

	private JButton showLecture = new JButton("<html>All<br>Lecture</html>");
	private ButtonGroup lectureGroup = new ButtonGroup();
	private JRadioButton[] lectureRadio = new JRadioButton[3];
	private JLabel[] lectureLabel = new JLabel[7];
	private JTextField[] lectureField = new JTextField[7];
	private JButton lectureInsert = new JButton("Insert");
	private JButton lectureUpdate = new JButton("Update");
	private JButton lectureDelete = new JButton("Delete");

	private JButton showCourse = new JButton("<html>All<br>Course</html>");
	private JButton resultCourse = new JButton("<html>Course<br>Result</html>");
	private ButtonGroup courseGroup = new ButtonGroup();
	private JRadioButton[] courseRadio = new JRadioButton[3];
	private JLabel[] courseLabel = new JLabel[9];
	private JTextField[] courseField = new JTextField[9];
	private JButton courseInsert = new JButton("Insert");
	private JButton courseUpdate = new JButton("Update");
	private JButton courseDelete = new JButton("Delete");

	private JButton showSupervision = new JButton("Term");
	private JButton termStudent = new JButton("<html>Term<br>Student</html>");
	private ButtonGroup supervisionGroup = new ButtonGroup();
	private JRadioButton[] supervisionRadio = new JRadioButton[3];
	private JLabel[] supervisionLabel = new JLabel[3];
	private JTextField[] supervisionField = new JTextField[3];
	private JButton supervisionInsert = new JButton("Insert");
	private JButton supervisionUpdate = new JButton("Update");
	private JButton supervisionDelete = new JButton("Delete");

	public static void main(String[] args) {
		new Prog1();
	}

	public Prog1() {
		new Login();
	}

	public void connectDB() {
		try {
			// Class.forName("oracle.jdbc.driver.OracleDriver");
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(URL, UserName, PassWord);
		} catch (ClassNotFoundException e) {
			System.out.println("Fail DB Driver loading :" + e.toString());
		} catch (SQLException e) {
			System.out.println("DB Fail connection : " + e.toString());
		} catch (Exception e) {
			System.out.println("Unkonwn error");
			e.printStackTrace();
		}
	}

	public void closeDB() {
		try {
			pstmt.close();
			rs.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public class DepartmentStructor {
		private int dNumber;
		private String dName;
		private String dPhone;

		private int cntStudent; // group by
		private int cntProfessor; // group by

		public int getDnumber() {
			return dNumber;
		}

		public String getDname() {
			return dName;
		}

		public String getDphone() {
			return dPhone;
		}

		public int getCntstudent() {
			return cntStudent;
		}

		public int getCntprofessor() {
			return cntProfessor;
		}

		public void setDnumber(int number) {
			dNumber = number;
		}

		public void setDname(String string) {
			dName = string;
		}

		public void setDphone(String string) {
			dPhone = string;
		}

		public void setCntstudent(int number) {
			cntStudent = number;
		}

		public void setCntprofessor(int number) {
			cntProfessor = number;
		}

	}

	public class ProfessorStructor {
		private int pNumber;
		private String pName;
		private String pCivilnumber;
		private String pAddress;
		private String pPhone;
		private String pEmail;
		private int pPicture;
		private int pDepartment;

		public int getPnumber() {
			return pNumber;
		}

		public String getPname() {
			return pName;
		}

		public String getPcivilnumber() {
			return pCivilnumber;
		}

		public String getPaddress() {
			return pAddress;
		}

		public String getPphone() {
			return pPhone;
		}

		public String getPemail() {
			return pEmail;
		}

		public int getPpicture() {
			return pPicture;
		}

		public int getPdepartment() {
			return pDepartment;
		}

		public void setPnumber(int number) {
			pNumber = number;
		}

		public void setPname(String name) {
			pName = name;
		}

		public void setPcivilnumber(String string) {
			pCivilnumber = string;
		}

		public void setPaddress(String string) {
			pAddress = string;
		}

		public void setPphone(String string) {
			pPhone = string;
		}

		public void setPemail(String string) {
			pEmail = string;
		}

		public void setPpicture(int number) {
			pPicture = number;
		}

		public void setPdepartment(int number) {
			pDepartment = number;
		}
	}

	public class StudentStructor {
		private int sNumber;
		private String sName;
		private String sCivilnumber;
		private String sAddress;
		private String sPhone;
		private String sEmail;
		private int sPicture;
		private int sDepartment;
		private int sProfessor;

		private int totalCredit; // group by

		public int getSnumber() {
			return sNumber;
		}

		public String getSname() {
			return sName;
		}

		public String getScivilnumber() {
			return sCivilnumber;
		}

		public String getSaddress() {
			return sAddress;
		}

		public String getSphone() {
			return sPhone;
		}

		public String getSemail() {
			return sEmail;
		}

		public int getSpicture() {
			return sPicture;
		}

		public int getSdepartment() {
			return sDepartment;
		}

		public int getSprofessor() {
			return sProfessor;
		}

		public int getTotalcredit() {
			return totalCredit;
		}

		public void setSnumber(int number) {
			sNumber = number;
		}

		public void setSname(String string) {
			sName = string;
		}

		public void setScivilnumber(String string) {
			sCivilnumber = string;
		}

		public void setSaddress(String string) {
			sAddress = string;
		}

		public void setSphone(String string) {
			sPhone = string;
		}

		public void setSemail(String string) {
			sEmail = string;
		}

		public void setSpicture(int number) {
			sPicture = number;
		}

		public void setSdepartment(int number) {
			sDepartment = number;
		}

		public void setSprofessor(int number) {
			sProfessor = number;
		}

		public void setTotalcredit(int number) {
			totalCredit = number;
		}
	}

	public class LectureStructor {
		private int lNumber;
		private int lProfessor;
		private String lName;
		private int lCredit;
		private String lTime;
		private String lPlace;
		private int lDepartment;

		public ProfessorStructor p;
		public DepartmentStructor d;

		public int getLnumber() {
			return lNumber;
		}

		public int getLprofessor() {
			return lProfessor;
		}

		public String getLname() {
			return lName;
		}

		public int getLcredit() {
			return lCredit;
		}

		public String getLtime() {
			return lTime;
		}

		public String getLplace() {
			return lPlace;
		}

		public int getLdepartment() {
			return lDepartment;
		}

		public void setLnumber(int number) {
			lNumber = number;
		}

		public void setLprofessor(int number) {
			lProfessor = number;
		}

		public void setLname(String string) {
			lName = string;
		}

		public void setLcredit(int number) {
			lCredit = number;
		}

		public void setLtime(String string) {
			lTime = string;
		}

		public void setLplace(String string) {
			lPlace = string;
		}

		public void setLdepartment(int number) {
			lDepartment = number;
		}
	}

	public class CourseStructor {
		private int cStudent;
		private int cProfessor;
		private int cLecture;
		private int cMiddle;
		private int cFinal;
		private int cOthers;
		private int cAttendance;
		private int cTotal;
		private String cGrade;

		public ProfessorStructor p;
		public LectureStructor l;

		public DepartmentStructor d;
		public StudentStructor s;

		private float avgTotal; // group by
		private int cntStudent; // group by

		public int getCstudent() {
			return cStudent;
		}

		public int getCprofessor() {
			return cProfessor;
		}

		public int getClecture() {
			return cLecture;
		}

		public int getCmiddle() {
			return cMiddle;
		}

		public int getCfinal() {
			return cFinal;
		}

		public int getCothers() {
			return cOthers;
		}

		public int getCattendance() {
			return cAttendance;
		}

		public int getCtotal() {
			return cTotal;
		}

		public String getCgrade() {
			return cGrade;
		}

		public float getAvgtotal() {
			return avgTotal;
		}

		public int getCntstudent() {
			return cntStudent;
		}

		public void setCstudent(int number) {
			cStudent = number;
		}

		public void setCprofessor(int number) {
			cProfessor = number;
		}

		public void setClecture(int number) {
			cLecture = number;
		}

		public void setCmiddle(int number) {
			cMiddle = number;
		}

		public void setCfinal(int number) {
			cFinal = number;
		}

		public void setCothers(int number) {
			cOthers = number;
		}

		public void setCattendance(int number) {
			cAttendance = number;
		}

		public void setCtotal(int number) {
			cTotal = number;
		}

		public void setCgrade(String string) {
			cGrade = string;
		}

		public void setAvgtotal(float f) {
			avgTotal = f;
		}

		public void setCntstudent(int number) {
			cntStudent = number;
		}
	}

	public class SupervisionStructor {
		private int superStudent;
		private int superProfessor;
		private String superTerm;

		private int cntStudent;

		public int getSuperstudent() {
			return superStudent;
		}

		public int getSuperprofessor() {
			return superProfessor;
		}

		public String getSuperterm() {
			return superTerm;
		}

		public int getCntstudent() {
			return cntStudent;
		}

		public void setSuperstudent(int number) {
			superStudent = number;
		}

		public void setSuperprofessor(int number) {
			superProfessor = number;
		}

		public void setSuperterm(String string) {
			superTerm = string;
		}

		public void setCntstudent(int number) {
			cntStudent = number;
		}
	}

	public void setComponent(JTextField[] changeField, int flag) {
		boolean condition;
		String str;
		if (flag == 0) {
			condition = false;
			str = "#ffffff";
		}

		else {
			condition = true;
			str = "#ffff00";
		}
		for (int index = 3; index <= 5; index++) {
			changeField[index].setBackground(Color.decode(str));
			changeField[index].setEnabled(condition);
		}
	}

	public void titleLabel(JPanel panel, String string) {
		JLabel label = new JLabel(string);
		label.setBounds(10, 10, 200, 50);
		if (string.equals("Supervision") || string.equals("Department"))
			label.setBounds(10, 10, 230, 50);
		label.setFont(new Font(null, Font.BOLD, 30));
		panel.add(label);
	}

	public void guideKey(JPanel panel) {
		JLabel[] colorLabel = new JLabel[6];
		colorLabel[0] = new JLabel();
		colorLabel[0].setBackground(Color.green);
		colorLabel[1] = new JLabel(" : PK");

		colorLabel[2] = new JLabel();
		colorLabel[2].setBackground(Color.yellow);
		colorLabel[3] = new JLabel(" : PK + FK");

		colorLabel[4] = new JLabel();
		colorLabel[4].setBackground(Color.red);
		colorLabel[5] = new JLabel(" : FK");

		for (int i = 0; i < 6; i++) {
			colorLabel[i].setBounds(10 + 50 * (i % 2), 60 + 30 * (i / 2), 50, 20);
			if (i % 2 == 0)
				colorLabel[i].setOpaque(true);
			panel.add(colorLabel[i]);
		}
		colorLabel[3].setBounds(60, 90, 100, 20);
	}

	public void radioButtonSetting(JPanel panel, JRadioButton[] radio) {
		ButtonGroup btnGroup = new ButtonGroup();
		String[] radio_text = { "Insert", "Update", "Delete" };
		for (int i = 0; i < radio.length; i++) {
			radio[i] = new JRadioButton(radio_text[i]);
			radio[i].setBounds(250 + i * 80, 40, 80, 30);
			radio[i].addActionListener(action);
			btnGroup.add(radio[i]);
			panel.add(radio[i]);
		}
		radio[0].setSelected(true);
	}

	public void defaultUISetting(int x, int y) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(x, y));
		setLayout(null);

		backgroundPanel.setBounds(0, 0, x, y);
		backgroundPanel.setBackground(Color.black);
		backgroundPanel.setLayout(null);
		add(backgroundPanel);
	}

	public void myInformation1() {
		StudentStructor s = new StudentStructor();
		s.setSaddress(studentField[3].getText());
		s.setSphone(studentField[4].getText());
		s.setSemail(studentField[5].getText());

		connectDB();
		String sql = "update Student set sAddress = ? , sPhone = ?, sEmail= ? where sNumber = ?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, s.getSaddress());
			pstmt.setString(2, s.getSphone());
			pstmt.setString(3, s.getSemail());
			pstmt.setInt(4, mySnumber);

			pstmt.executeUpdate();
		} catch (Exception e) {

		}
		closeDB();
	}

	public void myInformation2() {
		ProfessorStructor p = new ProfessorStructor();
		p.setPaddress(professorField[3].getText());
		p.setPphone(professorField[4].getText());
		p.setPemail(professorField[5].getText());

		connectDB();
		String sql = "update Professor set pAddress = ? , pPhone = ?, pEmail= ? where pNumber = ?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, p.getPaddress());
			pstmt.setString(2, p.getPphone());
			pstmt.setString(3, p.getPemail());
			pstmt.setInt(4, myPnumber);

			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		closeDB();
	}

	public class Login {
		public Login() {
			defaultUISetting(500, 350);
			setTitle("Student Information System");

			JPanel loginPanel = new JPanel();
			thisPanel = loginPanel;
			loginPanel.setBounds(10, 10, 475, 297);
			loginPanel.setBackground(Color.lightGray);
			loginPanel.setLayout(null);
			backgroundPanel.add(loginPanel);

			JLabel title = new JLabel("<html><pre>Student<br>  Information<br>    Sysmtem</pre></html> ");
			title.setFont(new Font(null, Font.BOLD, 30));
			title.setBounds(100, 20, 300, 130);
			loginPanel.add(title);

			JLabel idLabel = new JLabel("I D : ");
			idLabel.setBounds(110, 170, 50, 20);
			loginPanel.add(idLabel);

			JLabel pwLabel = new JLabel("PassWord : ");
			pwLabel.setBounds(84, 200, 100, 20);
			loginPanel.add(pwLabel);

			idText = new JTextField();
			idText.setBounds(180, 170, 100, 20);
			loginPanel.add(idText);

			pwText = new JPasswordField();
			pwText.setBounds(180, 200, 100, 20);
			loginPanel.add(pwText);

			login = new JButton("Login");
			login.setBounds(290, 170, 80, 50);
			login.addActionListener(action);
			loginPanel.add(login);

			initialization = new JButton("Init");
			initialization.setBounds(290, 230, 80, 30);
			initialization.addActionListener(action);
			loginPanel.add(initialization);

			setResizable(false);
			pack();
			setVisible(true);
		}
	}

	public class Student {
		public Student(int number) {
			mySnumber = number;
			connectDB();
			String sql = "select * from Student where sNumber = ?";
			StudentStructor s = null;

			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, number);
				rs = pstmt.executeQuery();

				rs.next();
				s = new StudentStructor();
				s.setSnumber(rs.getInt("sNumber"));
				s.setSname(rs.getString("sName"));
				s.setScivilnumber(rs.getString("sCivilnumber"));
				s.setSaddress(rs.getString("sAddress"));
				s.setSphone(rs.getString("sPhone"));
				s.setSemail(rs.getString("sEmail"));
				s.setSpicture(rs.getInt("sPicture"));
				s.setSdepartment(rs.getInt("sDepartment"));
				s.setSprofessor(rs.getInt("sProfessor"));
			} catch (Exception e) {
				s = null;
			}
			closeDB();

			defaultUISetting(1000, 700);
			setTitle("Student Information System - Welcome " + s.getSname() + " Student");

			JPanel studentPanel = new JPanel();
			thisPanel = studentPanel;
			studentPanel.setBounds(10, 10, 975, 645);
			studentPanel.setBackground(Color.lightGray);
			studentPanel.setLayout(null);
			backgroundPanel.add(studentPanel);

			studentLabel[0] = new JLabel("Number : ");
			studentLabel[0].setBounds(10, 10, 60, 30);
			studentField[0] = new JTextField(Integer.toString(s.getSnumber()));
			studentField[0].setBounds(80, 10, 120, 30);

			studentLabel[1] = new JLabel("Name : ");
			studentLabel[1].setBounds(225, 10, 50, 30);
			studentField[1] = new JTextField(s.getSname());
			studentField[1].setBounds(275, 10, 150, 30);

			studentLabel[2] = new JLabel("Civil : ");
			studentLabel[2].setBounds(450, 10, 70, 30);
			studentField[2] = new JTextField(s.getScivilnumber());
			studentField[2].setBounds(520, 10, 120, 30);

			studentLabel[3] = new JLabel("ADD.  : ");
			studentLabel[3].setBounds(10, 90, 50, 30);
			studentField[3] = new JTextField(s.getSaddress());
			studentField[3].setBounds(80, 90, 345, 30);

			studentLabel[4] = new JLabel("Phone : ");
			studentLabel[4].setBounds(10, 50, 70, 30);
			studentField[4] = new JTextField(s.getSphone());
			studentField[4].setBounds(80, 50, 120, 30);

			studentLabel[5] = new JLabel("Mail : ");
			studentLabel[5].setBounds(225, 50, 50, 30);
			studentField[5] = new JTextField(s.getSemail());
			studentField[5].setBounds(275, 50, 150, 30);

			studentLabel[6] = new JLabel("Dep. : ");
			studentLabel[6].setBounds(450, 50, 50, 30);
			studentField[6] = new JTextField(Integer.toString(s.getSdepartment()));
			studentField[6].setBounds(520, 50, 120, 30);

			studentLabel[7] = new JLabel("Pro. : ");
			studentLabel[7].setBounds(450, 90, 70, 30);
			studentField[7] = new JTextField(Integer.toString(s.getSprofessor()));
			studentField[7].setBounds(520, 90, 120, 30);

			for (int i = 0; i < 8; i++) {
				studentField[i].setEnabled(false);
				studentPanel.add(studentLabel[i]);
				studentPanel.add(studentField[i]);
			}

			showApplicationLecture.setBounds(10, 150, 130, 40);
			showApplicationLecture.addActionListener(action);
			studentPanel.add(showApplicationLecture);

			myCourse.setBounds(150, 150, 130, 40);
			myCourse.addActionListener(action);
			studentPanel.add(myCourse);

			JLabel courseLabel = new JLabel("Lec. : ");
			courseLabel.setBounds(450, 150, 70, 30);
			studentPanel.add(courseLabel);

			myCourseField.setBounds(520, 150, 120, 30);
			studentPanel.add(myCourseField);

			JLabel professorLabel = new JLabel("Pro. : ");
			professorLabel.setBounds(660, 150, 70, 30);
			studentPanel.add(professorLabel);

			myProfessorField.setBounds(730, 150, 130, 30);
			studentPanel.add(myProfessorField);

			insertCourse.setBounds(870, 150, 100, 30);
			insertCourse.addActionListener(action);
			studentPanel.add(insertCourse);

			deleteCourse.setBounds(870, 150, 100, 30);
			deleteCourse.addActionListener(action);
			studentPanel.add(deleteCourse);

			scrollPane.setBounds(10, 200, 955, 435);
			studentPanel.add(scrollPane);

			updateFlag = 0;
			myUpdate[0] = new JButton("<html>my<br>Information</html>");
			myUpdate[0].setBounds(730, 10, 130, 40);
			myUpdate[0].addActionListener(action);
			studentPanel.add(myUpdate[0]);

			logout.setBounds(870, 10, 100, 40);
			logout.addActionListener(action);
			studentPanel.add(logout);

			showApplicationLecture.doClick();

			setResizable(false);
			pack();
			setVisible(true);
		}
	}

	public class Professor {
		public Professor(int number) {
			myPnumber = number;
			connectDB();
			String sql = "select * from Professor where pNumber = ?";
			ProfessorStructor p = null;

			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, number);
				rs = pstmt.executeQuery();

				rs.next();
				p = new ProfessorStructor();
				p.setPnumber(rs.getInt("pNumber"));
				p.setPname(rs.getString("pName"));
				p.setPcivilnumber(rs.getString("pCivilnumber"));
				p.setPaddress(rs.getString("pAddress"));
				p.setPphone(rs.getString("pPhone"));
				p.setPemail(rs.getString("pEmail"));
				p.setPpicture(rs.getInt("pPicture"));
				p.setPdepartment(rs.getInt("pDepartment"));
			} catch (Exception e) {
				p = null;
			}
			closeDB();

			defaultUISetting(1000, 700);
			setTitle("Student Information system - Welcome " + p.getPname() + " Professor");

			JPanel professorPanel = new JPanel();
			thisPanel = professorPanel;
			professorPanel.setBounds(10, 10, 975, 645);
			professorPanel.setBackground(Color.lightGray);
			professorPanel.setLayout(null);
			backgroundPanel.add(professorPanel);

			professorLabel[0] = new JLabel("Number  : ");
			professorLabel[0].setBounds(10, 10, 60, 30);
			professorField[0] = new JTextField(Integer.toString(p.getPnumber()));
			professorField[0].setBounds(80, 10, 120, 30);

			professorLabel[1] = new JLabel("Name  : ");
			professorLabel[1].setBounds(225, 10, 50, 30);
			professorField[1] = new JTextField(p.getPname());
			professorField[1].setBounds(275, 10, 150, 30);

			professorLabel[2] = new JLabel("Civil  : ");
			professorLabel[2].setBounds(450, 10, 70, 30);
			professorField[2] = new JTextField(p.getPcivilnumber());
			professorField[2].setBounds(520, 10, 120, 30);

			professorLabel[3] = new JLabel("ADD.  : ");
			professorLabel[3].setBounds(10, 90, 50, 30);
			professorField[3] = new JTextField(p.getPaddress());
			professorField[3].setBounds(80, 90, 345, 30);

			professorLabel[4] = new JLabel("Phone : ");
			professorLabel[4].setBounds(10, 50, 70, 30);
			professorField[4] = new JTextField(p.getPphone());
			professorField[4].setBounds(80, 50, 120, 30);

			professorLabel[5] = new JLabel("Mail : ");
			professorLabel[5].setBounds(225, 50, 50, 30);
			professorField[5] = new JTextField(p.getPemail());
			professorField[5].setBounds(275, 50, 150, 30);

			professorLabel[6] = new JLabel("Dep. : ");
			professorLabel[6].setBounds(450, 50, 50, 30);
			professorField[6] = new JTextField(Integer.toString(p.getPdepartment()));
			professorField[6].setBounds(520, 50, 120, 30);

			for (int i = 0; i < 7; i++) {
				professorField[i].setEnabled(false);
				professorPanel.add(professorLabel[i]);
				professorPanel.add(professorField[i]);
			}

			myLectures.setBounds(10, 150, 130, 40);
			myLectures.addActionListener(action);
			professorPanel.add(myLectures);

			myStudents.setBounds(150, 150, 130, 40);
			myStudents.addActionListener(action);
			professorPanel.add(myStudents);

			studentScore.setBounds(290, 150, 130, 40);
			studentScore.addActionListener(action);
			professorPanel.add(studentScore);

			scorePanel.setLayout(null);
			scorePanel.setBounds(450, 90, 410, 105);
			scorePanel.setBackground(Color.gray);
			scorePanel.setVisible(false);
			professorPanel.add(scorePanel);

			JLabel[] scoreLabel = new JLabel[6];

			scoreLabel[0] = new JLabel("sName : ");
			scoreLabel[0].setBounds(10, 10, 70, 25);

			scoreField[0] = new JTextField();
			scoreField[0].setBounds(80, 10, 100, 25);

			scoreLabel[1] = new JLabel("lName : ");
			scoreLabel[1].setBounds(190, 10, 50, 25);

			scoreField[1] = new JTextField();
			scoreField[1].setBounds(240, 10, 100, 25);

			scoreLabel[2] = new JLabel("Midde : ");
			scoreLabel[2].setBounds(10, 40, 70, 25);

			scoreField[2] = new JTextField();
			scoreField[2].setBounds(80, 40, 50, 25);

			scoreLabel[3] = new JLabel("Final : ");
			scoreLabel[3].setBounds(190, 40, 50, 25);

			scoreField[3] = new JTextField();
			scoreField[3].setBounds(240, 40, 50, 25);

			scoreLabel[4] = new JLabel("Others : ");
			scoreLabel[4].setBounds(10, 70, 70, 25);

			scoreField[4] = new JTextField();
			scoreField[4].setBounds(80, 70, 50, 25);

			scoreLabel[5] = new JLabel("Att. : ");
			scoreLabel[5].setBounds(190, 70, 50, 25);

			scoreField[5] = new JTextField();
			scoreField[5].setBounds(240, 70, 50, 25);

			for (int i = 0; i < 6; i++) {
				scorePanel.add(scoreLabel[i]);
				scorePanel.add(scoreField[i]);
			}

			scoreUpdate.setBounds(300, 40, 90, 55);
			scoreUpdate.addActionListener(action);
			scorePanel.add(scoreUpdate);

			scrollPane.setBounds(10, 200, 955, 435);
			professorPanel.add(scrollPane);

			updateFlag = 0;
			myUpdate[1] = new JButton("<html>My<br>Information</html>");
			myUpdate[1].setBounds(730, 10, 130, 40);
			myUpdate[1].addActionListener(action);
			professorPanel.add(myUpdate[1]);

			logout.setBounds(870, 10, 100, 40);
			logout.addActionListener(action);
			professorPanel.add(logout);

			setResizable(false);
			pack();
			setVisible(true);

			myLectures.doClick();
		}
	}

	public class Manager {
		public Manager() {
			defaultUISetting(300, 380);
			setTitle("Manager");

			JPanel managerPanel = new JPanel();
			thisPanel = managerPanel;
			managerPanel.setBounds(10, 10, 275, 325);
			managerPanel.setBackground(Color.lightGray);
			managerPanel.setLayout(null);
			backgroundPanel.add(managerPanel);

			initData = new JButton("Data Init");
			initData.setBounds(18, 10, 130, 40);
			initData.addActionListener(action);
			managerPanel.add(initData);

			String s[] = { "Department", "Professor", "Student", "Lecture", "Course", "Supervision" };
			for (int i = 0; i < mButton.length; i++) {
				mButton[i] = new JButton(s[i]);
				mButton[i].setBounds(18 + 120 * (i % 2), 150 + 50 * (i / 2), 120, 40);
				mButton[i].addActionListener(action);
				managerPanel.add(mButton[i]);
			}

			logout.setBounds(150, 10, 108, 40);
			logout.addActionListener(action);
			managerPanel.add(logout);

			setResizable(false);
			pack();
			setVisible(true);
		}
	}

	public class ManageDepartment {
		public ManageDepartment(boolean isManager) {
			defaultUISetting(1000, 700);

			JPanel departmentPanel = new JPanel();
			thisPanel = departmentPanel;
			departmentPanel.setBounds(10, 10, 975, 645);
			departmentPanel.setBackground(Color.lightGray);
			departmentPanel.setLayout(null);
			backgroundPanel.add(departmentPanel);

			titleLabel(thisPanel, "Department");
			guideKey(thisPanel);

			showDepartment.setBounds(10, 150, 120, 40);
			showDepartment.addActionListener(action);
			departmentPanel.add(showDepartment);

			radioButtonSetting(thisPanel, departmentRadio);

			departmentPerson.setBounds(140, 150, 145, 40);
			departmentPerson.addActionListener(action);
			departmentPanel.add(departmentPerson);

			String[] s = { "dNumber : ", "dName : ", "pPhone : " };
			for (int i = 0; i < s.length; i++) {
				departmentLabel[i] = new JLabel(s[i]);
				departmentLabel[i].setBounds(250 + i * 190, 80, 70, 30);
				departmentPanel.add(departmentLabel[i]);

				departmentField[i] = new JTextField();
				departmentField[i].setBounds(320 + i * 190, 80, 100, 30);
				departmentPanel.add(departmentField[i]);
			}
			departmentField[0].setBackground(Color.green);

			departmentInsert = new JButton("Insert");
			departmentInsert.setBounds(820, 80, 100, 30);
			departmentInsert.addActionListener(action);
			departmentPanel.add(departmentInsert);

			departmentUpdate = new JButton("Update");
			departmentUpdate.setBounds(820, 80, 100, 30);
			departmentUpdate.addActionListener(action);
			departmentUpdate.setVisible(false);
			departmentPanel.add(departmentUpdate);

			departmentDelete = new JButton("Delete");
			departmentDelete.setBounds(440, 80, 100, 30);
			departmentDelete.addActionListener(action);
			departmentDelete.setVisible(false);
			departmentPanel.add(departmentDelete);

			scrollPane.setBounds(10, 200, 955, 435);
			departmentPanel.add(scrollPane);

			back.setBounds(760, 10, 100, 40);
			back.addActionListener(action);
			departmentPanel.add(back);

			logout.setBounds(870, 10, 100, 40);
			logout.addActionListener(action);
			departmentPanel.add(logout);

			setResizable(false);
			pack();
			setVisible(true);

			showDepartment.doClick();
		}
	}

	public class ManageProfessor {
		public ManageProfessor(boolean isManager) {
			defaultUISetting(1000, 700);

			JPanel professorPanel = new JPanel();
			thisPanel = professorPanel;
			professorPanel.setBounds(10, 10, 975, 645);
			professorPanel.setBackground(Color.lightGray);
			professorPanel.setLayout(null);
			backgroundPanel.add(professorPanel);

			titleLabel(thisPanel, "Professor");
			guideKey(thisPanel);

			showProfessor.setBounds(10, 150, 140, 40);
			showProfessor.addActionListener(action);
			professorPanel.add(showProfessor);

			radioButtonSetting(thisPanel, professorRadio);

			String[] s = { "pNumber : ", "pName : ", "pCivil : ", "pADD. : ", "pPhone : ", "pEmail : ", "pPic. : ", "pDep. : " };
			for (int i = 0; i < s.length; i++) {
				mprofessorLabel[i] = new JLabel(s[i]);
				mprofessorLabel[i].setBounds(250 + (i % 3) * 190, 80 + 40 * (i / 3), 70, 30);
				professorPanel.add(mprofessorLabel[i]);

				mprofessorField[i] = new JTextField();
				mprofessorField[i].setBounds(320 + (i % 3) * 190, 80 + 40 * (i / 3), 100, 30);
				professorPanel.add(mprofessorField[i]);
			}
			mprofessorField[0].setBackground(Color.green);
			mprofessorField[7].setBackground(Color.red);

			professorInsert = new JButton("Insert");
			professorInsert.setBounds(820, 80, 100, 30);
			professorInsert.addActionListener(action);
			professorPanel.add(professorInsert);

			professorUpdate = new JButton("Update");
			professorUpdate.setBounds(820, 80, 100, 30);
			professorUpdate.addActionListener(action);
			professorUpdate.setVisible(false);
			professorPanel.add(professorUpdate);

			professorDelete = new JButton("Delete");
			professorDelete.setBounds(440, 80, 100, 30);
			professorDelete.addActionListener(action);
			professorDelete.setVisible(false);
			professorPanel.add(professorDelete);

			JLabel SulMyeongChoong = new JLabel("");
			SulMyeongChoong.setBounds(290, 130, 360, 60);
			SulMyeongChoong.setBackground(Color.blue);
			professorPanel.add(SulMyeongChoong);

			scrollPane.setBounds(10, 200, 955, 435);
			professorPanel.add(scrollPane);

			back.setBounds(760, 10, 100, 40);
			back.addActionListener(action);
			professorPanel.add(back);

			logout.setBounds(870, 10, 100, 40);
			logout.addActionListener(action);
			professorPanel.add(logout);

			setResizable(false);
			pack();
			setVisible(true);

			showProfessor.doClick();
		}
	}

	public class ManageStudent {
		public ManageStudent(boolean isManager) {
			defaultUISetting(1000, 700);

			JPanel studentPanel = new JPanel();
			thisPanel = studentPanel;
			studentPanel.setBounds(10, 10, 975, 645);
			studentPanel.setBackground(Color.lightGray);
			studentPanel.setLayout(null);
			backgroundPanel.add(studentPanel);

			titleLabel(thisPanel, "Student");
			guideKey(thisPanel);

			showStudent.setBounds(10, 150, 100, 40);
			showStudent.addActionListener(action);
			studentPanel.add(showStudent);

			totalStudentCredit.setBounds(120, 150, 120, 40);
			totalStudentCredit.addActionListener(action);
			studentPanel.add(totalStudentCredit);

			radioButtonSetting(thisPanel, studentRadio);

			String[] s = { "sNumber : ", "sName : ", "sCivil : ", "sADD. : ", "sPhone : ", "sEmail : ", "sPic. : ", "sDep. : ", "sPro. : " };
			for (int i = 0; i < s.length; i++) {
				mstudentLabel[i] = new JLabel(s[i]);
				mstudentLabel[i].setBounds(250 + (i % 3) * 190, 80 + 40 * (i / 3), 70, 30);
				studentPanel.add(mstudentLabel[i]);

				mstudentField[i] = new JTextField();
				mstudentField[i].setBounds(320 + (i % 3) * 190, 80 + 40 * (i / 3), 100, 30);
				studentPanel.add(mstudentField[i]);
			}
			mstudentField[0].setBackground(Color.green);
			mstudentField[7].setBackground(Color.red);
			mstudentField[8].setBackground(Color.red);

			studentInsert = new JButton("Insert");
			studentInsert.setBounds(820, 80, 100, 30);
			studentInsert.addActionListener(action);
			studentPanel.add(studentInsert);

			studentUpdate = new JButton("Update");
			studentUpdate.setBounds(820, 80, 100, 30);
			studentUpdate.addActionListener(action);
			studentUpdate.setVisible(false);
			studentPanel.add(studentUpdate);

			studentDelete = new JButton("Delete");
			studentDelete.setBounds(440, 80, 100, 30);
			studentDelete.addActionListener(action);
			studentDelete.setVisible(false);
			studentPanel.add(studentDelete);

			JLabel SulMyeongChoong = new JLabel("");
			SulMyeongChoong.setBounds(290, 130, 360, 60);
			SulMyeongChoong.setBackground(Color.blue);
			studentPanel.add(SulMyeongChoong);

			scrollPane.setBounds(10, 200, 955, 435);
			studentPanel.add(scrollPane);

			back.setBounds(760, 10, 100, 40);
			back.addActionListener(action);
			studentPanel.add(back);

			logout.setBounds(870, 10, 100, 40);
			logout.addActionListener(action);
			studentPanel.add(logout);

			setResizable(false);
			pack();
			setVisible(true);

			showStudent.doClick();
		}
	}

	public class ManageLecture {
		public ManageLecture(boolean isManager) {
			defaultUISetting(1000, 700);

			JPanel lecturePanel = new JPanel();
			thisPanel = lecturePanel;
			lecturePanel.setBounds(10, 10, 975, 645);
			lecturePanel.setBackground(Color.lightGray);
			lecturePanel.setLayout(null);
			backgroundPanel.add(lecturePanel);

			titleLabel(thisPanel, "Lecture");
			guideKey(thisPanel);

			showLecture.setBounds(10, 150, 100, 40);
			showLecture.addActionListener(action);
			lecturePanel.add(showLecture);

			radioButtonSetting(thisPanel, lectureRadio);

			String[] s = { "lNumber: ", "lPro : ", "lName : ", "lCredit : ", "lTime : ", "lPlace : ", "lDep. : " };
			for (int i = 0; i < s.length; i++) {
				lectureLabel[i] = new JLabel(s[i]);
				lectureLabel[i].setBounds(250 + (i % 3) * 190, 80 + 40 * (i / 3), 70, 30);
				lecturePanel.add(lectureLabel[i]);

				lectureField[i] = new JTextField();
				lectureField[i].setBounds(320 + (i % 3) * 190, 80 + 40 * (i / 3), 100, 30);
				lecturePanel.add(lectureField[i]);
			}
			lectureField[0].setBackground(Color.green);
			lectureField[1].setBackground(Color.yellow);
			lectureField[6].setBackground(Color.red);

			lectureInsert = new JButton("Insert");
			lectureInsert.setBounds(820, 80, 100, 30);
			lectureInsert.addActionListener(action);
			lecturePanel.add(lectureInsert);

			lectureUpdate = new JButton("Update");
			lectureUpdate.setBounds(820, 80, 100, 30);
			lectureUpdate.addActionListener(action);
			lectureUpdate.setVisible(false);
			lecturePanel.add(lectureUpdate);

			lectureDelete = new JButton("Delete");
			lectureDelete.setBounds(630, 80, 100, 30);
			lectureDelete.addActionListener(action);
			lectureDelete.setVisible(false);
			lecturePanel.add(lectureDelete);

			scrollPane.setBounds(10, 200, 955, 435);
			lecturePanel.add(scrollPane);

			back.setBounds(760, 10, 100, 40);
			back.addActionListener(action);
			lecturePanel.add(back);

			logout.setBounds(870, 10, 100, 40);
			logout.addActionListener(action);
			lecturePanel.add(logout);

			setResizable(false);
			pack();
			setVisible(true);

			showLecture.doClick();
		}
	}

	public class ManageCourse {
		public ManageCourse(boolean isManager) {
			defaultUISetting(1000, 700);

			JPanel coursePanel = new JPanel();
			thisPanel = coursePanel;
			coursePanel.setBounds(10, 10, 975, 645);
			coursePanel.setBackground(Color.lightGray);
			coursePanel.setLayout(null);
			backgroundPanel.add(coursePanel);

			titleLabel(thisPanel, "Course");
			guideKey(thisPanel);

			showCourse.setBounds(10, 150, 100, 40);
			showCourse.addActionListener(action);
			coursePanel.add(showCourse);

			resultCourse.setBounds(120, 150, 100, 40);
			resultCourse.addActionListener(action);
			coursePanel.add(resultCourse);

			radioButtonSetting(thisPanel, courseRadio);

			String[] s = { "cStu. : ", "cPro. : ", "cLec. : ", "cMiddle : ", "cFinal : ", "cOthers : ", "cAtt. : ", "cTotal : ", "cGrade : " };
			for (int i = 0; i < s.length; i++) {
				courseLabel[i] = new JLabel(s[i]);
				courseLabel[i].setBounds(250 + (i % 3) * 190, 80 + 40 * (i / 3), 70, 30);
				coursePanel.add(courseLabel[i]);

				courseField[i] = new JTextField();
				courseField[i].setBounds(320 + (i % 3) * 190, 80 + 40 * (i / 3), 100, 30);
				coursePanel.add(courseField[i]);
			}
			courseField[0].setBackground(Color.yellow);
			courseField[1].setBackground(Color.yellow);
			courseField[2].setBackground(Color.yellow);

			courseInsert = new JButton("Insert");
			courseInsert.setBounds(820, 80, 100, 30);
			courseInsert.addActionListener(action);
			coursePanel.add(courseInsert);

			courseUpdate = new JButton("Update");
			courseUpdate.setBounds(820, 80, 100, 30);
			courseUpdate.addActionListener(action);
			courseUpdate.setVisible(false);
			coursePanel.add(courseUpdate);

			courseDelete = new JButton("Delete");
			courseDelete.setBounds(820, 80, 100, 30);
			courseDelete.addActionListener(action);
			courseDelete.setVisible(false);
			coursePanel.add(courseDelete);

			JLabel SulMyeongChoong = new JLabel("");
			SulMyeongChoong.setBounds(290, 130, 360, 60);
			SulMyeongChoong.setBackground(Color.blue);
			coursePanel.add(SulMyeongChoong);

			scrollPane.setBounds(10, 200, 955, 435);
			coursePanel.add(scrollPane);

			back.setBounds(760, 10, 100, 40);
			back.addActionListener(action);
			coursePanel.add(back);

			logout.setBounds(870, 10, 100, 40);
			logout.addActionListener(action);
			coursePanel.add(logout);

			setResizable(false);
			pack();
			setVisible(true);

			showCourse.doClick();
		}
	}

	public class ManageSupervision {
		public ManageSupervision(boolean isManager) {
			defaultUISetting(1000, 700);

			JPanel superPanel = new JPanel();
			thisPanel = superPanel;
			superPanel.setBounds(10, 10, 975, 645);
			superPanel.setBackground(Color.lightGray);
			superPanel.setLayout(null);
			backgroundPanel.add(superPanel);

			titleLabel(thisPanel, "Supervision");
			guideKey(thisPanel);

			showSupervision.setBounds(10, 150, 100, 40);
			showSupervision.addActionListener(action);
			superPanel.add(showSupervision);

			termStudent.setBounds(120, 150, 120, 40);
			termStudent.addActionListener(action);
			superPanel.add(termStudent);

			radioButtonSetting(thisPanel, supervisionRadio);

			String[] s = { "Stu. : ", "Pro. : ", "Term. : " };
			for (int i = 0; i < s.length; i++) {
				supervisionLabel[i] = new JLabel(s[i]);
				supervisionLabel[i].setBounds(250 + i * 190, 80, 70, 30);
				superPanel.add(supervisionLabel[i]);

				supervisionField[i] = new JTextField();
				supervisionField[i].setBounds(320 + i * 190, 80, 100, 30);
				superPanel.add(supervisionField[i]);
			}
			supervisionField[0].setBackground(Color.yellow);
			supervisionField[1].setBackground(Color.yellow);

			supervisionInsert = new JButton("Insert");
			supervisionInsert.setBounds(820, 80, 100, 30);
			supervisionInsert.addActionListener(action);
			superPanel.add(supervisionInsert);

			supervisionUpdate = new JButton("Update");
			supervisionUpdate.setBounds(820, 80, 100, 30);
			supervisionUpdate.addActionListener(action);
			supervisionUpdate.setVisible(false);
			superPanel.add(supervisionUpdate);

			supervisionDelete = new JButton("Delete");
			supervisionDelete.setBounds(440, 80, 100, 30);
			supervisionDelete.addActionListener(action);
			supervisionDelete.setVisible(false);
			superPanel.add(supervisionDelete);

			scrollPane.setBounds(10, 200, 955, 435);
			superPanel.add(scrollPane);

			back.setBounds(760, 10, 100, 40);
			back.addActionListener(action);
			superPanel.add(back);

			logout.setBounds(870, 10, 100, 40);
			logout.addActionListener(action);
			superPanel.add(logout);

			setResizable(false);
			pack();
			setVisible(true);

			showSupervision.doClick();
		}
	}

	public boolean studentIdCheck(StudentStructor s) {
		connectDB();
		String sql = "select * from Student";
		int id;
		String pw;
		boolean result = false;

		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				id = rs.getInt(1);
				pw = rs.getString(3).substring(0, 6);

				if (s.getSnumber() == id && s.getScivilnumber().equals(pw)) {
					result = true;
					break;
				} else {
					result = false;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		closeDB();
		return result;
	}

	public boolean professorIdCheck(ProfessorStructor p) {
		connectDB();
		String sql = "select * from Professor";
		int id;
		String pw;
		boolean result = false;

		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				id = rs.getInt(1);
				pw = rs.getString(3).substring(0, 6);

				if (p.getPnumber() == id && p.getPcivilnumber().equals(pw)) {
					result = true;
					break;
				} else {
					result = false;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		closeDB();
		return result;
	}

	public class Action implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			Object button = e.getSource();

			if (button == login) {
				if (idText.getText().length() == 8) {
					StudentStructor s = new StudentStructor();
					s.setSnumber(Integer.parseInt(idText.getText()));
					s.setScivilnumber(pwText.getText());

					if (studentIdCheck(s)) {
						thisPanel.setVisible(false);
						new Student(s.getSnumber());
					}

					else {
						pwText.setText("");
						JOptionPane.showMessageDialog(null, "Check a Password.");
					}
				}

				else if (idText.getText().length() == 6 || idText.getText().equals("0")) {
					ProfessorStructor p = new ProfessorStructor();
					p.setPnumber(Integer.parseInt(idText.getText()));
					p.setPcivilnumber(pwText.getText());

					if (professorIdCheck(p)) {
						thisPanel.setVisible(false);
						switch (idText.getText()) {
						case "0":
						case "000000":
							new Manager();
							break;
						default:
							new Professor(p.getPnumber());
							break;
						}
					}

					else {
						pwText.setText("");
						JOptionPane.showMessageDialog(null, "Check a Password.");
					}
				}

				else {
					idText.setText("");
					pwText.setText("");
					JOptionPane.showMessageDialog(null, "Check ID & Password.");
				}
			}

			if (button == initialization) {
				idText.setText(null);
				pwText.setText(null);
			}

			if (button == initData) {
				initTuple();
			}

			if (button == back) {
				thisPanel.setVisible(false);
				new Manager();
			}

			for (int i = 0; i < 2; i++) {
				if (button == myUpdate[i]) {
					updateFlag = 1 - updateFlag;
					if (updateFlag == 1)
						myUpdate[i].setText("Confirm");
					else
						myUpdate[i].setText("<html>My<br>Information</html>");

					switch (i) {
					case 0:
						setComponent(studentField, updateFlag);
						myInformation1();
						break;
					case 1:
						setComponent(professorField, updateFlag);
						myInformation2();
						break;
					}
				}
			}

			if (button == logout) {
				thisPanel.setVisible(false);
				output.setText("");
				new Login();
			}

			if (button == showApplicationLecture) {
				insertCourse.setVisible(true);
				deleteCourse.setVisible(false);
				connectDB();
				String sql = "select lNumber, lName, pName, lCredit, lTime, lPlace, dName\r\n" + "from Department, Professor, Lecture\r\n"
						+ "where dNumber = lDepartment and dNumber = pDepartment and pNumber = lProfessor and\r\n"
						+ " dNumber in (select pDepartment from Professor)";

				ArrayList<LectureStructor> datas = new ArrayList<LectureStructor>();

				try {
					pstmt = conn.prepareStatement(sql);
					rs = pstmt.executeQuery();

					while (rs.next()) {
						LectureStructor l = new LectureStructor();
						l.d = new DepartmentStructor();
						l.p = new ProfessorStructor();

						l.setLnumber(rs.getInt("lNumber"));
						l.setLname(rs.getString("lName"));
						l.p.setPname(rs.getString("pName"));
						l.setLcredit(rs.getInt("lCredit"));
						l.setLtime(rs.getString("lTime"));
						l.setLplace(rs.getString("lPlace"));
						l.d.setDname(rs.getString("dName"));

						datas.add(l);
					}
				} catch (SQLException error) {
					error.printStackTrace();
				}
				closeDB();

				output.setText("");
				output.append("  lNumber\tlName\tpName\t  lCredit\tlTime\tlPlace\tdName\n");

				for (LectureStructor l : datas) {
					StringBuffer sb = new StringBuffer();
					sb.append("      " + l.getLnumber() + "\t");
					sb.append(l.getLname() + "\t");
					sb.append(l.p.getPname() + "\t");
					sb.append("       " + l.getLcredit() + "\t");
					sb.append(l.getLtime() + "\t");
					sb.append(l.getLplace() + "\t");
					sb.append(l.d.getDname() + "\n");
					output.append(sb.toString());
				}

				output.setCaretPosition(0);
				output.requestFocus();
			}

			if (button == myCourse) {
				insertCourse.setVisible(false);
				deleteCourse.setVisible(true);
				connectDB();
				String sql = "select lName, pName, cMiddle, cFinal, cOthers, cAttendance, cTotal, cGrade\r\n" + "from Lecture, Professor, Course\r\n"
						+ "where cLecture = lNumber and cProfessor = pNumber and pNumber = lProfessor\r\n"
						+ "  and cStudent in (select sNumber from Student where sNumber = " + "?)";

				ArrayList<CourseStructor> datas = new ArrayList<CourseStructor>();
				try {
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, mySnumber);
					rs = pstmt.executeQuery();

					while (rs.next()) {
						CourseStructor c = new CourseStructor();
						c.p = new ProfessorStructor();
						c.l = new LectureStructor();

						c.l.setLname(rs.getString("lName"));
						c.p.setPname(rs.getString("pName"));
						c.setCmiddle(rs.getInt("cMiddle"));
						c.setCfinal(rs.getInt("cFinal"));
						c.setCothers(rs.getInt("cOthers"));
						c.setCattendance(rs.getInt("cAttendance"));
						c.setCtotal(rs.getInt("cTotal"));
						c.setCgrade(rs.getString("cGrade"));

						datas.add(c);
					}
				} catch (SQLException error) {
					error.printStackTrace();
				}
				closeDB();

				output.setText("");
				output.append("lName\tpName\tcMiddle\tcFinal\tcOthers\tcAttendance\tcTotal\tcGrade\n");

				for (CourseStructor c : datas) {
					StringBuffer sb = new StringBuffer();
					sb.append(c.l.getLname() + "\t");
					sb.append(c.p.getPname() + "\t");
					sb.append(c.getCmiddle() + "\t");
					sb.append(c.getCfinal() + "\t");
					sb.append(c.getCothers() + "\t");
					sb.append(c.getCattendance() + "\t");
					sb.append(c.getCtotal() + "\t");
					sb.append(c.getCgrade() + "\n");
					output.append(sb.toString());
				}

				output.setCaretPosition(0);
				output.requestFocus();
			}

			if (button == insertCourse) {
				connectDB();

				boolean value = false;
				String sql = "select lNumber, lProfessor from Professor, Lecture\r\n" + "where pNumber = lProfessor and lName = ? and pName = ?";
				LectureStructor l = null;

				try {
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, myCourseField.getText());
					pstmt.setString(2, myProfessorField.getText());
					rs = pstmt.executeQuery();

					rs.next();
					l = new LectureStructor();
					l.setLnumber(rs.getInt("lNumber"));
					l.setLprofessor(rs.getInt("lProfessor"));
					value = true;
				} catch (Exception error) {
					if (l.getLnumber() == 0 && l.getLprofessor() == 0) {
						JOptionPane.showMessageDialog(null, "Invalid Lecture.\nCheck lName & pName.");
						value = false;
					}
				}

				if (value) {
					sql = "insert into Course values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
					try {
						pstmt = conn.prepareStatement(sql);
						pstmt.setInt(1, mySnumber);
						pstmt.setInt(2, l.getLprofessor());
						pstmt.setInt(3, l.getLnumber());
						pstmt.setInt(4, 0);
						pstmt.setInt(5, 0);
						pstmt.setInt(6, 0);
						pstmt.setInt(7, 0);
						pstmt.setInt(8, 0);
						pstmt.setString(9, "0");

						pstmt.executeUpdate();
					} catch (Exception error) {
						JOptionPane.showMessageDialog(null, "Already exists in Course list.");
					}

					myCourseField.setText("");
					myProfessorField.setText("");

					myCourse.doClick();
				}
				closeDB();
			}

			if (button == deleteCourse) {
				connectDB();

				boolean value = false;
				String sql = "select lNumber, lProfessor from Professor, Lecture\r\n" + "where pNumber = lProfessor and lName = ? and pName = ?";
				LectureStructor l = null;

				try {
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, myCourseField.getText());
					pstmt.setString(2, myProfessorField.getText());
					rs = pstmt.executeQuery();

					rs.next();
					l = new LectureStructor();
					l.setLnumber(rs.getInt("lNumber"));
					l.setLprofessor(rs.getInt("lProfessor"));
					value = true;
				} catch (Exception error) {
					if (l.getLnumber() == 0 && l.getLprofessor() == 0) {
						JOptionPane.showMessageDialog(null, "Invalid Lecture.\nCheck lName & lProfessor");
						value = false;
					}
				}

				if (value) {
					sql = "delete from Course where cStudent = ? and cProfessor = ? and cLecture = ?";
					try {
						pstmt = conn.prepareStatement(sql);
						pstmt.setInt(1, mySnumber);
						pstmt.setInt(2, l.getLprofessor());
						pstmt.setInt(3, l.getLnumber());

						int flag;
						flag = pstmt.executeUpdate();
						if (flag == 0)
							JOptionPane.showMessageDialog(null, "It is not Coursing Lecture.");
					} catch (Exception error) {

					}

					myCourseField.setText("");
					myProfessorField.setText("");

					myCourse.doClick();
				}
				closeDB();
			}

			if (button == myLectures) {
				scorePanel.setVisible(false);
				connectDB();
				String sql = "select lNumber, lName, lCredit, lTime, lPlace\r\n" + "from Lecture\r\n"
						+ "where lProfessor in (select pNumber from Professor where pNumber = ? )";

				ArrayList<LectureStructor> datas = new ArrayList<LectureStructor>();

				try {
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, myPnumber);
					rs = pstmt.executeQuery();

					while (rs.next()) {
						LectureStructor l = new LectureStructor();
						l.setLnumber(rs.getInt("lNumber"));
						l.setLname(rs.getString("lName"));
						l.setLcredit(rs.getInt("lCredit"));
						l.setLtime(rs.getString("lTime"));
						l.setLplace(rs.getString("lPlace"));

						datas.add(l);
					}
				} catch (SQLException error) {
					error.printStackTrace();
				}
				closeDB();

				output.setText("");
				output.append("lNumber\tlName\tlCredit\tlTime\tlPlace\n");

				for (LectureStructor l : datas) {
					StringBuffer sb = new StringBuffer();
					sb.append(l.getLnumber() + "\t");
					sb.append(l.getLname() + "\t");
					sb.append(l.getLcredit() + "\t");
					sb.append(l.getLtime() + "\t");
					sb.append(l.getLplace() + "\n");
					output.append(sb.toString());
				}

				output.setCaretPosition(0);
				output.requestFocus();
			}

			if (button == myStudents) {
				scorePanel.setVisible(false);
				connectDB();
				String sql = "select sNumber, sName, sCivilnumber, sAddress, sPhone, sEmail"
						+ " from Student where sProfessor in (select pNumber from Professor where pNumber = ?)";

				ArrayList<StudentStructor> datas = new ArrayList<StudentStructor>();

				try {
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, myPnumber);
					rs = pstmt.executeQuery();

					while (rs.next()) {
						StudentStructor s = new StudentStructor();
						s.setSnumber(rs.getInt("sNumber"));
						s.setSname(rs.getString("sName"));
						s.setScivilnumber(rs.getString("sCivilnumber"));
						s.setSaddress(rs.getString("sAddress"));
						s.setSphone(rs.getString("sPhone"));
						s.setSemail(rs.getString("sEmail"));

						datas.add(s);
					}
				} catch (SQLException error) {
					error.printStackTrace();
				}
				closeDB();

				output.setText("");
				output.append("sNumber\tsName\tsCivil\t\tsAddress\t\tsPhone\t\tsEmail\n");

				for (StudentStructor s : datas) {
					StringBuffer sb = new StringBuffer();
					sb.append(s.getSnumber() + "\t");
					sb.append(s.getSname() + "\t");
					sb.append(s.getScivilnumber() + "\t");
					sb.append(s.getSaddress() + "\t\t");
					sb.append(s.getSphone() + "\t\t");
					sb.append(s.getSemail() + "\n");

					output.append(sb.toString());
				}

				output.setCaretPosition(0);
				output.requestFocus();
			}

			if (button == studentScore) {
				scorePanel.setVisible(true);
				connectDB();
				String sql = "select sName, lName, cMiddle, cFinal, cOthers, cAttendance, cTotal, cGrade\r\n" + "from Student, Lecture, Course\r\n"
						+ "where sNumber = cStudent and lNumber = cLecture and lProfessor = cProfessor\r\n"
						+ "  and cProfessor in (select pNumber from Professor where pNumber = ?)";

				ArrayList<CourseStructor> datas = new ArrayList<CourseStructor>();

				try {
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, myPnumber);
					rs = pstmt.executeQuery();

					while (rs.next()) {
						CourseStructor c = new CourseStructor();
						c.s = new StudentStructor();
						c.d = new DepartmentStructor();

						c.s.setSname(rs.getString("sName"));
						c.d.setDname(rs.getString("lName"));
						c.setCmiddle(rs.getInt("cMiddle"));
						c.setCfinal(rs.getInt("cFinal"));
						c.setCothers(rs.getInt("cOthers"));
						c.setCattendance(rs.getInt("cAttendance"));
						c.setCtotal(rs.getInt("cTotal"));
						c.setCgrade(rs.getString("cGrade"));

						datas.add(c);
					}
				} catch (SQLException error) {
					error.printStackTrace();
				}
				closeDB();

				output.setText("");
				output.append("sName\tlName\tcMiddle\tcFinal\tcOthers\tcAttendance\tcTotal\tcGrade\n");

				for (CourseStructor c : datas) {
					StringBuffer sb = new StringBuffer();
					sb.append(c.s.getSname() + "\t");
					sb.append(c.d.getDname() + "\t");
					sb.append(c.getCmiddle() + "\t");
					sb.append(c.getCfinal() + "\t");
					sb.append(c.getCothers() + "\t");
					sb.append(c.getCattendance() + "\t");
					sb.append(c.getCtotal() + "\t");
					sb.append(c.getCgrade() + "\n");

					output.append(sb.toString());
				}

				output.setCaretPosition(0);
				output.requestFocus();
			}

			if (button == scoreUpdate) {
				connectDB();

				boolean value = false;
				String sql = "select sNumber, lProfessor, lNumber from Student, Lecture, Course\r\n" + "where sNumber = cStudent and lNumber = cLecture\r\n"
						+ "  and lProfessor = cProfessor\r\n" + "  and sName = ? and cProfessor = ? and lName = ?";
				CourseStructor c = null;

				try {
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, scoreField[0].getText());
					pstmt.setInt(2, myPnumber);
					pstmt.setString(3, scoreField[1].getText());
					rs = pstmt.executeQuery();

					rs.next();
					c = new CourseStructor();
					c.setCstudent(rs.getInt("sNumber"));
					c.setCprofessor(rs.getInt("lProfessor"));
					c.setClecture(rs.getInt("lNumber"));

					value = true;
				} catch (Exception error) {
					if (c.getCstudent() == 0) {
						JOptionPane.showMessageDialog(null, "Check sName & lName.");
						value = false;
					} else
						error.getMessage();
				}

				if (value) {
					sql = "update Course  set cMiddle = ?, cFinal = ?, cOthers = ?, cAttendance = ?, cTotal = ?, cGrade = ? where cStudent = ? and cProfessor = ? and cLecture = ?";
					try {
						pstmt = conn.prepareStatement(sql);
						pstmt.setInt(1, Integer.parseInt(scoreField[2].getText()));
						pstmt.setInt(2, Integer.parseInt(scoreField[3].getText()));
						pstmt.setInt(3, Integer.parseInt(scoreField[4].getText()));
						pstmt.setInt(4, Integer.parseInt(scoreField[5].getText()));

						String grade;
						int TotalScore = (int) (Integer.parseInt(scoreField[2].getText()) * 0.4) + (int) (Integer.parseInt(scoreField[3].getText()) * 0.4)
								+ Integer.parseInt(scoreField[4].getText()) + Integer.parseInt(scoreField[5].getText());

						switch (TotalScore / 10) {
						case 10:
							grade = "4.5";
							break;
						case 9:
							grade = "4.0";
							break;
						case 8:
							grade = "3.5";
							break;
						case 7:
							grade = "3.0";
							break;
						case 6:
							grade = "2.5";
							break;
						case 5:
							grade = "2.0";
							break;
						case 4:
							grade = "1.5";
							break;
						case 3:
							grade = "1.0";
							break;
						default:
							grade = "0.0";
							break;
						}

						pstmt.setInt(5, TotalScore);
						pstmt.setString(6, grade);

						pstmt.setInt(7, c.getCstudent());
						pstmt.setInt(8, c.getCprofessor());
						pstmt.setInt(9, c.getClecture());

						pstmt.executeUpdate();
					} catch (Exception error) {

					}

					for (int i = 0; i < 6; i++)
						scoreField[i].setText("");

					studentScore.doClick();
				}
				closeDB();
			}

			for (int i = 0; i < 6; i++) {
				if (button == mButton[i]) {
					thisPanel.setVisible(false);

					switch (i) {
					case 0:
						new ManageDepartment(true);
						break;
					case 1:
						new ManageProfessor(true);
						break;
					case 2:
						new ManageStudent(true);
						break;
					case 3:
						new ManageLecture(true);
						break;
					case 4:
						new ManageCourse(true);
						break;
					case 5:
						new ManageSupervision(true);
						break;
					}
					break;
				}
			}

			if (button == showDepartment) {
				connectDB();
				String sql = "select * from Department";
				ArrayList<DepartmentStructor> datas = new ArrayList<DepartmentStructor>();

				try {
					pstmt = conn.prepareStatement(sql);
					rs = pstmt.executeQuery();

					while (rs.next()) {
						DepartmentStructor d = new DepartmentStructor();
						if (rs.getInt("dNumber") == 0)
							continue;
						d.setDnumber(rs.getInt("dNumber"));
						d.setDname(rs.getString("dName"));
						d.setDphone(rs.getString("dPhone"));

						datas.add(d);
					}

					output.setText("");
					output.append("dNumber\tdName\tdPhone\n");

					for (DepartmentStructor d : datas) {
						StringBuffer sb = new StringBuffer();
						sb.append(d.getDnumber() + "\t");
						sb.append(d.getDname() + "\t");
						sb.append(d.getDphone() + "\n");
						output.append(sb.toString());
					}
				} catch (Exception error) {

				}
				output.setCaretPosition(0);
				output.requestFocus();
				closeDB();
			}

			if (button == departmentPerson) {
				connectDB();
				String sql = "select dName, count(sNumber) as cntStudent, count(pNumber) as cntProfessor\r\n" + "from Department, Student, Professor\r\n"
						+ "where dNumber = sDepartment and dNumber = pDepartment and sProfessor = pNumber\r\n" + "group by dName\r\n"
						+ "having count(sNumber) >= 0 and count(pNumber) >= 0";
				ArrayList<DepartmentStructor> datas = new ArrayList<DepartmentStructor>();

				try {
					pstmt = conn.prepareStatement(sql);
					rs = pstmt.executeQuery();

					while (rs.next()) {
						DepartmentStructor d = new DepartmentStructor();

						d.setDname(rs.getString("dName"));
						d.setCntstudent(rs.getInt("cntStudent"));
						d.setCntprofessor(rs.getInt("cntProfessor"));

						datas.add(d);
					}

					output.setText("");
					output.append("dName\tcntStudent\tcntProfessor\n");

					for (DepartmentStructor d : datas) {
						StringBuffer sb = new StringBuffer();
						sb.append(d.getDname() + "\t");
						sb.append(d.getCntstudent() + "\t");
						sb.append(d.getCntprofessor() + "\n");
						output.append(sb.toString());
					}
				} catch (Exception error) {

				}
				output.setCaretPosition(0);
				output.requestFocus();
				closeDB();
			}

			if (button == showProfessor) {
				connectDB();
				String sql = "select * from Professor";
				ArrayList<ProfessorStructor> datas = new ArrayList<ProfessorStructor>();

				try {
					pstmt = conn.prepareStatement(sql);
					rs = pstmt.executeQuery();

					while (rs.next()) {
						ProfessorStructor d = new ProfessorStructor();
						if (rs.getInt("pNumber") == 0)
							continue;
						d.setPnumber(rs.getInt("pNumber"));
						d.setPname(rs.getString("pName"));
						d.setPcivilnumber(rs.getString("pCivilnumber"));
						d.setPaddress(rs.getString("pAddress"));
						d.setPphone(rs.getString("pPhone"));
						d.setPemail(rs.getString("pEmail"));
						d.setPpicture(rs.getInt("pPicture"));
						d.setPdepartment(rs.getInt("pDepartment"));

						datas.add(d);
					}

					output.setText("");
					output.append("pNumber\tpName\t\tpCivilnumber\t\tpAddress\tpPhone\t      pEmail\tpPicture\tpDepartment\n");

					for (ProfessorStructor p : datas) {
						StringBuffer sb = new StringBuffer();
						sb.append(p.getPnumber() + "\t");
						sb.append(p.getPname() + "\t\t");
						sb.append(p.getPcivilnumber() + "\t");
						sb.append(p.getPaddress() + "\t");
						sb.append(p.getPphone() + "\t      ");
						sb.append(p.getPemail() + "\t");
						sb.append(p.getPpicture() + "\t");
						sb.append(p.getPdepartment() + "\n");
						output.append(sb.toString());
					}
				} catch (Exception error) {

				}
				output.setCaretPosition(0);
				output.requestFocus();
				closeDB();
			}

			if (button == showStudent) {
				connectDB();
				String sql = "select * from Student";
				ArrayList<StudentStructor> datas = new ArrayList<StudentStructor>();

				try {
					pstmt = conn.prepareStatement(sql);
					rs = pstmt.executeQuery();

					while (rs.next()) {
						StudentStructor s = new StudentStructor();

						s.setSnumber(rs.getInt("sNumber"));
						s.setSname(rs.getString("sName"));
						s.setScivilnumber(rs.getString("sCivilNumber"));
						s.setSaddress(rs.getString("sAddress"));
						s.setSphone(rs.getString("sPhone"));
						s.setSemail(rs.getString("sEmail"));
						s.setSpicture(rs.getInt("sPicture"));
						s.setSdepartment(rs.getInt("sDepartment"));
						s.setSprofessor(rs.getInt("sProfessor"));

						datas.add(s);
					}

					output.setText("");
					output.append("sNumber\tsName\tsCivil\t      sAddress\tsPhone\t\tsEmail\tsPicture\tsDepatment\tsProfessor\n");

					for (StudentStructor s : datas) {
						StringBuffer sb = new StringBuffer();
						sb.append(s.getSnumber() + "\t");
						sb.append(s.getSname() + "\t");
						sb.append(s.getScivilnumber() + "    ");
						sb.append(s.getSaddress() + "\t");
						sb.append(s.getSphone() + "\t\t");
						sb.append(s.getSemail() + "\t");
						sb.append(s.getSpicture() + "\t");
						sb.append(s.getSdepartment() + "\t");
						sb.append(s.getSprofessor() + "\n");
						output.append(sb.toString());
					}
				} catch (Exception error) {

				}
				output.setCaretPosition(0);
				output.requestFocus();
				closeDB();
			}

			if (button == totalStudentCredit) {
				connectDB();
				String sql = "select sName, sum(lCredit) as TotalCredit\r\n" + " from Student, Lecture, Course\r\n" + " where\r\n"
						+ "sNumber = cStudent and\r\n" + "cLecture = lnumber and\r\n" + "cProfessor = lProfessor\r\n" + "group by sName\r\n"
						+ "having sum(lCredit) is not null";
				ArrayList<StudentStructor> datas = new ArrayList<StudentStructor>();

				try {
					pstmt = conn.prepareStatement(sql);
					rs = pstmt.executeQuery();

					while (rs.next()) {
						StudentStructor s = new StudentStructor();

						s.setSname(rs.getString("sName"));
						s.setTotalcredit(rs.getInt("TotalCredit"));
						datas.add(s);
					}

					output.setText("");
					output.append("sName\t\tTotalCredit\n");

					for (StudentStructor s : datas) {
						StringBuffer sb = new StringBuffer();
						sb.append(s.getSname() + "\t\t");
						sb.append(s.getTotalcredit() + "\n");
						output.append(sb.toString());
					}
				} catch (Exception error) {

				}
				output.setCaretPosition(0);
				output.requestFocus();
				closeDB();
			}

			if (button == showLecture) {
				connectDB();
				String sql = "select * from Lecture";
				ArrayList<LectureStructor> datas = new ArrayList<LectureStructor>();

				try {
					pstmt = conn.prepareStatement(sql);
					rs = pstmt.executeQuery();

					while (rs.next()) {
						LectureStructor l = new LectureStructor();

						l.setLnumber(rs.getInt("lNumber"));
						l.setLprofessor(rs.getInt("lProfessor"));
						l.setLname(rs.getString("lName"));
						l.setLcredit(rs.getInt("lCredit"));
						l.setLtime(rs.getString("lTime"));
						l.setLplace(rs.getString("lPlace"));
						l.setLdepartment(rs.getInt("lDepartment"));

						datas.add(l);
					}

					output.setText("");
					output.append("lNumber\tlProfessor\tlName\tlCredit\tlTime\tlPlace\tlDepartment\n");

					for (LectureStructor l : datas) {
						StringBuffer sb = new StringBuffer();
						sb.append(l.getLnumber() + "\t");
						sb.append(l.getLprofessor() + "\t");
						sb.append(l.getLname() + "\t");
						sb.append(l.getLcredit() + "\t");
						sb.append(l.getLtime() + "\t");
						sb.append(l.getLplace() + "\t");
						sb.append(l.getLdepartment() + "\n");
						output.append(sb.toString());
					}
				} catch (Exception error) {

				}
				output.setCaretPosition(0);
				output.requestFocus();
				closeDB();
			}

			if (button == showCourse) {
				connectDB();
				String sql = "select * from Course";
				ArrayList<CourseStructor> datas = new ArrayList<CourseStructor>();

				try {
					pstmt = conn.prepareStatement(sql);
					rs = pstmt.executeQuery();

					while (rs.next()) {
						CourseStructor c = new CourseStructor();

						c.setCstudent(rs.getInt("cStudent"));
						c.setCprofessor(rs.getInt("cProfessor"));
						c.setClecture(rs.getInt("cLecture"));
						c.setCmiddle(rs.getInt("cMiddle"));
						c.setCfinal(rs.getInt("cFinal"));
						c.setCothers(rs.getInt("cOthers"));
						c.setCattendance(rs.getInt("cAttendance"));
						c.setCtotal(rs.getInt("cTotal"));
						c.setCgrade(rs.getString("cGrade"));

						datas.add(c);
					}

					output.setText("");
					output.append("cStudent\tcProfessor\tcLecture\tcMiddle\tcFinal\tcOthers\tcAttendance\tcTotal\tcGrade\n");

					for (CourseStructor c : datas) {
						StringBuffer sb = new StringBuffer();
						sb.append(c.getCstudent() + "\t");
						sb.append(c.getCprofessor() + "\t");
						sb.append(c.getClecture() + "\t");
						sb.append(c.getCmiddle() + "\t");
						sb.append(c.getCfinal() + "\t");
						sb.append(c.getCothers() + "\t");
						sb.append(c.getCattendance() + "\t");
						sb.append(c.getCtotal() + "\t");
						sb.append(c.getCgrade() + "\n");

						output.append(sb.toString());
					}
				} catch (Exception error) {

				}
				output.setCaretPosition(0);
				output.requestFocus();
				closeDB();
			}

			if (button == resultCourse) {
				connectDB();
				String sql = "select lName, pName, avg(cTotal) as avgTotal, count(cStudent) as cntStudent\r\n" + "from Professor, Lecture, Course\r\n"
						+ "where pNumber = lProfessor and pNumber = cProfessor\r\n" + "  and lProfessor = cProfessor and lNumber = cLecture\r\n"
						+ "group by lName, pName\r\n" + "having count(cStudent) != 0";
				ArrayList<CourseStructor> datas = new ArrayList<CourseStructor>();

				try {
					pstmt = conn.prepareStatement(sql);
					rs = pstmt.executeQuery();

					while (rs.next()) {
						CourseStructor c = new CourseStructor();
						c.l = new LectureStructor();
						c.p = new ProfessorStructor();

						c.l.setLname(rs.getString("lName"));
						c.p.setPname(rs.getString("pName"));
						c.setAvgtotal(rs.getFloat("avgTotal"));
						c.setCntstudent(rs.getInt("cntStudent"));

						datas.add(c);
					}

					output.setText("");
					output.append("lName\tpName\t\tavgTotal\tcntStudent\n");

					for (CourseStructor c : datas) {
						StringBuffer sb = new StringBuffer();
						sb.append(c.l.getLname() + "\t");
						sb.append(c.p.getPname() + "\t\t");
						sb.append(c.getAvgtotal() + "\t");
						sb.append(c.getCntstudent() + "\n");

						output.append(sb.toString());
					}
				} catch (Exception error) {

				}
				output.setCaretPosition(0);
				output.requestFocus();
				closeDB();
			}

			if (button == showSupervision) {
				connectDB();
				String sql = "select * from Supervision";
				ArrayList<SupervisionStructor> datas = new ArrayList<SupervisionStructor>();

				try {
					pstmt = conn.prepareStatement(sql);
					rs = pstmt.executeQuery();

					while (rs.next()) {
						SupervisionStructor s = new SupervisionStructor();

						s.setSuperstudent(rs.getInt("superStudent"));
						s.setSuperprofessor(rs.getInt("superProfessor"));
						s.setSuperterm(rs.getString("superTerm"));

						datas.add(s);
					}

					output.setText("");
					output.append("superStudent\tsuperProfessor\t\tsuperTerm\n");

					for (SupervisionStructor l : datas) {
						StringBuffer sb = new StringBuffer();
						sb.append(l.getSuperstudent() + "\t");
						sb.append(l.getSuperprofessor() + "\t\t");
						sb.append(l.getSuperterm() + "\n");

						output.append(sb.toString());
					}
				} catch (Exception error) {

				}
				output.setCaretPosition(0);
				output.requestFocus();
				closeDB();
			}

			if (button == termStudent) {
				connectDB();
				String sql = "select superTerm, count(superStudent) as cntStudent\r\n" + "from Supervision\r\n" + "group by superTerm\r\n"
						+ "having superTerm is not null\r\n" + "order by superTerm";
				ArrayList<SupervisionStructor> datas = new ArrayList<SupervisionStructor>();

				try {
					pstmt = conn.prepareStatement(sql);
					rs = pstmt.executeQuery();

					while (rs.next()) {
						SupervisionStructor s = new SupervisionStructor();

						s.setSuperterm(rs.getString("superTerm"));
						s.setCntstudent(rs.getInt("cntStudent"));

						datas.add(s);
					}

					output.setText("");
					output.append("superTerm\tcntStudent\n");

					for (SupervisionStructor l : datas) {
						StringBuffer sb = new StringBuffer();
						sb.append(l.getSuperterm() + "\t");
						sb.append(l.getCntstudent() + "\n");

						output.append(sb.toString());
					}
				} catch (Exception error) {

				}
				output.setCaretPosition(0);
				output.requestFocus();
				closeDB();
			}

			if (button == departmentRadio[0] || button == departmentRadio[1]) {
				departmentField[1].setVisible(true);
				departmentField[2].setVisible(true);
				departmentLabel[1].setVisible(true);
				departmentLabel[2].setVisible(true);

				if (button == departmentRadio[0]) {
					departmentInsert.setVisible(true);
					departmentUpdate.setVisible(false);
				} else {
					departmentInsert.setVisible(false);
					departmentUpdate.setVisible(true);
				}

				departmentDelete.setVisible(false);
			}

			else if (button == departmentRadio[2]) {
				departmentField[1].setVisible(false);
				departmentField[2].setVisible(false);
				departmentLabel[1].setVisible(false);
				departmentLabel[2].setVisible(false);
				departmentInsert.setVisible(false);
				departmentUpdate.setVisible(false);
				departmentDelete.setVisible(true);
			}

			if (button == departmentInsert) {
				connectDB();

				String sql = "insert into department values(?,?,?)";

				try {
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, Integer.parseInt(departmentField[0].getText()));
					pstmt.setString(2, departmentField[1].getText());
					pstmt.setString(3, departmentField[2].getText());

					pstmt.executeUpdate();
				} catch (Exception error) {
					JOptionPane.showMessageDialog(null, "There already exists a dNumber.");
				}

				for (int i = 0; i < 3; i++)
					departmentField[i].setText("");

				closeDB();
				showDepartment.doClick();
			}

			if (button == departmentUpdate) {
				connectDB();
				String sql = "update Department set dName = ?, dPhone = ? where dNumber = ?";

				try {
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, departmentField[1].getText());
					pstmt.setString(2, departmentField[2].getText());
					pstmt.setInt(3, Integer.parseInt(departmentField[0].getText()));

					if (pstmt.executeUpdate() == 0)
						JOptionPane.showMessageDialog(null, "Check a dNumber.");
				} catch (Exception error) {

				}

				for (int i = 0; i < 3; i++)
					departmentField[i].setText("");

				showDepartment.doClick();
				closeDB();
			}

			if (button == departmentDelete) {
				connectDB();
				String sql = "delete from Department where dNumber = ?";
				try {
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, Integer.parseInt(departmentField[0].getText()));

					int flag;
					flag = pstmt.executeUpdate();
					if (flag == 0)
						JOptionPane.showMessageDialog(null, "There does not exist a dNumber.");
				} catch (Exception error) {

				}

				for (int i = 0; i < 3; i++)
					departmentField[i].setText("");

				showDepartment.doClick();
				closeDB();
			}

			if (button == professorRadio[0] || button == professorRadio[1]) {

				for (int i = 0; i < 8; i++) {
					mprofessorField[i].setVisible(true);
					mprofessorLabel[i].setVisible(true);
				}

				if (button == professorRadio[0]) {
					professorInsert.setVisible(true);
					professorUpdate.setVisible(false);
				} else {
					professorInsert.setVisible(false);
					professorUpdate.setVisible(true);
				}

				professorDelete.setVisible(false);
			}

			else if (button == professorRadio[2]) {
				for (int i = 0; i < 8; i++) {
					mprofessorField[i].setVisible(false);
					mprofessorLabel[i].setVisible(false);
				}
				mprofessorLabel[0].setVisible(true);
				mprofessorField[0].setVisible(true);

				professorInsert.setVisible(false);
				professorUpdate.setVisible(false);
				professorDelete.setVisible(true);
			}

			if (button == professorInsert) {
				connectDB();
				String sql = "insert into professor values(?, ?, ?, ?, ?, ?, ?, ?)";

				try {
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, Integer.parseInt(mprofessorField[0].getText()));
					for (int i = 2; i <= 6; i++)
						pstmt.setString(i, mprofessorField[i - 1].getText());
					pstmt.setInt(7, Integer.parseInt(mprofessorField[6].getText()));
					pstmt.setInt(8, Integer.parseInt(mprofessorField[7].getText()));

					pstmt.executeUpdate();
				} catch (Exception error) {
					// if (error.getMessage().substring(0, 9).equals("Duplicate"))
					// JOptionPane.showMessageDialog(null, "There already exists a pNumber.");
					// else if (error.getMessage().substring(0, 9).equals("Cannot"))
					// JOptionPane.showMessageDialog(null, "There does not exist a pDepartment.");
					if (error.getMessage().substring(0, 9).equals("ORA-00001"))
						JOptionPane.showMessageDialog(null, "There already exists a pNumber.");
					else if (error.getMessage().substring(0, 9).equals("ORA-02291"))
						JOptionPane.showMessageDialog(null, "There does not exist a pDepartment.");
				}

				for (int i = 0; i < 8; i++)
					mprofessorField[i].setText("");

				showProfessor.doClick();
				closeDB();
			}

			if (button == professorUpdate) {
				connectDB();
				String sql = "update Professor set pName = ?, pCivilnumber =?, pAddress = ?, pPhone = ?, pEmail = ?, pPicture = ?, pDepartment = ? where pNumber = ?";

				try {
					pstmt = conn.prepareStatement(sql);
					for (int i = 1; i <= 5; i++)
						pstmt.setString(i, mprofessorField[i].getText());

					for (int i = 6; i <= 8; i++) {
						if (mprofessorField[i % 8].getText().equals(""))
							mprofessorField[i % 8].setText("-1");
						pstmt.setInt(i, Integer.parseInt(mprofessorField[i % 8].getText()));
					}
					if (pstmt.executeUpdate() == 0)
						JOptionPane.showMessageDialog(null, "Check a pNumber.");
				} catch (Exception error) {
					JOptionPane.showMessageDialog(null, "There does not exist a pDepartment.");
				}

				for (int i = 0; i < 8; i++)
					mprofessorField[i].setText("");

				showProfessor.doClick();
				closeDB();
			}

			if (button == professorDelete) {
				connectDB();
				String sql = "delete from Professor where pNumber = ?";
				try {
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, Integer.parseInt(mprofessorField[0].getText()));

					int flag;
					flag = pstmt.executeUpdate();
					if (flag == 0)
						JOptionPane.showMessageDialog(null, "There does not exist a professor.");
				} catch (Exception error) {

				}

				for (int i = 0; i < 8; i++)
					mprofessorField[i].setText("");

				showProfessor.doClick();
				closeDB();
			}

			if (button == studentRadio[0] || button == studentRadio[1]) {

				for (int i = 0; i < 9; i++) {
					mstudentField[i].setVisible(true);
					mstudentLabel[i].setVisible(true);
				}

				if (button == studentRadio[0]) {
					studentInsert.setVisible(true);
					studentUpdate.setVisible(false);
				} else {
					studentInsert.setVisible(false);
					studentUpdate.setVisible(true);
				}

				studentDelete.setVisible(false);
			}

			else if (button == studentRadio[2]) {
				for (int i = 0; i < 9; i++) {
					mstudentField[i].setVisible(false);
					mstudentLabel[i].setVisible(false);
				}
				mstudentLabel[0].setVisible(true);
				mstudentField[0].setVisible(true);

				studentInsert.setVisible(false);
				studentUpdate.setVisible(false);
				studentDelete.setVisible(true);
			}

			if (button == studentInsert) {
				connectDB();
				String sql = "insert into Student values(?, ?, ?, ?, ?, ?, ?, ?, ?)";

				try {
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, Integer.parseInt(mstudentField[0].getText()));
					for (int i = 2; i <= 6; i++)
						pstmt.setString(i, mstudentField[i - 1].getText());
					pstmt.setInt(7, Integer.parseInt(mstudentField[6].getText()));
					pstmt.setInt(8, Integer.parseInt(mstudentField[7].getText()));
					pstmt.setInt(9, Integer.parseInt(mstudentField[8].getText()));

					pstmt.executeUpdate();
				} catch (Exception error) {
					// if (error.getMessage().substring(0, 9).equals("Duplicate"))
					// JOptionPane.showMessageDialog(null, "There alreay exists a sNumber.");
					// else if (error.getMessage().substring(0, 6).equals("Cannot"))
					// JOptionPane.showMessageDialog(null, "There do not exist sDepartment or
					// pDepartment.");
					if (error.getMessage().substring(0, 9).equals("ORA-00001"))
						JOptionPane.showMessageDialog(null, "There alreay exists a sNumber.");
					else if (error.getMessage().substring(0, 9).equals("ORA-02291"))
						JOptionPane.showMessageDialog(null, "There do not exist sDepartment or pDepartment.");
				}

				for (int i = 0; i < 9; i++)
					mstudentField[i].setText("");

				showStudent.doClick();
				closeDB();
			}

			if (button == studentUpdate) {
				connectDB();
				String sql = "update Student set sName = ?, sCivilnumber =?, sAddress = ?, sPhone = ?, sEmail=?, sPicture = ?, sDepartment = ?, sProfessor =? where sNumber = ?";

				try {
					pstmt = conn.prepareStatement(sql);
					for (int i = 1; i <= 5; i++)
						pstmt.setString(i, mstudentField[i].getText());

					for (int i = 6; i <= 9; i++) {
						if (mstudentField[i % 9].getText().equals(""))
							mstudentField[i % 9].setText("-1");
						pstmt.setInt(i, Integer.parseInt(mstudentField[i % 9].getText()));
					}
					if (pstmt.executeUpdate() == 0)
						JOptionPane.showMessageDialog(null, "Check a sNumber.");
				} catch (Exception error) {
					JOptionPane.showMessageDialog(null, "Check sDepartment or sProfessor.");
				}

				for (int i = 0; i < 9; i++)
					mstudentField[i].setText("");

				showStudent.doClick();
				closeDB();
			}

			if (button == studentDelete) {
				connectDB();
				String sql = "delete from Student where sNumber = ?";
				try {
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, Integer.parseInt(mstudentField[0].getText()));

					int flag;
					flag = pstmt.executeUpdate();
					if (flag == 0)
						JOptionPane.showMessageDialog(null, "There does not exists a Student.");
				} catch (Exception error) {

				}

				for (int i = 0; i < 8; i++)
					mstudentField[i].setText("");

				showStudent.doClick();
				closeDB();
			}

			if (button == lectureRadio[0] || button == lectureRadio[1]) {

				for (int i = 0; i < 7; i++) {
					lectureField[i].setVisible(true);
					lectureLabel[i].setVisible(true);
				}

				if (button == lectureRadio[0]) {
					lectureInsert.setVisible(true);
					lectureUpdate.setVisible(false);
				} else {
					lectureInsert.setVisible(false);
					lectureUpdate.setVisible(true);
				}

				lectureDelete.setVisible(false);
			}

			else if (button == lectureRadio[2]) {
				for (int i = 0; i < 7; i++) {
					lectureField[i].setVisible(false);
					lectureLabel[i].setVisible(false);
				}

				lectureField[0].setVisible(true);
				lectureField[1].setVisible(true);
				lectureLabel[0].setVisible(true);
				lectureLabel[1].setVisible(true);

				lectureInsert.setVisible(false);
				lectureUpdate.setVisible(false);
				lectureDelete.setVisible(true);
			}

			if (button == lectureInsert) {
				connectDB();
				String sql = "insert into Lecture values(?, ?, ?, ?, ?, ?, ?)";

				try {
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, Integer.parseInt(lectureField[0].getText()));
					pstmt.setInt(2, Integer.parseInt(lectureField[1].getText()));
					pstmt.setString(3, lectureField[2].getText());
					pstmt.setInt(4, Integer.parseInt(lectureField[3].getText()));
					pstmt.setString(5, lectureField[4].getText());
					pstmt.setString(6, lectureField[5].getText());
					pstmt.setInt(7, Integer.parseInt(lectureField[6].getText()));

					pstmt.executeUpdate();
				} catch (Exception error) {
					// if (error.getMessage().substring(0, 9).equals("Duplicate"))
					// JOptionPane.showMessageDialog(null, "<html><pre>There already exists a
					// Lecture.<br>Check PKs.</pre></html>");
					// else if (error.getMessage().substring(0, 6).equals("Cannot")) {
					// JOptionPane.showMessageDialog(null, "There do not exist lProfessor or
					// lDepartment.");

					if (error.getMessage().substring(0, 9).equals("ORA-00001"))
						JOptionPane.showMessageDialog(null, "<html><pre>There already exists a Lecture.<br>Check PKs.</pre></html>");
					else if (error.getMessage().substring(0, 9).equals("ORA-02291")) {
						JOptionPane.showMessageDialog(null, "There do not exist lProfessor or lDepartment.");
					}
				}

				for (int i = 0; i < 7; i++)
					lectureField[i].setText("");

				showLecture.doClick();
				closeDB();
			}

			if (button == lectureUpdate) {
				connectDB();
				String sql = "update Lecture set lName = ?, lCredit =?, lTime = ?, lPlace = ?, lDepartment = ? where lNumber = ? and lProfessor = ?";

				try {
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, lectureField[2].getText());
					if (lectureField[3].getText().equals(""))
						lectureField[3].setText("-1");
					pstmt.setInt(2, Integer.parseInt(lectureField[3].getText()));
					pstmt.setString(3, lectureField[4].getText());
					pstmt.setString(4, lectureField[5].getText());
					pstmt.setString(5, lectureField[6].getText());
					if (lectureField[0].getText().equals(""))
						lectureField[0].setText("-1");
					pstmt.setInt(6, Integer.parseInt(lectureField[0].getText()));
					if (lectureField[1].getText().equals(""))
						lectureField[1].setText("-1");
					pstmt.setInt(7, Integer.parseInt(lectureField[1].getText()));

					if (pstmt.executeUpdate() == 0)
						JOptionPane.showMessageDialog(null, "Check lNumber and lProfessor.");
				} catch (Exception error) {
					JOptionPane.showMessageDialog(null, "Check a lDepartment.");
				}

				for (int i = 0; i < 7; i++)
					lectureField[i].setText("");

				showLecture.doClick();
				closeDB();
			}

			if (button == lectureDelete) {
				connectDB();
				String sql = "delete from Lecture where lNumber = ? and lProfessor = ?";
				try {
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, Integer.parseInt(lectureField[0].getText()));
					pstmt.setInt(2, Integer.parseInt(lectureField[1].getText()));

					int flag;
					flag = pstmt.executeUpdate();
					if (flag == 0)
						JOptionPane.showMessageDialog(null, "There does not exist a Lecture.");
				} catch (Exception error) {

				}

				for (int i = 0; i < 7; i++)
					lectureField[i].setText("");

				showLecture.doClick();
				closeDB();
			}

			if (button == courseRadio[0] || button == courseRadio[1]) {

				for (int i = 0; i < 9; i++) {
					courseField[i].setVisible(true);
					courseLabel[i].setVisible(true);
				}

				if (button == courseRadio[0]) {
					courseInsert.setVisible(true);
					courseUpdate.setVisible(false);
				} else {
					courseInsert.setVisible(false);
					courseUpdate.setVisible(true);
				}

				courseDelete.setVisible(false);
			}

			else if (button == courseRadio[2]) {
				for (int i = 3; i < 9; i++) {
					courseField[i].setVisible(false);
					courseLabel[i].setVisible(false);
				}

				for (int i = 0; i < 3; i++) {
					courseField[i].setVisible(true);
					courseLabel[i].setVisible(true);
				}

				courseInsert.setVisible(false);
				courseUpdate.setVisible(false);
				courseDelete.setVisible(true);
			}

			if (button == courseInsert) {
				connectDB();
				String sql = "insert into Course values(?, ?, ?, ?, ?, ?, ?, ?, ?)";

				try {
					pstmt = conn.prepareStatement(sql);
					for (int i = 0; i <= 7; i++)
						pstmt.setInt(i + 1, Integer.parseInt(courseField[i].getText()));
					pstmt.setString(9, courseField[8].getText());

					pstmt.executeUpdate();
				} catch (Exception error) {
					// if (error.getMessage().substring(0, 9).equals("Duplicate"))
					// JOptionPane.showMessageDialog(null, "<html><pre>There already exists Course
					// information.<br>Check PKs.</pre></html>");
					// else if (error.getMessage().substring(0, 6).equals("Cannot")) {
					// JOptionPane.showMessageDialog(null, "There do not exist some PK.");

					if (error.getMessage().substring(0, 9).equals("ORA-00001"))
						JOptionPane.showMessageDialog(null, "<html><pre>There already exists Course information.<br>Check PKs.</pre></html>");
					else if (error.getMessage().substring(0, 9).equals("ORA-02291")) {
						JOptionPane.showMessageDialog(null, "There do not exist some PK.");
					}
				}

				for (int i = 0; i < 9; i++)
					courseField[i].setText("");

				showCourse.doClick();
				closeDB();
			}

			if (button == courseUpdate) {
				connectDB();
				String sql = "update Course set cMiddle = ?, cFinal =?, cOthers = ?, cAttendance = ?, cTotal = ?, cGrade = ? where cStudent = ? and cProfessor = ? and cLecture = ?";

				try {
					pstmt = conn.prepareStatement(sql);
					for (int i = 0; i <= 7; i++) {
						if (courseField[i].getText().equals(""))
							courseField[i].setText("-1");
					}

					for (int i = 1; i <= 9; i++) {
						if (i == 6)
							continue;
						pstmt.setInt(i, Integer.parseInt(courseField[(i + 2) % 9].getText()));
					}
					pstmt.setString(6, courseField[8].getText());

					if (pstmt.executeUpdate() == 0)
						JOptionPane.showMessageDialog(null, "Check PKs.");
				} catch (Exception error) {
					error.printStackTrace();
				}

				for (int i = 0; i < 9; i++)
					courseField[i].setText("");

				showCourse.doClick();
				closeDB();
			}

			if (button == courseDelete) {
				connectDB();
				String sql = "delete from Course where cStudent = ? and cProfessor = ? and cLecture = ?";
				try {
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, Integer.parseInt(courseField[0].getText()));
					pstmt.setInt(2, Integer.parseInt(courseField[1].getText()));
					pstmt.setInt(3, Integer.parseInt(courseField[2].getText()));

					if (pstmt.executeUpdate() == 0)
						JOptionPane.showMessageDialog(null, "There does not exist Course information.");
				} catch (Exception error) {

				}

				for (int i = 0; i < 8; i++)
					courseField[i].setText("");

				showCourse.doClick();
				closeDB();
			}

			if (button == supervisionRadio[0] || button == supervisionRadio[1]) {
				supervisionField[1].setVisible(true);
				supervisionField[2].setVisible(true);
				supervisionLabel[1].setVisible(true);
				supervisionLabel[2].setVisible(true);

				if (button == supervisionRadio[0]) {
					supervisionInsert.setVisible(true);
					supervisionUpdate.setVisible(false);
				} else {
					supervisionInsert.setVisible(false);
					supervisionUpdate.setVisible(true);
				}

				supervisionDelete.setVisible(false);
			}

			else if (button == supervisionRadio[2]) {
				supervisionField[1].setVisible(false);
				supervisionField[2].setVisible(false);
				supervisionLabel[1].setVisible(false);
				supervisionLabel[2].setVisible(false);
				supervisionInsert.setVisible(false);
				supervisionUpdate.setVisible(false);
				supervisionDelete.setVisible(true);
			}

			if (button == supervisionInsert) {
				connectDB();

				boolean value = false;
				String sql = "select sNumber, sProfessor from Student\r\n" + "where sNumber = ? and sProfessor = ?";
				SupervisionStructor s = null;

				try {
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, Integer.parseInt(supervisionField[0].getText()));
					pstmt.setInt(2, Integer.parseInt(supervisionField[1].getText()));
					rs = pstmt.executeQuery();

					rs.next();
					s = new SupervisionStructor();
					s.setSuperstudent(rs.getInt("sNumber"));
					s.setSuperprofessor(rs.getInt("sProfessor"));
					value = true;
				} catch (Exception error) {
					if (s.getSuperstudent() == 0 && s.getSuperprofessor() == 0) {
						JOptionPane.showMessageDialog(null, "Check sNumber and sProfessor in StudentTable.");
						value = false;
					}
				}

				if (value) {
					sql = "insert into Supervision values(?, ?, ?)";
					try {
						pstmt = conn.prepareStatement(sql);
						pstmt.setInt(1, s.getSuperstudent());
						pstmt.setInt(2, s.getSuperprofessor());
						pstmt.setString(3, supervisionField[2].getText());

						pstmt.executeUpdate();
					} catch (Exception error) {
						JOptionPane.showMessageDialog(null, "There already exists Information.");
					}

					for (int i = 0; i < 3; i++)
						supervisionField[i].setText("");

				}
				showSupervision.doClick();
				closeDB();
			}

			if (button == supervisionUpdate) {
				connectDB();
				String sql = "update Supervision set superTerm = ? where superStudent = ? and superProfessor = ?";

				try {
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, supervisionField[2].getText());
					pstmt.setInt(2, Integer.parseInt(supervisionField[0].getText()));
					pstmt.setInt(3, Integer.parseInt(supervisionField[1].getText()));

					if (pstmt.executeUpdate() == 0)
						JOptionPane.showMessageDialog(null, "Check PKs.");
				} catch (Exception error) {
					error.getMessage();
				}

				for (int i = 0; i < 3; i++)
					supervisionField[i].setText("");

				showSupervision.doClick();
				closeDB();
			}

			if (button == supervisionDelete) {
				connectDB();
				String sql = "delete from Supervision where superStudent = ?";
				try {
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, Integer.parseInt(supervisionField[0].getText()));

					if (pstmt.executeUpdate() == 0)
						JOptionPane.showMessageDialog(null, "There does not exist a Student.");
				} catch (Exception error) {

				}

				for (int i = 0; i < 3; i++)
					supervisionField[i].setText("");

				showSupervision.doClick();
				closeDB();
			}
		}
	}
	
	public void initTuple() { // Data Init
		connectDB();

		String sql;
		try {
			stmt = conn.createStatement();
			sql = "delete from Course";
			stmt.executeUpdate(sql);

			sql = "delete from Supervision";
			stmt.executeUpdate(sql);

			sql = "delete from Lecture";
			stmt.executeUpdate(sql);

			sql = "delete from Student";
			stmt.executeUpdate(sql);

			sql = "delete from Professor";
			stmt.executeUpdate(sql);

			sql = "delete from Department";
			stmt.executeUpdate(sql);

			sql = "insert into Department Values(1, 'MM.', '070-1111-1111')";
			stmt.executeUpdate(sql);

			sql = "insert into Department Values(2, 'ET.', '070-2222-2222')";
			stmt.executeUpdate(sql);

			sql = "insert into Department Values(3, 'DS', '070-3333-3333')";
			stmt.executeUpdate(sql);

			sql = "insert into Department Values(4, 'KL.', '070-4444-4444')";
			stmt.executeUpdate(sql);

			sql = "insert into Department Values(5, 'NNM', '070-5555-5555')";
			stmt.executeUpdate(sql);

			sql = "insert into Department Values(6, 'ID', '070-6666-6666')";
			stmt.executeUpdate(sql);

			sql = "insert into Department Values(7, 'BT.', '070-7777-7777')";
			stmt.executeUpdate(sql);

			sql = "insert into Department Values(8, 'SW.', '070-8888-8888')";
			stmt.executeUpdate(sql);

			sql = "insert into Department Values(9, 'His', '070-9999-9999')";
			stmt.executeUpdate(sql);

			sql = "insert into Department Values(10, 'EnI', '070-1010-1010')";
			stmt.executeUpdate(sql);

			sql = "insert into Department Values(11, 'ITG', '070-1100-1100')";
			stmt.executeUpdate(sql);

			sql = "insert into Department Values(12, 'CE.', '070-1212-1212')";
			stmt.executeUpdate(sql);

			sql = "insert into Department Values(13, 'Adm', '070-1313-1313')";
			stmt.executeUpdate(sql);

			sql = "insert into Department Values(14, 'HK', '070-1414-1414')";
			stmt.executeUpdate(sql);

			sql = "insert into Department Values(15, 'Che', '070-1515-1515')";
			stmt.executeUpdate(sql);

			sql = "insert into Department Values(0, 'NULL', '070-000-0000')";
			stmt.executeUpdate(sql);

			sql = "insert into Professor Values(000000, 'Manager', '000000-0000000', '000', '000-0000-0000', '0@u.ac.kr', 0, 0)";
			stmt.executeUpdate(sql);

			sql = "insert into Professor Values(010001, 'Jun Ji-Hyun', '700110-2111111', 'SangGye', '010-6666-2222', '47@u.ac.kr', 47, 3)";
			stmt.executeUpdate(sql);

			sql = "insert into Professor Values(010002, 'Sim HoSuk', '730210-1111111', 'WolGye', '010-6666-3333', '48@u.ac.kr', 48, 1)";
			stmt.executeUpdate(sql);

			sql = "insert into Professor Values(020001, 'HAN Ga-In', '750310-2222222', 'JoogGye', '010-6666-4444', '49@u.ac.kr', 49, 7)";
			stmt.executeUpdate(sql);

			sql = "insert into Professor Values(040001, 'Song Eun-Hee', '760410-2333333', 'HaGye', '010-6666-5555', '50@u.ac.kr', 50, 8)";
			stmt.executeUpdate(sql);

			sql = "insert into Professor Values(030001, 'Park Mi-Sun', '710510-2444444', 'NagnChun', '010-6666-6666', '51@u.ac.kr', 51, 4)";
			stmt.executeUpdate(sql);

			sql = "insert into Professor Values(050001, 'Lee Yong-Ja', '740610-2555555', 'DaeSin', '010-6666-7777', '52@u.ac.kr', 52, 2)";
			stmt.executeUpdate(sql);

			sql = "insert into Professor Values(030002, 'Song Hye-Kyo', '780710-2666666', 'YungChun', '010-6666-8888', '53@u.ac.kr', 53, 10)";
			stmt.executeUpdate(sql);

			sql = "insert into Professor Values(040002, 'Bang Si-Hyuk', '770810-1222222', 'YeonHee', '010-6666-9999', '54@u.ac.kr', 54, 6)";
			stmt.executeUpdate(sql);

			sql = "insert into Professor Values(010003, 'Jeon HyunMu', '790910-1333333', 'HongEun', '010-7777-1111', '55@u.ac.kr', 55, 11)";
			stmt.executeUpdate(sql);

			sql = "insert into Professor Values(050002, 'Yoon JongSu', '721110-1444444', 'GaSan', '010-7777-2222', '56@u.ac.kr', 56, 7)";
			stmt.executeUpdate(sql);

			sql = "insert into Professor Values(030003, 'Park Na-Rae', '711210-2777777', 'SiHeung', '010-7777-3333', '57@u.ac.kr', 57, 13)";
			stmt.executeUpdate(sql);

			sql = "insert into Professor Values(020002, 'JunWooSun', '750110-1555555', 'BangHak', '010-7777-4444', '58@u.ac.kr', 58, 5)";
			stmt.executeUpdate(sql);

			sql = "insert into Professor Values(040003, 'Son Ye-Jin', '740210-2888888', 'SsangMun', '010-7777-5555', '59@u.ac.kr', 59, 14)";
			stmt.executeUpdate(sql);

			sql = "insert into Professor Values(020003, 'Yoo Jae-Suk', '770310-1666666', 'ChunYeon', '010-7777-6666', '60@u.ac.kr', 60, 15)";
			stmt.executeUpdate(sql);

			sql = "insert into Professor Values(050003, 'Park Jin-Young', '740410-1777777', 'NaeGok', '010-7777-7777', '61@u.ac.kr', 61, 9)";
			stmt.executeUpdate(sql);

			sql = "insert into Professor Values(010004, 'yun Su-Jin', '700510-1888888', 'BangBae', '010-7777-8888', '62@u.ac.kr', 62, 12)";
			stmt.executeUpdate(sql);

			sql = "insert into Professor Values(010005, 'Cho ChanWoo', '730610-1999999', 'SuCho', '010-7777-9999', '63@u.ac.kr', 63, 2)";
			stmt.executeUpdate(sql);

			sql = "insert into Professor Values(020004, 'Jung You-Ra', '720710-2999999', 'JamWon', '010-8888-1111', '64@u.ac.kr', 64, 15)";
			stmt.executeUpdate(sql);

			sql = "insert into Professor Values(040012, 'Lee Soon-Jae', '270001-1001234', 'ChunHo', '010-5123-4689', '65@u.ac.kr', 99, 8)";
			stmt.executeUpdate(sql);

			sql = "insert into Professor Values(030004, 'Lee Sang-Bum', '710910-1222222', 'YangJae', '010-8888-3333', '66@u.ac.kr', 66, 5)";
			stmt.executeUpdate(sql);

			sql = "insert into Professor Values(050004, 'Lee Hoi-Yeon', '771010-2211111', 'JamWon', '010-8888-4444', '67@u.ac.kr', 67, 10)";
			stmt.executeUpdate(sql);

			sql = "insert into Professor Values(030005, 'ParkSangHyun', '751110-1233333', 'ToJeong', '010-8888-5555', '68@u.ac.kr', 68, 1)";
			stmt.executeUpdate(sql);

			sql = "insert into Professor Values(040005, 'Kim Sung-Yup', '781210-1244444', 'YangJae', '010-8888-6666', '69@u.ac.kr', 69, 13)";
			stmt.executeUpdate(sql);

			sql = "insert into Professor Values(010006, 'Hong WonHo', '790110-1255555', 'PoonNap', '010-8888-7777', '70@u.ac.kr', 70, 4)";
			stmt.executeUpdate(sql);

			sql = "insert into Professor Values(050005, 'Noh HeeJu', '730310-1266666', 'SukKwan', '010-8888-8888', '71@u.ac.kr', 71, 14)";
			stmt.executeUpdate(sql);

			sql = "insert into Professor Values(030006, 'JungMinYoung', '700410-1277777', 'JongAm', '010-8888-9999', '72@u.ac.kr', 72, 9)";
			stmt.executeUpdate(sql);

			sql = "insert into Professor Values(020005, 'Kim Hye-Soo', '760510-2222221', 'JeongNeung', '010-9999-1111', '73@u.ac.kr', 73, 12)";
			stmt.executeUpdate(sql);

			sql = "insert into Professor Values(040006, 'Park Ji-Yoon', '730710-2233333', 'YangJae', '010-9999-2222', '74@u.ac.kr', 74, 3)";
			stmt.executeUpdate(sql);

			sql = "insert into Professor Values(020006, 'Kim Jun-Hyuk', '780810-1288888', 'YungChun', '010-9999-3333', '75@u.ac.kr', 75, 6)";
			stmt.executeUpdate(sql);

			sql = "insert into Professor Values(050006, 'Kwon DoHee', '720910-2244444', 'GongNeung', '010-9999-4444', '76@u.ac.kr', 76, 11)";
			stmt.executeUpdate(sql);

			sql = "insert into Student Values(13090001, 'Jung Ho-Suk', '940110-1111111', 'NohYoo', '010-1111-1111', '1@naver.com',  1, 9, 030006)";
			stmt.executeUpdate(sql);

			sql = "insert into Student Values(19140003, 'Kim Da-Hyun', '000110-4111111', 'GaePo', '010-3333-1111', '19@naver.com', 19, 14, 050005)";
			stmt.executeUpdate(sql);

			sql = "insert into Student Values(18040003, 'Bae Joo-Hyun', '990110-2122222', 'NonHy', '010-3333-2222', '20@naver.com', 20, 4, 030001)";
			stmt.executeUpdate(sql);

			sql = "insert into Student Values(14020001, 'Min Yoon-Ki', '950110-1222222', 'Nyeon',   '010-1111-2222', '2@naver.com',  2, 2, 050001)";
			stmt.executeUpdate(sql);

			sql = "insert into Student Values(19110004, 'Han MiYeon', '000110-4233333', 'ilWon', '010-4444-3333', '30@naver.com', 30, 11, 010003)";
			stmt.executeUpdate(sql);

			sql = "insert into Student Values(13080004, 'Ki Hyo-Yeon', '940110-2244444', 'JaGok', '010-4444-4444', '31@naver.com', 31, 8, 040001)";
			stmt.executeUpdate(sql);

			sql = "insert into Student Values(15100001, 'Ki TaeYung', '960110-1333333', 'MoJin', '010-1111-3333', '3@naver.com',  3, 10, 030002)";
			stmt.executeUpdate(sql);

			sql = "insert into Student Values(16030001, 'Jun Jung-Kook', '970110-1444444', 'JaYang', '010-1111-4444', '4@naver.com',  4, 3, 010001)";
			stmt.executeUpdate(sql);

			sql = "insert into Student Values(18150005, 'Park Kyung-Ri', '990110-2299999', 'HaGye', '010-4444-9999', '36@naver.com', 36, 15, 020003)";
			stmt.executeUpdate(sql);

			sql = "insert into Student Values(19140005, 'Pyo Hye-Ri', '000110-4311111', 'Myungil', '010-5555-1111', '37@naver.com', 37, 14, 040003)";
			stmt.executeUpdate(sql);

			sql = "insert into Student Values(19040006, 'Jo So-Jin', '000110-4322222', 'Sangil', '010-5555-2222', '38@naver.com', 38, 4, 010006)";
			stmt.executeUpdate(sql);

			sql = "insert into Student Values(17070001, 'Kim Nam-Jun', '980110-1555555', 'JoonG', '010-1111-5555', '5@naver.com',  5, 7, 050002)";
			stmt.executeUpdate(sql);

			sql = "insert into Student Values(15050004, 'Park Ji-Hyo', '960110-2155555', 'GaSan', '010-3333-5555', '23@naver.com', 23, 5, 020002)";
			stmt.executeUpdate(sql);

			sql = "insert into Student Values(14120004, 'Park Ji-Min', '950110-2166666', 'SeGok', '010-3333-6666', '24@naver.com', 24, 12, 020005)";
			stmt.executeUpdate(sql);

			sql = "insert into Student Values(18130001, 'Kim Suk-Jin', '990110-1666666', 'SuCho', '010-1111-6666', '6@naver.com',  6, 13, 040005)";
			stmt.executeUpdate(sql);

			sql = "insert into Student Values(17060006, 'Seo Joo-Hyun', '980110-2288888', 'Gil',   '010-4444-8888', '35@naver.com', 35, 6, 040002)";
			stmt.executeUpdate(sql);

			sql = "insert into Student Values(19090001, 'Kim Min-Joon', '000110-3777777', 'GaYa', '010-1111-7777', '7@naver.com',  7, 9, 050003)";
			stmt.executeUpdate(sql);

			sql = "insert into Student Values(13020002, 'Yok Tak-Yeon', '940110-1888888', 'GaeW', '010-1111-8888', '7@naver.com',  8, 2, 010005)";
			stmt.executeUpdate(sql);

			sql = "insert into Student Values(14100002, 'Jo WooYung', '950110-1999999', 'Chun', '010-1111-9999', '9@naver.com',  9, 10, 050004)";
			stmt.executeUpdate(sql);

			sql = "insert into Student Values(17010007, 'Bang Min-Ah', '980110-2388888', 'YangJ', '010-5555-7777', '43@naver.com', 43, 1, 010002)";
			stmt.executeUpdate(sql);

			sql = "insert into Student Values(16060007, 'Lee Hye-Ri', '970110-2399999', 'Guui', '010-5555-8888', '44@naver.com', 44, 6, 020006)";
			stmt.executeUpdate(sql);

			sql = "insert into Student Values(16150008, 'KangMinKyung', '970110-2411111', 'GunJa', '010-5555-9999', '45@naver.com', 45, 15,020004)";
			stmt.executeUpdate(sql);

			sql = "insert into Student Values(16080004, 'Jang WonYung', '970110-2144444', 'SeGok', '010-3333-4444', '22@naver.com', 21, 8, 040002)";
			stmt.executeUpdate(sql);

			sql = "insert into Student Values(15030002, 'Kim Joon-Ho', '960110-1211111', 'GwaH', '010-2222-1111', '10@naver.com', 10, 3, 040006)";
			stmt.executeUpdate(sql);

			sql = "insert into Student Values(16010006, 'Lim Yoon-Ah', '970110-2277777', 'GoDuk', '010-4444-7777', '34@naver.com', 34, 1, 030005)";
			stmt.executeUpdate(sql);

			sql = "insert into Student Values(16070002, 'Lee ChanSu', '970110-1222222', 'JamW', '010-2222-2222', '11@naver.com', 11, 7, 020001)";
			stmt.executeUpdate(sql);

			sql = "insert into Student Values(17130002, 'Park Jung-Soo', '980110-1233333', 'MaGok', '010-2222-3333', '12@naver.com', 12, 13, 030003)";
			stmt.executeUpdate(sql);

			sql = "insert into Student Values(14080006, 'Son Sung-Ah', '950110-2355555', 'AmSa', '010-5555-4444', '40@naver.com', 40, 8, 040001)";
			stmt.executeUpdate(sql);

			sql = "insert into Student Values(18050006, 'Park So-Jin', '990110-2366666', 'Chun', '010-5555-5555', '41@naver.com', 41, 5, 030004)";
			stmt.executeUpdate(sql);

			sql = "insert into Student Values(18090002, 'Kim Hee-Chul', '990110-1244444', 'BanPo', '010-2222-4444', '13@naver.com', 13, 9, 050003)";
			stmt.executeUpdate(sql);

			sql = "insert into Student Values(16150005, 'Son Mi-Yung', '970110-2199999', 'Guui', '010-3333-9999', '27@naver.com', 27, 15, 020004)";
			stmt.executeUpdate(sql);

			sql = "insert into Student Values(17140005, 'Kim Tae-Yeon', '980110-2211111', 'YeokS', '010-4444-1111', '28@naver.com', 28, 14, 040003)";
			stmt.executeUpdate(sql);

			sql = "insert into Student Values(18040004, 'Lee Soon-Kyo', '990110-2222222', 'SinNa', '010-4444-2222', '29@naver.com', 29, 4, 010006)";
			stmt.executeUpdate(sql);

			sql = "insert into Student Values(19020002, 'KimJongWoon', '000110-3255555', 'GaSan', '010-2222-5555', '14@naver.com', 14, 2, 050001)";
			stmt.executeUpdate(sql);

			sql = "insert into Student Values(14050005, 'Kwon Yoo-Ri', '950110-2255555', 'AmSa', '010-4444-5555', '32@naver.com', 32, 5, 020002)";
			stmt.executeUpdate(sql);

			sql = "insert into Student Values(15120006, 'Lim SooYung', '960110-2266666', 'Poi', '010-4444-6666', '33@naver.com', 33, 12, 010004)";
			stmt.executeUpdate(sql);

			sql = "insert into Student Values(14100003, 'Lee Hyuk-Jae', '950110-1266666', 'OGok', '010-2222-6666', '15@naver.com', 15, 10, 030002)";
			stmt.executeUpdate(sql);

			sql = "insert into Student Values(13110005, 'Lee Geum-Jo', '940110-2344444', 'Jang', '010-5555-3333', '39@naver.com', 39, 11, 050006)";
			stmt.executeUpdate(sql);

			sql = "insert into Student Values(15120007, 'Kim Ah-Yung', '960110-2377777', 'Ha-il', '010-5555-6666', '42@naver.com', 42, 12, 020005)";
			stmt.executeUpdate(sql);

			sql = "insert into Student Values(17110004, 'Lim Na-Yeon', '980110-2133333', 'DaeC', '010-3333-3333', '21@naver.com', 21, 11, 010003)";
			stmt.executeUpdate(sql);

			sql = "insert into Student Values(15030003, 'Lee BoHae', '960110-1277777', 'OShoi', '010-2222-7777', '16@naver.com', 16, 3, 010001)";
			stmt.executeUpdate(sql);

			sql = "insert into Student Values(16070003, 'Choi Si-Won', '970110-1288888', 'HwaG', '010-2222-8888', '17@naver.com', 17, 7, 050002)";
			stmt.executeUpdate(sql);

			sql = "insert into Student Values(13010003, 'Yoo JuYeon', '940110-2177777', 'SuSeo', '010-3333-7777', '25@naver.com', 25, 1, 010002)";
			stmt.executeUpdate(sql);

			sql = "insert into Student Values(15060005, 'Kim Ji-Soo', '960110-2188888', 'SinSa', '010-3333-8888', '26@naver.com', 26, 6, 040002)";
			stmt.executeUpdate(sql);

			sql = "insert into Student Values(17130003, 'Kim RyeWuk', '980110-1299999', 'SinRim', '010-2222-9999', '18@naver.com', 18, 13, 030003)";
			stmt.executeUpdate(sql);

			sql = "insert into Lecture values(10, 30005, 'MA', 3, 'M, W 10:30', 'Kwang720', 1)";
			stmt.executeUpdate(sql);

			sql = "insert into Lecture values(10, 10002, 'MA', 3, 'T, T 13:30', 'Kwang719', 1)";
			stmt.executeUpdate(sql);

			sql = "insert into Lecture values(11, 30005, 'FM', 3, 'M, W 13:30', 'Kwang807', 1)";
			stmt.executeUpdate(sql);

			sql = "insert into Lecture values(11, 10002, 'FM', 3, 'T, T 12:00', 'Kwang202', 1)";
			stmt.executeUpdate(sql);

			sql = "insert into Lecture values(20, 10005, 'PoE', 3, 'T, T 16:30', 'Jip303', 2)";
			stmt.executeUpdate(sql);

			sql = "insert into Lecture values(20, 50001, 'PoE', 3, 'T, T 09:00', 'Jip304', 2)";
			stmt.executeUpdate(sql);

			sql = "insert into Lecture values(21, 10005, 'TBA', 3, 'T, T 15:00', 'Jip303', 2)";
			stmt.executeUpdate(sql);

			sql = "insert into Lecture values(21, 50001, 'TBA', 3, 'M, W 16:30', 'Jip308', 2)";
			stmt.executeUpdate(sql);

			sql = "insert into Lecture values(30, 10001, 'SW', 3, 'T, T 16:30', 'Kwang801', 3)";
			stmt.executeUpdate(sql);

			sql = "insert into Lecture values(30, 40006, 'SW', 3, 'M, W 10:30', 'Kwang801', 3)";
			stmt.executeUpdate(sql);

			sql = "insert into Lecture values(31, 10001, 'ME', 3, 'M, W 16:30', 'Kwang428', 3)";
			stmt.executeUpdate(sql);

			sql = "insert into Lecture values(31, 40006, 'ME', 3, 'T, T 15:00', 'Yul104', 3)";
			stmt.executeUpdate(sql);

			sql = "insert into Lecture values(40, 10006, 'KG', 3, 'M, W 12:00', 'Jip301', 4)";
			stmt.executeUpdate(sql);

			sql = "insert into Lecture values(40, 30001, 'KG', 3, 'T, T 15:00', 'Jip301', 4)";
			stmt.executeUpdate(sql);

			sql = "insert into Lecture values(41, 10006, 'RET', 3, 'M, W 16:30', 'Jip301', 4)";
			stmt.executeUpdate(sql);

			sql = "insert into Lecture values(41, 30001, 'RET', 3, 'T, T 10:30', 'Jip301', 4)";
			stmt.executeUpdate(sql);

			sql = "insert into Lecture values(50, 30004, 'MT', 3, 'M, W 16:30', 'Choong201', 5)";
			stmt.executeUpdate(sql);

			sql = "insert into Lecture values(50, 20002, 'MT', 3, 'T, T 10:30', 'Choong103A', 5)";
			stmt.executeUpdate(sql);

			sql = "insert into Lecture values(51, 30004, 'SP', 3, 'M, W 13:30', 'Choong209A', 5)";
			stmt.executeUpdate(sql);

			sql = "insert into Lecture values(51, 20002, 'SP', 3, 'T, T 15:00', 'Choong401', 5)";
			stmt.executeUpdate(sql);

			sql = "insert into Lecture values(60, 40002, 'iDD', 3, 'M, W 12:00', 'Se205', 6)";
			stmt.executeUpdate(sql);

			sql = "insert into Lecture values(60, 20006, 'iDD', 3, 'T, T 13:30', 'Se205', 6)";
			stmt.executeUpdate(sql);

			sql = "insert into Lecture values(61, 40002, 'PD', 3, 'M, W 09:00', 'Se205', 6)";
			stmt.executeUpdate(sql);

			sql = "insert into Lecture values(61, 20006, 'PD', 3, 'T, T 15:00', 'Se205', 6)";
			stmt.executeUpdate(sql);

			sql = "insert into Lecture values(70, 50002, 'BF', 3, 'M, W 10:30', 'Choong604', 7)";
			stmt.executeUpdate(sql);

			sql = "insert into Lecture values(70, 20001, 'BF', 3, 'T, T 13:30', 'Choong211', 7)";
			stmt.executeUpdate(sql);

			sql = "insert into Lecture values(71, 50002, 'GGe', 3, 'M, W 16:30', 'Choong105A', 7)";
			stmt.executeUpdate(sql);

			sql = "insert into Lecture values(71, 20001, 'GGe', 3, 'T, T 12:00', 'Goon319', 7)";
			stmt.executeUpdate(sql);

			sql = "insert into Lecture values(80, 40001, 'MM', 3, 'M, W 09:00', 'CenB112', 8)";
			stmt.executeUpdate(sql);

			sql = "insert into Lecture values(80, 40012, 'MM', 3, 'T, T 12:00', 'CenB102', 8)";
			stmt.executeUpdate(sql);

			sql = "insert into Lecture values(81, 40001, 'CG', 3, 'M, W 16:30', 'CenB101', 8)";
			stmt.executeUpdate(sql);

			sql = "insert into Lecture values(81, 40012, 'CG', 3, 'T, T 12:00', 'CenB111', 8)";
			stmt.executeUpdate(sql);

			sql = "insert into Lecture values(90, 50003, 'His', 3, 'M, W 15:00', 'Jip307', 9)";
			stmt.executeUpdate(sql);

			sql = "insert into Lecture values(90, 30006, 'His', 3, 'T, T 13:30', 'Jip307', 9)";
			stmt.executeUpdate(sql);

			sql = "insert into Lecture values(91, 50003, 'KMH', 3, 'M, W 16:30', 'Jip307', 9)";
			stmt.executeUpdate(sql);

			sql = "insert into Lecture values(91, 30006, 'KMH', 3, 'T, T 09:00', 'Jip307', 9)";
			stmt.executeUpdate(sql);

			sql = "insert into Lecture values(100, 50004, 'EC', 3, 'M, W 09:00', 'Choong202', 10)";
			stmt.executeUpdate(sql);

			sql = "insert into Lecture values(100, 30002, 'EC', 3, 'T, T 16:30', 'Choong905', 10)";
			stmt.executeUpdate(sql);

			sql = "insert into Lecture values(101, 50004, 'SCD', 3, 'M, W 13:30', 'Choong905', 10)";
			stmt.executeUpdate(sql);

			sql = "insert into Lecture values(101, 30002, 'SCD', 3, 'T, T 15:00', 'Choong202', 10)";
			stmt.executeUpdate(sql);

			sql = "insert into Lecture values(110, 10003, 'Lin', 3, 'M, W 16:30', 'CenB108', 11)";
			stmt.executeUpdate(sql);

			sql = "insert into Lecture values(110, 50006, 'Lin', 3, 'T, T 09:00', 'Kwang802', 11)";
			stmt.executeUpdate(sql);

			sql = "insert into Lecture values(111, 10003, 'Ai', 3, 'M, W 10:30', 'Goon319', 11)";
			stmt.executeUpdate(sql);

			sql = "insert into Lecture values(111, 50006, 'Ai', 3, 'T, T 13:30', 'CenB210', 11)";
			stmt.executeUpdate(sql);

			sql = "insert into Lecture values(120, 10004, 'DB', 3, 'T, T 10:30', 'CenB103', 12)";
			stmt.executeUpdate(sql);

			sql = "insert into Lecture values(120, 20005, 'DB', 3, 'M, W 12:00', 'ChoongB107', 12)";
			stmt.executeUpdate(sql);

			sql = "insert into Lecture values(121, 10004, 'CL', 4, 'M, W 10:30', 'CenB204', 12)";
			stmt.executeUpdate(sql);

			sql = "insert into Lecture values(121, 20005, 'CL', 4, 'T, T 13:30', 'Dong401', 12)";
			stmt.executeUpdate(sql);

			sql = "insert into Lecture values(130, 40005, 'CA', 3, 'M, W 13:30', 'Jip311', 13)";
			stmt.executeUpdate(sql);

			sql = "insert into Lecture values(130, 30003, 'CA', 3, 'T, T 15:00', 'Jip311', 13)";
			stmt.executeUpdate(sql);

			sql = "insert into Lecture values(131, 40005, 'Pol', 3, 'M, W 09:00', 'Jip311', 13)";
			stmt.executeUpdate(sql);

			sql = "insert into Lecture values(131, 30003, 'pol', 3, 'T, T 10:30', 'Jip311', 13)";
			stmt.executeUpdate(sql);

			sql = "insert into Lecture values(140, 40003, 'CP', 3, 'M, W 09:00', 'Kwang428', 14)";
			stmt.executeUpdate(sql);

			sql = "insert into Lecture values(140, 50005, 'CP', 3, 'T, T 13:30', 'Kwang428', 14)";
			stmt.executeUpdate(sql);

			sql = "insert into Lecture values(141, 40003, 'Alc', 3, 'M, W 12:00', 'Kwang428', 14)";
			stmt.executeUpdate(sql);

			sql = "insert into Lecture values(141, 50005, 'Alc', 3, 'T, T 16:30', 'Kwang428', 14)";
			stmt.executeUpdate(sql);

			sql = "insert into Lecture values(150, 20004, 'OC', 3, 'M, W 10:30', 'Yung401', 15)";
			stmt.executeUpdate(sql);

			sql = "insert into Lecture values(150, 20003, 'OC', 3, 'T, T 16:30', 'Yung401', 15)";
			stmt.executeUpdate(sql);

			sql = "insert into Lecture values(151, 20004, 'GB', 3, 'M, W 13:30', 'Yung401', 15)";
			stmt.executeUpdate(sql);

			sql = "insert into Lecture values(151, 20003, 'GB', 3, 'T, T 12:00', 'Yung401', 15)";
			stmt.executeUpdate(sql);

			sql = "insert into Course values(13010003, 30005, 10, 60, 85, 10, 10, 76, '4.5')";
			stmt.executeUpdate(sql);

			sql = "insert into Course values(13010003, 10002, 11, 90, 90, 8, 8, 88, '4.0')";
			stmt.executeUpdate(sql);

			sql = "insert into Course values(17010007, 30005, 10, 70, 70, 10, 10, 76, '3.5')";
			stmt.executeUpdate(sql);

			sql = "insert into Course values(17010007, 30005, 11, 90, 90, 10, 10, 92, '4.5')";
			stmt.executeUpdate(sql);

			sql = "insert into Course values(16010006, 10002, 10, 95, 60, 9, 10, 82, '4.5')";
			stmt.executeUpdate(sql);

			sql = "insert into Course values(16010006, 10002, 11, 70, 80, 10, 10, 79, '3.5')";
			stmt.executeUpdate(sql);

			sql = "insert into Course values(13020002, 50001, 20, 90, 60, 10, 10, 81, '4.0')";
			stmt.executeUpdate(sql);

			sql = "insert into Course values(13020002, 50001, 21, 70, 70, 8, 10, 74, '4.0')";
			stmt.executeUpdate(sql);

			sql = "insert into Course values(14020001, 10005, 20, 100, 90, 10, 10, 96, '4.5')";
			stmt.executeUpdate(sql);

			sql = "insert into Course values(14020001, 10005, 21, 100, 100, 10, 10, 100, '4.5')";
			stmt.executeUpdate(sql);

			sql = "insert into Course values(19020002, 10005, 20, 70, 70, 10, 10, 76, '3.5')";
			stmt.executeUpdate(sql);

			sql = "insert into Course values(14020001, 50001, 21, 80, 50, 10, 10, 73, '3.5')";
			stmt.executeUpdate(sql);

			sql = "insert into Course values(15030002, 10001, 30, 75, 60, 10, 10, 74, '4.0')";
			stmt.executeUpdate(sql);

			sql = "insert into Course values(15030002, 40006, 31, 100, 100, 7, 5, 92, '4.5')";
			stmt.executeUpdate(sql);

			sql = "insert into Course values(15030003, 40006, 30, 50, 80, 10, 10, 70, '3.5')";
			stmt.executeUpdate(sql);

			sql = "insert into Course values(15030003, 10001, 31, 70, 100, 5, 10, 81, '3.5')";
			stmt.executeUpdate(sql);

			sql = "insert into Course values(16030001, 40006, 30, 90, 80, 10, 10, 88, '4.5')";
			stmt.executeUpdate(sql);

			sql = "insert into Course values(16030001, 40006, 31, 80, 80, 10, 10, 84, '4.0')";
			stmt.executeUpdate(sql);

			sql = "insert into Course values(18040003, 10006, 40, 70, 50, 10, 10, 69, '3.5')";
			stmt.executeUpdate(sql);

			sql = "insert into Course values(18040003, 10006, 41, 70, 70, 10, 9, 75, '4.0')";
			stmt.executeUpdate(sql);

			sql = "insert into Course values(18040003, 30001, 40, 70, 60, 10, 10, 72, '4.0')";
			stmt.executeUpdate(sql);

			sql = "insert into Course values(18040003, 30001, 41, 70, 60, 10, 7, 69, '3.5')";
			stmt.executeUpdate(sql);

			sql = "insert into Course values(19040006, 10006, 40, 80, 90, 10, 10, 87, '4.5')";
			stmt.executeUpdate(sql);

			sql = "insert into Course values(19040006, 30001, 41, 95, 90, 10, 10, 94, '4.5')";
			stmt.executeUpdate(sql);

			sql = "insert into Course values(15050004, 30004, 50, 80, 50, 10, 10, 73, '3.5')";
			stmt.executeUpdate(sql);

			sql = "insert into Course values(15050004, 30004, 51, 80, 70, 10, 10, 80, '4.0')";
			stmt.executeUpdate(sql);

			sql = "insert into Course values(18050006, 20002, 50, 85, 70, 5, 10, 77, '4.0')";
			stmt.executeUpdate(sql);

			sql = "insert into Course values(18050006, 20002, 51, 75, 90, 8, 10, 83, '4.5')";
			stmt.executeUpdate(sql);

			sql = "insert into Course values(14050005, 30004, 50, 85, 60, 10, 10, 79, '4.5')";
			stmt.executeUpdate(sql);

			sql = "insert into Course values(14050005, 30004, 51, 90, 50, 10, 10, 78, '3.5')";
			stmt.executeUpdate(sql);

			sql = "insert into Course values(16060007, 20006, 60, 100, 100, 10, 10, 100, '4.5')";
			stmt.executeUpdate(sql);

			sql = "insert into Course values(15060005, 40002, 60, 95, 100, 10, 10, 97, '4.0')";
			stmt.executeUpdate(sql);

			sql = "insert into Course values(17060006, 40002, 60, 80, 100, 10, 10, 91, '3.5')";
			stmt.executeUpdate(sql);

			sql = "insert into Course values(16060007, 20006, 61, 100, 100, 10, 10, 100, '4.5')";
			stmt.executeUpdate(sql);

			sql = "insert into Course values(15060005, 40002, 61, 90, 95, 10, 10, 93, '4.0')";
			stmt.executeUpdate(sql);

			sql = "insert into Course values(17060006, 40002, 61, 90, 90, 10, 10, 92, '3.5')";
			stmt.executeUpdate(sql);

			sql = "insert into Course values(16070002, 50002, 70, 85, 65, 5, 10, 76, '3.5')";
			stmt.executeUpdate(sql);

			sql = "insert into Course values(16070002, 20001, 71, 70, 95, 8, 10, 82, '3.5')";
			stmt.executeUpdate(sql);

			sql = "insert into Course values(17070001, 20001, 70, 70, 70, 6, 10, 81, '4.0')";
			stmt.executeUpdate(sql);

			sql = "insert into Course values(17070001, 50002, 71, 97, 80, 17, 10, 91, '4.5')";
			stmt.executeUpdate(sql);

			sql = "insert into Course values(16070003, 50002, 70, 80, 80, 10, 10, 84, '4.5')";
			stmt.executeUpdate(sql);

			sql = "insert into Course values(16070003, 50002, 71, 90, 80, 10, 10, 88, '4.0')";
			stmt.executeUpdate(sql);

			sql = "insert into Course values(13080004, 40001, 80, 60, 80, 9, 10, 74, '3.5')";
			stmt.executeUpdate(sql);

			sql = "insert into Course values(13080004, 40012, 81, 85, 90, 10, 8, 87, '3.5')";
			stmt.executeUpdate(sql);

			sql = "insert into Course values(16080004, 40001, 80, 80, 75, 10, 10, 82, '4.0')";
			stmt.executeUpdate(sql);

			sql = "insert into Course values(16080004, 40012, 81, 90, 80, 10, 10, 88, '4.0')";
			stmt.executeUpdate(sql);

			sql = "insert into Course values(14080006, 40001, 80, 65, 100, 10, 10, 84, '4.5')";
			stmt.executeUpdate(sql);

			sql = "insert into Course values(14080006, 40012, 81, 100, 85, 10, 10, 94, '4.5')";
			stmt.executeUpdate(sql);

			sql = "insert into Course values(19090001, 50003, 90, 80, 80, 10, 10, 84, '4.5')";
			stmt.executeUpdate(sql);

			sql = "insert into Course values(13090001, 30006, 90, 75, 90, 8, 10, 83, '4.0')";
			stmt.executeUpdate(sql);

			sql = "insert into Course values(18090002, 50003, 90, 60, 80, 9, 10, 74, '3.5')";
			stmt.executeUpdate(sql);

			sql = "insert into Course values(19090001, 50003, 91, 90, 80, 10, 10, 88, '4.0')";
			stmt.executeUpdate(sql);

			sql = "insert into Course values(13090001, 30006, 91, 70, 70, 10, 10, 76, '3.5')";
			stmt.executeUpdate(sql);

			sql = "insert into Course values(18090002, 50003, 91, 90, 90, 10, 10, 92, '4.5')";
			stmt.executeUpdate(sql);

			sql = "insert into Course values(14100003, 30002, 100, 85, 70, 5, 10, 77, '4.0')";
			stmt.executeUpdate(sql);

			sql = "insert into Course values(14100002, 50004, 100, 100, 100, 10, 10, 100, '4.5')";
			stmt.executeUpdate(sql);

			sql = "insert into Course values(15100001, 30002, 100, 50, 80, 10, 10, 70, '3.5')";
			stmt.executeUpdate(sql);

			sql = "insert into Course values(14100003, 30002, 101, 70, 70, 10, 10, 76, '3.5')";
			stmt.executeUpdate(sql);

			sql = "insert into Course values(14100002, 50004, 101, 100, 90, 10, 10, 96, '4.5')";
			stmt.executeUpdate(sql);

			sql = "insert into Course values(15100001, 30002, 101, 90, 70, 6, 10, 81, '4.0')";
			stmt.executeUpdate(sql);

			sql = "insert into Course values(19110004, 10003, 110, 90, 80, 10, 10, 88, '4.5')";
			stmt.executeUpdate(sql);

			sql = "insert into Course values(17110004, 10003, 110, 70, 50, 10, 10, 69, '3.5')";
			stmt.executeUpdate(sql);

			sql = "insert into Course values(13110005, 50006, 110, 80, 70, 10, 10, 80, '4.0')";
			stmt.executeUpdate(sql);

			sql = "insert into Course values(19110004, 10003, 111, 80, 50, 10, 10, 73, '3.5')";
			stmt.executeUpdate(sql);

			sql = "insert into Course values(17110004, 10003, 111, 80, 80, 10, 10, 84, '4.5')";
			stmt.executeUpdate(sql);

			sql = "insert into Course values(13110005, 50006, 111, 85, 70, 5, 10, 77, '4.0')";
			stmt.executeUpdate(sql);

			sql = "insert into Course values(14120004, 20005, 120, 80, 50, 10, 10, 73, '3.5')";
			stmt.executeUpdate(sql);

			sql = "insert into Course values(15120007, 20005, 120, 60, 85, 10, 10, 76, '4.5')";
			stmt.executeUpdate(sql);

			sql = "insert into Course values(15120006, 10004, 120, 60, 80, 9, 10, 74, '4.0')";
			stmt.executeUpdate(sql);

			sql = "insert into Course values(14120004, 20005, 121, 70, 70, 10, 10, 76, '3.5')";
			stmt.executeUpdate(sql);

			sql = "insert into Course values(15120007, 20005, 121, 97, 80, 10, 10, 91, '4.5')";
			stmt.executeUpdate(sql);

			sql = "insert into Course values(15120006, 10004, 121, 80, 70, 10, 10, 80, '4.0')";
			stmt.executeUpdate(sql);

			sql = "insert into Course values(18130001, 40005, 130, 100, 100, 10, 10, 100, '4.5')";
			stmt.executeUpdate(sql);

			sql = "insert into Course values(17130002, 30003, 130, 80, 75, 10, 10, 82, '4.0')";
			stmt.executeUpdate(sql);

			sql = "insert into Course values(17130003, 30003, 130, 70, 70, 10, 9, 75, '3.5')";
			stmt.executeUpdate(sql);

			sql = "insert into Course values(18130001, 40005, 131, 70, 50, 10, 10, 69, '3.5')";
			stmt.executeUpdate(sql);

			sql = "insert into Course values(17130002, 30003, 131, 80, 80, 10, 10, 84, '4.5')";
			stmt.executeUpdate(sql);

			sql = "insert into Course values(17130003, 30003, 131, 50, 80, 10, 10, 70, '4.0')";
			stmt.executeUpdate(sql);

			sql = "insert into Course values(17140005, 40003, 140, 80, 100, 10, 10, 91, '4.5')";
			stmt.executeUpdate(sql);

			sql = "insert into Course values(19140003, 50005, 140, 50, 80, 10, 10, 70, '4.0')";
			stmt.executeUpdate(sql);

			sql = "insert into Course values(19140005, 40003, 140, 70, 10, 10, 8, 53, '3.5')";
			stmt.executeUpdate(sql);

			sql = "insert into Course values(17140005, 40003, 141, 40, 45, 10, 10, 53, '4.0')";
			stmt.executeUpdate(sql);

			sql = "insert into Course values(19140003, 50005, 141, 47, 38, 9, 8, 51, '3.5')";
			stmt.executeUpdate(sql);

			sql = "insert into Course values(19140005, 40003, 141, 45, 60, 10, 10, 61, '4.5')";
			stmt.executeUpdate(sql);

			sql = "insert into Course values(16150008, 20004, 150, 55, 30, 7, 10, 52, '3.5')";
			stmt.executeUpdate(sql);

			sql = "insert into Course values(16150005, 20004, 150, 47, 42, 10, 10, 55, '4.5')";
			stmt.executeUpdate(sql);

			sql = "insert into Course values(18150005, 20003, 150, 59, 29, 10, 9, 55, '4.0')";
			stmt.executeUpdate(sql);

			sql = "insert into Course values(16150008, 20004, 151, 80, 52, 10, 10, 74, '3.5')";
			stmt.executeUpdate(sql);

			sql = "insert into Course values(16150005, 20004, 151, 85, 60, 10, 10, 79, '4.5')";
			stmt.executeUpdate(sql);

			sql = "insert into Course values(18150005, 20003, 151, 76, 70, 9, 8, 75, '4.0')";
			stmt.executeUpdate(sql);

			sql = "insert into Supervision Values(13090001, 030006, '4/2')";
			stmt.executeUpdate(sql);

			sql = "insert into Supervision Values(19140003, 050005, '1/1')";
			stmt.executeUpdate(sql);

			sql = "insert into Supervision Values(18040003, 030001, '2/1')";
			stmt.executeUpdate(sql);

			sql = "insert into Supervision Values(14020001, 050001, '3/2')";
			stmt.executeUpdate(sql);

			sql = "insert into Supervision Values(19110004, 010003, '1/1')";
			stmt.executeUpdate(sql);

			sql = "insert into Supervision Values(13080004, 040001, '3/1')";
			stmt.executeUpdate(sql);

			sql = "insert into Supervision Values(15100001, 030002, '3/1')";
			stmt.executeUpdate(sql);

			sql = "insert into Supervision Values(16030001, 010001, '4/2')";
			stmt.executeUpdate(sql);

			sql = "insert into Supervision Values(18150005, 020003, '2/1')";
			stmt.executeUpdate(sql);

			sql = "insert into Supervision Values(19140005, 040003, '1/1')";
			stmt.executeUpdate(sql);

			sql = "insert into Supervision Values(19040006, 010006, '1/1')";
			stmt.executeUpdate(sql);

			sql = "insert into Supervision Values(17070001, 050002, '2/1')";
			stmt.executeUpdate(sql);

			sql = "insert into Supervision Values(15050004, 020002, '4/1')";
			stmt.executeUpdate(sql);

			sql = "insert into Supervision Values(14120004, 020005, '4/2')";
			stmt.executeUpdate(sql);

			sql = "insert into Supervision Values(18130001, 040005, '2/1')";
			stmt.executeUpdate(sql);

			sql = "insert into Supervision Values(17060006, 040002, '2/1')";
			stmt.executeUpdate(sql);

			sql = "insert into Supervision Values(19090001, 050003, '1/1')";
			stmt.executeUpdate(sql);

			sql = "insert into Supervision Values(13020002, 010005, '4/1')";
			stmt.executeUpdate(sql);

			sql = "insert into Supervision Values(14100002, 050004, '3/2')";
			stmt.executeUpdate(sql);

			sql = "insert into Supervision Values(17010007, 010002, '2/1')";
			stmt.executeUpdate(sql);

			sql = "insert into Supervision Values(16060007, 020006, '4/1')";
			stmt.executeUpdate(sql);

			sql = "insert into Supervision Values(16150008, 020004, '3/1')";
			stmt.executeUpdate(sql);

			sql = "insert into Supervision Values(16080004, 040002, '4/2')";
			stmt.executeUpdate(sql);

			sql = "insert into Supervision Values(15030002, 040006, '4/1')";
			stmt.executeUpdate(sql);

			sql = "insert into Supervision Values(16010006, 030005, '2/1')";
			stmt.executeUpdate(sql);

			sql = "insert into Supervision Values(16070002, 020001, '3/2')";
			stmt.executeUpdate(sql);

			sql = "insert into Supervision Values(17130002, 030003, '3/2')";
			stmt.executeUpdate(sql);

			sql = "insert into Supervision Values(14080006, 040001, '3/2')";
			stmt.executeUpdate(sql);

			sql = "insert into Supervision Values(18050006, 030004, '2/1')";
			stmt.executeUpdate(sql);

			sql = "insert into Supervision Values(18090002, 050003, '2/1')";
			stmt.executeUpdate(sql);

			sql = "insert into Supervision Values(16150005, 020004, '3/1')";
			stmt.executeUpdate(sql);

			sql = "insert into Supervision Values(17140005, 040003, '3/2')";
			stmt.executeUpdate(sql);

			sql = "insert into Supervision Values(18040004, 010006, '2/1')";
			stmt.executeUpdate(sql);

			sql = "insert into Supervision Values(19020002, 050001, '1/1')";
			stmt.executeUpdate(sql);

			sql = "insert into Supervision Values(14050005, 020002, '4/2')";
			stmt.executeUpdate(sql);

			sql = "insert into Supervision Values(15120006, 010004, '4/2')";
			stmt.executeUpdate(sql);

			sql = "insert into Supervision Values(14100003, 030002, '3/1')";
			stmt.executeUpdate(sql);

			sql = "insert into Supervision Values(13110005, 050006, '4/1')";
			stmt.executeUpdate(sql);

			sql = "insert into Supervision Values(15120007, 020005, '3/2')";
			stmt.executeUpdate(sql);

			sql = "insert into Supervision Values(17110004, 010003, '2/1')";
			stmt.executeUpdate(sql);

			sql = "insert into Supervision Values(15030003, 010001, '4/1')";
			stmt.executeUpdate(sql);

			sql = "insert into Supervision Values(16070003, 050002, '4/2')";
			stmt.executeUpdate(sql);

			sql = "insert into Supervision Values(13010003, 010002, '4/1')";
			stmt.executeUpdate(sql);

			sql = "insert into Supervision Values(15060005, 040002, '2/1')";
			stmt.executeUpdate(sql);

			sql = "insert into Supervision Values(17130003, 030003, '3/2')";
			stmt.executeUpdate(sql);

			JOptionPane.showMessageDialog(null, "Success Data Init !");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		closeDB();
	}
}