package at.schwar.android.morse.service.ext;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.Contacts;


@SuppressWarnings("deprecation")
public class OldContactsHelper implements ContactsHelper {

	final static String[] HAVE_CONTACT_PROJECTION = new String[] { Contacts.Phones.NUMBER };
	final static String[] GET_CONTACT_PROJECTION = new String[] { Contacts.Phones.NUMBER, Contacts.Phones.DISPLAY_NAME };
    static final int NUMBER_COLUMN_INDEX = 0;
    static final int DISPLAY_NAME_COLUMN_INDEX = 1;

	public boolean haveContactWithPhoneNumber(String phoneNumber, ContentResolver contentResolver) {
		final Uri uri = Uri.withAppendedPath(
				Contacts.Phones.CONTENT_FILTER_URL, Uri.encode(phoneNumber));

		final Cursor cursor = contentResolver.query(uri,HAVE_CONTACT_PROJECTION,null,null,null);
		try{
			return cursor.moveToNext();
		}finally{
			cursor.close();
		}
	}

	public Intent createContactEditor(CallerIDResult result) {
	    final Intent intent = new Intent(Contacts.Intents.Insert.ACTION, Contacts.People.CONTENT_URI);
	    intent.putExtra(Contacts.Intents.Insert.NAME, result.getName());
	    intent.putExtra(Contacts.Intents.Insert.PHONE, result.getPhoneNumber());
	    if(result.getAddress()!=null) intent.putExtra(Contacts.Intents.Insert.POSTAL, result.getAddress());
	    return intent;
	}

	public CallerIDResult getContact(String phoneNumber, ContentResolver contentResolver) {
		final Uri uri = Uri.withAppendedPath(Contacts.Phones.CONTENT_FILTER_URL, Uri.encode(phoneNumber));
		final Cursor cursor = contentResolver.query(uri,GET_CONTACT_PROJECTION,null,null,null);
		try{
			if(cursor.moveToNext()){
				CallerIDResult ret = new CallerIDResult();
				ret.setPhoneNumber(cursor.getString(NUMBER_COLUMN_INDEX));
				ret.setName(cursor.getString(DISPLAY_NAME_COLUMN_INDEX));
				return ret;
			}else{
				return new CallerIDResult();
			}
		}finally{
			cursor.close();
		}
	}
}
