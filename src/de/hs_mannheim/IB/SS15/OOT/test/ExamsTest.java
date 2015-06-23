package de.hs_mannheim.IB.SS15.OOT.test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import de.hs_mannheim.IB.SS15.OOT.Subject;
import de.hs_mannheim.IB.SS15.OOT.Exceptions.SameSubjectException;
import de.hs_mannheim.IB.SS15.OOT.Participants.Assessor;
import de.hs_mannheim.IB.SS15.OOT.Participants.Desire;
import de.hs_mannheim.IB.SS15.OOT.Participants.Examinee;
import de.hs_mannheim.IB.SS15.OOT.Participants.Examiner;
import de.hs_mannheim.IB.SS15.OOT.PlanObjects.Exam;

public class ExamsTest {
	private Exam exam, exam2, exam3, exam4,exam5,exam6,exam7,exam8,exam9, exam10,exam11,exam12,exam13,exam14,exam15;
	Desire desireOne, desireTwo, desireThree;
	private Subject[] subjects = new Subject[2];
	private Subject[] subjects2 = new Subject[2];
	private Subject[] subjects3 = new Subject[2];
	private Examinee examinee, examinee1, examinee2, examinee3, examinee4, examinee5, examinee6;
	private Examiner[] examiner = new Examiner[2];
	private Assessor assessor, assessor1, assessor2,assessor3,assessor4,assessor5,assessor6;
	private Examiner examiner1,examiner2,examiner3,examiner4,examiner5,examiner6, examiner7;
	private Subject subjectOne,subjectTwo, subjectThree;
	private ArrayList<Desire> desiresListOne,desiresList1,desiresList2,desiresList3,desiresList4,desiresList5,desiresList6 ;
    private Desire d1,d2,d3,d4,d5,d6;
   
	
	@Before
	public void setUp() throws Exception{
		
		// Subjects
		subjectOne  = new Subject("Lineare Algebra", "LAL");
		subjectTwo = new Subject("Analysis", "ANA");
		subjectThree = new Subject("H�here Mathematik 1", "HM1", true);
		subjects[0]= subjectOne;
		subjects[1]=subjectTwo;
		subjects3[0]=subjectOne;
		subjects3[1]=null;
		subjects2[0]=null;
		subjects2[1]=null;
		
		
		ArrayList<Subject> subjectsArrayList = new ArrayList<Subject>();
		subjectsArrayList.add(subjectOne);
		subjectsArrayList.add(subjectTwo);
		
		// desires
		desiresListOne = new ArrayList<Desire>();
		desireOne=new Desire(61 , 122,"wichtig",3 );
		desiresListOne.add(desireOne);
        // desiresListen
        desiresList1 = new ArrayList<Desire>();
        desiresList2 = new ArrayList<Desire>();
        desiresList3 = new ArrayList<Desire>();
        desiresList4 = new ArrayList<Desire>();
        desiresList5 = new ArrayList<Desire>();
        desiresList6 = new ArrayList<Desire>();
        // desires
        d1 = new Desire(600,930,"hoch",3 );
        d2 = new Desire(480,600,"hoch",3 );
        d3 = new Desire(480,600,"mittel",2 );
        d4 = new Desire(600,720,"mittel",2 );
        d5 = new Desire(600,720,"niedrig",1 );
        d6 = new Desire(480,500,"niedrig",1 );
       
        desiresList1.add(d1);
        desiresList2.add(d2);
        desiresList3.add(d3);
        desiresList4.add(d4);
        desiresList5.add(d5);
        desiresList6.add(d6);
       
        //assessors
        assessor1 = new Assessor("Rudi",subjectsArrayList);
        assessor2 = new Assessor("Johan",subjectsArrayList);
        assessor3 = new Assessor("Bert",subjectsArrayList);
        assessor4 = new Assessor("Ernie",subjectsArrayList);
        assessor5 = new Assessor("Olaf",subjectsArrayList);
        assessor6 = new Assessor("Ludwig",subjectsArrayList);
		
		//examiner && examinee
		Examinee examineeTest = new Examinee("Harald", subjectsArrayList, desiresList1);
		examinee1 = new Examinee("Wolfram", subjectsArrayList, desiresList1);
		examinee2 = new Examinee("Walburga", subjectsArrayList,desiresList2);
		examinee3 = new Examinee("Berta", subjectsArrayList,desiresList3);
		examinee4 = new Examinee("Herta", subjectsArrayList,desiresList4);
		examinee5 = new Examinee("Friedrich-Wilhelm", subjectsArrayList,desiresList5);
		examinee6 = new Examinee("Karl-August", subjectsArrayList,desiresList6);
		
		examiner1 = new Examiner("Bernd", subjectsArrayList, desiresList1);
		examiner2 = new Examiner("Hugo", subjectsArrayList, desiresList2);
		examiner3 = new Examiner("Brunhilde", subjectsArrayList,desiresList3);
		examiner4 = new Examiner("Heidi", subjectsArrayList,desiresList4);
		examiner5 = new Examiner("Willhelm", subjectsArrayList,desiresList5);
		examiner6 = new Examiner("Hedwig", subjectsArrayList,desiresList6);
		examiner7 = new Examiner("Horst", subjectsArrayList, desiresList6);
		Examiner examinerTestOne = new Examiner("Dieter", subjectsArrayList, desiresListOne);
		Examiner examinerTestTwo = new Examiner("Wolfgang", subjectsArrayList, null);
		examiner[0]=examinerTestOne;
		examiner[1]=examinerTestTwo;
		
		//assessor
		Assessor assessorTest = new Assessor("Helene", subjectsArrayList);
		
		
		// create exams
		exam = new Exam(subjects, examineeTest, examiner, assessorTest,3);
		exam2= new Exam(subjects2, examineeTest, examiner, assessorTest,3);
		exam3= new Exam(subjects3, examineeTest, examiner, assessorTest,3);
		
		examiner[0]=examiner1;
		examiner[1]=null;
		exam4 = new Exam(subjects, examinee1, examiner, assessor6, 25 );
		examiner[0]=examiner2;
		exam5 = new Exam(subjects, examinee1, examiner, assessor6, 25 );
		examiner[0]=examiner3;
		exam6 = new Exam(subjects, examinee1, examiner, assessor6, 25 );
		examiner[0]=examiner4;
		exam7 = new Exam(subjects, examinee1, examiner, assessor6, 25 );
		examiner[0]=examiner5;
		exam8 = new Exam(subjects, examinee1, examiner, assessor6, 25 );
		examiner[0]=examiner6;
		exam9 = new Exam(subjects, examinee1, examiner, assessor6, 25 );
		exam10 = new Exam(subjects, examinee1, examiner, assessor6, 25 );
		exam11 = new Exam(subjects, examinee2, examiner, assessor6, 25 );
		exam12 = new Exam(subjects, examinee3, examiner, assessor6, 25 );
		exam13 = new Exam(subjects, examinee4, examiner, assessor6, 25 );
		exam14 = new Exam(subjects, examinee5, examiner, assessor6, 25 );
		exam15 = new Exam(subjects, examinee6, examiner, assessor6, 25 );

	}
	
	//Assessor and examinee Prio 1 and not in testet time
	@Test
	public void checkDesireTest_LegalArgumentsWithExaminers(){
		assertEquals(false, exam4.checkDesires(3, 600));
		assertEquals(true, exam5.checkDesires(3, 600));
		assertEquals(true, exam6.checkDesires(3, 600));
		assertEquals(true, exam7.checkDesires(3, 600));
		assertEquals(true, exam8.checkDesires(3, 600));
		assertEquals(true, exam9.checkDesires(3, 600));
		
		assertEquals(false, exam4.checkDesires(2, 600));
		assertEquals(true, exam5.checkDesires(2, 600));
		assertEquals(true, exam6.checkDesires(2, 600));
		assertEquals(false, exam7.checkDesires(2, 600));
		assertEquals(true, exam8.checkDesires(2, 600));
		assertEquals(true, exam9.checkDesires(2, 600));
		
		assertEquals(false, exam4.checkDesires(1, 600));
		assertEquals(true, exam5.checkDesires(1, 600));
		assertEquals(true, exam6.checkDesires(1, 600));
		assertEquals(false, exam7.checkDesires(1, 600));
		assertEquals(false, exam8.checkDesires(1, 600));
		assertEquals(true, exam9.checkDesires(1, 600));
	}
	
	@Test
	public void checkDesireTest_LegalArgummentsWithExaminee(){
		assertEquals(false, exam10.checkDesires(3, 600));
		assertEquals(true, exam11.checkDesires(3, 600));
		assertEquals(true, exam12.checkDesires(3, 600));
		assertEquals(true, exam13.checkDesires(3, 600));
		assertEquals(true, exam14.checkDesires(3, 600));
		assertEquals(true, exam15.checkDesires(3, 600));
		
		assertEquals(false, exam10.checkDesires(2, 600));
		assertEquals(true, exam11.checkDesires(2, 600));
		assertEquals(true, exam12.checkDesires(2, 600));
		assertEquals(false, exam13.checkDesires(2, 600));
		assertEquals(true, exam14.checkDesires(2, 600));
		assertEquals(true, exam15.checkDesires(2, 600));
		
		assertEquals(false, exam10.checkDesires(1, 600));
		assertEquals(true, exam11.checkDesires(1, 600));
		assertEquals(true, exam12.checkDesires(1, 600));
		assertEquals(false, exam13.checkDesires(1, 600));
		assertEquals(false, exam14.checkDesires(1, 600));
		assertEquals(true, exam15.checkDesires(1, 600));
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void addSubjectTest_NameNull(){
		exam.addSubject(null);
		exam2.addSubject(null);
		exam3.addSubject(null);
	}
//	@Test (expected = SameSubjectException.class)
//	public void addSubjectTest_ArrayHalfFullSameSubject(){
//		exam3.addSubject(subjectOne);
//		exam.addSubject(subjectOne);
//	}
	
	@Test
	public void addSubjectTest_ArrayFull(){
		assertEquals(false, exam.addSubject(subjectThree));
	}
	
	@Test
	public void addSubjectTest_ArrayHalfFull_Legal(){
		assertEquals(true, exam3.addSubject(subjectTwo));
	}
	
	@Test
	public void addSubjectTest_LegalArguments(){
		assertEquals(true, exam2.addSubject(subjectOne));
		assertEquals(true, exam2.addSubject(subjectTwo));
		assertEquals(true, exam3.addSubject(subjectTwo));
	}
	@Test
	public void equalsTest(){
		assertEquals(true, subjectThree.equals(new Subject("H�here Mathematik 1", "HM1", true)));
		assertEquals(true, subjectOne.equals(new Subject("Lineare Algebra", "LAL")));
	}
}
