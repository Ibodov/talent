package com.smile.talent_official.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.smile.talent_official.db.dao.TalentDao
import com.smile.talent_official.data.models.TalentModel


//6 - Создаётся БД
@Database(entities = [TalentModel::class], version = 1) // при изменении в БД или в Dao необходимо увеличить версию
abstract class TalentDatabase : RoomDatabase() {
    abstract fun getTalentDao() : TalentDao

    companion object{
        private var database: TalentDatabase ?= null

        @Synchronized
        fun getInstance(context: Context) : TalentDatabase{
            return if (database == null) { // если БД будет null то
                database = Room.databaseBuilder(context, TalentDatabase::class.java, "db").build() //создаем БД
                database as TalentDatabase //и вернем
            }else{
                database as TalentDatabase //если она есть то просто вернем
            }
        }
    }
}