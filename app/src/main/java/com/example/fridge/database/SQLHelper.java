package com.example.fridge.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.FileOutputStream;

public class SQLHelper extends SQLiteOpenHelper {

    /** Name mDatabase in /assets. */
    private static final String DB_NAME = "database.db";

    /** Database version. */
    private static final int DB_VERSION = 2;

    /** The path to the database. */
    private static String mDbPath = "";

    /** App context. */
    private Context mContext;

    /** Database object. */
    private SQLiteDatabase mDatabase;

    /**
     * Constructor - initialize object.
     * @param context - app context.
     */
    public SQLHelper(final Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        final byte deviceVersion = 17;
        if (android.os.Build.VERSION.SDK_INT >= deviceVersion) {
            mDbPath = context.getApplicationInfo().dataDir + "/databases/";
        } else {
            mDbPath = context.getFilesDir().getPath()
                    + context.getPackageName()
                    + "/databases/";
        }

        this.mContext = context;

        copyDataBase();
        mDatabase = getWritableDatabase();
        this.getWritableDatabase();
    }

    /** Checking the existence of the database.
     * @return - exists.
     */
    private boolean checkDataBase() {
        File dbFile = new File(mDbPath + DB_NAME);
        return dbFile.exists();
    }

    /** Copy database in android catalog. */
    private void copyDataBase() {
        if (!checkDataBase()) {
            this.getReadableDatabase();
            this.close();
            try {
                copyDBFile();
            } catch (IOException mIOException) {
                throw new Error("ErrorCopyingDataBase");
            }
        }
    }

    /**
     * Copy process.
     * @throws IOException .
     */
    private void copyDBFile() throws IOException {
        InputStream mInput = mContext.getAssets().open(DB_NAME);
        OutputStream mOutput = new FileOutputStream(mDbPath + DB_NAME);
        final int mByte = 1024;
        byte[] mBuffer = new byte[mByte];
        int mLength;
        while ((mLength = mInput.read(mBuffer)) > 0) {
            mOutput.write(mBuffer, 0, mLength);
        }
        mOutput.flush();
        mOutput.close();
        mInput.close();
    }

    @Override
    public final void onCreate(final SQLiteDatabase db) {

    }

    @Override
    public final void onUpgrade(final SQLiteDatabase db,
                                final int oldVersion,
                                final int newVersion) {
        copyDataBase();
    }

    /**
     * Function to get value of field.
     * @return database object.
     */
    public final SQLiteDatabase getDatabase() {
        return mDatabase;
    }

}
