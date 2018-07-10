package idev.gin.nias.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class POST_AKUN {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("email")
        @Expose
        private String email;
        @SerializedName("nama")
        @Expose
        private String nama;
        @SerializedName("jenis_kelamin")
        @Expose
        private String jenisKelamin;
        @SerializedName("tempat_lahir")
        @Expose
        private String tempatLahir;
        @SerializedName("tanggal_lahir")
        @Expose
        private String tanggalLahir;
        @SerializedName("no_hp")
        @Expose
        private String noHp;
        @SerializedName("password")
        @Expose
        private String password;
        @SerializedName("role")
        @Expose
        private String role;
        @SerializedName("point")
        @Expose
        private Integer point;
        @SerializedName("created_at")
        @Expose
        private String createdAt;
        @SerializedName("updated_at")
        @Expose
        private String updatedAt;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getNama() {
            return nama;
        }

        public void setNama(String nama) {
            this.nama = nama;
        }

        public String getJenisKelamin() {
            return jenisKelamin;
        }

        public void setJenisKelamin(String jenisKelamin) {
            this.jenisKelamin = jenisKelamin;
        }

        public String getTempatLahir() {
            return tempatLahir;
        }

        public void setTempatLahir(String tempatLahir) {
            this.tempatLahir = tempatLahir;
        }

        public String getTanggalLahir() {
            return tanggalLahir;
        }

        public void setTanggalLahir(String tanggalLahir) {
            this.tanggalLahir = tanggalLahir;
        }

        public String getNoHp() {
            return noHp;
        }

        public void setNoHp(String noHp) {
            this.noHp = noHp;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }

        public Integer getPoint() {
            return point;
        }

        public void setPoint(Integer point) {
            this.point = point;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }



    @Override
    public String toString(){
        return "Post{"+ "email" + email
                +"nama" + nama
                +"jenis_kelamin" + jenisKelamin
                +"tempat_lahir" + tempatLahir
                + "tanggal_lahir" + tanggalLahir
                + "no_hp" + noHp
                + "password " + password
                + "role" + role +
                "}";
    }
}
