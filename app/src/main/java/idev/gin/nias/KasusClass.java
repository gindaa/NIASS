package idev.gin.nias;

public class KasusClass {
    private String mTextregis, mTextNama, mTextjk;

    public KasusClass(String textfaskes, String textnama, String textnik) {
        mTextNama = textfaskes;
        mTextregis = textnik;
        mTextjk = textnama;

    }

    public String getmTextregis() {
        return mTextregis;
    }

    public String getmTextNama() {
        return mTextNama;
    }

    public String getmTextjk() {
        return mTextjk;
    }
}