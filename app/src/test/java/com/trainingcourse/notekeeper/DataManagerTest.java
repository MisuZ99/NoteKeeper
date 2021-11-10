package com.trainingcourse.notekeeper;

import junit.framework.TestCase;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class DataManagerTest {
    static DataManager sDataManager;

    @BeforeClass
    public static void classSetUp() {
        sDataManager=DataManager.getInstance();
    }

    @Before
    public void setUp() {
        sDataManager.getNotes().clear();
        sDataManager.initializeExampleNotes();
    }

    @Test
    public void testCreateNewNote() {
        final CourseInfo course= sDataManager.getCourse("android_async");
        final String noteTitle="Test note title";
        final String noteText="Test note text";

        int noteIndex=sDataManager.createNewNote();
        NoteInfo newNote=sDataManager.getNotes().get(noteIndex);
        newNote.setCourse(course);
        newNote.setTitle(noteTitle);
        newNote.setText(noteText);

        NoteInfo compareNote=sDataManager.getNotes().get(noteIndex);
        Assert.assertEquals(course, compareNote.getCourse());
        Assert.assertEquals(noteTitle, compareNote.getTitle());
        Assert.assertEquals(noteText, compareNote.getText());
    }

    @Test
    public void findSimilarNote() {
        final CourseInfo course= sDataManager.getCourse("android_async");
        final String noteTitle="Test note title";
        final String noteText1="Test note text for first note";
        final String noteText2="Test note text for second note";

        int noteIndex1=sDataManager.createNewNote();
        NoteInfo newNote1=sDataManager.getNotes().get(noteIndex1);
        newNote1.setCourse(course);
        newNote1.setTitle(noteTitle);
        newNote1.setText(noteText1);

        int noteIndex2=sDataManager.createNewNote();
        NoteInfo newNote2=sDataManager.getNotes().get(noteIndex2);
        newNote2.setCourse(course);
        newNote2.setTitle(noteTitle);
        newNote2.setText(noteText2);

        int foundIndex1=sDataManager.findNote(newNote1);
        Assert.assertEquals(noteIndex1, foundIndex1);

        int foundIndex2=sDataManager.findNote(newNote2);
        Assert.assertEquals(noteIndex2, foundIndex2);
    }

    @Test
    public void createNewNoteOneStepCreation() {
        final CourseInfo course =sDataManager.getCourse("android_async");
        final String noteTitle="Test note title";
        final String noteText="This is the body of my test note";

        int noteIndex= sDataManager.createNewNote(course,noteTitle,noteText);

        NoteInfo compareNote=sDataManager.getNotes().get(noteIndex);
        Assert.assertEquals(course,compareNote.getCourse());
        Assert.assertEquals(noteTitle,compareNote.getTitle());
        Assert.assertEquals(noteText,compareNote.getText());
    }
}