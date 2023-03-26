/*
  Created by Benhard on 26/03/2023, 7:07 PM
  narding30x@gmail.com
  Last modified 26/03/2023, 7:05 PM
  Copyright (c) 2023.
  All rights reserved.
 */
package com.codev.recruitment.benhardrivera.utils

import android.content.Context
import android.widget.Toast

/**
 * This class manages all the Constants
 */
object Constants {

    const val DB_NAME = "ContactsDB"
    const val table_name = "contacts_table"

    /**
     * This method is responsible for showing toast.
     *
     * @param msg message to be shown.
     * @param ctx context to use.
     */
    fun showToast(msg: String?, ctx: Context?) {
        Toast.makeText(ctx, msg, Toast.LENGTH_SHORT).show()
    }
}