package idev.gin.nias.data.remote;

import idev.gin.nias.dao.POST_FASKES;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;

public interface APIServiceFaskes {

    @retrofit2.http.POST("/faskes")
    @FormUrlEncoded
    Call<POST_FASKES> savePost(@Field("tanggal") String tanggal,
                               @Field("nama_faskes") String namaFaskes,
                               @Field("kabupaten") String kabupaten,
                               @Field("provinsi") String provinsi,
                               @Field("no_registrasi_faskes") String noRegistrasiFaskes,
                               @Field("no_registrasi_tbkota") String noRegistrasiTbkota,
                               @Field("nama_pasien") String namaPasien,
                               @Field("nik") String nik,
                               @Field("jenis_kelamin") String jenisKelamin,
                               @Field("umur") String umur,
                               @Field("alamat") String alamat,
                               @Field("rtrw") String rtrw,
                               @Field("kelurahan") String kelurahan,
                               @Field("kecamatan") String kecamatan,
                               @Field("kabupatenkontak") String kabupatenKontak,
                               @Field("perujuk") String perujuk,
                               @Field("tipe_diagnosis_tb") String tipeDiagnosisTb,
                               @Field("klasifikasi_lokasi_anatomi") String klasisfikasiLokasiAnatomi,
                               @Field("skoring_tb") String skoringTb,
                               @Field("tanggal_mulai_pengobatan") String tanggalMulaiPengobatan,
                               @Field("panduan_oat") String paduabObat,
                               @Field("sumber_obat") String sumberObat,
                               @Field("hasil_mikroskopis") String hasilMikroskopis,
                               @Field("hasil_tes_cepat") String hasilTesCepat,
                               @Field("hasil_biarkan") String hasilBiarkan,
                               @Field("noreglab_bulan2") String noreglabBulan2,
                               @Field("mikroskopis_bulan2") String mikroskopisBulan2,
                               @Field("noreglab_bulan3") String noreglabBulan3,
                               @Field("mikroskopis_bulan3") String mikroskopisBulan3,
                               @Field("noreglab_bulan5") String noreglabBulan5,
                               @Field("mikroskopis_bulan5") String mikroskopisBulan5,
                               @Field("noreglab_akhir") String noreglabAkhir,
                               @Field("mikroskopis_akhir") String mikroskopisAkhir,
                               @Field("tanggal_akhir") String tanggalAkhir,
                               @Field("hasil_akhir") String hasilAkhir,
                               @Field("tanggal_dianjurkan_hiv") String tanggalDianjurkanHivl,
                               @Field("hasil_tes_hiv") String hasilTesHiv,
                               @Field("ppk") String ppk,
                               @Field("art") String art,
                               @Field("dm") String dm,
                               @Field("terapi_dm") String terapiDm,
                               @Field("dipindah_ke_tb") String dipindahKeTb,
                               @Field("keterangan") String keterangan,
                               @Field("fk_faskes") String fkFaskes,
                               @Field("status") String status);

}
