package com.trainingcourse.notekeeper;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.Matchers.allOf;
import static org.junit.Assert.*;

import android.content.Intent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;
import static androidx.test.espresso.Espresso.pressBack;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class NoteCreationTest {
    static DataManager sDataManager;
    @BeforeClass
    public static void classSetUp() {
        sDataManager=DataManager.getInstance();
    }

    @Rule
    public ActivityTestRule<NoteListActivity> mNoteListActivityRule;
    {
        new ActivityTestRule<> (NoteListActivity.class);
    }

    @Test
    public void createNewNote() {
        //ViewInteraction fabNewNote = onView(withId(R.id.fab));
        //fabNewNote.perform(click());

        ActivityTestRule<NoteListActivity> mNoteListActivityRule=new ActivityTestRule<>(NoteListActivity.class);
        mNoteListActivityRule.launchActivity(new Intent());

        final CourseInfo course=sDataManager.getCourse("java_lang");
        final String noteTitle="Test note tile";
        final String noteText="This is the body of our test note";

        onView(withId(R.id.fab)).perform(click());

        onView(withId(R.id.spinner_courses)).perform(click());
        onData(allOf(instanceOf(CourseInfo.class),equalTo(course))).perform(click());
        onView(withId(R.id.text_note_title)).perform(typeText(noteTitle));
        onView(withId(R.id.text_note_text)).perform(typeText(noteText),
                closeSoftKeyboard());

        pressBack();
    }

}