package com.e.listofpeople.viewmodels

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.e.listofpeople.data.Person
import com.e.listofpeople.data.PersonPagingSource
import kotlinx.coroutines.flow.Flow

class PersonListViewModel : ViewModel() {

    val personList: Flow<PagingData<Person>> = Pager(PagingConfig(20)) {
        PersonPagingSource()
    }.flow
}