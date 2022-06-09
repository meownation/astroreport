package org.example;
public enum AstroSign {
    ARIES("Ovan"),
    TAURUS("Bik"),
    GEMINI("Blizanac"),
    CANCER("Rak"),
    LEO("Lav"),
    VIRGO("Devica"),
    LIBRA("Vaga"),
    SCORPIO("Škorpija"),
    SAGITTARIUS("Strelac"),
    CAPRICORN("Jarac"),
    AQUARIUS("Vodolija"),
    PISCES("Ribe");

    public final String srpski;

    private AstroSign(String srpski){
        this.srpski=srpski;
    }

}
