package com.dev.server.data;

import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.dev.note.Note;
import com.dev.note.NoteModel;
import com.dev.server.hibernate.HibernateUtil;

@Component
public class DataSource implements DataSourceModel {

	@Autowired(required = true)
	private SessionFactory sessionFactory;

	private ArrayList<NoteModel> notes;

	public Session session = null;

	public DataSource() {
	}

	/*
	 * @see com.dev.server.data.DataSourceModel#getStoredNotes()
	 */
	public ArrayList<NoteModel> getStoredNotes() {
		System.out.println("sessionFactory - " + sessionFactory);
		session = sessionFactory.openSession();
		session.beginTransaction();
		notes = (ArrayList<NoteModel>) session.createCriteria(Note.class).list();
		session.getTransaction().commit();
		session.close();
		return notes;
	}

	/*
	 * @see com.dev.server.data.DataSourceModel#setNotes(java.util.ArrayList) We
	 * check flag deleted to check if node was deleted on client. If yes - we
	 * delete note. Also we flag changed to check if node was changed on client.
	 * If yes - we update note.
	 */
	@Transactional
	public void setNotes(ArrayList<NoteModel> notes) {
		for (NoteModel noteModel : notes) {
			if (noteModel.isDeleted()) {
				session = sessionFactory.openSession();
				if (session.get(Note.class, noteModel.getId()) != null) {
					session.beginTransaction();
					NoteModel note = (NoteModel) session.get(Note.class, noteModel.getId());
					session.delete(note);
					session.getTransaction().commit();
				}
			} else if (noteModel.isChanged()) {
				noteModel.setChanged(false);
				session = sessionFactory.openSession();
				if (session.get(Note.class, noteModel.getId()) != null) {
					session.beginTransaction();
					NoteModel note = (NoteModel) session.get(Note.class, noteModel.getId());
					note.setChanged(noteModel.isChanged());
					note.setDeleted(noteModel.isDeleted());
					note.setText(noteModel.getText());
					session.getTransaction().commit();
				} else {
					session.beginTransaction();
					session.save(noteModel);
					session.getTransaction().commit();
				}
				session.close();
			}
		}
	}

	/*
	 * @see com.dev.server.data.DataSourceModel#removeNote(java.lang.Integer)
	 */
	@Transactional
	public void removeNote(Integer noteId) {
		session = HibernateUtil.buildSessionFactory().openSession();
		session.beginTransaction();
		Note note = (Note) session.get(Note.class, noteId);
		if (note != null) {
			session.delete(note);
		}
		session.getTransaction().commit();
		session.close();
	}

	/*
	 * @see com.dev.server.data.DataSourceModel#getNote(java.lang.Integer)
	 */
	@Transactional
	public NoteModel getNote(Integer noteId) {
		session = sessionFactory.openSession();
		session.beginTransaction();
		NoteModel note = (NoteModel) session.get(Note.class, noteId);
		session.getTransaction().commit();
		session.close();
		return note;
	}

	/*
	 * @see com.dev.server.data.DataSourceModel#updateNote(java.lang.Integer,
	 * java.lang.String)
	 */
	@Transactional
	public void updateNote(Integer noteId, String text) {
		session = sessionFactory.openSession();
		session.beginTransaction();
		Note note = (Note) session.get(Note.class, noteId);
		note.setText(text);
		note.setChanged(true);
		session.getTransaction().commit();
		session.close();
	}
}
