package idev.gin.nias.dao

// TODO: Convert aja lagi ke java, gua gada POJO generator

data class LoginDao(
        val type: String,
        val token: String,
        val refreshToken: Any
)