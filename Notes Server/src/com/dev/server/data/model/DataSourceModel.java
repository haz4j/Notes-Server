package com.dev.server.data.model;

import java.util.ArrayList;

import com.dev.node.NoteModel;

public interface DataSourceModel {

	ArrayList<NoteModel> getStoredNodes();

}
