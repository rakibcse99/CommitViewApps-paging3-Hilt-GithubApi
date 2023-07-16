package com.rakib.commitviewapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.rakib.commitviewapp.adapter.CommitViewAdapter
import com.rakib.commitviewapp.base.BaseFragment
import com.rakib.commitviewapp.base.Status
import com.rakib.commitviewapp.databinding.FragmentProfileBinding
import com.rakib.commitviewapp.utils.toast
import com.rakib.commitviewapp.viewmodel.CommitViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class
ProfileFragment : BaseFragment() {
    private lateinit var binding: FragmentProfileBinding

    @Inject
    lateinit var commitViewAdapter: CommitViewAdapter
    private val viewModel by viewModels<CommitViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater)
        //showCategoryBottomSheet()
        return binding.root
    }


    override fun configUi() {
        val userName = arguments?.getString("username", "rakibcse99")

        viewModel.getUserProfile(userName ?:"rakibcse99")

        viewModel.userModelResult.observe(this){
            when(it.status){
                Status.SUCCESS -> {
                    it.data?.let {userModel->
                        binding.textName.text = userModel.name
                        binding.usernameTextView.text = "@${userModel.login}"
                        Glide.with(this).load(userModel.avatar_url).into( binding.profileImageView);
                        binding.bioTextView.text = "Bio: ${userModel.bio}"
                        binding.publicReposTextview.text = "Public Repos: ${userModel.public_repos}"
                        binding.publicGistsTextview.text = "Public Gists: ${userModel.public_gists}"

                    }
                 }

                Status.LOADING -> {}
                Status.ERROR -> {
                    val errMsg = it.error?.message ?: ""
                    toast(errMsg)
                 }
            }

        }
    }


}
