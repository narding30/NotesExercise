/*
  Created by Benhard on 26/03/2023, 7:07 PM
  narding30x@gmail.com
  Last modified 26/03/2023, 7:05 PM
  Copyright (c) 2023.
  All rights reserved.
 */
package com.codev.recruitment.benhardrivera.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.codev.recruitment.benhardrivera.adapter.ContactsAdapter
import com.codev.recruitment.benhardrivera.databinding.ActivityMainBinding
import com.codev.recruitment.benhardrivera.viewmodel.ContactViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * This class manages the Main View
 */
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val viewModel : ContactViewModel by viewModels()

    private var createContactDialog : CreateContactDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        createContactDialog = CreateContactDialog(this, viewModel)


        binding.floatingActionButton.setOnClickListener{
            CreateContactDialog.contacts = null
            createContactDialog!!.createContact()
        }

        viewModel.getAllContacts().observe(this) { list ->
            binding.recyclerView.layoutManager = LinearLayoutManager(applicationContext)
            binding.recyclerView.adapter = ContactsAdapter(this, list, viewModel)
        }
    }
}