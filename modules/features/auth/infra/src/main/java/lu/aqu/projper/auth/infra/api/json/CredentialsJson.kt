package lu.aqu.projper.auth.infra.api.json

import com.google.gson.annotations.SerializedName

data class CredentialsJson(
    @SerializedName("email") val email: String,
    @SerializedName("password") val password: String
)
