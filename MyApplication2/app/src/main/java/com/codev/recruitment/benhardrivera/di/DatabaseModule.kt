/*
  Created by Benhard on 26/03/2023, 7:07 PM
  narding30x@gmail.com
  Last modified 26/03/2023, 7:05 PM
  Copyright (c) 2023.
  All rights reserved.
 */
package com.codev.recruitment.benhardrivera.di

import android.app.Application
import androidx.room.Room
import com.codev.recruitment.benhardrivera.data.ContactsDatabase
import com.codev.recruitment.benhardrivera.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * This class manages the Dependency Injection
 */
@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    /**
     * This method is responsible for providing a instance of our database.
     *
     * @param application the global context/state of the application.
     * @return an instance of our database (ContactsDatabase)
     */
    @Provides
    @Singleton
    fun provideDatabase(
        application: Application
    ) = Room.databaseBuilder(
        application,
        ContactsDatabase::class.java,
        Constants.DB_NAME
    ).build()

    /**
     * This method is responsible for providing a single instance of our dao.
     *
     * @param db the instance of our database.
     * @return an instance of our dao (ContactsDao)
     */
    @Provides
    @Singleton
    fun provideMovieAppDao(db: ContactsDatabase) = db.contactsDao()
}