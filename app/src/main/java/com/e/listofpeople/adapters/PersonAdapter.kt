package com.e.listofpeople.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.e.listofpeople.R
import com.e.listofpeople.data.Person
import com.e.listofpeople.databinding.ListItemPersonBinding

class PersonAdapter
    : PagingDataAdapter<Person, PersonAdapter.PersonViewHolder>(PersonDiffCallback()) {

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = PersonViewHolder(
        ListItemPersonBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    class PersonViewHolder(
        private val binding: ListItemPersonBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(person: Person?) {
            binding.nameAndIdText.run {
                text = this.context.getString(
                    R.string.person_list_item,
                    person?.fullName,
                    person?.id.toString()
                )
            }
        }
    }
}

private class PersonDiffCallback : DiffUtil.ItemCallback<Person>() {

    override fun areItemsTheSame(oldItem: Person, newItem: Person): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Person, newItem: Person): Boolean {
        return oldItem == newItem
    }
}