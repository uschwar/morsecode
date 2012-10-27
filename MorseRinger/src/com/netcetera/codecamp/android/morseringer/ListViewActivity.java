/*
 * Copyright (C) 2012 by Netcetera AG.
 * All rights reserved.
 *
 * The copyright to the computer program(s) herein is the property of Netcetera AG, Switzerland.
 * The program(s) may be used and/or copied only with the written permission of Netcetera AG or
 * in accordance with the terms and conditions stipulated in the agreement/contract under which 
 * the program(s) have been supplied.
 */
package com.netcetera.codecamp.android.morseringer;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;


public class ListViewActivity extends ListActivity {

  private TextView selection;
  final String[] items={"A", "B", "C"};


  @Override
  public void onCreate(Bundle icicle) {
    super.onCreate(icicle);
    setContentView(R.layout.list_view);
    setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1));
    selection = (TextView) findViewById(R.id.selection);
  }

  @Override
  public void onListItemClick(ListView parent, View v, int position, long id) {
    selection.setText(items[position]);
  }
}
