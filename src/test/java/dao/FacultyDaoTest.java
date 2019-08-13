package dao;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import models.Faculty;
import models.Group;

public class FacultyDaoTest {

private FacultyDao facultyDao = new FacultyDao();
	
	@Before
    public void executedBeforeEach() {
		facultyDao.deleteForTesting();
    }

	@Test
	public void testFacultyListPersistence() {

		Faculty facultyToSave = new Faculty();
		facultyToSave.setName("testFaculty");

		List<Group> groups = new ArrayList<>();
		Group group1 = new Group();
		Group group2 = new Group();
		group1.setFaculty(facultyToSave);
		group2.setFaculty(facultyToSave);
		group1.setName("group1");
		group2.setName("group2");

		groups.add(group1);
		groups.add(group2);

		facultyToSave.setGroups(groups);
		
		facultyDao.save(facultyToSave);
		
		List<Group> actual = facultyToSave.getGroups();
		
		assertEquals(groups, actual);
	}

}
