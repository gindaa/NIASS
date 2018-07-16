package idev.gin.nias.dao




data class FaskesDao(
        val status: String,
        val result: Result
)

data class Result(
        val total: Int,
        val perPage: Int,
        val page: String,
        val lastPage: Int,
        val data: List<Data>
)

data class Data(
        val id: Int,
        val tanggal: String,
        val nama_faskes: String,
        val kabupaten: String,
        val provinsi: String,
        val no_registrasi_faskes: String,
        val no_registrasi_tbkota: String,
        val nama_pasien: String,
        val nik: String,
        val jenis_kelamin: String,
        val umur: String,
        val alamat: String,
        val rtrw: String,
        val kelurahan: String,
        val kecamatan: String,
        val kabupatenkontak: String,
        val provinsi_alamat: String,
        val perujuk: String,
        val tipe_diagnosis_tb: String,
        val klasifikasi_lokasi_anatomi: String,
        val klasifikasi_status_hiv: String,
        val klasifikasi_riwayat_pengobatan: Any,
        val skoring_tb: String,
        val tanggal_mulai_pengobatan: String,
        val panduan_oat: String,
        val sumber_obat: String,
        val hasil_mikroskopis: String,
        val hasil_tes_cepat: String,
        val hasil_biarkan: String,
        val noreglab_bulan2: String,
        val mikroskopis_bulan2: String,
        val noreglab_bulan3: String,
        val mikroskopis_bulan3: String,
        val noreglab_bulan5: String,
        val mikroskopis_bulan5: String,
        val noreglab_akhir: String,
        val mikroskopis_akhir: String,
        val tanggal_akhir: String,
        val hasil_akhir: String,
        val tanggal_dianjurkan_hiv: String,
        val tanggal_tes_hiv: String,
        val hasil_tes_hiv: String,
        val ppk: String,
        val art: String,
        val dm: String,
        val terapi_dm: String,
        val dipindah_ke_tb: String,
        val keterangan: String,
        val lokasi: String
)