/*
  Created by Benhard on 26/03/2023, 7:07 PM
  narding30x@gmail.com
  Last modified 26/03/2023, 7:05 PM
  Copyright (c) 2023.
  All rights reserved.
 */
package com.codev.recruitment.benhardrivera.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codev.recruitment.benhardrivera.data.entity.Contacts
import com.codev.recruitment.benhardrivera.data.repository.ContactsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * This class manages the Contact View Model
 */
@HiltViewModel
class ContactViewModel @Inject constructor(
    private val contactsRepository: ContactsRepository
) : ViewModel() {

    /**
     * This method is responsible for adding and updating our database.
     *
     * @param contact details to be added/updated.
     */
    fun upsert(contact : Contacts) = viewModelScope.launch{
        contactsRepository.upsert(contact)
    }

    /**
     * This method is responsible for deleting a row on our database.
     *
     * @param contact details to be deleted.
     */
    fun delete(contact: Contacts) = viewModelScope.launch {
        contactsRepository.delete(contact)
    }

    /**
     * This method is responsible for getting all data on our database.
     *
     * @return all contact details.
     */
    fun getAllContacts() : LiveData<List<Contacts>> =
        contactsRepository.getAllContacts()
}
