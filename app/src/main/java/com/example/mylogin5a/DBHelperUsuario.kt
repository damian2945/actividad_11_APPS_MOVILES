package com.example.mylogin5a

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelperUsuario(context: Context) : SQLiteOpenHelper(context, DB_name, null, DB_version){
    companion object {
        private const val DB_version = 1
        private const val DB_name = "dbServicios"
        private const val nomTable = "usuarios"
        private const val keyId = "id"
        private const val usrLogin = "userLogin"
        private const val usrPass = "userPass"
        private const val usrEmail = "userEmail"
        private const val usrNombre = "userNombre"
    }

    //Variable para crear nuestra tabla
    private val sqlCreate: String = "CREATE TABLE $nomTable ($keyId INTEGER PRIMARY KEY,$usrLogin TEXT,$usrPass TEXT,$usrEmail TEXT,$usrNombre TEXT)"

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(sqlCreate)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $nomTable")
        onCreate(db)
    }

    override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }
}