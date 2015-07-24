package com.example.android_day0629_homework;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class MyActivity extends Activity {
	/**
	 * Called when the activity is first created.
	 */
	private ListView contentListview;
	private FileAdapter fileAdapter;
	private List<File> files;
	private List<File> newFiles;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		contentListview = (ListView) this.findViewById(R.id.content_listview);

		addFiles(getFilesDir());
		files = Arrays.asList(getFilesDir().getParentFile().listFiles());
		fileAdapter = new FileAdapter(this, files);
		contentListview.setAdapter(fileAdapter);
		newFiles = files;
		contentListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				ViewGroup viewGroup = (ViewGroup) contentListview.getChildAt(position - contentListview
						.getFirstVisiblePosition
								());
				String currentName = ((TextView) viewGroup.findViewById(R.id.file_path)).getText().toString().trim();
				File currentFile = findFile(files, currentName);
				if (currentFile != null && currentFile.isDirectory()) {
					if (currentName.equals("...")) {
						files = Arrays.asList(currentFile.getParentFile().getParentFile().listFiles());
					} else {
						files = Arrays.asList(currentFile.listFiles());
					}
					if (files != null && files.size() > 0) {
						newFiles = files;
					}
				}
				FileAdapter.changedDatas(newFiles);
				fileAdapter.notifyDataSetChanged();
			}
		});
	}

	private void addFiles(File file) {
		for (int i = 0; i < 20; i++) {
			File folder = new File(file, "folder" + i);
			if (!folder.exists()) {
				folder.mkdirs();
			}
			File addFile = new File(file, "file" + i);
			if (!file.exists()) {
				try {
					addFile.createNewFile();
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		File fileUp = new File(file, "...");
		if (!fileUp.exists()) {
			fileUp.mkdirs();
		}
	}

	private File findFile(List<File> files, String name) {
		for (File file : files) {
			if (file.getName().equals(name)) {
				return file;
			}
		}
		return null;
	}
}
