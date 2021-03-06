package com.xavey.android.db;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.xavey.android.ApplicationValues;
import com.xavey.android.model.Audio;
import com.xavey.android.model.Document;
import com.xavey.android.model.Form;
import com.xavey.android.model.SyncImage;
import com.xavey.android.model.User;
import com.xavey.android.model.XMedia;
import com.xavey.android.util.JSONReader;

public class XaveyDBHelper extends SQLiteOpenHelper {

	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "Xavey";
	private static final String ID = "ID";
	// Form
	// ID
	private static final String FORM_TABLE = "form";
	private static final String FORM_ID = "form_id";
	private static final String FORM_TITLE = "form_title";
	private static final String FORM_SUBTITLE = "form_subtitle";
	private static final String FORM_DESC = "form_desc";
	private static final String FORM_LOCATION_REQUIRED = "form_location_required";
	private static final String FORM_VERSION = "form_version";
	private static final String FORM_JSON = "form_json";
	private static final String ORG_AUTO_ID = "org_auto_id";
	private static final String ORG_GIVEN_ID = "org_given_id";
	private static final String ORG_NAME = "org_name";
	// private static final String WORKER_ID = "worker_id";
	// private static final String WORKER_EMAIL = "worker_email";
	// private static final String WORKER_NAME = "worker_name";
	private static final String CREATOR_ID = "creator_id";
	private static final String CREATOR_EMAIL = "creator_email";
	private static final String CREATOR_NAME = "creator_name";
	private static final String FORM_FIELDS = "form_fields";
	private static final String IS_IMAGE_SYNCED = "is_image_synced";
	private final ArrayList<Form> formList = new ArrayList<Form>();

	// ID
	private static final String USER_TABLE = "user";
	private static final String USER_ID = "user_id";
	private static final String USER_NAME = "user_name";
	private static final String PASSWORD = "password";
	private static final String HASH_PWD = "hashPwd";
	private static final String EMAIL = "email";
	private static final String ROLE = "role";
	private static final String ORGANIZATION = "organization";
	private static final String LOGO_NAME = "logo_name";
	private static final String LOGO_IMAGE = "logo_image";
	private static final String TOKEN = "token";

	private static final String WORKER_FORM_TABLE = "worker_form";
	// ID
	// USER_ID
	// FORM_ID
	private static final String ASSIGN = "assign";

	private static final String DOCUMENT_TABLE = "document";

	// ID
	private static final String DOCUMENT_ID = "document_id";
	private static final String DOCUMENT_NAME = "document_name";
	private static final String DOCUMENT_JSON = "document_json";
	private static final String DOCUMENT_JSON_TO_SUBMIT = "document_json_to_submit";
	private static final String CREATED_AT = "created_date";
	private static final String CREATED_WORKER = "created_worker";
	private static final String SUBMITTED = "submitted";
	
	// FORM_ID
	private final ArrayList<Document> documentList = new ArrayList<Document>();

	// IMAGE TABLE
	private static final String MEDIA_TABLE = "media";
	private static final String MEDIA_NAME = "media_name";
	private static final String MEDIA_PATH = "media_path";
	private static final String MEDIA_ID = "media_id";
	private static final String DOC_ID = "doc_id";
	private static final String MEDIA_TYPE = "media_type";

	// Audio TABLE
	private static final String AUDIO_TABLE = "audio";
	private static final String AUDIO_NAME = "audio_name";
	private static final String AUDIO_PATH = "audio_path";
	private static final String AUDIO_ID = "audio_id";

	// Synced Image Table
	private static final String SYNCED_IMAGE_TABLE = "synced_image";
	//ID
	private static final String IMAGE_ID = "image_id";
	private static final String SYNC_ID = "sync_id";
	private static final String IMAGE_BYTE = "image_byte";
	
	public XaveyDBHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		String CREATE_FORM_TABLE = "CREATE TABLE " + FORM_TABLE + "(" + ID
				+ " INTEGER PRIMARY KEY," + FORM_ID + " INTEGER," + FORM_TITLE
				+ " TEXT," + FORM_SUBTITLE + " TEXT," + FORM_DESC + " TEXT,"
				+ FORM_LOCATION_REQUIRED + " TEXT," + FORM_VERSION + " TEXT,"
				+ FORM_JSON + " TEXT," + ORG_AUTO_ID + " TEXT," + ORG_GIVEN_ID
				+ " TEXT," + ORG_NAME + " TEXT," + CREATOR_ID + " TEXT,"
				+ CREATOR_EMAIL + " TEXT," + CREATOR_NAME + " TEXT,"
				+ FORM_FIELDS + " TEXT," + IS_IMAGE_SYNCED + " TEXT" + ")";

		String CREATE_USER_TABLE = "CREATE TABLE " + USER_TABLE + "(" + ID
				+ " INTEGER PRIMARY KEY," + USER_ID + " INTEGER," + USER_NAME
				+ " TEXT," + PASSWORD + " TEXT," + HASH_PWD + " TEXT," + EMAIL
				+ " TEXT," + ROLE + " INTEGER," + ORGANIZATION + " TEXT,"
				+ LOGO_NAME + " TEXT," + LOGO_IMAGE + " BLOB," + TOKEN
				+ " TEXT" + ")";

		String CREATE_WORKER_FORM_TABLE = "CREATE TABLE " + WORKER_FORM_TABLE
				+ "(" + ID + " INTEGER PRIMARY KEY," + USER_ID + " INTEGER,"
				+ FORM_ID + " INTEGER," + ASSIGN + " INTEGER" + ")";

		String CREATE_DOCUMENT_TABLE = "CREATE TABLE " + DOCUMENT_TABLE + "("
				+ ID + " INTEGER PRIMARY KEY," + DOCUMENT_ID + " TEXT,"
				+ DOCUMENT_NAME + " TEXT," + DOCUMENT_JSON + " TEXT,"
				+ DOCUMENT_JSON_TO_SUBMIT + " TEXT," + CREATED_AT
				+ " DATETIME DEFAULT CURRENT_TIMESTAMP," + FORM_ID
				+ " INTEGER," + CREATED_WORKER + " TEXT," + SUBMITTED
				+ " INTEGER" + ")";

		String CREATE_MEDIA_TABLE = "CREATE TABLE " + MEDIA_TABLE + "(" 
									+ ID + " INTEGER PRIMARY KEY," 
									+ MEDIA_NAME + " TEXT, " 
									+ MEDIA_PATH + " TEXT, " 
									+ MEDIA_ID + " INTEGER, " 
									+ DOC_ID + " INTEGER, "
									+ MEDIA_TYPE + " TEXT"
									+ ")";

		String CREATE_AUDIO_TABLE = "CREATE TABLE " + AUDIO_TABLE + "(" + ID
				+ " INTEGER PRIMARY KEY," + AUDIO_NAME + " TEXT," + AUDIO_PATH
				+ " TEXT," + AUDIO_ID + " TEXT," + DOC_ID + " TEXT" + ")";
		
		String CREATE_SYNCED_IMAGE_TABLE = 
				"CREATE TABLE " 
				+ SYNCED_IMAGE_TABLE 
				+ "(" 
				+ ID + " INTEGER PRIMARY KEY," 
				+ IMAGE_ID + " TEXT," 
				+ SYNC_ID + " TEXT," 
				+ IMAGE_BYTE + " BLOB" 
				+ ")";

		db.execSQL(CREATE_FORM_TABLE);
		db.execSQL(CREATE_USER_TABLE);
		db.execSQL(CREATE_WORKER_FORM_TABLE);
		db.execSQL(CREATE_DOCUMENT_TABLE);
		db.execSQL(CREATE_MEDIA_TABLE);
		//db.execSQL(CREATE_AUDIO_TABLE);
		db.execSQL(CREATE_SYNCED_IMAGE_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// store data here
		onUpgradeMaintainResources();
		
	}
	
	public void onUpgradeMaintainResources(){
		ArrayList<Form> existingFormList = getAllForms();
		ArrayList<User> existingUserList = getAllUsers();
		ArrayList<Document> existingDocumentList = getAllDocuments();
		ArrayList<HashMap<String, String>> existingWorkerFormList = getAllWorkerForm();
		ArrayList<XMedia> existingMediaList = getAllMedia();
		ArrayList<SyncImage> existingSyncedImageList = getAllSyncedImage();

		SQLiteDatabase db = this.getWritableDatabase();
		db.execSQL("DROP TABLE IF EXISTS " + FORM_TABLE);
		db.execSQL("DROP TABLE IF EXISTS " + DOCUMENT_TABLE);
		db.execSQL("DROP TABLE IF EXISTS " + WORKER_FORM_TABLE);
		db.execSQL("DROP TABLE IF EXISTS " + USER_TABLE);
		db.execSQL("DROP TABLE IF EXISTS " + MEDIA_TABLE);
		db.execSQL("DROP TABLE IF EXISTS " + SYNCED_IMAGE_TABLE);
		onCreate(db);
		
		// add old forms
		if(existingFormList!=null && existingFormList.size()>0){
			for(int i=0; i<existingFormList.size(); i++){
				Form form = existingFormList.get(i);
				addNewForm(form);
			}
		}
		
		// add old users
		if(existingUserList!=null && existingUserList.size()>0){
			for(int i=0; i<existingFormList.size(); i++){
				User user = existingUserList.get(i);
				addNewUser(user);
			}
		}
		
		// add old documents 
		if(existingDocumentList!=null && existingDocumentList.size()>0){
			for(int i=0; i<existingFormList.size(); i++){
				Document document = existingDocumentList.get(i);
				addNewDocument(document);
			}
		}
		
		
		// add old worker forms
		if(existingWorkerFormList!=null && existingWorkerFormList.size()>0){
			for(int i=0; i<existingWorkerFormList.size(); i++){
				HashMap<String, String> map = existingWorkerFormList.get(i);
				addNewWorkerForm(map.get(USER_ID), map.get(FORM_ID), map.get(ASSIGN));
			}
		}

		// add old media 
		if(existingMediaList!=null && existingMediaList.size()>0){
			for(int i=0; i<existingMediaList.size(); i++){
				XMedia xmedia = existingMediaList.get(i);
				addNewMedia(xmedia);
			}
		}

		// add old synced_images
		if(existingSyncedImageList!=null && existingSyncedImageList.size()>0){
			for(int i=0; i<existingSyncedImageList.size(); i++){
				SyncImage syncImage = existingSyncedImageList.get(i);
				addNewSyncImage(syncImage);
			}
		}
	}

	// FORMS
	public void addNewForm(Form form) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(FORM_ID, form.getForm_id());
		values.put(FORM_TITLE, form.getForm_title());
		values.put(FORM_SUBTITLE, form.getForm_subtitle());
		values.put(FORM_DESC, form.getForm_desc());
		values.put(FORM_LOCATION_REQUIRED, form.isForm_location_required() + "");
		values.put(FORM_VERSION, form.getForm_version());
		values.put(FORM_JSON, form.getForm_json());
		values.put(ORG_AUTO_ID, form.getOrg_auto_id());
		values.put(ORG_GIVEN_ID, form.getOrg_given_id());
		values.put(ORG_NAME, form.getOrg_name());
		/*
		 * values.put(WORKER_ID, WORKER_ID); values.put(WORKER_EMAIL,
		 * form.getWorker_email()); values.put(WORKER_NAME,
		 * form.getWorker_name());
		 */
		values.put(CREATOR_ID, form.getCreator_id());
		values.put(CREATOR_EMAIL, form.getCreator_email());
		values.put(CREATOR_NAME, form.getCreator_name());
		values.put(FORM_FIELDS, form.getForm_fields());
		values.put(IS_IMAGE_SYNCED, form.isImageSynced());
		db.insert(FORM_TABLE, null, values);
		db.close();
	}

	public Form getFormByFormID(String form_id) {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery("select * from " + FORM_TABLE + " where "
				+ FORM_ID + "=?", new String[] { form_id });
		Form form = new Form();
		if (cursor != null && cursor.getCount() > 0) {
			cursor.moveToFirst();
			// 0 is id and no need
			form.setForm_id(cursor.getString(1));
			form.setForm_title(cursor.getString(2));
			form.setForm_subtitle(cursor.getString(3));
			form.setForm_desc(cursor.getString(4));
			form.setForm_location_required(Boolean.parseBoolean(cursor
					.getString(5)));
			form.setForm_version(cursor.getString(6));
			String form_json = cursor.getString(7);
			form_json = JSONReader.convertStandardJSONString(form_json);
			form.setForm_json(form_json);
			form.setOrg_auto_id(cursor.getString(8));
			form.setOrg_given_id(cursor.getString(9));
			form.setOrg_name(cursor.getString(10));
			form.setCreator_id(cursor.getString(11));
			form.setCreator_email(cursor.getString(12));
			form.setCreator_name(cursor.getString(13));
			form.setForm_fields(cursor.getString(14));
			if(cursor.getString(15).equals("1")){
				form.setImageSynced(true);
			}
			else{
				form.setImageSynced(false);
			}
			cursor.close();
			db.close();
			return form;
		} else
			return null;
	}

	public ArrayList<Form> getAllForms() {
		formList.clear();
		String selectQuery = "SELECT * FROM " + FORM_TABLE;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
		if (cursor.moveToFirst()) {
			do {
				Form form = new Form();
				form.setId(cursor.getString(0));
				form.setForm_id(cursor.getString(1));
				form.setForm_title(cursor.getString(2));
				form.setForm_subtitle(cursor.getString(3));
				form.setForm_desc(cursor.getString(4));
				form.setForm_location_required(Boolean.parseBoolean(cursor.getString(5)));
				form.setForm_version(cursor.getString(6));
				String form_json = cursor.getString(7);
				form_json = JSONReader.convertStandardJSONString(form_json);
				form.setForm_json(form_json);
				form.setOrg_auto_id(cursor.getString(8));
				form.setOrg_given_id(cursor.getString(9));
				form.setOrg_name(cursor.getString(10));
				form.setCreator_id(cursor.getString(11));
				form.setCreator_email(cursor.getString(12));
				form.setCreator_name(cursor.getString(13));
				form.setForm_fields(cursor.getString(14));
				form.setImageSynced(Boolean.parseBoolean(cursor.getString(15)));
				formList.add(form);
			} while (cursor.moveToNext());
		}
		cursor.close();
		db.close();
		return formList;
	}

	public ArrayList<Form> getFormsByUserID(String user_id) {
		ArrayList<Form> formList = new ArrayList<Form>();
		ArrayList<String> form_ids = getFormIDsByUserID(user_id);
		for (String form_id : form_ids) {
			formList.add(getFormByFormID(form_id));
		}
		return formList;
	}

	public ArrayList<Form> getAssignedFormsByUserID(String user_id) {
		ArrayList<Form> formList = new ArrayList<Form>();
		ArrayList<String> form_ids = getAssignedFormIDsByUserID(user_id);
		for (String form_id : form_ids) {
			formList.add(getFormByFormID(form_id));
		}
		return formList;
	}

	public boolean isFormAlreadyExistInDB(String form_id) {
		SQLiteDatabase db = this.getReadableDatabase();
		String query = "select * from " + FORM_TABLE + " where " + FORM_ID
				+ "=?";
		Cursor cursor = db.rawQuery(query, new String[] { form_id });
		if (cursor.getCount() == 0)
			return false;
		else
			return true;
	}

	public boolean isMediaAlreadyExistInDB(String imagePath) {
		SQLiteDatabase db = this.getReadableDatabase();
		String query = "select * from " + MEDIA_TABLE + " where " + MEDIA_PATH
				+ "=?";
		Cursor cursor = db.rawQuery(query, new String[] { imagePath });
		if (cursor.getCount() == 0)
			return false;
		else
			return true;
	}

	// update
	public int updateForm(Form form) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(FORM_ID, form.getForm_id());
		values.put(FORM_TITLE, form.getForm_title());
		values.put(FORM_SUBTITLE, form.getForm_subtitle());
		values.put(FORM_DESC, form.getForm_desc());
		values.put(FORM_LOCATION_REQUIRED, form.isForm_location_required() + "");
		values.put(FORM_VERSION, form.getForm_version());
		values.put(FORM_JSON, form.getForm_json());
		values.put(ORG_AUTO_ID, form.getOrg_auto_id());
		values.put(ORG_GIVEN_ID, form.getOrg_given_id());
		values.put(ORG_NAME, form.getOrg_name());
		values.put(CREATOR_ID, form.getCreator_id());
		values.put(CREATOR_EMAIL, form.getCreator_email());
		values.put(CREATOR_NAME, form.getCreator_name());
		values.put(FORM_FIELDS, form.getForm_fields());
		values.put(IS_IMAGE_SYNCED, form.isImageSynced());
		return db.update(FORM_TABLE, values, FORM_ID + "=?",
				new String[] { form.getForm_id() + "" });
	}

	// delete

	// USER
	public void addNewUser(User user) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(USER_ID, user.getUser_id());
		values.put(USER_NAME, user.getUser_name());
		values.put(PASSWORD, user.getPwd());
		values.put(HASH_PWD, user.getHashPwd());
		values.put(EMAIL, user.getEmail());
		values.put(ROLE, user.getRole());
		values.put(ORGANIZATION, user.getOrganization());
		values.put(LOGO_NAME, user.getLogoName());
		values.put(LOGO_IMAGE, user.getLogoImage());
		values.put(TOKEN, user.getToken());
		db.insert(USER_TABLE, null, values);
		db.close();
	}

	public int updateUser(User user) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(USER_ID, user.getUser_id());
		values.put(USER_NAME, user.getUser_name());
		values.put(PASSWORD, user.getPwd());
		values.put(HASH_PWD, user.getHashPwd());
		values.put(EMAIL, user.getEmail());
		values.put(ROLE, user.getRole());
		values.put(ORGANIZATION, user.getOrganization());
		values.put(LOGO_NAME, user.getLogoName());
		values.put(LOGO_IMAGE, user.getLogoImage());
		values.put(TOKEN, user.getToken());
		return db.update(USER_TABLE, values, USER_ID + "=?",
				new String[] { user.getUser_id() });
	}

	public int updateTokenByUserID(String user_id, String token) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(TOKEN, token);
		return db.update(USER_TABLE, values, USER_ID + "=?",
				new String[] { token });
	}

	public User getUserByUserID(String user_id) {
		SQLiteDatabase db = this.getReadableDatabase();
		String values = USER_ID + "," + USER_NAME + ", " + PASSWORD + ", "
				+ HASH_PWD + ", " + EMAIL + ", " + ROLE + ", " + ORGANIZATION
				+ ", " + LOGO_NAME + ", " + LOGO_IMAGE + ", " + TOKEN;
		Cursor cursor = db.rawQuery("select " + values + " from " + USER_TABLE
				+ " where " + USER_ID + "=?", new String[] { user_id });
		User user = new User();
		if (cursor != null && cursor.getCount() > 0) {
			cursor.moveToFirst();
			user.setUser_id(cursor.getString(0));
			user.setUser_name(cursor.getString(1));
			user.setPwd(cursor.getString(2));
			user.setHashPwd(cursor.getString(3));
			user.setEmail(cursor.getString(4));
			user.setRole(cursor.getString(5));
			user.setOrganization(cursor.getString(6));
			user.setLogoName(cursor.getString(7));
			user.setLogoImage(cursor.getBlob(8));
			user.setToken(cursor.getString(9));
		}
		cursor.close();
		db.close();
		return user;
	}

	public ArrayList<User> getAllUsers() {
		SQLiteDatabase db = this.getReadableDatabase();
		ArrayList<User> userList = new ArrayList<User>();
		String values = USER_ID + "," + USER_NAME + ", " + PASSWORD + ", "
				+ HASH_PWD + ", " + EMAIL + ", " + ROLE + ", " + ORGANIZATION
				+ ", " + LOGO_NAME + ", " + LOGO_IMAGE;
		Cursor cursor = db.rawQuery("select " + values + " from " + USER_TABLE,
				null);
		User user = new User();
		if (cursor != null && cursor.getCount() > 0) {
			cursor.moveToFirst();
			user.setUser_id(cursor.getString(0));
			user.setUser_name(cursor.getString(1));
			user.setPwd(cursor.getString(2));
			user.setHashPwd(cursor.getString(3));
			user.setEmail(cursor.getString(4));
			user.setRole(cursor.getString(5));
			user.setOrganization(cursor.getString(6));
			user.setLogoName(cursor.getString(7));
			user.setLogoImage(cursor.getBlob(8));
			userList.add(user);
		}
		cursor.close();
		db.close();
		return userList;
	}
	//log and logo image error
	public ArrayList<User> getOldAllUsers() {
		SQLiteDatabase db = this.getReadableDatabase();
		ArrayList<User> userList = new ArrayList<User>();
		String values = USER_ID + "," + USER_NAME + ", " + PASSWORD + ", "
				+ HASH_PWD + ", " + EMAIL + ", " + ROLE + ", " + ORGANIZATION
				+ ", " + LOGO_NAME + ", " + "log_image";
		Cursor cursor = db.rawQuery("select " + values + " from " + USER_TABLE,
				null);
		User user = new User();
		if (cursor != null && cursor.getCount() > 0) {
			cursor.moveToFirst();
			user.setUser_id(cursor.getString(0));
			user.setUser_name(cursor.getString(1));
			user.setPwd(cursor.getString(2));
			user.setHashPwd(cursor.getString(3));
			user.setEmail(cursor.getString(4));
			user.setRole(cursor.getString(5));
			user.setOrganization(cursor.getString(6));
			user.setLogoName(cursor.getString(7));
			user.setLogoImage(cursor.getBlob(8));
			userList.add(user);
		}
		cursor.close();
		db.close();
		return userList;
	}

	public boolean isUserAlreadyExistInDB(String user_id) {
		SQLiteDatabase db = this.getReadableDatabase();
		String query = "select * from " + USER_TABLE + " where " + USER_ID
				+ "=?";
		Cursor cursor = db.rawQuery(query, new String[] { user_id });
		int count = cursor.getCount();
		if (cursor.getCount() == 0)
			return false;
		else
			return true;
	}

	public String getUserIDByUserName(String user_name) {
		String userID = "";

		if (user_name != null) {
			user_name = user_name.toLowerCase();
			SQLiteDatabase db = this.getReadableDatabase();
			String query = "select " + USER_ID + " from " + USER_TABLE
					+ " where lower(" + USER_NAME + ")=?";
			String[] parameter = new String[] { user_name };
			Cursor cursor = db.rawQuery(query, parameter);
			if (cursor != null && cursor.getCount() > 0) {
				cursor.moveToFirst();
				userID = cursor.getString(0);// assume that user name is not
												// duplicated
			}
			return userID;
		} else {
			return null;
		}
	}

	public String getTokenByUserID(String userID) {
		String token = "null";
		SQLiteDatabase db = this.getReadableDatabase();
		String query = "select " + TOKEN + " from " + USER_TABLE + " where "
				+ USER_ID + "=?";
		String[] parameter = new String[] { userID };
		Cursor cursor = db.rawQuery(query, parameter);
		if (cursor != null && cursor.getCount() > 0) {
			cursor.moveToFirst();
			token = cursor.getString(0);
		}
		return token;
	}

	// WORKER_FORM
	
	public ArrayList<HashMap<String,String>> getAllWorkerForm(){
		ArrayList<HashMap<String, String>> workerFormList = new ArrayList<HashMap<String,String>>();
		String selectQuery = "SELECT * FROM " + WORKER_FORM_TABLE;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, new String[] {});
		if (cursor.moveToFirst()) {
			do {
				HashMap<String, String> map = new HashMap<String, String>();
				map.put(USER_ID, cursor.getString(0));
				map.put(FORM_ID, cursor.getString(1));
				map.put(ASSIGN, cursor.getString(2));
				workerFormList.add(map);
			} while (cursor.moveToNext());
		}
		cursor.close();
		db.close();
		return workerFormList;
	}
	
	public void addNewWorkerForm(String user_id, String form_id, String assign) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(USER_ID, user_id);
		values.put(FORM_ID, form_id);
		values.put(ASSIGN, assign);
		db.insert(WORKER_FORM_TABLE, null, values);
		db.close();
	}

	public ArrayList<String> getFormIDsByUserID(String user_id) {
		SQLiteDatabase db = this.getReadableDatabase();
		ArrayList<String> formIDs = new ArrayList<String>();
		String selectQuery = "select " + FORM_ID + " from " + WORKER_FORM_TABLE
				+ " where " + USER_ID + "=?";
		Cursor cursor = db.rawQuery(selectQuery, new String[] { user_id });
		if (cursor.moveToFirst()) {
			do {
				String form_id = cursor.getString(0);
				formIDs.add(form_id);
			} while (cursor.moveToNext());
		}
		cursor.close();
		db.close();
		return formIDs;
	}

	public ArrayList<String> getAssignedFormIDsByUserID(String user_id) {
		SQLiteDatabase db = this.getReadableDatabase();
		ArrayList<String> formIDs = new ArrayList<String>();
		String selectQuery = "select " + FORM_ID + " from " + WORKER_FORM_TABLE
				+ " where " + USER_ID + "=?" + " and " + ASSIGN + "=?";
		Cursor cursor = db.rawQuery(selectQuery, new String[] { user_id, "1" });
		int count = cursor.getCount();
		if (cursor.moveToFirst()) {
			do {
				String form_id = cursor.getString(0);
				formIDs.add(form_id);
			} while (cursor.moveToNext());
		}
		cursor.close();
		
		
		db.close();
		return formIDs;
	}

	public boolean isUserIDAndFormIDPaired(String user_id, String form_id) {
		SQLiteDatabase db = this.getReadableDatabase();
		String query = "select * from " + WORKER_FORM_TABLE + " where "
				+ USER_ID + "=?" + " and " + FORM_ID + "=?";
		Cursor cursor = db.rawQuery(query, new String[] { user_id, form_id });
		if (cursor.getCount() == 0)
			return false;
		else
			return true;
	}

	public int setAssignByUserIDAndFormID(String userID, String formID,
			String value) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(ASSIGN, value);
		return db.update(WORKER_FORM_TABLE, values, USER_ID + "=? AND "
				+ FORM_ID + "=?", new String[] { userID, formID });
	}

	public int setAllAssignZeroByUserID(String userID) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(ASSIGN, "0");
		return db.update(WORKER_FORM_TABLE, values, USER_ID + "=?",
				new String[] { userID });
	}

	// ---------------------------------------------------------
	// DOCUMENT
	public void addNewDocument(Document document) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(DOCUMENT_ID, document.getDocument_id());
		values.put(DOCUMENT_NAME, document.getDocument_name());
		values.put(DOCUMENT_JSON, document.getDocument_json());
		values.put(DOCUMENT_JSON_TO_SUBMIT,
				document.getDocument_json_to_submit());
		values.put(CREATED_AT, getCurrentDateTime());
		values.put(FORM_ID, document.getForm_id());
		values.put(CREATED_WORKER, document.getCreated_worker());
		values.put(SUBMITTED, document.getSubmitted());
		db.insert(DOCUMENT_TABLE, null, values);
		db.close();
	}
	
	public void deleteAllDocumentsByCurrentLoggedInUser(){
		SQLiteDatabase db = this.getWritableDatabase();
		String deleteQuery = "delete from "+ DOCUMENT_TABLE +" where "+FORM_ID+" in (select "+FORM_ID+" from "+ WORKER_FORM_TABLE +" where "+USER_ID+"="+ApplicationValues.loginUser.getUser_id()+") ";
		Log.i("deleteQuery", deleteQuery);
		db.execSQL(deleteQuery);
		//db.rawQuery(deleteQuery, null);
		db.close();
	}

	private String getCurrentDateTime() {
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyy-MM-dd   HH:mm:ss", Locale.getDefault());
		Date date = new Date();
		return dateFormat.format(date);
	}

	public Date getDateCreatedAtByDocumentID(String documentID) {
		SQLiteDatabase db = this.getReadableDatabase();
		String rawQuery = "select " + CREATED_AT + " from " + DOCUMENT_TABLE
				+ " where " + DOCUMENT_ID + "=?";
		Date createdAt = null;
		Cursor cursor = db.rawQuery(rawQuery, new String[] { documentID });
		if (cursor != null && cursor.getCount() > 0) {
			cursor.moveToFirst();
			createdAt = new Date(cursor.getLong(0));
		}
		return createdAt;
	}

	public Document getDocumentByDocumentID(String documentID) {
		Document document = new Document();
		SQLiteDatabase db = this.getReadableDatabase();
		String rawQuery = "select * from " + DOCUMENT_TABLE + " where "
				+ DOCUMENT_ID + "=?";
		String[] parameters = new String[] { documentID };
		Cursor cursor = db.rawQuery(rawQuery, parameters);
		if (cursor != null && cursor.getCount() > 0) {
			cursor.moveToFirst();
			document.setId(cursor.getString(0));
			document.setDocument_id(cursor.getString(1));
			document.setDocument_name(cursor.getString(2));
			document.setDocument_json(cursor.getString(3));
			document.setDocument_json_to_submit(cursor.getString(4));
			document.setCreated_at(cursor.getString(5));
			document.setForm_id(cursor.getString(6));
			document.setCreated_worker(cursor.getString(7));
			document.setSubmitted(cursor.getString(8));
		}
		cursor.close();
		db.close();
		return document;
	}

	public ArrayList<Document> getAllDocumentsByCreaterID(String createdWorker) {
		documentList.clear();
		String selectQuery = "SELECT * FROM " + DOCUMENT_TABLE + " WHERE "
				+ CREATED_WORKER + "=? " + " ORDER BY " + CREATED_AT + " DESC ";
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db
				.rawQuery(selectQuery, new String[] { createdWorker });
		if (cursor.moveToFirst()) {
			do {
				Document document = new Document();
				document.setId(cursor.getString(0)); // this ID will be useful
														// as index in
														// HistoryFragment
				document.setDocument_id(cursor.getString(1));
				document.setDocument_name(cursor.getString(2));
				document.setDocument_json(cursor.getString(3));
				document.setDocument_json_to_submit(cursor.getString(4));
				document.setCreated_at(cursor.getString(5));
				document.setForm_id(cursor.getString(6));
				document.setCreated_worker(cursor.getString(7));
				document.setSubmitted(cursor.getString(8));
				documentList.add(document);
			} while (cursor.moveToNext());
		}
		cursor.close();
		db.close();
		return documentList;
	}

	public ArrayList<Document> getAllDocumentsByFormID(String formID) {
		documentList.clear();
		String selectQuery = "SELECT * FROM " + DOCUMENT_TABLE + " WHERE "
				+ FORM_ID + "=? " + " ORDER BY " + CREATED_AT + " ASC ";
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, new String[] { formID });
		if (cursor.moveToFirst()) {
			do {
				Document document = new Document();
				document.setId(cursor.getString(0));
				document.setDocument_id(cursor.getString(1));
				document.setDocument_name(cursor.getString(2));
				document.setDocument_json(cursor.getString(3));
				document.setDocument_json_to_submit(cursor.getString(4));
				document.setCreated_at(cursor.getString(5));
				document.setForm_id(cursor.getString(6));
				document.setCreated_worker(cursor.getString(7));
				document.setSubmitted(cursor.getString(8));
				documentList.add(document);
			} while (cursor.moveToNext());
		}
		cursor.close();
		db.close();
		return documentList;
	}

	public ArrayList<Document> getAllDocuments() {
		documentList.clear();
		String selectQuery = "SELECT * FROM " + DOCUMENT_TABLE + " ORDER BY "
				+ CREATED_AT + " DESC ";
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
		if (cursor.moveToFirst()) {
			do {
				Document document = new Document();
				document.setId(cursor.getString(0)); // this ID will be useful
														// as index in
														// HistoryFragment
				document.setDocument_id(cursor.getString(1));
				document.setDocument_name(cursor.getString(2));
				document.setDocument_json(cursor.getString(3));
				document.setDocument_json_to_submit(cursor.getString(4));
				document.setCreated_at(cursor.getString(5));
				document.setForm_id(cursor.getString(6));
				document.setCreated_worker(cursor.getString(7));
				document.setSubmitted(cursor.getString(8));
				documentList.add(document);
			} while (cursor.moveToNext());
		}
		cursor.close();
		db.close();
		return documentList;
	}

	public ArrayList<Document> getDocumentsBySubmitted(String submitted) {
		// if submitted parameter is zero... it will return unsubmitted docs
		// else return submitted docs
		documentList.clear();
		String selectQuery = "SELECT * FROM " + DOCUMENT_TABLE + " WHERE "
				+ SUBMITTED + "=?" + " ORDER BY " + CREATED_AT + " DESC ";
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, new String[] { submitted });
		if (cursor.moveToFirst()) {
			do {
				Document document = new Document();
				document.setId(cursor.getString(0)); // this ID will be useful
														// as index in
														// HistoryFragment
				document.setDocument_id(cursor.getString(1));
				document.setDocument_name(cursor.getString(2));
				document.setDocument_json(cursor.getString(3));
				document.setDocument_json_to_submit(cursor.getString(4));
				document.setCreated_at(cursor.getString(5));
				document.setForm_id(cursor.getString(6));
				document.setCreated_worker(cursor.getString(7));
				document.setSubmitted(cursor.getString(8));
				documentList.add(document);
			} while (cursor.moveToNext());
		}
		cursor.close();
		db.close();
		return documentList;
	}

	public int updateDocumentSubmitted(Document document, String submitted_value) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(SUBMITTED, submitted_value);
		return db.update(DOCUMENT_TABLE, values, DOCUMENT_ID + "=?",
				new String[] { document.getDocument_id() });
	}

	public int updateDocumentSubmittedByJSON(Document document,
			String submitted_value) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(SUBMITTED, submitted_value);
		return db.update(DOCUMENT_TABLE, values, DOCUMENT_JSON + "=?",
				new String[] { document.getDocument_json() });
	}

	public int updateDocument(Document document) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(DOCUMENT_NAME, document.getDocument_name());
		values.put(DOCUMENT_JSON, document.getDocument_json());
		values.put(DOCUMENT_JSON_TO_SUBMIT,
				document.getDocument_json_to_submit());
		values.put(SUBMITTED, document.getSubmitted());
		return db.update(DOCUMENT_TABLE, values, DOCUMENT_ID + "=?",
				new String[] { document.getDocument_id() });
	}

	public int updateDocumentByJSON(Document document, String old_document_json) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(DOCUMENT_NAME, document.getDocument_name());
		values.put(DOCUMENT_JSON, document.getDocument_json());
		return db.update(DOCUMENT_TABLE, values, DOCUMENT_JSON + "=?",
				new String[] { old_document_json });
	}

	public void deleteDocumentByID(String document_id) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(DOCUMENT_TABLE, DOCUMENT_ID + "=?",
				new String[] { document_id });
		db.close();
	}

	public Form getRelatedFormByDocumentID(String document_id) {
		Document document = getDocumentByDocumentID(document_id);
		Form form = getFormByFormID(document.getForm_id());
		return form;
	}

	public ArrayList<Document> getRelatedDocumentsByFormID(String form_id) {
		SQLiteDatabase db = this.getReadableDatabase();
		ArrayList<Document> relatedDocumentList = new ArrayList<Document>();
		String query = "select * from " + DOCUMENT_TABLE + " where " + FORM_ID
				+ "=?";
		Cursor cursor = db.rawQuery(query, new String[] { form_id });
		if (cursor != null && cursor.getCount() > 0) {
			cursor.moveToFirst();
			Document document = new Document();
			document.setId(cursor.getString(0));
			document.setDocument_id(cursor.getString(1));
			document.setDocument_name(cursor.getString(2));
			document.setDocument_json(cursor.getString(3));
			document.setDocument_json_to_submit(cursor.getString(4));
			document.setCreated_at(cursor.getString(5));
			document.setForm_id(cursor.getString(6));
			document.setCreated_worker(cursor.getString(7));
			document.setSubmitted(cursor.getString(8));
			relatedDocumentList.add(document);
		}
		return relatedDocumentList;
	}

	// Media

	public void addNewMedia(XMedia media) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(MEDIA_ID, media.getMedia_id());
		values.put(MEDIA_NAME, media.getMedia_name());
		values.put(MEDIA_PATH, media.getMedia_path());
		values.put(DOC_ID, media.getDoc_id());
		values.put(MEDIA_TYPE, media.getMedia_type());
		db.insert(MEDIA_TABLE, null, values);
		db.close();
	}
	
	public ArrayList<XMedia> getAllMedia(){
		ArrayList<XMedia> imageList = new ArrayList<XMedia>();
		String selectQuery = "SELECT * FROM " + MEDIA_TABLE + " WHERE "
				+ DOC_ID + "=?";
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, new String[] {});
		if (cursor.moveToFirst()) {
			do {
				XMedia media = new XMedia();
				media.setId(cursor.getString(0));
				media.setMedia_name(cursor.getString(1));
				media.setMedia_path(cursor.getString(2));
				media.setMedia_id(cursor.getString(3));
				media.setDoc_id(cursor.getString(4));
				media.setMedia_type(cursor.getString(5));
				imageList.add(media);
			} while (cursor.moveToNext());
		}
		cursor.close();
		db.close();
		return imageList;
	}

	public ArrayList<XMedia> getAllMediaByDocumentID(String documentID) {
		ArrayList<XMedia> imageList = new ArrayList<XMedia>();
		String selectQuery = "SELECT * FROM " + MEDIA_TABLE + " WHERE "
				+ DOC_ID + "=?";
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, new String[] { documentID });
		if (cursor.moveToFirst()) {
			do {
				XMedia media = new XMedia();
				media.setId(cursor.getString(0));
				media.setMedia_name(cursor.getString(1));
				media.setMedia_path(cursor.getString(2));
				media.setMedia_id(cursor.getString(3));
				media.setDoc_id(cursor.getString(4));
				media.setMedia_type(cursor.getString(5));
				imageList.add(media);
			} while (cursor.moveToNext());
		}
		cursor.close();
		db.close();
		return imageList;
	}

	public int updateMediaByPath(XMedia media) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(MEDIA_ID, media.getMedia_id());
		return db.update(MEDIA_TABLE, values, MEDIA_PATH + "=?",
				new String[] { media.getMedia_path() });
	}

	public XMedia getImageByImageID(String media_id) {
		XMedia media = new XMedia();
		SQLiteDatabase db = this.getReadableDatabase();
		String rawQuery = "SELECT * FROM " + MEDIA_TABLE + " WHERE " + MEDIA_ID
				+ "=?";
		String[] parameters = new String[] { media_id };
		Cursor cursor = db.rawQuery(rawQuery, parameters);
		if (cursor != null && cursor.getCount() > 0) {
			cursor.moveToFirst();
			media.setId(cursor.getString(0));
			media.setMedia_name(cursor.getString(1));
			media.setMedia_path(cursor.getString(2));
			media.setMedia_id(cursor.getString(3));
			media.setDoc_id(cursor.getString(4));
			media.setMedia_type(cursor.getString(5));
		}
		cursor.close();
		db.close();
		return media;
	}

	// following method is useful cuz we assumed that the image_name
	// (field_name) is currently unique
	public XMedia getImagePathByImageName(String image_name, String documentID) {
		XMedia media = new XMedia();
		SQLiteDatabase db = this.getReadableDatabase();
		String rawQuery = "SELECT * FROM " + MEDIA_TABLE + " WHERE "
				+ MEDIA_NAME + "=?" + " AND " + DOC_ID + "=?";
		String[] parameters = new String[] { image_name, documentID };
		Cursor cursor = db.rawQuery(rawQuery, parameters);
		if (cursor != null && cursor.getCount() > 0) {
			cursor.moveToFirst();
			media.setId(cursor.getString(0));
			media.setMedia_name(cursor.getString(1));
			media.setMedia_path(cursor.getString(2));
			media.setMedia_id(cursor.getString(3));
			media.setDoc_id(cursor.getString(4));
			media.setMedia_type(cursor.getString(5));
		}
		cursor.close();
		db.close();
		return media;
	}

	public int updateImage(XMedia media) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(MEDIA_ID, media.getMedia_id());
		values.put(MEDIA_NAME, media.getMedia_name());
		values.put(MEDIA_PATH, media.getMedia_path());
		values.put(DOC_ID, media.getDoc_id());
		values.put(MEDIA_TYPE, media.getMedia_type());
		return db.update(MEDIA_TABLE, values, MEDIA_ID + "=?",
				new String[] { media.getMedia_id() });
	}

	// AUDIO

	public void addNewAudio(Audio audio) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(AUDIO_ID, audio.getAudio_id());
		values.put(AUDIO_NAME, audio.getAudio_name());
		values.put(AUDIO_PATH, audio.getAudio_path());
		values.put(DOC_ID, audio.getDoc_id());
		db.insert(AUDIO_TABLE, null, values);
		db.close();
	}

	public boolean isAudioAlreadyExistInDB(String audioPath) {
		SQLiteDatabase db = this.getReadableDatabase();
		String query = "select * from " + AUDIO_TABLE + " where " + AUDIO_PATH
				+ "=?";
		Cursor cursor = db.rawQuery(query, new String[] { audioPath });
		if (cursor.getCount() == 0)
			return false;
		else
			return true;
	}

	public int updateAudioByPath(Audio audio) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(IMAGE_ID, audio.getAudio_id());
		return db.update(AUDIO_TABLE, values, AUDIO_PATH + "=?",
				new String[] { audio.getAudio_path() });
	}
	
	//synced image table
	// get all syncd image
	public ArrayList<SyncImage> getAllSyncedImage(){
		ArrayList<SyncImage> syncedImageList = new ArrayList<SyncImage>();
		String selectQuery = "SELECT * FROM " + SYNCED_IMAGE_TABLE;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, new String[] {});
		if (cursor.moveToFirst()) {
			do {
				SyncImage synceImage = new SyncImage();
				synceImage.setId(cursor.getString(0));
				synceImage.setImageID(cursor.getString(1));
				synceImage.setSynceID(cursor.getString(2));
				synceImage.setImgByte(cursor.getBlob(3));
				syncedImageList.add(synceImage);
			} while (cursor.moveToNext());
		}
		cursor.close();
		db.close();
		return syncedImageList;
	}
	
	// add new synced image
	public void addNewSyncImage(SyncImage syncImage) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(IMAGE_ID, syncImage.getImageID());
		values.put(SYNC_ID, syncImage.getSynceID());
		values.put(IMAGE_BYTE, syncImage.getImgByte());
		db.insert(SYNCED_IMAGE_TABLE, null, values);
		db.close();
	}
	
	// update new synced image
	public int updateSyncImage(SyncImage syncImage) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(IMAGE_ID, syncImage.getImageID());
		values.put(SYNC_ID, syncImage.getSynceID());
		values.put(IMAGE_BYTE, syncImage.getImgByte());
		return db.update(SYNCED_IMAGE_TABLE, values, IMAGE_ID + "=?",
				new String[] { syncImage.getImageID() });
	}
	
	// get all image
	public SyncImage getSyncImageByImageID(String imageID) {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery("select * from " + SYNCED_IMAGE_TABLE + " where "
				+ IMAGE_ID + "=?", new String[] { imageID });
		SyncImage syncImage = new SyncImage();
		if (cursor != null && cursor.getCount() > 0) {
			cursor.moveToFirst();
			// 0 is id and no need
			syncImage.setImageID(cursor.getString(1));
			syncImage.setSynceID(cursor.getString(2));
			syncImage.setImgByte(cursor.getBlob(3));
			cursor.close();
			db.close();
			return syncImage;
		} else
			return null;
	}
	
	//isImageIDAlreadyExistInSyncImageTable
	public boolean isImageIDAlreadyExistInSyncImageTable(String imageID) {
		SQLiteDatabase db = this.getReadableDatabase();
		String query = "select * from " + SYNCED_IMAGE_TABLE + " where " + IMAGE_ID
				+ "=?";
		Cursor cursor = db.rawQuery(query, new String[] { imageID });
		if (cursor.getCount() == 0)
			return false;
		else
			return true;
	}
	
	//isSyncIDAlreadyExistInSyncImageTable
	public boolean isSyncIDAlreadyExistInSyncImageTable(String imageID, String syncID) {
		SQLiteDatabase db = this.getReadableDatabase();
		String query = "select * from " + SYNCED_IMAGE_TABLE + " where " + IMAGE_ID
				+ "=?" + " and " + SYNC_ID + "=?";
		Cursor cursor = db.rawQuery(query, new String[] { imageID, syncID });
		if (cursor.getCount() == 0)
			return false;
		else
			return true;
	}
	
}
