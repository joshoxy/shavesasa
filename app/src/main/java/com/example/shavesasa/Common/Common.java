package com.example.shavesasa.Common;

import com.Model.Barbershop;
import com.Model.User;

public class Common {
    public static final String KEY_ENABLE_BUTTON_NEXT = "ENABLE_BUTTON_NEXT" ;
    public static final String KEY_BARBERSHOP_STORE = "BARBERSHOP_SAVE" ;
    public static final String KEY_BARBER_LOAD_DONE = "BARBER_LOAD_DONE";
    public static String IS_LOGIN = "IsLogin";
    public static User currentUser;
    public static Barbershop currentBarbershop;
    public static int step = 0;
    public static String city = "";
}
