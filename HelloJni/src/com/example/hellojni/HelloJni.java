/* 此範例使用JNI 來Call C的函數
 * 步驟如下:下面檔案都已經包含在此專案內
 * 1. 在Linux底下 的android-ndkxxx 底下(可在老師的software裡面有壓縮檔 應該也可在android官網下載)
 * 2. /sample/底下建立一個專案名稱目錄 並用terminal 進入此目錄 
 * 3. 此目錄下面建立一個/jni目錄  裡面有兩個檔案 xxxx.c (想要編譯的c檔案) Android.mk(設定檔 點入看即知道)
 * 4. 在專案目錄下 terminal 執行../../ndk-build  (因為隔兩個目錄下才有此執行檔)
 * 5. 會在專案目錄下 建立 libs 與 obj 目錄
 * 6. 將此三個目錄拷貝到 Android 專案內 注意package activity名稱須要與之前c 檔案內設定相同
 * 7. java 檔案請參考 書本內設定 (可能需要看一下 官網如何寫)
 * 8. 編譯並執行在模擬器上可看到結果 
 */

package com.example.hellojni;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class HelloJni extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello_jni);
        TextView tv = new TextView(this);
        tv.setText( stringFromJNI());
        setContentView(tv);
        
    }
    public native String stringFromJNI();
    
    static {
    	System.loadLibrary("hello-jni");
    }
}
