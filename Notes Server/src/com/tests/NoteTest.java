/**
 * 
 */
package com.tests;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.dev.note.Note;

/**
 * @author hazard
 *
 */
public class NoteTest {

	Note note;

	@Before
	public void setUp() throws Exception {
		note = new Note(23, false, "textText", false);
	}

	/**
	 * Test method for {@link com.dev.note.Note#Note()}.
	 */
	@Test
	public void testNote() {
		assertEquals("Notes should equals", note, new Note(23, false, "textText", false));
	}

	/**
	 * Test method for {@link com.dev.note.Note#getId()}.
	 */
	@Test
	public void testGetId() {
		assertEquals("IDs should equals", 23, note.getId());
	}

	/**
	 * Test method for {@link com.dev.note.Note#setId(int)}.
	 */
	@Test
	public void testSetId() {

		note.setId(24);
		assertEquals("IDs should equals", 24, note.getId());
		note.setId(23);
	}

	/**
	 * Test method for {@link com.dev.note.Note#isChanged()}.
	 */
	@Test
	public void testIsChanged() {
		assertEquals("Changed should equals", false, note.isChanged());
	}

	/**
	 * Test method for {@link com.dev.note.Note#setChanged(boolean)}.
	 */
	@Test
	public void testSetChanged() {
		note.setChanged(true);
		assertEquals("Changed should equals", true, note.isChanged());
		note.setChanged(false);
	}

	/**
	 * Test method for {@link com.dev.note.Note#getText()}.
	 */
	@Test
	public void testGetText() {
		assertEquals("Texts should equals", "textText", note.getText());
	}

	/**
	 * Test method for {@link com.dev.note.Note#setText(java.lang.String)}.
	 */
	@Test
	public void testSetText() {
		note.setText("The new text");
		assertEquals("Texts should equals", "The new text", note.getText());
		note.setText("textText");
	}

	/**
	 * Test method for {@link com.dev.note.Note#isDeleted()}.
	 */
	@Test
	public void testIsDeleted() {
		assertEquals("Deleted should equals", false, note.isDeleted());
	}

	/**
	 * Test method for {@link com.dev.note.Note#setDeleted(boolean)}.
	 */
	@Test
	public void testSetDeleted() {
		note.setDeleted(true);
		assertEquals("Deleted should equals", true, note.isDeleted());
		note.setDeleted(false);
	}
}
