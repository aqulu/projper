package lu.aqu.projper.auth.infra.api.json

import com.google.gson.annotations.SerializedName

data class LogoutResponseJson(
    @SerializedName("status") val status: String?
)
