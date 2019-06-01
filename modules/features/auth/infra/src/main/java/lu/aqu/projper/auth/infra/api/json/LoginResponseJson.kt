package lu.aqu.projper.auth.infra.api.json

import com.google.gson.annotations.SerializedName

data class LoginResponseJson(
    @SerializedName("id") val id: Long,
    @SerializedName("email") val email: String,
    @SerializedName("token") val token: String
)
