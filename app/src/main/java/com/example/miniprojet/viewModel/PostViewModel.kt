package com.example.miniprojet.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.miniprojet.model.Comment
import com.example.miniprojet.model.Post
import com.example.miniprojet.repository.PostRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class PostViewModel(private val repo: PostRepository = PostRepository()) : ViewModel() {

    private val _postsState = MutableStateFlow<UiState<List<Post>>>(UiState.Loading)
    val postsState: StateFlow<UiState<List<Post>>> = _postsState

    private val _postDetailState = MutableStateFlow<UiState<Post>>(UiState.Loading)
    val postDetailState = _postDetailState

    private val _commentsState = MutableStateFlow<UiState<List<Comment>>>(UiState.Loading)
    val commentsState = _commentsState

    private val _formState = MutableStateFlow<UiState<Post?>>(UiState.Loading)
    val formState = _formState

    private val _snackbarMessage = MutableSharedFlow<String>()
    val snackbarMessage = _snackbarMessage.asSharedFlow()

    fun loadPosts() = viewModelScope.launch {
        _postsState.value = UiState.Loading
        runCatching { repo.list() }
            .onSuccess { list ->
                _postsState.value = if (list.isEmpty()) UiState.Empty else UiState.Success(list)
            }
            .onFailure { _postsState.value = UiState.Error(it.message ?: "Erreur inconnue") }
    }

    fun loadPostAndComments(id: Int) = viewModelScope.launch {
        _postDetailState.value = UiState.Loading
        _commentsState.value = UiState.Loading

        runCatching { repo.details(id) }
            .onSuccess { _postDetailState.value = UiState.Success(it) }
            .onFailure { _postDetailState.value = UiState.Error(it.message ?: "...") }

        runCatching { repo.comments(id) }
            .onSuccess { _commentsState.value = UiState.Success(it) }
            .onFailure { _commentsState.value = UiState.Error(it.message ?: "...") }
    }

    fun loadPostForEdit(id: Int) = viewModelScope.launch {
        _formState.value = UiState.Loading
        runCatching { repo.details(id) }
            .onSuccess { _formState.value = UiState.Success(it) }
            .onFailure { _formState.value = UiState.Error(it.message ?: "...") }
    }

    fun createOrUpdatePost(title: String, body: String, postId: Int? = null) = viewModelScope.launch {
        if (title.isBlank() || body.isBlank()) {
            _snackbarMessage.emit("Titre et contenu obligatoires")
            return@launch
        }

        val result = runCatching {
            if (postId == null) {
                repo.create(1, title, body)  // userId=1 (fixe pour test)
            } else {
                repo.patch(postId, mapOf("title" to title, "body" to body))
            }
        }

        result.onSuccess {
            _snackbarMessage.emit(if (postId == null) "Post créé !" else "Post modifié !")
        }.onFailure {
            _snackbarMessage.emit("Échec : ${it.message}")
        }
    }

    fun deletePost(id: Int) = viewModelScope.launch {
        if (repo.remove(id)) {
            _snackbarMessage.emit("Post supprimé (simulé)")
        } else {
            _snackbarMessage.emit("Échec suppression")
        }
    }
}
