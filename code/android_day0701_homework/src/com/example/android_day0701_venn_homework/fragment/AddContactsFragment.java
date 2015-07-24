package com.example.android_day0701_venn_homework.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import com.example.android_day0701_venn_homework.R;
import com.example.android_day0701_venn_homework.contactsTool.ContactsTool;
import com.example.android_day0701_venn_homework.entity.Contacts;

/**
 * project:com.example.android_day0701_venn_homework.fragment
 * user:VennUser
 * date:2015/7/2
 */
public class AddContactsFragment extends Fragment {

	private static Context context;
	private static EditText editTextName, editTextNumber;

	public AddContactsFragment() {

	}

	public static void getContext(Context con) {
		context = con;
	}

	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_addcontacts, container, false);
		editTextName = (EditText) view.findViewById(R.id.txt_name);
		editTextNumber = (EditText) view.findViewById(R.id.txt_number);
		return view;
	}

	public static void addContacts() {
		String name = editTextName.getText().toString();
		String number = editTextNumber.getText().toString();
		Contacts contact = new Contacts();
		if (name != null && number != null) {
			contact.setName(name);
			contact.setPhoneNumber(number);
		}
		ContactsTool.addContacts(context, contact);
	}

	public static void editClear() {
		editTextName.setText("");
		editTextNumber.setText("");
	}
}