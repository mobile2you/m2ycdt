package br.com.m2yconductorservices.data.remote.models.response

data class TokenCDTResponse (var access_token: String,
                          var expires_in: Int,
                          var token_type: String)