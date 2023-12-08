package davi.evelyn.harian.wilsiman.teachhelp.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Classe de suporte as configurações da app
 */
public class Config {

    // endereço base do servidor web
    public static String TEACHHELP_APP_URL = "https://teachhelp-a66yxe07.b4a.run/mobile/";

    public static String TEACHHELP_APP_RAIZ_URL = "https://teachhelp-a66yxe07.b4a.run/";


    public static void setLogin(Context context, String email) {
        SharedPreferences mPrefs = context.getSharedPreferences("configs", 0);
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putString("email", email).commit();
    }

    /**
     * Obtem o login
     * @param context
     * @return
     */
    public static String getLogin(Context context) {
        SharedPreferences mPrefs = context.getSharedPreferences("configs", 0);
        return mPrefs.getString("email", "");
    }


    public static void setPassword(Context context, String senha) {
        SharedPreferences mPrefs = context.getSharedPreferences("configs", 0);
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putString("senha", senha).commit();
    }

    /**
     * @param context
     * @return
     */
    public static String getPassword(Context context) {
        SharedPreferences mPrefs = context.getSharedPreferences("configs", 0);
        return mPrefs.getString("senha", "");
    }
}
