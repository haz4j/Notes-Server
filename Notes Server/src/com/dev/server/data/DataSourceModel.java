package com.dev.server.data;

import java.util.ArrayList;

import com.dev.note.NoteModel;

/**
 * The Interface DataSourceModel.
 */
public interface DataSourceModel {

	/**
	 * Gets the stored notes.
	 *
	 * @return the stored notes
	 */
	ArrayList<NoteModel> getStoredNotes();

	/**
	 * Sets the notes.
	 *
	 * @param storedNotes
	 *            the new notes
	 */
	void setNotes(ArrayList<NoteModel> storedNotes);

	/**
	 * Removes the note.
	 *
	 * @param noteId
	 *            the note id
	 */
	void removeNote(Integer noteId);

	/**
	 * Gets the note.
	 *
	 * @param noteId
	 *            the note id
	 * @return the note
	 */
	NoteModel getNote(Integer noteId);

	/**
	 * Update note.
	 *
	 * @param noteId
	 *            the note id
	 * @param text
	 *            the text
	 */
	void updateNote(Integer noteId, String text);
}
