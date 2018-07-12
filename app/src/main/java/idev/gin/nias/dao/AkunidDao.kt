package idev.gin.nias.dao

data class AkunidDao(
        val status: String,
        val result: List<Result>
)

data class Result(
        val id: Int,
        val email: String,
        val nama: String,
        val jenis_kelamin: String,
        val tempat_lahir: String,
        val tanggal_lahir: String,
        val no_hp: String,
        val password: String,
        val role: String,
        val point: Int,
        val created_at: String,
        val updated_at: String
)