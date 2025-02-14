package com.bignerdranch.android.photogalleryactivity

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.http.Query


class PhotoGalleryViewModel(private val app: Application): AndroidViewModel(app) {

    val galleryItemLiveData: LiveData<List<GalleryItem>>

    private val flickrFetchr = FlickrFetchr()
    private val mutableSearchTerm = MutableLiveData<String>()

    val searchTerm: String
        get() = mutableSearchTerm.value ?:""


    init {
        mutableSearchTerm.value = QueryPreferences.getStoredQuery(app)


         galleryItemLiveData = MediatorLiveData<List<GalleryItem>>().apply {
            addSource(mutableSearchTerm){
                searchTerm -> val liveData =
                    if ( searchTerm.isBlank()){flickrFetchr.fetchPhotos()
                    } else {
                        flickrFetchr.searchPhoto(searchTerm)
                    }
                addSource(liveData){
                    photo -> value = photo
                    removeSource(liveData)
                }
            }
        }
    }


    fun fetchPhotos(query: String = ""){
        QueryPreferences.setStoredQuery(app, query)
        mutableSearchTerm.value = query
    }
}