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

import java.util.HashMap;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class ListViewActivity extends ListActivity {
  private TextView selection;
  private static final String[] items={"A", "B", "C"};
  private static final int SAMPLINGRATE = 44100;
  
  @Override
  public void onCreate(Bundle icicle) {
    super.onCreate(icicle);
    setContentView(R.layout.list_view);
    setListAdapter(new ArrayAdapter<String>(this,
                        android.R.layout.simple_list_item_1,
                        items));
    selection=(TextView)findViewById(R.id.selection);
  }
  
  @Override
  public void onListItemClick(ListView parent, View v, int position,
                                long id) {
    selection.setText(items[position]);
    MorseSoundGenerator morseSoundGenerator = new MorseSoundGenerator(SAMPLINGRATE, 800.0, 50);
    morseSoundGenerator.morse(items[position]);
  }
}

