package com.bignerdranch.android.photogalleryactivity.api

import com.bignerdranch.android.photogalleryactivity.GalleryItem
import com.google.gson.annotations.SerializedName

class PhotoResponse {
    @SerializedName("photo")
    lateinit var galleryItems: List<GalleryItem>
}