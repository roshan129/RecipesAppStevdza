package com.adivid.recipesappstevdza.data

import javax.inject.Inject

class Repository @Inject constructor(
    remoteDataSource: RemoteDataSource
) {

    val remote = remoteDataSource

}