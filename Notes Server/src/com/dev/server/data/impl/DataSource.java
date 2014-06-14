package com.dev.server.data.impl;

import java.util.ArrayList;

import org.hibernate.Session;

import com.dev.node.NoteModel;
import com.dev.server.data.model.DataSourceModel;
import com.dev.server.hibernate.HibernateUtil;

public class DataSource implements DataSourceModel {

	private Session session;
	private ArrayList<NoteModel> notes;

	static {
		System.err.println("DataSource i called");
	}

	public DataSource() {
	}

	public ArrayList<NoteModel> getStoredNodes() {
		session = HibernateUtil.buildSessionFactory().openSession();
		session.beginTransaction();
		notes = (ArrayList<NoteModel>) session.createCriteria(NoteModel.class).list();
		System.err.println("we ve got nodes");
		session.getTransaction().commit();
		session.close();
		return notes;
	}

	@Override
	public void deleteAllNodes() {
		ArrayList<NoteModel> storedNotes = getStoredNodes();
		session = HibernateUtil.buildSessionFactory().openSession();
		session.beginTransaction();
		for (int i = 0; i < storedNotes.size(); i++) {
			session.delete(storedNotes.get(i));
		}
		session.getTransaction().commit();
		session.close();
	}

	public void setNodes(ArrayList<NoteModel> nodes) {
		deleteAllNodes();
		session = HibernateUtil.buildSessionFactory().openSession();
		session.beginTransaction();
		for (int i = 0; i < nodes.size(); i++) {
			session.save(nodes.get(i));
		}
		session.getTransaction().commit();
		session.close();
	}
}
