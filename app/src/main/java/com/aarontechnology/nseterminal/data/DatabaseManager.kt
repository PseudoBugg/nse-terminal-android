package com.aarontechnology.nseterminal.data

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.net.Uri
import android.util.Log
import java.io.File
import java.io.FileOutputStream

object DatabaseManager {
    private const val PREFS_NAME = "nseterminal_prefs"
    private const val KEY_DB_CONFIGURED = "is_db_configured"
    private const val KEY_DB_NAME = "configured_db_filename"
    const val DB_FILE_NAME = "screener_nse100.db"

    fun isDatabaseConfigured(context: Context): Boolean {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val configured = prefs.getBoolean(KEY_DB_CONFIGURED, false)
        val file = context.getDatabasePath(DB_FILE_NAME)
        return configured && file.exists() && file.length() > 0
    }

    fun getConfiguredFileName(context: Context): String {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        return prefs.getString(KEY_DB_NAME, DB_FILE_NAME) ?: DB_FILE_NAME
    }

    /**
     * Imports the selected SQLite database via Storage Access Framework (SAF).
     * Requires ZERO dangerous storage permissions.
     */
    fun importDatabaseFromUri(context: Context, uri: Uri, originalFileName: String?): Boolean {
        return try {
            val dbFile = context.getDatabasePath(DB_FILE_NAME)
            dbFile.parentFile?.mkdirs()

            context.contentResolver.openInputStream(uri)?.use { inputStream ->
                FileOutputStream(dbFile).use { outputStream ->
                    inputStream.copyTo(outputStream)
                }
            }

            val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            prefs.edit()
                .putBoolean(KEY_DB_CONFIGURED, true)
                .putString(KEY_DB_NAME, originalFileName ?: DB_FILE_NAME)
                .apply()
            true
        } catch (e: Exception) {
            Log.e("DatabaseManager", "Error importing database: ${e.message}", e)
            false
        }
    }

    fun openDatabase(context: Context): SQLiteDatabase? {
        val dbFile = context.getDatabasePath(DB_FILE_NAME)
        if (!dbFile.exists()) return null
        return try {
            SQLiteDatabase.openDatabase(dbFile.absolutePath, null, SQLiteDatabase.OPEN_READONLY)
        } catch (e: Exception) {
            Log.e("DatabaseManager", "Could not open SQLite database: ${e.message}", e)
            null
        }
    }

    fun resetDatabase(context: Context) {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        prefs.edit().clear().apply()
        val dbFile = context.getDatabasePath(DB_FILE_NAME)
        if (dbFile.exists()) {
            dbFile.delete()
        }
    }
}
