package br.com.m2yconductorservices.data.remote.models.response

data class PaginatedResponse<T>(val number: Int,
                                val totalPages: Int,
                                val hasNextPage: Boolean,
                                val last: Boolean?,
                                val total: Float?,
                                val content: List<T>)