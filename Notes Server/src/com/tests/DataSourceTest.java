package com.tests;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.dev.note.NoteModel;
import com.dev.server.data.DataSource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:beansConfFileServer.xml" })
public class DataSourceTest {

	DataSource dataSource;

	@Test
	public void testGetStoredNotes() {
		dataSource = new DataSource();
		ArrayList<NoteModel> noteList = dataSource.getStoredNotes();
		assertNotNull(noteList);
	}
	//
	// @Test
	// public void testSetNotes() {
	// ArrayList<NoteModel> noteList = dataSource.getStoredNotes();
	// int lastIndex = noteList.get(noteList.size() - 1).getId();
	// Note newNote = new Note(lastIndex + 1, true, "temp note", false);
	// ArrayList<NoteModel> newNoteList = new ArrayList<>();
	// newNoteList.add(newNote);
	// dataSource.setNotes(newNoteList);
	// Note storedNote = (Note) dataSource.getNote(lastIndex + 1);
	// assertEquals("Notes should be the same", newNote, storedNote);
	// }
	//
	// @Test
	// public void testGetNote() {
	//
	// ArrayList<NoteModel> noteList = dataSource.getStoredNotes();
	// int lastIndex = noteList.get(noteList.size() - 1).getId();
	// Note newNote = new Note(lastIndex + 1, true, "temp note 2", false);
	// ArrayList<NoteModel> newNoteList = new ArrayList<>();
	// newNoteList.add(newNote);
	// dataSource.setNotes(newNoteList);
	// Note storedNote = (Note) dataSource.getNote(lastIndex + 1);
	// assertEquals("Notes should be the same", newNote, storedNote);
	// }
	//
	// @Test
	// public void testUpdateNote() {
	// ArrayList<NoteModel> noteList = dataSource.getStoredNotes();
	// int lastIndex = noteList.get(noteList.size() - 1).getId();
	// Note newNote = new Note(lastIndex + 1, true, "temp note 2", false);
	// ArrayList<NoteModel> newNoteList = new ArrayList<>();
	// newNoteList.add(newNote);
	// dataSource.setNotes(newNoteList);
	//
	// dataSource.updateNote((lastIndex + 1), "the new text");
	// assertEquals("Texts of notes should be the same", "the new text",
	// dataSource.getNote(lastIndex + 1).getText());
	// }
	//
	// @Test
	// public void testRemoveNote() {
	// ArrayList<NoteModel> noteList = dataSource.getStoredNotes();
	// int lastIndex = noteList.get(noteList.size() - 1).getId();
	// Note newNote = new Note(lastIndex + 1, true, "Removed note", false);
	// ArrayList<NoteModel> newNoteList = new ArrayList<>();
	// newNoteList.add(newNote);
	// dataSource.setNotes(newNoteList);
	// dataSource.removeNote(lastIndex + 1);
	// assertEquals("Note should be the removed", null,
	// dataSource.getNote(lastIndex + 1));
	// }

}
