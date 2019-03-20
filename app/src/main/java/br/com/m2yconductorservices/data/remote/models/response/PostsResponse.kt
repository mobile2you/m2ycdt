package br.com.m2yconductorservices.data.remote.models.response

/**
 * Created by azul on 17/04/17.
 */

data class PostsResponse (
    var userId: Int = 0,
    var id: Int = 0,
    var title: String? = null,
    var body: String? = null
)
