package com.rakib.commitviewapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rakib.commitviewapp.base.ErrorResponse
import com.rakib.commitviewapp.base.Resource
import com.rakib.commitviewapp.repository.CommitViewRepository
import com.rakib.commitviewapp.repository.model.UserModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CommitViewModel @Inject constructor(private val commitViewRepo: CommitViewRepository) :
    ViewModel() {
    val commitViewData = commitViewRepo.data


    private var _userModelResult = MutableLiveData<Resource<UserModel, ErrorResponse>>()
    var userModelResult: LiveData<Resource<UserModel, ErrorResponse>> = _userModelResult

    fun getUserProfile(userName: String) {
        _userModelResult.postValue(Resource.loading(null))
        viewModelScope.launch {
            _userModelResult.postValue(commitViewRepo.getUserProfile(userName))
        }
    }

}

