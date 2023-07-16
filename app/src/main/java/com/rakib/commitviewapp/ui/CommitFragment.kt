package com.rakib.commitviewapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.rakib.commitviewapp.R
import com.rakib.commitviewapp.adapter.LoaderAdapter
import com.rakib.commitviewapp.adapter.CommitViewAdapter
import com.rakib.commitviewapp.base.BaseFragment
import com.rakib.commitviewapp.databinding.FragmentCommitBinding
import com.rakib.commitviewapp.repository.model.CommitModelItem
import com.rakib.commitviewapp.utils.SimpleCallback
import com.rakib.commitviewapp.viewmodel.CommitViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class CommitFragment : BaseFragment() {
    @Inject
    lateinit var commitViewAdapter: CommitViewAdapter
    private val viewModel by viewModels<CommitViewModel>()

    private lateinit var binding:FragmentCommitBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCommitBinding.inflate(inflater)
        return binding.root
    }

    override fun configUi() {
        commitViewAdapter.clickListener = object : SimpleCallback<CommitModelItem> {
            override fun callback(any: CommitModelItem) {
                val bundle = Bundle()
                bundle.putString("username", any.committer.login)
                findNavController().navigate(
                    R.id.action_commitFragment_to_profileFragment,
                    args = bundle
                )
            }

        }
        //   viewModel.insertAllProductVM(Utils.productItems)
        binding.commitViewRecyclerView.adapter = commitViewAdapter.withLoadStateFooter(LoaderAdapter())
        lifecycleScope.launch {
            viewModel.commitViewData.collectLatest {
                commitViewAdapter.submitData(it)
            }
        }

    }

    override fun setupNavigation() {


    }


}