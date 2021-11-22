package com.e.listofpeople

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.e.listofpeople.adapters.PersonAdapter
import com.e.listofpeople.databinding.FragmentPersonListBinding
import com.e.listofpeople.viewmodels.PersonListViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class PersonListFragment : Fragment() {

    private val viewModel: PersonListViewModel by viewModels()

    private val adapter = PersonAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentPersonListBinding.inflate(inflater, container, false)
        binding.personList.adapter = adapter
        adapter.addLoadStateListener {
            if (it.refresh is LoadState.Error) {
                showError(it.refresh.toString())
            }
        }
        return binding.root
    }

    private fun showError(errorText: String) {
        AlertDialog.Builder(context).run {
            setMessage(errorText)
            setPositiveButton(getString(R.string.error_pop_up_retry)) { _, _ ->
                adapter.retry()
            }
            show()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            viewModel.personList.collectLatest {
                adapter.submitData(it)
            }
        }
    }
}