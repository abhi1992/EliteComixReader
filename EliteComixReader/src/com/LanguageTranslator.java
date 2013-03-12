package com;

import com.memetix.mst.language.Language;
import com.memetix.mst.translate.Translate;
import java.util.Map;
import java.util.Set;


public class LanguageTranslator {
    private Language language1, language2;
    
    public LanguageTranslator(String clientId, String clientSecret) {
        setId(clientId, clientSecret);
    }
    
    public LanguageTranslator() {}
    
    public String Translate(String text) throws Exception {
        return Translate.execute(text, language1, language2); 
    }
    
    public Language getLanguage1() {
        return language1;
    }

    public Language getLanguage2() {
        return language2;
    }
    
    public static void setId(String clientId, String clientSecret) {
        Translate.setClientId(clientId);
        Translate.setClientSecret(clientSecret);
    }
    
    public static String[] getAvailableLanguages() throws Exception{
        
        String arr[] = new String[Language.values().length];
        int i = 0;
        for(Language l : Language.values()) {
            switch(l) {
                case ARABIC :
                    arr[i] = "ARABIC";
                    break;
                case AUTO_DETECT :
                    arr[i] = "AUTO_DETECT";
                    break;
                case BULGARIAN :
                    arr[i] = "BULGARIAN";
                    break;
                case CATALAN :
                    arr[i] = "CATALAN";
                    break;
                case CHINESE_SIMPLIFIED :
                    arr[i] = "CHINESE_SIMPLIFIED";
                    break;
                case CHINESE_TRADITIONAL :
                    arr[i] = "CHINESE_TRADITIONAL";
                    break;
                case CZECH :
                    arr[i] = "CZECH";
                    break;
                case DANISH :
                    arr[i] = "DANISH";
                    break;
                case DUTCH :
                    arr[i] = "DUTCH";
                    break;
                case ENGLISH :
                    arr[i] = "ENGLISH";
                    break;
               case ESTONIAN :
                    arr[i] = "ESTONIAN";
                    break; 
                case FINNISH :
                    arr[i] = "FINNISH";
                    break;
                case FRENCH :
                    arr[i] = "FRENCH";
                    break;
                case GERMAN :
                    arr[i] = "GERMAN";
                    break;
                case GREEK :
                    arr[i] = "GREEK";
                    break;
                case HAITIAN_CREOLE :
                    arr[i] = "HAITIAN_CREOLE";
                    break;
                case HEBREW :
                    arr[i] = "HEBREW";
                    break;
                case HINDI :
                    arr[i] = "HINDI";
                    break;
                case HMONG_DAW :
                    arr[i] = "HMONG_DAW";
                    break;
                case HUNGARIAN :
                    arr[i] = "HUNGARIAN";
                    break;
                case INDONESIAN :
                    arr[i] = "INDONESIAN";
                    break;
                case ITALIAN :
                    arr[i] = "ITALIAN";
                    break;
                case JAPANESE :
                    arr[i] = "JAPANESE";
                    break;
                case KOREAN :
                    arr[i] = "KOREAN";
                    break;
                case LATVIAN :
                    arr[i] = "LATVIAN";
                    break;
                case LITHUANIAN :
                    arr[i] = "LITHUANIAN";
                    break;
                case NORWEGIAN :
                    arr[i] = "NORWEGIAN";
                    break;
                case POLISH :
                    arr[i] = "POLISH";
                    break;
                case PORTUGUESE :
                    arr[i] = "PORTUGUESE";
                    break;
                case ROMANIAN :
                    arr[i] = "ROMANIAN";
                    break;
                case RUSSIAN :
                    arr[i] = "RUSSIAN";
                    break;
                case SLOVAK :
                    arr[i] = "SLOVAK";
                    break;
                case SLOVENIAN :
                    arr[i] = "SLOVENIAN";
                    break;
                case SPANISH :
                    arr[i] = "SPANISH";
                    break;
                case SWEDISH :
                    arr[i] = "SWEDISH";
                    break;
                case THAI :
                    arr[i] = "THAI";
                    break;
                case TURKISH :
                    arr[i] = "TURKISH";
                    break;
                case UKRAINIAN :
                    arr[i] = "UKRAINIAN";
                    break;
                case VIETNAMESE :
                    arr[i] = "VIETNAMESE";
                    break;
                default :
                    break;
            }
            i++;
        }
            
        return arr;
    }
    
    private Language getLanguageFromString(String l) {
        
        Language ll = null;
        switch(l) {
                case "ARABIC" :
                    ll = Language.ARABIC;
                    break;
                case "AUTO_DETECT" :
                    ll = Language.AUTO_DETECT;
                    break;
                case "BULGARIAN" :
                    ll = Language.BULGARIAN;
                    break;
                case "CATALAN" :
                    ll = Language.CATALAN;
                    break;
                case "CHINESE_SIMPLIFIED" :
                    ll = Language.CHINESE_SIMPLIFIED;
                    break;
                case "CHINESE_TRADITIONAL" :
                    ll = Language.CHINESE_TRADITIONAL;
                    break;
                case "CZECH" :
                    ll = Language.CZECH;
                    break;
                case "DANISH" :
                    ll = Language.DANISH;
                    break;
                case "DUTCH" :
                    ll = Language.DUTCH;
                    break;
                case "ENGLISH" :
                    ll = Language.ENGLISH;
                    break;
               case "ESTONIAN" :
                    ll = Language.ESTONIAN;
                    break; 
                case "FINNISH" :
                    ll = Language.FINNISH;
                    break;
                case "FRENCH" :
                    ll = Language.FRENCH;
                    break;
                case "GERMAN" :
                    ll = Language.GERMAN;
                    break;
                case "GREEK" :
                    ll = Language.GREEK;
                    break;
                case "HAITIAN_CREOLE" :
                    ll = Language.HAITIAN_CREOLE;
                    break;
                case "HEBREW" :
                    ll = Language.HEBREW;
                    break;
                case "HINDI" :
                    ll = Language.HINDI;
                    break;
                case "HMONG_DAW" :
                    ll = Language.HMONG_DAW;
                    break;
                case "HUNGARIAN" :
                    ll = Language.HUNGARIAN;
                    break;
                case "INDONESIAN" :
                    ll = Language.INDONESIAN;
                    break;
                case "ITALIAN" :
                    ll = Language.ITALIAN;
                    break;
                case "JAPANESE" :
                    ll = Language.JAPANESE;
                    break;
                case "KOREAN" :
                    ll = Language.KOREAN;
                    break;
                case "LATVIAN" :
                    ll = Language.LATVIAN;
                    break;
                case "LITHUANIAN" :
                    ll = Language.LITHUANIAN;
                    break;
                case "NORWEGIAN" :
                    ll = Language.NORWEGIAN;
                    break;
                case "POLISH" :
                    ll = Language.POLISH;
                    break;
                case "PORTUGUESE" :
                    ll = Language.PORTUGUESE;
                    break;
                case "ROMANIAN" :
                    ll = Language.ROMANIAN;
                    break;
                case "RUSSIAN" :
                    ll = Language.RUSSIAN;
                    break;
                case "SLOVAK" :
                    ll = Language.SLOVAK;
                    break;
                case "SLOVENIAN" :
                    ll = Language.SLOVENIAN;
                    break;
                case "SPANISH" :
                    ll = Language.SPANISH;
                    break;
                case "SWEDISH" :
                    ll = Language.SWEDISH;
                    break;
                case "THAI" :
                    ll = Language.THAI;
                    break;
                case "TURKISH" :
                    ll = Language.TURKISH;
                    break;
                case "UKRAINIAN" :
                    ll = Language.UKRAINIAN;
                    break;
                case "VIETNAMESE" :
                    ll = Language.VIETNAMESE;
                    break;
                default :
                    break;
            }
        return ll;
    }
    
    public void setLanguage1(String lang) {
        language1 = getLanguageFromString(lang);
    }
    
    public void setLanguage2(String lang) {
        language2 = getLanguageFromString(lang);
    }    
}
