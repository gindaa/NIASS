package idev.gin.nias.dao

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class POST_AKUN {

    @SerializedName("id")
    @Expose
    var id: Int? = null
    @SerializedName("email")
    @Expose
    var email: String? = null
    @SerializedName("nama")
    @Expose
    var nama: String? = null
    @SerializedName("jenis_kelamin")
    @Expose
    var jenisKelamin: String? = null
    @SerializedName("tempat_lahir")
    @Expose
    var tempatLahir: String? = null
    @SerializedName("tanggal_lahir")
    @Expose
    var tanggalLahir: String? = null
    @SerializedName("no_hp")
    @Expose
    var noHp: String? = null
    @SerializedName("password")
    @Expose
    var password: String? = null
    @SerializedName("role")
    @Expose
    var role: String? = null
    @SerializedName("point")
    @Expose
    var point: Int? = null
    @SerializedName("created_at")
    @Expose
    var createdAt: String? = null
    @SerializedName("updated_at")
    @Expose
    var updatedAt: String? = null


    override fun toString(): String {
        return ("Post{" + "email" + email
                + "nama" + nama
                + "jenis_kelamin" + jenisKelamin
                + "tempat_lahir" + tempatLahir
                + "tanggal_lahir" + tanggalLahir
                + "no_hp" + noHp
                + "password " + password
                + "role" + role +
                "}")
    }
}
