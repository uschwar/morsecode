package com.netcetera.codecamp.android.morseringer.ext;

import android.content.ContentResolver;
import android.content.Intent;

public interface ContactsHelper {
	boolean haveContactWithPhoneNumber(String phoneNumber, ContentResolver contentResolver);
	Intent createContactEditor(CallerIDResult result);
	CallerIDResult getContact(String phoneNumber, ContentResolver contentResolver);
}
