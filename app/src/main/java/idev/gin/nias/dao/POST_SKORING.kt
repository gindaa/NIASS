package idev.gin.nias.dao

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class POST_SKORING {

    @SerializedName("kontak_tb")
    @Expose
    var kontakTb: String? = null
    @SerializedName("uji_tuberkulin")
    @Expose
    var ujiTuberkulin: String? = null
    @SerializedName("berat_badan")
    @Expose
    var beratBadan: String? = null
    @SerializedName("demam")
    @Expose
    var demam: String? = null
    @SerializedName("batuk_kronik")
    @Expose
    var batukKronik: String? = null
    @SerializedName("pembesaran_kelenjar")
    @Expose
    var pembesaranKelenjar: String? = null
    @SerializedName("pembengkakan_tulang")
    @Expose
    var pembengkakanTulang: String? = null
    @SerializedName("foto_toraks")
    @Expose
    var fotoToraks: String? = null
    @SerializedName("fk_register")
    @Expose
    var fkRegister: String? = null

    override fun toString(): String {
        return "POST_SKORING{" +
                "kontakTb='" + kontakTb +
                ", ujiTuberkulin='" + ujiTuberkulin +
                ", beratBadan='" + beratBadan +
                ", demam='" + demam +
                ", batukKronik='" + batukKronik +
                ", pembesaranKelenjar='" + pembesaranKelenjar +
                ", pembengkakanTulang='" + pembengkakanTulang +
                ", fotoToraks='" + fotoToraks +
                ", fkRegister='" + fkRegister +
                '}'.toString()
    }
}
