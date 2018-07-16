package idev.gin.nias.dao


data class RiwayatDao(
    val status: String,
    val result: ResultDao
)

data class ResultDao(
    val total: Int,
    val perPage: Int,
    val page: String,
    val lastPage: Int,
    val data: List<DataDao>
)

data class DataDao(
    val id: Int,
    val nama_kader: String,
    val desa: String,
    val tanggal: String,
    val nama_orantua: String,
    val nama_anak: String,
    val usia_anak: Int,
    val jumlah_anak: Int,
    val alamat_desa: String,
    val lat: String,
    val long: String,
    val kontak_tb: String,
    val berat_badan_59bulan: String,
    val berat_badan_14tahun: String,
    val demam: String,
    val batuk: String,
    val pembesaran_kelenjar_limfe: String,
    val pembesaran_tulang: String,
    val fk_faskes: Int
)