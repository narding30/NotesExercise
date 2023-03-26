/*
  Created by Benhard on 26/03/2023, 7:07 PM
  narding30x@gmail.com
  Last modified 26/03/2023, 7:05 PM
  Copyright (c) 2023.
  All rights reserved.
 */
package com.codev.recruitment.benhardrivera.ui

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.WindowManager
import com.codev.recruitment.benhardrivera.databinding.CreateContactDialogBinding
import com.codev.recruitment.benhardrivera.data.entity.Contacts
import com.codev.recruitment.benhardrivera.utils.Constants
import com.codev.recruitment.benhardrivera.viewmodel.ContactViewModel

/**
 * This class manages the Dialog processes
 */
class CreateContactDialog(
    private val context: Context,
    private val contactViewModel: ContactViewModel
) {
    companion object {
        var contacts: Contacts? = null
    }

    /**
     * This method is responsible for creating and showing our Dialog.
     */
    fun createContact() {
        val dialogBinding = CreateContactDialogBinding.inflate(LayoutInflater.from(context))
        val dialog = Dialog(context)
        dialog.setContentView(dialogBinding.root)
        dialog.setCancelable(true)

        dialog.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )

        val phoneNumber = dialogBinding.etNumber
        val firstName = dialogBinding.etFirstName
        val lastName = dialogBinding.etLastName

        contacts?.let {
            phoneNumber.setText(it.number)
            lastName.setText(it.lastname)
            firstName.setText(it.firstname)
        }

        dialogBinding.save.setOnClickListener {
            upsertContact(
                firstName.text.toString(),
                lastName.text.toString(),
                phoneNumber.text.toString()
            )

            dialog.dismiss()
        }

        dialog.show()
    }

    /**
     * This method is responsible for checking and adding/updating our database.
     *
     * @param firstName of the user.
     * @param lastName of the user.
     * @param phoneNumber of the user.
     */
    private fun upsertContact(firstName: String, lastName: String, phoneNumber: String) {
        if (phoneNumber.isEmpty()
            || firstName.isEmpty()
            || lastName.isEmpty()
        ) {
            Constants.showToast("Please input all fields", context)
            return
        }
        contacts?.let {
            contactViewModel.upsert(
                contact = Contacts(
                    id = it.id,
                    firstname = firstName,
                    lastname = lastName,
                    number = phoneNumber,
                    favorite = it.favorite
                )
            )
            Constants.showToast("Successfully updated contact", context)
        } ?: run {
            contactViewModel.upsert(
                contact = Contacts(
                    firstname = firstName,
                    lastname = lastName,
                    number = phoneNumber,
                    favorite = false
                )
            )
            Constants.showToast("Successfully added contact", context)
        }
    }
}