/*
  Created by Benhard on 26/03/2023, 7:07 PM
  narding30x@gmail.com
  Last modified 26/03/2023, 7:05 PM
  Copyright (c) 2023.
  All rights reserved.
 */
package com.codev.recruitment.benhardrivera.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.codev.recruitment.benhardrivera.utils.Constants

/**
 * This class manages the Entity for Contacts
 */
@Entity(tableName = Constants.table_name)
class Contacts (
    @PrimaryKey(autoGenerate = true)
    var id : Int? = null,
    var firstname : String,
    var lastname : String,
    var favorite : Boolean,
    var number : String
)