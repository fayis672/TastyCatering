package com.example.tastycatering.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.tastycatering.data.db.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext application: Context)=
      Room.databaseBuilder(application,AppDatabase::class.java,"selected databse")
          .fallbackToDestructiveMigration()
          .allowMainThreadQueries()
          .build()

    @Provides
    @Singleton
    fun provideDoa(appDatabase: AppDatabase) = appDatabase.selectedItemDao()

}