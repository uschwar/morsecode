package at.schwar.android.morse.service.ext;

import android.content.ContentResolver;
import android.content.Intent;

public interface ContactsHelper {
	boolean haveContactWithPhoneNumber(String phoneNumber, ContentResolver contentResolver);
	Intent createContactEditor(CallerIDResult result);
	CallerIDResult getContact(String phoneNumber, ContentResolver contentResolver);
}
