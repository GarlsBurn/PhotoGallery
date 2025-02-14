package com.bignerdranch.android.photogalleryactivity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.http.Query


class PhotoGalleryViewModel: ViewModel() {

    val galleryItemLiveData: LiveData<List<GalleryItem>>

    private val flickrFetchr = FlickrFetchr()
    private val mutableSearchTerm = MutableLiveData<String>()



    init {
        mutableSearchTerm.value = "car"


         galleryItemLiveData = MediatorLiveData<List<GalleryItem>>().apply {
            addSource(mutableSearchTerm){
                searchTerm -> val liveData = flickrFetchr.searchPhoto(searchTerm)
                addSource(liveData){
                    photo -> value = photo
                    removeSource(liveData)
                }
            }
        }
    }


    fun fetchPhotos(query: String = ""){
        mutableSearchTerm.value = query
    }
}