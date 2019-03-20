package br.com.m2yconductorservices.data.remote.models.response

data class FullLoginResponse(val token : TokenCDTResponse, val user: UserResponse)