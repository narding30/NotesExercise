/*
  Created by Benhard on 26/03/2023, 7:07 PM
  narding30x@gmail.com
  Last modified 26/03/2023, 7:05 PM
  Copyright (c) 2023.
  All rights reserved.
 */

package com.codev.recruitment.benhardrivera.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Delete
import com.codev.recruitment.benhardrivera.data.entity.Contacts

/**
 * This class manages the Dao for Contacts
 */
@Dao
interface ContactsDao {
    @Query("Select * from contacts_table")
    fun getAllContacts() : LiveData<List<Contacts>>

    @Insert(onConflict = OnConflictStrategy.REPLACE )
    suspend fun upsert(contact : Contacts)

    @Delete
    suspend fun delete(contact: Contacts)
}

