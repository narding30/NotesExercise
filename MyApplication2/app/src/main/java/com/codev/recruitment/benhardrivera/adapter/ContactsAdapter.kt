/*
  Created by Benhard on 26/03/2023, 7:07 PM
  narding30x@gmail.com
  Last modified 26/03/2023, 7:05 PM
  Copyright (c) 2023.
  All rights reserved.
 */
package com.codev.recruitment.benhardrivera.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.codev.recruitment.benhardrivera.R
import com.codev.recruitment.benhardrivera.databinding.ContactsCardviewBinding
import com.codev.recruitment.benhardrivera.data.entity.Contacts
import com.codev.recruitment.benhardrivera.ui.CreateContactDialog
import com.codev.recruitment.benhardrivera.utils.Constants
import com.codev.recruitment.benhardrivera.viewmodel.ContactViewModel

/**
 * This class manages the Adapter for RecyclerView of our View
 */
class ContactsAdapter(
    private val context: Context,
    private val list: List<Contacts>,
    private val contactsViewModel: ContactViewModel
) : RecyclerView.Adapter<ContactsAdapter.ViewHolder>() {

    class ViewHolder(val binding: ContactsCardviewBinding) : RecyclerView.ViewHolder(binding.root)

    private var createContactDialog: CreateContactDialog? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ContactsCardviewBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        createContactDialog = CreateContactDialog(context, contactsViewModel)
        holder.binding.contactName.text = "${list[position].lastname}, ${list[position].firstname}"
        holder.binding.contactNumber.text = list[position].number

        val isFavorite = list[position].favorite
        if (isFavorite) {
            holder.binding.favorite.setImageResource(R.drawable.favorite)
        }

        holder.binding.delete.setOnClickListener {
            contactsViewModel.delete(list[position])
            notifyItemRemoved(position)
            Constants.showToast("Successfully deleted", context)
        }

        holder.binding.favorite.setOnClickListener {
            contactsViewModel.upsert(
                Contacts(
                    id = list[position].id,
                    firstname = list[position].firstname,
                    lastname = list[position].lastname,
                    number = list[position].number,
                    favorite = !isFavorite
                )
            )
        }

        holder.binding.edit.setOnClickListener {
            CreateContactDialog.contacts = list[position]
            createContactDialog!!.createContact()
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}
