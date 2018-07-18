package idev.gin.nias.dao


data class PelacakanDao(
    val status: String,
    val result: Result
)

data class ResultPelacakan(
    val total: Int,
    val perPage: Int,
    val page: String,
    val lastPage: Int,
    val data: List<Data>
)

data class DataPelacakan(
    val id: Int,
    val tanggal_wawancara: String,
    val unit_pelayanan: String,
    val kabupaten: String,
    val nama_kontak: String,
    val tanggal_lahir_kontak: String,
    val jeniskelamin_kontak: String,
    val lat: String,
    val long: String,
    val kontak_serumah: Int,
    val kontak_erat: Int,
    val gejala_tb: String,
    val tanggal_penyuntikan_uji: String,
    val tanggal_baca_uji: String,
    val diameter_indurasi: String,
    val rontgen_dada: String,
    val tanggal_bta: String,
    val hasil_bta: String,
    val tanggal_xpert: String,
    val hasil_xpert: String,
    val tanggal_biakan: String,
    val hasil_biakan: String,
    val hasil_akhir: String,
    val faktor_resiko: String,
    val tindak_lanjut: String,
    val status: String,
    val fk_faskes: Int
)