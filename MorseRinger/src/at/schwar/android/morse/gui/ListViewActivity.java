/*
 * Copyright (C) 2012 by Netcetera AG.
 * All rights reserved.
 *
 * The copyright to the computer program(s) herein is the property of Netcetera AG, Switzerland.
 * The program(s) may be used and/or copied only with the written permission of Netcetera AG or
 * in accordance with the terms and conditions stipulated in the agreement/contract under which 
 * the program(s) have been supplied.
 */
package at.schwar.android.morse.gui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import at.schwar.android.morse.service.MorseCode;
import at.schwar.android.morse.service.MorseSoundGenerator;

public class ListViewActivity extends ListActivity {
  private TextView selection;
  private  List<String> itemsDisplay = new ArrayList<String>();
  private  List<String> itemsRead = new ArrayList<String>();
  private static final int SAMPLINGRATE = 44100;
  
  @Override
  public void onCreate(Bundle icicle) {
    super.onCreate(icicle);
    setContentView(R.layout.list_view);
    
    Set<Character> keys = MorseCode.getCodes().keySet();
    ArrayList<Character> keyList = new ArrayList<Character>(keys);
    Collections.sort(keyList);
    for (Character letter :  keyList ) {
      itemsDisplay.add(letter + " " + MorseCode.getMorseCode(letter));
      itemsRead.add(letter.toString());
    }
    
    setListAdapter(new ArrayAdapter<String>(this,
        android.R.layout.simple_list_item_1,
        itemsDisplay));
    selection=(TextView)findViewById(R.id.selection);
  }
  
  @Override
  public void onListItemClick(ListView parent, View v, int position,
                                long id) {
    selection.setText(itemsDisplay.get(position));
    MorseSoundGenerator morseSoundGenerator = new MorseSoundGenerator(SAMPLINGRATE, 800.0, 50);
    morseSoundGenerator.morseOnce(itemsRead.get(position));
  }
}

