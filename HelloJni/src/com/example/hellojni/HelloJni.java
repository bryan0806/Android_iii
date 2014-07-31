/* ���d�Ҩϥ�JNI ��Call C�����
 * �B�J�p�U:�U���ɮ׳��w�g�]�t�b���M�פ�
 * 1. �bLinux���U ��android-ndkxxx ���U(�i�b�Ѯv��software�̭������Y�� ���Ӥ]�i�bandroid�x���U��)
 * 2. /sample/���U�إߤ@�ӱM�צW�٥ؿ� �å�terminal �i�J���ؿ� 
 * 3. ���ؿ��U���إߤ@��/jni�ؿ�  �̭�������ɮ� xxxx.c (�Q�n�sĶ��c�ɮ�) Android.mk(�]�w�� �I�J�ݧY���D)
 * 4. �b�M�ץؿ��U terminal ����../../ndk-build  (�]���j��ӥؿ��U�~����������)
 * 5. �|�b�M�ץؿ��U �إ� libs �P obj �ؿ�
 * 6. �N���T�ӥؿ������� Android �M�פ� �`�Npackage activity�W�ٶ��n�P���ec �ɮפ��]�w�ۦP
 * 7. java �ɮ׽аѦ� �ѥ����]�w (�i��ݭn�ݤ@�U �x���p��g)
 * 8. �sĶ�ð���b�������W�i�ݨ쵲�G 
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
