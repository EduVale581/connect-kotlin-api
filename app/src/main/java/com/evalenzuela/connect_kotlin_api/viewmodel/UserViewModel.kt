package com.evalenzuela.connect_kotlin_api.viewmodel


import android.content.Context
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.evalenzuela.connect_kotlin_api.data.model.User
import com.evalenzuela.connect_kotlin_api.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class UserViewModel : ViewModel() {


    private val repo = UserRepository()

    private val _users = MutableStateFlow<List<User>>(emptyList())
    val users: StateFlow<List<User>> = _users

    private val _lastResult = MutableStateFlow<User?>(null)

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading

    fun loadingTrue() {
        _loading.value = true
        _loading.value = false
    }

    fun fetchUsers() {
        viewModelScope.launch {
            _loading.value = true
            try {
                _users.value = repo.getUsers()
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                _loading.value = false
            }
        }
    }

    fun createUser(name: String, email: String) {
        viewModelScope.launch {
            _loading.value = true
            try {
                _lastResult.value = repo.createUser(name, email)
                fetchUsers()
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                _loading.value = false
            }
        }
    }

    fun uploadUserWithImage(context: Context, name: String, email: String, uri: Uri) {
        viewModelScope.launch {
            _loading.value = true
            try {
                _lastResult.value = repo.uploadUserWithImage(context, name, email, uri)
                fetchUsers()
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                _loading.value = false
            }
        }
    }

    fun updateUser(id: Int, name: String, email: String) {
        viewModelScope.launch {
            _loading.value = true
            try {
                _lastResult.value = repo.updateUser(id, name, email)
                fetchUsers()
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                _loading.value = false
            }
        }
    }

    fun deleteUser(id: Int) {
        viewModelScope.launch {
            _loading.value = true
            try {
                repo.deleteUser(id)
                fetchUsers()
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                _loading.value = false
            }
        }
    }
}
