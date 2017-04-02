package com.krishna.nagabhushan.expandcollapseitemdemo;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;

import com.krishna.nagabhushan.widgets.ExpandCollapseItem;

import java.util.ArrayList;

public class MainActivity extends Activity
{
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		buildLayout();
	}
	
	private void buildLayout()
	{
		LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
		
		// Group1 - No Child Items
		ExpandCollapseItem item1 = (ExpandCollapseItem) findViewById(R.id.item1);
		LinearLayout item1Layout = (LinearLayout) inflater.inflate(R.layout.layout_group_title, null);
		final CheckBox item1CheckBox = (CheckBox) item1Layout.findViewById(R.id.checkBox);
		item1CheckBox.setText("Group Item 1");
		item1.setViews(item1Layout, null);
		
		// Group2 - With Child Items
		ExpandCollapseItem item2 = (ExpandCollapseItem) findViewById(R.id.item2);
		LinearLayout item2Layout = (LinearLayout) inflater.inflate(R.layout.layout_group_title, null);
		final CheckBox item2CheckBox = (CheckBox) item2Layout.findViewById(R.id.checkBox);
		item2CheckBox.setText("Group Item 2");
		final String[] item2ChildNames = {"Child Item 21", "Child Item 22", "Child Item 23", "Child Item 24", "Child Item 25"};
		ArrayList<View> item2ChildLayouts = new ArrayList<View>(item2ChildNames.length);
		final ArrayList<CheckBox> item2ChildCheckBoxes = new ArrayList<>(item2ChildNames.length);
		for (String childName : item2ChildNames)
		{
			LinearLayout childLayout = (LinearLayout) inflater.inflate(R.layout.layout_group_child, null);
			CheckBox childCheckBox = (CheckBox) childLayout.findViewById(R.id.checkBox);
			childCheckBox.setText(childName);
			item2ChildCheckBoxes.add(childCheckBox);
			item2ChildLayouts.add(childLayout);
		}
		item2.setViews(item2Layout, item2ChildLayouts);
		
		Button submitButton = (Button) findViewById(R.id.button_submit);
		submitButton.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				String showText = "Following CheckBoxes have beeen selected\n";
				if (item1CheckBox.isChecked())
				{
					showText += "Group Item 1\n";
				}
				if (item2CheckBox.isChecked())
				{
					showText += "Group Item 2\n";
				}
				for (int i = 0; i < item2ChildCheckBoxes.size(); i++)
				{
					if (item2ChildCheckBoxes.get(i).isChecked())
					{
						showText += item2ChildNames[i] + "\n";
					}
				}
				
				AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(MainActivity.this);
				dialogBuilder.setTitle("Result");
				dialogBuilder.setMessage(showText);
				dialogBuilder.setNegativeButton("Dismiss", null);
				dialogBuilder.show();
			}
		});
	}
}
