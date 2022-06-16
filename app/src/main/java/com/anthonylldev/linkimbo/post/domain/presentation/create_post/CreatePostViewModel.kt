package com.anthonylldev.linkimbo.post.domain.presentation.create_post

import android.app.Application
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anthonylldev.linkimbo.authentication.domain.model.User
import com.anthonylldev.linkimbo.post.application.data.PostRequest
import com.anthonylldev.linkimbo.post.application.service.PostService
import com.anthonylldev.linkimbo.post.domain.model.Post
import com.anthonylldev.linkimbo.util.Constants
import com.anthonylldev.linkimbo.util.ImageUtil
import com.anthonylldev.linkimbo.util.ui.presentation.TextFieldState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.io.ByteArrayOutputStream
import javax.inject.Inject

@HiltViewModel
class CreatePostViewModel @Inject constructor(
    private val application: Application,
    private val postService: PostService,
    private val sharedPreferences: SharedPreferences
) : ViewModel() {

    private val _imageUri = mutableStateOf<Uri?>(null)
    val imageUri: State<Uri?> = _imageUri

    private val _bitmap = mutableStateOf<Bitmap?>(null)
    val bitmap: State<Bitmap?> = _bitmap

    private val _postDetail = mutableStateOf(TextFieldState())
    val postDetail: State<TextFieldState> = _postDetail

    fun setImageUriState(state: Uri?) {
        this._imageUri.value = state

        if (this._imageUri.value != null) {
            setBitmapState(this._imageUri.value!!)
        }
    }

    private fun setBitmapState(uri: Uri) {
        if (Build.VERSION.SDK_INT < 28) {
            this._bitmap.value = MediaStore.Images
                .Media.getBitmap(application.contentResolver, uri)

        } else {
            val source = ImageDecoder
                .createSource(application.contentResolver, uri)
            this._bitmap.value = ImageDecoder.decodeBitmap(source)
        }
    }

    fun setPostDetailState(state: TextFieldState) {
        this._postDetail.value = state
    }

    fun saveImage() {
        val userId: String? = sharedPreferences.getString(Constants.PERSONAL_USER_ID, null)

        viewModelScope.launch {
            if (_bitmap.value != null && userId != null) {
                val timestamp = System.currentTimeMillis()
                val image = ImageUtil.bitmapToBase64(_bitmap.value!!)

                if (image != null) {
                    postService.insertPost(
                        PostRequest(
                            userId = userId,
                            imageBase64 = image,
                            description = _postDetail.value.text,
                            timestamp = timestamp
                        )
                    )
                }
            }
        }
    }
}