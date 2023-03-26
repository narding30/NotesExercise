/*
  Created by Benhard on 26/03/2023, 7:07 PM
  narding30x@gmail.com
  Last modified 26/03/2023, 7:05 PM
  Copyright (c) 2023.
  All rights reserved.
 */
package com.codev.recruitment.benhardrivera.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.codev.recruitment.benhardrivera.data.dao.ContactsDao
import com.codev.recruitment.benhardrivera.data.entity.Contacts

/**
 * This class manages the Room Database
 */
@Database(entities = [Contacts::class], version = 1, exportSchema = false)
abstract class ContactsDatabase : RoomDatabase() {

    abstract fun contactsDao() : ContactsDao
}
